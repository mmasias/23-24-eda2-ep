import java.util.List;

public class Document {
    private int id;
    private String title;
    private int publicationYear;
    private DocumentType documentType;

    public Document(int id, String title, int publicationYear, DocumentType documentType) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.documentType = documentType;
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

    public void displayInformation() {
        System.out.println("-------------------------");
        System.out.println("Título: " + title);
        System.out.println("Año de publicación: " + publicationYear);
        System.out.println("Tipo de documento: " + documentType);
        System.out.println("-------------------------\n");
    }
}

