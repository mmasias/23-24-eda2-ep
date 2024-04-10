package antonioGavilanes.reto003;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    
    private List<Book> books;
    private List<Author> authors;
    private List<BookAuthor> relations;
    private Scanner scanner;

    public LibraryManager() {
        books = new ArrayList<>();
        authors = new ArrayList<>();
        relations = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void startLibraryManager() {
    }

    private void listBooks() {
    }

    private void addBook() {
    }

    private void addBook(Book book) {
    }

    private void addAuthor(Book book) {
    }

    private void addAuthor(Author author) {
    }

    private void addRelation(int bookId, int authorId) {
    }

    private List<Author> getAuthorsByBookId(int bookId) {
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        return null;
    }

    private Book findBookById(int bookId) {
        return null;
    }

    private Author findAuthorById(int authorId) {
        return null;
    }

    private void listAuthors() {
    }
}