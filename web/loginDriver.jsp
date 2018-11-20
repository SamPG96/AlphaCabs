<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Driver Dashboard</title>

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
        <h1>DRIVER Dashboard</h1>
        
        <div class>
            <button class="tablinks" onclick="openTab(event, 'Information')">Information</button>
        </div>
        
        <div id="Information" class="tabcontent">
            <h3>Daily Jobs</h3>
            <!--<p>< %@include file='.jsp' %></p>-->
            <p>INPUT JSP/DETAILS HERE - Display Daily Jobs</p>

        </div>

    </body>
</html>
