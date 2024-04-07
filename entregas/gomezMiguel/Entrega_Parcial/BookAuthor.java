package Entrega_Parcial;
public class BookAuthor {
    private int bookid;
    private int authorid;

    public BookAuthor(int bookid, int authorid) {
        this.bookid = bookid;
        this.authorid = authorid;
    }

    public int getbookid() {
        return bookid;
    }

    public void setbookid(int bookid) {
        this.bookid = bookid;
    }

    public int getauthorid() {
        return authorid;
    }

    public void setauthorid(int authorid) {
        this.authorid = authorid;
    }

}
