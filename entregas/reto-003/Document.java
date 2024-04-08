import java.time.LocalDate;
import java.util.List;

public class Document {
    private int id;
    private String title;
    private LocalDate publicationDate;
    public final Document.DocumentType type;
    private List<String> keywords;
    public enum DocumentType{BOOK,MAGAZINE,ARTICLE,PAPER}


    public Document(int id, String title, LocalDate publicationDate,DocumentType type, List<String> keywords){
        this.id=id;
        this.title=title;
        this.publicationDate=publicationDate;
        this.type=type;
        this.keywords=keywords;
    }

    public int getId(){
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getKeywords() {
        return keywords;
    }

    @Override
    public String toString(){
        return String.format("ID: %d, Título: %s, Fecha de Publicación: %s, Tipo: %s, Palabras clave: %s",
                id, title, publicationDate, type, String.join(", ", keywords));
    }

    public void setTitle(String title) {

        this.title=title;
    }

    public void setPublicationDate(LocalDate newPublicationDate) {
        this.publicationDate=newPublicationDate;
    }

    public void setKeywords(List<String> keywordsList) {
        this.keywords = keywordsList;
    }


}
