<%-- 
    Document   : adminDashUsers
    Created on : 02-Dec-2018, 18:05:52
    Author     : jakec
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
    </head>
    <body>
                <form method="GET" action="AdminDashUserServlet.do">
        
        <div class="mb-1">    
        <input type="submit" value="Display all users"/>
        </div>
            <table class="table table-hover">               
            
               <c:out value="userTable"/>
                       ${userTable}
            </table>
        </form>
    <jsp:include page="/common/foot.jsp"/>
    </body>
</html>
