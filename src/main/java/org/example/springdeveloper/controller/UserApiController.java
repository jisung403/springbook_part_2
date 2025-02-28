package org.example.springdeveloper.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.example.springdeveloper.dto.AddUserRequest;
import org.example.springdeveloper.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor // ✅ final 필드를 포함한 생성자를 자동 생성 (의존성 주입을 쉽게 처리)
@Controller // ✅ Spring MVC의 컨트롤러로 등록 (뷰를 반환하는 역할)
public class UserApiController {

    private final UserService userService; // ✅ 사용자 관련 비즈니스 로직을 처리하는 서비스

    /**
     * 📌 회원가입을 처리하는 API
     * @param request 사용자가 입력한 회원가입 정보 (이메일, 비밀번호)
     * @return 회원가입 후 로그인 페이지로 리다이렉트
     */
    @PostMapping("/user") // ✅ "/user" URL로 POST 요청이 들어오면 실행됨
    public String signup(AddUserRequest request) {
        userService.save(request); // ✅ 회원가입 서비스 메서드 호출 (사용자 저장)
        return "redirect:/login"; // ✅ 회원가입 완료 후 로그인 페이지로 이동
    }

    /**
     * 📌 로그아웃을 처리하는 API
     * @param request HTTP 요청 객체
     * @param response HTTP 응답 객체
     * @return 로그아웃 후 로그인 페이지로 리다이렉트
     */
    @GetMapping("/logout") // ✅ "/logout" URL로 GET 요청이 들어오면 실행됨
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // ✅ Spring Security의 SecurityContextLogoutHandler를 사용하여 로그아웃 처리
        new SecurityContextLogoutHandler().logout(request, response,
                SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login"; // ✅ 로그아웃 후 로그인 페이지로 이동
    }
}

