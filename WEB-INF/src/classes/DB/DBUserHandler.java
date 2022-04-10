/**
 * Handles registration and login stuff
 */
package Provisio;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.lang.NumberFormatException;
import java.lang.ClassNotFoundException;
import java.sql.SQLException;
import java.util.Arrays;

public class DBUserHandler {
	public DBUserHandler(){}

	/**
	 * Check if valid login
	 * @param user_email : String
	 * @param user_password : String
	 * 
	 * NOTE: returns -1 for invalid login
	 * 
	 * @return Integer (user id)
	 */
	public static Integer loginUser(
		String user_email,
		String user_password
	){
		// See if user exists:
		try {
			DBSelectStatement select_statement = new DBSelectStatement();
			select_statement
				.fromTable("users")
				.columns(
					new String[] {
						"salt"
					}
				)
				.where(
					new String[] {
						"email = ?", user_email
					}
				);

			DBResult result = DBHelper.selectStatement(select_statement);
			ArrayList<Hashtable<String, String>> records = result.getRecords();

			if (records.size() == 0)
				return -1;

			// Get salt:
			String salt = records.get(0).get("salt");

			// Hash user specified password with salt:
			String user_password_hash = PasswordHasher.hash(
				user_password,
				salt
			);

			select_statement = new DBSelectStatement();
			select_statement
				.fromTable("users")
				.columns(
					new String[] {
						"id"
					}
				)
				.where(
					new String[] {
						"email = ?", user_email
					}
				)
				.where(
					new String[] {
						"password = ?", user_password_hash
					}
				);

			result = DBHelper.selectStatement(select_statement);
			records = result.getRecords();

			if (records.size() == 0)
				return -1;

			// Use first record:
			return Integer.parseInt(records.get(0).get("id"));
		} catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | NumberFormatException e){
			System.out.println("userLogin() failed: ");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Register a user
	 * 
	 * NOTE: returns -1 if fail
	 */
	public static Integer registerUser(
		String first_name,
		String last_name,
		String user_email,
		String user_password
	){
		try {
			// First create a salt:
			String salt = PasswordHasher.generateSalt(32);

			// Hash the password:
			String user_password_hash = PasswordHasher.hash(
				user_password,
				salt
			);

			DBInsertStatement insert_statement = new DBInsertStatement();

			// Specify the column names:
			ArrayList<String> column_names = new ArrayList(
				Arrays.asList(
					"first_name",
					"last_name",
					"email",
					"password",
					"salt"
				)
			);

			// Specify the values:
			ArrayList<String> values = new ArrayList(
				Arrays.asList(
					first_name,
					last_name,
					user_email,
					user_password_hash,
					salt
				)
			);

			insert_statement
				.intoTable("users")
				.columns(column_names)
				.values(values);

			DBHelper.insertStatement(insert_statement);

			// Get last inserted ID:
			DBSelectStatement select_statement = new DBSelectStatement();
			select_statement
				.fromTable("users")
				.columns(
					new String[] {
						"id"
					}
				)
				.where(
					new String[] {
						"email = ?", user_email
					}
				);

			DBResult result = DBHelper.selectStatement(select_statement);
			ArrayList<Hashtable<String, String>> records = result.getRecords();

			System.out.println("Insert ID select: ");
			System.out.println(records);

			// Make sure something got returned:
			if (records.size() == 0)
				return -1;

			String user_id = records.get(0).get("id");

			// Now insert a record for them for provisio points:
			insert_statement = new DBInsertStatement();
			column_names = new ArrayList(
				Arrays.asList(
					"user_id",
					"points"
				)
			);

			values = new ArrayList(
				Arrays.asList(
					user_id,
					"0" // Default to 0 points
				)
			);

			insert_statement
				.intoTable("user_provisio_points")
				.columns(column_names)
				.values(values);

			DBHelper.insertStatement(insert_statement);

			return Integer.parseInt(user_id);
		} catch (SQLException | ClassNotFoundException | NoSuchAlgorithmException | NumberFormatException e){
			System.out.println("registerUser() failed.");
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Check if user exists
	 */
	public static Boolean userExists(
		String user_email
	){
		try {
			DBSelectStatement select_statement = new DBSelectStatement();
			select_statement
				.fromTable("users")
				.where(
					new String[] {
						"email = ?", user_email
					}
				);

			DBResult result = DBHelper.selectStatement(select_statement);
			ArrayList<Hashtable<String, String>> records = result.getRecords();

			System.out.println(records);

			if (records.size() == 0)
				return false;

			return true;
		} catch (SQLException | ClassNotFoundException e){
			System.out.println("userExists() failed.");
			e.printStackTrace();
			return true;
		}
	}
}