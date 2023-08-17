<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 16/08/2023
  Time: 21:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Successful Entry</title>
</head>
<body>
    <h1>Name : <%= request.getParameter("name")%></h1>
    <h1>CustomerID : <%= request.getParameter("customerID")%></h1>
    <h1>Table ID : <%= request.getParameter("tableID")%></h1>
    <h1>Table Number : <%= request.getParameter("tableNumber")%></h1>
    <br/><br/><br/><br/><br/>
    <h1><a href="/RestaurantServer/Waiter"><button>Go Home</button></a></h1>
</body>
</html>
