package Views;

import Enums.DocumentType;
import Models.Author;
import Models.Document;
import Models.Keyword;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class DigitalLibraryView {

  private Scanner scanner = new Scanner(System.in);

  public void displayMainOptions() {
    System.out.println("\nDigital Library System");
    System.out.println("1. View Documents");
    System.out.println("2. View Authors");
    System.out.println("3. View Keywords");
    System.out.println("0. Exit");
    System.out.print("Choose an option: ");
  }

  public int getOption() {
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.print("Please enter a valid number: ");
    }
    int option = scanner.nextInt();
    scanner.nextLine();
    return option;
  }

  public void displayDocumentsList(List<Document> documents) {
    if (documents.isEmpty()) {
      System.out.println("No documents available.");
    } else {
      System.out.println("\nDocuments:");
      int index = 1;
      for (Document document : documents) {
        System.out.printf(
          "%d: %s (Year: %d)\n",
          index++,
          document.getTitle(),
          document.getPublicationYear()
        );
      }
    }
    System.out.println("--------------------------------");
  }

  public void displayAuthorsList(List<Author> authors) {
    if (authors.isEmpty()) {
      System.out.println("No authors available.");
    } else {
      int index = 1;
      System.out.println("\nAuthors:");
      for (Author author : authors) {
        System.out.printf(
          "%d. %s (%s)\n",
          index++,
          author.getName(),
          author.getAffiliation()
        );
      }
    }
    System.out.println("--------------------------------");
  }

  public void displayKeywordsList(List<Keyword> keywords) {
    if (keywords.isEmpty()) {
      System.out.println("No keywords available.");
    } else {
      System.out.println("\nKeywords:");
      int index = 1;
      for (Keyword keyword : keywords) {
        System.out.printf("%d. %s\n", index++, keyword.getKeyword());
      }
    }
    System.out.println("--------------------------------");
  }

  public void displayEntityOptions(String entityType) {
    System.out.println("\nOptions for " + entityType + ":");
    System.out.println("1. Add new " + entityType);
    System.out.println("2. Edit an existing " + entityType);
    System.out.println("3. Delete an existing " + entityType);
    System.out.println("4. View details of an existing " + entityType);
    if (entityType.equals("Document")) {
      System.out.println("5. Search for documents by filter");
    }
    System.out.println("0. Go back to main menu");
    System.out.print("Select an option: ");
  }

  public void displayInvalidOption() {
    System.out.println("Invalid option. Please try again.");
  }

  public void displayMessage(String message) {
    System.out.println(message);
  }

  public String promptForKeyword() {
    System.out.print("Enter keyword: ");
    return scanner.nextLine();
  }

  public int promptForKeywordIndex() {
    System.out.print("Enter keyword number: ");
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.print("Please enter a valid number for keyword number: ");
    }
    return scanner.nextInt();
  }

  public void displayKeywordDetails(Keyword keyword) {
    System.out.println("\nKeyword Details:");
    System.out.printf(
      "ID: %d, Keyword: %s\n",
      keyword.getId(),
      keyword.getKeyword()
    );
  }

  public String promptForAuthorName() {
    System.out.print("Enter author's name: ");
    return scanner.nextLine();
  }

  public String promptForAuthorAffiliation() {
    System.out.print("Enter author's affiliation: ");
    return scanner.nextLine();
  }

  public int promptForAuthorIndex() {
    System.out.print("Enter author's index: ");
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.print("Please enter a valid number for author ID: ");
    }
    int index = scanner.nextInt();
    scanner.nextLine();
    return index;
  }

  public void displayAuthorDetails(Author author) {
    System.out.println("\nAuthor Details:");
    System.out.printf(
      "ID: %d, Name: %s, Affiliation: %s\n",
      author.getId(),
      author.getName(),
      author.getAffiliation()
    );
  }

  public String promptForDocumentTitle() {
    System.out.println("Enter document title: ");
    return scanner.nextLine();
  }

  public int promptForDocumentYear() {
    System.out.println("Enter publication year: ");
    int year = scanner.nextInt();
    scanner.nextLine();
    return year;
  }

  public DocumentType promptForDocumentType() {
    System.out.println("Select document type:");
    for (DocumentType type : DocumentType.values()) {
      System.out.println(type.ordinal() + ": " + type);
    }
    int choice = getOption();
    return DocumentType.values()[choice];
  }

  public int promptForDocumentIndex() {
    System.out.print("Enter document index: ");
    return getOption();
  }

  public void displayDocumentDetails(
    Document document,
    List<Author> authorList,
    List<Keyword> keywordList
  ) {
    System.out.println("\nDocument Details:");
    System.out.println("ID: " + document.getId());
    System.out.println("Title: " + document.getTitle());
    System.out.println("Year: " + document.getPublicationYear());
    System.out.println("Type: " + document.getDocumentType());

    // Displaying authors
    System.out.print("Authors: ");
    String authors = authorList
      .stream()
      .filter(author -> document.getAuthors().contains(author.getId()))
      .map(author -> author.getName())
      .collect(Collectors.joining(", "));
    System.out.println(authors.isEmpty() ? "No authors listed" : authors);

    // Displaying keywords
    System.out.print("Keywords: ");
    String keywords = keywordList
      .stream()
      .filter(keyword -> document.getKeywords().contains(keyword.getId()))
      .map(Keyword::getKeyword)
      .collect(Collectors.joining(", "));
    System.out.println(keywords.isEmpty() ? "No keywords listed" : keywords);
  }

  public Integer promptForNewOrExistingAuthor() {
    System.out.println(
      "1. Add new author\n2. Choose from existing authors\n3. Continue"
    );
    return getOption();
  }

  public List<Integer> promptForAuthors(List<Author> authors) {
    if (authors.isEmpty()) {
      System.out.println("No authors available. Please add authors first.");
      return new ArrayList<>();
    }
    this.displayAuthorsList(authors);
    System.out.println("Enter the index of authors (comma separated): ");
    String line = scanner.nextLine();
    return parseIndexes(line, authors.size())
      .stream()
      .map(index -> authors.get(index - 1).getId())
      .collect(Collectors.toList());
  }

  public List<Integer> promptForKeywords(List<Keyword> keywords) {
    if (keywords.isEmpty()) {
      System.out.println("No keywords available. Please add keywords first.");
      return new ArrayList<>();
    }
    this.displayKeywordsList(keywords);
    System.out.println("Enter the index of keywords (comma separated): ");
    String line = scanner.nextLine();
    return parseIndexes(line, keywords.size())
      .stream()
      .map(index -> keywords.get(index - 1).getId())
      .collect(Collectors.toList());
  }

  private List<Integer> parseIndexes(String line, int maxSize) {
    return Arrays
      .stream(line.split(","))
      .map(String::trim)
      .filter(str -> str.matches("\\d+") && Integer.parseInt(str) <= maxSize)
      .map(Integer::parseInt)
      .collect(Collectors.toList());
  }
}
