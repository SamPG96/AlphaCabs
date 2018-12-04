<%-- 
    Document   : adminDashBookings
    Created on : 02-Dec-2018, 14:11:54
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="tables.css">

        <title>Manage Bookings</title>

        <!--<style>
        </style>-->

    </head>
    <body>

        <form method="GET" action="AdminDashBookingsServlet.do">

            <table class="tableLine">
                <tr><button type="submit" class="allbookings">Display All Bookings</button></tr>

                <c:out value="bookingsTable"/>
                ${bookingsTable}

            </table>
        </form>

        <form method="GET" action="AdminDashBookingsServlet.do">

            <table class="tableLine">
                <tr><button type="submit" class="outbookings">Display Outstanding Bookings</button></tr>

                <c:out value="outstandingBookings"/>
                ${outstandingBookings}

            </table>
        </form>

        <jsp:include page="/common/foot.jsp"/>
    </body>
</html>