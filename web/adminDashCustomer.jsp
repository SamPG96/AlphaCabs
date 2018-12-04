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
        <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.1.1/css/bootstrap.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/dataTables.bootstrap4.min.css">
        
    </head>
    <body>
        <form method="GET" action="AdminDashCustomerServlet.do">
        
        <div class="mb-1">    
        <input type="submit" value="Display all customers"/>
        </div>
            <table id="example" class="table table-striped table-bordered" style="width:100%">               
            
               <c:out value="customerTable"/>
                       ${customerTable}
            </table>
        </form>
    <jsp:include page="/common/foot.jsp"/>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript">
	
	$(document).ready(function() {
    	$('#example').DataTable();
	} );

</script>
</html>
