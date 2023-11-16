<%--
  Created by IntelliJ IDEA.
  User: arsen_asatryan
  Date: 10/11/2023
  Time: 21:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<div style="text-align: right;">
    <a href="/logout" style="float: right;">Logout</a>
</div>

<div class="login-container" align="center">

    <h2>Welcome</h2>

    <h3><%=  request.getAttribute("message") != null ? request.getAttribute("message") : "" %>
    </h3>
    <h3><%=  request.getSession().getAttribute("username") != null ? "Welcome dear " + request.getSession().getAttribute("username") : "" %>
    </h3>
    <br>
    <form >
        <div class="form-group">
            <button type="button" class="btn" name="login" onclick="window.location.href='/secure/changePassword.jsp'">Change</button>
        </div>
    </form>
</div>


</body>
</html>
