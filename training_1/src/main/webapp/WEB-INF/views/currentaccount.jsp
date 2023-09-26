<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="../../js/currentaccount.js"></script>
    <meta charset="UTF-8">
    <title>Account List</title>
</head>
<body>

<h2>계좌 목록</h2>

<table border="1">
    <thead>
        <tr>
            <th></th>
            <th>Account Name</th>
            <th>Balance</th>
            <!-- 다른 필요한 필드가 있다면 여기에 추가 -->
        </tr>
    </thead>
    <tbody>
        <c:forEach var="account" items="${account}">
            <tr>
                <td><button type="button" id="${account.accountNumber}">주계좌 등록</button></td>
                <td>${account.accountNumber}</td>
                <td>${account.balance}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>


</body>

</body>
</html>
