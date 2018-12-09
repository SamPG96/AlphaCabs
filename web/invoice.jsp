<%-- 
    Document   : invoice
    Created on : 21-Nov-2018, 01:09:12
    Author     : yuugy
--%>

<%@page import="model.tableclasses.Booking"%>
<%@page import="model.Helper"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <jsp:include page="/common/head.jsp"/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>Invoice</title>
    </head>

    <body>


        <%Booking booking = (Booking) request.getAttribute("booking");%>


        <div class="main">
            <center><h1>Invoice</h1></center>
            <center><h2>Thank you for choosing AlphaCabs!</h2></center>
            <div class="container">


                <div class="col-xs-12" style="height:20px;"></div>
                <div class="row">
                    <div class="col-sm-4">
                        Time of Booking: <%=Helper.formatDateWithTime(booking.getTimeBooked())%>
                    </div>
                    <div class="col-sm-4">
                        Order Reference: <%=booking.getId()%>
                    </div>
                </div>
                <div class="line"></div>
                <div class="col-xs-12" style="height:20px;"></div>
                <div class="row">  
                    <div class="col-md-6">
                        <h3>Your Journey Details</h3>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-6">
                        Booked Under The Name: <%=booking.getCustomer().getFirstName()%> <%=booking.getCustomer().getLastName()%>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        Journey Departure Time: <%=Helper.formatDateWithTime(booking.getDepartureTime())%>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        Departing From: <%=booking.getSourceAddress()%>
                    </div>
                    <div class="col-sm-4">
                        Arriving At: <%=booking.getDestinationAddress()%>
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        Distance (Miles): <%=Helper.doubleToTwoDecPlacesString(booking.getDistance())%>
                    </div>
                    <div class="col-sm-4">
                        Number of Passengers: <%=booking.getNumOfPassengers()%>
                    </div>
                </div>
                <div class="line"></div>
                <div class="row">
                    <div class="col-sm-4">
                        Fare Charge ex. VAT: £<%=Helper.doubleToTwoDecPlacesString(booking.getFareExcVAT())%>
                    </div>
                    <div class="col-sm-4">
                        Fare Charge inc. VAT: £<%=Helper.doubleToTwoDecPlacesString(booking.getFareIncVAT())%> 
                    </div>
                </div>
                    <div class="col-xs-12" style="height:20px;"></div>
                    <center><button class="btn" onClick="javascript:window.print()">Print Invoice</button></center>
            </div>
        </div>
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
