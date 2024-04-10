package entregas.quiñonezJorge;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
  private ArrayList<Author> authors;
  private ArrayList<Document> documents;
  private ArrayList<Relation> relations;
  private LibraryGenerator generator;
  private LibraryEditor editor;
  private boolean running;
  private Scanner input = new Scanner(System.in);

  public LibraryManager() {
    authors = new ArrayList<Author>();
    documents = new ArrayList<Document>();
    relations = new ArrayList<Relation>();
    generator = new LibraryGenerator();
    editor = new LibraryEditor();
  }

  public void start() {
    running = true;
    while (running) {
      showMenu();
      System.out.print("Choose an option: ");
      manageInput(input.nextLine());
    }
  }

  private void showMenu() {
    System.out.println();
    System.out.println("1. Add document");
    System.out.println("2. Edit document");
    System.out.println("3. Remove document");
    System.out.println("4. Add author");
    System.out.println("5. Edit author");
    System.out.println("6. Remove author");
    System.out.println("7. List authors");
    System.out.println("8. List documents");
    System.out.println("9. Filter documents");
    System.out.println("0. Exit");
    System.out.println();
  }

  private void manageInput(String option) {
    switch (option) {
      case "1":
        addDocument();
        break;
      case "4":
        addAuthors();
        break;
      // case "3":
      // removeDocument();
      // break;
      // case "4":
      // listDocuments();
      // break;
      // case "5":
      // filterDocuments();
      // break;
      case "0":
        running = !running;
        break;
      default:
        System.out.print("Invalid option");
        new Scanner(System.in).nextLine();
        break;
    }
  }

  // private void listAuthors() {
  // for (int i = 0; i <= authors.size(); i++) {
  // System.out.println(i + ". " + authors.get(i).getName());
  // }
  // }

  private void addDocument() {
    boolean creating = true;
    while (creating) {
      System.out.print("Do you want to create a document? (y/n): ");
      String option = input.nextLine();
      if (option.equals("n")) {
        creating = !creating;
      } else if (option.equals("y")) {
        Document createdDoc = generator.createDocument(documents.size() + 1);
        ArrayList<Author> createdAuthors = addAuthors();

        documents.add(createdDoc);

        for (Author author : createdAuthors) {
          relations.add(new Relation(author.getId(), createdDoc.getId()));
        }
      }
    }
  }

  private ArrayList<Author> addAuthors() {
    ArrayList<Author> list = new ArrayList<Author>();
    System.out.print("How many authors will you add?:");
    int amount = input.nextInt();
    for (int i = 0; i < amount; i++) {
      Author created = generator.createAuthor(authors.size() + 1);
      list.add(created);
      authors.add(created);
    }
    return list;
  }

  // private void editDocument() {
  // for (Document document : documents) {
  // System.out.println("Do you want to edit the document: " + document.getTitle()
  // + "? (y/n)");
  // Scanner input = new Scanner(System.in);
  // if (input.nextLine().equals("y")) {
  // document.edit();
  // }
  // }
  // }

  // private void removeDocument() {
  // for (Document document : documents) {
  // System.out.println("Do you want to remove the document: " +
  // document.getTitle() + "? (y/n)");
  // Scanner input = new Scanner(System.in);
  // if (input.nextLine().equals("y")) {
  // documents.remove(document);
  // }
  // }
  // }

  // private void listDocuments() {
  // System.out.println();
  // for (Document document : documents) {
  // System.out.println(document.toString() + "\n");
  // }
  // new Scanner(System.in).nextLine();
  // }

  // private void filterDocuments() {
  // Scanner input = new Scanner(System.in);
  // System.out
  // .println("You can search documents by title, author, release date, document
  // type, keywords (0 to cancel)");
  // System.out.print("Search: ");
  // String search = input.nextLine();
  // if (!search.equals("0")) {
  // System.out.println();
  // for (Document document : documents) {
  // if (document.toString().contains(search)) {
  // System.out.println(document.toString() + "\n");
  // }
  // }
  // new Scanner(System.in).nextLine();
  // }
  // }
}
