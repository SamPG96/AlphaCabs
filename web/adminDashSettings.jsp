<%-- 
    Document   : adminDashSettings
    Created on : 01-Dec-2018, 14:40:33
    Author     : Tom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>

        <div class="container">
            <div class="row">   
                <h3>Settings</h3>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
            <form>
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>Price per mile (£):</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="pricePerMile" value=${pricePerMile} />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>Short Distance Price (£):</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="shortDistPrice" value=${shortDistPrice}  />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>Short Distance Definition (Miles):</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="shortDist" value=${shortDist}  />
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>VAT (%):</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="vat" value=${vat}  />
                    </div>
                </div>
                <div class="errMessage"><%=((String) (request.getAttribute("errMsg")) != null) ? (String) (request.getAttribute("errMsg")) : ""%></div>
                <form method="POST" action="AdminDashSettingsServlet.do">
                    <input class="btn my-1 " style="width:100%" type="submit" value="Submit"/>
                </form>
            </form>

        </div>

    </body>

</html>