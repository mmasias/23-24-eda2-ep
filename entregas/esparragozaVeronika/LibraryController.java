import java.util.List;
import java.util.ArrayList;

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
                    handleSearchByTitle();
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

    private void handleSearchByTitle() {
        String title = view.promptTitle();
        List<Document> documents = model.searchByTitle(title);
        if (documents.isEmpty()) {
            view.displayMessage("No documents found with that title.");
        } else {
            view.displayDocuments(documents);
        }
    }

    public void displayAllDocuments() {
        List<Document> documents = model.getAllDocuments();
        view.displayAllDocuments(documents);
    }

    private void handleApplicationOutput(){
        view.displayMessage("Exiting the application.");
    }
}
