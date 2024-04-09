import java.util.ArrayList;
import java.util.List;

public class Document {
    private final String title;
    private final List<Author> authors;
    private final int publicationYear;
    private final String documentType;
    private final List<Keyword> keywords;
    protected Document(String title, List<Author> authors, int publicationYear, String documentType, List<Keyword> keywords) {
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.documentType = documentType;
        this.keywords = keywords;
    }
    protected String getTitle() {
        return title;
    }
    protected List<Author> getAuthors() {
        return authors;
    }
    protected int getPublicationYear() {
        return publicationYear;
    }
    protected String getDocumentType() {
        return documentType;
    }
    protected List<Keyword> getKeywords() {
        return keywords;
    }
}