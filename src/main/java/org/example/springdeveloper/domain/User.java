package org.example.springdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name= "users") // 데이터베이스에서 사용할 테이블명을 "users"로 지정
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 접근 제한 (외부에서 직접 객체 생성 방지)
@Getter // 클래스 내 모든 필드에 대해 Getter 메서드 자동 생성
@Entity // JPA 엔티티(Entity)로 선언
public class User implements UserDetails { // Spring Security의 UserDetails 인터페이스를 구현하여 인증 객체로 사용

    @Id // 기본 키(Primary Key) 필드로 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB의 자동 증가(AUTO_INCREMENT) 전략 사용
    @Column(name = "id", updatable = false) // "id" 컬럼을 정의 (수정 불가능)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    // "email" 컬럼을 정의 (null 불가, 중복 불가 - 즉, 유일한 값이어야 함)
    private String email;

    @Column(name = "password") // "password" 컬럼을 정의
    private String password;

    /**
     * 📌 User 엔티티의 생성자 (객체 생성 시 이메일, 비밀번호를 설정)
     * @param email 사용자의 이메일 (고유한 값)
     * @param password 사용자의 비밀번호 (암호화된 상태)
     */
    @Builder // 빌더 패턴을 사용하여 객체 생성 (가독성이 좋고, 선택적으로 값 설정 가능)
    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * 📌 사용자의 권한을 반환하는 메서드 (UserDetails 인터페이스 구현)
     * @return 사용자의 권한 목록 (Spring Security의 GrantedAuthority 형태)
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // 현재는 모든 사용자가 "user" 권한을 갖도록 설정 (추후 확장 가능)
        return List.of(new SimpleGrantedAuthority("user"));
    }

    /**
     * 📌 사용자의 ID(=이메일)를 반환하는 메서드
     * @return 이메일 (사용자의 고유한 식별자 역할)
     */
    @Override
    public String getUsername() {
        return email;
    }

    /**
     * 📌 사용자의 비밀번호를 반환하는 메서드
     * @return 암호화된 비밀번호
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * 📌 계정 만료 여부를 반환하는 메서드
     * @return true: 계정이 만료되지 않음 (항상 활성화 상태)
     */
    @Override
    public boolean isAccountNonExpired() {
        // 계정이 만료되었는지 확인하는 로직 (현재는 항상 true)
        return true;
    }

    /**
     * 📌 계정 잠금 여부를 반환하는 메서드
     * @return true: 계정이 잠기지 않음 (항상 사용 가능)
     */
    @Override
    public boolean isAccountNonLocked() {
        // 계정이 잠금되었는지 확인하는 로직 (현재는 항상 true)
        return true;
    }

    /**
     * 📌 패스워드 만료 여부를 반환하는 메서드
     * @return true: 패스워드가 만료되지 않음 (항상 유효)
     */
    @Override
    public boolean isCredentialsNonExpired() {
        // 비밀번호가 만료되었는지 확인하는 로직 (현재는 항상 true)
        return true;
    }

    /**
     * 📌 계정 사용 가능 여부를 반환하는 메서드
     * @return true: 계정이 활성화 상태 (항상 사용 가능)
     */
    @Override
    public boolean isEnabled() {
        // 계정이 사용 가능한지 확인하는 로직 (현재는 항상 true)
        return true;
    }
}
