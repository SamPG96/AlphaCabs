<%-- 
    Document   : index
    Created on : 09-Nov-2018, 13:05:42
    Author     : Alex, Sam
--%>

<%@page import="model.tableclasses.GenericItem"%>
<%@page import="model.tableclasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AlphaCabs</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <jsp:include page="/common/head.jsp"/>
     
        <div class="main">
            
            
            
            <%
        try {
            if(session != null && session.getAttribute("userID") != null)  {

                
           GenericItem userType = (GenericItem)session.getAttribute("userType");
            
           if(userType.getId() == 1){            

            %>
            <jsp:include page="loginAdmin.jsp"/>
            <%
            }
            
           if(userType.getId() == 2){ 
            %>
            <jsp:include page="loginDriver.jsp"/>
            <%
            }
            if(userType.getId() == 4){ 
            %>
            <jsp:include page="loginCustomer.jsp"/>
            <%
            }
              
            } else {
    %>
            
           
            
        <jsp:include page="booking.jsp"/>
           
        
        
       
            
            
            <%
       
            }
        } catch (Exception e) {

        }
    %>
            
            
         
        
        
        
        </div>
    <jsp:include page="/common/foot.jsp"/>
    
</html>
