package org.example.jsfdemo.services;

import jakarta.ejb.Local;
import org.example.jsfdemo.entities.User;

import java.util.List;

@Local
public interface UserService {
    public void addUser(User user);
    public User getUser(String Username);
    public List<User> getUsers();
    public void updateUser(User user);

}
