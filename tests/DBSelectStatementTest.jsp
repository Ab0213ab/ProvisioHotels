<!--
	Test select statements
-->
<%@ 
	page 
	language="java" 
	contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "
    			java.io.IOException,
    			jakarta.servlet.jsp.JspWriter,
    			java.util.ArrayList,
    			java.util.Set,java.util.Hashtable,
    			Provisio.DBResult,
    			Provisio.DBSelectStatement,
    			Provisio.DBHelper
    		"
%>

<%!
	/**
	 * Print out records returned from query
	 */
	public void printRecords(
		ArrayList<Hashtable<String, String>> records,
		JspWriter out
	) throws IOException {
		for (Hashtable<String, String> rec: records){
			Set<String> column_names = rec.keySet();

			for (String column_name: column_names){
				out.println("[ " + column_name + " ] = " + rec.get(column_name));
			}
		}
	}
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>DB Select Statement Tests</title>
	</head>
	<body>
		<pre>
			<%
				// Display all records:
				out.println("-------------- Test 1 ----------------");
				DBSelectStatement select_statement = new DBSelectStatement();
				select_statement.fromTable("users");

				DBResult result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Display specific columns:
				out.println();
				out.println("-------------- Test 2 ----------------");
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"first_name",
							"last_name"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Display specific columns and with where:
				out.println();
				out.println("-------------- Test 3 ----------------");
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"first_name"
						}
					)
					.where(
						new String[] {
							"last_name = 'McRib'"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Display specific columns, multiple wheres, and arguments:
				out.println();
				out.println("-------------- Test 4 ----------------");
				String username = "Bob";
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"password"
						}
					)
					.where(
						new String[] {
							"first_name = ?", username
						}
					)
					.where(
						new String[] {
							"last_name LIKE '%Mc%'"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Display specific columns, multiple wheres, and arguments with OR:
				out.println();
				out.println("-------------- Test 4 ----------------");
				username = "Bob";
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"password"
						}
					)
					.concatenateWithAnd(false)
					.where(
						new String[] {
							"first_name = ?", username
						}
					)
					.where(
						new String[] {
							"last_name LIKE '%Mc%'"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Display specific columns, multiple wheres, and arguments with OR and AND:
				out.println();
				out.println("-------------- Test 5 ----------------");
				username = "Bob";
				String last_name = "Mc";
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"first_name",
							"last_name"
						}
					)
					.where(
						new String[] {
							"first_name = ? AND last_name LIKE ? OR 1=1", username, "%" + last_name + "%"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Test joining tables:
				out.println();
				out.println("-------------- Test 6 ----------------");
				username = "Bob";
				last_name = "Mc";
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"users.first_name",
							"users.last_name",
							"user_reservations.check_in_date"
						}
					)
					.where(
						new String[] {
							"first_name = ? AND last_name LIKE ? OR 1=1", username, "%" + last_name + "%"
						}
					)
					.join(
						"user_reservations",
						new String[] {
							"users.id", "user_reservations.user_id"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);

				// Test joining multiple tables:
				out.println();
				out.println("-------------- Test 7 ----------------");
				username = "Bob";
				last_name = "Mc";
				select_statement = new DBSelectStatement();
				select_statement
					.fromTable("users")
					.columns(
						new String[] {
							"users.first_name",
							"users.last_name",
							"user_reservations.check_in_date",
							"user_provisio_points.points"
						}
					)
					.where(
						new String[] {
							"first_name = ? AND last_name LIKE ? OR 1=1", username, "%" + last_name + "%"
						}
					)
					.join(
						"user_reservations",
						new String[] {
							"users.id", "user_reservations.user_id"
						}
					)
					.join(
						"user_provisio_points",
						new String[] {
							"users.id", "user_provisio_points.user_id"
						}
					);

				result = DBHelper.selectStatement(select_statement);

				printRecords(result.getRecords(), out);
			%>
		</pre>
	</body>
</html>

