<%--
  Created by IntelliJ IDEA.
  User: arsen_asatryan
  Date: 10/11/2023
  Time: 21:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>



<div class="login-container" align="center" >

    <h2>Login</h2>
    <h3><%=  request.getAttribute("message") != null ? request.getAttribute("message") : "" %> </h3>
    <br>
    <form action="/login" method="post">
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <br>
        <div class="form-group remember-me">
            <input type="checkbox" id="remember" name="remember">
            <label for="remember">Remember Me</label>
        </div>
        <br>
        <div class="form-group">
            <button type="submit" class="btn" name="login">Login</button>
            <button type="button" class="btn" onclick="window.location.href='register.jsp'">Register</button>
        </div>
    </form>
</div>





</body>
</html>
