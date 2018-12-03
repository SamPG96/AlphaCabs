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

    </head>
   
    <form method="GET" action="DriverDashServlet.do">

            <table class="tableLine">
                <tr><input type="submit" value="Display Bookings"/></tr>                
            
               <c:out value="bookingsTable"/>
                       ${bookingsTable}
            
            
            </table>

                   </form>
    
    
    
    
    
    
    
    
    
    
    
    
   <% //driver manager update booking status = 4 %>
    
    
    
</html>
