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
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.2.7/css/select.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">
        <title>Manage Bookings</title>

        <!--<style>
        </style>-->

    </head>
    <body>

        <form method="GET" action="AdminDashBookingsServlet.do">

            <div class="container">
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
                        <button type="submit" class="displayButton">Display Bookings</button>
                    </div>
                </div>
            </div>

            <table id="bookingsTable" class="display" style="width:100%">   
                ${bookingsTable}
            </table>
        </form>
    </body>

    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript">
        function getid(elem) {
            var id = $(elem).attr('name');

            $.post("http://localhost:8080/AlphaCabs/AdminDashUserServlet.do", {"id": id});
        }
        ;
        $(document).ready(function () {
            $('#bookingsTable').DataTable({
            });
        });

    </script>
</html>