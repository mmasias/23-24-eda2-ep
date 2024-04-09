import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Libreria {
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
            System.out.println("\n ~~~| Sistema de Gestión de Biblioteca |~~~");
            System.out.println("  1. Agregar Documento");
            System.out.println("  2. Eliminar Documento");
            System.out.println("  3. Actualizar Documento");
            System.out.println("  4. Buscar Documentos");
            System.out.println("  5. Detalle del Documento Seleccionado");
            System.out.println("  6. Deseleccionar Documento");
            System.out.println("  7. Listar Documentos por Autor");
            System.out.println("  8. Listar Autores por Documento");
            System.out.println("  0. Salir");

            if (selectedDocument != null){
                System.out.println("\nDocumento Seleccionado: " + selectedDocument.getTitle());
            } else System.out.println("\nNada seleccionado");
            String choice = getInput(" Elija una opción: ");

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
                    System.out.println("Saliendo de la aplicacion...");
                    break;
                default:
                    System.out.println("Opcion invalida, por favor elije otra.");
                    break;
            }
        }
    }

    private void addBook() {
        String title = getInput("Ingrese el titulo del documento: ");
        
        String authorsStr = getInput("Ingres los autores separados por comas: ");
        List<String> authors = new ArrayList<>(Arrays.asList(authorsStr.split("\\s*,\\s*")));

        int year = Integer.parseInt(getInput("Ingrese el año de publicacion: "));

        String type = getInput("Ingrese el tipo de documento: ");

        String keywordsStr = getInput("Ingrese las palabras clave separadas por coma: ");
        List<String> keywords = new ArrayList<>(Arrays.asList(keywordsStr.split("\\s*,\\s*")));

        Book newDocument = new Book(title, year, type, keywords);
        
        addDocument(title, authors, year, type, keywords);
        System.out.println("Documento agregado exitosamente.");

        selectedDocument = newDocument;
    }

    private void removeBook() {
        String title;
        if (selectedDocument != null){
            title = selectedDocument.getTitle();
        } else{
            title = getInput("Ingrese el titulo del documento que quiere eliminar ");
        } 
        deleteDocument(title);
        System.out.println("Documento seleccionado.");
    }

    private void updateBok() {
        String title;
        if (selectedDocument != null){
            title = selectedDocument.getTitle();
        } else{
            title = getInput("Ingrese le titulo del documento que quiere editar: ");
        } 

        Book existingDocument = searchDocByTitle(title);
        if (existingDocument == null) {
            System.out.println("Documento no encontrado.");
            return;
        }

        String newTitle = getInput("Ingrese el titulo del nuevo documento" + existingDocument.getTitle() + "''): ");
        if (newTitle.isEmpty()) {
            newTitle = existingDocument.getTitle();
        }

        String modifyAuthors = getInput("Desea modificar los autores? (S/N): ");
        if ("N".equalsIgnoreCase(modifyAuthors)) {
            System.out.println("Autores del documento: " + String.join(", ", existingDocument.getAuthorNames()));
            while (true) {
                String authorModification = getInput("Agrega o elimina autores, 'Listo' para finalizar: ");
                if ("Listo".equalsIgnoreCase(authorModification)) {
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

        String yearStr = getInput("Ingrese el año de publicacion del nuevo documento" + existingDocument.getPublishingYear() + "''): ");
        int year = yearStr.isEmpty() ? existingDocument.getPublishingYear() : Integer.parseInt(yearStr);

        String type = getInput("Ingrese el nuevo tipo de documento" + existingDocument.getType() + "''): ");
        if (type.isEmpty()) {
            type = existingDocument.getType();
        }

        String keywordsStr = getInput("Ingrese las nuevas palabras clave separadas por coma:  ");
        List<String> keywords = keywordsStr.isEmpty() ? existingDocument.getKeyWords() : new ArrayList<>(Arrays.asList(keywordsStr.split("\\s*,\\s*")));
    
        Book updatedDocument = new Book(newTitle, year, type, keywords);
        
        updateDocument(title, updatedDocument);
        System.out.println("Documento actualizado exitosamente.");

        selectedDocument = updatedDocument;
    }
    
    private void searchBook() {
        List<Book> searchResults;
        String searchType = getInput("Buscar por (titulo/autor/año/tipo/palabraClave): ");
        switch (searchType.toLowerCase()) {
            case "titulo":
                String title = getInput("Ingresa el titulo: ");
                Book document = searchDocByTitle(title);
                if (document != null) {
                    System.out.println(" Documento encontrado: " + document);
                    System.out.println(" - Titulo: " + document.getTitle());
                    System.out.println(" - Autores: " + String.join(", ", document.getAuthorNames()));
                    System.out.println(" - Año de publicacion: " + document.getPublishingYear());
                    System.out.println(" - Tipo: " + document.getType());
                    System.out.println(" - Palabras clave: " + String.join(", ", document.getKeyWords()));
                    selectedDocument = document;

                } else {
                    System.out.println("Documento no encontrado por su titulo '" + title + "'");
                }
                break;
            case "autor":
                String author = getInput("Ingrese el autor: ");
                searchResults = searchDocByAuthor(author);
                for (Book doc : searchResults) {
                    System.out.println("Encontrado: " + doc.getTitle() + " Por: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            case "año":
                int year = getInt("Ingrese el año: ");
                searchResults = searchDocByYear(year);
                for (Book doc : searchResults) {
                    System.out.println("Encontrado: " + doc.getTitle() + " Por: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            case "tipo":
                String type = getInput("Ingrese el tipo de documento: ");
                searchResults = searcDocByType(type);
                for (Book doc : searchResults) {
                    System.out.println("Encontrado: " + doc.getTitle() + " By: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            case "keyword":
                String keyword = getInput("Enter keyword: ");
                searchResults = searchDocByKeyword(keyword);
                for (Book doc : searchResults) {
                    System.out.println("Found: " + doc.getTitle() + " Por: " + String.join(", ", doc.getAuthorNames()));
                }
                break;
            default:
                System.out.println("Tipo de busqueda erroneo, por favor ingrese uno de los mostrados anteriormente.");
                break;
        }
    } 

    private void listBooks() {
        System.out.println("Documentos disponibles, enlistado por autores");
        for (Author author : getAllAuthors()) {
            System.out.println("  - " + author.getName() + ":");
            for (Book document : author.getDocuments()){
                System.out.println("    * " + document.getTitle());
            }
        }
    }

    private void listAuthors() {
        System.out.println("Autores disponibles, enlistado por documentos");
        for (Book document : getAllDocuments()) {
            System.out.println("  - " + document.getTitle() + ":");
            for (Author author : document.getAuthors()){
                System.out.println("    * " + author.getName());
            }
        }
    }
    
    private void showSelected() {
        if (selectedDocument != null){
            System.out.println(" Documento: " + selectedDocument);
                    System.out.println(" - Titulo: " + selectedDocument.getTitle());
                    System.out.println(" - Autores: " + String.join(", ", selectedDocument.getAuthorNames()));
                    System.out.println(" - Año de publicacion: " + selectedDocument.getPublishingYear());
                    System.out.println(" - Tipo: " + selectedDocument.getType());
                    System.out.println(" - Palabras clave: " + String.join(", ", selectedDocument.getKeyWords()));
        } else System.out.println("Documento no seleccionado.");
    }

    private void deselect() {
        selectedDocument = null;
        System.out.println("EL documento ha sido deseleccionado.");
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

    public static void main(String[] args) {
        Libreria libreria = new Libreria();
        libreria.main();
    }
}