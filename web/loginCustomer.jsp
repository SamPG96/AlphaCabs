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
        <h1>CUSTOMER Dashboard</h1>
        
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'Booking')">Booking</button>
            <button class="tablinks" onclick="openTab(event, 'Details')">Edit Details</button>
        </div>
        
        <div id="Booking" class="tabcontent">
            <h3>Make a Booking</h3>
            <p>INPUT JSP FILE HERE</p>
        </div>
        
        <div id="Details" class="tabcontent">
            <h3>Edit Your Details</h3>
            <p>INPUT JSP FILE HERE</p>
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
        
        <jsp:include page="foot.jsp"/>
			
    </body>
</html>
