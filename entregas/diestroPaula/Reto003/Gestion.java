package entregas.diestroPaula.Reto003;

import java.util.ArrayList;
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
            System.out.println("2. Buscar documento");
            System.out.println("3. Mostrar todos los documentos");
            System.out.println("4. Mostrat todos los autores");
            System.out.println("5. Editar documento");
            System.out.println("6. Eliminar documento");
            System.out.println("7. Salir");
            System.out.print("Ingrese su opción: ");
            opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {
                case 1:
                    agregarDocumento();
                    break;
                case 2:
                    buscarDocumento();
                    break;
                case 3:
                    mostrarDocumentos();
                    break;
                case 4:
                    mostrarAutores();
                    break;
                case 5:
                    editarDocumento();
                    break;
                case 6:
                    eliminarDocumento();
                    break;
                case 7:
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

    public void editarDocumento() {
        System.out.println("\nIngrese el título del documento a editar:");
        String tituloBusqueda = scanner.nextLine();
        boolean encontrado = false;

        for (Documento documento : documentos) {
            if (documento.getTitulo().equalsIgnoreCase(tituloBusqueda)) {
                System.out.println("Documento encontrado:");
                documento.mostrarDetalles();
                encontrado = true;

                System.out.println("¿Qué dato desea editar?");
                System.out.println("1. Título");
                System.out.println("2. Autores");
                System.out.println("3. Año de publicación");
                System.out.println("4. Palabras clave");
                System.out.println("5. Tipo de documento");
                System.out.print("Ingrese su opción: ");
                int opcion = Integer.parseInt(scanner.nextLine());

                switch (opcion) {
                    case 1:
                        System.out.println("Ingrese el nuevo título:");
                        String nuevoTitulo = scanner.nextLine();
                        documento.setTitulo(nuevoTitulo);
                        break;
                    case 2:
                        ArrayList<Autor> autores = new ArrayList<>();
                        boolean añadiendoAutores = true;
                        while (añadiendoAutores) {
                            System.out.println("Ingrese el nombre del autor (-1 para terminar):");
                            String nombreAutor = scanner.nextLine();
                            if (nombreAutor.equalsIgnoreCase("-1")) {
                                añadiendoAutores = false;
                            } else {
                                autores.add(new Autor(nombreAutor));
                            }
                        }
                        break;
                    case 3:
                        System.out.println("Ingrese el nuevo año de publicación:");
                        String nuevoAño = scanner.nextLine();
                        documento.setAñoPublicacion(nuevoAño);
                        break;
                    case 4:
                        ArrayList<String> palabrasClave = new ArrayList<>();
                        System.out.println("Introduzca las palabras clave del documento (-1 para terminar):");
                        while (true) {
                            String palabraClave = scanner.nextLine();
                            if (palabraClave.equalsIgnoreCase("-1")) {
                                break;
                            } else {
                                palabrasClave.add(palabraClave);
                            }
                        }
                        break;
                    case 5:
                        System.out.println("Ingrese el nuevo tipo de documento:");
                        String nuevoTipo = scanner.nextLine();
                        documento.setTipo(nuevoTipo);
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }
                break;
            }
        }

        if (!encontrado) {
            System.out.println("El documento no fue encontrado.");
        }
    }

    public void eliminarDocumento() {
        System.out.println("\nIngrese el título del documento a eliminar:");
        String tituloBusqueda = scanner.nextLine();
        boolean encontrado = false;

        for (Documento documento : documentos) {
            if (documento.getTitulo().equalsIgnoreCase(tituloBusqueda)) {
                documentos.remove(documento);
                System.out.println("Documento eliminado exitosamente.");
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("El documento no fue encontrado.");
        }
    }

    public void buscarDocumento() {
        System.out.println("\nIngrese el título del documento a buscar:");
        String tituloBusqueda = scanner.nextLine();
        boolean encontrado = false;

        for (Documento documento : documentos) {
            if (documento.getTitulo().equalsIgnoreCase(tituloBusqueda)) {
                System.out.println("Documento encontrado:");
                documento.mostrarDetalles();
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            System.out.println("El documento no fue encontrado.");
        }
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
                Autor nuevAutor = new Autor(autores.size() + 1, nombre);
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
