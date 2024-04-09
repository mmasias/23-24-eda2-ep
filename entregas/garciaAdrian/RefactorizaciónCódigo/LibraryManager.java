import java.util.ArrayList;
import java.util.Arrays;
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
            System.out.println("2. Listar Autores");
            System.out.println("3. Agregar Libro");
            System.out.println("4. Agregar Autor");
            System.out.println("5. Agregar Relación");
            System.out.println("6. Agregar Palabras Clave a Libro");
            System.out.println("7. Listar Libros por Palabras Clave");
            System.out.println("8. Salir");
            System.out.print("Ingrese su elección: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    if (books.isEmpty()) {
                        System.out.println("No hay libros para mostrar.");
                    } else {
                        listBooks();
                    }
                    break;
                case 2:
                    if (authors.isEmpty()) {
                        System.out.println("No hay autores para mostrar.");
                    } else {
                        listAuthors();
                    }
                    break;
                case 3:
                    addBook();
                    break;
                case 4:
                    addAuthor();
                    break;
                case 5:
                    addRelation();
                    break;
                case 6:
                    addKeywordsToBook();
                    break;
                case 7:
                    searchBooksByKeywords();
                    break;
                case 8:
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
            List<Author> bookAuthors = getAuthorsByBookId(book.getId());
            if (!bookAuthors.isEmpty()) {
                System.out.println("Autores:");
                for (Author author : bookAuthors) {
                    System.out.println("- " + author.getName());
                }
            } else {
                System.out.println("No se encontraron autores para este libro.");
            }
            if (book.getKeywords().length > 0) {
                System.out.println("Palabras Clave:");
                for (String keyword : book.getKeywords()) {
                    System.out.println("- " + keyword);
                }
            } else {
                System.out.println("No hay palabras clave para este libro.");
            }
            System.out.println();
        }
    }

    private void listAuthors() {
        System.out.println("Lista de Autores:");
        for (Author author : authors) {
            System.out.println(author);
        }
    }

    private void addBook() {
        System.out.print("Ingrese el ID del libro: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el título del libro: ");
        String title = scanner.nextLine();
        System.out.print("Ingrese el año de publicación del libro: ");
        int publicationYear = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el tipo de libro: ");
        String type = scanner.nextLine();

        Book newBook = new Book(id, title, publicationYear, type);
        books.add(newBook);
        System.out.println("Libro agregado correctamente.");
    }

    private void addAuthor() {
        System.out.print("Ingrese el ID del autor: ");
        int id = scanner.nextInt();
        scanner.nextLine(); 
        System.out.print("Ingrese el nombre del autor: ");
        String name = scanner.nextLine();

        Author newAuthor = new Author(id, name);
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

        BookAuthor relation = new BookAuthor(bookId, authorId);
        relations.add(relation);
        System.out.println("Relación libro-autor agregada correctamente.");
    }

    private void addKeywordsToBook() {
        System.out.print("Ingrese el ID del libro al que desea agregar palabras clave: ");
        int bookId = scanner.nextInt();
        scanner.nextLine(); 

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("Libro no encontrado.");
            return;
        }

        System.out.print("Ingrese las palabras clave separadas por coma: ");
        String inputKeywords = scanner.nextLine();
        String[] keywords = inputKeywords.split(",");
        book.setKeywords(keywords);
        System.out.println("Palabras clave agregadas correctamente al libro.");
    }

    private void searchBooksByKeywords() {
        System.out.print("Ingrese las palabras clave para buscar libros: ");
        String inputKeywords = scanner.nextLine();
        String[] keywords = inputKeywords.split(",");

        List<Book> matchedBooks = new ArrayList<>();
        for (Book book : books) {
            for (String keyword : keywords) {
                if (Arrays.asList(book.getKeywords()).contains(keyword.trim())) {
                    matchedBooks.add(book);
                    break;
                }
            }
        }

        if (matchedBooks.isEmpty()) {
            System.out.println("No se encontraron libros con las palabras clave proporcionadas.");
        } else {
            System.out.println("Libros encontrados:");
            for (Book book : matchedBooks) {
                System.out.println(book);
            }
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

    private List<Author> getAuthorsByBookId(int bookId) {
        List<Author> bookAuthors = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getBookId() == bookId) {
                Author author = findAuthorById(relation.getAuthorId());
                if (author != null) {
                    bookAuthors.add(author);
                }
            }
        }
        return bookAuthors;
    }

    private Author findAuthorById(int authorId) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.startLibraryManager();
    }
}


