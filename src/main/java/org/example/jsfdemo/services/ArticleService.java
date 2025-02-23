package org.example.jsfdemo.services;

import jakarta.ejb.Local;
import org.example.jsfdemo.entities.Article;

import java.util.List;

@Local
public interface ArticleService {
    Article getArticle(Integer id);
    public void addArticle(Article article);
    public List<Article> getArticles();
    public void updateArticle(Article article);
    public void deleteArticle(Article article);
}
