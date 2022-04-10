/**
 * Contains all arguments needed for a where clause via DBHelper class
 */
package Provisio;

import java.util.ArrayList;
import java.sql.SQLException;

public class DBWhereClause implements DBStatementInterface, DBWhereClauseInterface {
	private String complete_where_clause;
	private ArrayList<String> complete_where_clause_arguments;
	private Boolean did_init_private_fields;
	private Boolean use_and;

	public DBWhereClause(){
		this.did_init_private_fields = false;
		this.use_and = true;
	}

	/**
	 * Adds a where clause statement
	 * 
	 * Example:
	 * 		where(
	 * 			["field1 = ?", arg]
	 * 		)
	 * 		.where(
	 * 			["field2 = 'test'"]
	 * 		);
	 * 
	 * 		"WHERE field1 = ? AND field2 = 'test'" (where ? is prepared param `arg`)
	 * 
	 * @param clause_statement : String = ["field1 = ?", arg] (or ["field1 = 2"])
	 * @return this
	 */
	public DBWhereClauseInterface where(
		String[] clause_statement
	) throws SQLException {
		// Just abort if they pass in an empty clause statement:
		if (clause_statement.length == 0)
			throw new SQLException("WHERE clause statement length was 0.");

		// Initalize private fields if applicable:
		if (this.did_init_private_fields == false){
			this.complete_where_clause = "";
			this.complete_where_clause_arguments = new ArrayList<String>();

			this.did_init_private_fields = true;
		}

		if (this.complete_where_clause.equals(""))
			this.complete_where_clause = clause_statement[0];
		else
			this.complete_where_clause += " " + this.getConcatenationOperator() + " " + clause_statement[0];

		// Add the rest as arguments:
		for (int i = 1; i < clause_statement.length; i++)
			this.complete_where_clause_arguments.add(clause_statement[i]);

		return this;
	}

	/**
	 * Specify whether where arguments should be concatenated
	 * with AND or OR
	 * @param use_and : if true, will use AND, otherwise OR
	 * @return this
	 */
	public DBWhereClauseInterface concatenateWithAnd(
		Boolean use_and
	){
		this.use_and = use_and;
		return this;
	}

	/**
	 * Get the complete where clause
	 * @return string | null
	 */
	protected String getWhereClause(){
		return this.complete_where_clause;
	}

	/**
	 * Get the complete list of where clause arguments
	 * @return ArrayList<String> | null
	 */
	protected ArrayList<String> getWhereClauseArguments(){
		return this.complete_where_clause_arguments;
	}

	/**
	 * Retrieve if where clause was specified
	 * @return Boolean
	 */
	protected Boolean didSpecifyWhereClause(){
		return this.did_init_private_fields;
	}

	/**
	 * Get the specified argument concatenation operator (AND or OR)
	 * @return String : " AND " or " OR "
	 */
	private String getConcatenationOperator(){
		return (this.use_and) ? " AND " : " OR ";
	}
}