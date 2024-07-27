package app.springbootproject4web.app.Book.Pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import app.springbootproject4web.app.Author.Pojo.Author;
import app.springbootproject4web.app.Book.BookDTO.BookDTO;
import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String title;

    @ManyToOne
    private Author author;

    @JsonIgnore
    @ManyToMany(mappedBy = "books", cascade = CascadeType.ALL)
    private List<Bookshelf> bookshelfs;

    public Book() {
    }

    public Book(String title, Author author) {
        setTitle(title);
        setAuthor(author);
    }

    public Book(BookDTO BookDTO) {
        setTitle(BookDTO.getTitle());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Bookshelf> getBookshelfs() {
        return bookshelfs;
    }

    public void setBookshelfs(List<Bookshelf> bookshelfs) {
        this.bookshelfs = bookshelfs;
    }

    @Override
    public String toString() {
        return "Book: " + getTitle() + " " + " author: " + getAuthor() + " bookshelfs: " + getBookshelfs();
    }
}
