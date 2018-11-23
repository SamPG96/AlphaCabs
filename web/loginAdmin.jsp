<!--<!DOCTYPE html>

To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
 
        <style>
            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #f1f1f1;                
            }
            
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
            }
            
            .tab button:hover {
                background-color: #ddd
            }
            .tab button.active {
                background-color: #ccc
            }
            
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            }            
        
        </style>
        
    </head>
    <body>
        <h1>ADMIN Dashboard</h1>
        
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Drivers')">Drivers</button>
            <button class="tablinks" onclick="openTab(event, 'Bookings')">Bookings</button>
            <button class="tablinks" onclick="openTab(event, 'Details')">Customer Details</button>
            <button class="tablinks" onclick="openTab(event, 'Report')">Daily Report</button>            
            <button class="tablinks" onclick="openTab(event, 'Settings')">Settings</button>            
        </div>
        
        
	
        <div id="Drivers" class="tabcontent">
            <h3>Manage Drivers</h3>
            <p><//%@include file='admindashdrivers.jsp' %></p>
            <p>INPUT JSP FILE HERE</p>
        </div>
        
        <div id="Bookings" class="tabcontent">
            <h3>Assign Drivers to Bookings</h3>
            <p><//%@include file='admindashbookings.jsp' %></p>
            <p>INPUT JSP FILE HERE</p>
        </div>
        
        <div id="Details" class="tabcontent">
            <h3>Change User Details</h3>
            <p>< %@include file='.jsp' %></p>
            <p>INPUT JSP FILE HERE - For changing and approving new customers upon request</p>
        </div>
            
        <div id="Report" class="tabcontent">
            <h3>View Daily Report</h3>
            <p>< %@include file='.jsp' %></p>
            <p>INPUT JSP FILE HERE - For viewing daily report</p>
        </div>            

        <div id="Settings" class="tabcontent">
            <h3>Manage Settings</h3>
            <p>< %@include file='.jsp' %></p>
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
    <//jsp:include page="/common/foot.jsp"/>
</html>-->

<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        
        
        <style>
            .tab {
                overflow: hidden;
                border: 1px solid #ccc;
                background-color: #f1f1f1;                
            }
            
            .tab button {
                background-color: inherit;
                float: left;
                border: none;
                outline: none;
                cursor: pointer;
                padding: 14px 16px;
                transition: 0.3s;
                font-size: 17px;
            }
            
            .tab button:hover {
                background-color: #ddd
            }
            .tab button.active {
                background-color: #ccc
            }
            
            .tabcontent {
                display: none;
                padding: 6px 12px;
                border: 1px solid #ccc;
                border-top: none;
            }            
        
        </style>
        
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
            <jsp:include page="admindashdrivers.jsp"/>
            <p>INPUT JSP FILE HERE - For viewing drivers</p>
        </div>

        <div id="Bookings" class="tabcontent">
            <h3>Assign Drivers to Bookings</h3>
            <p><%@include file='admindashbookings.jsp' %></p>
            <p>INPUT JSP FILE HERE - For assigning drivers to bookings</p>
        </div>

        <div id="Details" class="tabcontent">
            <h3>Change User Details</h3>
            <!--<p>< %@include file='.jsp' %></p>-->
            <p>INPUT JSP FILE HERE - For changing and approving new customers upon request</p>
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
