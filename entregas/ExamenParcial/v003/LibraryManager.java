package v003;

import javax.swing.text.Document;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {

    private List<Book> books;
    private List<Author> authors;
    private List<BookAuthor> authorrelations;
    private List<KeyWord> keywords;
    private List<BookKeyWord> keywordrelations;
    private Scanner scanner;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.authorrelations = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.keywordrelations = new ArrayList<>();
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
            System.out.println("4. Listar palabras clave");
            System.out.println("5. Buscar");
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
                case "4":
                    listKeyWords();
                    break;
                case "5":
                    searchBooks();
                    break;
                case "-1":
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
                System.out.println(getKeyWordsByBookId(book.getId()));
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
        System.out.println("¿Deseas añadir palabras clave a este libro? (s/n)");
        response = scanner.nextLine();
        if ("s".equalsIgnoreCase(response)) {
            addKeyWord(book);
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
                addAuthorRelation(book.getId(), newAuthor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    addAuthorRelation(book.getId(), authorId);
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

    private void addAuthorRelation(int bookId, int authorId) {
        authorrelations.add(new BookAuthor(bookId, authorId));
    }

    private List<Author> getAuthorsByBookId(int bookId) {
        List<Author> result = new ArrayList<>();
        for (BookAuthor relation : authorrelations) {
            if (relation.getBookId() == bookId) {
                result.add(findAuthorById(relation.getAuthorId()));
            }
        }
        return result;
    }

    private void addKeyWord(Book book) {
        boolean addingKeywords = true;
        while (addingKeywords) {
            System.out.println(
                    "Selecciona el ID de la palabra clave para asociar con el libro, o introduce 'nuevo' para añadir una nueva palabra clave:");
            listKeyWords();
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre de la nueva palabra clave:");
                String name = scanner.nextLine();
                KeyWord newKeyWord = new KeyWord(keywords.size() + 1, name);
                addKeyWord(newKeyWord);
                addKeyWordRelation(book.getId(), newKeyWord.getId());
                System.out.println("Palabra clave nueva añadida y asociada al libro.");
            } else {
                try {
                    int keywordId = Integer.parseInt(input);
                    addKeyWordRelation(book.getId(), keywordId);
                    System.out.println("Palabra clave asociada al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otra palabra clave a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                addingKeywords = !addingKeywords;
            }
        }
    }

    public void addKeyWord(KeyWord keyword) {
        keywords.add(keyword);
    }

    private void addKeyWordRelation(int bookId, int keywordId) {
        keywordrelations.add(new BookKeyWord(bookId, keywordId));
    }

    private List<KeyWord> getKeyWordsByBookId(int bookId) {
        List<KeyWord> result = new ArrayList<>();
        for (BookKeyWord relation : keywordrelations) {
            if (relation.getBookId() == bookId) {
                result.add(findKeyWordById(relation.getKeyWordId()));
            }
        }
        return result;
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        List<Book> result = new ArrayList<>();
        for (BookAuthor relation : authorrelations) {
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

    private void listKeyWords() {
        if (keywords.isEmpty()) {
            System.out.println("> No hay palabras clave disponibles.");
        } else {
            for (KeyWord keyword : keywords) {
                System.out.println("ID: " + keyword.getId() + ", Palabra clave: " + keyword.getName());
            }
        }
    }

    private KeyWord findKeyWordById(int keyword) {
        for (KeyWord keyWord : keywords) {
            if (keyWord.getId() == keyword) {
                return keyWord;
            }
        }
        return null;
    }

    private void searchBooks() {
        System.out.println("Elige el criterio de búsqueda:");
        System.out.println("1. Título");
        System.out.println("2. Año de publicación");
        System.out.println("3. Autor");
        System.out.println("4. Tipo de documento");
        System.out.println("5. Palabras clave");

        String choice = scanner.nextLine();

        switch (choice) {
            case "1":
                searchByTitle();
                break;
            case "2":
                searchByPublicationYear();
                break;
            case "3":
                searchByAuthor();
                break;
            case "4":
                searchByType();
                break;
            case "5":
                searchByKeyWords();
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void searchByTitle() {
        System.out.println("Introduce el título del libro que deseas buscar:");
        String title = scanner.nextLine();
        LinkedList<Book> foundBooks = new LinkedList<>();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                foundBooks.add(book);
            }
        }

        displaySearchResults(foundBooks);
    }

    private void searchByPublicationYear() {
        System.out.println("Introduce el año de publicación:");
        int year = scanner.nextInt();
        scanner.nextLine();

        LinkedList<Book> foundBooks = new LinkedList<>();
        for (Book book : books) {
            if (book.getPublicationYear() == year) {
                foundBooks.add(book);
            }
        }

        displaySearchResults(foundBooks);
    }

    private void searchByAuthor() {
        System.out.println("Introduce el nombre del autor:");
        String authorName = scanner.nextLine();

        LinkedList<Book> foundBooks = new LinkedList<>();
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(authorName)) {
                foundBooks.addAll(getBooksByAuthorId(author.getId()));
            }
        }

        displaySearchResults(foundBooks);
    }

    private void searchByType() {
        System.out.println("Introduce el tipo de documento:");
        String type = scanner.nextLine();

        LinkedList<Book> foundBooks = new LinkedList<>();
        for (Book book : books) {
            if (book.getType().equalsIgnoreCase(type)) {
                foundBooks.add(book);
            }
        }

        displaySearchResults(foundBooks);
    }

    private void searchByKeyWords() {
        System.out.println("Introduce la palabra clave:");
        String keyword = scanner.nextLine();

        LinkedList<Book> foundBooks = new LinkedList<>();
        for (KeyWord keyWord : keywords) {
            if (keyWord.getName().equalsIgnoreCase(keyword)) {
                for (BookKeyWord relation : keywordrelations) {
                    if (relation.getKeyWordId() == keyWord.getId()) {
                        foundBooks.add(findBookById(relation.getBookId()));
                    }
                }
            }
        }

        displaySearchResults(foundBooks);
    }

    private void displaySearchResults(LinkedList<Book> foundBooks) {
        if (foundBooks.isEmpty()) {
            System.out.println("No se encontraron libros que coincidan con la búsqueda.");
        } else {
            System.out.println("Libros encontrados:");
            for (Book book : foundBooks) {
                System.out.println(book);
                System.out.println(getAuthorsByBookId(book.getId()));
                System.out.println(getKeyWordsByBookId(book.getId()));
            }
        }
    }

}
