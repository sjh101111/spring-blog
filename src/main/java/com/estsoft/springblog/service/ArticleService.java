package com.estsoft.springblog.service;

import com.estsoft.springblog.domain.Article;
import com.estsoft.springblog.domain.ModifyArticleRequest;
import com.estsoft.springblog.repository.BlogRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final BlogRepository repository;

    public ArticleService(BlogRepository repository) {
        this.repository = repository;
    }

    public List<Article> findAll(){
        return repository.selectAllArticle();
    }

    public Article findOne(Long id){
        return repository.selectOneArticle(id);
    }

    public int insertOneArticle(Article article){
        return repository.insertOneArticle(article);
    }

    public Article updateOneArticle(Long id, ModifyArticleRequest request){
        Article article = findOne(id);
        String title = article.getTitle();
        String content = article.getContent();

        if(!title.equals(request.getTitle())){
            title = request.getTitle();
        }

        if(!content.equals(request.getContent())){
            content = request.getContent();
        }

        Article updateArticle = new Article(id, title, content);
        repository.updateOneArticle(new Article(id, title, content));

        return updateArticle;
    }

    public int deleteOneArticle(Long id){
        return repository.delteOneArticle(id);
    }
}