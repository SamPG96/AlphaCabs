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
        <div class="container">
            <div class="row">
                <h3>Manage Customers</h3>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
            <div class="row">
                <button onclick="window.location.href = 'adminCustRegister.jsp'" class="btn">Add New Customer</button>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
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

                        $.post("AdminDashCustomerServlet.do", {"userid": userid, "userstatus": userstatus});
                        //wait is needed otherwise sql crashes. currently set to half a second
                        window.setTimeout(loadpage, 500);

                    };

                    function loadpage() {
                        //reloads page
                        window.location.reload();
                    }
                    
                    $(document).ready(function () {
                        $('#customersTable').DataTable({
                            select: {
                                style: 'single'
                            }
                        });
                    });
    </script>
</html>
