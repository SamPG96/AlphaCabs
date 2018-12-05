<%-- 
    Document   : login
    Created on : 09-Nov-2018, 00:43:37
    Author     : 
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <title>Login</title>
    </head>
    <jsp:include page="/common/head.jsp"/>

    <div class="main">
        <div class="container">
            <form method="POST" action="LoginServlet.do">
                <form class="row-sm-8">
                    <center><h1>Login</h1></center>
                    <div style="height:20px;"></div>
                    <div class="form-row">
                        <div class="col-md-6 offset-md-3">
                            <label>Username:</label>
                            <input class="form-control" type="text" name="username"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="col-md-6 offset-md-3">
                            <label>Password</label>
                            <input class="form-control" type="password" name="password"/>
                        </div>
                    </div>
                    <input type='hidden' name='reDirectOnSuccess' value=${param.reDirectOnSuccess} />
                    <div class="form-row">
                        <div class="col-md-6 offset-md-3">
                            <input class="btn my-1 " style="width:100%" type="submit" value="Login"/>
                            <div class="errMessage"><%=((String) (request.getAttribute("errMsg")) != null) ? (String) (request.getAttribute("errMsg")) : ""%></div>
                        </div>
                    </div>
                </form>
            </form>
        </div>
    </div>

    <jsp:include page="/common/foot.jsp"/>
</html>

