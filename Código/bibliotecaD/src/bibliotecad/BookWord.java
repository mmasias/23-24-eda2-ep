package bibliotecad;

public class BookWord {
    private int bookId;
    private int wordId;

    public BookWord(int bookId, int wordId) {
        this.bookId = bookId;
        this.wordId = wordId;
    }    
    /** 
     * @return int
     */
    public int getBookId() {
        return bookId;
    }    
    /** 
     * @return int
     */
    public int getWordId() {
        return wordId;
    }   
    
}
