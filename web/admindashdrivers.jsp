<%-- 
    Document   : AdminDashDriverJSP
    Created on : 19-Nov-2018, 11:02:44
    Author     : tc2-buxton
--%>

<%@page import="model.tableclasses.Booking"%>
<%@page import="java.sql.*" %>
<%@page import="java.util.*" %>
<%@page import="model.BookingManager" %>
<%@page import="model.Jdbc" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">

    </head>
    <body>
        
        <input type="radio" name="select"/>
        <% out.println("DISPLAY DRIVER HERE - row 1");%>

        <br><input type="radio" name="select"/>
        <% out.println("DISPLAY DRIVER HERE - row 2");%>

        <br><input type="radio" name="select"/>
        <% out.println("DISPLAY DRIVER HERE - row 3");%>

        <br><input type="radio" name="select"/>
        <% out.println("DISPLAY DRIVER HERE - row 4");%>
        
        <br><input type="radio" name="select"/>
        <% out.println("etc.");%>

        <br><input type="submit" value="Create New Driver" name="newDriver"/>
        <input type="submit" value="Change Details" name="detailsChange"/>
        
        <form method="POST" action="AdminDashDriversServlet.do"></form>

        <jsp:include page="foot.jsp"/>

    </body>
</html>