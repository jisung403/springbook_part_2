package org.example.springdeveloper.repository;

import org.example.springdeveloper.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * BlogRepository 인터페이스는 Spring Data JPA의 JpaRepository를 확장하여
 * Article 엔티티에 대한 CRUD(Create, Read, Update, Delete) 및 페이징/정렬 기능을 제공합니다.
 * 제네릭 타입으로 Article과 Article의 기본키 타입(Long)을 지정하여 사용합니다.
 */
public interface BlogRepository extends JpaRepository<Article, Long> {
    // 추가적인 커스텀 쿼리 메서드를 선언할 수 있습니다.
}
