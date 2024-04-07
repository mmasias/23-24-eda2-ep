import java.util.ArrayList;

public class Document {
    private int id;
    private String tittle;
    private int publicationYear;
    private String typeDocument;
    private ArrayList<String> keyWords;
    public Document(int id, String tittle,  int publicationYear, String typeDocument,
            ArrayList<String> keyWords) {
        this.id = id;
        this.tittle = tittle;
        this.publicationYear = publicationYear;
        this.typeDocument = typeDocument;
        this.keyWords = keyWords;
    }
    public int getId() {
        return id;
    }
    


    
    
}
