package stockm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stockm.config.JwtToken;
import stockm.dto.AccountDto;
import stockm.service.AccountService;
import stockm.service.PasswordUpdateService;
import stockm.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {
	 
    private final UserService service;
    private final PasswordUpdateService pwservice;
    private final AccountService accountService;

 
    public UserController(UserService service, PasswordUpdateService pwservice,AccountService accountService) {
        this.service = service;
        this.pwservice = pwservice;
        this.accountService = accountService;
    }

    @GetMapping("/loginpage") 
    public String loginPage() {
        //pwservice.encryptAndSaveAllPasswords(); //비번 암호화
        System.out.println("비번암호");
        return "loginpage"; 
    }
 
    @PostMapping("/login")
    public String loginSuccess(
            @RequestParam("email") String email,
            @RequestParam("password") String password, Model model) {
        System.out.println(email);
        //pwservice.encryptAndSaveAllPasswords();
        JwtToken token = service.login(email, password);

        System.out.println(token);

        // 토큰에서 사용자 정보(이메일)를 추출
        String userEmailFromToken = service.extractUserFromToken(token);
        System.out.println(userEmailFromToken+"userEmailFromToken");

        // 해당 사용자의 계좌 정보를 가져옴
        List<AccountDto> account = accountService.getAccountForUser(userEmailFromToken);
        model.addAttribute("account", account);
        System.out.println(account + "s");

        return "currentaccount";
    }

    
    
    
}

