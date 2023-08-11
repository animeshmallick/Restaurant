<% MenuWrapper menuWrapper; %>
<% ArrayList<Order> orderList; %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.Order" %>
<%@ page import="model.MenuWrapper" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu Table</title>
    <% orderList = (ArrayList<Order>)request.getAttribute("orders"); %>
    <% menuWrapper = (MenuWrapper)request.getAttribute("menu"); %>
</head>
<body>
<h1>Cart Details</h1>
<table id="menuTable">
    <%for(int i=0; i<orderList.size(); i++) { %>
    <tr id="Row<%= i %>">
        <td id="ProductID<%= i %>" hidden="hidden"><%= orderList.get(i).getProductID() %></td>
        <td id="ProductName<%= i %>"><%= menuWrapper.getProductFromMenu(orderList.get(i).getProductID()).getProductName() %></td>
        <td id="ProductPrice<%= i %>"><%= menuWrapper.getProductFromMenu(orderList.get(i).getProductID()).getProductPrice() %></td>
        <td id="ProductQuantity<%= i %>"><%= orderList.get(i).getQuantity() %></td>
        <td id="ProductAmount<%= i %>"><%= menuWrapper
                .getProductFromMenu(orderList.get(i).getProductID())
                .getProductPrice() *  orderList.get(i).getQuantity() %></td>
    </tr>
    <%}%>
</table>
</body>
</html>