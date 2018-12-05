<%-- 
    Document   : adminDashSettings
    Created on : 01-Dec-2018, 14:40:33
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <title>Settings Page</title>

    </head>

    <body>

        <form method="GET" action="AdminDashSettingsServlet.do">
            <table class="tableLine">
                
                <button type="submit" class="allconfigs" value="displayConfigs">Display Current Configurations</button>
                
                <!--<c:out value="configTable"/>-->
                ${configTable}

            </table>
        </form>

        <form method="POST" action="AdminDashSettingsServlet.do">
            
            <label for="NewPrice"><b>Enter New Price per Mile:</b></label><br>
            
            <input type="text" name="newValue"><br>
            
            <input type="submit" value="Confirm Price Change" name="price_change">
            
        </form>

    </body>

</html>