<!--
	Test insert statements
-->
<%@ 
	page 
	language="java" 
	contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
    import = "
    			Provisio.DBInsertStatement,
    			Provisio.DBHelper,
    			java.util.ArrayList,
    			java.util.Arrays
    		"
%>

<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>DB Insert Statement Tests</title>
	</head>
	<body>
		<pre>
			<%
				// Display all records:
				out.println("-------------- Test 1 ----------------");
				DBInsertStatement insert_statement = new DBInsertStatement();
				ArrayList<String> columns = new ArrayList(
					Arrays.asList(
						"user_id",
						"points"
					)
				);

				ArrayList<String> values = new ArrayList(
					Arrays.asList(
						"1",
						"10"
					)
				);

				insert_statement
					.intoTable("user_provisio_points")
					.columns(
						columns
					)
					.values(
						values
					);

				try {
					DBHelper.insertStatement(insert_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}

				// Try to error it out:
				out.println("");
				out.println("-------------- Test 2 ----------------");
				insert_statement = new DBInsertStatement();
				columns = new ArrayList(
					Arrays.asList(
						"user_id",
						"points",
						"extra"
					)
				);

				values = new ArrayList(
					Arrays.asList(
						"1",
						"10"
					)
				);

				insert_statement
					.intoTable("user_provisio_points")
					.columns(
						columns
					)
					.values(
						values
					);

				try {
					DBHelper.insertStatement(insert_statement);
					out.println("Success!");
				} catch (Exception e){
					out.println(e.getMessage());
				}
			%>
		</pre>
	</body>
</html>

