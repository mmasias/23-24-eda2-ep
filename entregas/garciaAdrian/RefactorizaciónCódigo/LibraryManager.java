import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Book> books;
    private List<Author> authors;
    private List<BookAuthor> relations;
    private Scanner scanner;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void startLibraryManager() {
        while (true) {
            System.out.println("1. Listar Libros");
            System.out.println("2. Agregar Libro");
            System.out.println("3. Agregar Autor");
            System.out.println("4. Agregar Relación");
            System.out.println("5. Salir");
            System.out.print("Ingrese su elección: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    listBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    addAuthor();
                    break;
                case 4:
                    addRelation();
                    break;
                case 5:
                    System.out.println("Saliendo del Administrador de Biblioteca.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción inválida. Por favor, intente de nuevo.");
            }
        }
    }

    private void listBooks() {
        System.out.println("Lista de Libros:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addBook() {
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el año de publicación: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el tipo de libro: ");
        String type = scanner.nextLine();

        Book newBook = new Book(books.size() + 1, title, publicationYear, type);
        books.add(newBook);
        System.out.println("Libro agregado correctamente.");
    }

    private void addAuthor() {
        System.out.print("Ingrese el nombre del autor: ");
        String name = scanner.nextLine();

        Author newAuthor = new Author(authors.size() + 1, name);
        authors.add(newAuthor);
        System.out.println("Autor agregado correctamente.");
    }

    private void addRelation() {
        System.out.print("Ingrese el ID del libro: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el ID del autor: ");
        int authorId = scanner.nextInt();
        scanner.nextLine();

        Book book = findBookById(bookId);
        Author author = findAuthorById(authorId);

        if (book != null && author != null) {
            BookAuthor newRelation = new BookAuthor(bookId, authorId);
            relations.add(newRelation);
            System.out.println("Relación agregada correctamente.");
        } else {
            System.out.println("Libro o autor no encontrado.");
        }
    }

    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    private Author findAuthorById(int authorId) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }
}
