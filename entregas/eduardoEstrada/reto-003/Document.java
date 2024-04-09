import java.util.ArrayList;
import java.util.List;

public class Document {
    private String title;
    private int publicationYear;
    private DocumentType documentType;
    private List<DocumentAuthor> authors;
    private List<DocumentKeyword> keywords;

    public Document(String title, int publicationYear, DocumentType documentType) {
        this.title = title;
        this.publicationYear = publicationYear;
        this.documentType = documentType;
        this.authors = new ArrayList<>();
        this.keywords = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public DocumentType getDocumentType() {
        return documentType;
    }

    public List<DocumentAuthor> getAuthors() {
        return authors;
    }

    public List<DocumentKeyword> getKeywords() {
        return keywords;
    }

    public void addAuthor(Author author) {
        authors.add(new DocumentAuthor(this, author));
    }

    public void removeAuthor(Author author) {
        authors.removeIf(documentAuthor -> documentAuthor.getAuthor().equals(author));
    }

    public void addKeyword(Keywords keyword) {
        keywords.add(new DocumentKeyword(this, keyword));
    }

    public void removeKeyword(Keywords keyword) {
        keywords.removeIf(documentKeyword -> documentKeyword.getKeyword().equals(keyword));
    }

    public void displayInformation() {
        System.out.println("-------------------------");
        System.out.println("Título: " + title);
        System.out.println("Autor/es: " + authors);
        System.out.println("Año de publicación: " + publicationYear);
        System.out.println("Tipo de documento: " + documentType);
        System.out.println("Palabras clave: " + keywords);
        System.out.println("-------------------------\n");
    }

    public void setTitle(String newTitle) {
        this.title = newTitle;
    }
    
    public void setPublicationYear(int newPublicationYear) {
        this.publicationYear = newPublicationYear;
    }
    
    public void setDocumentType(DocumentType newDocumentType) {
        this.documentType = newDocumentType;
    }
}