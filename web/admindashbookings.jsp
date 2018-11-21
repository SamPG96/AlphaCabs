<%-- 
    Document   : admindashbookings
    Created on : 19-Nov-2018, 12:49:54
    Author     : tc2-buxton
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <title>Booking Assignment Page</title>

    </head>
    <body>
        
        <form action="admindashbookings.jsp">
            <!-- TODO The drop down list should appear for the first booking in the list (least recent),
            and give a list of drivers that can be assigned to that job-->
            <% out.println("DISPLAY LIST OF BOOKINGS"); %>
            <select name="select">
                <!--<option value="-1">Driver</option>-->
                <option value="driver1">Driver 1</option>
                <option value="driver2">Driver 2</option>
                <option value="driver3">Driver 3</option>
            </select>
            <br>
            <!-- TODO press submit changes database so selected driver is assigned to job -->
            <input type="Submit" name="Submit" value="Submit">
        </form>

            <form method="POST" action="AdminDashBookingsServlet.do">

        <jsp:include page="foot.jsp"/>

    </body>
</html>