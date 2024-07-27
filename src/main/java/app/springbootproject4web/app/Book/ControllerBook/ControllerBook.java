package app.springbootproject4web.app.Book.ControllerBook;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.springbootproject4web.app.Author.Pojo.Author;
import app.springbootproject4web.app.Author.ServiceAuthor.ServiceAuthor;
import app.springbootproject4web.app.Book.BookDTO.BookDTO;
import app.springbootproject4web.app.Book.Pojo.Book;
import app.springbootproject4web.app.Book.ServiceBook.ServiceBook;
import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;
import app.springbootproject4web.app.Bookshelf.ServiceBookshelf.ServiceBookshelf;

@RestController
@RequestMapping("books")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerBook {
    @Autowired
    private ServiceAuthor serviceAuthor;

    @Autowired
    private ServiceBook serviceBook;

    @Autowired
    private ServiceBookshelf serviceBookshelf;

    @PostMapping("add")
    public ResponseEntity<Book> addBooks() {

        try {
            Author a1 = new Author("John", "Doe");
            Author a2 = new Author("Mattia", "Murgia");
            Author a3 = new Author("Mario", "Rossi");
            Author a4 = new Author("Filippo", "Maria");

            serviceAuthor.saveAuthor(a1);
            serviceAuthor.saveAuthor(a2);
            serviceAuthor.saveAuthor(a3);
            serviceAuthor.saveAuthor(a4);

            Book b1 = new Book("Book1", a1);
            Book b2 = new Book("Book2", a2);
            Book b3 = new Book("Book3", a3);
            Book b4 = new Book("Book4", a4);

            serviceBook.saveBook(b1);
            serviceBook.saveBook(b2);
            serviceBook.saveBook(b3);
            serviceBook.saveBook(b4);

            Bookshelf bs1 = new Bookshelf("Bookshelf1", "Via Catania", Arrays.asList(b1, b2));
            Bookshelf bs2 = new Bookshelf("Bookshelf2", "Via Palermo", Arrays.asList(b3, b4));

            serviceBookshelf.saveBookshelf(bs1);
            serviceBookshelf.saveBookshelf(bs2);

        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = serviceBook.getAllBooks();
        return ResponseEntity.ok(books);
    }

    // Get all books in the Bookshelfs by ID
    @GetMapping("/bookshelfs/{bookshelfId}")
    public ResponseEntity<List<Book>> getBooksByBookshelf(@PathVariable Integer bookshelfId) {
        Optional<Bookshelf> bookshelf = serviceBookshelf.getBookshelfById(bookshelfId);

        if (bookshelf.isEmpty())
            return ResponseEntity.notFound().build();

        List<Book> books = serviceBook.findByBookshelf(bookshelf.get());
        return ResponseEntity.ok(books);
    }

    @PostMapping("")
    public ResponseEntity<Book> addBook(@RequestBody BookDTO BookDTO) {
        Book newBook = new Book(BookDTO);
        Optional<Author> findAuthor = serviceAuthor.getAuthorById(BookDTO.getAuthorId());

        if (findAuthor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        Author newAuthor = findAuthor.get();
        newBook.setAuthor(newAuthor);
        serviceBook.saveBook(newBook);

        List<Bookshelf> bookshelvesList = new ArrayList<>();
        for (Integer bookshelfId : BookDTO.getBookshelfIds()) {

            Optional<Bookshelf> findBookshelf = serviceBookshelf.getBookshelfById(bookshelfId);
            if (findBookshelf.isEmpty()) {
                continue;
            }

            Bookshelf bookshelf = findBookshelf.get();
            bookshelf.addBook(newBook);

            serviceBookshelf.saveBookshelf(bookshelf);
            bookshelvesList.add(bookshelf);
        }
        newBook.setBookshelfs(bookshelvesList);

        return ResponseEntity.ok(newBook);
    }

    @PatchMapping("{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Integer id, @RequestBody BookDTO BookDTO) {
        Optional<Book> findBook = serviceBook.getBookById(id);
        if (findBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book newBook = findBook.get();
        newBook.setTitle(BookDTO.getTitle());

        Optional<Author> findAuthor = serviceAuthor.getAuthorById(BookDTO.getAuthorId());
        if (findAuthor.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        Author newAuthor = findAuthor.get();
        newBook.setAuthor(newAuthor);
        serviceBook.saveBook(newBook);

        List<Bookshelf> bookshelfsList = new ArrayList<>();
        List<Bookshelf> Allbookshelfs = serviceBookshelf.getAllBookshelfs();
        for (Bookshelf bookshelfId : Allbookshelfs) {

            if (BookDTO.getBookshelfIds().contains(bookshelfId.getId())) {
                bookshelfId.removeBook(newBook);
                bookshelfId.addBook(newBook);
                serviceBookshelf.saveBookshelf(bookshelfId);
                bookshelfsList.add(bookshelfId);
            } else {
                bookshelfId.removeBook(newBook);
                serviceBookshelf.saveBookshelf(bookshelfId);
            }
            newBook.setBookshelfs(bookshelfsList);
        }
        return ResponseEntity.ok(newBook);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Integer id) {
        Optional<Book> findBook = serviceBook.getBookById(id);
        if (findBook.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Book newBook = findBook.get();

        serviceBook.deleteBook(newBook);
        return ResponseEntity.ok().build();
    }
}
