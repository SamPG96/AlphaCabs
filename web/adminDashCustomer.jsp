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
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.2.7/css/select.dataTables.min.css">
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">
        
    </head>
    <body> 
        <div class="container">
                <div class="row">
                    <div class="col-sm-4">
                        <button href="AdminCustRegister.jsp" class="btn mb-1">Add New Customer</button>
                    </div>
                </div>
            <table id="customersTable" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Name</th>
                        <th>Address</th>
                        <th>Status</th>
                        <th>Approve</th>
                    </tr>
                </thead>
                <tbody>  
                    ${customersTable}
                </tbody>
            </table>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript">
function getUser(elem) {
    var userid = $(elem).attr('data-userid');
    var userstatus = $(elem).attr('data-userstatus');

        $.post("AdminDashCustomerServlet.do", {"userid": userid, "userstatus" : userstatus});
};
$(document).ready(function() {
    $('#customersTable').DataTable( {
        select: {
            style: 'single'
        }
    } );
} );
</script>
</html>
