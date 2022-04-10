<!--
	Test insert statements
-->
<%@ 
	page 
	language="java" 
	contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "
    			Provisio.DBUpdateStatement,
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
				DBUpdateStatement update_statement = new DBUpdateStatement();

				update_statement
					.table("user_provisio_points")
					.set(
						new String[][] {
							new String[] {
								"points", "15"
							}
						}
					)
					.where(
						new String[] {
							"user_id = 3"
						}
					);

				try {
					DBHelper.updateStatement(update_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}

				// Try without WHERE:
				out.println("");
				out.println("-------------- Test 2 ----------------");
				update_statement = new DBUpdateStatement();

				update_statement
					.table("user_provisio_points")
					.set(
						new String[][] {
							new String[] {
								"points", "15"
							}
						}
					);

				try {
					DBHelper.updateStatement(update_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}

				// Try without any set pairs:
				out.println();
				out.println("-------------- Test 3 ----------------");
				update_statement = new DBUpdateStatement();

				update_statement
					.table("user_provisio_points")
					.set(
						new String[][] {
							new String[] {}
						}
					)
					.where(
						new String[] {
							"user_id = 3"
						}
					);

				try {
					DBHelper.updateStatement(update_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}

				// Simulate SQL Injection:
				out.println();
				out.println("-------------- Test 4 ----------------");
				update_statement = new DBUpdateStatement();

				update_statement
					.table("user_provisio_points")
					.set(
						new String[][] {
							new String[] {
								"points", "15"
							}
						}
					)
					.where(
						new String[] {
							"user_id = ?", "' OR 1=1"
						}
					);

				try {
					DBHelper.updateStatement(update_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}
			%>
		</pre>
	</body>
</html>

