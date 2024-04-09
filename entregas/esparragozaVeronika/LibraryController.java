import java.util.List;
import java.util.stream.Collectors;

public class LibraryController {
    private final Library model;
    private final LibraryView view;
    private boolean isRunning = true;

    protected LibraryController(Library model, LibraryView view) {
        this.model = model;
        this.view = view;
    }
    protected void startApplication() {
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
                    view.displayMessage("Opcion invalida, intentelo otra vez.");
            }
        }
    }
    private void handleAddDocument() {
        Document document = view.promptDocumentDetails();
        model.addDocument(document);
        view.displayMessage("Documento añadido.");
    }
    private void handleRemoveDocument() {
        String title = view.promptTitle();
        List<Document> documents = model.searchByTitle(title);
        if (documents.isEmpty()) {
            view.displayMessage("No hay documentos con ese titulo.");
        } else {
            Document documentToRemove = view.promptDocumentSelection(documents);
            model.removeDocument(documentToRemove);
            view.displayMessage("El documento se ha eliminado.");
        }
    }
    private void handleSearch() {
        view.displayDocumentSearch();
        int choice = view.getChoice();
        switch (choice) {
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
    private void findDocumentByTitle(){
        String documentTitle = view.promptTitle();
        List<Document> documentsByTitle = model.searchByTitle(documentTitle);
        documentsByTitle.stream()
                .filter(document -> document.getDocumentType().toLowerCase().contains(documentTitle))
                .collect(Collectors.toList());
        if (documentsByTitle.isEmpty()) {
            view.displayMessage("No hay documentos con ese titulo.");
        } else {
            view.displayDocuments(documentsByTitle);
        }
    }
    private void findDocumentsByAuthor() {
        List<Author> authors = view.promptAuthors();
        List<Document> allDocuments = model.getAllDocuments();
        List<Document> filteredDocuments = allDocuments.stream()
                .filter(document -> document.getAuthors().stream()
                        .anyMatch(author ->
                                authors.stream()
                                        .anyMatch(au ->
                                                author.getAuthorName().toLowerCase().contains(au.getAuthorName().toLowerCase()))))
                .toList();

        if (filteredDocuments.isEmpty()) {
            view.displayMessage("No hay documentos con ese autor.");
        } else {
            view.displayDocuments(filteredDocuments);
        }
    }
    private void findDocumentsByKeyword() {
        List<Keyword> keywords = view.promptKeywords();
        List<Document> allDocuments = model.getAllDocuments();
        List<Document> filteredDocuments = allDocuments.stream()
                .filter(document -> document.getKeywords().stream()
                        .anyMatch(keyword ->
                                keywords.stream()
                                        .anyMatch(key ->
                                                keyword.getKeyword().toLowerCase().contains(key.getKeyword().toLowerCase()))))
                .toList();

        if (filteredDocuments.isEmpty()) {
            view.displayMessage("No hay documentos con esa palabra clave.");
        } else {
            view.displayDocuments(filteredDocuments);
        }
    }
    private void findDocumentsByType() {
        String documentType = view.promptDocumentType();
        List<Document> documentsByType = model.searchByType(documentType);
        List<Document> filteredDocuments = documentsByType.stream()
                .filter(document -> document.getDocumentType().toLowerCase().contains(documentType))
                .collect(Collectors.toList());

        if (filteredDocuments.isEmpty()) {
            view.displayMessage("No hay documentos con ese tipo.");
        } else {
            view.displayDocuments(filteredDocuments);
        }
    }
    private void findDocumentsByPublicationYear() {
        int publicationYear = view.promptPublicationYear();
        List<Document> allDocuments = model.searchByYearofPublication(publicationYear);
        List<Document> documentsByYear = allDocuments.stream()
                .filter(document -> document.getPublicationYear() == publicationYear)
                .collect(Collectors.toList());

        if (documentsByYear.isEmpty()) {
            view.displayMessage("No hay documentos publicados en el año " + publicationYear + ".");
        } else {
            view.displayDocuments(documentsByYear);
        }
    }
    private void displayAllDocuments() {
        List<Document> documents = model.getAllDocuments();
        view.displayAllDocuments(documents);
    }
    private void handleApplicationOutput(){
        view.displayMessage("Exiting the application.");
    }
}
