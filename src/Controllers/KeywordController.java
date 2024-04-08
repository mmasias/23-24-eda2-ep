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
    view.displayKeywordsList(library.getKeywords());
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

  public Keyword getKeywordById(int id) {
    return library.getKeywordById(id);
  }

  private void addNewKeyword() {
    String keyword = view.promptForKeyword();
    Keyword newKeyword = new Keyword(keyword);
    library.addKeyword(newKeyword);
    view.displayMessage("Keyword added successfully.");
  }

  private void editKeyword() {
    int keywordIndex = view.promptForKeywordIndex();
    Keyword keyword = library.getKeyword(keywordIndex);
    if (keyword != null) {
      String newKeyword = view.promptForKeyword();
      keyword.setKeyword(newKeyword);
      library.updateKeyword(keywordIndex, keyword);
      view.displayMessage("Keyword updated successfully.");
    } else {
      view.displayMessage("Keyword not found.");
    }
  }

  private void deleteKeyword() {
    int keywordIndex = view.promptForKeywordIndex();
    library.deleteKeyword(keywordIndex);
    view.displayMessage("Keyword deleted successfully.");
  }

  private void viewKeywordDetails() {
    int keywordIndex = view.promptForKeywordIndex();
    Keyword keyword = library.getKeyword(keywordIndex);
    if (keyword != null) {
      view.displayKeywordDetails(keyword);
    } else {
      view.displayMessage("Keyword not found.");
    }
  }
}
