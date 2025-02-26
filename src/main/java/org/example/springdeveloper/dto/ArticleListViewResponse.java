package org.example.springdeveloper.dto;

import lombok.Getter;
import org.example.springdeveloper.domain.Article;

@Getter // 모든 필드에 대한 Getter 메서드를 Lombok이 자동 생성
public class ArticleListViewResponse { // 게시글 목록 조회를 위한 DTO 클래스
    private final Long id; // 게시글의 고유 ID (수정 불가능한 final 필드)
    private final String title; // 게시글 제목 (수정 불가능한 final 필드)
    private final String content; // 게시글 내용 (수정 불가능한 final 필드)

    /**
     * Article 엔티티 객체를 받아 DTO로 변환하는 생성자
     * @param article Article 엔티티 객체
     */
    public ArticleListViewResponse(Article article) {
        this.id = article.getId(); // Article 엔티티의 ID 값을 가져와 설정
        this.title = article.getTitle(); // Article 엔티티의 제목 값을 가져와 설정
        this.content = article.getContent(); // Article 엔티티의 내용 값을 가져와 설정
    }
}
