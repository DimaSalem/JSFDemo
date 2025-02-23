package org.example.jsfdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.*;

@Entity
@Table(name = "AUTHOR")
public class Author {
    @Id
    @Column(name = "ID", nullable = false)
    private Integer id;

    @Size(max = 255)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Size(max = 255)
    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "AGE")
    private Integer age;

    @OneToMany (mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Article> articles = new ArrayList<>();

    public Integer getId() {
        return id;
    }

    public List<Article> getArticles() {
        return articles;
    }
    public void addArticle(Article article) {
        this.articles.add(article);
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

}