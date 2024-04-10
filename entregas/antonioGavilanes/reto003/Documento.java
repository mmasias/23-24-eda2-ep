package antonioGavilanes.reto003;

import java.util.List;

public class Documento {
    private int id;
    private String title;
    private int publicationYear;
    private final String type;
    private List<String> keywords;

    public Documento(int id, String title, int publicationYear, String type, List<String> keywords) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.type = type;
        this.keywords = keywords;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", publicationYear=" + publicationYear +
                ", type='" + type + '\'' +
                ", keywords=" + keywords +
                '}';
    }

}
