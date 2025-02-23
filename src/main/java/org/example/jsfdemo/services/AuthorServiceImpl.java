package org.example.jsfdemo.services;

import org.example.jsfdemo.entities.Author;
import jakarta.ejb.Stateless;
import jakarta.persistence.*;

import java.util.List;

@Stateless
public class AuthorServiceImpl implements AuthorService {
    @PersistenceContext(unitName = "TOBY_PU")
    private EntityManager entityManager;

    @Override
    public Author getAuthor(Integer id) {
        return entityManager.find(Author.class, id);
    }

    @Override
    public void addAuthor(Author author) {
        entityManager.persist(author);
    }

    @Override
    public List<Author> getAuthors() {
        String hql = "SELECT a FROM Author a";
        TypedQuery<Author> query = entityManager.createQuery(hql, Author.class);
        return query.getResultList();
    }

    @Override
    public void updateAuthor(Author author) {
        if(author.getId() != null) {
//            Article managedAuthor = entityManager.merge(article);
            entityManager.persist(author);

        }
    }

    @Override
    public void deleteAuthor(Author author) {
        if(author.getId() != null) {
            Author managedAuthor = entityManager.merge(author);
            entityManager.remove(managedAuthor);
        }
    }
}
