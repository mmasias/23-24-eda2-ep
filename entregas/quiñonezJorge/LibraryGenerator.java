package entregas.quiñonezJorge;

import java.util.Scanner;

public class LibraryGenerator {
  private Scanner input = new Scanner(System.in);

  public LibraryGenerator() {
  }

  public Document createDocument(int index) {
    System.out.print("Insert document title: ");
    String title = input.nextLine();
    System.out.print("Insert document type: ");
    String type = input.nextLine();
    System.out.print("Insert document releaseDate with the format xx/xx/xxx: ");
    String releaseDate = input.nextLine();
    return new Document(index, title, releaseDate, type, null);
  }

  public Author createAuthor(int index) {
    System.out.print("Insert new author's name: ");
    String name = input.nextLine();
    return new Author(index, name);
  }
}
