<%-- 
    Document   : AdminDashDriverJSP
    Created on : 19-Nov-2018, 11:02:44
    Author     : tc2-buxton
--%>

<%@page import="java.sql.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">

    </head>
    <body>

            Id
            FirstName
            LastName
            Registration

            <input type="submit" value="Create New Driver" name="newDriver"/>

            <form method="POST" action="AdminDashDriversServlet.do"></form>

        <jsp:include page="foot.jsp"/>

    </body>
</html>