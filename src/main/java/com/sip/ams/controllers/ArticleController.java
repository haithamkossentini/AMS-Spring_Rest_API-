package com.sip.ams.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Article;
import com.sip.ams.services.ArticlerService;



@RestController
@RequestMapping({"/articles"})
@CrossOrigin(origins="*")
public class ArticleController {
    @Autowired
    ArticlerService articleService;

    @GetMapping("/")
    public List<Article> getAllArticles() {

        return articleService.getAllArticles();

    }

    @PostMapping("/")
    public Article createArticle(@Valid @RequestBody Article article) {
        return articleService.saveArticle(article);
    }

    @PutMapping("/{articleId}")
    public Article updateArticle(@PathVariable Long articleId, @Valid @RequestBody Article articleRequest) {
        return articleService.updateArticle(articleId,articleRequest);
    }


    @DeleteMapping("/{articleId}")
    public Article deleteArticle(@PathVariable Long articleId) {
        return articleService.deleteArticle(articleId);
    }

    @GetMapping("/{articleId}")
    public Article getArticle(@PathVariable Long articleId) {
        return articleService.getOneArticleById(articleId);
    }


}

