package org.example.springdeveloper.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.DefaultSecurityFilterChain;

import static org.springframework.boot.autoconfigure.security.servlet.PathRequest.toH2Console;

@RequiredArgsConstructor // final 필드를 포함한 생성자를 자동으로 생성해 줌
@Configuration // Spring 설정 클래스임을 나타냄 (Spring Security 설정을 위한 클래스)
public class WebSecurityConfig {

    private final UserDetailsService userService;
    // ✅ 사용자 정보를 로드하는 서비스 (UserDetailService를 주입받음)

    /**
     * 📌 Spring Security의 웹 보안 설정을 일부 비활성화하는 메서드
     * @return WebSecurityCustomizer 객체
     */
    @Bean
    public WebSecurityCustomizer configure() {
        return (web) -> web.ignoring()
                .requestMatchers(toH2Console()) // ✅ H2 콘솔 URL 보안 적용 예외 처리 (H2 데이터베이스 사용 시 필요)
                .requestMatchers("/static/**"); // ✅ 정적 파일(css, js, images 등) 보안 예외 처리
    }

    /**
     * 📌 특정 HTTP 요청에 대한 보안 설정을 수행하는 메서드
     * @param http Spring Security의 HttpSecurity 객체
     * @return DefaultSecurityFilterChain (보안 필터 체인)
     * @throws Exception 예외 처리
     */
    @Bean
    public DefaultSecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests() // ✅ 인증 및 인가(접근 권한) 설정 시작
                .requestMatchers("/login", "/signup", "/user").permitAll()
                // ✅ "/login", "/signup", "/user" URL은 **인증 없이 접근 허용**
                .anyRequest().authenticated()
                // ✅ 위에서 설정한 URL을 제외한 모든 요청은 **인증된 사용자만 접근 가능**

                .and()
                .formLogin() // ✅ 폼 기반 로그인(Form Login) 설정
                .loginPage("/login") // ✅ 로그인 페이지 경로 지정
                .defaultSuccessUrl("/articles")
                // ✅ 로그인 성공 시 이동할 페이지 ("/articles"로 이동)

                .and()
                .logout() // ✅ 로그아웃 설정
                .logoutSuccessUrl("/login") // ✅ 로그아웃 후 이동할 페이지 지정 ("/login")
                .invalidateHttpSession(true)
                // ✅ 로그아웃 시 HTTP 세션을 무효화하여 보안 강화

                .and()
                .csrf().disable()
                // 🚨 CSRF(Cross-Site Request Forgery) 공격 방지를 비활성화 (실습을 위해 비활성화, 실제 서비스에서는 활성화 필요)

                .build();
    }

    /**
     * 📌 사용자 인증(Authentication) 관리 설정
     * @param http Spring Security의 HttpSecurity 객체
     * @param bCryptPasswordEncoder 비밀번호 암호화 객체
     * @param userService 사용자 정보 서비스 (UserDetailsService)
     * @return AuthenticationManager 객체
     * @throws Exception 예외 처리
     */
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       BCryptPasswordEncoder bCryptPasswordEncoder,
                                                       UserDetailsService userService)
            throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(userService) // ✅ 사용자 정보 서비스 설정 (로그인할 때 사용자 정보 로드)
                .passwordEncoder(bCryptPasswordEncoder) // ✅ 비밀번호를 암호화하여 검증
                .and()
                .build();
    }

    /**
     * 📌 비밀번호 암호화(Encoding) 처리를 위한 Bean 등록
     * @return BCryptPasswordEncoder 객체
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
        // ✅ BCrypt 해싱을 사용하여 비밀번호를 안전하게 암호화
    }
}

