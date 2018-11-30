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
        <title>Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="dashboards.css">
    </head>
    <body>
        <h1>ADMIN Dashboard</h1>
        
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Drivers')">Drivers</button>
            <button class="tablinks" onclick="openTab(event, 'Bookings')">Bookings</button>
            <button class="tablinks" onclick="openTab(event, 'Details')">Customers</button>
            <button class="tablinks" onclick="openTab(event, 'Report')">Daily Report</button>
            <button class="tablinks" onclick="openTab(event, 'Settings')">Settings</button>        
        </div>
        
        <div id="Drivers" class="tabcontent">
            <h3>Manage Drivers</h3>
            <jsp:include page="adminDashDrivers.jsp"/>
        </div>

        <div id="Bookings" class="tabcontent">
            <h3>Assign Drivers to Bookings</h3>
            <p><%@include file='adminDashBookings.jsp' %></p>
        </div>

        <div id="Details" class="tabcontent">
            <h3>Change User Details</h3>
            <p><%@include file='adminDashCustomer.jsp' %></p>
        </div>        
             
        
        <div id="Report" class="tabcontent">
            <h3>View Daily Report</h3>
            <!--<p>< %@include file='.jsp' %></p>-->
            <p>INPUT JSP FILE HERE - For viewing daily report</p>
        </div>
        
        <div id="Settings" class="tabcontent">
            <h3>Manage Settings</h3>
            <!--<p>< %@include file='.jsp' %></p>-->
            <p>INPUT JSP FILE HERE - For changing the Price per Mile</p>
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
			
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
