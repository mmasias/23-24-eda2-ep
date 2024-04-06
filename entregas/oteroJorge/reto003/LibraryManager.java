package entregas.oteroJorge.reto003;

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
        addTopicToBook(book);
    }

    private void addAuthorToBook(Book book) {
        System.out.println("Would you like to add an author to the book? (y/n)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            addAuthor(book);
        }
    }

    private void addTopicToBook(Book book) {
        System.out.println("Would you like to add a topic to the book? (y/n)");
        String response = scanner.nextLine();
        if (response.equalsIgnoreCase("y")) {
            addTopic(book);
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
                addAuthorRelation(book.getId(), authorId);
                System.out.println("Relation added successfully");
            } catch (NumberFormatException e) {
                System.out.println("Invalid author id");
            }
        }
    }

    private void addTopic(Book book) {
        System.out.println("Select a topic to add to the book or type 'n' to add a new topic:");
        listTopics();
        String input = scanner.nextLine();
        if ("n".equalsIgnoreCase(input)) {
            addNewTopic(book);
        } else {
            try {
                int topicId = Integer.parseInt(input);
                addTopicRelation(book.getId(), topicId);
                System.out.println("Relation added successfully");
            } catch (NumberFormatException e) {
                System.out.println("Invalid topic id");
            }
        }
    }

    private void addNewAuthor(Book book) {
        System.out.println("Enter the name of the author:");
        String name = scanner.nextLine();
        Author newAuthor = new Author(authors.size() + 1, name);
        authors.add(newAuthor);
        addAuthorRelation(book.getId(), newAuthor.getId());
        System.out.println("Relation added successfully");
    }

    private void addNewTopic(Book book) {
        System.out.println("Enter the name of the topic:");
        String name = scanner.nextLine();
        Topic newTopic = new Topic(topics.size() + 1, name);
        topics.add(newTopic);
        addTopicRelation(book.getId(), newTopic.getId());
        System.out.println("Relation added successfully");
    }

    private void addAuthorRelation(int bookId, int authorId) {
        bookAuthors.add(new BookAuthor(bookId, authorId));
    }

    private void addTopicRelation(int bookId, int topicId) {
        bookTopics.add(new BookTopic(bookId, topicId));
    }

    private String getAuthorsByBookId(int bookId) {
        StringBuilder authors = new StringBuilder();
        for (BookAuthor bookAuthor : bookAuthors) {
            if (bookAuthor.getBookId() == bookId) {
                for (Author author : this.authors) {
                    if (author.getId() == bookAuthor.getAuthorId()) {
                        authors.append(author.getName()).append(", ");
                    }
                }
            }
        }
        return authors.toString().substring(0, authors.length() - 2);
    }

    private void searchAuthor(){
        System.out.println("Enter author name:");
        String name = scanner.nextLine();
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(name)) {
                System.out.println("Author found: " + author.getId());
                return;
            }
        }
    }

    private void searchTopic(){
        System.out.println("Enter topic name:");
        String name = scanner.nextLine();
        for (Topic topic : topics) {
            if (topic.getName().equalsIgnoreCase(name)) {
                System.out.println("Topic found: " + topic.getId());
                return;
            }
        }
    }

    private void searchBook(){
        System.out.println("Enter book title:");
        String title = scanner.nextLine();
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                System.out.println("Book found: " + book.getId());
                return;
            }
        }
    }

    private void findBookByAuthor(){
        System.out.println("Enter author id:");
        int authorId = Integer.parseInt(scanner.nextLine());
        for (BookAuthor bookAuthor : bookAuthors) {
            if (bookAuthor.getAuthorId() == authorId) {
                for (Book book : books) {
                    if (book.getId() == bookAuthor.getBookId()) {
                        System.out.println("Book found: " + book.getTitle());
                    }
                }
            }
        }
    }

    private void findBookByTopic(){
        System.out.println("Enter topic id:");
        int topicId = Integer.parseInt(scanner.nextLine());
        for (BookTopic bookTopic : bookTopics) {
            if (bookTopic.getTopicId() == topicId) {
                for (Book book : books) {
                    if (book.getId() == bookTopic.getBookId()) {
                        System.out.println("Book found: " + book.getTitle());
                    }
                }
            }
        }
    }

    private void findAuthorByBook(){
        System.out.println("Enter book id:");
        int bookId = Integer.parseInt(scanner.nextLine());
        for (BookAuthor bookAuthor : bookAuthors) {
            if (bookAuthor.getBookId() == bookId) {
                for (Author author : authors) {
                    if (author.getId() == bookAuthor.getAuthorId()) {
                        System.out.println("Author found: " + author.getName());
                    }
                }
            }
        }
    }

    private void findTopicByBook(){
        System.out.println("Enter book id:");
        int bookId = Integer.parseInt(scanner.nextLine());
        for (BookTopic bookTopic : bookTopics) {
            if (bookTopic.getBookId() == bookId) {
                for (Topic topic : topics) {
                    if (topic.getId() == bookTopic.getTopicId()) {
                        System.out.println("Topic found: " + topic.getName());
                    }
                }
            }
        }
    }

    private void searcher(){
        boolean searching = true;
        while (searching) {
            System.out.println("Select an option:");
            System.out.println("1. Search author id");
            System.out.println("2. Search topic id");
            System.out.println("3. Search book id");
            System.out.println("4. Find book by author id");
            System.out.println("5. Find book by topic id");
            System.out.println("6. Find author by book id");
            System.out.println("7. Find topic by book id");
            System.out.println("F. Exit");
            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    searchAuthor();
                    break;
                case "2":
                    searchTopic();
                    break;
                case "3":
                    searchBook();
                    break;
                case "4":
                    findBookByAuthor();
                    break;
                case "5":
                    findBookByTopic();
                    break;
                case "6":
                    findAuthorByBook();
                    break;
                case "7":
                    findTopicByBook();
                    break;
                case "F":
                    searching = false;
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
            System.out.println();
        }
    }

    public void start(){
        boolean running = true;
        while (running) {
            System.out.println("Select an option:");
            System.out.println("1. Add book");
            System.out.println("2. List books");
            System.out.println("3. List authors");
            System.out.println("4. List topics");
            System.out.println("5. Add example");
            System.out.println("6. Searcher");
            System.out.println("F. Exit");
            String option = scanner.nextLine();
            switch (option) {
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
                    listTopics();
                    break;
                case "5":
                    addExampleBooks();
                    break;
                case "6":
                    searcher();
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
        System.out.println("End of the program");
    }
}
