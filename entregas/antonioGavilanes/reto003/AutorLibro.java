package antonioGavilanes.reto003;

public class AutorLibro {

    private int bookId;
    private int authorId;

    public AutorLibro(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getAuthorId() {
        return authorId;
    }
    
}
