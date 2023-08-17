<%--
  Created by IntelliJ IDEA.
  User: anime
  Date: 15/08/2023
  Time: 20:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer Details</title>
</head>
<body>
<h1>Customer Details. !!</h1>
<form name="tableAllotment" action="/../RestaurantServer/TableAllotment" method="POST">
    <h2>Name : <input name="name" value="<%= request.getAttribute("name") %>" readonly></h2>
    <h3>Mobile Number : <input name="phone" value="<%= request.getParameter("phoneNumber") %>" readonly></h3>
    <h3>Guests : <input name="guest" value="<%= request.getAttribute("guest") %>" readonly></h3>
    <h3>Comments : <input name="comment" value="<%= request.getAttribute("comment") %>" readonly></h3>
    <h2>Table ID : <input name="tableID" value="<%= request.getAttribute("tableID") %>" readonly></h2>
    <h3>Booking Time : <input name="bookingTime" value="<%= request.getAttribute("time")%>" readonly></h3>
    <h3>Customer ID : <input name="customerID" value="<%= request.getAttribute("id") %>" readonly></h3>
    <h2>Table Number : <select name="tableNumber">
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
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
    </select></h2>
    <h2><input type="submit" name="submit" value="Submit"></h2>
</form>
</body>
</html>
