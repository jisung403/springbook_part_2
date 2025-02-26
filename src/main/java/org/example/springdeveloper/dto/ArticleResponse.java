package org.example.springdeveloper.dto;

import lombok.Getter;
import org.example.springdeveloper.domain.Article;

@Getter // Lombok 어노테이션을 사용하여 모든 필드에 대한 getter 메서드를 자동 생성합니다.
public class ArticleResponse {

    // 클라이언트에게 전달할 게시글의 제목
    private String title;
    // 클라이언트에게 전달할 게시글의 내용
    private String content;

    /**
     * Article 엔티티를 기반으로 ArticleResponse DTO를 생성하는 생성자입니다.
     * 엔티티의 데이터를 DTO로 변환하여, 클라이언트에 필요한 정보만 전달할 수 있도록 합니다.
     *
     * @param article 변환할 Article 엔티티 객체
     */
    public ArticleResponse(Article article) {
        this.title = article.getTitle();   // Article 엔티티의 제목을 가져와서 설정
        this.content = article.getContent(); // Article 엔티티의 내용을 가져와서 설정
    }
}

