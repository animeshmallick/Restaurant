<%@ page import="model.TableMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="model.Table" %>
<% Map<Integer, Table> tables; %>
<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 16/08/2023
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table Views</title>
</head>
<body>
    <% tables = ((TableMap) request.getAttribute("tables")).getTableMap(); %>
    <table id="tablesTable">
        <% for(Map.Entry<Integer, Table> tableMapEntry : tables.entrySet()) { %>
            <tr>
                <td><%= tableMapEntry.getKey() %></td>
                <td><%= tableMapEntry.getValue().getStatus() %></td>
                <td><a href="/RestaurantServer/Waiter/Table?tableNumber=<%= tableMapEntry.getKey() %>">View Details</a> </td>
            </tr>
        <% } %>
    </table>
</body>
</html>
