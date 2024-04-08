package v003;

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
        System.out.println("Bienvenido al Gestor de Biblioteca");
        boolean isWorking = true;
        while (isWorking) {
            System.out.println("Elige una opción:");
            System.out.println("1. Agregar un nuevo libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Listar autores");
            System.out.println("9. Salir");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    listBooks();
                    break;
                case "3":
                    listAuthors();
                    break;
                case "9":
                    System.out.println("Saliendo del gestor de biblioteca...");
                    isWorking = !isWorking;
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
                    break;
            }
        }
    }

    private void listBooks() {
        System.out.println("Libros en la biblioteca:");
        if (books.isEmpty()) {
            System.out.println("> No hay libros en la biblioteca.");
        } else {
            for (Book book : books) {
                System.out.println(book);
                System.out.println(getAuthorsByBookId(book.getId()));
            }
        }
    }

    private void addBook() {
        System.out.println("Introduce el título del libro:");
        String title = scanner.nextLine();
        System.out.println("Introduce el año de publicación:");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el tipo de libro:");
        String type = scanner.nextLine();

        Book newBook = new Book(books.size() + 1, title, year, type);
        addBook(newBook);
    }

    private void addBook(Book book) {
        books.add(book);
        System.out.println("Libro añadido. ¿Deseas añadir autores a este libro? (s/n)");
        String response = scanner.nextLine();
        if ("s".equalsIgnoreCase(response)) {
            addAuthor(book);
        }
    }

    private void addAuthor(Book book) {
        boolean addingAuthors = true;
        while (addingAuthors) {
            System.out.println(
                    "Selecciona el ID del autor para asociar con el libro, o introduce 'nuevo' para añadir un nuevo autor:");
            listAuthors();
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre del nuevo autor:");
                String name = scanner.nextLine();
                Author newAuthor = new Author(authors.size() + 1, name);
                addAuthor(newAuthor);
                addRelation(book.getId(), newAuthor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    addRelation(book.getId(), authorId);
                    System.out.println("Autor asociado al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otro autor a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                addingAuthors = !addingAuthors;
            }
        }
    }

    private void addAuthor(Author author) {
        authors.add(author);
    }

    private void addRelation(int bookId, int authorId) {
        relations.add(new BookAuthor(bookId, authorId));
    }

    private List<Author> getAuthorsByBookId(int bookId) {
        List<Author> result = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getBookId() == bookId) {
                result.add(findAuthorById(relation.getAuthorId()));
            }
        }
        return result;
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        List<Book> result = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getAuthorId() == authorId) {
                result.add(findBookById(relation.getBookId()));
            }
        }
        return result;
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

    private void listAuthors() {
        if (authors.isEmpty()) {
            System.out.println("> No hay autores disponibles.");
        } else {
            for (Author author : authors) {
                System.out.println("ID: " + author.getId() + ", Autor: " + author.getName());
            }
        }
    }

}
