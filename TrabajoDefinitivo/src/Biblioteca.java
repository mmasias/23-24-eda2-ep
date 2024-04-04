import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Biblioteca {

    private ArrayList<Documento> documentos;
    private ArrayList<Autor> autores;
    private Scanner scanner;

    public Biblioteca() {
        documentos = new ArrayList<>();
        autores = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void iniciarGestionBiblioteca() {
        boolean gestionando = true;
        while (gestionando) {
            System.out.println("Elige una opción:");
            System.out.println("1. Gestionar Documentos");
            System.out.println("2. Buscar Documentos");
            System.out.println("3. Salir del gestor");
            int eleccion = scanner.nextInt();
            scanner.nextLine(); 
            switch (eleccion) {
                case 1:
                    gestionarDocumentos();
                    break;
                case 2:
                    buscarDocumentos();
                    break;
                case 3:
                    gestionando = false;
                    System.out.println("Saliendo del gestor de biblioteca...");
                    break;
                default:
                    System.out.println("Introduce una opción válida.");
            }
        }
    }
    private void gestionarDocumentos() {
        System.out.println("Gestionando Documentos");
        // Implementa la gestión de documentos aquí
    }

    private void buscarDocumentos() {
        System.out.println("Buscando Documentos");
        // Implementa la búsqueda de documentos aquí
    }

    public void anadirDocumento(Documento documento){
        documentos.add(documento);
        System.out.println("Documento añadido!");
    }

    public void borrarDocumento(String titulo){
        if(buscarPorTitulo(titulo)==null){
            System.out.println("No existe tal documento en la biblioteca");
        } else {
            documentos.removeIf(documento -> documento.getTitulo().equalsIgnoreCase(titulo));
            System.out.println("Documento borrado");
        }
    }

    public Documento buscarPorTitulo(String titulo){
        for (Documento doc : documentos) {
            if(doc.getTitulo().equalsIgnoreCase(titulo)) {
                return doc;
            }
        }
        return null;
    }

    public ArrayList<Documento> buscarPorAutor(String autor){
        ArrayList<Documento> docs = new ArrayList<>();
        for (Documento doc : documentos){
            for (String aut : doc.getAutores()){
                if (aut.equalsIgnoreCase(autor)){
                    docs.add(doc);
                }
            }
        }
        return docs;
    }

    public Documento buscarPorTipo(String tipo){
        for (Documento doc : documentos) {
            if(doc.getTipoDocumento().equalsIgnoreCase(tipo)) {
                return doc;
            }
        }
        return null;
    }

    public ArrayList<Documento> buscarPorAno(int year){
        ArrayList<Documento> docs = new ArrayList<>();
        for (Documento doc : documentos){
            if (doc.getPublicacion().getValue()==year){
                docs.add(doc);
            }
        }
        return docs;
    }

    public ArrayList<Documento> buscarPorPalabrasClave(String palabra){
        ArrayList<Documento> docs = new ArrayList<>();
        for (Documento doc : documentos){
            for (String aut : doc.getPalabrasClave()){
                if (aut.equalsIgnoreCase(palabra)){
                    docs.add(doc);
                }
            }
        }
        return docs;
    }



   

    public ArrayList<Documento> todoDocumentos() {
        return documentos;
    }
}
