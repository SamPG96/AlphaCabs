<%-- 
    Document   : profile
    Created on : 21-Nov-2018, 02:21:32
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
        
            <h1>Profile</h1>
            
            
            
            <form method="POST" action="LogoutServlet.do">
                <input type="submit" value="Logout"/>
            </form>
                
            <button type="button">Edit</button>
            
        </div>
</html>
