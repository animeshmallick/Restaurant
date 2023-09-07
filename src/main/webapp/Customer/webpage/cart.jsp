<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="model.Table" %>
<% Table table; %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Cart</title>
    <% table = (Table)request.getAttribute("table"); %>
    <script src="<%=request.getContextPath()%>/main/webapp/Scripts/scripts.js"></script>
</head>
<body>
<h1>Cart Details</h1>

<% if(table.getCart().isEmpty()) { %>
<h1>Empty Cart. !!</h1>
<%}%>

<table id="cartTable">
    <%for(int i=0; i<table.getCart().size(); i++) { %>
    <tr id="Row<%= i %>">
        <td id="ProductID<%= i %>" hidden="hidden"><%= table.getCart().get(i).getProductID() %></td>
        <td id="ProductName<%= i %>"><%= table.getMenuWrapper().getProductFromMenu(
                table.getCart().get(i).getProductID()).getProductName() %></td>
        <td id="ProductPrice<%= i %>"><%= table.getMenuWrapper().getProductFromMenu(
                table.getCart().get(i).getProductID()).getProductPrice() %></td>
        <td id="ProductQuantity<%= i %>"><%= table.getCart().get(i).getQuantity() %></td>
        <td id="ProductAmount<%= i %>"><%= table.getMenuWrapper()
                .getProductFromMenu(table.getCart().get(i).getProductID())
                .getProductPrice() *  table.getCart().get(i).getQuantity() %></td>
    </tr>
    <%}%>
</table>
<% if(!table.getCart().isEmpty()) { %>
    <form name="placeOrder" method="post" action="<%=request.getContextPath()%>/Orders">
        <input type="submit" name="submit" value="Place order">
    </form>
<%}%>
<a href="<%=request.getContextPath()%>/Orders">View Orders</a>
</body>
</html>