package org.example.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springdeveloper.domain.User;
import org.example.springdeveloper.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // final 필드를 포함한 생성자를 자동으로 생성해 줌
@Service // Spring의 서비스 계층으로 등록 (비즈니스 로직을 담당)
public class UserDetailService implements UserDetailsService {
    // ✅ Spring Security에서 사용자 정보를 가져오는 인터페이스(UserDetailsService)를 구현

    private final UserRepository userRepository;
    // ✅ UserRepository를 주입받아 사용자 정보를 데이터베이스에서 조회할 수 있도록 설정

    /**
     * 📌 사용자 이메일(email)을 기준으로 데이터베이스에서 사용자 정보를 조회하는 메서드
     * @param email 사용자의 이메일 (로그인 시 입력한 값)
     * @return User (Spring Security의 인증 객체)
     * @throws IllegalArgumentException 이메일에 해당하는 사용자가 없을 경우 예외 발생
     */
    @Override
    public User loadUserByUsername(String email) {
        // ✅ 이메일을 기준으로 사용자 정보를 조회
        return userRepository.findByEmail(email)
                // ✅ 만약 해당 이메일이 존재하지 않으면 예외 발생
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없음: " + email));
    }
}

