<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>송금</title>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

<h2>송금</h2>
<form id="transferForm">
    송금계좌: <input type="text" id="fromAccountNumber" name="fromAccountNumber"><br><br>
    이체계촤: <input type="text" id="toAccountNumber" name="toAccountNumber"><br><br>
    송금액: <input type="text" id="amount" name="amount"><br><br>
    <input type="button" value="Transfer" onclick="executeTransfer()">
</form>

<script type="text/javascript">
    function executeTransfer() {
    	console.log("executeTransfer 함수 호출됨");
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
            	console.log(fromAccountNumber);
                alert('송금중 에러 발생: ' + error.responseText);
                
            }
        });
    }
</script>

</body>
</html>
