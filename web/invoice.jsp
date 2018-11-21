<%-- 
    Document   : invoice
    Created on : 21-Nov-2018, 01:09:12
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Invoice</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    
    
     
        <div class="main">
            <center><h1>Alphacab: Invoice</h1></center>
            
           <%--
<%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%>
           --%>
            
            
            <div class="line"></div>
              <%-- DATE  ORDER ID--%>  
              <h2>Thank you for using Alphacabs</h2>
              Details of the trip:
              Name:
              Source:
              Destination:
              Passengers:
              Driver:
              <div class="line"></div>
              Miles:
              Price per mile:
              VAT:
              Total including VAT:
             
        </div>
    
    
    <jsp:include page="/common/foot.jsp"/>
</html>
