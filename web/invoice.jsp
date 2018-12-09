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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <jsp:include page="/common/head.jsp"/>

    <%Booking booking = (Booking) request.getAttribute("booking");%>

    <div class="main">
        <center><h1>Invoice</h1></center>



        <div style="float:left;"> <%=booking.getTimeBooked()%></div> <div style="float:right;">Order Reference: <%=booking.getId()%>       </div><br>

        <div class="line"></div><br>

        <h2>Thank you for choosing AlphaCabs</h2> <br><br>

        <b>Your Journey Details</b> <br><br>

        <b>Name:</b> <%=booking.getCustomer().getFirstName()%> <%=booking.getCustomer().getLastName()%> <br><br>
        <b>Source:</b> <%=booking.getSourceAddress()%><br><br>
        <b>Destination:</b> <%=booking.getDestinationAddress()%><br><br>
        <b>Passengers:</b> <%=booking.getNumOfPassengers()%><br><br>

        <div class="line"></div><br>

        <b>Miles:</b> <%=Helper.doubleToTwoDecPlacesString(booking.getDistance())%> <br><br>
        <b>Price ex. VAT: </b>£<%=Helper.doubleToTwoDecPlacesString(booking.getFareExcVAT())%> <br><br>
        <b>Total inc. VAT: </b>£<%=Helper.doubleToTwoDecPlacesString(booking.getFareIncVAT())%> <br><br>

    </div>
    <jsp:include page="/common/foot.jsp"/>
</html>
