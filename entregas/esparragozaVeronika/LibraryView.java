import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryView {
    private Scanner scanner;

    public LibraryView() {
        scanner = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("----- Biblioteca -----");
        System.out.println("1. Agregar documento");
        System.out.println("2. Eliminar documento");
        System.out.println("3. Buscar por título");
        System.out.println("4. Ver documentos");
        System.out.println("5. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public int getChoice() {
        return scanner.nextInt();
    }

    public Document promptDocumentDetails() {
        String title = promptTitle();
        int publicationYear = promptPublicationYear();
        List<Author> authors = promptAuthors();
        String documentType = promptDocumentType();
        List<Keyword> keywords = promptKeywords();

        return new Document(title, authors, publicationYear, documentType, keywords);
    }

    protected String promptTitle() {
        System.out.print("Ingrese el título del documento: ");
        String title = scanner.nextLine();
        scanner.nextLine();
        return title;
    }

    private int promptPublicationYear() {
        System.out.print("Ingrese el año de publicación: ");
        return scanner.nextInt();
    }

    private List<Author> promptAuthors() {
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

    private String promptDocumentType() {
        System.out.print("Ingrese el tipo de documento: ");
        return scanner.nextLine();
    }

    private List<Keyword> promptKeywords() {
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
        for (Document document : documents) {
            System.out.println(document);
        }
    }

    public Document promptDocumentSelection(List<Document> documents) {
        System.out.println("Seleccione un documento:");
        for (int i = 0; i < documents.size(); i++) {
            System.out.println((i + 1) + ". " + documents.get(i));
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
            System.out.println("Título: " + document.getTitle());
            System.out.println("Autores:");
            for (Author author : document.getAuthors()) {
                System.out.println("- " + author.getAuthorName());
            }
            System.out.println("Año de publicación: " + document.getPublicationYear());
            System.out.println("Tipo de documento: " + document.getDocumentType());
            System.out.println("Palabras clave:");
            for (Keyword keyword : document.getKeywords()) {
                System.out.println("- " + keyword.getKeyword());
            }
            System.out.println();
        }
    }
}