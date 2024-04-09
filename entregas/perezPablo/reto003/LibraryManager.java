package reto003;

import java.util.*;

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
        System.out.println("Iniciando biblioteca");
        boolean start = true;
        while (start) {
            System.out.println("Elige una opción:");
            System.out.println("0. Salir");
            System.out.println("1. Listar libros");
            System.out.println("2. Añadir libro");
            System.out.println("3. Listar autores");
            
            String option = scanner.nextLine();

            switch (option) {
                case "0":
                    System.out.println("Saliendo de la biblioteca");
                    start = !start;
                    break;
                case "1":
                    listBooks();
                    break;
                case "2":
                    addBook();
                    break;
                case "3":
                    listAuthors();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

    private void listBooks() {
        if (books.isEmpty()) {
            System.out.println("No hay libros");
        } else {
            for (Book b : books) {
                System.out.println(b.toString());
            }
        }
    }

    private void addBook() {
        Book b = null;
        Author a = null;
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce título");
        String t = sc.nextLine();
        System.out.println("Introduce id del libro");
        int idB = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce año de publicación");
        int y = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce tipo de libro");
        String tp = sc.nextLine();
        b = new Book(idB, t, y, tp);
        System.out.println("Introduce nombre del autor");
        String n = sc.nextLine();
        System.out.println("Introduce id del autor");
        int idA = Integer.parseInt(sc.nextLine());
        a = new Author(idA, n);
        books.add(b);
        authors.add(a);
        relations.add(new BookAuthor(idB, idA));
    }

    private void addBook(Book book) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del autor del libro " + book.toString());
        String n = sc.nextLine();
        System.out.println("Introduce el id del autor");
        int id = Integer.parseInt(sc.nextLine());
        Author a = new Author(id, n);
        authors.add(a);
        books.add(book);
        relations.add(new BookAuthor(book.getId(), id));
        books.add(book);
    }

    private void addAuthor(Author author) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del titulo del libro " + author.toString());
        String t = sc.nextLine();
        System.out.println("Introduce el id del autor");
        int id = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el año de publicación");
        int y = Integer.parseInt(sc.nextLine());
        System.out.println("Introduce el tupo de libro");
        String tp = sc.nextLine();
        Book b = new Book(id, t, y, tp);
        authors.add(author);
        books.add(b);
        relations.add(new BookAuthor(id, author.getId()));
    }

    private void addRelation(int bookId, int authorId) {
        BookAuthor r = new BookAuthor(bookId, authorId);
        relations.add(r);
    }

    private List<Author> getAuthorsByBookId (int bookId) {
        List<Author> returnList = new ArrayList<>();
        for (BookAuthor r : relations ) {
            if (r.getBookId() == bookId) {
                returnList.add(findAuthorById(r.getAuthorId()));
            }
        }
        return returnList;
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        List<Book> returnList = new ArrayList<>();
        for (BookAuthor r : relations ) {
            if (r.getAuthorId() == authorId) {
                returnList.add(findBookById(r.getBookId()));
            }
        }
        return returnList;
    }

    private Book findBookById(int bookId) {
        for (Book b : books) {
            if (b.getId() == bookId) {
                return b;
            }
        }
        return null;
    }

    private Author findAuthorById(int authorId) {
        for (Author a : authors) {
            if (a.getId() == authorId) {
                return a;
            }
        }
        return null;
    }

    private void listAuthors() {
        if (authors.isEmpty()) {
            System.out.println("No hay autores");
        } else {
            for (Author a : authors) {
                System.out.println(a.toString());
            }
        }
    }
}
