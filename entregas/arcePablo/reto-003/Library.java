import java.sql.Date;
import java.util.ArrayList;
import java.util.Scanner;

import Types.DocType;
import Types.KeyWordTypes;
public class Library {
    private ArrayList<Document> documents;

    public Library() {
        documents = new ArrayList<>();
    }

    public ArrayList<Document> getDocuments() {
        return documents;
    }

    public void addDocument(Document document) {
        documents.add(document);
    }

    public Document readDocument(int index) {
        if (index >= 0 && index < documents.size()) {
            return documents.get(index);
        }
        return null;
    }

    public void updateDocument(int index, Document updatedDocument) {
        if (index >= 0 && index < documents.size()) {
            documents.set(index, updatedDocument);
        }
    }

    public Document updateToNewDocument(int index) {
        if (index >= 0 && index < documents.size()) {
            Document docToUpdate = documents.get(index);
            Scanner scanner = new Scanner(System.in);
            
            System.out.println("Actualización del Documento:");
            System.out.println("1. Título: " + docToUpdate.getTitle());
            System.out.println("2. Autor: " + docToUpdate.getAuthor().getName());
            System.out.println("3. Palabras Clave: " + docToUpdate.getKeyWords());
            System.out.println("4. Fecha de Publicación: " + docToUpdate.getDatePublished());
            System.out.println("5. Tipo de Documento: " + docToUpdate.getType());
            System.out.println("Seleccione el número del campo que desea actualizar (o ingrese 0 para salir):");
            
            int choice = scanner.nextInt();
            scanner.nextLine();             
            switch (choice) {
                case 1:
                    System.out.println("Ingrese el nuevo título:");
                    String newTitle = scanner.nextLine();
                    docToUpdate.setTitle(newTitle);
                    break;
                case 2:
                    System.out.println("Ingrese el nuevo autor:");
                    String newAuthor = scanner.nextLine();
                    docToUpdate.getAuthor().setName(newAuthor);
                    break;
                case 3:
                    System.out.println("Ingrese las nuevas palabras clave (separadas por comas):");
                    String newKeywordsInput = scanner.nextLine();
                    String[] newKeywordsArray = newKeywordsInput.split(",");
                    ArrayList<KeyWordTypes> newKeywords = new ArrayList<>();
                    for (String keyword : newKeywordsArray) {
                        newKeywords.add(KeyWordTypes.valueOf(keyword.trim()));
                    }
                    docToUpdate.setKeyWords(newKeywords);
                    break;
                case 4:
                    System.out.println("Ingrese la nueva fecha de publicación (en formato yyyy-mm-dd):");
                    String newDateInput = scanner.nextLine();
                    Date newDatePublished = Date.valueOf(newDateInput);
                    docToUpdate.setDatePublished(newDatePublished);
                    break;
                case 5:
                    System.out.println("Seleccione el nuevo tipo de documento:");
                    for (DocType type : DocType.values()) {
                        System.out.println(type.ordinal() + ". " + type);
                    }
                    int typeChoice = scanner.nextInt();
                    docToUpdate.setType(DocType.values()[typeChoice]);
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    return docToUpdate; 
                default:
                    System.out.println("Opción no válida. Saliendo...");
                    return docToUpdate;
            }

            return docToUpdate; 
        } else {
            System.out.println("Índice fuera de rango. No se puede actualizar el documento.");
            return null;
        }
    }

    public int getDocumentCount() {
        return documents.size();
    }

    public int getLastDocumentId() {
        if (documents.size() > 0) {
            return documents.get(documents.size() - 1).getDocumentId();
        }
        return 0;
    }

    public int searchDocumentByID(int id) {
        for (int i = 0; i < documents.size(); i++) {
            if (documents.get(i).getDocumentId() == id) {
                return i;
            }
        }
        return -1;
    }

    public void deleteDocument(int index) {
        if (index >= 0 && index < documents.size()) {
            documents.remove(index);
        }
    }
}
