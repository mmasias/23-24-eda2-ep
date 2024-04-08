import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DocumentCollection {

    private List<Document> documents;

    public DocumentCollection() {
        documents = new ArrayList<>();
    }

    public void addDocument(Document document) throws DocumentAlreadyExistsException {
        if (documents.contains(document)) {
            throw new DocumentAlreadyExistsException("El documento ya existe");
        }
        documents.add(document);
    }

    public List<Document> searchByTitle(String title) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents) {
            if (document.getTitle().toLowerCase().contains(title.toLowerCase())) {
                results.add(document);
            }
        }
        return results;
    }

    public List<Document> searchByAuthor(String authorName) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents) {
            for (Author author : document.getAuthors()) {
                if (author.getName().toLowerCase().contains(authorName.toLowerCase())) {
                    results.add(document);
                    break;
                }
            }
        }
        return results;
    }

    public List<Document> searchByKeywords(String keyword) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents) {
            for (Keywords keywords : document.getKeywords()) {
                if (keywords.getKeyword().toLowerCase().contains(keyword.toLowerCase())) {
                    results.add(document);
                    break;
                }
            }
        }
        return results;
    }

    public void editDocument(String title, Document newDocument) {
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getTitle().equals(title)) {
                documents.set(i, newDocument);
                break;
            }
        }
    }

    public void removeDocument(String title) {
        documents.removeIf(document -> document.getTitle().equalsIgnoreCase(title));
    }

    public void displayDocuments() {
        for (Document document : documents) {
            document.displayInformation();
        }
    }

    // Búsqueda a texto completo:
    public List<Document> searchByContent(String content) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents) {
            if (document.getTitle().toLowerCase().contains(content.toLowerCase()) ||
                    document.getAuthors().toString().toLowerCase().contains(content.toLowerCase()) ||
                    document.getKeywords().toString().toLowerCase().contains(content.toLowerCase())) {
                results.add(document);
            }
        }
        return results;
    }

    // Ordenación de documentos:
    public void sortByTitle() {
        Collections.sort(documents, (o1, o2) -> o1.getTitle().compareTo(o2.getTitle()));
    }

    public void sortByPublicationYear() {
        Collections.sort(documents, (o1, o2) -> o1.getPublicationYear() - o2.getPublicationYear());
    }

    public class DocumentAlreadyExistsException extends Exception {

        public DocumentAlreadyExistsException(String message) {
            super(message);
        }
    }

    public int countDocuments() {
        // Implement logic to count the number of documents in the collection
        return this.documents.size(); // Example using size of document list
    }

    public boolean documentExists(String title) {
        // Implement logic to check if a document with the given title exists
        for (Document document : documents) {
        if (document.getTitle().equalsIgnoreCase(title)) {
            return true;
        }
        }
        return false;
    }
}
