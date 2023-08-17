<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 15/08/2023
  Time: 20:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New Customer Entry</title>
</head>
<body>
    <form name="newCustomerEntryForm" action="/../RestaurantServer/NewEntry" method="GET">
        Phone Number : <input type="text" name="phoneNumber"><br/>
        <input type="submit" value="Find Customer">
    </form>
</body>
</html>
