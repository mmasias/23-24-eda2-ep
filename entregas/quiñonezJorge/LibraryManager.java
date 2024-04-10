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
      manageInput(input.nextInt());
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

  private void manageInput(int option) {
    switch (option) {
      case 1:
        addDocument();
        break;
      case 4:
        addAuthors();
        break;
      case 7:
        listAllAuthors();
        break;
      case 8:
        listAllDocuments();
        break;
      case 9:
        filterDocuments();
        break;
      case 0:
        running = !running;
        break;
      default:
        break;
    }
  }

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
    System.out.print("How many authors will you add?: ");
    int amount = input.nextInt();
    for (int i = 0; i < amount; i++) {
      Author created = generator.createAuthor(authors.size() + 1);
      list.add(created);
      authors.add(created);
    }
    return list;
  }

  private void listAllAuthors() {
    System.out.println();
    for (Author author : authors) {
      System.out.println(author.toString());
    }
    new Scanner(System.in).nextLine();
  }

  private void listAllDocuments() {
    for (Document doc : documents) {
      System.out.println(doc.toString());
    }
    new Scanner(System.in).nextLine();
  }

  private void filterDocuments() {

    for (Relation rel : relations) {
      System.out.println(rel.getAuthorId() + "," + rel.getDocumentId());
    }
    new Scanner(System.in).nextLine();
    System.out.println();
    System.out.println("1. Filter documents by author id");
    System.out.println("2. Filter authors by document id");
    System.out.println("0. Exit");
    System.out.print("Choose an option: ");
    int op = input.nextInt();

    switch (op) {
      case 1:
        filterByAuthorId();
        break;
      case 2:
        filterByDocumentId();
        break;
      case 0:
        return;
      default:
        break;
    }
  }

  private void filterByAuthorId() {
    listAllAuthors();
    System.out.println("Select the author id: ");
    int index = input.nextInt();

    System.out.println(authors.get(index - 1).toString());
    System.out.println("Documents:");
    for (Relation rel : relations) {
      if (rel.getAuthorId() == index) {
        System.out.println(documents.get(rel.getDocumentId() - 1).toString());
      }
    }
    new Scanner(System.in).nextLine();
  }

  private void filterByDocumentId() {
    listAllDocuments();
    System.out.print("Select the document id: ");
    int index = input.nextInt();
    System.out.println(documents.get(index - 1).toString());
    System.out.println("Authors:");
    for (Relation rel : relations) {
      if (rel.getDocumentId() == index) {
        System.out.println(authors.get(rel.getAuthorId() - 1).toString());
      }
    }
    new Scanner(System.in).nextLine();
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
