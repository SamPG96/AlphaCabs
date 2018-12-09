<%-- 
    Document   : adminDashChangeDriverDetails
    Created on : 08-Dec-2018, 16:35:01
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
            <div class="container">
                <hr>

                <label for='Id'><b>Id Number</b></label>
                <input type='text' name='idnumber' required>

                <label for='Forename'><b>Forename</b></label>
                <input type='text' name='forenameC' required>

                <label for='Surname'><b>Surname</b></label>
                <input type='text' name='surnameC' required>

                <label for='registration'><b>Registration</b></label>
                <input type='text' name='registrationC' required>

                <div class="clearfix">
                    <button type="button" onclick="document.location.href = 'index.jsp'" class="cancel">Cancel</button>

                    <button type="submit" class="newdriver" name="button_click" value="changedetails">Confirm Change Details</button>
                </div>
            </div>
        </form>
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
