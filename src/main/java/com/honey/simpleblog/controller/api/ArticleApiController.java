package com.honey.simpleblog.controller.api;

import com.honey.simpleblog.dto.ArticleRequestDto;
import com.honey.simpleblog.dto.ArticleResponseDto;
import com.honey.simpleblog.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("/api/articles")
@RequiredArgsConstructor
@RestController
public class ArticleApiController {

    private final ArticleService articleService;
    private final static int FIXED_LIST_SIZE = 20;

    /**
     * ajax 요청을 받아 고정된 사이즈의 게시글 리스트를 비동기로 불러오는 메서드
     * @param page 현재 페이지
     * @return 현재 페이지에서 고정된 사이즈의 게시글 리스트를 반환한다.
     */
    @GetMapping
    public List<ArticleResponseDto> getArticleList(@RequestParam(defaultValue = "1") Integer page) {
        return articleService.getArticleByPage(page, FIXED_LIST_SIZE);
    }

    /**
     * article을 id로 조회
     * @param articleId id
     * @return ArticleDto
     */
    @GetMapping("/{id}")
    public ArticleResponseDto getArticle(@PathVariable("id") Long articleId) {
        return articleService.getArticle(articleId);
    }

    /**
     * article 생성 메서드
     * @param articleDto RequestBody로 요청.
     * @return 결과 여부에 따라 상태메서드 반환
     */
    @PostMapping
    public ResponseEntity<String> createArticle(
            @RequestBody ArticleRequestDto articleDto, @SessionAttribute(name = "id", required = false) String userAccountId) {
        boolean result = articleService.saveArticle(articleDto, userAccountId);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }

    /**
     * article 업데이트 메서드
     * @param articleDto RequestBody로 요청
     * @return 결과 여부에 따라 상태메서드 반환
     */
    @PutMapping
    public ResponseEntity<String> updateArticle(
            @RequestBody ArticleRequestDto articleDto, @SessionAttribute("id") String userAccountId) {
        boolean result = articleService.updateArticle(articleDto, userAccountId);
        if (result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("fail");
        }
    }

    /**
     * article 삭제 메서드
     * @param articleId 쿼리파라미터로 요청
     * @return 결과 여부에 따라 상태메서드 반환
     */
    @DeleteMapping
    public ResponseEntity<String> deleteArticle(
            @RequestParam("id") Long articleId, @SessionAttribute("id") String userAccountId) {
        boolean result = articleService.deleteArticle(articleId, userAccountId);
        if(result) {
            return ResponseEntity.status(HttpStatus.OK).body("success");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("fail");
        }
    }

}
