package Entrega_Parcial;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    
private ArrayList<Book> books;
private ArrayList<Author> authors;
private ArrayList<BookAuthor> relations;
private Scanner scanner;

public LibraryManager(){
    this.books = new ArrayList<>();
    this.authors = new ArrayList<>();
    this.relations = new ArrayList<>();
    this.scanner = new Scanner(System.in);
}

public void UseLibraryManager(){
    System.out.println("Bienvenido a la biblioteca Virtual");
    boolean exit = false;
    while(!exit){
        System.out.println("1. Agregar libro");
        System.out.println("2. Agregar autor");
        System.out.println("3. Asignar autor a libro");
        System.out.println("4. Listar libros");
        System.out.println("5. Listar autores");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opcion: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch(option){
            case 1:
                addBook();
                break;
            case 2:
                addAuthor();
                break;
            case 3:
                assignAuthorToBook();
                break;
            case 4:
                listBooks();
                break;
            case 5:
                listAuthors();
                break;
            case 6:
                exit = true;
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}

private void addBook(){
    System.out.println("Ingrese el titulo del libro: ");
    String title = scanner.nextLine();
    System.out.println("Ingrese el año de publicacion: ");
    int year = scanner.nextInt();
    System.out.println("Ingrese el tipo de libro: ");
    String type = scanner.nextLine();
    Book book = new Book(books.size() + 1,title, year, type);
    books.add(book);
    System.out.println("Libro agregado con exito");
}

private void addAuthor(){
    System.out.println("Ingrese el nombre del autor: ");
    String name = scanner.nextLine();
    Author author = new Author(name, authors.size() + 1);
    authors.add(author);
    System.out.println("Autor agregado con exito");
}

}
