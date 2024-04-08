import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Book> documents;
    private List<Author> authors;
    private Book selectedDocument = null;
    private static final Scanner scanner = new Scanner(System.in);


    public LibraryManager() {
        this.documents = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n ~~~| Library Management System |~~~");
            System.out.println("  1. Add Document");
            System.out.println("  2. Delete Document");
            System.out.println("  3. Update Document");
            System.out.println("  4. Search Documents");
            System.out.println("  5. Detail Selected Document");
            System.out.println("  6. Deselect Document");
            System.out.println("  7. List Documents by Author");
            System.out.println("  8. List Authors by Documents");
            System.out.println("  0. Exit");

            if (selectedDocument != null){
                System.out.println("\nSelected Document: " + selectedDocument.getTitle());
            } else System.out.println("\nNothing selected");
            String choice = getInput(" Choose an option: ");

            switch (choice) {
                case "1":
                    addBook();
                    break;
                case "2":
                    removeBook();
                    break;
                case "3":
                    updateBok();
                    break;
                case "4":
                    searchBook();
                    break;
                case "5":
                    showSelected();
                    break;
                case "6":
                    deselect();
                    break;
                case "7":
                    listBooks();
                    break;
                case "8":
                    listAuthors();
                    break;
                case "0":
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option.");
                    break;
            }
        }
    }

    private void addBook() {
        String title = getInput("Enter document title: ");
        
        String authorsStr = getInput("Enter authors (comma-separated): ");
        List<String> authors = new ArrayList<>(Arrays.asList(authorsStr.split("\\s*,\\s*")));

        int year = Integer.parseInt(getInput("Enter publishing year: "));

        String type = getInput("Enter document type: ");

        String keywordsStr = getInput("Enter keywords (comma-separated): ");
        List<String> keywords = new ArrayList<>(Arrays.asList(keywordsStr.split("\\s*,\\s*")));

        Book newDocument = new Book(title, year, type, keywords);
        
        addDocument(title, authors, year, type, keywords);
        System.out.println("Document added successfully.");

        selectedDocument = newDocument;
    }

    private void removeBook() {
        String title;
        if (selectedDocument != null){
            title = selectedDocument.getTitle();
        } else{
            title = getInput("Enter the title of the document to delete: ");
        } 
        deleteDocument(title);
        System.out.println("Document deleted.");
    }

    private void updateBok() {
        String title;
        if (selectedDocument != null){
            title = selectedDocument.getTitle();
        } else{
            title = getInput("Enter the title of the document you want to update: ");
        } 

        Book existingDocument = searchDocByTitle(title);
        if (existingDocument == null) {
            System.out.println("Document not found.");
            return;
        }

        String newTitle = getInput("Enter new document title (or press Enter to keep '" + existingDocument.getTitle() + "''): ");
        if (newTitle.isEmpty()) {
            newTitle = existingDocument.getTitle();
        }

        String modifyAuthors = getInput("Do you want to modify authors? (yes/no): ");
        if ("yes".equalsIgnoreCase(modifyAuthors)) {
            System.out.println("Current document authors: " + String.join(", ", existingDocument.getAuthorNames()));
            while (true) {
                String authorModification = getInput("Add or remove authors (+ AuthorName / - AuthorName). Type 'done' to finish: ");
                if ("done".equalsIgnoreCase(authorModification)) {
                    break;
                } else if (authorModification.startsWith("+")) {
                    String authorName = authorModification.substring(2);
                    Author author = searchAuthorByName(authorName);
                    if (author != null){
                        existingDocument.addAuthor(author);
                    } else {
                        existingDocument.addAuthor(new Author(authorName));
                    }
                } else if (authorModification.startsWith("-")) {
                    String authorName = authorModification.substring(2);
                    Author author = searchAuthorByName(authorName);
                    if (author != null){
                        existingDocument.removeAuthor(author);
                    }
                }
            }
        }

        String yearStr = getInput("Enter new publishing year (or press Enter to keep '" + existingDocument.getPublishingYear() + "''): ");
        int year = yearStr.isEmpty() ? existingDocument.getPublishingYear() : Integer.parseInt(yearStr);

        String type = getInput("Enter new document type (or press Enter to keep '" + existingDocument.getType() + "''): ");
        if (type.isEmpty()) {
            type = existingDocument.getType();
        }

        String keywordsStr = getInput("Enter new keywords (comma-separated, or press Enter to keep existing): ");
        List<String> keywords = keywordsStr.isEmpty() ? existingDocument.getKeyWords() : new ArrayList<>(Arrays.asList(keywordsStr.split("\\s*,\\s*")));
    
        Book updatedDocument = new Book(newTitle, year, type, keywords);
        
        updateDocument(title, updatedDocument);
        System.out.println("Document updated successfully.");

        selectedDocument = updatedDocument;
    }
    
    private void searchBook() {
        List<Book> searchResults;
        String searchType = getInput("Search by (title/author/year/type/keyword): ");
        switch (searchType.toLowerCase()) {
            case "title":
                String title = getInput("Enter title: ");
                Book document = searchDocByTitle(title);
                if (document != null) {
                    System.out.println(" Document found: " + document);
                    System.out.println(" - Title: " + document.getTitle());
                    System.out.println(" - Authors: " + String.join(", ", document.getAuthorNames()));
                    System.out.println(" - Publishing Year: " + document.getPublishingYear());
                    System.out.println(" - Type: " + document.getType());
                    System.out.println(" - Keywords: " + String.join(", ", document.getKeyWords()));
                    selectedDocument = document;

                } else {
                    System.out.println("No document found with the title '" + title + "'");
                }
                break;
            case "author":
                String author = getInput("Enter author: ");
                searchResults = searchDocByAuthor(author);
                for (Book doc : searchResults) {
                    System.out.println("Found: " + doc.getTitle() + " By: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            case "year":
                int year = getInt("Enter year: ");
                searchResults = searchDocByYear(year);
                for (Book doc : searchResults) {
                    System.out.println("Found: " + doc.getTitle() + " By: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            case "type":
                String type = getInput("Enter document type: ");
                searchResults = searcDocByType(type);
                for (Book doc : searchResults) {
                    System.out.println("Found: " + doc.getTitle() + " By: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            case "keyword":
                String keyword = getInput("Enter keyword: ");
                searchResults = searchDocByKeyword(keyword);
                for (Book doc : searchResults) {
                    System.out.println("Found: " + doc.getTitle() + " By: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            default:
                System.out.println("Invalid search type.");
                break;
        }
    } 

    private void listBooks() {
        System.out.println("Available Documents, listed by Author:");
        for (Author author : getAllAuthors()) {
            System.out.println("  - " + author.getName() + ":");
            for (Book document : author.getDocuments()){
                System.out.println("    * " + document.getTitle());
            }
        }
    }

    private void listAuthors() {
        System.out.println("Available Authors, listed by Document:");
        for (Book document : getAllDocuments()) {
            System.out.println("  - " + document.getTitle() + ":");
            for (Author author : document.getAuthors()){
                System.out.println("    * " + author.getName());
            }
        }
    }
    
    private void showSelected() {
        if (selectedDocument != null){
            System.out.println(" Document: " + selectedDocument);
                    System.out.println(" - Title: " + selectedDocument.getTitle());
                    System.out.println(" - Authors: " + String.join(", ", selectedDocument.getAuthorNames()));
                    System.out.println(" - Publishing Year: " + selectedDocument.getPublishingYear());
                    System.out.println(" - Type: " + selectedDocument.getType());
                    System.out.println(" - Keywords: " + String.join(", ", selectedDocument.getKeyWords()));
        } else System.out.println("No Document Selected.");
    }

    private void deselect() {
        selectedDocument = null;
        System.out.println("Document Deselected.");
    }

    private List<Book> getAllDocuments() {
        return this.documents;
    }

    private List<Author> getAllAuthors() {
        return this.authors;        
    }

    private void addDocument(String title, List<String> authorNames, int publishingYear, String type, List<String> keywords) {
        Book document = new Book(title, publishingYear, type, keywords);
        
        for (String authorName : authorNames) {
            Author author = null;
            for (Author existingAuthor : authors) {
                if (existingAuthor.getName().equalsIgnoreCase(authorName)) {
                    author = existingAuthor;
                    break;
                }
            }
            if (author == null) {
                author = new Author(authorName);
                authors.add(author);
            }
            document.addAuthor(author);
        }

        documents.add(document);
    }
    
    private Book searchDocByTitle(String title) {
        for (Book doc : documents) {
            if (doc.getTitle().equalsIgnoreCase(title)) {
                return doc;
            }
        }
        return null;
    }
    
    private List<Book> searchDocByAuthor(String authorName) {
        List<Book> results = new ArrayList<>();
        Author author = searchAuthorByName(authorName);
        if (author != null) {
            results.addAll(author.getDocuments());
        }
        return results;
    }
    
    private Author searchAuthorByName(String name) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null;
    }
    
    private List<Book> searcDocByType(String type) {
        List<Book> results = new ArrayList<>();
        for (Book doc : documents) {
            if (doc.getType().equalsIgnoreCase(type)) {
                results.add(doc);
            }
        }
        return results;
    }

    private List<Book> searchDocByYear(int year) {
        List<Book> results = new ArrayList<>();
        for (Book doc : documents) {
            if (doc.getPublishingYear() == year) {
                results.add(doc);
            }
        }
        return results;
    }
    
    private List<Book> searchDocByKeyword(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book doc : documents) {
            if (doc.getKeyWords().contains(keyword)) {
                results.add(doc);
            }
        }
        return results;
    }

    private void updateDocument(String title, Book updatedDocument) {
        for (Book doc : documents) {
            if (doc.getTitle().equalsIgnoreCase(title)) {
                doc.setTitle(updatedDocument.getTitle());
                doc.setPublishingYear(updatedDocument.getPublishingYear());
                doc.setType(updatedDocument.getType());
                doc.setKeyWords(updatedDocument.getKeyWords());

                for (Author docAuthor : doc.getAuthors()) {
                    boolean existsAuthor = false;
                    for (Author author : this.authors) {
                        if (author.getName().equals(docAuthor.getName())) {
                            existsAuthor = true;
                            break;
                        }
                    }
                    if (!existsAuthor) {
                        this.authors.add(docAuthor);
                    }
                    if (!docAuthor.getDocuments().contains(doc)) {
                        docAuthor.addDocument(doc);
                    }
                }
                return;
            }
        }
    }

    private void deleteDocument(String title) {
        Book toDelete = searchDocByTitle(title);
        
        if (toDelete != null) {
            List<Author> docAuthors = toDelete.getAuthors();
            if (docAuthors != null) {
                for (Author author : docAuthors) {
                    author.removeDocument(toDelete);
                }
            }
            documents.remove(toDelete);
        }
    }

    private static String getInput(String msg){
        System.out.print(msg);
        String userInput = scanner.nextLine();
        return userInput;
    }

    private static int getInt(String msg){
        System.out.print(msg);
        int userInput = scanner.nextInt();
        return userInput;
    }
    
}