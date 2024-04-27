<%-- 
    Document   : reservation
    Created on : Feb 4, 2024, 8:00:48 AM
    Author     : student
--%>

<%@page import="ryerson.ca.helper.ReservationSlot"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Select a reservation slot</title>
    <style>
        body {
            background-color: blanchedalmond;
        }
    </style>
</head>

<body>

    <% 
        ArrayList<ReservationSlot> reservations = (ArrayList)request.getAttribute("reservationListInfo");
    %>

    <%--<center><h2>Hello <%=session.getAttribute("uname")%></h2></center> --%>
    <br>
    <center><h3> The following are the available reservations.</h3></center>
    <br>
        <table border="2" align="left" cellpadding="5" cellspacing="5">
            <tr>
                <th> Date</th>
                <th> Time </th>
                <th> Table Capacity </th>
            </tr>
            <% if(reservations != null){ %>
                <% for(ReservationSlot reservation: reservations){ %>
                    <tr>
                        <td> <%=reservation.getDate()%></td>
                        <td> <%=reservation.getTime()%></td>
                        <td> <%=reservation.getCapacity()%></td>
                    </tr>
                <% } %>
            <% } else { %>
                <tr>
                    <td colspan="4">No Reservation available.</td>
                </tr>
            <% } %>
        </table>
    </form>
</body>

</html>