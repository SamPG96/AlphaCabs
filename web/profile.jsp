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
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>

    <jsp:include page="/common/head.jsp"/>

    <div class="main">
        <div class="container">
            <form method="POST" action="LogoutServlet.do">
                <center><h1>Profile</h1></center>
                <div style="height:20px;"></div>
                <center><button type="submit" class="btn" name="logout">Logout</button></center>
                <div style="height:20px;"></div>
                <center><button type="submit" class="btn" name="edit">Edit Your Details</button></center>
        </div>
    </form>
</div>

</div>

<jsp:include page="/common/foot.jsp"/>
</html>
