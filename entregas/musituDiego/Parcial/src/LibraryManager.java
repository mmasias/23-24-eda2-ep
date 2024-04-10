import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Document> documents;
    private List<Author> authors;
    private List<DocumentAuthor> relations;
    private Scanner scanner;
    public enum DocumentType{BOOK,MAGAZINE,ARTICLE,PAPER}


    public LibraryManager(){
        this.documents=new ArrayList<>();
        this.authors=new ArrayList<>();
        this.relations=new ArrayList<>();
        this.scanner= new Scanner(System.in);
    }

    public void startLibraryManager(){
        while(true){
            System.out.println("----------------------");
            System.out.println("-Gestor Biblioteca -");
            System.out.println("----------------------");
            System.out.println();
            System.out.println("1. Todos los documentos");
            System.out.println("2. Nuevo documento");
            System.out.println();
            System.out.println("3. Todos los Autores");
            System.out.println("4. Nuevo Autor");
            System.out.println("5. Añadir/Eliminar autor a Documento");
            System.out.println();
            System.out.println("6. Buscar documento por ID/Titulo");
            System.out.println("7. Buscar todos los autores de un documento por ID de documento");
            System.out.println("8. Buscar documentos de un autor por ID de autor");
            System.out.println("9. Buscar documentos por palabras clave");
            System.out.println();
            System.out.println("10. Modificar Documento");
            System.out.println("11. Cerrar Programa");


            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    listDocuments();
                    break;
                case 2:
                    scanner.nextLine();
                    Document newdocument = addDocument();
                    if (newdocument != null) {
                        System.out.println("¿Deseas añadir un autor y relacionarlo con este documento? (s/n)");
                        String decision = scanner.nextLine();
                        if (decision.equalsIgnoreCase("s")) {
                            Author newAuthor = addAuthor();
                            if (newAuthor != null) {
                                addRelation(newdocument.getId(), newAuthor.getId());
                            }
                        }
                    }
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    scanner.nextLine();
                    Author newAuthor = addAuthor();
                    if (newAuthor != null) {
                        System.out.println("¿Deseas añadir un documento y relacionarlo con este autor? (s/n)");
                        String decision = scanner.nextLine();
                        if (decision.equalsIgnoreCase("s")) {
                            Document documentToAdd = addDocument();
                            if (documentToAdd != null) {
                                addRelation(documentToAdd.getId(), newAuthor.getId());
                            }
                        }
                    }
                    break;
                case 5:
                    addOrRemoveAuthorsFromDocument();
                    break;

                case 6:
                    System.out.println("Indica el ID o título del documento:");
                    scanner.nextLine();
                    String input = scanner.nextLine();
                    Document document = null;
                
                    try {
                        // Intenta convertir la entrada a un entero para buscar por ID.
                        int documentId = Integer.parseInt(input);
                        System.out.println("buscando por id");
                        document = findDocumentById(documentId);
                    } catch (NumberFormatException e) {
                        System.out.println("Buscando por titulo");
                        document = findDocumentByTitle(input);
                    }
                    if(document!=null){
                        System.out.println(document.toString());
                    } else{
                        System.out.println("documento no econtrado o id no valido");
                    }
                    break;
                case 7:
                    System.out.println("Indica el ID del documento para buscar sus autores:");
                    int otherdocument = scanner.nextInt();
                    scanner.nextLine();
                    List<Author> authors = getAuthorsByDocumentId(otherdocument);
                    if(authors.isEmpty()){
                        System.out.println("No se encontraron autores para el documento con ID: " + otherdocument);
                    }else{
                        System.out.println("-------------");
                        System.out.println("Autores del documento con ID: " + otherdocument);
                        System.out.println("-------------");
                        for(Author author : authors){
                            System.out.println(author.toString());
                        }
                    }
                    break;

                case 8:
                    System.out.println("Indica el ID del autor para buscar sus documentos:");
                    int authorId = scanner.nextInt();
                    scanner.nextLine();
                    List<Document> documents = getDocumentsByAuthorId(authorId);
                    if(documents.isEmpty()){
                        System.out.println("No se encontraron documentos para el autor con ID: " + authorId);
                    }else{
                        System.out.println("documentos del autor con ID: " + authorId);
                        for(Document document2 : documents){
                            System.out.println(document2.toString());
                        }
                    }
                    break;

                case 9:
                    findDocumentsByKeywords();
                    break;
                case 10:
                    modifyDocument();
                    break;

                case 11:
                    System.out.println("Cerrando gestor Biblioteca");
                    break;

                default:
                    System.out.println("Opción no valida.");
                    break;
            }
        }
    }



    public void listDocuments(){
        if(documents.isEmpty()){
            System.out.println("La lista de documentos esta vacia");

        }else{
            System.out.println("----------------------");
            System.out.println("-Todos los Documentos -");
            System.out.println("----------------------");
            for(Document document : documents){
                System.out.println(document.toString());
                System.out.println("----------------------");

            }
        }
    }

    public Document addDocument(){
        
        System.out.println("Titulo del Documento:");
        String title = scanner.nextLine();

        System.out.println("Fecha de Publicación (dd/MM/yyyy):");
        String dateString = scanner.nextLine();
        LocalDate publicationDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            publicationDate = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Por favor, introduce la fecha en formato dd/MM/yyyy.");
            return null;
        }

        System.out.println("Tipo de documento (BOOK, MAGAZINE, ARTICLE, PAPER):");
        String typeInput = scanner.nextLine().toUpperCase();
        Document.DocumentType type;
        try {
            type = Document.DocumentType.valueOf(typeInput);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de documento no válido.");
            return null;
        }
    
        System.out.println("Palabras clave (separadas por comas):");
        String keywordsInput = scanner.nextLine();
        List<String> keywords = Arrays.asList(keywordsInput.split(","));
        
        String input = title+type+keywordsInput;
        int id = input.hashCode();
        Document newDocument = new Document(id, title, publicationDate,type, keywords);
        documents.add(newDocument);
        System.out.println("Siguiente documento añadido: " + newDocument.toString());
        return newDocument;
    }

    public Author addAuthor(){
        System.out.println("Añadiendo Autor:");
        System.out.println("Nombre del Autor:");
        String name = scanner.nextLine();
        int id = name.hashCode();

        Author newAuthor = new Author(id, name);
        authors.add(newAuthor);
        System.out.println("Siguiente autor Añadido: "+newAuthor.toString());
        return newAuthor;
        
    }

    public void addRelation(int documentId, int authorId){
        Document document = findDocumentById(documentId);
        Author author = findAuthorById(authorId);

        if(document==null||author==null){
            System.out.println("documento:"+documentId);
            System.out.println("Autor:"+authorId);
            System.out.println("Ha surgido un problema, revisa ambos id");
            return;
        }
        for(DocumentAuthor relation : relations){
            if(relation.getDocumentId()==documentId &&relation.getAuthorId()==authorId){
                System.out.println("La relación ya existe");
                return;
            }
        }

        DocumentAuthor newRelation = new DocumentAuthor(documentId, authorId);
        relations.add(newRelation);
        System.out.println("Relación creada con exito");

    }

    public DocumentAuthor findRelation(int docId, int authorId) {
        for (DocumentAuthor relation : relations) {
            if (relation.getDocumentId() == docId && relation.getAuthorId() == authorId) {
                return relation;
            }
        }
        return null;
    }
    

    public List<Author> getAuthorsByDocumentId(int documentId){
        List<Author> authorsFordocument = new ArrayList<>();
        for(DocumentAuthor relation : relations){
            if(relation.getDocumentId()==documentId){
                Author author = findAuthorById(relation.getAuthorId());
                if(author!=null){
                    authorsFordocument.add(author);
                }
            }
        }
        return authorsFordocument;
    }

    public List<Document> getDocumentsByAuthorId(int authorId){
        List<Document> documentsForAutor = new ArrayList<>();
        for(DocumentAuthor relation : relations){
            if(relation.getAuthorId()==authorId){
                Document document = findDocumentById(relation.getDocumentId());
                if(document!=null){
                    documentsForAutor.add(document);
                }
            }
        }
        return documentsForAutor;
    }

    public void addOrRemoveAuthorsFromDocument() {
        System.out.println("Indica el ID del documento:");
        int docId = scanner.nextInt();
        scanner.nextLine();
    
        Document document = findDocumentById(docId);
        if (document == null) {
            System.out.println("Documento no encontrado.");
            return;
        }
    
        System.out.println("Autores disponibles:");
        for (Author author : authors) {
            System.out.println("ID: " + author.getId() + ", Nombre: " + author.getName());
        }
        
        System.out.println("Introduce el ID del autor para añadir o eliminar del documento:");
        int authorId = scanner.nextInt();
        scanner.nextLine();
    
        Author author = findAuthorById(authorId);
        if (author == null) {
            System.out.println("Autor con ID " + authorId + " no encontrado.");
            return;
        }
    
        DocumentAuthor existingRelation = findRelation(docId, authorId);
        if (existingRelation != null) {
            relations.remove(existingRelation);
            System.out.println("Autor con ID " + authorId + " eliminado del documento.");
            return;
        } else {
            DocumentAuthor newRelation = new DocumentAuthor(docId, authorId);
            relations.add(newRelation);
            System.out.println("Autor con ID " + authorId + " añadido al documento.");
            return;
        }
    }
    
    public void modifyDocument() {
        System.out.println("Indica el ID o título del documento a modificar:");
        scanner.nextLine();
        String input = scanner.nextLine();
        Document documentToModify = null;
    
        try {
            int documentId = Integer.parseInt(input);
            documentToModify = findDocumentById(documentId);
        } catch (NumberFormatException e) {
            documentToModify = findDocumentByTitle(input);
        }
    
        if (documentToModify == null) {
            System.out.println("Documento no encontrado.");
            return;
        }
    
        System.out.println("Modificando documento: " + documentToModify.toString());
        System.out.println("Introduce el nuevo título del documento (deja en blanco para no cambiar):");
        String newTitle = scanner.nextLine();
        if (!newTitle.isEmpty()) {
            documentToModify.setTitle(newTitle);
        }
    
        System.out.println("Introduce la nueva fecha de publicación (dd/MM/yyyy) (deja en blanco para no cambiar):");
        String newDate = scanner.nextLine();
        if (!newDate.isEmpty()) {
            try {
                LocalDate newPublicationDate = LocalDate.parse(newDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                documentToModify.setPublicationDate(newPublicationDate);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha no válido. No se cambió la fecha.");
            }
        }
    
        System.out.println("Introduce las nuevas palabras clave (separadas por comas) (deja en blanco para no cambiar):");
        String newKeywords = scanner.nextLine();
        if (!newKeywords.isEmpty()) {
            List<String> keywordsList = Arrays.asList(newKeywords.split(","));
            documentToModify.setKeywords(keywordsList);
        }
    
        System.out.println("Documento modificado con éxito: " + documentToModify.toString());
    }

    public Document findDocumentByTitle(String input) {
        for (Document document : documents){
            if(document.getTitle().equals(input)){
                return document;
            }
        } 

        return null;

    }

    public Document findDocumentById(int documentId){
        for(Document document :documents){
            if(document.getId()==documentId){
                return document;
            }
        }
        return null;
    }

    public void findDocumentsByKeywords() {
        System.out.println("Introduce las palabras clave para buscar documentos (separadas por comas):");
        scanner.nextLine();
        String input = scanner.nextLine();
        List<String> keywordsToSearch = Arrays.asList(input.split("\\s*,\\s*"));
        List<Document> matchingDocuments = new ArrayList<>();
        for (Document document : documents) {
            for (String keyword : document.getKeywords()) {
                if (keywordsToSearch.contains(keyword)) {
                    matchingDocuments.add(document);
                    break; //No enviamos duplicados
                }
            }
        }
    
        if (matchingDocuments.isEmpty()) {
            System.out.println("No se encontraron documentos con las palabras clave proporcionadas.");
        } else {
            System.out.println("Documentos encontrados:");
            for (Document document : matchingDocuments) {
                System.out.println(document.toString());
            }
        }
    }

    public Author findAuthorById(int authorId){
        for(Author author : authors){
            if(author.getId()==authorId){
                return author;
            }
        }
        return null;
    }

    public void listAuthors(){
        if(authors.isEmpty()){
            System.out.println("La lista de autores esta vacia");
        }else{
            System.out.println("----------------------");
            System.out.println("- Todos los Autores  -");
            System.out.println("----------------------");
            for(Author author : authors)
            System.out.println(author.toString());
            System.out.println("----------------------");
        }
    }
}
