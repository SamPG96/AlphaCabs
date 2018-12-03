<%-- 
    Document   : head
    Created on : 08-Nov-2018, 19:47:52
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
<!--<link rel="stylesheet" type="text/css" href="stylesheet.css">-->
</head>
<html>
    
    <body>
        <div class="navbar">
        <img class="img-fluid" src="imgs/Alpha.png" align="left">
        
        
       <%
        try {
            if(session != null && session.getAttribute("userID") != null)  {
%>
       
        <div class="mx-auto">
            <a class="badge badge-light" href="profile.jsp"> Profile </a>
        </div>
        
        <%
            } else {
    %>
   
        <div class="mx-auto">
            <a class="badge badge-light" href="login.jsp"> Login </a>
        </div>
    
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
               <!--<div class="line"></div>-->
        </div>
    </body>
</html>
