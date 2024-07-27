package app.springbootproject4web.app.Author.Pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.springbootproject4web.app.Book.Pojo.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 128, nullable = false)
    private String surname;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private List<Book> books;

    public Author() {
    };

    public Author(String name, String surname) {
        setName(name);
        setSurname(surname);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author: " + getName() + " " + getSurname();
    }
}
