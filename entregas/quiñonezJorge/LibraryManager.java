package entregas.quiñonezJorge;

import java.util.ArrayList;
import java.util.Scanner;

public class LibraryManager {
  private ArrayList<Author> authors;
  private ArrayList<Document> documents;
  private ArrayList<Relation> relations;
  private LibraryGenerator generator;
  private boolean running;
  private Scanner input = new Scanner(System.in);

  public LibraryManager() {
    authors = new ArrayList<Author>();
    documents = new ArrayList<Document>();
    relations = new ArrayList<Relation>();
    generator = new LibraryGenerator();
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
    System.out.println("1. Add author");
    System.out.println("2. Add document");
    System.out.println("3. List authors");
    System.out.println("4. List documents");
    System.out.println("5. Filter documents");
    System.out.println("0. Exit");
    System.out.println();
  }

  private void manageInput(int option) {
    switch (option) {
      case 1:
        addAuthors();
        break;
      case 2:
        addDocument();
        break;
      case 3:
        listAllAuthors();
        new Scanner(System.in).nextLine();
        break;
      case 4:
        listAllDocuments();
        new Scanner(System.in).nextLine();
        break;
      case 5:
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
      }
      if (option.equals("y")) {
        ArrayList<Author> createdAuthors = new ArrayList<Author>();

        Document createdDoc = generator.createDocument(documents.size() + 1);
        documents.add(createdDoc);

        System.out.println("Select the authors if they're on the list (-1 if not, 0 to finish): ");
        for (Author author : authors) {
          System.out.println(author.toString());
        }

        boolean adding = true;
        while (adding) {
          System.out.print("Select: ");
          int selection = input.nextInt();

          if (selection == 0) {
            adding = !adding;
          } else if (selection == -1) {
            createdAuthors = addAuthors();
          } else {
            createdAuthors.add(authors.get(selection - 1));
          }
        }

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
  }

  private void listAllDocuments() {
    for (Document doc : documents) {
      System.out.println(doc.toString());
    }
  }

  private void filterDocuments() {
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
}
