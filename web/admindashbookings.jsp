<%-- 
    Document   : admindashbookings
    Created on : 19-Nov-2018, 12:49:54
    Author     : tc2-buxton
--%>

<%@page import="java.sql.*" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">

        <title>Booking Assignment Page</title>

    </head>
    <body>

            Id
            CustomerId
            DriverId
            SourceAddress
            DestinationAddress
            DistanceKM
            TimeBooked
            TimeArrived
            BookingStatusId

            <form method="POST" action="AdminDashBookingsServlet.do"></form>

        <jsp:include page="foot.jsp"/>

    </body>
</html>