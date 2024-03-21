package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class LibraryManager {
    List<Document> documents;

    public LibraryManager() {
        this.documents = new ArrayList<>();
    }

    public void addDocument(Document document) {
        this.documents.add(document);
    }

    public List<Document> searchDocument(String criterio, String valor) {
        List<Document> resultados = new ArrayList<>();
        for (Document document : documents) {
            switch (criterio) {
                case "titulo":
                    if (document.getTitle().getName().equalsIgnoreCase(valor)) {
                        resultados.add(document);
                    }
                    break;
                case "autor":
                    for (Author author : document.getAuthors()) {
                        if (author.getName().equalsIgnoreCase(valor)) {
                            resultados.add(document);
                            break;
                        }
                    }
                    break;
                case "lenguaje":
                    if (document.getLanguage().equalsIgnoreCase(valor)) {
                        resultados.add(document);
                    }
                    break;
                case "año":
                    if (document.getPublishYear().equalsIgnoreCase(valor)) {
                        resultados.add(document);
                    }
                    break;
                case "tipo":
                    if (document.getType().equalsIgnoreCase(valor)) {
                        resultados.add(document);
                    }
                    break;
                case "keyword":
                    for (String keyword : document.getKeywords()) {
                        if (keyword.equalsIgnoreCase(valor)) {
                            resultados.add(document);
                            break;
                        }
                    }
                    break;
            }
        }
        return resultados;
    }

    public void mostrarDocumentos(List<Document> documents) {
        for (Document document : documents) {
            System.out.println("ID: " + document.getId());
            System.out.println("Título: " + document.getTitle().getName());
            System.out.println("Tipo: " + document.getType());
            System.out.println("Autores:");
            for (Author author : document.getAuthors()) {
                System.out.println("- " + author.getName());
            }
            System.out.println("Año de Publicación: " + document.getPublishYear());
            System.out.println("Keywords:");
            for (String keyword : document.getKeywords()) {
                System.out.println("- " + keyword);
            }
            System.out.println();
        }
    }

    public void addDocument() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el título del documento: ");
        Title title = new Title(scanner.nextLine());

        System.out.print("Ingrese el tipo del documento: ");
        String type=scanner.nextLine();

        System.out.print("Ingrese el lenguaje del documento: ");
        String language=scanner.nextLine();

        System.out.print("Ingrese el año de publicación del documento: ");
        String publishYear = scanner.nextLine();

        System.out.print("Ingrese la cantidad de keywords del documento: ");
        int numKeywords = scanner.nextInt();
        scanner.nextLine(); 
        String[] keywords = new String[numKeywords];
        for (int i = 0; i < numKeywords; i++) {
            System.out.print("Ingrese la keyword " + (i + 1) + ": ");
            keywords[i] = scanner.nextLine();
        }

        System.out.print("Ingrese la cantidad de autores del documento: ");
        int numAutores = scanner.nextInt();
        scanner.nextLine(); 
        Author[] authors = new Author[numAutores];
        for (int i = 0; i < numAutores; i++) {
            System.out.print("Ingrese el nombre del autor " + (i + 1) + ": ");
             authors[i] = new Author(scanner.nextLine());
        }
                    
                    
        Document nuevoDocumento = new Document(title, type, language, publishYear);
        nuevoDocumento.setKeywords(keywords);
        nuevoDocumento.setAuthors(authors);
        documents.add(nuevoDocumento);
        System.out.println("Documento agregado correctamente.\n");
    }

    public void searchOption() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Seleccione el criterio de búsqueda:");
        System.out.println("1. Título");
        System.out.println("2. Autor");
        System.out.println("3. Año de publicación");
        System.out.println("4. Tipo");
        System.out.println("5. Keyword");
        System.out.println("6. Lenguaje");
        System.out.print("Opción: ");
        int criterio = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.print("Ingrese el valor de búsqueda: ");
        String valor = scanner.nextLine();
        
        List<Document> resultados = null;
        switch (criterio) {
            case 1:
            resultados = searchDocument("titulo", valor);
            break;
            case 2:
            resultados = searchDocument("autor", valor);
            break;
            case 3:
            resultados = searchDocument("año", valor);
            break;
            case 4:
            resultados = searchDocument("tipo", valor);
            break;
            case 5:
            resultados = searchDocument("keyword", valor);
            break;
            case 6:
            resultados = searchDocument("lenguaje", valor);
            break;
            default:
            System.out.println("Opción inválida.");
        }
        
        if (resultados != null && !resultados.isEmpty()) {
            System.out.println("Resultados de la búsqueda:");
            mostrarDocumentos(resultados);
        } else {
            System.out.println("No se encontraron documentos que coincidan con la búsqueda.\n");
        }
        
    }

    public void startLibraryManager() {
        Scanner scanner = new Scanner(System.in);
        boolean isWorking = true;

        while (isWorking) {
            System.out.println("1. Agregar Documento");
            System.out.println("2. Buscar Documento");
            System.out.println("3. Ver Catalogo");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    addDocument();
                    
                    break;
                case 2:
                    searchOption();
                    break;
                case 3:
                    System.out.println("Catálogo de la biblioteca:");
                    mostrarDocumentos(documents);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    isWorking = false;
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }

}