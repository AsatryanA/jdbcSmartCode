<%--
  Created by IntelliJ IDEA.
  User: arsen_asatryan
  Date: 13/11/2023
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Change Password</title>
</head>
<body>
<div class="login-container" align="center">

    <h2>Welcome</h2>
    <h3><%=  request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </h3>
    <br>
    <form action="/change" method="post">
        <div class="form-group">
            <label for="newPassword">New Password:</label>
            <input type="password" id="newPassword" name="newPassword" required>
        </div>
        <div class="form-group">
            <label for="repeatPassword">Repeat Password:</label>
            <input type="password" id="repeatPassword" name="repeatPassword" required>
        </div>
        <div class="form-group">
            <button type="submit" class="btn" name="login">Change</button>
        </div>
    </form>
</div>
</body>
</html>
