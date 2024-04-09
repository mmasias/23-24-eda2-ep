import java.util.ArrayList;
import java.util.List;

public class Library {
    private final List<Document> documents;
    protected Library() {
        documents = new ArrayList<>();
    }
    protected void addDocument(Document document) {
        documents.add(document);
    }
    protected void removeDocument(Document document) {
        documents.remove(document);
    }
    protected List<Document> searchByTitle(String title) {
        List<Document> result = new ArrayList<>();
        for (Document document : documents) {
            if (document.getTitle().equalsIgnoreCase(title)) {
                result.add(document);
            }
        }
        return result;
    }
    protected List<Document> searchByType(String type) {
        List<Document> result = new ArrayList<>();
        for (Document document : documents) {
            if (document.getDocumentType().equalsIgnoreCase(type)) {
                result.add(document);
            }
        }
        return result;
    }
    protected List<Document> searchByYearofPublication(int year) {
        List<Document> result = new ArrayList<>();
        for (Document document : documents) {
            if (document.getPublicationYear() == year) {
                result.add(document);
            }
        }
        return result;
    }
    protected List<Document> getAllDocuments() {
        return new ArrayList<>(documents);
    }

}
