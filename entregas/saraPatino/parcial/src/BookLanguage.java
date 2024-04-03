package src;

class BookLanguage {
    private int bookId;
    private int authorId;

    public BookLanguage(int bookId, int authorId) {
        this.bookId = bookId;
        this.authorId = authorId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getLanguageId() {
        return authorId;
    }
}