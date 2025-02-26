package org.example.springdeveloper.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.example.springdeveloper.domain.Article;
/*
DTO(Data Transfer Object) 역할:
이 클래스는 클라이언트로부터 전달받은 게시글 생성 요청 데이터를 담는 객체입니다.
 */

@NoArgsConstructor // 인자가 없는 기본 생성자를 생성합니다.
// JSON 요청 등에서 기본 생성자가 필요할 때 사용합니다.
@AllArgsConstructor // 모든 필드(title, content)를 파라미터로 받는 생성자를 생성합니다.
@Getter // 모든 필드에 대한 getter 메서드를 자동 생성합니다.
public class AddArticleRequest {

    // 게시글 제목을 저장하는 필드
    private String title;

    // 게시글 내용을 저장하는 필드
    private String content;

    /**
     * 현재 DTO(AddArticleRequest)를 Article 엔티티로 변환하는 메서드입니다.
     * 빌더 패턴을 사용하여 Article 객체를 생성합니다.
     *
     * @return 생성된 Article 엔티티
     */
    public Article toEntity() { // Article 객체를 생성하는 변환 메서드
        return Article.builder()
                .title(title)    // AddArticleRequest의 title 값을 Article 엔티티에 설정
                .content(content) // AddArticleRequest의 content 값을 Article 엔티티에 설정
                .build();         // 빌더 패턴으로 Article 객체를 생성하여 반환
    }
}
