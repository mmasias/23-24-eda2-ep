package Views;

import Models.Author;
import Models.Document;
import Models.Keyword;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import Enums.DocumentType;

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
    return scanner.nextInt();
  }

  public void displayDocumentsList(List<Document> documents) {
    if (documents.isEmpty()) {
      System.out.println("No documents available.");
    } else {
      System.out.println("\nDocuments:");
      for (Document document : documents) {
        System.out.printf(
          "%d: %s (Year: %d)\n",
          document.getId(),
          document.getTitle(),
          document.getPublicationYear()
        );
      }
    }
  }

  public void displayAuthorsList(List<Author> authors) {
    if (authors.isEmpty()) {
      System.out.println("No authors available.");
    } else {
      System.out.println("\nAuthors:");
      for (Author author : authors) {
        System.out.printf(
          "%d: %s (%s)\n",
          author.getId(),
          author.getName(),
          author.getAffiliation()
        );
      }
    }
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
}


  public void displayEntityOptions(String entityType) {
    System.out.println("\nOptions for " + entityType + ":");
    System.out.println("1. Add new " + entityType);
    System.out.println("2. Edit an existing " + entityType);
    System.out.println("3. Delete an existing " + entityType);
    System.out.println("4. View details of an existing " + entityType);
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
    scanner.nextLine(); // Consume any leftover newline character
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

  public int promptForAuthorId() {
    System.out.print("Enter author's ID: ");
    while (!scanner.hasNextInt()) {
      scanner.next();
      System.out.print("Please enter a valid number for author ID: ");
    }
    return scanner.nextInt();
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

  public int promptForDocumentId() {
    System.out.print("Enter document ID: ");
    return getOption(); // Assumes that getOption() handles invalid inputs
  }

  public void displayDocumentDetails(Document document) {
    System.out.println("\nDocument Details:");
    System.out.println("ID: " + document.getId());
    System.out.println("Title: " + document.getTitle());
    System.out.println("Year: " + document.getPublicationYear());
    System.out.println("Type: " + document.getDocumentType());
    // Display authors and keywords based on their IDs
  }

  public List<Integer> promptForAuthors(List<Author> authors) {
    if (authors.isEmpty()) {
      System.out.println("No authors available. Please add authors first.");
      return new ArrayList<>();
    }
    displayAuthorsList(authors);
    System.out.println("Enter the index of authors (comma separated): ");
    String line = scanner.nextLine();
    return parseIndexes(line, authors.size());
  }

  public List<Integer> promptForKeywords(List<Keyword> keywords) {
    if (keywords.isEmpty()) {
      System.out.println("No keywords available. Please add keywords first.");
      return new ArrayList<>();
    }
    displayKeywordsList(keywords);
    System.out.println("Enter the index of keywords (comma separated): ");
    String line = scanner.nextLine();
    return parseIndexes(line, keywords.size());
  }

  private List<Integer> parseIndexes(String input, int size) {
    List<Integer> indexes = new ArrayList<>();
    String[] parts = input.split(",");
    for (String part : parts) {
      try {
        int index = Integer.parseInt(part.trim());
        if (index >= 1 && index <= size) {
          indexes.add(index - 1);
        } else {
          System.out.println("Invalid index: " + index + ". Skipping.");
        }
      } catch (NumberFormatException e) {
        System.out.println("Invalid input: " + part + ". Skipping.");
      }
    }
    return indexes;
  }
}
