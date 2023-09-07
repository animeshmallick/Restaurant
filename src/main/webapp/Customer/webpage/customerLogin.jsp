<html>
    <head><title>Login</title></head>
    <script>
        function validateLoginForm(){
            let tableId = document.loginForm.tableID.value;

            if (tableId == null || tableId === ""){
                document.getElementById("tableIDSpan").innerHTML="Invalid Table ID.";
                return false;
            } else { document.getElementById("tableIDSpan").innerHTML=""; }

            if (tableId.length !== 6) {
                document.getElementById("tableIDSpan").innerHTML="Invalid Table ID.";
                return false;
            } else { document.getElementById("tableIDSpan").innerHTML="." }

            if (isNaN(tableId)) {
                document.getElementById("tableIDSpan").innerHTML="Invalid Table ID.";
                return false;
            } else { document.getElementById("tableIDSpan").innerHTML="" }
            return true;
        }
    </script>
    <body>
        <h1>
            <form name="loginForm" method="POST" action="<%=request.getContextPath()%>/CustomerLogin"
                  onsubmit="return validateLoginForm()" >
                TABLE ID : <input type="text" name="tableID"><span id="tableIDSpan"></span><br/>
                <input type="submit" value="Login.">
            </form>
        </h1>
    </body>
</html>
