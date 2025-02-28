package org.example.springdeveloper.service;

import lombok.RequiredArgsConstructor;
import org.example.springdeveloper.domain.User;
import org.example.springdeveloper.dto.AddUserRequest;
import org.example.springdeveloper.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor // ✅ final 필드를 포함한 생성자를 자동 생성 (의존성 주입을 쉽게 처리)
@Service // ✅ Spring의 서비스 계층으로 등록 (비즈니스 로직을 수행하는 클래스)
public class UserService {

    private final UserRepository userRepository; // ✅ 데이터베이스에서 사용자 정보를 저장하고 조회하는 저장소
    private final BCryptPasswordEncoder bCryptPasswordEncoder; // ✅ 비밀번호를 안전하게 암호화하는 객체

    /**
     * 📌 사용자를 저장하는 메서드 (회원가입)
     * @param dto 사용자 등록 요청 객체 (이메일 & 패스워드 포함)
     * @return 저장된 사용자의 ID 반환
     */
    public Long save(AddUserRequest dto) {
        return userRepository.save(User.builder()
                        .email(dto.getEmail()) // ✅ 요청된 이메일 저장
                        .password(bCryptPasswordEncoder.encode(dto.getPassword())) // ✅ 비밀번호를 암호화하여 저장
                        .build())
                .getId(); // ✅ 저장된 사용자 엔티티의 ID 반환
    }
}
