package app.springbootproject4web.app.Bookshelf.ControllerBookshelf;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;
import app.springbootproject4web.app.Bookshelf.ServiceBookshelf.ServiceBookshelf;

@RestController
@RequestMapping("bookshelfs")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerBookshelf {

    @Autowired
    private ServiceBookshelf serviceBookshelf;

    @GetMapping("")
    @Transactional
    public ResponseEntity<List<Bookshelf>> getAllBookshelfs() {
        List<Bookshelf> bookshelfs = serviceBookshelf.getAllBookshelfs();
        return ResponseEntity.ok().body(bookshelfs);
    }
}
