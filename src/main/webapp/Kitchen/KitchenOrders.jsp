<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 17/08/2023
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Table" %>
<%@ page import="model.Order" %>
<% ArrayList<Table> tables; %>
<html>
<head>
    <title>Kitchen Orders</title>
</head>
<body>
    <% tables = (ArrayList<Table>) request.getAttribute("tables"); %>
    <table id="ordersTable">
        <% for(Table table : tables) { %>
            <tr></tr>
            <% for(Order order : table.getOrders()) {%>
           <tr>
               <td><%= table.getMenuWrapper().getProductFromMenu(order.getProductID()).getProductName() %></td>
               <td><%= order.getQuantity()%></td>
               <%if(order.getStatus().equalsIgnoreCase("preparing")) {%>
                   <td><a href="<%=request.getContextPath()%>/Kitchen/OrderPrepared?orderID=<%=order.getOrderID()%>">
                       <button>Order Prepared</button></a></td>
               <% }
               if(order.getStatus().equalsIgnoreCase("confirmed")) {%>
                    <td><a href="<%=request.getContextPath()%>/Kitchen/PrepareOrder?orderID=<%=order.getOrderID()%>">
                        <button>Start Preparing</button></a></td>
               <% } %>
           </tr>
        <% }
        }%>
    </table>
</body>
</html>
