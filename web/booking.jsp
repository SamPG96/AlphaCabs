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
        
             <center> <h1>Alphacab Customer Form</h1></center>
            <center> Please fill out this form to request a taxi:</center>
            
            
            
            <center><form method="POST" action="BookingFormServlet.do">
               <table>
                    <tr>
                        <td>Source:</td><td></td><td>Destination</td>
                    </tr>
                    <tr>
                        <td><input type="text" name="source"/></td><td></td><td><input type="text" name="destination"/></td>
                    </tr>
                    <tr>
                        <td>Date:</td><td>Time:</td><td>Passengers:</td>
                    </tr><tr>
                        <td><input type="date" name="date"/></td><td><input type="time" name="time"/></td><td><input type="number" name="passengers"/></td>
                    </tr><tr>
                        <td></td><td><input type="submit" value="Submit"/></td><td></td>
                    </tr>

                </table>
            </form>
            <div class="errMessage"><%=((String)(request.getAttribute("errMsg"))!=null)?(String)(request.getAttribute("errMsg")):""%></div>
            </center>
                            
                
        </div>
    
   
    
</html>
