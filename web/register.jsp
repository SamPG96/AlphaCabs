<%-- 
    Document   : register
    Created on : 21-Nov-2018, 01:26:39
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    
    <jsp:include page="/common/head.jsp"/>
     
        <div class="main">
            <h1>Register:</h1>
            Please enter the following details for the new account:
            
            
            <center><form method="POST" action="???.do">
            <table>
                 <tr>
                <td>First name:</td><td>Last name:</td>
            </tr>
            <tr>
                <td><input type="text" name="firstName"/></td><td><input type="text" name="lastName"/></td>
            </tr>
            <tr>
                <td>Home address:</td><td></td>
            </tr>
            <tr>
                <td><input type="text" name="homeAddress"/></td><td></td>
            </tr>
            <tr>
                <td>Password:</td><td>Confirm password:</td>
            </tr>
            <tr>
                <td><input type="password" name="password"/></td><td><input type="password" name="password"/></td>
            </tr>
            
            <tr>
                <td><input type="password" name="password"/></td><td><input type="password" name="password"/></td>
            </tr>
            <tr
                ><td><input type="submit" value="Create and book"/></td><td></td>
            </tr>
            
                
            </table>
                </form>
            
            <%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%>
            </center>
       
               
            
           
            
            
            
        </div>
    
    <jsp:include page="/common/foot.jsp"/>
    
</html>