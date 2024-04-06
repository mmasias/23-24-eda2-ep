package Controllers;

import Enums.DocumentType;
import Models.Author;
import Models.DigitalLibrary;
import Models.Document;
import Models.Keyword;
import Views.DigitalLibraryView;
import java.util.List;

public class DigitalLibraryController {

  private DigitalLibrary library;
  private DigitalLibraryView view;
  private KeywordController keywordController;
  private AuthorController authorController;

  public DigitalLibraryController(
    DigitalLibrary library,
    DigitalLibraryView view
  ) {
    this.library = library;
    this.view = view;
    this.keywordController = new KeywordController(library, view);
    this.authorController = new AuthorController(library, view);
  }

  public void start() {
    boolean exit = false;
    while (!exit) {
      view.displayMainOptions();
      int option = view.getOption();
      switch (option) {
        case 1:
          view.displayDocumentsList(library.getDocuments());
          handleDocumentOptions();
          break;
        case 2:
          authorController.handleAuthorOptions();
          break;
        case 3:
          keywordController.handleKeywordOptions();
          break;
        case 0:
          exit = true;
          break;
        default:
          view.displayInvalidOption();
          break;
      }
    }
  }

  private void handleDocumentOptions() {
    view.displayEntityOptions("Document");
    int option = view.getOption();
    switch (option) {
      case 1:
        addNewDocument();
        break;
      case 2:
        editDocument();
        break;
      case 3:
        deleteDocument();
        break;
      case 4:
        viewDocumentDetails();
        break;
      case 0:
        return;
      default:
        view.displayInvalidOption();
        handleDocumentOptions();
        break;
    }
  }

  private void addNewDocument() {
    String title = view.promptForDocumentTitle();
    int year = view.promptForDocumentYear();
    List<Author> authorChoices = library.getAuthors();
    List<Integer> authorIds = view.promptForAuthors(authorChoices);
    List<Keyword> keywordChoices = library.getKeywords();
    List<Integer> keywordIds = view.promptForKeywords(keywordChoices);
    DocumentType documentType = view.promptForDocumentType();

    Document newDocument = new Document(
      title,
      authorIds,
      year,
      documentType,
      keywordIds
    );
    library.addDocument(newDocument);
    view.displayMessage("Document added successfully.");
  }

  private void editDocument() {
    int documentId = view.promptForDocumentId();
    Document document = library.getDocument(documentId);
    if (document != null) {
      String newTitle = view.promptForDocumentTitle();
      int newYear = view.promptForDocumentYear();
      List<Integer> newAuthorIds = view.promptForAuthors(library.getAuthors());
      List<Integer> newKeywordIds = view.promptForKeywords(
        library.getKeywords()
      );
      DocumentType newDocumentType = view.promptForDocumentType();
      document.setTitle(newTitle);
      document.setPublicationYear(newYear);
      document.setAuthors(newAuthorIds);
      document.setKeywords(newKeywordIds);
      document.setDocumentType(newDocumentType);
      library.updateDocument(documentId, document);
      view.displayMessage("Document updated successfully.");
    } else {
      view.displayMessage("Document not found.");
    }
  }

  private void deleteDocument() {
    int documentId = view.promptForDocumentId();
    library.deleteDocument(documentId);
    view.displayMessage("Document deleted successfully.");
  }

  private void viewDocumentDetails() {
    int documentId = view.promptForDocumentId();
    Document document = library.getDocument(documentId);
    if (document != null) {
      view.displayDocumentDetails(document);
    } else {
      view.displayMessage("Document not found.");
    }
  }

}
