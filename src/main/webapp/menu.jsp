<% ArrayList<Product> productList; %>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%@ page import="model.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="model.MenuWrapper" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Menu Table</title>
    <% productList = ((MenuWrapper)request.getAttribute("menu")).getMenu(); %>
    <script>
        function addToCart(i) {
            let row = i.substring(10);
            let itemID = document.getElementById("ProductID" + row).innerHTML;
            let quantity = document.getElementById("ProductQuantityDropdown" + row).value;
            let cookieName = "order";
            let cookieValue = itemID.concat("Q").concat(quantity);
            let oldCookieValue = getExistingOrderCookieValue();
            let newCookieValue = oldCookieValue.concat("AND").concat(cookieValue);
            document.cookie = cookieName.concat("=").concat(newCookieValue);
        }

        function getExistingOrderCookieValue() {
            let cookie = document.cookie;
            let firstIndex = cookie.indexOf('order=') + 6;
            if(firstIndex < 6 || firstIndex > cookie.length)
                return "";
            cookie = cookie.substring(firstIndex);
            let lastIndex = cookie.indexOf(';');
            if(lastIndex === -1)
                return cookie;
            return cookie.substring(0,lastIndex);
        }
    </script>
    
</head>
<body>
    <h1>Menu Details</h1>
    <table id="menuTable">
        <%for(int i=0; i<productList.size(); i++) { %>
            <tr id="Row<%= i %>">
                <td id="ProductID<%= i %>" hidden="hidden"><%= productList.get(i).getProductID() %></td>
                <td id="ProductName<%= i %>"><%= productList.get(i).getProductName() %></td>
                <td id="ProductDescription<%= i %>"><%= productList.get(i).getProductDescription() %></td>
                <td id="ProductPrice<%= i %>"><%= productList.get(i).getProductPrice() %></td>
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
</body>
</html>