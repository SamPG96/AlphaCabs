<%-- 
    Document   : adminDashBookings
    Created on : 02-Dec-2018, 14:11:54
    Author     : Tom
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <link rel="stylesheet" type="text/css" href="tables.css">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">

    </head>
    <body>

        <form method="GET" action="AdminDashBookingsServlet.do">

            <div class="container">
                <div class="row">   
                    <h3>View Bookings</h3>
                </div>
                <div class="col-xs-12" style="height:20px;"></div>
                <div class="row">
                    <div class="col-sm-4">
                        <label for='check'>Only outstanding bookings</label>
                    </div>
                    <div class="col-sm-4">
                        <input type="checkbox" id="checkOutstanding" name="checkOutstanding">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <button type="submit" class="btn mb-1">Display Bookings</button>
                    </div>
                    <div class="col-sm-4">
                        <button type="submit" name="assigndriver" class="btn mb-1">Assign Driver(s)</button>
                    </div>
                </div>
                <div class="col-xs-12" style="height:20px;"></div>
                <table id="bookingsTable" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>Source address</th>
                            <th>Destination address</th>
                            <th>No. of Passengers</th>
                            <th>Distance (Miles)</th>
                            <th>Price ex. VAT (£)</th>
                            <th>Price inc. VAT (£)</th>
                            <th>Time of Booking</th>
                            <th>Departure time</th>
                            <th>Arrival time</th>
                            <th>Driver</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${bookingsTable}
                    </tbody>
                </table>
                <div class="col-xs-12" style="height:20px;"></div>
            </div>
        </form>

    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript">
        function getid(elem) {
            var id = $(elem).attr('name');

            $.post("http://localhost:8080/AlphaCabs/AdminDashBookingsServlet.do", {"id": id});
        }
        ;
        $(document).ready(function () {
            $('#bookingsTable').DataTable({
                select: {
                    style: 'single'
                }
            });
        });

    </script>
</html>