package Controllers;

import Models.DigitalLibrary;
import Models.Keyword;
import Views.DigitalLibraryView;

public class KeywordController {

  private DigitalLibrary library;
  private DigitalLibraryView view;

  public KeywordController(DigitalLibrary library, DigitalLibraryView view) {
    this.library = library;
    this.view = view;
  }

  public void handleKeywordOptions() {
    view.displayEntityOptions("Keyword");
    int option = view.getOption();
    switch (option) {
      case 1:
        addNewKeyword();
        break;
      case 2:
        editKeyword();
        break;
      case 3:
        deleteKeyword();
        break;
      case 4:
        viewKeywordDetails();
        break;
      case 0:
        return;
      default:
        view.displayInvalidOption();
        handleKeywordOptions();
        break;
    }
  }

  private void addNewKeyword() {
    String keyword = view.promptForKeyword();
    Keyword newKeyword = new Keyword(keyword);
    library.addKeyword(newKeyword);
    view.displayMessage("Keyword added successfully.");
  }

  private void editKeyword() {
    int keywordId = view.promptForKeywordId();
    Keyword keyword = library.getKeyword(keywordId);
    if (keyword != null) {
      String newKeyword = view.promptForKeyword();
      keyword.setKeyword(newKeyword);
      library.updateKeyword(keywordId, keyword);
      view.displayMessage("Keyword updated successfully.");
    } else {
      view.displayMessage("Keyword not found.");
    }
  }

  private void deleteKeyword() {
    int keywordId = view.promptForKeywordId();
    library.deleteKeyword(keywordId);
    view.displayMessage("Keyword deleted successfully.");
  }

  private void viewKeywordDetails() {
    int keywordId = view.promptForKeywordId();
    Keyword keyword = library.getKeyword(keywordId);
    if (keyword != null) {
      view.displayKeywordDetails(keyword);
    } else {
      view.displayMessage("Keyword not found.");
    }
  }
}
