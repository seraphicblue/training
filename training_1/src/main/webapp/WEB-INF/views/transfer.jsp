<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Transfer Money</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<h2>Transfer Money</h2>
<form id="transferForm">
    From Account Number: <input type="text" id="fromAccountNumber" name="fromAccountNumber"><br><br>
    To Account Number: <input type="text" id="toAccountNumber" name="toAccountNumber"><br><br>
    Amount: <input type="text" id="amount" name="amount"><br><br>
    <input type="button" value="Transfer" onclick="executeTransfer()">
</form>

<script type="text/javascript">
    function executeTransfer() {
        var fromAccountNumber = $("#fromAccountNumber").val();
        var toAccountNumber = $("#toAccountNumber").val();
        var amount = $("#amount").val();

        $.ajax({ 
            url: '/transfer/execute',
            type: 'POST',
            data: {
                'fromAccountNumber': fromAccountNumber,
                'toAccountNumber': toAccountNumber,
                'amount': amount
            },
            success: function(response) {
                alert(response);
            },
            error: function(error) {
                alert('Error during transfer: ' + error.responseText);
            }
        });
    }
</script>

</body>
</html>
