package org.example.springdeveloper.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.springdeveloper.domain.Article;
import org.example.springdeveloper.dto.AddArticleRequest;
import org.example.springdeveloper.dto.UpdateArticleRequest;
import org.example.springdeveloper.repository.BlogRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;

@RequiredArgsConstructor // final 필드나 @NotNull이 붙은 필드에 대한 생성자를 자동으로 생성하여 의존성 주입을 쉽게 합니다.
@Service // 이 클래스를 스프링의 서비스 빈으로 등록하여, 비즈니스 로직을 수행하는 서비스 계층임을 나타냅니다.
public class BlogService {

    // BlogRepository를 주입받아, 데이터베이스의 Article 엔티티에 대한 CRUD 작업을 수행합니다.
    private final BlogRepository blogRepository;

    /**
     * 블로그 글을 저장하는 메서드입니다.
     * 클라이언트로부터 전달받은 AddArticleRequest DTO를 엔티티로 변환한 후,
     * BlogRepository를 통해 데이터베이스에 저장합니다.
     *
     * @param request 게시글 생성 요청 DTO
     * @return 저장된 Article 엔티티
     */
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    /**
     * 데이터베이스에 저장된 모든 블로그 글을 조회하는 메서드입니다.
     *
     * @return 전체 Article 리스트
     */
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    /**
     * 주어진 id에 해당하는 블로그 글을 조회하는 메서드입니다.
     * 만약 해당 id의 게시글이 없으면, IllegalArgumentException 예외를 발생시킵니다.
     *
     * @param id 조회할 게시글의 id
     * @return 조회된 Article 엔티티
     * @throws IllegalArgumentException 해당 id의 게시글이 존재하지 않을 경우
     */
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    /**
     * 주어진 id에 해당하는 블로그 글을 삭제하는 메서드입니다.
     *
     * @param id 삭제할 게시글의 id
     */
    public void delete(long id) {
        blogRepository.deleteById(id);
    }

    /**
     * 블로그 글을 수정하는 메서드입니다.
     * @Transactional 어노테이션을 통해 메서드 내의 데이터 변경 작업이 하나의 트랜잭션으로 처리됨을 보장합니다.
     *
     * @param id 수정할 게시글의 id
     * @param request 게시글 수정 요청 DTO
     * @return 수정된 Article 엔티티
     * @throws IllegalArgumentException 해당 id의 게시글이 존재하지 않을 경우
     */
    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        // id에 해당하는 게시글을 조회합니다. 없으면 예외 발생
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        // 조회된 게시글의 제목과 내용을 수정합니다.
        article.update(request.getTitle(), request.getContent());
        // 트랜잭션 커밋 시, 변경된 내용이 데이터베이스에 반영됩니다.

        return article;
    }
}

