package app.springbootproject4web.app.Author.ServiceAuthor;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.springbootproject4web.app.Author.Pojo.Author;
import app.springbootproject4web.app.Author.RepositoryAuthor.RepoAuthor;

@Service
public class ServiceAuthor {
    @Autowired
    private RepoAuthor repoAuthor;

    public void saveAuthor(Author Author) {
        repoAuthor.save(Author);
    }

    public List<Author> getAllAuthors() {
        return repoAuthor.findAll();
    }

    public Optional<Author> getAuthorById(Integer ID) {
        return repoAuthor.findById(ID);
    }

    public void deleteAuthor(Author Author) {
        repoAuthor.delete(Author);
    }
}
