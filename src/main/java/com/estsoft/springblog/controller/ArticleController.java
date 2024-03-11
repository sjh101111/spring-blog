package com.estsoft.springblog.controller;

import com.estsoft.springblog.domain.AddArticleRequest;
import com.estsoft.springblog.domain.Article;
import com.estsoft.springblog.domain.ArticleResponse;
import com.estsoft.springblog.domain.ModifyArticleRequest;
import com.estsoft.springblog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping("/api/articles")
    @ResponseBody
    public ResponseEntity<List<ArticleResponse>> showAll(){
        List<Article> list = articleService.findAll();
        List<ArticleResponse> articleRepons = list.stream().map(n->new ArticleResponse(n.getId(), n.getTitle(), n.getContent())).toList();
        return ResponseEntity.status(HttpStatus.OK).body(articleRepons);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponse> showOne(@PathVariable Long id){
        Article one = articleService.findOne(id);
        return ResponseEntity.ok(one.from());
    }

    @PostMapping("/api/articles")
    public ResponseEntity<ArticleResponse> save(@RequestBody AddArticleRequest request){
        Article article = request.mapper();
        int count = articleService.insertOneArticle(article);
        log.info("{}",count);
        return ResponseEntity.status(HttpStatus.CREATED).body(article.from());
    }

    @PutMapping(value = "/api/articles/{id}")
    @ResponseBody
    public ResponseEntity<ArticleResponse> modify( @PathVariable Long id, @RequestBody ModifyArticleRequest request){
        Article article = articleService.updateOneArticle(id, request);
        return ResponseEntity.ok(article.from());
    }

    @DeleteMapping(value = "/api/articles/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void delete(@PathVariable Long id){
        articleService.deleteOneArticle(id);
    }

}