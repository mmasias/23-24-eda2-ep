package app;

import java.util.ArrayList;
import java.util.Scanner;


public class Library {
    private ArrayList<Document> documents;
    private ArrayList<Author> authors;
    private ArrayList<DocumentAuthor> relations;
    private Scanner userInput;
    
    public Library() {
        this.documents = new ArrayList<Document>();
        this.authors = new ArrayList<Author>();
        this.relations = new ArrayList<DocumentAuthor>();
        this.userInput = new Scanner(System.in);
    }

    public void addDocument(Document document) {
        documents.add(document);
        System.out.println("Libro añadido. ¿Deseas añadir autores a este libro? (s/n)");
        String response = userInput.nextLine();
        if ("s".equalsIgnoreCase(response)) {
            addAuthor(document);
        }
    }

    public void createDocument() {
        int wordsCount = 0;
        ArrayList<String> keyWords = new ArrayList<>();

        System.out.println("Introduce el título del documento: ");
        String title = userInput.nextLine();

        System.out.println("Introduce el año de publicación: ");
        int publishingYear = userInput.nextInt();

        System.out.println("Introduce el número de temas/palabras clave del documento: ");
        int keyWordNumber = userInput.nextInt();
        userInput.nextLine();

        while (wordsCount != keyWordNumber) {
            System.out.println("Introduce el tema/palabra clave: ");
            String keyWord = userInput.nextLine();
            keyWords.add(keyWord);
            
            wordsCount++;
        }

        System.out.println("Introduce el tipo de documento (Libro, Revista, Artículo o Paper Científico): ");
        String typeOfDocument = userInput.nextLine();
        
        DocumentType docType = DocumentType.valueOf(typeOfDocument);

        Document document = new Document(documents.size() + 1, title, publishingYear, docType, keyWords);
        addDocument(document);

    }

    public void addAuthor(Author author) {
        authors.add(author);
    }

    public void addAuthor(Document document) {
        boolean addingAuthors = true;
        while(addingAuthors) {
            System.out.println("Introduce el ID del autor para asociar con el libro, o 'nuevo' para añadir un nuevo autor");
            String input = userInput.nextLine();
            if("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre del nuevo autor: ");
                String name = userInput.nextLine();
                System.out.println("Introduce los apellidos del nuevo autor: ");
                String surname = userInput.nextLine();
                Author newAuthor = new Author(authors.size() + 1, name, surname);
                addAuthor(newAuthor);
                addRelation(document.getId(), newAuthor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    addRelation(document.getId(), authorId);
                    System.out.println("Autor asociado al libro.");
                } catch (NumberFormatException e) {
                    System.err.println("Entrada no válida");
                }
            }
        }
    }

    public void addRelation(int documentId, int authorId) {
        relations.add(new DocumentAuthor(documentId, authorId));
    }
    
    public Document searchDocumentById(int id) {
        for (Document d: documents) {
            if (d.getId() == id) {
                return d;
            }
        }
        return null;
    }

    public Document searchDocumentByName(String documentName) {
        for(Document d : documents) {
            if(d.getTitle().equals(documentName)){
                return d;
            }
        }
        return null;
    }


    public Author searchAuthorById(int id) {
        for(Author a: authors) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }

    public Author searchAuthorByName(String name) {
        for(Author a: authors) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public ArrayList<Document> getDocumentsByAuthorId(int authorId) {
        ArrayList<Document> result = new ArrayList<>();
        for (DocumentAuthor da: relations) {
            if (da.getAuthorId() == authorId) {
                result.add(searchDocumentById(da.getDocumentId()));
            }
        }
        return result;
    }

    public ArrayList<Author> getAuthorsByDocumentId(int documentId) {
        ArrayList<Author> result = new ArrayList<>();
        for (DocumentAuthor da : relations) {
            if (da.getDocumentId() == documentId) {
                result.add(searchAuthorById(da.getAuthorId()));
            }
        }
        return result;
    }

    public void editDocumentName(String oldName, String newName) {
        Document document = searchDocumentByName(oldName);
        document.setTitle(newName);
    }

    public void editDocumentName(Document document) {
        System.out.println("Introduce el nuevo título: ");
        String newTitle = userInput.nextLine();
        editDocumentName(document.getTitle(), newTitle);
        System.out.println("El título ha sido cambiado a " + searchDocumentByName(newTitle).getTitle());

    }

    public void editPublishingYear(String title, int newYear) {
        Document document = searchDocumentByName(title);
        document.setPublishingYear(newYear);
    }

    public void editPublishingYear(Document document) {
        System.out.println("Introduce el nuevo año de publicación: ");
        int newYear = userInput.nextInt();
        editPublishingYear(document.getTitle(), newYear);
        System.out.println("El año de publicación ha sido cambiado a " + document.getPublishingYear());
    }

    public void editKeyWord(String title, String oldKeyWord, String newKeyWord) {
        Document document = searchDocumentByName(title);
        int oldWordIndex = document.searchKeyWordIndex(oldKeyWord);
        document.getKeyWords().set(oldWordIndex, newKeyWord);
        
    }

    public void editKeyWord(Document document) {
        System.out.println("Introduce la palabra clave a editar: ");
        String oldKeyWord = userInput.nextLine();

        System.out.println("Introduce la nueva palabra clave: ");
        String newKeyWord = userInput.nextLine();

        editKeyWord(document.getTitle(), oldKeyWord, newKeyWord);
        System.out.println("La palabra clave " + oldKeyWord + " ha sido cambiada a " + newKeyWord);
    }

    public void editDocument() {
        boolean isEditingRunning = true;

        while (isEditingRunning) {
            System.out.println("Introduce el título del documento: ");
            String title = userInput.nextLine();
            Document documentWanted = searchDocumentByName(title);
            System.out.println("Seleccione la acción que desea realizar: ");
            System.out.println("T - Editar Título | P - Editar Año de Publicación | K - Editar Palabra Clave | S - Salir");
            String userResponse = userInput.nextLine();
    
            switch(userResponse) {
                case "T": 
                    editDocumentName(documentWanted);
                    break;
                case "P":
                    editPublishingYear(documentWanted);
                    break;
                case "K":
                    editKeyWord(documentWanted);
                    break;
                case "S": 
                    isEditingRunning = false;
                    userInput.close();
                    break;
            }
        }
      
    }

    public void deleteAllDocuments() {
        documents.clear();
    }

    public void deleteAllAuthors() {
        authors.clear();
    }

    public void deleteAllRelations() {
        relations.clear();
    }

    public void showDocuments() {
        System.out.println("Documentos en la biblioteca: ");
        if (documents.isEmpty()) {
            System.out.println("No hay documentos en la biblioteca. ");
        } else {
            for (Document d: documents) {
                d.toStringDocument();
                System.out.println(getAuthorsByDocumentId(d.getId()));
            }
        }
    }

    public void showAuthors() {
        System.out.println("Autores registrados: ");
        if(authors.isEmpty()) {
            System.out.println("No hay autores disponibles. ");
        } else {
            for (Author a : authors) {
                a.toStringAuthor();
            }
        }
    }
    
}
