import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Library {
    private List<Document> documentList;
    private List<Author> authorList;
    private List<KeyWords> keyWordsList;
    private List<DocumentAuthor> relationDocumentAuthors;
    private List<DocumentKeyWords> relationDocumentKeyWords;
    private Scanner userInput;

    public Library() {
        documentList = new ArrayList<Document>();
        authorList = new ArrayList<Author>();
        keyWordsList = new ArrayList<KeyWords>();
        relationDocumentAuthors = new ArrayList<DocumentAuthor>();
        relationDocumentKeyWords = new ArrayList<DocumentKeyWords>();
        this.userInput = new Scanner(System.in);
    }

    private void addDocument() {
        System.out.println("Nombre del documento: ");
        userInput.nextLine();
        String tittle = userInput.nextLine();
        System.out.println("Año de publicacion: ");
        int age = userInput.nextInt();
        userInput.nextLine();
        boolean validTypeOfDocument = true;
        String typeDocument = "";
        do {
            System.out.println("Tipo de documento: (1-Libro, 2-Revista, 3-Artículo, 4-Paper) ");
            validTypeOfDocument= true;
            int numberDocument = userInput.nextInt();
            userInput.nextLine();

            if (numberDocument == 1) {
                typeDocument = "Libro";

            } else if (numberDocument == 2) {
                typeDocument = "Revista";

            } else if (numberDocument == 3) {
                typeDocument = "Artículo";
            } else if (numberDocument == 4) {
                typeDocument = "Paper";
            } else {
                System.out.println("Tipo de documento no valido, ingrese otro");
                validTypeOfDocument = false;

            }

        } while (!validTypeOfDocument);
        Document newDocument = new Document(documentList.size() + 1, tittle, age, typeDocument);
        documentList.add(newDocument);
        System.out.println("Documento añadido correctamente, el id del documento es: " + newDocument.getId());

    }

    private void addAuthor() {
        boolean continueAdding = true;
        do {
            System.out.println(
                    "Quieres añadir un autor o asociar un autor con un libro mediante su id? (1-Añadir, 2-Asociar, 3-Salir)");
            int option = userInput.nextInt();
            if (option == 1) {
                System.out.println("¿Cuántos autores desea añadir al sistema? ");
                int numberAuthors = userInput.nextInt();
                userInput.nextLine();
                for (int i = 0; i < numberAuthors; i++) {
                    System.out.println("Nombre del autor: ");
                    String name = userInput.nextLine();
                    System.out.println("Apellido del autor: ");
                    String surname = userInput.nextLine();
                    boolean exists = false;
                    for (Author existingAuthor : authorList) {
                        if (existingAuthor.getName().equalsIgnoreCase(name)
                                && existingAuthor.getSurname().equalsIgnoreCase(surname)) {
                            System.out.println("El autor ya existe en la base de datos.");
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        Author newAuthor = new Author(name, surname, authorList.size() + 1);
                        authorList.add(newAuthor);
                        System.out.println("Autor añadido correctamente, el id del autor es: " + newAuthor.getId());
                    }
                }
            } else if (option == 2) {
                System.out.println("Autores posibles");
                printAuthorListing();
                System.out.println("Id del autor: ");
                int authorId = userInput.nextInt();
                System.out.println("Documentos posibles");
                printIDDocument();
                System.out.println("Id del documento: ");
                int documentId = userInput.nextInt();
                boolean authorExists = false;
                for (Author existingAuthor : authorList) {
                    if (existingAuthor.getId() == authorId) {
                        authorExists = true;
                        break;
                    }
                }
                boolean documentExists = false;
                for (Document existingDocument : documentList) {
                    if (existingDocument.getId() == documentId) {
                        documentExists = true;
                        break;
                    }
                }
                if (!authorExists) {
                    System.out.println("El ID del autor no existe en la base de datos.");
                } else if (!documentExists) {
                    System.out.println("El ID del documento no existe en la base de datos.");
                } else {
                    DocumentAuthor newRelation = new DocumentAuthor(documentId, authorId);
                    addRelationDocumentAuthor(documentId, authorId);
                    System.out.println("Autor asociado correctamente");
                }
            } else if (option == 3) {
                System.out.println("Saliendo...");
                continueAdding = false;
            } else {
                System.out.println("Opcion no valida");
            }
        } while (continueAdding);

    }

    private void addKeyword() {
        boolean continueAdding = true;
        do {
            System.out.println("Quieres añadir una palabra clave o asociar una con un libro mediante su id? (1-Añadir, 2-Asociar, 3-Salir)");
            int option = userInput.nextInt();
            if (option == 1) {
                System.out.println("¿Cuántos palabras clave desea añadir al sistema? ");
                int numberKeyWords = userInput.nextInt();
                userInput.nextLine();
                for (int i = 0; i < numberKeyWords; i++) {
                    System.out.println("Palabra Clave: ");
                    String keyword = userInput.nextLine();
                    boolean exists = false;
                    for (KeyWords existingKeyWords : keyWordsList) {
                        if (existingKeyWords.getKeyWord().equalsIgnoreCase(keyword)) {
                            System.out.println("La palabra ya existe en la base de datos.");
                            exists = true;
                            break;
                        }
                    }

                    if (!exists) {
                        KeyWords keyWords = new KeyWords(keyword, authorList.size() + 1);
                        keyWordsList.add(keyWords);
                        System.out.println("Palabra clave aññadida correctamente, el id es "+ keyWords.getId());
                    }
                }
            } else if (option == 2) {
                System.out.println("Palabras clave posibles");
                printKeyWordsListing();
                System.out.println("Id de la palabra clave: ");
                int keyWordId = userInput.nextInt();
                System.out.println("Documentos posibles");
                printIDDocument();
                System.out.println("Id del documento: ");
                int documentId = userInput.nextInt();
                boolean keyWordExists = false;
                for (KeyWords existingKeyWords : keyWordsList) {
                    if (existingKeyWords.getId() == keyWordId) {
                        keyWordExists = true;
                        break;
                    }
                }
                boolean documentExists = false;
                for (Document existingDocument : documentList) {
                    if (existingDocument.getId() == documentId) {
                        documentExists = true;
                        break;
                    }
                }
                if (!keyWordExists) {
                    System.out.println("El ID de la palabra clave no existe en la base de datos.");
                } else if (!documentExists) {
                    System.out.println("El ID del documento no existe en la base de datos.");
                } else {
                    DocumentKeyWords newRelation = new DocumentKeyWords(documentId, keyWordId);
                    addRelationDocumentKeyWords(documentId, keyWordId);
                    System.out.println("Palabra Clave asociada correctamente");
                }
            } else if (option == 3) {
                System.out.println("Saliendo...");
                continueAdding = false;
            } else {
                System.out.println("Opcion no valida");
            }
        } while (continueAdding);

    }

    private void addRelationDocumentAuthor(int documentId, int authorId) {
        relationDocumentAuthors.add(new DocumentAuthor(documentId, authorId));
    }

    private void addRelationDocumentKeyWords(int documentId, int keyWordId) {
        relationDocumentKeyWords.add(new DocumentKeyWords(documentId, keyWordId));
    }

    private void printDocumentListing() {
        if (documentList.isEmpty()) {
            System.out.println("No hay documentos en el sistema");
        } else {
            for (Document document : documentList) {
                System.out.println("Id: " + document.getId());
                System.out.println("Titulo: " + document.getTittle());

                System.out.println("Año de publicacion: " + document.getPublicationYear());
                System.out.println("Tipo de documento: " + document.getTypeDocument());

            }
        }
    }

    private void printIDDocument() {
        if (documentList.isEmpty()) {
            System.out.println("No hay documentos en el sistema");
        } else {
            for (Document document : documentList) {
                System.out.println("Id: " + document.getId()+ ". Titulo: "+ document.getTittle());

            }
        }
    }

    private void printDocumentEspecific(int id) {
        if (documentList.isEmpty()) {
            System.out.println("No hay documentos en el sistema");
        } else {
            for (Document document : documentList) {
                if (document.getId() == id) {
                    System.out.println("Id: " + document.getId());
                    System.out.println("Titulo: " + document.getTittle());
                    System.out.println("Año de publicacion: " + document.getPublicationYear());
                    System.out.println("Tipo de documento: " + document.getTypeDocument());
                }
            }
        }

    }
    

    private void printAuthorListing() {
        if (authorList.isEmpty()) {
            System.out.println("No hay autores en el sistema");
        } else {
            for (Author author : authorList) {
                System.out.println("Id: " + author.getId()+ ". Nombre: " + author.getName() +author.getSurname());
            }
        }
    }

    private void printAuthorEspecific(int id) {
        if (authorList.isEmpty()) {
            System.out.println("No hay autores en el sistema");
        } else {
            for (Author author : authorList) {
                if (author.getId() == id) {
                    System.out.println("Id: " + author.getId());
                    System.out.println("Nombre: " + author.getName());
                    System.out.println("Apellido: " + author.getSurname());
                }
            }
        }
    }

    private void printKeyWordsListing() {
        if (keyWordsList.isEmpty()) {
            System.out.println("No hay palabras clave en el sistema");
        } else {
            for (KeyWords keyWord : keyWordsList) {
                System.out.println("Id: " + keyWord.getId());
                System.out.println("Palabra clave: " + keyWord.getKeyWord());

            }
        }
    }

    private void printKeyWordsEspecific(int id) {
        if (keyWordsList.isEmpty()) {
            System.out.println("No hay palabras clave en el sistema");
        } else {
            for (KeyWords keyWord : keyWordsList) {
                if (keyWord.getId() == id) {
                    System.out.println("Id: " + keyWord.getId());
                    System.out.println("Palabra clave: " + keyWord.getKeyWord());
                }
            }
        }
    }

    private void editDocument() {

        System.out.println("¿Que documento quieres editar?, introduce el id del documento");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validOption = true;
        if (documentList.get(0).getId() == id || relationDocumentAuthors.get(0).getDocumentId() == id
                || relationDocumentKeyWords.get(0).getDocumentId() == id) {

            do {
                System.out.println("Que quieres editar");
                System.out.println(
                        "1- Titulo,2- Autores, 3- Año de publicacion, 4- Tipo de documento, 5- Palabras clave, 6-Salir");
                int option = userInput.nextInt();
                if (option == 1) {
                    System.out.println("Nuevo titulo: ");
                    String newTittle = userInput.nextLine();
                    documentList.get(0).setTittle(newTittle);
                } else if (option == 2) {
                    System.out.println("¿Cuántos autores tiene el documento? ");
                    int numberAuthors = userInput.nextInt();
                    userInput.nextLine();
                    removeRelationsDocumentAuthors(id);

                    for (int i = 0; i < numberAuthors; i++) {
                        System.out.println("Nombre del autor: ");
                        String name = userInput.nextLine();
                        System.out.println("Apellido del autor: ");
                        String surname = userInput.nextLine();
                        Author author = new Author(name, surname, authorList.size() + 1);
                        authorList.add(author);
                        addRelationDocumentAuthor(documentList.get(0).getId(), author.getId());
                        System.out.println("Autor añadido correctamente, el id del autor es: " + author.getId());
                        System.out.println("");
                    }

                } else if (option == 3) {
                    System.out.println("Año de publicacion: ");
                    int age = userInput.nextInt();
                    documentList.get(0).setPublicationYear(age);
                } else if (option == 4) {
                    boolean validTypeOfDocument = true;
                    String typeDocument = "";
                    do {
                        System.out.println("Tipo de documento: (1-Libro, 2-Revista, 3-Artículo, 4-Paper) ");
                        int numberDocument = userInput.nextInt();

                        if (numberDocument == 1) {
                            typeDocument = "Libro";

                        } else if (numberDocument == 2) {
                            typeDocument = "Revista";

                        } else if (numberDocument == 3) {
                            typeDocument = "Artículo";
                        } else if (numberDocument == 4) {
                            typeDocument = "Paper";
                        } else {
                            System.out.println("Tipo de documento no valido, ingrese otro");
                            validTypeOfDocument = false;

                        }

                    } while (validTypeOfDocument = false);
                    documentList.get(0).setTypeDocument(typeDocument);
                } else if (option == 5) {
                    System.out.println("¿Cuántos palabras clave tiene el documento? ");
                    int numberKeyWords = userInput.nextInt();
                    userInput.nextLine();
                    List<DocumentKeyWords> relationsToRemove = new ArrayList<>();
                    for (DocumentKeyWords relation : relationDocumentKeyWords) {
                        if (relation.getDocumentId() == id) {
                            relationsToRemove.add(relation);
                        }
                    }
                    relationDocumentKeyWords.removeAll(relationsToRemove);

                    for (int i = 0; i < numberKeyWords; i++) {
                        System.out.println("Palabra clave: ");
                        String keyword = userInput.nextLine();
                        KeyWords keyWord = new KeyWords(keyword, keyWordsList.size() + 1);
                        keyWordsList.add(keyWord);
                        addRelationDocumentKeyWords(documentList.get(0).getId(), keyWord.getId());
                    }

                } else {
                    System.out.println("Opcion no valida");
                    validOption = false;
                }

            } while (validOption = false);
        } else {
            System.out.println("El documento no existe");

        }

    }

    private void removeRelationsDocumentAuthors(int documentId) {
        List<DocumentAuthor> relationsToRemove = new ArrayList<>();
        for (DocumentAuthor relation : relationDocumentAuthors) {
            if (relation.getDocumentId() == documentId) {
                relationsToRemove.add(relation);
            }
        }
        relationDocumentAuthors.removeAll(relationsToRemove);
    }

    private void deleteDocument() {
        System.out.println("¿Que documento quieres eliminar?, dime el id del documento");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        if (documentList.contains(id)) {
            documentList.remove(id);
        } else {
            System.out.println("El documento no existe");
        }
        removeRelationsDocumentAuthors(id);

    }

    private void deleteAuthor() {
        System.out.println("¿Que autor quieres eliminar?, dime el id del autor");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean found = false;
    for (Author author : authorList) {
        if (author.getId() == id) {
            found = true;
            authorList.remove(author);
            System.out.println("Autor eliminado correctamente");
            break; 
        }
    }
    if (!found) {
        System.out.println("El autor no existe");
    }
        removeRelationsDocumentAuthors(id);

    }

    private void searchDocument() {
        System.out.println("¿Que documento quieres buscar?, dime el id del documento");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validDocument = false;
        for (Document document : documentList) {
            if (document.getId() == id) {
                printDocumentEspecific(id);
                ;
                validDocument = true;
            }
        }
        if (validDocument == false) {
            System.out.println("El documento no existe");
        }
    }

    private void searchByAuthor() {
        System.out.println("¿Que autor quieres buscar?, dime el id del autor");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validAuthor = false;
        for (Author author : authorList) {
            if (author.getId() == id) {
                printAuthorEspecific(id);
                validAuthor = true;
            }
        }
        if (validAuthor == false) {
            System.out.println("El autor no existe");
        }
    }

    public void searchDocumentByAge() {
        System.out.println("¿Que año quieres buscar?");
        Scanner userInput = new Scanner(System.in);
        int age = userInput.nextInt();
        boolean validAge = false;
        for (Document document : documentList) {
            if (document.getPublicationYear() == age) {
                printDocumentListing();
                validAge = true;
            }
        }
        if (validAge == false) {
            System.out.println("El año no existe");
        }
    }

    private void searchDocumentByTypeOfDocument() {
        System.out.println("¿Que tipo de documento quieres buscar?");
        Scanner userInput = new Scanner(System.in);
        boolean validTypeOfDocument = true;
        String typeDocument = "";
        do {
            System.out.println("Tipo de documento: (1-Libro, 2-Revista, 3-Artículo, 4-Paper) ");
            int numberDocument = userInput.nextInt();

            if (numberDocument == 1) {
                typeDocument = "Libro";

            } else if (numberDocument == 2) {
                typeDocument = "Revista";

            } else if (numberDocument == 3) {
                typeDocument = "Artículo";
            } else if (numberDocument == 4) {
                typeDocument = "Paper";
            } else {
                System.out.println("Tipo de documento no valido, ingrese otro");
                validTypeOfDocument = false;

            }

        } while (validTypeOfDocument = false);
        boolean validType = false;
        for (Document document : documentList) {
            if (document.getTypeDocument().equals(typeDocument)) {
                printDocumentListing();
                System.out.println();
                validType = true;
            }
        }
        if (validType == false) {
            System.out.println("El tipo de documento no existe");
        }
    }

    private void searchDocumentByKeyWords() {
        System.out.println("¿Que palabra clave quieres buscar?, dime el id de la palabra clave");
        Scanner userInput = new Scanner(System.in);
        int id = userInput.nextInt();
        boolean validKeyWord = false;
        for (KeyWords keyWord : keyWordsList) {
            if (keyWord.getId() == id) {
                printKeyWordsEspecific(id);
                validKeyWord = true;
            }
        }
        if (validKeyWord == false) {
            System.out.println("La palabra clave no existe");
        }
    }

    private void printRelationsDocumentAuthors() {
        for (DocumentAuthor relation : relationDocumentAuthors) {
            int documentId = relation.getDocumentId();
            int authorId = relation.getAuthorId();

            String documentTitle = "";
            for (Document document : documentList) {
                if (document.getId() == documentId) {
                    documentTitle = document.getTittle();
                    break;
                }
            }

            String authorName = "";
            for (Author author : authorList) {
                if (author.getId() == authorId) {
                    authorName = author.getName() + " " + author.getSurname();
                    break;
                }
            }

            System.out.println("Documento: " + documentTitle + ", Autor: " + authorName);
        }
    }

    private void printCompleteDocumentList() {
        for (Document document : documentList) {
            System.out.println("Id: " + document.getId());
            System.out.println("Titulo: " + document.getTittle());
            System.out.println("Año de publicacion: " + document.getPublicationYear());
            System.out.println("Tipo de documento: " + document.getTypeDocument());
            System.out.println("Autores: ");
            for (DocumentAuthor relation : relationDocumentAuthors) {
                if (relation.getDocumentId() == document.getId()) {
                    for (Author author : authorList) {
                        if (author.getId() == relation.getAuthorId()) {
                            System.out.println("Nombre: " + author.getName() + author.getSurname());
                        }
                    }
                }
            }
            System.out.println("Palabras clave: ");
            for (DocumentKeyWords relation : relationDocumentKeyWords) {
                if (relation.getDocumentId() == document.getId()) {
                    for (KeyWords keyWord : keyWordsList) {
                        if (keyWord.getId() == relation.getKeyWordId()) {
                            System.out.println("Palabra clave: " + keyWord.getKeyWord());
                        }
                    }
                }
            }
        }
    }

    public void menu() {
        boolean continueMenu = true;
        do {
            try {
            System.out.println("Menu");
            System.out.println("1- Añadir documento");
            System.out.println("2- Añadir/asociar autor");
            System.out.println("3- Añadir/asociar palabra clave");
            System.out.println("4- Editar documento");
            System.out.println("5- Eliminar documento");
            System.out.println("6- Eliminar autor");
            System.out.println("7- Buscar documento");
            System.out.println("8- Buscar autor");
            System.out.println("9- Buscar documento por palabra clave");
            System.out.println("10- Listar documentos");
            System.out.println("11- Listar autores");
            System.out.println("12- Listar palabras clave");
            System.out.println("13- Listar relaciones documento-autores");
            System.out.println("14- Listar lista completa de documentos");
            System.out.println("15- Salir");
            int option = userInput.nextInt();
            switch (option) {
                case 1:
                    addDocument();
                    break;
                case 2:
                    addAuthor();
                    break;
                case 3:
                    addKeyword();
                    break;
                case 4:
                    editDocument();
                    break;
                case 5:
                    deleteDocument();
                    break;
                case 6:
                    deleteAuthor();
                    break;
                case 7:
                    searchDocument();
                    break;
                case 8:
                    searchByAuthor();
                    break;
                case 9:
                    searchDocumentByKeyWords();
                    break;
                case 10:
                    printDocumentListing();
                    break;
                case 11:
                    printAuthorListing();
                    break;
                case 12:
                    printKeyWordsListing();
                    break;
                case 13:
                    printRelationsDocumentAuthors();
                    break;
                case 14:
                    printCompleteDocumentList();
                    break;
                case 15:
                    System.out.println("Saliendo...");
                    continueMenu = false;
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }catch (Exception e) {
            System.out.println("Entrada inválida. Por favor, introduzca un número.");
            userInput.nextLine();
        }
        } while (continueMenu);
    }

}
