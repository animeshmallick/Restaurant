<%--
  Created by IntelliJ IDEA.
  User: animesh
  Date: 15/08/2023
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.MenuWrapper" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.ArrayList" %>
<% ArrayList<Order> orderList; %>
<% MenuWrapper menuWrapper; %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Your Orders !!</title>
    <% menuWrapper = (MenuWrapper) request.getAttribute("menu"); %>
    <% orderList= (ArrayList<Order>) request.getAttribute("liveOrder"); %>
</head>
<body>
    <table id="orders">
        <% for(int i=0; i<orderList.size(); i++) { %>
            <tr>
                <td><%= menuWrapper.getProductFromMenu(orderList.get(i).getProductID()).getProductName() %></td>
                <td><%= orderList.get(i).getQuantity() %></td>
                <td><%= menuWrapper.getProductFromMenu(orderList.get(i).getProductID()).getProductPrice() %></td>
                <td><%= menuWrapper.getProductFromMenu(orderList.get(i).getProductID()).getProductPrice()
                        *  orderList.get(i).getQuantity() %></td>
                <td><%= orderList.get(i).getStatus() %></td>
            </tr>
        <%}%>
    </table>
    <% if(!orderList.isEmpty()) { %>
        <form action="/Bill" method="post">
            <
        </form>
    <% } %>
</body>
</html>
