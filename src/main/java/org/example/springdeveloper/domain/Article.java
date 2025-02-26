package org.example.springdeveloper.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity // 이 클래스가 JPA 엔티티임을 나타내며, 데이터베이스 테이블과 매핑됨
@Getter // Lombok 어노테이션으로, 모든 필드에 대한 getter 메서드를 자동 생성
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자를 생성하되, 외부에서 직접 호출하지 못하도록 protected 접근제한을 설정
public class Article {

    @Id // 해당 필드를 엔티티의 기본키(primary key)로 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본키 생성을 데이터베이스에 위임하며, 1씩 자동 증가
    @Column(name = "id", updatable = false) // 데이터베이스 컬럼 'id'와 매핑되며, 수정 불가능한 값임
    private Long id;

    @Column(name = "title", nullable = false) // 데이터베이스 컬럼 'title'과 매핑, null 값을 허용하지 않음
    private String title;

    @Column(name = "content", nullable = false) // 데이터베이스 컬럼 'content'와 매핑, null 값을 허용하지 않음
    private String content;

    // Lombok의 @Builder 어노테이션을 통해 빌더 패턴으로 객체를 생성할 수 있게 함.
    // 이 생성자를 사용하여 Article 객체를 생성할 때 필수값을 전달받음
    @Builder
    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    // 엔티티의 제목과 내용을 업데이트하기 위한 메서드
    // 게시글 수정 시 호출하여 기존 객체의 상태를 변경함
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }

    @CreatedDate // 엔티티가 생성될 때 생성 시간 저장
    @Column(name= "created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate // 엔티티가 수정될 때 수정 시간 저장
    @Column(name= "updated_at")
    private LocalDateTime updatedAt;
}
