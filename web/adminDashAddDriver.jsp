<%-- 
    Document   : adminDashAddDriver
    Created on : 08-Dec-2018, 16:34:40
    Author     : Tom
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
        <link rel="stylesheet" type="text/css" href="dashboards.css">
        <meta name="viewport" content="width=device-width, initial-scale=1">
    </head>
    <jsp:include page="/common/head.jsp"/>
    <body>
        <form class="modal-content" method="POST" action="AdminDashDriversServlet.do">
            <div class='container'>
                <hr>

                <label for='Forename'><b>Forename</b></label>
                <input type='text' placeholder='Enter First Name' name='forename' required>

                <label for='Surname'><b>Surname</b></label>
                <input type='text' placeholder='Enter Last Name' name='surname' required>

                <label for='registration'><b>Registration</b></label>
                <input type='text' placeholder='Enter Car Registration' name='registration' required>

                <label for='password'><b>Password</b></label>
                <input type='text' placeholer='Enter a Password' name='password' required>

                <label for='confirmation'><b>Confirm Password</b></label>
                <input type='text' placeholer='Confirm Password' name='confirmation' required>

                <div class="clearfix">
                    <button type="button" onclick="document.location.href = 'index.jsp'" class="cancel">Cancel</button>

                    <button type="submit" class="newdriver" name="button_click" value="newdriver">Add Driver</button>

                </div>

            </div>

            <br>New Driver Username:
            <br><b><%=request.getParameter("userName")%></b>

        </form>
    </body>
        <jsp:include page="/common/foot.jsp"/>
</html>
