<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 17/08/2023
  Time: 03:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="model.Table" %>
<%@ page import="model.Order" %>
<% Table table; %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Table Details</title>
</head>
<body>
    <% table = (Table) request.getAttribute("table"); %>
    <h1>Table Number : <%= table.getTableNumber() %></h1>
    <h1>Table Status : <%= table.getStatus() %></h1>
    <table id="tableOrderTable">
        <% for(Order order : table.getOrders()) { %>
            <tr>
                <td><%= table.getMenuWrapper().getProductFromMenu(order.getProductID()).getProductName() %></td>
                <td><%= order.getQuantity() %></td>
                <% if(order.getStatus().equalsIgnoreCase("placed")) { %>
                    <td><a href="/RestaurantServer/ConfirmOrder?orderID=<%=order.getOrderID()%>&tableNumber=<%=table.getTableNumber()%>">
                        <button>Confirm Order</button></a></td>
                    <td><a href="/RestaurantServer/DeleteOrder?orderID=<%=order.getOrderID()%>&tableNumber=<%=table.getTableNumber()%>">
                        <button>Delete Order</button></a></td>
                <% } %>
            </tr>
        <% } %>
    </table>
    <% if(table.getStatus().equalsIgnoreCase("Action Required")) { %>
        <a href="/RestaurantServer/ConfirmAllOrders?tableNumber=<%= table.getTableNumber() %>&tableID=<%= table.getTableID() %>">
            <button>Confirm all orders on the table.</button></a>
    <% } %>
</body>
</html>
