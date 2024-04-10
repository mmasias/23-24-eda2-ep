import java.util.Objects;
import java.util.Arrays;

public class Book {
    private int id;
    private String title;
    private int publicationYear;
    private final String type;
    private String[] keywords;

    public Book(int id, String title, int publicationYear, String type) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.type = type;
        this.keywords = new String[0];
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getType() {
        return type;
    }

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", type='" + type + '\'' +
                ", keywords=" + Arrays.toString(keywords) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id == book.id &&
                publicationYear == book.publicationYear &&
                Objects.equals(title, book.title) &&
                Objects.equals(type, book.type) &&
                Arrays.equals(keywords, book.keywords);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, title, publicationYear, type);
        result = 31 * result + Arrays.hashCode(keywords);
        return result;
    }
}

