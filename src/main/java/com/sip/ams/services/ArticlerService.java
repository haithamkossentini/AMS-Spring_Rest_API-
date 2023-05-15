package com.sip.ams.services;

import com.sip.ams.entities.Article;
import com.sip.ams.repositories.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticlerService {
    @Autowired
    ArticleRepository articleRepository;

    public List<Article> getAllArticles(){
            return articleRepository.findAll();
    }

    public Article getOneArticleById(long idArticle)
    {
        return articleRepository.findById(idArticle).orElseThrow(() -> new IllegalArgumentException("ArticleId " + idArticle + " not found"));
    }

    public Article saveArticle(Article article)
    {
        return articleRepository.save(article);
    }

    public Article updateArticle(long idArticle, Article article)
    {
    	Article temp = null;

        Optional<Article> opt = articleRepository.findById(idArticle);

        if(opt.isPresent())
        {
            temp = opt.get();
            temp.setLabel(article.getLabel());
            temp.setPrice(article.getPrice());
            temp.setPicture(article.getPicture());
            temp.setProvider(article.getProvider());
            articleRepository.save(temp);

        }
        if(temp == null) throw new IllegalArgumentException("Article with id = "+ idArticle +"not Found");
        return temp;

    }
    public Article deleteArticle(long idArticle)
    {
    	Article temp = null;

        Optional<Article> opt = articleRepository.findById(idArticle);

         if(opt.isPresent())
         {
            temp = opt.get();
            articleRepository.delete(temp);

         }
            if(temp == null) throw new IllegalArgumentException("Article with id = "+ idArticle +"not Found");
        return temp;
    }
}
