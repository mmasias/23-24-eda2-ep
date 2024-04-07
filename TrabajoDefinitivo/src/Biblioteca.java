import java.time.Year;
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
            System.out.println("1. Gestionar Biblioteca");
            System.out.println("2. Buscar Documentos");
            System.out.println("3. Salir del gestor");
            int eleccion = scanner.nextInt();
            scanner.nextLine();
            switch (eleccion) {
                case 1:
                    gestionarBiblioteca();
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

    private void gestionarBiblioteca() {
        boolean crud = true;
        while (crud) {
            System.out.println("---------------------------Gestion de Biblioteca---------------------------");
            System.out.println("1. Añadir Documento");
            System.out.println("2. Editar Documento");
            System.out.println("3. Eliminar Documento");
            System.out.println("4. Volver");
            System.out.println("----------------------------------------------------------------------------");
            int eleccion = scanner.nextInt();
            scanner.nextLine();
            switch (eleccion) {
                case 1:
                    crearDocumento();
                    break;
                case 2:
                    editarDocumento();
                    break;
                case 3:
                    borrarDocumento();
                    break;
                case 4:
                    crud = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void crearDocumento() {
        System.out.println("--------------------Creacion de Documento--------------------");
        System.out.println("Introduce título del documento:");
        String tituloDelDoc = scanner.nextLine();

        System.out.println("Introduce Nombre del autor o autores (separados por comas):");
        String nombreAutor = scanner.nextLine();
        ArrayList<String> autoresDelDoc = new ArrayList<>(Arrays.asList(nombreAutor.split("\\s*,\\s*")));
        if(autoresDelDoc.isEmpty()) {
            autoresDelDoc.add("Autor Desconocido");
        }

        System.out.println("Introduce el año del documento:");
        int anoDelDoc = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Introduce el tipo de documento:");
        String tipoDeDoc = scanner.nextLine();

        System.out.println("Introduce palabras clave del documento (separadas por comas):");
        String palabrasClave = scanner.nextLine();
        ArrayList<String> palabrasClaveDelDoc = new ArrayList<>(Arrays.asList(palabrasClave.split("\\s*,\\s*")));

        System.out.println("Documento Creado!");
        anadirDocumento(new Documento(tituloDelDoc, autoresDelDoc, Year.of(anoDelDoc), tipoDeDoc, palabrasClaveDelDoc));
    }

    private void editarDocumento() {
        System.out.println("---------------------Edicion de Documento--------------------");
        System.out.println("Introduce el título del documento a editar:");
        String docAEditar = scanner.nextLine();

        Documento documento = buscarPorTitulo(docAEditar);
        if (documento == null) {
            System.out.println("No existe tal documento en la biblioteca");
        } else {
            System.out.println("---------------------Edicion de Documento--------------------");
            System.out.println("Documento seleccionado: ");
            documento.imprimirDetalleDocumento();
            System.out.println("¿Qué deseas modificar? ");
            System.out.println("1. Título");
            System.out.println("2. Autores");
            System.out.println("3. Año de publicación");
            System.out.println("4. Tipo de documento");
            System.out.println("5. Palabras clave");
            System.out.println("--------------------------------------------------------------");

            int eleccion = scanner.nextInt();
            scanner.nextLine();

            switch (eleccion) {
                case 1:
                    System.out.println("Introduce el nuevo título:");
                    String nuevoTitulo = scanner.nextLine();
                    documento.setTitulo(nuevoTitulo);
                    break;
                case 2:
                    System.out.println("Introduce los nuevos autores (separados por comas):");
                    String nuevosAutores = scanner.nextLine();
                    ArrayList<String> autores = new ArrayList<>(Arrays.asList(nuevosAutores.split("\\s*,\\s*")));
                    documento.setAutores(autores);
                    break;
                case 3:
                    System.out.println("Introduce el nuevo año de publicación:");
                    int nuevoAno = scanner.nextInt();
                    documento.setPublicacion(Year.of(nuevoAno));
                    break;
                case 4:
                    System.out.println("Introduce el nuevo tipo de documento:");
                    String nuevoTipo = scanner.nextLine();
                    documento.setTipoDocumento(nuevoTipo);
                    break;
                case 5:
                    System.out.println("Introduce las nuevas palabras clave (separadas por comas):");
                    String nuevasPalabrasClave = scanner.nextLine();
                    ArrayList<String> palabrasClave = new ArrayList<>(Arrays.asList(nuevasPalabrasClave.split("\\s*,\\s*")));
                    documento.setPalabrasClave(palabrasClave);
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
    }

    private void borrarDocumento(){
        System.out.println("------------------Eliminacion de Documento-------------------");
        System.out.println("Introduce título del documento a eliminar:");
        String docABorrar = scanner.nextLine();
        borrarDocumento(docABorrar);
    }

    private void buscarDocumentos() {
        System.out.println("------------------Busqueda de Documento----------------------");
        System.out.println("1. Buscar por título");
        System.out.println("2. Buscar por autor");
        System.out.println("3. Buscar por tipo de documento");
        System.out.println("4. Buscar por año de publicación");
        System.out.println("5. Buscar por palabras clave");
        System.out.println("6. Mostrar todos");
        System.out.println("-------------------------------------------------------------");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                System.out.println("Introduce el título del documento:");
                String tituloBusqueda = scanner.nextLine();
                ArrayList<Documento> documentosPorTitulo = new ArrayList<>();
                documentosPorTitulo.add(buscarPorTitulo(tituloBusqueda));
                mostrarDocumentosEncontrados(documentosPorTitulo);
                break;
            case 2:
                System.out.println("Introduce el nombre del autor:");
                String autorBusqueda = scanner.nextLine();
                ArrayList<Documento> documentosPorAutor = buscarPorAutor(autorBusqueda);
                mostrarDocumentosEncontrados(documentosPorAutor);
                break;
            case 3:
                System.out.println("Introduce el tipo de documento:");
                String tipoBusqueda = scanner.nextLine();
                Documento documentoPorTipo = buscarPorTipo(tipoBusqueda);
                if (documentoPorTipo != null) {
                    System.out.println("Documento encontrado:");
                    documentoPorTipo.imprimirDetalleDocumento();
                } else {
                    System.out.println("No se encontraron documentos con el tipo especificado.");
                }
                break;
            case 4:
                System.out.println("Introduce el año de publicación:");
                int anoBusqueda = scanner.nextInt();
                ArrayList<Documento> documentosPorAno = buscarPorAno(anoBusqueda);
                mostrarDocumentosEncontrados(documentosPorAno);
                break;
            case 5:
                System.out.println("Introduce la palabra clave:");
                String palabraClaveBusqueda = scanner.nextLine();
                ArrayList<Documento> documentosPorPalabraClave = buscarPorPalabrasClave(palabraClaveBusqueda);
                mostrarDocumentosEncontrados(documentosPorPalabraClave);
                break;
            case 6:
                System.out.println("Mostrando todos los documentos");
                mostrarDocumentosEncontrados(todoDocumentos());
                break;
            default:
                System.out.println("Opción no válida.");
        }
    }

    private void mostrarDocumentosEncontrados(ArrayList<Documento> documentos) {
        if (documentos.isEmpty()) {
            System.out.println("No se ha encontrado ningún documento que cumpla con el criterio de búsqueda.");
        } else {
            System.out.println("Documentos encontrados:" + documentos.size());
            for (Documento documento : documentos) {
                documento.imprimirDetalleDocumento();
            }
        }
    }

    public void anadirDocumento(Documento documento){
        documentos.add(documento);
        System.out.println("Documento añadido!");
    }

    public void borrarDocumento(String titulo){
        Documento documento = buscarPorTitulo(titulo);
        if (documento == null){
            System.out.println("No existe tal documento en la biblioteca");
        } else {
            documentos.remove(documento);
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
            if (doc.getPublicacion().getValue() == year){
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
        return new ArrayList<>(documentos);
    }
}
