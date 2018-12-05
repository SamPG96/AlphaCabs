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
            <div class="container">

                <div class="row">   
                    <h3>View Daily Report</h3>
                </div>
                <div class="col-xs-12" style="height:20px;"></div>
                <div class="row">
                    <div class="col-sm-4">
                        <div name='todaysDate' class="row">
                            ${todaysDate}
                        </div>
                        <div name='todaysTurnover' class="row">
                            ${todaysTurnover}
                        </div>
                        <div name='numCustServed' class="row">
                            ${numCustServed}
                        </div>
                    </div>
                </div>
                <div class="col-xs-12" style="height:20px;"></div>
                    <table id="todaysBookings" class="display" style="width:100%">
                        <thead>
                            <tr>
                                <th>Customer Name</th>
                                <th>Source</th>
                                <th>Destination</th>
                                <th>Time</th>
                                <th>Fair ex. VAT (£)</th>
                            </tr>
                        </thead>
                        <tbody>
                             ${todaysBookingsTable}
                        </tbody>
                    </table>
            </div>
        </div>
</body>

<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/1.10.19/js/dataTables.bootstrap4.min.js"></script>
<script type="text/javascript">

    $(document).ready(function () {
        $('#todaysBookings').DataTable();
    });

</script>

</html>
