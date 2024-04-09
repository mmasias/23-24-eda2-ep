import java.util.ArrayList;
import java.util.List;

public class Autor {
    private String name;
    private List<Book> documents;

    public Author(String name) {
        this.name = name;
        this.documents = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Book> getDocuments() {
        return documents;
    }

    public void addDocument(Book document) {
        if (!documents.contains(document)) {
            documents.add(document);
            if (!document.getAuthors().contains(this)) {
                document.addAuthor(this);
            }
        }
    }

    public void removeDocument(Book document) {
        if (documents.remove(document)) {
            if (document.getAuthors().contains(this)) {
                document.removeAuthor(this);
            }
        }
    }
}