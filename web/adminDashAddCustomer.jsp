<%--
    Document   : register
    Created on : 07-Dec-2018, 12:26:39
    Author     : jakec
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
    <body>
        <div class="main">
            <div class="container">
                <form method="POST" action="RegistrationServlet.do">
                    <center><h1>Register:</h1></center>
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
                        <div class="form-row">
                            <div class="form-group col-sm-6">
                                <label>Password:</label>
                                <input class="form-control" type="password" name="password"/>
                            </div>
                            <div class="form-group col-sm-6">
                                <label>Confirm password:</label>
                                <input class="form-control" type="password" name="passwordConfirm"/>
                            </div>
                        </div>
                            <div class="form-check">
                                <input id="activateAccount" class="form-check-input" type="checkbox" name="activateAccount">
                                <label class="form-check-label" for="activateAccount">Activate Account</label>
                            </div>
                    <input class="btn my-1" style="width: 100%" type="submit" value="Register"/>
                        <div class="errMessage"><%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%></div>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="/common/foot.jsp"/>
</html>
