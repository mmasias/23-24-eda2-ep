package Controllers;

import Models.Author;
import Models.Document;
import Models.DigitalLibrary;
import Models.Keyword;
import Views.DigitalLibraryView;

public class DigitalLibraryController {
    private DigitalLibrary library;
    private DigitalLibraryView view;

    public DigitalLibraryController(DigitalLibrary library, DigitalLibraryView view) {
        this.library = library;
        this.view = view;
    }

    public void start() {
        view.displayMainOptions();
        handleMainOptions();
    }

    private void handleMainOptions() {
        int option = view.getOption();
        switch (option) {
            case 1:
                view.displayDocumentsList(library.getDocuments());
                handleDocumentOptions();
                break;
            case 2:
                view.displayAuthorsList(library.getAuthors());
                handleAuthorOptions();
                break;
            case 3:
                view.displayKeywordsList(library.getKeywords());
                handleKeywordOptions();
                break;
            default:
                view.displayInvalidOption();
                start(); // restart main options
                break;
        }
    }

    private void handleDocumentOptions() {
        // Placeholder for handling document-related user inputs
        // Implement options for add, edit, delete, view document
    }

    private void handleAuthorOptions() {
        // Placeholder for handling author-related user inputs
        // Implement options for add, edit, delete, view author
    }

    private void handleKeywordOptions() {
        // Placeholder for handling keyword-related user inputs
        // Implement options for add, edit, delete, view keyword
    }
}