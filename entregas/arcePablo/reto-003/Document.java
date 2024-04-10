import java.sql.Date;
import java.util.ArrayList;

import Types.DocType;
import Types.KeyWordTypes;

public class Document {
    private String title;
    private int documentId;
    private Author author;
    private ArrayList<KeyWordTypes> keyWords;
    private Date datePublished;
    private Enum<DocType> type;

    public Document(int id) {
        title = "";
        documentId = id;
        author = new Author("Unknown");
        keyWords = new ArrayList<>();
        datePublished = new Date(0);
        type = DocType.OTRO;
    }
    
    public Document(String title, String author, ArrayList<KeyWordTypes> keyWords, Date datePublished, Enum<DocType> type, int id) {
        this.title = title;
        documentId = id;
        this.author = new Author(author);
        this.keyWords = keyWords;
        this.datePublished = datePublished;
        this.type = type;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public int getDocumentId() {
        return documentId;
    }
    
    public Author getAuthor() {
        return author;
    }
    
    public void setAuthor(Author author) {
        this.author = author;
    }
    
    public ArrayList<KeyWordTypes> getKeyWords() {
        return keyWords;
    }
    
    public void setKeyWords(ArrayList<KeyWordTypes> keyWords) {
        this.keyWords = keyWords;
    }
    
    public Date getDatePublished() {
        return datePublished;
    }
    
    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }
    
    public Enum<DocType> getType() {
        return type;
    }
    
    public void setType(Enum<DocType> type) {
        this.type = type;
    }
}
