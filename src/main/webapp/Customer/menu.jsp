<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="model.Table" %>
<% Table table; %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu Table</title>
    <% table = ((Table)request.getAttribute("table")); %>
    <script src="scripts/scripts.js"></script>
    
</head>
<body>
    <h1>Menu Details</h1>
    <table id="menuTable">
        <%for(int i=0; i<table.getMenuWrapper().getMenu().size(); i++) { %>
            <tr id="Row<%= i %>">
                <td id="ProductID<%= i %>" hidden="hidden"><%= table.getMenuWrapper().getMenu().get(i).getProductID() %></td>
                <td id="ProductName<%= i %>"><%= table.getMenuWrapper().getMenu().get(i).getProductName() %></td>
                <td id="ProductDescription<%= i %>"><%= table.getMenuWrapper().getMenu().get(i).getProductDescription() %></td>
                <td id="ProductPrice<%= i %>"><%= table.getMenuWrapper().getMenu().get(i).getProductPrice() %></td>
                <td id="ProductQuantity<%= i %>">
                    <select id="ProductQuantityDropdown<%= i %>">
                        <option value="0">0</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                    </select>
                </td>
                <td>
                    <button id="SaveButton<%= i %>" onclick="addToCart(this.id)">Save</button>
                </td>
            </tr>
        <%}%>
    </table>
    <a href="http://localhost:8080/RestaurantServer/Cart">Go To Cart</a>
</body>
</html>