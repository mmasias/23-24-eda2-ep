import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Documento> documentos;
    private ArrayList<Autor> autores;
    private ArrayList<Relacion> relaciones;
    Scanner scanner = new Scanner(System.in);

    public Gestion() {
        this.documentos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.relaciones = new ArrayList<>();
    }

    public void agregarDocumento(Documento documento) {
        documentos.add(documento);
        System.out.println("Documento añadido. Deseas agregarle autores? (s/n)");
        String respuesta = scanner.nextLine();
        if ("s".equalsIgnoreCase(respuesta)) {
            agregarAutor(documento);
        }
    }

    public void agregarDocumento() {
        System.out.println("Ingrese el título del documento: ");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese el año del documento: ");
        int año = scanner.nextInt();
        Tipo tipo = null;
        try {
            System.out.println("Ingrese el tipo del documento (LIBRO, REVISTA, ARTICULO, PAPER): ");
            tipo = Tipo.valueOf(scanner.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo de documento no válido. Por favor, ingrese uno de los siguientes: LIBRO, REVISTA, ARTICULO, PAPER");
        }
        scanner.nextLine();
        System.out.println("Ingrese las palabras clave del documento (separadas por comas): ");
        String palabrasClave = scanner.nextLine();
        String[] palabrasClaveArray = palabrasClave.split(",");
        ArrayList<String> palabrasClaveList = new ArrayList<>();
        for (String palabra : palabrasClaveArray) {
            palabrasClaveList.add(palabra.trim());
        }
        Documento documento = new Documento(documentos.size() + 1, titulo, año, palabrasClaveList, tipo);
        agregarDocumento(documento);
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public void agregarAutor(Documento documento) {
        boolean agregarAutor = true;
        while (agregarAutor) {
            System.out.println("Selecciona el ID del autor para asociar con el libro, o introduce 'nuevo' para añadir un nuevo autor:");
            listarAutores();
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre del nuevo autor:");
                String nombre = scanner.nextLine();
                System.out.println("Introduce el apellido del nuevo autor:");
                String apellido = scanner.nextLine();
                Autor nuevoAutor = new Autor(autores.size() + 1, nombre, apellido);
                agregarAutor(nuevoAutor);
                añadirRelacion(documento.getId(), nuevoAutor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    añadirRelacion(documento.getId(), authorId);
                    System.out.println("Autor asociado al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otro autor a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                agregarAutor = !agregarAutor;
            }
        }
    }

    public void listarDocumentos() {
        for (Documento documento : documentos) {
            System.out.println(documento);
        }
        System.out.println("-------------------------");
    }

    public void listarAutores() {
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    Documento buscarDocumentoPorId(int id) {
        for (Documento documento : documentos) {
            if (documento.getId() == id) {
                return documento;
            }
        }
        return null;
    }

    Autor buscarAutorPorId(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    ArrayList<Autor> buscarAutoresPorId(int idDocumento){
        ArrayList<Autor> autores = new ArrayList<>();

        for (Relacion relacion : relaciones) {
            if (relacion.getIdLibro() == idDocumento) {
                autores.add(buscarAutorPorId(relacion.getIdAutor()));
            }
            
        }
        return autores;
    }

    ArrayList<Documento> buscarDocumentosPorId(int idAutor){
        ArrayList<Documento> documentos = new ArrayList<>();

        for (Relacion relacion : relaciones) {
            if (relacion.getIdAutor() == idAutor) {
                documentos.add(buscarDocumentoPorId(relacion.getIdLibro()));
            }
            
        }
        return documentos;
    }

    public void modificar(Documento documento, String titulo, int año, ArrayList<Autor> autores,
            ArrayList<String> palabrasClave, Tipo tipo) {
        documento.setTitulo(titulo);
        documento.setAño(año);
        documento.setPalabrasClave(palabrasClave);
        documento.setTipo(tipo);
    }

    /*public ArrayList<Documento> buscar(String criterio, String valor) {
        ArrayList<Documento> resultado = new ArrayList<>();
        try {
            for (Documento doc : documentos) {
                switch (criterio.toLowerCase()) {
                    case "titulo":
                        if (doc.getTitulo().toLowerCase().contains(valor.toLowerCase())) {
                            resultado.add(doc);
                        }
                        break;
                    case "autor":
                        for (Autor autor : doc.getAutores()) {
                            if (autor.getNombre().toLowerCase().contains(valor.toLowerCase()) ||
                                    autor.getApellido().toLowerCase().contains(valor.toLowerCase())) {
                                resultado.add(doc);
                                break;
                            }
                        }
                        break;
                    case "anio":
                        if(doc.getAño()==Integer.parseInt(valor)){
                            resultado.add(doc);
                        }
                        break;
                    case "tipo":
                        if (doc.getTipo().toString().toLowerCase().equals(valor.toLowerCase())) {
                            resultado.add(doc);
                        }
                        break;
                    case "palabras clave":
                        for (String palabraClave : doc.getPalabrasClave()) {
                            if (palabraClave.toLowerCase().contains(valor.toLowerCase())) {
                                resultado.add(doc);
                                break;
                            }
                        }
                        break;
                    default:
                        System.out.println("Criterio de búsqueda no válido.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar documentos: " + e.getMessage());
        }
        return resultado;
    }
*/
    public void eliminarDocumento(Documento documento) {
        try {
            documentos.remove(documento);
        } catch (Exception e) {
            System.out.println("Error al eliminar documento: " + e.getMessage());
        }
    }

    public void eliminarDocumento() {
        listarDocumentos();
        System.out.print("Ingrese el ID del documento que desea eliminar: ");
        int id = scanner.nextInt();
        Documento documento = buscarDocumentoPorId(id);
        if (documento != null) {
            eliminarDocumento(documento);
            System.out.println("Documento eliminado.");
        } else {
            System.out.println("Documento no encontrado.");
        }
    }

    public void eliminarAutor(Autor autor) {
        try {
            autores.remove(autor);
        } catch (Exception e) {
            System.out.println("Error al eliminar autor: " + e.getMessage());
        }
    }

    public void eliminarAutor() {
        listarAutores();
        System.out.print("Ingrese el ID del autor que desea eliminar: ");
        int id = scanner.nextInt();
        Autor autor = buscarAutorPorId(id);
        if (autor != null) {
            eliminarAutor(autor);
            System.out.println("Autor eliminado.");
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    private void mostrarMenu(Gestion biblioteca) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            try {
                System.out.println("\u001B[31m" + "\nBiblioteca de Documentos" + "\u001B[0m");
                System.out.println("1. Agregar documento");
                System.out.println("2. Listar documentos");
                System.out.println("3. Listar autores");
                System.out.println("4. Eliminar documento");
                System.out.println("5. Eliminar autor");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        biblioteca.agregarDocumento();
                        break;
                    case 2:
                        biblioteca.listarDocumentos();
                        break;
                    case 3:
                        biblioteca.listarAutores();
                        break;
                    case 4:
                        biblioteca.eliminarDocumento();
                        break;
                    case 5:
                        biblioteca.eliminarAutor();
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Número no válido. Intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Formato de entrada no válido. Intente de nuevo.");
                scanner.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        Gestion biblioteca = new Gestion();
        biblioteca.mostrarMenu(biblioteca);
        
    }

    /* 
    private static void buscarDocumento(Gestion biblioteca, Scanner scanner) {
        System.out.print("Ingrese el criterio de búsqueda (titulo, autor, anio, tipo, palabras clave): ");
        String criterio = scanner.nextLine();
        System.out.print("Ingrese el valor a buscar: ");
        String valor = scanner.nextLine();

        ArrayList<Documento> resultado = biblioteca.buscar(criterio, valor);

        if (resultado.isEmpty()) {
            System.out.println("No se encontraron documentos que coincidan con la búsqueda.");
        } else {
            System.out.println("Documentos encontrados:");
            for (Documento doc : resultado) {
                System.out.println("-------------------------");
                System.out.println("ID: " + doc.getId());
                System.out.println("Título: " + doc.getTitulo());
                System.out.println("Tipo: " + doc.getTipo());
                System.out.println("Año: " + doc.getAño());
                System.out.println("Palabras Clave: " + doc.getPalabrasClave().toString());

            }
            System.out.println("-------------------------");
        }
    }
    */
    public void añadirRelacion(int idDocumento, int idAutor) {
        Relacion relacion = new Relacion(idDocumento, idAutor);
        relaciones.add(relacion);
    }
}