<%-- 
    Document   : index
    Created on : 09-Nov-2018, 13:05:42
    Author     : Alex, Sam
--%>
<%@page import="model.tableclasses.GenericItem"%>
<%@page import="model.tableclasses.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AlphaCabs</title>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
    </head>
    <jsp:include page="/common/head.jsp"/>

    <div class="main">



        <%
            if (session != null && session.getAttribute("userID") != null) {

                GenericItem userType = (GenericItem) session.getAttribute("userType");

                if (userType.getId() == 1) {

        %>
                    <%@include file="adminDash.jsp"%>
        <%      }
                else if (userType.getId() == 2) {
        %>
                    <%@include file="driverDash.jsp"%>
        <%
                }
                else if (userType.getId() == 4) {
        %>
                    <%@include file="customerDash.jsp"%>
        <%
                }
            } else {
                // Reset cached objects
                session.removeAttribute("cachedCustomerID");
                session.removeAttribute("cachedBooking");
        %>
                <jsp:include page="booking.jsp"/>
        <%
            }
        %>

    </div>
    <jsp:include page="/common/foot.jsp"/>

</html>
