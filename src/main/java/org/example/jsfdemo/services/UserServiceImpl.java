package org.example.jsfdemo.services;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.*;
import org.example.jsfdemo.entities.Author;
import org.example.jsfdemo.entities.User;

import java.util.List;

@Stateless
public class UserServiceImpl implements UserService {
    @PersistenceContext(unitName = "TOBY_PU")
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    public User getUser(String Username) {
        return entityManager.find(User.class, Username);
    }

    @Override
    public List<User> getUsers() {
        String hql = "SELECT u FROM User u";
        TypedQuery<User> query = entityManager.createQuery(hql, User.class);
        return query.getResultList();
    }

    @Override
    public void updateUser(User user) {
        if(user.getUsername() != null) {
            entityManager.merge(user);
        }
    }
}
