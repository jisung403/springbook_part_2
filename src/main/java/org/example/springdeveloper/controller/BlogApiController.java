package org.example.springdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.example.springdeveloper.domain.Article;
import org.example.springdeveloper.dto.AddArticleRequest;
import org.example.springdeveloper.dto.ArticleResponse;
import org.example.springdeveloper.dto.UpdateArticleRequest;
import org.example.springdeveloper.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor // final 필드(blogService)에 대해 생성자를 자동 생성하여 의존성 주입을 처리합니다.
@RestController // 스프링 MVC의 REST 컨트롤러로, HTTP 요청에 대해 JSON 형식의 응답을 반환합니다.
public class BlogApiController {

    // 비즈니스 로직을 수행하는 BlogService를 주입받습니다.
    private final BlogService blogService;

    /**
     * 새 게시글을 추가하는 API 엔드포인트입니다.
     * HTTP POST 요청을 처리하며, 요청 본문의 JSON 데이터를 AddArticleRequest 객체로 매핑합니다.
     * 저장된 게시글 정보를 HTTP 응답 본문에 담아 전송합니다.
     *
     * @param request 클라이언트로부터 전달받은 게시글 생성 요청 데이터
     * @return HTTP 상태 코드 CREATED(201)와 함께 생성된 Article 객체를 반환
     */
    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        // 요청 데이터를 Article 엔티티로 변환하여 저장하고, 저장된 엔티티를 반환받음
        Article savedArticle = blogService.save(request);

        // HTTP 상태 201 (CREATED)와 함께 생성된 게시글 정보를 응답 본문에 담아 전송
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    /**
     * 모든 게시글을 조회하는 API 엔드포인트입니다.
     * HTTP GET 요청을 처리하여, 데이터베이스에 저장된 모든 게시글 정보를 조회합니다.
     *
     * @return HTTP 상태 코드 OK(200)와 함께 모든 게시글 정보를 ArticleResponse 객체 리스트로 반환
     */
    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponse>> findAllArticles() {
        // 모든 Article 엔티티를 조회하고, 이를 ArticleResponse DTO로 변환하여 리스트로 생성
        List<ArticleResponse> articles = blogService.findAll()
                .stream()
                .map(ArticleResponse::new)
                .toList();
        // HTTP 상태 200 (OK)와 함께 게시글 리스트 반환
        return ResponseEntity.ok()
                .body(articles);
    }

    /**
     * 특정 게시글을 조회하는 API 엔드포인트입니다.
     * URL 경로에 포함된 {id} 값을 통해 게시글을 식별하고 조회합니다.
     *
     * @param id 조회할 게시글의 고유 식별자
     * @return HTTP 상태 코드 OK(200)와 함께 조회된 게시글 정보를 ArticleResponse 객체로 반환
     */
    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> findAllArticles(@PathVariable long id) {
        // BlogService를 통해 id에 해당하는 Article 엔티티를 조회
        Article article = blogService.findById(id);
        // 조회된 엔티티를 ArticleResponse DTO로 변환하여 반환
        return ResponseEntity.ok()
                .body(new ArticleResponse(article));
    }

    /**
     * 특정 게시글을 삭제하는 API 엔드포인트입니다.
     * URL 경로에 포함된 {id} 값을 통해 삭제할 게시글을 식별합니다.
     *
     * @param id 삭제할 게시글의 고유 식별자
     * @return HTTP 상태 코드 OK(200)로 삭제 결과를 응답 (응답 본문은 비어 있음)
     */
    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable long id) {
        // BlogService를 통해 해당 id의 게시글 삭제
        blogService.delete(id);

        // HTTP 상태 200 (OK)와 함께 빈 응답 반환
        return ResponseEntity.ok()
                .build();
    }

    /**
     * 특정 게시글을 수정하는 API 엔드포인트입니다.
     * URL 경로의 {id}와 요청 본문의 JSON 데이터를 통해 수정할 게시글을 식별하고 업데이트합니다.
     *
     * @param id 수정할 게시글의 고유 식별자
     * @param request 클라이언트로부터 전달받은 게시글 수정 요청 데이터
     * @return HTTP 상태 코드 OK(200)와 함께 수정된 게시글 정보를 반환
     */
    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable long id,
                                                 @RequestBody UpdateArticleRequest request) {
        // BlogService를 통해 해당 id의 게시글을 업데이트하고, 업데이트된 엔티티를 반환받음
        Article updatedArticle = blogService.update(id, request);

        // HTTP 상태 200 (OK)와 함께 업데이트된 게시글 정보를 응답 본문에 담아 반환
        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}

