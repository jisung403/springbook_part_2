package org.example.springdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter // ✅ 클래스의 모든 필드에 대해 Getter 메서드를 자동 생성
@Setter // ✅ 클래스의 모든 필드에 대해 Setter 메서드를 자동 생성
public class AddUserRequest {

    private String email; // ✅ 사용자의 이메일 (로그인 ID 역할)
    private String password; // ✅ 사용자의 비밀번호 (암호화 전 상태)

}

