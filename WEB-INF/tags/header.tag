<%@tag description="Header Template" pageEncoding="UTF-8"%>
<%@attribute name="title" fragment="true" %>
<%@attribute name="styles" fragment="true" %>
<%@attribute name="header_title" fragment="true" %>
<%@attribute name="page_title" fragment="true" %>
<%@attribute name="body" fragment="true" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<title><jsp:invoke fragment="title" /></title> 
		<link rel="stylesheet" type="text/css" crossorigin="anonymous" href="/Provisio/css/styles.css"/>
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

		<jsp:invoke fragment="styles" />
	</head>
<body>
	<div class='container'>  
	    <div id="mySidebar" class="sidebar">
	        <div class="closebtn" onclick="closeNav()"><i class="fa fa-times"></i></div>
	        <h2 id="nav-bar-top">
	            <a style="color:black;" href="/Provisio/login">Login/Register</a>
	        </h2>
	        <div class="nav-item"><a href="/Provisio/jsp/index.jsp">Home</a></div>
	        <div class="nav-item"><a href="#">About Us</a></div>
	        <div class="nav-item"><a href="#">Locations</a></div>
	        <div class="nav-item"><a href="#">Book Your Vacation</a></div>
	        <div class="nav-item"><a href="#">Lookup Your Vacation</a></div>
	        <div class="nav-item"><a href="#">Provisio Points</a></div>
	    </div>

	    <div id="main">
	        <button class="openbtn" onclick="openNav()">
	            <strong>&gt</strong>
	        </button>
	        <div class='top-bar'>
	            <div class="header-content_2" id="brand">
	            	<h1>
	            		<jsp:invoke fragment="header_title" />
	            	</h1>
	            	<% 
	            		if (
	            			session.getAttribute("user_email") != null
	            		){
	            	%>
		            	<div id="user-profile">
		            		<a id="user-profile-anchor" onclick="openForm()">
		            			<i class="fa fa-user-circle fa-2x" aria-hidden="true"></i>
		            		</a>

		            		<div class="form-popup" id="myForm">
								<form action="/Provisio/logout" class="form-container">

									<center>
										<h1><i class="fa fa-user-circle" aria-hidden="true"></i></h1>
										<label for="userE">
											<b>
												<%= session.getAttribute("user_email") %>
											</b>
										</label>
										<br>
										<br>
									</center>

									<button type="button" class="btn">Provisio Points</button>
									<button type="submit" class="btn logout"><br>Logout</button>

								</form>
							</div>
		            	</div>
		            <%
		            	}
		            %>
	            </div>
	        </div>
	        <div class="page-heading">
	        	<h3>
	        		<jsp:invoke fragment="page_title" />
	        	</h3>
	        </div>

			<jsp:invoke fragment="body" />
	    </div>
	</div>

	<!-- Functions for collapsing side nav bar -->
	<script>
	    function openNav() {
	    document.getElementById("mySidebar").style.width = "250px";
	    document.getElementById("main").style.marginLeft = "250px";
	    }
	    
	    function closeNav() {
	    document.getElementById("mySidebar").style.width = "0";
	    document.getElementById("main").style.marginLeft= "0";
	    }

	    <% if (session.getAttribute("user_email") != null){ %>
	    	let shouldOpen = true;
	    	function openForm() {
	    		if (shouldOpen) {
	    			shouldOpen = false;
			  		document.getElementById("myForm").style.display = "block";
	    		} else {
	    			shouldOpen = true;
			  		document.getElementById("myForm").style.display = "none";
			  	}
			}

			function closeForm() {
			  document.getElementById("myForm").style.display = "none";
			}
		<% } %>
	</script>
</body>
</html>