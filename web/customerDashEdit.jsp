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


 <div class="main">
        <div class="container">
            
            <form method="POST" action="BookingFormServlet.do">
                
                <table id="editUser" class="display" style="width:100%">
                    
                  ${editTable}  
                    
                </table>
                
                  <button type="button" name="edit" value="Submit Edit">
                  
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
</html>