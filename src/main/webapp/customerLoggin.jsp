<html>
    <head><title>Login</title></head>
    <script>
        function validateform(){
            var otp = document.loginForm.otp.value;

            if (otp == null || otp == ""){
                document.getElementById("otpSpan").innerHTML="Invalid OTP.";
                return false;
            } else { document.getElementById("otpSpan").innerHTML=""; }

            if (otp.length == 6) {
                document.getElementById("otpSpan").innerHTML="Invalid OTP.";
                return false;
            } else { document.getElementById("otpSpan").innerHTML="." }

            if (isNaN(otp)) {
                document.getElementById("otpSpan").innerHTML="Invalid OTP.";
                return false;
            } else { document.getElementById("phoneSpan").innerHTML="" }
            return true;
        }
    </script>
    <body>
        <h1>
            <form name="loginForm" method="POST" action="CustomerLogin" onsubmit="return validateform()" >
                OTP : <input type="text" name="otp"><span id="otpSpan"></span><br/>
                <input type="submit" value="Login.">
            </form>
        </h1>
    </body>
</html>
