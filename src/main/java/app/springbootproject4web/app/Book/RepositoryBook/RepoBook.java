package app.springbootproject4web.app.Book.RepositoryBook;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.springbootproject4web.app.Book.Pojo.Book;
import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;

@Repository
public interface RepoBook extends JpaRepository<Book, Integer> {
    List<Book> findByBookshelfs(Bookshelf Bookshelfs);
}
