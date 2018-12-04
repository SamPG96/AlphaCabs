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

        <form method="GET" action="AdminDashSettingsServlet.java">
            <table class="tableLine">
                
                <button type="submit" class="allconfigs">Display Current Configurations</button>
                
                <!--<c:out value="configTable"/>-->
                ${configTable}

            </table>
        </form>

        <form method="POST" action="AdminDashSettingsServlet.java">
            Please Enter the new £ per Mile and press SUBMIT:
            <input type="text" name="newPrice"><br>
            <input type="submit" value="Submit">
        </form>   
    </body>

</html>