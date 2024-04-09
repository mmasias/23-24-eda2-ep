import java.util.ArrayList;
import java.util.List;

public class DocumentCollection {
    private List<Document> documents;
    private List<DocumentAuthor> documentAuthors;
    private List<DocumentKeyword> documentKeywords;

    public DocumentCollection() {
        documents = new ArrayList<>();
        documentAuthors = new ArrayList<>();
        documentKeywords = new ArrayList<>();
    }

    public void addDocument(Document document) {
        documents.add(document);
        System.out.println("Documento añadido: " + document.getTitle());
    
        for (DocumentAuthor documentAuthor : document.getAuthors()) {
            if (!documentAuthors.contains(documentAuthor)) {
                documentAuthors.add(documentAuthor);
                System.out.println("Autor añadido: " + documentAuthor.getAuthor().getName());
            }
        }
    
        for (DocumentKeyword documentKeyword : document.getKeywords()) {
            if (!documentKeywords.contains(documentKeyword)) {
                documentKeywords.add(documentKeyword);
                System.out.println("Palabra clave añadida: " + documentKeyword.getKeyword());
            }
        }
    }
    public List<Document> searchByTitle(String title) {
        List<Document> results = new ArrayList<>();
        for (Document document : documents) {
            if (document.getTitle().equalsIgnoreCase(title)) {
                results.add(document);
            }
        }
        return results;
    }

    public List<Document> searchByAuthor(String authorName) {
        List<Document> results = new ArrayList<>();
        for (DocumentAuthor documentAuthor : documentAuthors) {
            if (documentAuthor.getAuthor().getName().equalsIgnoreCase(authorName)) {
                results.add(documentAuthor.getDocument());
            }
        }
        return results;
    }

    public List<Document> searchByKeywords(Keywords keyword) {
        List<Document> results = new ArrayList<>();
        for (DocumentKeyword documentKeyword : documentKeywords) {
            if (documentKeyword.getKeyword().equals(keyword)) {
                results.add(documentKeyword.getDocument());
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
}