<%-- 
    Document   : adminDashChangeDriverDetails
    Created on : 08-Dec-2018, 16:35:01
    Author     : Tom
--%>

<%@page import="model.Helper"%>
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

    <%Booking booking = (Booking) request.getAttribute("assignBooking");%>
    <%String driverOptions = (String) request.getAttribute("driverOptions");%>

    <body>
        <div class="main">
            <div class="container">

                <form method="POST" action="AssignDriverServlet.do">
                    <center><h1>Assign Driver</h1></center>
                    <div style="height:20px;"></div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            Source Address: <%=booking.getSourceAddress()%> 
                        </div>
                        <div class="form-group col-sm-6">
                            Destination Address: <%=booking.getDestinationAddress()%>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            Date and Time: <%=Helper.formatDateWithTime(booking.getDepartureTime())%>
                        </div>
                        <div class="form-group col-sm-6">
                            No. of Passengers <%=booking.getNumOfPassengers()%>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            Distance (Miles): <%=Helper.doubleToTwoDecPlacesString(booking.getDistance())%>
                        </div>
                        <div class="form-group col-sm-6">
                            Booking ID: <%=booking.getId()%>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>Driver:</label>
                            <select class="form-control" name="driverselector"><%=driverOptions%></select>
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
