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
        <div class="main">
            <div class="container">
                <form method="POST" action="AdminDashDriversServlet.do">
                    <center><h1>Add New Driver</h1></center>
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <label>Forename:</label>
                                <input class="form-control" type='text' name='forename' required>
                            </div>
                            <div class="form-group col-sm-6">
                                <label>Surname:</label>
                                <input class="form-control" type='text' name='surname' required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <label>Car Registration:</label>
                                <input class="form-control" type='text' name='registration' required>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <label>Password:</label>
                                <input class="form-control" type='password' name='password' required>
                            </div>
                            <div class="form-group col-sm-6">
                                <label>Confirm Password:</label>
                                <input class="form-control" type='password' placeholer='Confirm Password' name='confirmation' required>
                            </div>
                        </div>
                        <div class="clearfix">
                            <button class="btn my-1" type="button" onclick="document.location.href = 'index.jsp'">Cancel</button>

                            <button class="btn my-1" type="submit" name="button_click" value="newdriver">Add Driver</button>
                        </div>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
