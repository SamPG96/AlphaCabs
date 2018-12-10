<%-- 
    Document   : regConfirm
    Created on : 21-Nov-2018, 01:27:46
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register confirmation</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>

    <jsp:include page="/common/head.jsp"/>

    <div class="main">
        <center><h1>Account created!</h1></center>
        <center><p>Issued username: <%= request.getAttribute("newUsername") %></p></center>
        <center><p>Your account is pending administrative approval</p></center>

        <form method="POST" action="BookingFormServlet.do">
            <center><input class="btn" type="submit" value="Confirm booking" /></center>
        </form>
    </div>
</html>
