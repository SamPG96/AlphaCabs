<%-- 
    Document   : adminDash
    Created on : 23-Nov-2018, 10:39:15
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrative Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="dashboards.css">

    </head>
    <body>
        <h1>Administrative Dashboard</h1>

        <div class="tab">
            <form method="GET" action="AdminDashDriversServlet.do">
                <button class="tablinks" onclick="openTab(event, 'Drivers')">Drivers</button>
            </form>
            <button class="tablinks" onclick="openTab(event, 'Bookings')">Bookings</button>
            <button class="tablinks" onclick="openTab(event, 'Customers')">Customers</button>
            <button class="tablinks" onclick="openTab(event, 'Users')">Users</button>
            <form method="GET" action="AdminDashReportServlet.do">
                <button type="submit" class="tablinks" onclick="openTab(event, 'Report')">Daily Report</button>
            </form>
            <form method="GET" action="AdminDashSettingsServlet.do">
                <button class="tablinks" onclick="openTab(event, 'Settings')">Settings</button>
            </form>
        </div>

        <div id="Drivers" class="tabcontent">
            <jsp:include page="adminDashDrivers.jsp"/>
        </div>

        <div id="Bookings" class="tabcontent">
            <h3>Assign Drivers to Bookings</h3>
            <%@include file='adminDashBookings.jsp' %>
        </div>

        <div id="Customers" class="tabcontent">
            <h3>Change Customer Details</h3>
            <%@include file='adminDashCustomer.jsp' %>
        </div>        

        <div id="Users" class="tabcontent">
            <h3>Approve Users</h3>
            <%@include file='adminDashUsers.jsp' %>
        </div>

        <div id="Report" class="tabcontent">
            <%@include file='adminDashReport.jsp' %>
        </div>

        <div id="Settings" class="tabcontent">
            <p><%@include file='adminDashSettings.jsp' %></p>
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
                if (window.location.href.indexOf("AdminDashCustomerServlet.do") > -1) {
                    openTab(event, 'Customers');
                } else if (window.location.href.indexOf("AdminDashBookingsServlet.do") > -1) {
                    openTab(event, 'Bookings');
                } else if (window.location.href.indexOf("AdminDashUserServlet.do") > -1) {
                    openTab(event, 'Users');
                } else if (window.location.href.indexOf("AdminDashReportServlet.do") > -1) {
                    openTab(event, 'Report');
                } else if (window.location.href.indexOf("AdminDashDriversServlet.do") > -1) {
                    openTab(event, 'Drivers');
                } else if (window.location.href.indexOf("AdminDashSettingsServlet.do") > -1) {
                    openTab(event, 'Settings');
                }
            }
            window.onload = displayTables;
        </script>

    </body>
</html>
