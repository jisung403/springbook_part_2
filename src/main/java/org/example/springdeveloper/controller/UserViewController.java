package org.example.springdeveloper.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller // ✅ Spring MVC의 컨트롤러로 등록 (뷰를 반환하는 역할)
public class UserViewController {

    /**
     * 📌 로그인 페이지를 반환하는 메서드
     * @return "login" 템플릿 (Thymeleaf에서 login.html을 렌더링)
     */
    @GetMapping("/login") // ✅ "/login" URL로 GET 요청이 오면 실행됨
    public String login() {
        return "login"; // ✅ login.html 템플릿을 반환 (src/main/resources/templates/login.html)
    }

    /**
     * 📌 회원가입 페이지를 반환하는 메서드
     * @return "signup" 템플릿 (Thymeleaf에서 signup.html을 렌더링)
     */
    @GetMapping("/signup") // ✅ "/signup" URL로 GET 요청이 오면 실행됨
    public String signup() {
        return "signup"; // ✅ signup.html 템플릿을 반환 (src/main/resources/templates/signup.html)
    }
}

