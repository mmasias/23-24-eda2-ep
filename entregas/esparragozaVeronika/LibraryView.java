import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private Scanner scanner;

    public LibraryView() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("----------------- Biblioteca -----------------");
        System.out.println("1. Agregar documento");
        System.out.println("2. Eliminar documento");
        System.out.println("3. Buscar por caracteristica");
        System.out.println("4. Ver documentos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int getChoice() {
        int choice = scanner.nextInt();
        scanner.nextLine();
        return choice;
    }

    public Document promptDocumentDetails() {
        String title = promptTitle();
        int publicationYear = promptPublicationYear();
        List<Author> authors = promptAuthors();
        String documentType = promptDocumentType();
        List<Keyword> keywords = promptKeywords();

        return new Document(title, authors, publicationYear, documentType, keywords);
    }

    public void displayDocumentSearch() {
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
        System.out.print("Ingrese el número de autores: ");
        int numAuthors = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numAuthors; i++) {
            System.out.print("Ingrese el nombre del autor " + (i + 1) + ": ");
            String authorName = scanner.nextLine();
            authors.add(new Author(authorName));
        }
        return authors;
    }

    protected String promptDocumentType() {
        System.out.print("Ingrese el tipo de documento: ");
        String type = null;
        try {
            type = scanner.nextLine();
        } catch (Exception err) {
            System.out.println("Error: " + err.getMessage());
        }
        return type;
    }

    protected List<Keyword> promptKeywords() {
        List<Keyword> keywords = new ArrayList<>();
        System.out.print("Ingrese el número de palabras clave: ");
        int numKeywords = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < numKeywords; i++) {
            System.out.print("Ingrese la palabra clave " + (i + 1) + ": ");
            String keyword = scanner.nextLine();
            keywords.add(new Keyword(keyword));
        }
        return keywords;
    }

    public void displayDocuments(List<Document> documents) {
        System.out.println("Documentos encontrados:");
        displayAllDocuments(documents);
    }

    public Document promptDocumentSelection(List<Document> documents) {
        System.out.println("Seleccione un documento:");
        for (int i = 0; i < documents.size(); i++) {
            System.out.println((i + 1) + ". " + documents.get(i).getTitle());
        }
        int choice = scanner.nextInt();
        return documents.get(choice - 1);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayAllDocuments(List<Document> documents) {
        System.out.println("Documentos guardados en la biblioteca:");
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