<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>

<div style="text-align: center">
    <h2>Log in</h2>
    <form action="login" method="post">
        <label for="username">Username</label>
        <input type="text" id="username" name="username"> <br>
        <span style="color:red">${errorUsername}</span> <br>
        <label for="password">Password</label>
        <input type="password" id="password" name="password"> <br>
        <span style="color:red">${errorPassword}</span> <br>
        <input type="submit" value="Login">
    </form>
    <br>
    <a href="register.jsp">Sign up</a>
</div>

</body>
</html>
