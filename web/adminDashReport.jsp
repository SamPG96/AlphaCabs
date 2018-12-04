<%-- 
    Document   : adminDashReport
    Created on : 03-Dec-2018, 16:16:48
    Author     : c2-newcombe
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
    </head>
    <body>
        <form method="GET" action="AdminDashReportServlet.do">
            <div class="container">
                <div class="mb-1">    
                    <input type="submit" value="Generate Daily Report"/>
                </div>
                <div class="row">
                    <div class="col-sm-4">
                        <div name='todaysTurnover' class="row">
                                    ${todaysTurnover}
                        </div>
                        <div name='numCustServed' class="row">
                                    ${numCustServed}
                        </div>
                    </div>
                    <div class="col-sm-8">

                    </div>
                </div>
            </div>
        </form>
    </body>
</html>
