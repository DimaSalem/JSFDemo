package org.example.jsfdemo.services;

import org.example.jsfdemo.entities.Article;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;
import org.example.jsfdemo.entities.Author;

import java.util.List;

@Stateless
public class ArticleServiceImpl implements ArticleService {
    @PersistenceContext(unitName = "TOBY_PU")
    private EntityManager entityManager;

    @Override
    public Article getArticle(Integer id) {
        return entityManager.find(Article.class, id);
    }

    @Override
    public void addArticle(Article article) {
        entityManager.persist(article);
    }

    @Override
    public List<Article> getArticles() {
        String hql = "SELECT a FROM Article a";
        TypedQuery<Article> query = entityManager.createQuery(hql, Article.class);
        return query.getResultList();
    }

    @Override
    public void updateArticle(Article article) {
        if(article.getId() != null) {
            entityManager.merge(article);
        }
    }

    @Override
    public void deleteArticle(Article article) {
        if(article.getId() != null) {
            Article managedAuthor = entityManager.merge(article);
            entityManager.remove(managedAuthor);
        }
    }
}
