package app.springbootproject4web.app.Bookshelf.Pojo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import app.springbootproject4web.app.Book.Pojo.Book;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Bookshelf {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 128, nullable = false)
    private String adress;

    @ManyToMany
    @JsonManagedReference
    private List<Book> books;

    public Bookshelf() {
    }

    public Bookshelf(String name, String adress, List<Book> books) throws Exception {
        setName(name);
        setAdress(adress);
        setBooks(books);
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

    public void setName(String name) throws Exception {

        if (name == null || name.isEmpty())
            throw new Exception("Name vuoto");

        if (name.length() > 64)
            throw new Exception("Name troppo lungo");

        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) throws Exception {
        if (name == null || name.isEmpty())
            throw new Exception("Address è vuoto");

        if (name.length() > 128)
            throw new Exception("Address troppo long");

        this.adress = adress;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void removeBook(Book book) {
        for (int i = 0; i < books.size(); i++) {

            Book b = books.get(i);
            if (b.getId() == book.getId()) {
                books.remove(i);
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "Bookshelf: " + getName() + " " + getAdress() + " " + " all books: " + getBooks();
    }
}
