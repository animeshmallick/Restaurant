<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Booking Confirmation</title>
</head>
<body>
    <h1>Booking Confirmed !!</h1><br/><br/></h1>
    <h2>Full Name : <%= request.getParameter("name")%></h2>
    <h2>Phone Number : <%= request.getParameter("phone")%></h2>
    <h2>Expected Guest : <%= request.getParameter("guest")%></h2>
    <br/><br/>
    <a href="/RestaurantServer/customerLogin.jsp">
        <button>Login Now.</button>
    </a>
</body>
</html>