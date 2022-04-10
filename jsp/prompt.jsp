<%@ 
    page 
    language="java" 
    contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"
%>

<%@
    taglib 
    prefix="t" 
    tagdir="/WEB-INF/tags" 
%>

<t:header>
    <jsp:attribute name="title">
        Provisio Lobby
    </jsp:attribute>
    <jsp:attribute name="styles">
        <!-- Any styles, cdns, or scripts go here -->
    </jsp:attribute>
    <jsp:attribute name="header_title">
        Provisio Hotels
    </jsp:attribute>
    <jsp:attribute name="page_title">
        Provisio Customer Lobby
    </jsp:attribute>
    <jsp:attribute name="body">
        <p id="reg-message"><strong>Success! What would you like to do next?</strong></p>

        <div class="prompt-item"><a href="ReservationBooking.jsp">Book a Vacation</a></div>
        <div class="prompt-item"><a href="#">Lookup a Vacation</a></div>
        <div class="prompt-item"><a href="#">Track Provisio Points</a></div>
        <div class="prompt-item"><a href="/Provisio/jsp/index.jsp">Home</a></div>
    </jsp:attribute>
</t:header>
