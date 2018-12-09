<%-- 
    Document   : driverDash
    Created on : 21-Nov-2018, 01:21:37
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Driver Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="dashboards.css">
    </head>

    <body>
        <h1>&nbsp;&nbsp;Driver Dashboard</h1>
        <form method="GET" action="DriverDashServlet.do">

            <div class="container">
                <div class="row">
                    <div class="col-sm-4">
                        <label for='check'>Only todays bookings</label>
                    </div>
                    <div class="col-sm-4">
                        <input type="checkbox" id="checkToday" name="checkToday">
                    </div>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <input type="submit" value="Display Bookings"/>
                    </div>
                </div>            
                <table id="driverBookings" class="display" style="width:100%">
                    <thead>
                        <tr>
                            <th>Customer Name</th>
                            <th>Source Address</th>
                            <th>Destination Address</th>
                            <th>No. of Passengers</th>
                            <th>Departure Time</th>
                            <th>Arrival Time</th>
                            <th>Fair Inc. VAT (£)</th>
                            <th>Status</th>
                        </tr>
                    </thead>
                    <tbody>
                        ${bookingsTable}
                    </tbody>
                </table>
            </div>
        </form>
    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#driverBookings').DataTable();
        });

    </script>
</html>
