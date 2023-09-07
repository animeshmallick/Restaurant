<%--
  Created by IntelliJ IDEA.
  User: animesh
  Date: 15/08/2023
  Time: 15:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Table" %>
<% Table table; %>

<html>
<head>
    <meta charset="UTF-8">
    <title>Your Orders !!</title>
    <% table = (Table) request.getAttribute("table"); %>
</head>
<body>
    <table id="orders">
        <% for(int i=0; i<table.getOrders().size(); i++) { %>
            <tr>
                <td><%= table.getMenuWrapper().getProductFromMenu(
                        table.getOrders().get(i).getProductID()).getProductName() %></td>
                <td><%= table.getOrders().get(i).getQuantity() %></td>
                <td><%= table.getMenuWrapper().getProductFromMenu(
                        table.getOrders().get(i).getProductID()).getProductPrice() %></td>
                <td><%= table.getMenuWrapper().getProductFromMenu(
                        table.getOrders().get(i).getProductID()).getProductPrice()
                        *  table.getOrders().get(i).getQuantity() %></td>
                <td><%= table.getOrders().get(i).getStatus() %></td>
            </tr>
        <%}%>
    </table>
    <br/><br/><br/><br/>
    <% if(!table.getOrders().isEmpty()) { %>
        <form action="<%=request.getContextPath()%>/Bill" method="post">
            <input type="hidden" name="tableID" value="<%= table.getTableID() %>">
            <input type="submit" value="Generate Bill">
        </form>
    <% } %>
    <a href="<%= request.getContextPath()%>/Menu" id="goToMenu">
        <button>Go To Menu</button>
    </a>
</body>
</html>
