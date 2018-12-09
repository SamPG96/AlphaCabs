<%-- 
    Document   : custDashUpcomingJourneys
    Created on : 09-Dec-2018, 14:36:16
    Author     : Conor
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    </head>
    <body>
        <div class="container">

            <div class="row">   
                <h3>Upcoming Journeys</h3>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
            <table id="upcomingBookings" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th>Source</th>
                        <th>Destination</th>
                        <th>Distance (Miles)</th>
                        <th>No. of Passengers</th>
                        <th>Time</th>
                        <th>Fair inc. VAT (£)</th>
                    </tr>
                </thead>
                <tbody>
                    ${upcomingBookingsTable}
                </tbody>
            </table>
        </div>
    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#upcomingBookings').DataTable();
        });

    </script>
</html>
