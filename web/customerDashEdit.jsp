<%-- 
    Document   : customerDashEdit
    Created on : 09-Dec-2018, 11:49:10
    Author     : yuugy
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    
    <body>
        <div class="container">
            <div class="row">   
                <h3>My Details</h3>
            </div>    
            <div class="col-xs-12" style="height:20px;"></div>
            <form method="POST" action="CustomerDashEditServlet.do">
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>First name:</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="firstName" value=${firstName} required />
                    </div>
                </div>    
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>Last name:</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="lastName" value=${lastName} required />
                    </div>
                </div> 
                <div class="form-row">
                    <div class="form-group col-sm-6">
                        <label>Address:</label>
                    </div>
                    <div class="form-group col-sm-6">
                        <input class="form-control" type="text" name="address" value=${address} required />
                    </div>
                </div>
                <input class="btn my-1 " style="width:100%" type="submit" value="Update"/>
            </form>
        </div>
    </body>
</html>
    
    
    
    
<!--    <div class="main">
        <div class="container"> 
            <form method="POST" action="CustomerDashEditServlet.do">
                
                <table id="editUser" class="display" style="width:100%">
                    
           
                    
                </table>
                
                <input type="submit" name="edit" value="Change">
            </form>           
        </div>
    </div>
 <script src="https://code.jquery.com/jquery-3.3.1.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#editUser').DataTable();
        });

    </script>
    </body>
</html>-->