<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="dashboards.css">
    </head>
    <body>
        <h1>Customer Dashboard</h1>

        <div class="tab">
            <form method="GET" action="BookingFormServlet.do">
                <button class="tablinks" onclick="openTab(event, 'NewBooking')">New Booking</button>
            </form>
            <form method="GET" action="CustomerDashEditServlet.do">
                <button class="tablinks" onclick="openTab(event, 'Details')">Edit Details</button>
            </form>
            <button class="tablinks" onclick="openTab(event, 'NewBooking')" id="bookingTab">New Booking</button>
            <form method="GET" action="CustDashUpcomingJourneysServlet.do">
                <button class="tablinks" onclick="openTab(event, 'UpcomingJourneys')">Upcoming Journeys</button>
            </form>
            <form method="GET" action="CustDashPreviousJourneysServlet.do">
                <button class="tablinks" onclick="openTab(event, 'PreviousJourneys')">Previous Journeys</button>
            </form>
            <button class="tablinks" onclick="openTab(event, 'Details')">Edit Details</button>
        </div>

        <div id="NewBooking" class="tabcontent">
            <%@include file="booking.jsp" %>
        </div>

        <div id="UpcomingJourneys" class="tabcontent">
            <%@include file='custDashUpcomingJourneys.jsp' %>
        </div>

        <div id="PreviousJourneys" class="tabcontent">
            <%@include file='custDashPreviousJourneys.jsp' %>
        </div>

        <div id="Details" class="tabcontent">
            <%@include file="customerDashEdit.jsp" %>
        </div>

        <script>
            function openTab(evt, option) {
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("tabcontent");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }
                tablinks = document.getElementsByClassName("tablinks");
                for (i = 0; i < tablinks.length; i++) {
                    tablinks[i].className = tablinks[i].className.replace("active", "");
                }
                document.getElementById(option).style.display = "block";
                evt.currentTarget.className += " active";
            }
        </script>
        <script>
            function displayTables() {
                if (window.location.href.indexOf("CustomerDashEditServlet.do") > -1) {
                    openTab(event, "Details");
                } else if (window.location.href.indexOf("BookingFormServlet.do") > -1) {
                    openTab(event, "NewBooking");
                } else if (window.location.href.indexOf("CustDashUpcomingJourneysServlet.do") > -1) {
                    openTab(event, 'UpcomingJourneys');
                } else if (window.location.href.indexOf("BookingFormServlet.do") > -1) {
                    openTab(event, 'NewBooking');
                }else if (window.location.href.indexOf("CustDashPreviousJourneysServlet.do") > -1) {
                    openTab(event, 'PreviousJourneys');
                }
            }
            window.onload = displayTables;
        </script>
    </body>
</html>
