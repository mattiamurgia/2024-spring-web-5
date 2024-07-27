package app.springbootproject4web.app.Author.ControllerAuthor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.springbootproject4web.app.Author.Pojo.Author;
import app.springbootproject4web.app.Author.ServiceAuthor.ServiceAuthor;

@RestController
@RequestMapping("authors")
@CrossOrigin(origins = "http://localhost:5173")
public class ControllerAuthor {
    @Autowired
    private ServiceAuthor serviceAuthor;

    @GetMapping("")
    public ResponseEntity<List<Author>> getAllAuthors() {
        List<Author> authors = serviceAuthor.getAllAuthors();
        return ResponseEntity.ok().body(authors);
    }
}
