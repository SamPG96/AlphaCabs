<%-- 
    Document   : admindashbookings
    Created on : 19-Nov-2018, 12:49:54
    Author     : tc2-buxton
--%>


<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">

        <title>Booking Assignment Page</title>

    </head>
    <body>

        <%
            // FOR ALEX!!!
            // ArrayList<Booking> allBookings = BookingManager.getAllBookings(jdbc);
            //for (Booking booking: allBookings){
            //   booking.getSourceAddress();
            //   booking.getDestinationAddress();
            // }

        %>
        <!-- TODO The drop down list should appear for the first booking in the list (least recent),
        and give a list of drivers that can be assigned to that job-->



        <form method="GET" action="AdminDashBookingsServlet.do">

            <table class="tableLine">
                <tr><input type="submit" value="Display Bookings"/></tr>                
            
               <c:out value="bookingsTable"/>
                       ${bookingsTable}
            
            
            </table>

                   </form>


        <jsp:include page="/common/foot.jsp"/>

    </body>
</html>