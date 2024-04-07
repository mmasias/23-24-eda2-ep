import javax.print.Doc;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class LibraryController {
    private Library model;
    private LibraryView view;
    private boolean isRunning = true;

    public LibraryController(Library model, LibraryView view) {
        this.model = model;
        this.view = view;
    }

    public void startApplication() {
        while(isRunning){
            view.displayMenu();
            int choice = view.getChoice();
            switch (choice) {
                case 1:
                    handleAddDocument();
                    break;
                case 2:
                    handleRemoveDocument();
                    break;
                case 3:
                    handleSearch();
                    break;
                case 4:
                    displayAllDocuments();
                    break;
                case 5:
                    handleApplicationOutput();
                    isRunning = false;
                    break;
                default:
                    view.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void handleAddDocument() {
        Document document = view.promptDocumentDetails();
        model.addDocument(document);
        view.displayMessage("Document added successfully.");
    }

    private void handleRemoveDocument() {
        String title = view.promptTitle();
        List<Document> documents = model.searchByTitle(title);
        if (documents.isEmpty()) {
            view.displayMessage("No documents found with that title.");
        } else {
            Document documentToRemove = view.promptDocumentSelection(documents);
            model.removeDocument(documentToRemove);
            view.displayMessage("Document removed successfully.");
        }
    }

    private void handleSearch() {
        switch (view.promptDocumentSearch()){
            case 1:
                findDocumentByTitle();
                break;
            case 2:
                findDocumentsByPublicationYear();
                break;
            case 3:
                findDocumentsByAuthor();
                break;
            case 4:
                findDocumentsByType();
                break;
            case 5:
                findDocumentsByKeyword();
                break;
            default:
                System.out.println("No es una opcion valida");
        }
    }

    private List<Document> findDocumentByTitle(){
        String title = view.promptTitle();
        List<Document> documentsByTitle = model.searchByTitle(title);
        if (documentsByTitle.isEmpty()) {
            view.displayMessage("No documents found with that title.");
        } else {
            view.displayDocuments(documentsByTitle);
        }
        return documentsByTitle;
    }

    public List<Document> findDocumentsByAuthor() {
        List<Author> authorName = view.promptAuthors();
        List<Document> documentsByAuthor = new ArrayList<>();
        List<Document> allDocuments = model.getAllDocuments();
        for (Document document : allDocuments) {
            for (Author authorDocument : document.getAuthors()) {
                for (Author authors : authorName) {
                    if (authorDocument.getAuthorName().toLowerCase().contains(authors.getAuthorName().toLowerCase())) {
                        documentsByAuthor.add(document);
                        break;
                    }
                }
            }
        }
        return documentsByAuthor;
    }

    public List<Document> findDocumentsByKeyword( ) {
        List<Keyword> keyword = view.promptKeywords();
        List<Document> documentsByKeyword = new ArrayList<>();
        List<Document> allDocuments = model.getAllDocuments();
        for (Document document : allDocuments) {
            for (Keyword key : document.getKeywords()) {
                for (Keyword keywords : keyword) {
                    if (key.getKeyword().toLowerCase().contains(keywords.getKeyword().toLowerCase())) {
                        documentsByKeyword.add(document);
                        break;
                    }
                }
            }
        }
        return documentsByKeyword;
    }

    public List<Document> findDocumentsByType() {
        System.out.println("Por aqui pasamos 1");
        String type = view.promptDocumentType().toLowerCase();
        System.out.println("Por aqui pasamos 2-- " + type);
        List<Document> allDocuments = model.getAllDocuments();

        return allDocuments.stream()
                .filter(document -> document.getDocumentType().toLowerCase().contains(type))
                .collect(Collectors.toList());
    }

    public List<Document> findDocumentsByPublicationYear() {
        int publicationYear = view.promptPublicationYear();
        List<Document> documentsByPublicationYear = new ArrayList<>();
        List<Document> allDocuments = model.getAllDocuments();
        for (Document document : allDocuments) {
            if (document.getPublicationYear() == publicationYear) {
                documentsByPublicationYear.add(document);
            }
        }
        return documentsByPublicationYear;
    }



    public void displayAllDocuments() {
        List<Document> documents = model.getAllDocuments();
        view.displayAllDocuments(documents);
    }

    private void handleApplicationOutput(){
        view.displayMessage("Exiting the application.");
    }
}
