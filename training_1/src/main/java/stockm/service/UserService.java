package stockm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stockm.config.JwtToken;
import stockm.config.JwtTokenProvider;
import stockm.repository.UserRepository;

@Service
@Transactional
public class UserService  {
	 @Autowired
	    private UserDetailsService userDetailsService;
	 
	private final BCryptPasswordEncoder encoder;
    private final UserRepository repository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;
    
 
    public UserService(BCryptPasswordEncoder encoder, UserRepository repository, AuthenticationManagerBuilder authenticationManagerBuilder, JwtTokenProvider jwtTokenProvider) {
    	this.encoder = encoder;
        this.repository = repository;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
        this.jwtTokenProvider = jwtTokenProvider;
    }
 
    public JwtToken login(String email, String password) {
        // Authentication 객체 생성
    	System.out.println("로그인 도착");
    	UserDetails userDetails = null;

        try {
            // 사용자 정보 가져오기
            userDetails = userDetailsService.loadUserByUsername(email);
        } catch (UsernameNotFoundException e) {
            // 사용자가 존재하지 않을 경우 처리
            System.out.println("User not found: " + email);
            throw new BadCredentialsException("Invalid email");
        } catch (Exception e) {
            // 다른 예외 발생 시 처리
            System.out.println("Error loading user details: " + e.getMessage());
            throw new RuntimeException("Error occurred while fetching user details", e);
        }

        // 이후 로직
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
        System.out.println(email+password);
        System.out.println(authenticationToken);
       
        try {
            Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
            System.out.println(authentication);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        System.out.println(authenticationToken);
        // 검증된 인증 정보로 JWT 토큰 생성
        JwtToken token = jwtTokenProvider.generateToken(authentication);
        


        return token;
    }
    
    
   
}