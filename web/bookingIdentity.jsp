<%-- 
    Document   : bookingIdentity
    Created on : 21-Nov-2018, 01:21:37
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Template</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    
    <jsp:include page="/common/head.jsp"/>
     
        <div class="main">
       
       <!--Return back to the booking servlet if the customer signs in-->
       <jsp:include page="login.jsp">
           <jsp:param name="reDirectOnSuccess" value="BookingFormServlet.do" />
       </jsp:include>
       <div class="line"></div>
      
       <center><a href="guest.jsp" class="button">Book as guest</a>
       <a href="register.jsp" class="button">Book with a new AlphaCabs account</a></center>
        </div>
    
    <jsp:include page="/common/foot.jsp"/>
    
</html>
