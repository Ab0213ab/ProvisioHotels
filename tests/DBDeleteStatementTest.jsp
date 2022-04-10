<!--
	Test insert statements
-->
<%@ 
	page 
	language="java" 
	contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "
    			Provisio.DBDeleteStatement,
    			Provisio.DBHelper
    		"
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>DB Update Statement Tests</title>
	</head>
	<body>
		<pre>
			<%
				// Update a record:
				out.println("-------------- Test 1 ----------------");
				DBDeleteStatement delete_statement = new DBDeleteStatement();

				delete_statement
					.fromTable("user_provisio_points")
					.where(
						new String[] {
							"id = 7"
						}
					);

				try {
					DBHelper.deleteStatement(delete_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}

				// Try without WHERE:
				out.println("");
				out.println("-------------- Test 2 ----------------");
				delete_statement = new DBDeleteStatement();

				delete_statement
					.fromTable("user_provisio_points");

				try {
					DBHelper.deleteStatement(delete_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}

				// Simulate SQL Injection:
				out.println();
				out.println("-------------- Test 3 ----------------");
				delete_statement = new DBDeleteStatement();

				delete_statement
					.fromTable("user_provisio_points")
					.where(
						new String[] {
							"user_id = ?", "' OR 1=1"
						}
					);

				try {
					DBHelper.deleteStatement(delete_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}
			%>
		</pre>
	</body>
</html>

