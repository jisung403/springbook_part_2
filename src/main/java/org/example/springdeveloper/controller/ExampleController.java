package org.example.springdeveloper.controller;

import org.springframework.ui.Model;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller // Spring MVC의 컨트롤러임을 명시
public class ExampleController {

    @GetMapping("/thymeleaf/example") // "/thymeleaf/example" URL로 GET 요청이 오면 해당 메서드 실행
    public String thymeleafExample(Model model) { // 뷰(View)로 데이터를 전달할 Model 객체를 매개변수로 받음

        // Person 객체 생성 및 값 설정
        Person examplePerson = new Person();
        examplePerson.setId(1L); // ID 설정 (Long 타입)
        examplePerson.setName("홍길동"); // 이름 설정
        examplePerson.setAge(20); // 나이 설정
        examplePerson.setHobbies(List.of("운동", "독서")); // 취미 리스트 설정

        // 뷰에서 사용할 데이터를 모델에 추가
        model.addAttribute("person", examplePerson); // "person"이라는 키로 examplePerson 객체 저장
        model.addAttribute("today", LocalDate.now()); // "today"라는 키로 현재 날짜 저장

        return "example"; // "example"이라는 이름의 Thymeleaf 뷰 파일 (example.html) 반환하여 렌더링
    }

    @Setter // Lombok을 이용하여 Setter 메서드 자동 생성
    @Getter // Lombok을 이용하여 Getter 메서드 자동 생성
    class Person { // Person 클래스 정의 (내부 클래스)
        private Long id; // 사용자 ID (고유 값)
        private String name; // 사용자 이름
        private int age; // 사용자 나이
        private List<String> hobbies; // 사용자 취미 리스트 (여러 개의 취미를 저장)
    }
}
