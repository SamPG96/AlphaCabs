<%-- 
    Document   : adminDashCustomer
    Created on : 23-Nov-2018, 10:39:15
    Author     : yuugy
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <body>
             <form method="GET" action="AdminDashCustomerServlet.do">

            <table class="tableLine">
                <tr><input type="submit" value="Display all customers"/></tr>                
            
               <c:out value="customerTable"/>
                       ${customerTable}
            </table>

                   </form>
            <jsp:include page="/common/foot.jsp"/>
    </body>
</html>
