<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 17/08/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.MenuWrapper" %>
<% ArrayList<Order> orders; %>
<% MenuWrapper menu; %>
        <html>
<head>
    <title>Kitchen Orders</title>
</head>
<body>
    <% orders = (ArrayList<Order>) request.getAttribute("orders"); %>
    <% menu = (MenuWrapper) request.getAttribute("menu"); %>
    <table id="ordersTable">
        <% for(Order order : orders) { %>
           <tr>
               <td><%= menu.getProductFromMenu(order.getProductID()).getProductName() %></td>
               <td><%= order.getQuantity()%></td>
               <%if(order.getStatus().equalsIgnoreCase("preparing")) {%>
                   <td><a href="/RestaurantServer/OrderPrepared?orderID=<%=order.getOrderID()%>">
                       <button>Order Prepared</button></a></td>
               <% }
               if(order.getStatus().equalsIgnoreCase("confirmed")) {%>
                    <td><a href="/RestaurantServer/PrepareOrder?orderID=<%=order.getOrderID()%>">
                        <button>Start Preparing</button></a></td>
               <% } %>
           </tr>
        <% } %>
    </table>
</body>
</html>
