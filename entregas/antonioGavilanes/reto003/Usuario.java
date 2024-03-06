package antonioGavilanes.reto003;

import java.util.Scanner;

class Usuario {

    private String name;
    private DocumentoNode first;

    public Usuario(String name) {
        this.name = name;
        this.first = null;
    }

    private void addDoc(Documento food) {
        DocumentoNode newDocumentoNode = new DocumentoNode(food);
        if (first == null) {
            first = newDocumentoNode;
        } else {
            DocumentoNode current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newDocumentoNode);
        }
    }

    private void createDoc() {
        boolean creating = true;
        Scanner userInput = new Scanner(System.in);
        while (creating) {
            System.out.println("Nombre del documento por crear (-1 para terminar)");
            String docName = userInput.nextLine();
            if (docName.equals("-1")) {
                creating = !creating;
            } else {
                System.out.println("Autor del documento");
                String docAuthor = userInput.nextLine();
                System.out.println("Año del documento");
                String docYear = userInput.nextLine();
                System.out.println("Tipo del documento");
                String docType = userInput.nextLine();
                System.out.println("Palabras clave del documento");
                String docKeywords = userInput.nextLine();
                Documento newDoc = new Documento(docName, docAuthor, docYear, docType, docKeywords);
                addDoc(newDoc);
            }
        }
    }

    private void editDoc() {
        boolean editing = true;
        Scanner userInput = new Scanner(System.in);
        while (editing) {
            System.out.println("Nombre del documento por editar (-1 para terminar)");
            String docName = userInput.nextLine();
            if (docName.equals("-1")) {
                editing = !editing;
            } else {
                editDoc(docName);
            }
        }
    }

    private void editDoc(String docName) {
        DocumentoNode current = first;
        while (current != null) {
            if (current.getDoc().getTitulo().equals(docName)) {
                System.out.println("Nuevo nombre del documento");
                String newDocName = new Scanner(System.in).nextLine();
                current.getDoc().setTitulo(newDocName);
                return;
            }
            current = current.getNext();
        }
        System.out.println("No se encontró el documento " + docName);
    }

    private void deleteDoc() {
        boolean deleting = true;
        Scanner userInput = new Scanner(System.in);
        while (deleting) {
            System.out.println("Nombre del documento por eliminar (-1 para terminar)");
            String docName = userInput.nextLine();
            if (docName.equals("-1")) {
                deleting = !deleting;
            } else {
                removeDoc(docName);
            }
        }
    }

    private void removeDoc(String docName) {
        if (first.getDoc().getTitulo().equals(docName)) {
            first = first.getNext();
        } else {
            DocumentoNode current = first;
            while (current.getNext() != null) {
                if (current.getNext().getDoc().getTitulo().equals(docName)) {
                    current.setNext(current.getNext().getNext());
                    return;
                }
                current = current.getNext();
            }
        }
    }

    private void startManagement() {
        manage();
    }

    public void manage() {
        boolean managing = true;
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.println("1. Crear documento");
            System.out.println("2. Editar documento");
            System.out.println("3. Eliminar documento");
            System.out.println("4. Ver documentos");
            System.out.println("5. Salir");
            int option = userInput.nextInt();
            switch (option) {
                case 1:
                    createDoc();
                    break;
                case 2:
                    editDoc();
                    break;
                case 3:
                    deleteDoc();
                    break;
                case 4:
                    System.out.println(this);
                    break;
                case 5:
                    managing = !managing;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (managing);
    }

    @Override
    public String toString() {
        String result = "";
        DocumentoNode current = first;
        while (current != null) {
            System.out.println("====================================");
            System.out.println(result + "Documento: " + current.getDoc().getTitulo());
            System.out.println("====================================");
            System.out.println("Autor: " + current.getDoc().getAutor());
            System.out.println("Año: " + current.getDoc().getAño());
            System.out.println("Tipo: " + current.getDoc().getTipo());
            System.out.println("Palabras clave: " + current.getDoc().getPalabrasClave());
            current = current.getNext();
        }
        return result;
    }

    public static void main(String[] args) {
        Usuario user = new Usuario("Antonio");
        user.startManagement();
    }

}