<%@ 
	page 
	language="java" 
	contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
%>
<%@ 
	taglib 
	uri="http://java.sun.com/jsp/jstl/core" 
	prefix="c"
%>

<%@
	taglib 
	prefix="t" 
	tagdir="/WEB-INF/tags" 
%>

<t:header>
    <jsp:attribute name="title">
        Provisio Login
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <style>
            .error-message {
                color: red;
                margin-bottom: 30px;
            }

            #entry-field {
                display: flex;
                align-items: center;
                flex-direction: column;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Provisio Customer Login
    </jsp:attribute>
    <jsp:attribute name="body">
        <div id="entry-field">
            <p>To login, enter your email address and password in the fields <br> below and then click the submit button.</p><br><br>

            <c:if test="${requestScope.error_message != null}">
                <div class="error-message">${requestScope.error_message}</div>
            </c:if>

            <form action="/Provisio/login" method="POST">
                <label for="emailA">Email Address:</label>
                <input type="text" id="emailA" name="username">
                <br><br>
                <label for="passW">Password:</label>
                <input type="password" id="passW" name="password">
                <br><br>
                <button type="submit" value="submit">Submit</button>
                <br><br>
                <a href="/Provisio/jsp/register.jsp">New user? Click here.</a>
            </form>
        </div>
    </jsp:attribute>
</t:header>
