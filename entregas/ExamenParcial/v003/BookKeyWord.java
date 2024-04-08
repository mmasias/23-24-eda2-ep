package v003;

class BookKeyWord {
    private int bookId;
    private int keywordId;

    public BookKeyWord(int bookId, int keywordId) {
        this.bookId = bookId;
        this.keywordId = keywordId;
    }

    public int getBookId() {
        return bookId;
    }

    public int getKeyWordId() {
        return keywordId;
    }
}