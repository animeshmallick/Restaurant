<html>
    <head>
        <title>Booking Details.</title>
    </head>
    <script>
        function validateform(){
            var name=document.bookingForm.name.value;
            var phone=document.bookingForm.phone.value;
            var guest=document.bookingForm.guest.value;

            if (name==null || name==""){
                document.getElementById("nameSpan").innerHTML="Not a valid name.";
                return false;
            } else { document.getElementById("nameSpan").innerHTML="Valid."; }

            if (phone.length > 10) {
                document.getElementById("phoneSpan").innerHTML="Invalid phone number.";
                return false;
            } else { document.getElementById("phoneSpan").innerHTML="Valid."; }

            if (isNaN(phone)) {
                document.getElementById("phoneSpan").innerHTML="Enter Numeric value only.";
                return false;
            } else { document.getElementById("phoneSpan").innerHTML="Valid."; }

            if (isNaN(guest)) {
                document.getElementById("guestSpan").innerHTML="Enter Numeric value only.";
                return false;
            } else { document.getElementById("guestSpan").innerHTML="Valid."; }

            if (guest < 1) {
                document.getElementById("guestSpan").innerHTML="Number of guest should be greater than 0";
                return false;
            } else { document.getElementById("guestSpan").innerHTML="Valid."; }

            return true;
        }
    </script>
    <body>
        <h2>
            <form name="bookingForm" method="GET" action="CustomerBooking" onsubmit="return validateform()" >
                Full Name : <input type="text" name="name"><span id="nameSpan"></span><br/>
                Phone Number : <input type="text" name="phone"><span id="phoneSpan"></span><br/>
                Number of Guests : <input type="text" name="guest"><span id="guestSpan"></span><br/>
                <input type="submit" value="Confirm Booking.">
            </form>
        </h2>
    </body>
</html>
