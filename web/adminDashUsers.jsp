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
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/select/1.2.7/css/select.dataTables.min.css">
        <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.5.2/css/buttons.dataTables.min.css">
    </head>
    <body>
        <div class="container">

            <div class="row">   
                <h3>Users Overview</h3>
            </div>
            <div class="col-xs-12" style="height:20px;"></div>
            <table id="usersTable" class="display" style="width:100%">
                <thead>
                    <tr>
                        <th>Username</th>
                        <th>Type</th>
                        <th>Name</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    ${userTable}
                </tbody>
            </table>
        </div>
    </body>



    <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/select/1.2.7/js/dataTables.select.min.js"></script>
    <script src="https://cdn.datatables.net/buttons/1.5.2/js/dataTables.buttons.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $('#usersTable').DataTable({
                select: {
                    style: 'single'
                }
            });
        });

    </script>
</html>
