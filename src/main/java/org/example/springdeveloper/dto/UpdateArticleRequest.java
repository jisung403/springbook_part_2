package org.example.springdeveloper.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor // 기본 생성자를 자동으로 생성합니다. (필드 값 없이 객체 생성이 가능)
@AllArgsConstructor // 모든 필드(title, content)를 파라미터로 받는 생성자를 자동 생성합니다.
@Getter // Lombok 어노테이션을 사용하여 모든 필드에 대한 getter 메서드를 자동 생성합니다.
public class UpdateArticleRequest {

    // 게시글 수정 요청에서 전달받을 제목
    private String title;

    // 게시글 수정 요청에서 전달받을 내용
    private String content;
}
