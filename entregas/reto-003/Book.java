import java.time.LocalDate;

public class Book {
    private int id;
    private String title;
    private LocalDate publicationDate;
    private final String type;

    public Book(int id, String title, LocalDate publicationDate,String type){
        this.id=id;
        this.title=title;
        this.publicationDate=publicationDate;
        this.type=type;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString(){
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationDate +
                ", type='" + type + '\'' +
                '}';
    }
}
