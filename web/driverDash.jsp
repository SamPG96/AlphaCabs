<%-- 
    Document   : driverDash
    Created on : 21-Nov-2018, 01:21:37
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
    
    <jsp:include page="/common/foot.jsp"/>
    
</html>
