<%-- 
    Document   : AdminDashDriverJSP
    Created on : 19-Nov-2018, 11:02:44
    Author     : tc2-buxton
--%>

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
        <meta name="viewport" content="width=device-width, initial-scale=1">

    </head>
    <body>

        <div class="container">

            <div class="row">   
                <h3>Manage Drivers</h3>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
            <div id="addDriver" class="row">
                <button type="button" class="btn" name="add_driver" onclick="document.location.href = 'adminDashAddDriver.jsp'">Add New Driver</button>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
            <table id="driversTable" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Registration</th>
                        <th>Status</th>
                        <th></th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    ${driversTable}
                </tbody>
            </table>
        </div>

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript">

                    function getDriverUser(elem) {
                        var userid = $(elem).attr('data-userid');
                        var userstatus = $(elem).attr('data-userstatus');
                        var action = $(elem).attr('data-action');

                        $.post("AdminDashDriversServlet.do", {"userid": userid, "userstatus": userstatus, "action": action});
                        
                        //wait is needed otherwise sql crashes. currently set to half a second
                            window.setTimeout(loadpage, 500);
                    }
                    ;

                    function loadpage() {
                        //reloads page
                        window.location.reload();
                    }
                    $(document).ready(function () {
                        $('#driversTable').DataTable();
                    });

        </script>
    </body>
</html>