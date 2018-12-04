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

        <!--<style>
            body {font-family: Arial, Helvetica, sans-serif;}
            * {box-sizing: border-box;}
            /* Full-width input fields */
            input[type=text] {
                width: 100%;
                padding: 15px;
                margin: 5px 0 22px 0;
                display: inline-block;
                border: none;
                background: #f1f1f1;
            }
            /* Add a background color when the inputs get focus */
            input[type=text]:focus {
                background-color: #ddd;
                outline: none;
            }
            /* Set a style for all buttons */
            button {
                background-color: dodgerblue;
                color: white;
                padding: 14px 20px;
                margin: 8px 0;
                border: none;
                cursor: pointer;
                width: 100%;
                opacity: 0.9;
            }
            button:hover {
                opacity:1;
            }
            /* Extra styles for the cancel button */
            .cancelbtn {
                padding: 14px 20px;
                background-color: #f44336;
            }
            /* Float cancel and signup buttons and add an equal width */
            .cancelbtn, .signupbtn {
                float: left;
                width: 50%;
            }
            /* Add padding to container elements */
            .container {
                padding: 16px;
            }
            /* The Modal (background) */
            .modal {
                display: none; /* Hidden by default */
                position: fixed; /* Stay in place */
                z-index: 1; /* Sit on top */
                left: 0;
                top: 0;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                overflow: auto; /* Enable scroll if needed */
                background-color: cadetblue;
                padding-top: 50px;
            }
            /* Modal Content/Box */
            .modal-content {
                background-color: cadetblue;
                margin: 5% auto 15% auto; /* 5% from the top, 15% from the bottom and centered */
                border: 1px solid #888;
                width: 80%; /* Could be more or less, depending on screen size */
            }
            /* Style the horizontal ruler */
            hr {
                border: 1px solid #f1f1f1;
                margin-bottom: 25px;
            }
            /* The Close Button (x) */
            .close {
                position: absolute;
                right: 35px;
                top: 15px;
                font-size: 40px;
                font-weight: bold;
                color: #f1f1f1;
            }
            .close:hover,
            .close:focus {
                color: #f44336;
                cursor: pointer;
            }
            /* Clear floats */
            .clearfix::after {
                content: "";
                clear: both;
                display: table;
            }
            /* Change styles for cancel button and signup button on extra small screens */
            @media screen and (max-width: 300px) {
                .cancelbtn, .signupbtn {
                    width: 100%;
                }
            }
            .btn {
                border: none;
                color: white;
                padding: 8px 15px;
                cursor: pointer;
            }
            .select{background-color: #CCCCCC;}
            .select:hover{background-color: captiontext;}
        </style>-->

    </head>
    <body>

        <!--CHANGE & REMOVE DRIVER DETAILS-->
        <form method="GET" action="AdminDashDriversServlet.do">
            <table class="tableLine">

                <button type="submit" class="alldrivers">Display All Drivers</button>

                <!--< c:out value="driversTable"/>-->
                ${driversTable}

                <!--
                <button onclick="document.getElementById('id02').style.display = 'block'">Change Driver Details</button>
                <div id="id02" class="model">
                    <span onclick="document.getElementById('id02').style.display = 'none'" class="close" title ="Close">Change Driver Details</span>
                    <form class="modal-content" method="POST" action="AdminDashDriversServlet.do">
                        <div class="container">
                            <hr>
                            <label for='Forename'><b>Forename</b></label>
                            <input type='text' name='forename' required>
                            <label for='Surname'><b>Surname</b></label>
                            <input type='text' name='surname' required>
                            <label for='registration'><b>Registration</b></label>
                            <input type='text' name='registration' required>
                            <label for='username'><b>Username</b></label>
                            <input type='text' name='username' required>
                            <label for='password'><b>Password</b></label>
                            <input type='text' name='password' required>
                            <label for='confirmation'><b>Confirm Password</b></label>
                            <input type='text' name='confirmation' required>
                            <div class="clearfix">
                                <button type="button" onclick="document.getElementById('id02').style.display = 'none'" class="cancel">Cancel</button>
                                <button type='submit' class='newdriver'>Confirm Change Details</button>
                            </div>
                        </div>
                    </form>
                </div>
                -->

            </table>

            <!--<form method="POST" action="AdminDashDriversServlet.do">
            </form>-->
        </form>


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

                        <button type='submit' class='newdriver'>Add Driver</button>

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