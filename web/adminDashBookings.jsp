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

                <table class="tableLine">
                    ${bookingsTable}
                </table>
        </form>
    </body>
</html>