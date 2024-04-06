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

  public DigitalLibraryController(
    DigitalLibrary library,
    DigitalLibraryView view
  ) {
    this.library = library;
    this.view = view;
    this.keywordController = new KeywordController(library, view);
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
          view.displayAuthorsList(library.getAuthors());
          handleAuthorOptions();
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

  private void handleAuthorOptions() {
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
        break;
      default:
        view.displayInvalidOption();
        handleAuthorOptions();
        break;
    }
  }

  private void addNewAuthor() {
    String name = view.promptForAuthorName();
    String affiliation = view.promptForAuthorAffiliation();
    Author author = new Author(name, affiliation);
    library.addAuthor(author);
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
