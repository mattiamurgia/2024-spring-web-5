package app.springbootproject4web.app.Book.BookDTO;

import java.util.List;

public class BookDTO {
    private String title;
    private Integer authorId;
    private List<Integer> bookshelfIds;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Integer authorId) {
        this.authorId = authorId;
    }

    public List<Integer> getBookshelfIds() {
        return bookshelfIds;
    }

    public void setBookshelfIds(List<Integer> bookshelfIds) {
        this.bookshelfIds = bookshelfIds;
    }

    @Override
    public String toString() {
        return "BookDTO: " + getTitle() + " Author ID: " + getAuthorId() + " Bookshelf ID: " + getBookshelfIds();
    }
}
