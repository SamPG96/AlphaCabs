<%-- 
    Document   : head
    Created on : 08-Nov-2018, 19:47:52
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<link rel="stylesheet" type="text/css" href="stylesheet.css">
</head>
<html>
    
    <body>
        <div class="navbar">
        <img src="imgs/Alpha.png" align="left">
        
        
       <%
        try {
            if(session != null && session.getAttribute("userID") != null)  {
%>
       
        <a href="profile.jsp"> Profile </a>
        
        <%
            } else {
    %>
   <a href="login.jsp"> Login </a>
    
    <%
       
            }
        } catch (Exception e) {

        }
    %>
        
        
        
        <%--
        <a href="login.jsp"> Login </a>
        --%>
        <br>
        <br>
        <h1>Alpha Cabs</h1>
               <div class="line"></div>
        </div>
    </body>
</html>
