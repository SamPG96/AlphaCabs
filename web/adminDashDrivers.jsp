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
            <button class="btn" onclick="document.getElementById('id01').style.display = 'block'">Add New Driver</button>
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
                
        <!--REMOVE DRIVER-->
        <div id="id03" class="modal">
            <span onclick="document.getElementById('id03').style.display = 'none'" class="close" title="Close">Enter the Details of the Driver</span>

            <form class="modal-content" method="POST" action="AdminDashDriversServlet.do">
                <div class='container'>
                    <hr>

                    <label for='Id'><b>Driver ID</b></label>
                    <input type='text' placeholder='Enter ID Only' name='id' required>

                    <div class="clearfix">
                        <button type="button" onclick="document.getElementById('id03').style.display = 'none'" class="cancel">Cancel</button>

                        <button type="submit" class="removedriver" name="button_click" value="removedriver">Confirm Removal of Driver</button>

                    </div>

                </div>
            </form>
        </div> 

        <!--CHANGE DRIVER DETAILS FORM-->
        <div id="id02" class="modal">
            <span onclick="document.getElementById('id02').style.display = 'none'" class="close" title ="Close">Change Driver Details</span>

            <form class="modal-content" method="POST" action="AdminDashDriversServlet.do">
                <div class="container">
                    <hr>

                    <label for='Id'><b>Id Number</b></label>
                    <input type='text' name='idnumber' required>

                    <label for='Forename'><b>Forename</b></label>
                    <input type='text' name='forenameC' required>

                    <label for='Surname'><b>Surname</b></label>
                    <input type='text' name='surnameC' required>

                    <label for='registration'><b>Registration</b></label>
                    <input type='text' name='registrationC' required>

                    <div class="clearfix">
                        <button type="button" onclick="document.getElementById('id02').style.display = 'none'" class="cancel">Cancel</button>

                        <button type="submit" class="newdriver" name="button_click" value="changedetails">Confirm Change Details</button>
                    </div>
                </div>
            </form>
        </div>

        <!--ADD NEW DRIVER-->
        <div id="id01" class="modal">
            <span onclick="document.getElementById('id01').style.display = 'none'" class="close" title="Close">Enter Driver Details</span>

            <form class="modal-content" method="POST" action="AdminDashDriversServlet.do">
                <div class='container'>
                    <hr>

                    <label for='Forename'><b>Forename</b></label>
                    <input type='text' placeholder='Enter First Name' name='forename' required>

                    <label for='Surname'><b>Surname</b></label>
                    <input type='text' placeholder='Enter Last Name' name='surname' required>

                    <label for='registration'><b>Registration</b></label>
                    <input type='text' placeholder='Enter Car Registration' name='registration' required>

                    <label for='password'><b>Password</b></label>
                    <input type='text' placeholer='Enter a Password' name='password' required>

                    <label for='confirmation'><b>Confirm Password</b></label>
                    <input type='text' placeholer='Confirm Password' name='confirmation' required>

                    <div class="clearfix">
                        <button type="button" onclick="document.getElementById('id01').style.display = 'none'" class="cancel">Cancel</button>

                        <button type="submit" class="newdriver" name="button_click" value="newdriver">Add Driver</button>

                    </div>

                </div>

                <br>New Driver Username:
                <br><b><%=request.getParameter("userName")%></b>

            </form>
        </div>

        <script>
            // Get the models
            //modal = name, set to be 'hidden'
            var modal = document.getElementById('id01');
            var modal = document.getElementById('id02');
            var modal = document.getElementById('id03');
            // When the user clicks anywhere outside of the model, close it
            /*
             window.onclick = function (event) {
             if (event.target == modal) {
             modal.style.display = "none";
             } else if (event.target == modal2) {
             modal2.style.display = "none";
             }
             }
             */
        </script>

        <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
        <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
        <script type="text/javascript">

            $(document).ready(function () {
                $('#driversTable').DataTable();
            });

        </script>
    </body>
</html>