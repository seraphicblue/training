function mainaccount(account) {
    var accountNumber = account.id;
    console.log(accountNumber);
    
    $.ajax({
      type: "GET",
      url: "/accounts/mainaccount/" + accountNumber,
      
    }).done(function(){
    	document.location.reload();
    }); 
}

document.addEventListener('DOMContentLoaded', function() {
    var buttons = document.querySelectorAll('button[id]');
    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
            mainaccount(this);
        });
    });
});
