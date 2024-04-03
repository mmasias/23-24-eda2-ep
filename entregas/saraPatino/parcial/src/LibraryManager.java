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
    private List<BookLanguage> relationsLanguage;
    private List<Language> languages;
    private Scanner scanner;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.keywords = new ArrayList<>();
        this.relationsAuthor = new ArrayList<>();
        this.relationsKeyword = new ArrayList<>();
        this.relationsLanguage = new ArrayList<>();
        this.languages = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void startLibraryManager() {
        System.out.println("Bienvenido al Gestor de Biblioteca");
        boolean isWorking = true;

        addDefaulAuthors();
        addDefaultKeywords();
        addDefaulLanguages();

        while (isWorking) {
            System.out.println("Elige una opción:");
            System.out.println("1. Agregar un nuevo libro");
            System.out.println("2. Listar libros");
            System.out.println("3. Listar autores");
            System.out.println("5. Listar libros por keyword");
            System.out.println("6. Listar libros por autor");
            System.out.println("7. Listar libros por lenguaje");
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
                case "7":
                    listBooksByLanguages();
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
                System.out.println(getLanguagesByBookId(book.getId()));
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
                System.out.println(getKeywordsByBookId(book.getId()));
            }
        }
    }

    private void listBooksByLanguages() {
        System.out.println("Introduce el ID del lenguage:");
        listLanguages();
        try {
            int languageId = Integer.parseInt(scanner.nextLine());
            listBooksByLanguages(languageId);
        } catch (NumberFormatException e) {
            System.out.println("Entrada no válida.");
        }
    }

    private void listBooksByLanguages(int languageId) {
        List<Book> booksByLanguage = getBooksByLanguageId(languageId);
        if (booksByLanguage.isEmpty()) {
            System.out.println("> No hay libros asociados a este lenguaje.");
        } else {
            for (Book book : booksByLanguage) {
                System.out.println(book);
                System.out.println(getLanguagesByBookId(book.getId()));
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
        System.out.println("¿Deseas añadir lenguajes a este libro? (s/n)");
        String responseLanguages = scanner.nextLine();
        if ("s".equalsIgnoreCase(responseLanguages)) {
            addLanguage(book);
        }
    }

    private void addDefaulAuthors() {
        Author author1 = new Author(authors.size() + 1 , "J.K Rowling");
        authors.add(author1);
        Author author2 = new Author(authors.size() + 1 , "Sarah J Maas");
        authors.add(author2);
        Author author3 = new Author(authors.size() + 1 , "Louisa May Alcott");
        authors.add(author3);
        Author author4 = new Author(authors.size() + 1 , "Jane Austen");
        authors.add(author4);
        Author author5 = new Author(authors.size() + 1 , "Isaac Asimov");
        authors.add(author5);
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

    private void addDefaultKeywords() {
        Keyword keyword1 = new Keyword(keywords.size() + 1 , "Fantasy");
        keywords.add(keyword1);
        Keyword keyword2 = new Keyword(keywords.size() + 1 , "Romance");
        keywords.add(keyword2);
        Keyword keyword3 = new Keyword(keywords.size() + 1 , "Science Fiction");
        keywords.add(keyword3);
        Keyword keyword4 = new Keyword(keywords.size() + 1 , "Clasic");
        keywords.add(keyword4);
        Keyword keyword5 = new Keyword(keywords.size() + 1 , "Mystery");
        keywords.add(keyword5);
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

    private void addDefaulLanguages() {
        Language english = new Language(languages.size() + 1 , "English");
        languages.add(english);
        Language espanol = new Language(languages.size() + 1 , "Espanol");
        languages.add(espanol);
    }

    private void addLanguage(Book book) {
        boolean addingLanguages = true;
    

        while (addingLanguages) {
            System.out.println(
                    "Selecciona el ID del lenguaje para asociar con el libro, o introduce 'nuevo' para añadir un nuevo lenguaje:");
            listLanguages();
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre de el lenguaje que deseas agregar:");
                String language = scanner.nextLine();
                Language newLanguage = new Language(languages.size() + 1, language);
                addLanguage(newLanguage);
                addRelationLanguage(book.getId(), newLanguage.getId());
                System.out.println("Language nuevo añadido y asociado al libro.");
            } else {
                try {
                    int languageId = Integer.parseInt(input);
                    addRelationLanguage(book.getId(), languageId);
                    System.out.println("Lenguaje asociado al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otro lenguaje a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                addingLanguages = !addingLanguages;
            }
        }
    }

    private void addLanguage(Language language) {
        languages.add(language);
    }

    private void addRelationLanguage(int bookId, int languageId) {
        relationsLanguage.add(new BookLanguage(bookId, languageId));
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

    private List<Language> getLanguagesByBookId(int bookId) {
        List<Language> result = new ArrayList<>();
        for (BookLanguage relation : relationsLanguage) {
            if (relation.getBookId() == bookId) {
                result.add(findLanguageById(relation.getLanguageId()));
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

    private List<Book> getBooksByLanguageId(int languageId) {
        List<Book> result = new ArrayList<>();
        for (BookLanguage relation : relationsLanguage) {
            if (relation.getLanguageId() == languageId) {
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

    private Language findLanguageById(int languageId) {
        for (Language language : languages) {
            if (language.getId() == languageId) {
                return language;
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
            for (Keyword keyword : keywords) {
                System.out.println("ID: " + keyword.getId() + ", Keyword: " + keyword.getKeyword());
            }
        }
    }

    private void listLanguages() {
        if (languages.isEmpty()) {
            System.out.println("> No hay languages disponibles.");
        } else {
            for (Language language : languages) {
                System.out.println("ID: " + language.getId() + ", Language: " + language.getLanguage());
            }
        }
    }

}