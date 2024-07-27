package app.springbootproject4web.app.Bookshelf.ServiceBookshelf;

import java.util.List;
import java.util.Optional;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.springbootproject4web.app.Book.Pojo.Book;
import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;
import app.springbootproject4web.app.Bookshelf.RepositoryBookshelf.RepoBookshelf;

@Service
public class ServiceBookshelf {

    @Autowired
    private RepoBookshelf repoBookshelf;

    public void saveBookshelf(Bookshelf Bookshelf) {
        repoBookshelf.save(Bookshelf);
    }

    // Get all Bookshelfs without Book and Author
    public List<Bookshelf> getAllaBookshelfsClean() {
        return repoBookshelf.findAll();
    }

    // Get all Bookshelfs with Book and Author
    @Transactional
    public List<Bookshelf> getAllBookshelfs() {
        List<Bookshelf> bookshelfs = repoBookshelf.findAll();

        for (Bookshelf bookshelf : bookshelfs) {
            Hibernate.initialize(bookshelf.getBooks());
            for (Book book : bookshelf.getBooks()) {
                Hibernate.initialize(book.getAuthor());
            }
        }
        return bookshelfs;
    }

    public Optional<Bookshelf> getBookshelfById(Integer ID) {
        return repoBookshelf.findById(ID);
    }

    public void deleteBookshelf(Bookshelf Bookshelf) {
        repoBookshelf.delete(Bookshelf);
    }
}
