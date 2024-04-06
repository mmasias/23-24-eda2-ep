package entregas.oteroJorge.reto003;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {

    private List<Book> books;
    private List<Author> authors;
    private List<BookAuthor> bookAuthors;
    private List<Topic> topics;
    private List<BookTopic> bookTopics;
    private Scanner scanner;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.bookAuthors = new ArrayList<>();
        this.topics = new ArrayList<>();
        this.bookTopics = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    private void listBooks() {
        System.out.println(">Available books:");
        if (books.isEmpty()) {
            System.out.println(">>No books available");
        } else {
            for (Book book : books) {
                System.out.println(">>" + book.getTitle() + " by " + getAuthorsByBookId(book.getId()));
            }
        }
    }

    private void listAuthors() {
        System.out.println("Authors:");
        if (authors.isEmpty()) {
            System.out.println(">No authors available");
        } else {
            for (Author author : authors) {
                System.out.println(author.getId() + ". " + author.getName());
            }
        }
    }

    private void listTopics() {
        System.out.println("Topics:");
        if (topics.isEmpty()) {
            System.out.println(">No topics available");
        } else {
            for (Topic topic : topics) {
                System.out.println(topic.getId() + ". " + topic.getName());
            }
        }
    }



    private void addExampleBooks(){
        Book book1 = new Book(1, "The Lord of the Rings", 1954, "Fantasy");
        Book book2 = new Book(2, "Harry Potter and the Philosopher's Stone", 1997, "Fantasy");
        Book book3 = new Book(3, "1984", 1949, "Dystopian");
        Book book4 = new Book(4, "Animal Farm", 1945, "Dystopian");
        books.add(book1);
        books.add(book2);
        books.add(book3);
        books.add(book4);
        Author author1 = new Author(1, "J.R.R. Tolkien");
        Author author2 = new Author(2, "J.K. Rowling");
        Author author3 = new Author(3, "George Orwell");
        authors.add(author1);
        authors.add(author2);
        authors.add(author3);
        bookAuthors.add(new BookAuthor(1, 1));
        bookAuthors.add(new BookAuthor(2, 2));
        bookAuthors.add(new BookAuthor(3, 3));
        bookAuthors.add(new BookAuthor(4, 3));
        Topic topic1 = new Topic(1, "Fantasy");
        Topic topic2 = new Topic(2, "Dystopian");
        Topic topic3 = new Topic(3, "Science Fiction");
        topics.add(topic1);
        topics.add(topic2);
        topics.add(topic3);
        bookTopics.add(new BookTopic(1, 1));
        bookTopics.add(new BookTopic(2, 1));
        bookTopics.add(new BookTopic(3, 2));

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
        addAuthorToBook(book);
    }

    private void addAuthorToBook(Book book) {
        System.out.println("Would you like to add an author to the book? (y/n)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            addAuthor(book);
        }
    }

    private void addAuthor(Book book) {
        System.out.println("Select an author to add to the book or type 'n' to add a new author:");
        listAuthors();
        String input = scanner.nextLine();
        if ("n".equalsIgnoreCase(input)) {
            addNewAuthor(book);
        } else {
            try {
                int authorId = Integer.parseInt(input);
                addRelation(book.getId(), authorId);
                System.out.println("Relation added successfully");
            } catch (NumberFormatException e) {
                System.out.println("Invalid author id");
            }
        }
    }

    private void addNewAuthor(Book book) {
        System.out.println("Enter the name of the author:");
        String name = scanner.nextLine();
        Author newAuthor = new Author(authors.size() + 1, name);
        authors.add(newAuthor);
        addRelation(book.getId(), newAuthor.getId());
        System.out.println("Relation added successfully");
    }

    private void addRelation(int bookId, int authorId) {
        bookAuthors.add(new BookAuthor(bookId, authorId));
    }

    private String getAuthorsByBookId(int bookId) {
        StringBuilder authorsList = new StringBuilder();
        for (BookAuthor relation : bookAuthors) {
            if (relation.getBookId() == bookId) {
                Author author = findAuthorById(relation.getAuthorId());
                if (author != null) {
                    authorsList.append(author.getName()).append(", ");
                }
            }
        }
        if (authorsList.length() > 0) {
            authorsList.setLength(authorsList.length() - 2);
        }
        return authorsList.toString();
    }

    private Author findAuthorById(int authorId) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }

    public void start(){
        boolean running = true;
        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. List books");
            System.out.println("2. Add book");
            System.out.println("3. List authors");
            System.out.println("4. List topics");
            System.out.println("5. Add example");
            System.out.println("5. Find author by id");
            System.out.println("6. Find book by id");
            System.out.println("7. Find topic by id");
            System.out.println("F. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    listBooks();
                    break;
                case "2":
                    addBook();
                    break;
                case "3":
                    listAuthors();
                    break;
                case "4":
                    listTopics();
                    break;
                case "5":
                    addExampleBooks();
                    break;
                case "6":

                    break;
                case "7":

                    break;
                case "F":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println();
        }
        scanner.close();
    }
}
