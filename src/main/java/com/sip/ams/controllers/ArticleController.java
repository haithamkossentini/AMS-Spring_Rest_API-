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
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.services.ArticlerService;



@RestController
@RequestMapping({"/articles"})
@CrossOrigin(origins="*")
public class ArticleController {

	private final ArticleRepository articleRepository;
	private final ProviderRepository providerRepository;
    
	@Autowired
    public ArticleController(ArticleRepository articleRepository, ProviderRepository providerRepository) {
        this.articleRepository = articleRepository;
        this.providerRepository = providerRepository;
    }
    @Autowired
    ArticlerService articleService;

    @GetMapping("/")
    public List<Article> getAllArticles() {

        return articleService.getAllArticles();

    }

   /* @PostMapping("/")
    public Article createArticle(@Valid @RequestBody Article article) {
        return articleService.saveArticle(article);
    }
*/
    @PostMapping("/{providerId}")
    Article createArticle(@PathVariable (value = "providerId") Long providerId,
            @Valid @RequestBody Article article) {
			return providerRepository.findById(providerId).map(provider -> {
			article.setProvider(provider);
			return articleRepository.save(article);
			}).orElseThrow(() -> new IllegalArgumentException("ProviderId " + providerId + " not found"));
			}
    /*@PutMapping("/{articleId}")
    public Article updateArticle(@PathVariable Long articleId, @Valid @RequestBody Article articleRequest) {
        return articleService.updateArticle(articleId,articleRequest);
    }*/
    @PutMapping("/{providerId}/{articleId}")
    public Article updateArticle(@PathVariable (value = "providerId") Long providerId,
                                 @PathVariable (value = "articleId") Long articleId,
                                 @Valid @RequestBody Article articleRequest) {
        if(!providerRepository.existsById(providerId)) {
            throw new IllegalArgumentException("ProviderId " + providerId + " not found");
        }

        return articleRepository.findById(articleId).map(article -> {
        	 article.setPrice(articleRequest.getPrice());
             article.setLabel(articleRequest.getLabel()); 
             article.setPicture(articleRequest.getPicture()); 
        return articleRepository.save(article);
        }).orElseThrow(() -> new IllegalArgumentException("ArticleId " + articleId + "not found"));
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

