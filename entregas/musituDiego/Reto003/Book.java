package v003;

public class Book {
    private int id;
    private String title;
    private int publicationYear;
    private final String type;

    public Book(int id, String title, int publicationYear, String type) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "> Book{" +
               "id=" + id +
               ", title='" + title + '\'' +
               ", publicationYear=" + publicationYear +
               ", type='" + type + '\'' +
               '}';
    }
}
