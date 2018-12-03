<%-- 
    Document   : guest
    Created on : 21-Nov-2018, 01:27:06
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
            Please enter the following details:
        <center><form method="POST" action="GuestServlet.do">
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
                <td><input type="submit" value="Book"/></td><td></td>
            </tr>
            
                
            </table>
                </form>
            <div class="errMessage"><%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%></div>
        </center>
            
            
            
            
            
       
        </div>
    
    <jsp:include page="/common/foot.jsp"/>
    
</html>
