package Controllers;

import Models.Author;
import Models.DigitalLibrary;
import Views.DigitalLibraryView;

public class AuthorController {

    private DigitalLibrary library;
    private DigitalLibraryView view;

    public AuthorController(DigitalLibrary library, DigitalLibraryView view) {
        this.library = library;
        this.view = view;
    }

    public void handleAuthorOptions() {
        view.displayEntityOptions("Author");
        int option = view.getOption();
        switch (option) {
            case 1:
                addNewAuthor();
                break;
            case 2:
                editAuthor();
                break;
            case 3:
                deleteAuthor();
                break;
            case 4:
                viewAuthorDetails();
                break;
            case 0:
                return;
            default:
                view.displayInvalidOption();
                handleAuthorOptions();
                break;
        }
    }

    private void addNewAuthor() {
        String name = view.promptForAuthorName();
        String affiliation = view.promptForAuthorAffiliation();
        Author newAuthor = new Author(name, affiliation);
        library.addAuthor(newAuthor);
        view.displayMessage("Author added successfully.");
    }

    private void editAuthor() {
        int authorId = view.promptForAuthorId();
        Author author = library.getAuthor(authorId);
        if (author != null) {
            String name = view.promptForAuthorName();
            String affiliation = view.promptForAuthorAffiliation();
            author.setName(name);
            author.setAffiliation(affiliation);
            library.updateAuthor(authorId, author);
            view.displayMessage("Author updated successfully.");
        } else {
            view.displayMessage("Author not found.");
        }
    }

    private void deleteAuthor() {
        int authorId = view.promptForAuthorId();
        library.deleteAuthor(authorId);
        view.displayMessage("Author deleted successfully.");
    }

    private void viewAuthorDetails() {
        int authorId = view.promptForAuthorId();
        Author author = library.getAuthor(authorId);
        if (author != null) {
            view.displayAuthorDetails(author);
        } else {
            view.displayMessage("Author not found.");
        }
    }
}
