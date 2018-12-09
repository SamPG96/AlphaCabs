<%-- 
    Document   : booking
    Created on : 21-Nov-2018, 00:41:02
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
                     <center><h1>Booking Form</h1></center>
                     <div style="height:20px;"></div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>Source Address:</label>
                            <input class="form-control" type="text" name="source"/>
                        </div>
                        <div class="form-group col-sm-6">
                            <label>Destination Address:</label>
                            <input class="form-control" type="text" name="destination"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>Date</label>
                            <input class="form-control" type="date" name="date"/>
                        </div>
                        <div class="form-group col-sm-6">
                            <label>Time</label>
                            <input class="form-control" type="time" name="time"/>
                        </div>
                    </div>
                    <div class="form-row">
                        <div class="form-group col-sm-6">
                            <label>No. of Passengers</label>
                            <input class="form-control" type="number" name="passengers"/>
                        </div>
                            
                    </div>
                    <input class="btn my-1 " style="width:100%" type="submit" value="Submit"/>
                    <div class="errMessage"><%=((String) (request.getAttribute("errMsg")) != null) ? (String) (request.getAttribute("errMsg")) : ""%></div>
                </form>
        </div>
    </div>
           
</html>
