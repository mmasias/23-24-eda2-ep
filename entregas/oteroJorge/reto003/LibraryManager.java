package entregas.oteroJorge.reto003;

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

    private void listBooks() {
        System.out.println("Books:");
        if (books.isEmpty()) {
            System.out.println("No books available");
        } else {
            for (Book book : books) {
                System.out.println(getAuthorsByBookId(book.getId()));
            }
        }
    }

    private void addBook() {
        System.out.println("Enter the title of the book:");
        String title = scanner.nextLine();
        System.out.println("Enter the publication year of the book:");
        int publicationYear = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter the type of the book:");
        String type = scanner.nextLine();
        Book book = new Book(books.size() + 1, title, publicationYear, type);
        books.add(book);
        System.out.println("Book added successfully");
    }

    private void addAuthorToBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully, would you like to add an author to the book? (y/n)");
        String response = scanner.nextLine();
        if (response.equals("y")) {
            addAuthorToBook(book);
        }
    }

    private void addAuthor(Book book) {
        boolean addingAuthors = true;
        while (addingAuthors) {
            System.out.println("Select an author to add to the book or type 'n' to add a new author:");
            listAuthors();
            String input = scanner.nextLine();
            if ("n".equalsIgnoreCase(input)) {
                addNewAuthor();
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    addRelation(book.getId(), authorId);
                    System.out.println("Relation added successfully");
                } catch (NumberFormatException e) {
                    System.out.println("Invalid author id");
                }
            }
            System.out.println("Would you like to add another author to the book? (y/n)");
            if (!"y".equalsIgnoreCase(scanner.nextLine())) {
                addingAuthors = !addingAuthors;
            }
        }
    }

    private void addNewAuthor() {
        System.out.println("Enter the name of the author:");
        String name = scanner.nextLine();
        Author newAuthor = new Author(authors.size() + 1, name);
        addAuthor(newAuthor);
        addRelation(book.getId(), newAuthor.getId());
        System.out.println("Relation added successfully");
    }

    private void addAuthor(Author author) {
        authors.add(author);
    }

    private void addRelation(int bookId, int authorId) {
        relations.add(new BookAuthor(bookId, authorId));
    }

    private List<Author> getAuthorsByBookId(int bookId) {
        List<Author> authorsByBookId = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getBookId() == bookId) {
                for (Author author : authors) {
                    if (author.getId() == relation.getAuthorId()) {
                        authorsByBookId.add(author);
                    }
                }
            }
        }
        return authorsByBookId;
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        List<Book> booksByAuthorId = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getAuthorId() == authorId) {
                for (Book book : books) {
                    if (book.getId() == relation.getBookId()) {
                        booksByAuthorId.add(book);
                    }
                }
            }
        }
        return booksByAuthorId;
    }

    private Book getBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
    
    private Author getAuthorById(int authorId) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }

    private void listAuthors() {
        System.out.println("Authors:");
        if (authors.isEmpty()) {
            System.out.println("No authors available");
        } else {
            for (Author author : authors) {
                System.out.println(author);
            }
        }
    }
    
}
