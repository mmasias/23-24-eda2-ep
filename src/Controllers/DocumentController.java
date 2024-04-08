package Controllers;

import Enums.DocumentType;
import Models.Author;
import Models.DigitalLibrary;
import Models.Document;
import Models.Keyword;
import Views.DigitalLibraryView;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DocumentController {

  private DigitalLibrary library;
  private DigitalLibraryView view;

  public DocumentController(DigitalLibrary library, DigitalLibraryView view) {
    this.library = library;
    this.view = view;
  }

  public void handleDocumentOptions(
    AuthorController authorController,
    KeywordController keywordController
  ) {
    view.displayDocumentsList(library.getDocuments());
    view.displayEntityOptions("Document");
    int option = view.getOption();
    switch (option) {
      case 1:
        addNewDocument(authorController, keywordController);
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
      case 5:
        advancedDocumentSearch();
        break;
      case 0:
        return;
      default:
        view.displayInvalidOption();
        handleDocumentOptions(authorController, keywordController);
        break;
    }
  }

  private void addNewDocument(
    AuthorController authorController,
    KeywordController keywordController
  ) {
    String title = view.promptForDocumentTitle();
    int year = view.promptForDocumentYear();
    List<Integer> authorIds = new ArrayList<Integer>();
    int authorChoice = 0;
    do {
      List<Author> authorChoices = library.getAuthors();
      view.displayAuthorsList(authorChoices);
      authorChoice = view.promptForNewOrExistingAuthor();
      switch (authorChoice) {
        case 1:
          authorIds.add(authorController.addNewAuthor());
          break;
        case 2:
          authorIds =
            Stream
              .concat(
                authorIds.stream(),
                view.promptForAuthors(authorChoices).stream()
              )
              .toList();
          break;
        case 3:
          break;
        default:
          view.displayInvalidOption();
          break;
      }
    } while (authorChoice != 3);
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
    int documentId = view.promptForDocumentIndex();
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
    int documentId = view.promptForDocumentIndex();
    library.deleteDocument(documentId);
    view.displayMessage("Document deleted successfully.");
  }

  private void viewDocumentDetails() {
    int documentId = view.promptForDocumentIndex();
    Document document = library.getDocument(documentId);
    if (document != null) {
      view.displayDocumentDetails(
        document,
        library.getAuthors(),
        library.getKeywords()
      );
    } else {
      view.displayMessage("Document not found.");
    }
  }

  private void advancedDocumentSearch() {
    System.out.println("Search documents by:");
    System.out.println("1. Authors");
    System.out.println("2. Keywords");
    int searchOption = view.getOption();
    switch (searchOption) {
      case 1:
        searchByAuthors();
        break;
      case 2:
        searchByKeywords();
        break;
      default:
        System.out.println("Invalid option. Please try again.");
        advancedDocumentSearch();
    }
  }

  private void searchByAuthors() {
    List<Integer> authorIds = view.promptForAuthors(library.getAuthors());
    List<Document> documents = authorIds
      .stream()
      .flatMap(authorId -> library.getDocumentsByAuthor(authorId).stream())
      .distinct()
      .collect(Collectors.toList());
    view.displayDocumentsList(documents);
  }

  private void searchByKeywords() {
    List<Integer> keywordIds = view.promptForKeywords(library.getKeywords());
    List<Document> documents = keywordIds
      .stream()
      .flatMap(keywordId -> library.getDocumentsByKeyword(keywordId).stream())
      .distinct()
      .collect(Collectors.toList());
    view.displayDocumentsList(documents);
  }
}
