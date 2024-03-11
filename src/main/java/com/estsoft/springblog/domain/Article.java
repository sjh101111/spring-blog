package com.estsoft.springblog.domain;

//import jakarta.persistence.*;
import lombok.*;

// POJO (=Plain Object Java Object)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Article {
    private Long id;
    private String title;
    private String content;

    public Article(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public ArticleResponse from(){
        return new ArticleResponse(id, title, content);
    }
}