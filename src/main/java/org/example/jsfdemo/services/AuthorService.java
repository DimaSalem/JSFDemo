package org.example.jsfdemo.services;

import jakarta.ejb.Local;
import org.example.jsfdemo.entities.Author;
import java.util.List;

@Local
public interface AuthorService {
     Author getAuthor(Integer id);
     public void addAuthor(Author author);
     public List<Author> getAuthors();
     public void updateAuthor(Author author);
     public void deleteAuthor(Author author);
}
