package org.example.springdeveloper.controller;

import org.example.springdeveloper.domain.Article;
import org.example.springdeveloper.dto.ArticleViewResponse;
import org.springframework.ui.Model;
import lombok.RequiredArgsConstructor;
import org.example.springdeveloper.dto.ArticleListViewResponse;
import org.example.springdeveloper.service.BlogService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 붙은 필드를 포함하는 생성자를 자동으로 생성
@Controller // Spring MVC의 컨트롤러임을 명시
public class BlogViewController {

    private final BlogService blogService; // 블로그 서비스 클래스 (비즈니스 로직 처리)

    /**
     * 전체 블로그 글 목록을 조회하는 핸들러 메서드
     * @param model 뷰에 데이터를 전달하는 객체
     * @return articleList 뷰 페이지 (articleList.html)
     */
    @GetMapping("/articles") // "/articles" URL로 GET 요청이 들어오면 실행
    public String getArticles(Model model) {
        // blogService.findAll()을 통해 모든 게시글을 조회한 후 DTO로 변환
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new) // Article 엔티티를 ArticleListViewResponse DTO로 변환
                .toList();

        model.addAttribute("articles", articles); // 뷰에서 사용할 "articles" 데이터를 모델에 추가

        return "articleList"; // articleList.html 뷰 반환
    }

    /**
     * 특정 블로그 글을 조회하는 핸들러 메서드
     * @param id 조회할 글의 ID (URL Path Variable)
     * @param model 뷰에 데이터를 전달하는 객체
     * @return article 뷰 페이지 (article.html)
     */
    @GetMapping("/articles/{id}") // "/articles/{id}" URL로 GET 요청이 들어오면 실행
    public String getArticle(@PathVariable Long id, Model model) {
        Article article = blogService.findById(id); // ID를 이용해 블로그 글 조회
        model.addAttribute("article", new ArticleViewResponse(article)); // 조회한 글을 DTO로 변환하여 뷰에 전달

        return "article"; // article.html 뷰 반환
    }

    /**
     * 새 블로그 글을 작성하거나 기존 글을 수정하는 핸들러 메서드
     * @param id 수정할 글의 ID (없을 수도 있음, 즉 새 글 작성)
     * @param model 뷰에 데이터를 전달하는 객체
     * @return newArticle 뷰 페이지 (newArticle.html)
     */
    @GetMapping("/new-article") // "/new-article" URL로 GET 요청이 들어오면 실행
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) { // ID가 없으면 새 글 작성 모드
            model.addAttribute("article", new ArticleViewResponse()); // 빈 DTO 객체 전달
        } else { // ID가 있으면 기존 글 수정 모드
            Article article = blogService.findById(id); // 해당 ID의 글을 조회
            model.addAttribute("article", new ArticleViewResponse(article)); // 조회한 글을 DTO로 변환하여 뷰에 전달
        }
        return "newArticle"; // newArticle.html 뷰 반환
    }
}

