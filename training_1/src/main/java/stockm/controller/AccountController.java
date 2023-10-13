package stockm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stockm.dto.AccountDto;
import stockm.service.AccountService;


@Controller
@RequestMapping("/accounts") 
public class AccountController {
	 private final AccountService accountService;

	    @Autowired
	    public AccountController(AccountService accountService) {
	        this.accountService = accountService;
	    }
	    
	    @GetMapping("/updateaccountview")
	    public String updateaccountview(Model model) {
	    	
	        System.out.println("df");
	         return "/update_account"; //
	    }

	    
	    @GetMapping("/number/{accountNumber}")
	    public String getAccountByNumber(@PathVariable String accountNumber, Model model) {
	        AccountDto account = accountService.findByAccountNumber(accountNumber);
	        model.addAttribute("account", account);
	        System.out.println(account);
	        return "/account";  
	    }

	    @PutMapping("/numbers/{accountNumber}")
	    public ResponseEntity<?> updateBalance(@PathVariable String accountNumber, @RequestBody Double amount) {
	    	System.out.println("put에 도착");
	        AccountDto account = accountService.findByAccountNumber(accountNumber);
	        if (account == null) {
	            return ResponseEntity.notFound().build();
	        }
	        account.setBalance(amount);
	        accountService.updateBalance(account);
	        return ResponseEntity.ok().build();
	    }
	    
	    //전체 계좌 내역
	    @GetMapping("/wholeaccount")
        public String getwholeAccount(Model model) {
        	List<AccountDto> account= accountService.wholeAccount();
        	model.addAttribute("account",account);
        	System.out.println(account+"s");
        	return "/currentaccount";
        }
	    
	    //주계좌 등록
	    @GetMapping("/mainaccount/{accountNumber}")
	    public String insertmainaccount(@PathVariable String accountNumber) {
	    	System.out.println("mainaccount 입장");
	        accountService.insertmainaccount(accountNumber);
	        return "currentaccount";
	    }
	   
	
}
