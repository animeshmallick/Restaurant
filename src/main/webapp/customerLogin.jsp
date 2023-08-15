<html>
    <head><title>Login</title></head>
    <script src="/scripts/scripts.js"></script>
    <body>
        <h1>
            <form name="loginForm" method="POST" action="CustomerLogin" onsubmit="return validateLoginForm()" >
                TABLE ID : <input type="text" name="tableID"><span id="tableIDSpan"></span><br/>
                <input type="submit" value="Login.">
            </form>
        </h1>
    </body>
</html>
