package entregas.RodriguezGonzalezJoseLuis.entrega003.entities;

public class Document {
    private int id;
    private String title;
    private List<Author> authors;
    private int publicationYear;
    private String documentType;
    private List<Keyword> keywords;

    public Document(int id, String title, List<Author> authors, int publicationYear, String documentType, List<Keyword> keywords) {
        this.id = id;
        this.title = title;
        this.authors = authors;
        this.publicationYear = publicationYear;
        this.documentType = documentType;
        this.keywords = keywords;
    }

    public int getId(){
        return id;
    }
    public String getTitle() {
        return title;
    }
    public List<Author> getAuthors() {
        return authors;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public String getDocumentType() {
        return documentType;
    }
    public List<Keyword> getKeywords() {
        return keywords;
    }
}
