package src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Library {
    List<Document> documents;

    public Library() {
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
            System.out.println("Authors:");
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

    public static void main(String[] args) {
        Library biblioteca = new Library();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("1. Agregar Documento");
            System.out.println("2. Buscar Documento");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar el buffer

            switch (opcion) {
                case 1:
                    Document nuevoDocumento = new Document();
                    System.out.print("Ingrese el título del documento: ");
                    nuevoDocumento.setTitle(new Title(scanner.nextLine()));

                    System.out.print("Ingrese el tipo del documento: ");
                    nuevoDocumento.setType(scanner.nextLine());

                    System.out.print("Ingrese el año de publicación del documento: ");
                    nuevoDocumento.setPublishYear(scanner.nextLine());

                    System.out.print("Ingrese la cantidad de autores del documento: ");
                    int numAutores = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    Author[] authors = new Author[numAutores];
                    for (int i = 0; i < numAutores; i++) {
                        System.out.print("Ingrese el nombre del autor " + (i + 1) + ": ");
                        authors[i] = new Author(scanner.nextLine());
                    }
                    nuevoDocumento.setAuthors(authors);

                    System.out.print("Ingrese la cantidad de keywords del documento: ");
                    int numKeywords = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer
                    String[] keywords = new String[numKeywords];
                    for (int i = 0; i < numKeywords; i++) {
                        System.out.print("Ingrese la keyword " + (i + 1) + ": ");
                        keywords[i] = scanner.nextLine();
                    }
                    nuevoDocumento.setKeywords(keywords);

                    biblioteca.addDocument(nuevoDocumento);
                    System.out.println("Documento agregado correctamente.\n");
                    break;
                case 2:
                    System.out.println("Seleccione el criterio de búsqueda:");
                    System.out.println("1. Título");
                    System.out.println("2. Autor");
                    System.out.println("3. Año de publicación");
                    System.out.println("4. Tipo");
                    System.out.println("5. Keyword");
                    System.out.print("Opción: ");
                    int criterio = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    System.out.print("Ingrese el valor de búsqueda: ");
                    String valor = scanner.nextLine();

                    List<Document> resultados = null;
                    switch (criterio) {
                        case 1:
                            resultados = biblioteca.searchDocument("titulo", valor);
                            break;
                        case 2:
                            resultados = biblioteca.searchDocument("autor", valor);
                            break;
                        case 3:
                            resultados = biblioteca.searchDocument("año", valor);
                            break;
                        case 4:
                            resultados = biblioteca.searchDocument("tipo", valor);
                            break;
                        case 5:
                            resultados = biblioteca.searchDocument("keyword", valor);
                            break;
                        default:
                            System.out.println("Opción inválida.");
                    }

                    if (resultados != null && !resultados.isEmpty()) {
                        System.out.println("Resultados de la búsqueda:");
                        biblioteca.mostrarDocumentos(resultados);
                    } else {
                        System.out.println("No se encontraron documentos que coincidan con la búsqueda.\n");
                    }
                    break;
                case 3:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                default:
                    System.out.println("Opción inválida.");
            }
        }
    }
}