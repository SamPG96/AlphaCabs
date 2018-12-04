<%-- 
    Document   : invoice
    Created on : 21-Nov-2018, 01:09:12
    Author     : yuugy
--%>

<%@page import="model.tableclasses.Booking"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    
    <%Booking booking = (Booking)request.getAttribute("booking");%>
     
        <div class="main">
            <center><h1>Alphacab: Invoice</h1></center>
            
         
           
<div style="float:left;"> <%=booking.getTimeBooked()%></div> <div style="float:right;">Order Reference: <%=booking.getId()%>       </div><br>
            
            <div class="line"></div><br>
              
            <h2>Thank you for using Alphacabs</h2> <br><br>
                
              <b>Details of the trip</b> <br><br>
           
           <b>Name:</b> <%=booking.getCustomer().getFirstName() %> <%=booking.getCustomer().getLastName() %> <br><br>
           <b>Source:</b> <%=booking.getSourceAddress() %><br><br>
           <b>Destination:</b> <%=booking.getDestinationAddress()%><br><br>
           <b>Passengers:</b> <%=booking.getNumOfPassengers()%><br><br>
           
                     <div class="line"></div><br>
            <b>Miles:</b> <br><br>
            <b>Price per mile:</b> <br><br>
            <b>VAT:</b> <br><br>
            <b>Total including </b> <br><br>
             <b>  VAT:</b> <br><br>
            
              
             
        </div>
</html>
