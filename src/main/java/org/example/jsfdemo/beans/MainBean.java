package org.example.jsfdemo.beans;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import org.example.jsfdemo.entities.Article;
import org.example.jsfdemo.entities.Author;
import org.example.jsfdemo.services.ArticleService;
import org.example.jsfdemo.services.AuthorService;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DialogFrameworkOptions;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//import entities.*;

@Named (value= "mainBean")
@ViewScoped
public class MainBean implements Serializable {
    @EJB
    private AuthorService authorService;
    @EJB
    private ArticleService articleService;

    private Author author;
    private Integer authorId;
    private List<Author> authors;
    private List<Article> articles;
    private Article article;
    private Integer articleId;
    private boolean isEdit;



    public MainBean() {
    }

    @PostConstruct
    public void init() {
        author = new Author();
        article = new Article();
        setArticles(new ArrayList<>());
        setAuthors(authorService.getAuthors());
    }

    public void loadAuthor() {
        if (authorId != null) {
            this.author = authorService.getAuthor(authorId);
        }
    }

    public void clearAuthor(){
        author = new Author();
        isEdit = false;
        PrimeFaces.current().executeScript("PF('dlgAuthor').show();");
    }
    private void saveAddAuthor(){
        if ((author != null && author.getId() != null)) {
            authorService.addAuthor(author);
            authors.add(author);
//            setAuthors(authorService.getAuthors());
        }
    }
    public void saveAuthor() {
        if (isEdit)
            saveEditAuthor();
        else
            saveAddAuthor();
        PrimeFaces.current().executeScript("PF('dlgAuthor').hide();");
    }
    public void showEditAuthor(){
        isEdit = true;
        PrimeFaces.current().executeScript("PF('dlgAuthor').show();");
    }
    public void saveEditAuthor(){
        if ((author != null && author.getId() != null)) {
            authorService.updateAuthor(author);
            authors.remove(author);
            authors.add(author);
//            setAuthors(authorService.getAuthors());
        }
    }
    public void deleteAuthor() {
        if ((author != null && author.getId() != null)) {
            authorService.deleteAuthor(author);
            authors.remove(author);
//            setAuthors(authorService.getAuthors());
        }
    }




    public void clearArticle(){
        article = new Article();
        authorId = null;
        isEdit = false;
        PrimeFaces.current().executeScript("PF('dlgArticle').show();");
    }
    public void saveAddArticle() {
        if ((article != null && authorId != null)) {
            article.setAuthor(authorService.getAuthor(authorId));
            articleService.addArticle(article);
            articles.add(article);
//            setArticles(articleService.getArticles());
        }
    }
    public void saveArticle() {
        if (isEdit)
            saveEditArticle();
        else
            saveAddArticle();
        PrimeFaces.current().executeScript("PF('dlgArticle').hide();");

    }
    public void showEditArticle(){
        isEdit = true;
        PrimeFaces.current().executeScript("PF('dlgArticle').show();");
    }
    public void saveEditArticle(){
        if ((article != null && article.getId() != null)) {
            articleService.updateArticle(article);
            articles.remove(article);
            articles.add(article);
//            setAuthors(authorService.getAuthors());
        }
    }
    public void deleteArticle() {
        if ((article != null && article.getId() != null)) {
            articleService.deleteArticle(article);
            articles.remove(article);
//            setAuthors(authorService.getAuthors());
        }
    }






        public void loadArticle() {
        if (articleId != null) {
            this.article = articleService.getArticle(articleId);
        }
    }

    public void saveAuthorAndArticle() {
        saveAuthor();
        saveArticle();
        exit("../views/MainBean.xhtml");
    }

    public void onAuthorSelect(){

        if(this.author!=null){
            setArticles(this.author.getArticles());
        }
    }

    public void addAuthorAndArticle() {
        exit("../views/addAuthorAndArticle.xhtml");
    }
    public void exit(String url) {
        FacesContext fc = FacesContext.getCurrentInstance();
        try {
            fc.getExternalContext().redirect(url);
        } catch (IOException ex) {
            Logger.getLogger(null).log(Level.SEVERE, null, ex);
        }
    }







//    getter and setter
public Author getAuthor() {
    return author;
}

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }
    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public void setAuthors(List<Author> authors) {
        this.authors= authors!= null ? authors : new ArrayList<>();
    }
    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }
}

