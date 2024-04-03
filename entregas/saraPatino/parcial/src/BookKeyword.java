package src;

class BookKeyword {
    private int bookId;
    private int authorId;

    public BookKeyword(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getKeywordId() {
        return authorId;
    }
}