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
              
            <h2>Thank you for using Alphacabs</h2>
            
            <%-- DATE  ORDER ID--%>  
            <table>
                        
            <tr>
                <td>Details of the trip:</td><td></td>
            </tr>
            <tr>
                <td>Name:</td><td></td>
            </tr>
            <tr>
                <td>Source:</td><td></td>
            </tr>
            <tr>
                <td>Destination:</td><td></td>
            </tr>
            <tr>
                <td>Passengers:</td><td></td>
            </tr>
                     <div class="line"></div>
            <tr>
                <td>Miles:</td><td></td>
            </tr>
            <tr>
                <td>Price per mile:</td><td></td>
            </tr>
            <tr>
                <td>VAT:</td><td></td>
            </tr>
            <tr>
                <td>Total including VAT:</td><td></td>
            </tr>
                      
            </table> 
              
             
        </div>
    
    
    <jsp:include page="/common/foot.jsp"/>
</html>
