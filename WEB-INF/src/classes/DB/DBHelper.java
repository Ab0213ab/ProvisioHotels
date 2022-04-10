/**
 * Helper for DB queries
 */
package Provisio;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.ClassNotFoundException;
import java.sql.ResultSetMetaData;

public class DBHelper {
	// Set connection details here:
	private final static String db_url = "jdbc:mysql://localhost:3306/provisio?serverTimezone=UTC";
	private final static String db_username = "root";
	private final static String db_password = "root";

	public DBHelper(){}

	/**
	 * Select from table with WHERE clause
	 * @param table_name : name of table
	 * @param columns : array of columns to be retrieved
	 * @param where : array of where clause components (e.g. [["field1 = 2"], ["field2 LIKE ?", arg], ["field3 = ?", arg], ["field4 = 2 OR field5 = 3"]])
	 * @return ResultSet | null
	 */
	public static DBResult selectStatement(
		final DBSelectStatement select_statement_object
	) throws ClassNotFoundException, SQLException {
		// Abort if requirements aren't met:
		if (select_statement_object.getTableName() == null)
				throw new SQLException("Must specify a table name.");

		Connection connection = DBHelper.getConnection();

		if (connection == null)
			throw new SQLException("Connection failed to connect in selectStatement().");

		// If no columns were specified, then default to all:
		String columns = "*";

		if (select_statement_object.getColumnsAsString() != null)
			columns = select_statement_object.getColumnsAsString();

		String sql_select_statement = "SELECT " +
											columns +
									  " FROM " + select_statement_object.getTableName();

		// Add in join clause if applicable:
		if (select_statement_object.didSpecifyJoinClause())
			sql_select_statement += select_statement_object.getJoinClause();

		// Add where clause if applicable:
		if (select_statement_object.didSpecifyWhereClause())
			sql_select_statement += " WHERE " + select_statement_object.getWhereClause();

		System.out.println(sql_select_statement);

		PreparedStatement prepared_statement = connection.prepareStatement(sql_select_statement);

		ArrayList<String> where_clause_arguments = select_statement_object.getWhereClauseArguments();
		if (where_clause_arguments != null){
			for (int i = 0; i < where_clause_arguments.size(); i++){
				System.out.println("WHERE CLAUSE ARGUMENT: " + where_clause_arguments.get(i));
				prepared_statement.setString(i + 1, where_clause_arguments.get(i));
			}
		}

		ResultSet result_set = prepared_statement.executeQuery();
		DBResult db_result = new DBResult();
		ArrayList<Hashtable<String, String>> records = new ArrayList<Hashtable<String, String>>();

		ResultSetMetaData result_set_metadata = result_set.getMetaData();
		int column_count = result_set_metadata.getColumnCount();

		while (result_set.next()){
			Hashtable<String, String> rec = new Hashtable<String, String>();

			for (int i = 1; i <= column_count; i++){
				rec.put(
					result_set_metadata.getColumnName(i),
					result_set.getString(i)
				);
			}

			records.add(rec);
		}

		db_result.setRecords(records);

		connection.close();
		return db_result;
	}

	/**
	 * Insert into table
	 */
	public static void insertStatement(
		DBInsertStatement insert_statement_object
	) throws ClassNotFoundException, SQLException {
		// Abort if they passed in an invalid insert statement:
		if (insert_statement_object.isValidInsertStatement() == false)
			throw new SQLException("Invalid insert statement. Please verify and try again.");

		Connection connection = DBHelper.getConnection();

		if (connection == null)
			throw new SQLException("Connection failed to connect in insertStatement().");

		// Create a string of ? for each column name and value:
		ArrayList<String> column_names = insert_statement_object.getColumnNames();
		ArrayList<String> column_values = insert_statement_object.getColumnValues();
		String placeholders = "";

		for (int i = 0; i < column_names.size(); i++){
			if (i == 0)
				placeholders = "?";
			else
				placeholders += ",?";
		}

		String sql_insert_statement = "INSERT INTO " + insert_statement_object.getTableName() +
											" (" + insert_statement_object.getColumnNamesAsString() + ")" +
									  " VALUES (" + placeholders + ")";

		System.out.println(sql_insert_statement);

		PreparedStatement prepared_statement = connection.prepareStatement(sql_insert_statement);

		// Bind specified column values to placeholders:
		for (int i = 0; i < column_values.size(); i++)
			prepared_statement.setString(i + 1, column_values.get(i));

		prepared_statement.execute();
		connection.close();
	}

	/**
	 * Update table
	 */
	public static void updateStatement(
		DBUpdateStatement update_statement_object
	) throws ClassNotFoundException, SQLException {
		// Abort if invalid update statement:
		if (update_statement_object.isValidUpdateStatement() == false)
			throw new SQLException("Invalid update statement. Please verify and try again.");

		Connection connection = DBHelper.getConnection();

		if (connection == null)
			throw new SQLException("Connection failed to connect in updateStatement().");

		String sql_update_statement = "UPDATE " + update_statement_object.getTableName() +
											" SET " + update_statement_object.getSetPairsAsString() +
									  " WHERE " + update_statement_object.getWhereClause();

		PreparedStatement prepared_statement = connection.prepareStatement(sql_update_statement);

		ArrayList<String> set_pair_values = update_statement_object.getSetPairValuesAsArray();
		for (int i = 0; i < set_pair_values.size(); i++)
			prepared_statement.setString(i + 1, set_pair_values.get(i));

		ArrayList<String> where_clause_arguments = update_statement_object.getWhereClauseArguments();
		for (int i = set_pair_values.size(); i < where_clause_arguments.size() * 2; i++)
			prepared_statement.setString(i + 1, where_clause_arguments.get(i - set_pair_values.size()));

		prepared_statement.execute();
		connection.close();
	}

	/**
	 * Delete record(s)
	 */
	public static void deleteStatement(
		DBDeleteStatement delete_statement_object
	) throws ClassNotFoundException, SQLException {
		// Abort if invalid delete statement:
		if (delete_statement_object.isValidDeleteStatement() == false)
			throw new SQLException("Invalid delete statement. Please review and try again.");

		Connection connection = DBHelper.getConnection();

		if (connection == null)
			throw new SQLException("Connection failed to connect in updateStatement().");

		String sql_delete_statement = "DELETE FROM " + delete_statement_object.getTableName() + " WHERE " + delete_statement_object.getWhereClause();

		PreparedStatement prepared_statement = connection.prepareStatement(sql_delete_statement);

		ArrayList<String> where_clause_arguments = delete_statement_object.getWhereClauseArguments();
		for (int i = 0; i < where_clause_arguments.size(); i++)
			prepared_statement.setString(i + 1, where_clause_arguments.get(i));

		prepared_statement.execute();
		connection.close();
	}

	/**
	 * Create a DB connection and return it
	 * @return Connection|null
	 */
	private static Connection getConnection() throws ClassNotFoundException {
		try {
			Class.forName("com.mysql.jdbc.Driver");

			return DriverManager.getConnection(
				DBHelper.db_url,
				DBHelper.db_username,
				DBHelper.db_password
			);
		} catch (SQLException e){
			System.out.println("Failed to connect to DB: " + e.getMessage());
			return null;
		}
	}
}