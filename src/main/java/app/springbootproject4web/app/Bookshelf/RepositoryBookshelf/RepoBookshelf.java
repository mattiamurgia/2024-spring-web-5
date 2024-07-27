package app.springbootproject4web.app.Bookshelf.RepositoryBookshelf;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.springbootproject4web.app.Bookshelf.Pojo.Bookshelf;

@Repository
public interface RepoBookshelf extends JpaRepository<Bookshelf, Integer> {

}
