package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {

    private List<Book> books;
    private List<Author> authors;
    private List<Keyword> keywords;
    private List<BookAuthor> relationsAuthor;
    private List<BookKeyword> relationsKeyword;
    private Scanner scanner;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.relationsAuthor = new ArrayList<>();
        this.relationsKeyword = new ArrayList<>();
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
            System.out.println("5. Listar libros por keyword");
            System.out.println("6. Listar libros por autor");
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
                    listKeywords();
                    break;
                case "5":
                    listBooksByKeywords();
                    break;
                case "6":
                    listBooksByAuthor();
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
                System.out.println(getKeywordsByBookId(book.getId()));
            }
        }
    }

    private void listBooksByKeywords() {
        System.out.println("Introduce el ID de la keyword:");
        listKeywords();
        try {
            int keywordId = Integer.parseInt(scanner.nextLine());
            listBooksByKeywords(keywordId);
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        }
    }

    private void listBooksByKeywords(int keywordId) {
        List<Book> booksByKeyword = getBooksByKeywordId(keywordId);
        if (booksByKeyword.isEmpty()) {
            System.out.println("> No hay libros asociados a esta keyword.");
        } else {
            for (Book book : booksByKeyword) {
                System.out.println(book);
                System.out.println(getAuthorsByBookId(book.getId()));
            }
        }
    }

    private void listBooksByAuthor() {
        System.out.println("Introduce el ID del autor:");
        listAuthors();
        try {
            int authorId = Integer.parseInt(scanner.nextLine());
            listBooksByAuthor(authorId);
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        }
    }

    private void listBooksByAuthor(int authorId) {
        List<Book> booksByAuthor = getBooksByAuthorId(authorId);
        if (booksByAuthor.isEmpty()) {
            System.out.println("> No hay libros asociados a este autor.");
        } else {
            for (Book book : booksByAuthor) {
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
        String responseAuthor = scanner.nextLine();
        if ("s".equalsIgnoreCase(responseAuthor)) {
            addAuthor(book);
        }
        System.out.println("¿Deseas añadir keywords a este libro? (s/n)");
        String responseKeywords = scanner.nextLine();
        if ("s".equalsIgnoreCase(responseKeywords)) {
            addKeyword(book);
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
                addRelationAuthor(book.getId(), newAuthor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    addRelationAuthor(book.getId(), authorId);
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

    private void addRelationAuthor(int bookId, int authorId) {
        relationsAuthor.add(new BookAuthor(bookId, authorId));
    }

    private void addKeyword(Book book) {
        boolean addingKeywords = true;
        while (addingKeywords) {
            System.out.println(
                    "Selecciona el ID de la keyword para asociar con el libro, o introduce 'nuevo' para añadir una nueva keyword:");
            listKeywords();
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre de la nueva keyword:");
                String keyword = scanner.nextLine();
                Keyword newKeyword = new Keyword(keywords.size() + 1, keyword);
                addKeyword(newKeyword);
                addRelationKeyword(book.getId(), newKeyword.getId());
                System.out.println("Keyword nuevo añadido y asociado al libro.");
            } else {
                try {
                    int keywordId = Integer.parseInt(input);
                    addRelationKeyword(book.getId(), keywordId);
                    System.out.println("Keyword asociado al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otra keyword a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                addingKeywords = !addingKeywords;
            }
        }
    }

    private void addKeyword(Keyword keyword) {
        keywords.add(keyword);
    }

    private void addRelationKeyword(int bookId, int keywordId) {
        relationsKeyword.add(new BookKeyword(bookId, keywordId));
    }

    private List<Author> getAuthorsByBookId(int bookId) {
        List<Author> result = new ArrayList<>();
        for (BookAuthor relation : relationsAuthor) {
            if (relation.getBookId() == bookId) {
                result.add(findAuthorById(relation.getAuthorId()));
            }
        }
        return result;
    }

    private List<Keyword> getKeywordsByBookId(int bookId) {
        List<Keyword> result = new ArrayList<>();
        for (BookKeyword relation : relationsKeyword) {
            if (relation.getBookId() == bookId) {
                result.add(findKeywordById(relation.getKeywordId()));
            }
        }
        return result;
    }

    private List<Book> getBooksByKeywordId(int keywordId) {
        List<Book> result = new ArrayList<>();
        for (BookKeyword relation : relationsKeyword) {
            if (relation.getKeywordId() == keywordId) {
                result.add(findBookById(relation.getBookId()));
            }
        }
        return result;
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        List<Book> result = new ArrayList<>();
        for (BookAuthor relation : relationsAuthor) {
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

    private Keyword findKeywordById(int keywordId) {
        for (Keyword keyword : keywords) {
            if (keyword.getId() == keywordId) {
                return keyword;
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

    private void listKeywords() {
        if (keywords.isEmpty()) {
            System.out.println("> No hay keywords disponibles.");
        } else {
            for (Keyword author : keywords) {
                System.out.println("ID: " + author.getId() + ", Keyword: " + author.getKeyword());
            }
        }
    }

}