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
    <body>
        <div class="main"> 
            <div class="container">
                <form method="POST" action="GuestServlet.do">
                    <center><h1>Guest Booking</h1></center>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>First name:</label>
                            <input class="form-control" type="text" name="firstName"/>
                        </div>
                        <div class="form-group col-sm-6">
                            <label>Last name:</label>
                            <input class="form-control" type="text" name="lastName"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label>Home address:</label>
                            <input class="form-control" type="text" name="homeAddress"/>
                        </div>
                    </div>
                    <input class="btn my-1" style="width: 100%" type="submit" value="Book"/>
                    <div class="errMessage"><%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%></div>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
