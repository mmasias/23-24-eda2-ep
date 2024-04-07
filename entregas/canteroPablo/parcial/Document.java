import java.util.ArrayList;

public class Document {
    private int id;
    private String tittle;
    private int publicationYear;
    private String typeDocument;
    public Document(int id, String tittle,  int publicationYear, String typeDocument) {
        this.id = id;
        this.tittle = tittle;
        this.publicationYear = publicationYear;
        this.typeDocument = typeDocument;
    }
    public int getId() {
        return id;
    }
    public String getTittle() {
        return tittle;
    }
    public int getPublicationYear() {
        return publicationYear;
    }
    public String getTypeDocument() {
        return typeDocument;
    }
    public void setTittle(String tittle) {
        this.tittle = tittle;
    }
    public void setPublicationYear(int publicationYear) {
        this.publicationYear = publicationYear;
    }
    public void setTypeDocument(String typeDocument) {
        this.typeDocument = typeDocument;
    }
    

    


    
    
}
