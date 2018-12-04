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

        <!--DISPLAY ALL DRIVERS-->
        <form method="GET" action="AdminDashDriversServlet.do">
            <table class="tableLine">

                <button type="submit" class="alldrivers" value="displayAll">Display All Drivers</button>

                <!--< c:out value="driversTable"/>-->
                ${driversTable}

            </table>
        </form>

        <!--CHANGE DRIVER DETAILS FORM-->
        <button onclick="document.getElementById('id02').style.display = 'block'">Change Driver Details</button>

        <div id="id02" class="model">
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
                        
                        <button type='submit' class='newdriver' name='changedetailsbutton' value='changedetails'>Confirm Change Details</button>
                    </div>
                </div>
            </form>
        </div>

        <!--ADD NEW DRIVER-->
        <button onclick="document.getElementById('id01').style.display = 'block'">Add Driver</button>

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

                        <button type='submit' class='newdriver' name='newdriverbutton' value='newdriver'>Add Driver</button>

                    </div>

                </div>

                <br>New Driver Username:
                <br><b><%=request.getParameter("userName")%></b>

            </form>
        </div>

        <script>
            // Get the models
            var modal = document.getElementById('id01');
            var model = document.getElementById('id02');
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

        <jsp:include page="/common/foot.jsp"/>

    </body>
</html>