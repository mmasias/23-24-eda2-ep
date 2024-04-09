import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private final Scanner scanner;
    protected LibraryView() {
        scanner = new Scanner(System.in);
    }

    protected void displayMenu() {
        System.out.println("----------------- Biblioteca -----------------");
        System.out.println("1. Agregar documento");
        System.out.println("2. Eliminar documento");
        System.out.println("3. Buscar por caracteristica");
        System.out.println("4. Ver documentos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }
    protected int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }
    protected Document promptDocumentDetails() {
        String title = promptTitle();
        int publicationYear = promptPublicationYear();
        List<Author> authors = promptAuthors();
        String documentType = promptDocumentType();
        List<Keyword> keywords = promptKeywords();

        return new Document(title, authors, publicationYear, documentType, keywords);
    }
    protected void displayDocumentSearch() {
        System.out.println("Ingrese la opcion por la que desea buscar: ");
        System.out.println("1. Titulo");
        System.out.println("2. Año de publicacion");
        System.out.println("3. Autor");
        System.out.println("4. Tipo de documento");
        System.out.println("5. Palabras clave");
    }
    protected String promptTitle() {
        System.out.print("Ingrese el título del documento: ");
        return scanner.nextLine();
    }
    protected int promptPublicationYear() {
        System.out.print("Ingrese el año de publicación: ");
        return scanner.nextInt();
    }
    protected List<Author> promptAuthors() {
        List<Author> authors = new ArrayList<>();
        boolean validInput = false;
        while (!validInput) {
            try {
                System.out.print("Ingrese el número de autores: ");
                int numAuthors = scanner.nextInt();
                scanner.nextLine();
                for (int i = 0; i < numAuthors; i++) {
                    System.out.print("Ingrese el nombre del autor " + (i + 1) + ": ");
                    String authorName = scanner.nextLine();
                    authors.add(new Author(authorName));
                }
                validInput = true;
                return authors;
            } catch (InputMismatchException e) {
                System.out.println("Error: El número de autores debe ser un número entero.");
                scanner.nextLine();
            }
        }
        return authors;
    }
    protected String promptDocumentType() {
        System.out.print("Ingrese el tipo de documento: ");
        String type = null;
        try {
            type = scanner.nextLine().toLowerCase();
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
        }
        return type;
    }
    protected List<Keyword> promptKeywords() {
        List<Keyword> keywords = new ArrayList<>();
        boolean validInput = false;

        while (!validInput) {
            try {
                System.out.print("Ingrese el número de palabras clave: ");
                int numKeywords = scanner.nextInt();
                scanner.nextLine();

                for (int i = 0; i < numKeywords; i++) {
                    System.out.print("Ingrese la palabra clave " + (i + 1) + ": ");
                    String keyword = scanner.nextLine();
                    keywords.add(new Keyword(keyword));
                }

                validInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Error: El número de palabras clave debe ser un número entero.");
                scanner.nextLine();
            }
        }

        return keywords;
    }
    protected void displayDocuments(List<Document> documents) {
        displayAllDocuments(documents);
    }
    protected Document promptDocumentSelection(List<Document> documents) {
        System.out.println("Seleccione un documento:");
        for (int i = 0; i < documents.size(); i++) {
            System.out.println((i + 1) + ". " + documents.get(i).getTitle());
        }
        int choice = scanner.nextInt();
        return documents.get(choice - 1);
    }
    protected void displayMessage(String message) {
        System.out.println(message);
    }
    protected void displayAllDocuments(List<Document> documents) {
        System.out.println("Documentos guardados en la biblioteca:");

        if (documents.isEmpty()) {
            System.out.println("No hay documentos similares.");
        } else {
            for (Document document : documents) {
                System.out.print("--> Titulo: " + document.getTitle());
                System.out.print(" - Autores:[ ");
                for (Author author : document.getAuthors()) {
                    System.out.print(author.getAuthorName() + " . ");
                }
                System.out.print("] - Año de publicación: " + document.getPublicationYear());
                System.out.print(" - Tipo de documento: " + document.getDocumentType());
                System.out.print(" - Palabras clave:[ ");
                for (Keyword keyword : document.getKeywords()) {
                    System.out.print(keyword.getKeyword() + " . ");
                }
                System.out.print("]");
                System.out.println();
            }
        }
    }
}