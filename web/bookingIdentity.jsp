<%-- 
    Document   : bookingIdentity
    Created on : 21-Nov-2018, 01:21:37
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>


    <div class="main">

        <!--Return back to the booking servlet if the customer signs in-->
        <jsp:include page="login.jsp">
            <jsp:param name="reDirectOnSuccess" value="BookingFormServlet.do" />
        </jsp:include>

        <center>
            <input type="button" class="btn" onclick="location.href = 'guest.jsp';" value="Book as Guest" />
            <input type="button" class="btn" onclick="location.href = 'register.jsp';" value="Register for Account" /></center>
    </div>
</html>
