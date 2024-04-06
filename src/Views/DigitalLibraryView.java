package Views;

import Models.Author;
import Models.Document;
import Models.Keyword;
import java.util.List;
import java.util.Scanner;

public class DigitalLibraryView {
    private Scanner scanner = new Scanner(System.in);

    public void displayMainOptions() {
        System.out.println("\nDigital Library System");
        System.out.println("1. View Documents");
        System.out.println("2. View Authors");
        System.out.println("3. View Keywords");
        System.out.println("0. Exit");
        System.out.print("Choose an option: ");
    }

    public int getOption() {
        while (!scanner.hasNextInt()) {
            scanner.next();
            System.out.print("Please enter a valid number: ");
        }
        return scanner.nextInt();
    }

    public void displayDocumentsList(List<Document> documents) {
        if (documents.isEmpty()) {
            System.out.println("No documents available.");
        } else {
            System.out.println("\nDocuments:");
            for (Document document : documents) {
                System.out.printf("%d: %s (Year: %d)\n", document.getId(), document.getTitle(), document.getPublicationYear());
            }
        }
        displayEntityOptions("Document");
    }

    public void displayAuthorsList(List<Author> authors) {
        if (authors.isEmpty()) {
            System.out.println("No authors available.");
        } else {
            System.out.println("\nAuthors:");
            for (Author author : authors) {
                System.out.printf("%d: %s (%s)\n", author.getId(), author.getName(), author.getAffiliation());
            }
        }
        displayEntityOptions("Author");
    }

    public void displayKeywordsList(List<Keyword> keywords) {
        if (keywords.isEmpty()) {
            System.out.println("No keywords available.");
        } else {
            System.out.println("\nKeywords:");
            for (Keyword keyword : keywords) {
                System.out.printf("%d: %s\n", keyword.getId(), keyword.getKeyword());
            }
        }
        displayEntityOptions("Keyword");
    }

    public void displayEntityOptions(String entityType) {
        System.out.println("\nOptions for " + entityType + ":");
        System.out.println("1. Add new " + entityType);
        System.out.println("2. Edit an existing " + entityType);
        System.out.println("3. Delete an existing " + entityType);
        System.out.println("4. View details of an existing " + entityType);
        System.out.println("0. Go back to main menu");
        System.out.print("Select an option: ");
    }

    public void displayInvalidOption() {
        System.out.println("Invalid option. Please try again.");
    }
}
