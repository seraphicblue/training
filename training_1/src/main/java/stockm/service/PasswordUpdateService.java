package stockm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import stockm.dto.Users;
import stockm.repository.UserRepository;

@Service
public class PasswordUpdateService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public void encryptAndSaveAllPasswords() {
        // 1. 데이터베이스에서 모든 사용자 정보 가져오기
        List<Users> users = userRepository.findAll();

        for (Users user : users) {
            // 2. 비밀번호 암호화
            String encryptedPassword = bCryptPasswordEncoder.encode(user.getPassword());
            
            // 3. 암호화된 비밀번호로 업데이트
            user.setPassword(encryptedPassword);
            userRepository.save(user);
        }

        
    }
}
