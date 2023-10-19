package stockm.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import stockm.config.JwtToken;
import stockm.service.PasswordUpdateService;
import stockm.service.UserService;

@Controller
@RequestMapping("/api/user")
public class UserController {
	 
    private final UserService service;
    private final PasswordUpdateService pwservice;
 
    public UserController(UserService service, PasswordUpdateService pwservice) {
        this.service = service;
        this.pwservice = pwservice;
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
            @RequestParam("password") String password) {
    	System.out.println(email);
        JwtToken token = service.login(email, password);
         
        System.out.println("토큰생성");
        return "accounts/wholeaccount";
    }
}

