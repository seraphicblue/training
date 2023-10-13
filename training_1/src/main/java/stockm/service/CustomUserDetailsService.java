package stockm.service;

import java.util.ArrayList;
import java.util.Collections; // 이 임포트를 추가하세요
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import stockm.dto.Users;
import stockm.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // 이메일을 사용하여 DB에서 사용자 검색
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> 
                        new UsernameNotFoundException("User not found with email: " + email)
                );

        return new org.springframework.security.core.userdetails.User(
                user.getEmail(), 
                user.getPassword(), 
                new ArrayList<>()  // 여기서는 권한 목록을 비워 두었지만 필요에 따라 권한을 설정할 수 있습니다.
        );
    }
}

    

