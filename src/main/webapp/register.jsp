<%--
  Created by IntelliJ IDEA.
  User: arsen_asatryan
  Date: 10/11/2023
  Time: 21:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Page</title>
</head>
<body>
<div class="register-container" align="center"  >

    <h2>Registration</h2>
    <h3><%=  request.getAttribute("message") != null ? request.getAttribute("message") : "" %> </h3>
    <br>
    <form action="/register" method="post">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>

        <div class="form-group">
            <label for="repeatPassword">Repeat Password:</label>
            <input type="password" id="repeatPassword" name="repeatPassword" required>
        </div>
        <div class="form-group">
            <label for="age">Age:</label>
            <input type="number" id="age" name="age" required>
        </div>

        <br>
        <div class="form-group">
            <button type="submit" class="btn" name="register">Register</button>
            <button type="button" class="btn" onclick="window.location.href='index.jsp'">Login</button>
        </div>
    </form>
</div>
</body>
</html>
