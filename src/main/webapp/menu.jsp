<% ArrayList<Product> productList; %>
<% ArrayList<Order> cartItem; %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="model.Product" %>
<%@ page import="model.Order" %>
<%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu Table</title>
    <% productList = ((ArrayList<Product>)request.getAttribute("menu")); %>
    
</head>
<body>
    <h1>Menu Details</h1>
    <table id="menuTable">
        <%for(int i=0; i<productList.size(); i++) { %>
            <tr name="MenuRow" id=<%= i %>>
                <td id="ProductID" hidden="hidden"><%= productList.get(i).getProductID() %></td>
                <td id="ProductName"><%= productList.get(i).getProductName() %></td>
                <td id="ProductDescription"><%= productList.get(i).getProductDescription() %></td>
                <td id="ProductPrice"><%= productList.get(i).getProductPrice() %></td>
                <td id="ProductQuantity">
                    <select name="quantity" id="ProductQuantityDropdown">
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
            </tr>
        <%}%>
    </table>
    <br/><br/><br/>
    <form name="CreateCart" method="POST">

    </form>
</body>
</html>