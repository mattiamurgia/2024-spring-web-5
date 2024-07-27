package app.springbootproject4web.app.Author.RepositoryAuthor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.springbootproject4web.app.Author.Pojo.Author;

@Repository
public interface RepoAuthor extends JpaRepository<Author, Integer> {

}
