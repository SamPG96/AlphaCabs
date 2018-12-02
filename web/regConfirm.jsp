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
        <h1>Account created!</h1>
        <p>Issued username: <%= request.getAttribute("newUsername") %></p>
        <p>Your details have been sent to an Admin for approval</p>

        <form method="POST" action="BookingFormServlet.do">
            <input type="submit" value="Confirm booking" />
        </form>
    </div>

    <jsp:include page="/common/foot.jsp"/>

</html>
