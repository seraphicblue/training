<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
</head>
<body>
    <h2>Login Form</h2>
    <form action="/api/user/login" method="post">
        <div>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email">
        </div>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password">
        </div>
        <div>
            <input type="submit" value="Login">
        </div>
    </form>
</body>
</html>
