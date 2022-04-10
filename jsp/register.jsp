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
        Provisio Registration
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
        <style> #regform{     width: 100%;     padding: 20px;     margin: 8px 0;     box-sizing: border-box;     text-align: center;     display: inline-block; }  input[type=text], input[type=password], input[type=submit] { padding: 10px; margin: 5px; font-size: medium; } 

            .error-message {
                color: red;
                text-align: center;
            }
        </style>
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Provisio Customer Registration
    </jsp:attribute>
    <jsp:attribute name="body">
        <p id="reg-message"><strong>To register, enter your information into the fields below and click the submit button. Note: password should be at least 8 characters in length and include one uppercase and one lowercase letter</strong></p>
        <div class="regisform">
            <c:if test="${requestScope.error_message != null}">
                <div class="error-message">${requestScope.error_message}</div>
            </c:if>
            <form id="regform" action="/Provisio/register" method="POST">
                <label for="fname">First Name </label>
                <input type="text" id="fname" name="fname"><br>
                <label for="lname">Last Name </label>
                <input type="text" id="lname" name="lname"><br>
                <label for="email">Email Address </label>
                <input type="text" id="email" name="email"><br>
                <label for="pass">Password </label>
                <input minlength="8" type="password" id="pass" name="pass"><br>
                <input type="submit" value="Submit">
            </form>
        </div>
    </jsp:attribute>
</t:header>
