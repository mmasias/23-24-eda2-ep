import java.util.ArrayList;
import java.util.List;

public class Document {
    private String title;
    private List<Author> authors;
    private int publicationYear;
    private String documentType;
    private List<Keyword> keywords;

    public Document(String title, List<Author> authors, int publicationYear, String documentType, List<Keyword> keywords) {
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.documentType = documentType;
        this.keywords = keywords;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }
}