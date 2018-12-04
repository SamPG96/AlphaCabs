<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer Dashboard</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="dashboards.css">
    </head>
    <body>
        <h1>CUSTOMER Dashboard</h1>
        
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Booking')" id="bookingTab">Booking</button>
            <button class="tablinks" onclick="openTab(event, 'Details')">Edit Details</button>
        </div>
        
        <div id="Booking" class="tabcontent">
            <h3>Make a Booking</h3>
            <jsp:include page="booking.jsp"/>
        </div>
        
        <div id="Details" class="tabcontent">
            <h3>Edit Your Details</h3>
            <p>INPUT JSP FILE HERE - Request changes to Details here</p>
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
            // Open booking tab by default
            document.getElementById("bookingTab").click();
        </script>
			
    </body>
</html>
