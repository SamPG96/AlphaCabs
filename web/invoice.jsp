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
            
         
           
<left> <%booking.getTimeBooked();%></left> <%-- DATE  ORDER ID--%><right>Order Reference: <%booking.getId();%></right>
            
            <div class="line"></div>
              
            <h2>Thank you for using Alphacabs</h2>
                
            <h3>   Details of the trip: <h3>
           
           Name: <%booking.getCustomer().getLastName(); %>
           Source: <%booking.getSourceAddress(); %>
           Destination: <%booking.getDestinationAddress();%>
           Passengers: <%booking.getNumOfPassengers();%>
           
                     <div class="line"></div>
            Miles:
            Price per mile:
            VAT:
            Total including VAT:
            
              
             
        </div>
    
    
    <jsp:include page="/common/foot.jsp"/>
</html>
