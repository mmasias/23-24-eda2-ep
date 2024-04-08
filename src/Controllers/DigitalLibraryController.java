package Controllers;

import Models.DigitalLibrary;
import Views.DigitalLibraryView;

public class DigitalLibraryController {
  private DigitalLibraryView view;
  private KeywordController keywordController;
  private AuthorController authorController;
  private DocumentController documentController;

  public DigitalLibraryController(
    DigitalLibrary library,
    DigitalLibraryView view
  ) {
    this.view = view;
    this.keywordController = new KeywordController(library, view);
    this.authorController = new AuthorController(library, view);
    this.documentController = new DocumentController(library, view);
  }

  public void start() {
    boolean exit = false;
    while (!exit) {
      view.displayMainOptions();
      int option = view.getOption();
      switch (option) {
        case 1:
          documentController.handleDocumentOptions(authorController, keywordController);
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
}
