<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<div style="text-align: center">
    <h2>Register</h2>
    <form action="register" method="post">
        <label for="username">Username:</label>
        <input type="text" id="username" name="username"> <br>
        <span style="color:red">${errorUsername}</span><br><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password"> <br>
        <span style="color:red">${errorPassword}</span><br><br>
        <label for="userAge">Age:</label>
        <input type="text" id="userAge" name="userAge"> <br>
        <span style="color:red">${errorAge}</span> <br><br>
        <input type="submit" value="Register">
    </form>
    <br>
    <a href="login.jsp">Login</a>
</div>
</body>
</html>
