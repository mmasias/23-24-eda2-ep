package entregas.diestroPaula.Reto003;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;
import java.util.List;

public class Gestion {
    private List<AutorDocumento> relaciones;
    private List<Autor> autores;
    private List<Documento> documentos;
    private Scanner scanner;

    public Gestion() {
        documentos = new ArrayList<>();
        autores = new ArrayList<>();
        relaciones = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion;
        do {
            System.out.println("Menú:");
            System.out.println("1. Agregar nuevo documento");
            System.out.println("2. Mostrar todos los documentos");
            System.out.println("3. Mostrat todos los autores");
            System.out.println("4. Editar documento");
            System.out.println("5. Eliminar documento");
            System.out.println("6. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarDocumento();
                    break;
                case 2:
                    mostrarDocumentos();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    editarDocumento(opcion, null, opcion, null);
                    ;
                    break;
                case 5:
                    eliminarDocumento(opcion);
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.");
            }
        } while (opcion != 6);
    }

    public void agregarDocumento() {
        System.out.println("Ingrese el título del documento:");
        String titulo = scanner.nextLine();
        System.out.println("Introduzca el año de publicación:");
        int año = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduzca el tipo de libro:");
        String tipo = scanner.nextLine();

        Documento nuevoDocumento = new Documento(documentos.size() + 1, titulo, año, tipo);
        agregarDocumento(nuevoDocumento);
    }

    private void agregarDocumento(Documento documento) {
        documentos.add(documento);
        System.out.println("Documento añadido. ¿Desea añadir autores a este documento? (s/n)");
        String respuesta = scanner.nextLine();
        if ("s".equalsIgnoreCase(respuesta)) {
            agregarAutor(documento);
        }
    }

    public void editarDocumento(int idDocumento, String nuevoTitulo, int nuevoAñoPublicacion, String nuevoTipo) {
        Documento documento = buscarDocumentoId(idDocumento);
        if (documento != null) {
            documento.setTitulo(nuevoTitulo);
            documento.setAñoPublicaion(nuevoAñoPublicacion);
            documento.setTipo(nuevoTipo);
            System.out.println("Documento editado con éxito.");
        } else {
            System.out.println("Documento no encontrado.");
        }
    }

    public void eliminarDocumento(int idDocumento) {
        Iterator<Documento> iterator = documentos.iterator();
        while (iterator.hasNext()) {
            Documento documento = iterator.next();
            if (documento.getId() == idDocumento) {
                iterator.remove();
                System.out.println("Documento eliminado con éxito.");
                return;
            }
        }
        System.out.println("Documento no encontrado.");
    }

    public void mostrarDocumentos() {
        System.out.println("Lista de Documentos:");
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos para mostrar.");
        } else {
            for (Documento documento : documentos) {
                System.out.println(documento);
                System.out.println(getAutoresIdDocumento(documento.getId()));
            }
        }
    }

    private void agregarAutor(Documento documento) {
        boolean añadiendoAutores = true;
        while (añadiendoAutores) {
            System.out.println(
                    "Seleccione el ID del autor para asociar con el documento, o introduzca 'nuevo' para añadir un nuevo autor:");
            mostrarAutores();
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduzca el nombre del nuevo autor:");
                String nombre = scanner.nextLine();
                Autor nuevoAutor = new Autor(autores.size() + 1, nombre);
                agregarAutor(nuevoAutor);
                agregarRelacion(documento.getId(), nuevoAutor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int idAutor = Integer.parseInt(input);
                    agregarRelacion(documento.getId(), idAutor);
                    System.out.println("Autor asociado al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Desea añadir otro autor a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                añadiendoAutores = !añadiendoAutores;
            }
        }
    }

    private void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    private void mostrarAutores() {
        if (autores.isEmpty()) {
            System.out.println("No hay autores para listar.");
        } else {
            System.out.println("Lista de Autores:");
            for (Autor autor : autores) {
                System.out.println("ID: " + autor.getId() + ", Nombre: " + autor.getNombre());
            }
        }
    }

    private void agregarRelacion(int idDocumento, int idAutor) {
        relaciones.add(new AutorDocumento(idDocumento, idAutor));
    }

    private List<Autor> getAutoresIdDocumento(int idDocumento) {
        List<Autor> resultado = new ArrayList<>();
        for (AutorDocumento relacion : relaciones) {
            if (relacion.getIdDocumento() == idDocumento) {
                resultado.add(buscarAutorId(relacion.getIdAutor()));
            }
        }
        return resultado;
    }

    private List<Documento> getDocumentosIdAutor(int idAutor) {
        List<Documento> resultado = new ArrayList<>();
        for (AutorDocumento relacion : relaciones) {
            if (relacion.getIdAutor() == idAutor) {
                resultado.add(buscarDocumentoId(relacion.getIdDocumento()));
            }
        }
        return resultado;
    }

    private Documento buscarDocumentoId(int idDocumento) {
        for (Documento documento : documentos) {
            if (documento.getId() == idDocumento) {
                return documento;
            }
        }
        return null;
    }

    private Autor buscarAutorId(int idAutor) {
        for (Autor autor : autores) {
            if (autor.getId() == idAutor) {
                return autor;
            }
        }
        return null;
    }
}
