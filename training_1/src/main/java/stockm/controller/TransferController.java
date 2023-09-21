package stockm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import stockm.service.AccountService;

@Controller 
@RequestMapping("/transfer")
public class TransferController {

    private final AccountService accountService;

    @Autowired
    public TransferController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/transferpage")
    public String tranferpage(){ 
    	System.out.println("transfer도착");
    	return "/transfer";
    }	
    
    @PostMapping("/execute")
    @ResponseBody
    public String executeTransfer(
            @RequestParam String fromAccountNumber,
            @RequestParam String toAccountNumber,
            @RequestParam Double amount) {
    	System.out.println("executeTransfer 도착");
        try {
            accountService.transfer(fromAccountNumber, toAccountNumber, amount);
            System.out.println("송금 성공");
            return "송금 성공!";
        } catch (Exception e) {
            return "송금 중 에러 발생: " + e.getMessage();
        }
    }
}
