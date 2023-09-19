<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Balance</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<!-- A simple form to input the account number and amount -->
<form id="updateForm">
    Account Number: <input type="text" id="accountNumber" name="accountNumber"><br>
    Amount: <input type="text" id="amount" name="amount"><br>
    <input type="button" value="Update" onclick="updateBalance()">
</form>

<script type="text/javascript">
    function updateBalance() {
        var accountNumber = $("#accountNumber").val();
        var amount = $("#amount").val();

        $.ajax({
            url: '/accounts/numbers/' + accountNumber,
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify(parseFloat(amount)),
            success: function(response) {
                alert('계좌가 성공적으로 업데이트 되었습니다!');
            },
            error: function(error) {
                alert('계좌를 업데이트하는데 문제가 생겼습니다!');
            }
        });
    }
</script>

</body>
</html>
