<html>
    <head>
        <title>Booking Details.</title>
    <script src="scripts/scripts.js"></script>
    </head>
    <body>
        <center>
            <h2>
            <form name="bookingForm" method="GET" action="CustomerBooking" onsubmit="return validateBookingForm()" >
                Full Name : <input type="text" name="name"><span id="nameSpan"></span><br/>
                Phone Number : <input type="text" name="phone"><span id="phoneSpan"></span><br/>
                Number of Guests : <input type="text" name="guest"><span id="guestSpan"></span><br/>
                <input type="submit" value="Confirm Booking.">
            </form>
            </h2>
        </center>
    </body>
</html>
