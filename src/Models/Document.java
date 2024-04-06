import java.util.List;

import Enums.DocumentType;

public class Document {
    private String title;
    private List<Integer> authors;
    private int publicationYear;
    private DocumentType documentType;
    private List<Integer> keywords;

    public Document(String title, List<Integer> authors, int publicationYear, DocumentType documentType, List<Integer> keywords) {
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.documentType = documentType;
        this.keywords = keywords;
    }
}
