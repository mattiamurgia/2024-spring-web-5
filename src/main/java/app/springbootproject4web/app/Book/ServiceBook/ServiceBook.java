package app.springbootproject4web.app.Book.ServiceBook;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.springbootproject4web.app.Book.Pojo.Book;
import app.springbootproject4web.app.Book.RepositoryBook.RepoBook;
import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;

@Service
public class ServiceBook {

    @Autowired
    private RepoBook repoBook;

    public void saveBook(Book Book) {
        repoBook.save(Book);
    }

    public List<Book> getAllBooks() {
        return repoBook.findAll();
    }

    public List<Book> findByBookshelf(Bookshelf Bookshelf) {
        return repoBook.findByBookshelfs(Bookshelf);
    }

    public Optional<Book> getBookById(Integer ID) {
        return repoBook.findById(ID);
    }

    public void deleteBook(Book Book) {
        repoBook.delete(Book);

    }
}
