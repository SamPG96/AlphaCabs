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
                <form method="POST" action="DriverFormServlet.do">
                    <center><h1>Add Driver</h1></center>
                    <div style="height:20px;"></div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>First Name:</label>
                            <input class="form-control" type="text" name="firstname" />
                        </div>
                        <div class="form-group col-sm-6">
                            <label>Last Name:</label>
                            <input class="form-control" type="text" name="lastname" />
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>Registration:</label>
                            <input class="form-control" type="text" name="registration"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>Password:</label>
                            <input class="form-control" type="password" name="password"  />
                        </div>
                        <div class="form-group col-sm-6">
                            <label>Confirm Password:</label>
                            <input class="form-control" type="password" name="confpassword" />
                        </div>
                    </div>
                    <div class="errMessage"><%=((String) (request.getAttribute("errMsg")) != null) ? (String) (request.getAttribute("errMsg")) : ""%></div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <button class="btn my-1 " style="width:100%" onclick="document.location.href = 'index.jsp'" class="cancel">Cancel</button>
                        </div>
                        <div class="form-group col-sm-6">
                            <input class="btn my-1 " style="width:100%" type="submit" value="Submit"/>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
