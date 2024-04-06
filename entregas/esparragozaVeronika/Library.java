import java.util.ArrayList;
import java.util.List;

public class Library {

    private List<Document> documents;
    private List<Author> authors;
    private List<Keyword> keywords;

    public Library() {
        documents = new ArrayList<>();
        authors = new ArrayList<>();
        keywords = new ArrayList<>();
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public void removeDocument(Document document) {
        documents.remove(document);
    }

    public List<Document> searchByTitle(String title) {
        List<Document> result = new ArrayList<>();
        for (Document document : documents) {
            if (document.getTitle().equalsIgnoreCase(title)) {
                result.add(document);
            }
        }
        return result;
    }

    protected List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

    private List<Author> getAllAuthors() {
        return new ArrayList<>(authors);
    }

    private List<Keyword> getAllKeywords() {
        return new ArrayList<>(keywords);
    }
}
