package v003.src;

class BookAuthor {
    private int bookId;
    private int authorId;

    public BookAuthor(int bookId, int authorId) {
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