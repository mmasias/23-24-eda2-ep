import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ManejadorBiblioteca {

    static Biblioteca biblioteca = new Biblioteca();
    static Scanner scanner = new Scanner(System.in);

    public static List<Autor> solicitarAutores() {
        List<Autor> autores = new ArrayList<>();
        System.out.println("Ingrese el nombre del autor. Ingrese 'f' para terminar:");
        while (true) {
            String nombre = scanner.nextLine().trim();
            if ("f".equalsIgnoreCase(nombre)) {
                break;
            }
            Autor autorExistente = biblioteca.buscarAutorPorNombre(nombre);
            if (autorExistente == null) {
                biblioteca.agregarAutor(nombre);
                autorExistente = biblioteca.buscarAutorPorNombre(nombre);
            } else {
                System.out.println("Ya existe un autor con este nombre. ID: " + autorExistente.getId());
            }
            autores.add(autorExistente);
        }
        return autores;
    }

    public static void editarLibro() {
        System.out.print("Ingrese el ID del libro a editar: ");
        int idLibro = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el nuevo título del libro (deje en blanco si no desea cambiarlo): ");
        String nuevoTitulo = scanner.nextLine();



        System.out.print("Ingrese el nuevo año de publicación (0 para no cambiar): ");
        int nuevoAño = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Ingrese el nuevo tipo de documento (LIBRO, REVISTA, ARTICULO, TESIS)-(deje en blanco si no desea cambiarlo): ");
        String tipo = scanner.nextLine().toUpperCase();
        TipoLibro nuevoTipo = tipo.isEmpty() ? null : TipoLibro.valueOf(tipo);


        biblioteca.getGestionLibro().editarLibro(idLibro, nuevoTitulo.isEmpty() ? null : nuevoTitulo, null, nuevoAño == 0 ? null : nuevoAño, nuevoTipo, null);
    }



    public static void ejecutar() {


        while (true) {
            System.out.println("\nMenú de la Biblioteca:");
            System.out.println("1. Añadir libro");
            System.out.println("2. Añadir autor a un libro por ID");
            System.out.println("3. Listar todos los libros");
            System.out.println("4. Editar libro");
            System.out.println("5. Eliminar libro");
            System.out.println("6. Buscar");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el título del libro: ");
                    String titulo = scanner.nextLine();

                    List<Autor> autores = solicitarAutores();

                    System.out.print("Ingrese el año de publicación: ");
                    int año = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Ingrese el tipo de documento (LIBRO, REVISTA, ARTICULO, TESIS, NOVELA): ");
                    String tipo = scanner.nextLine().toUpperCase();

                    System.out.print("Ingrese palabras clave (para varias, sepárelas por coma): ");
                    String palabrasClaveStr = scanner.nextLine();
                    List<String> palabrasClave = new ArrayList<>(Arrays.asList(palabrasClaveStr.split("\\s*,\\s*")));

                    TipoLibro tipoLibro;
                    try {
                        tipoLibro = TipoLibro.valueOf(tipo);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Tipo de documento no válido: " + tipo);
                        break;
                    }

                    Libro nuevoLibro = new Libro(titulo, autores, año, tipoLibro, palabrasClave);
                    biblioteca.agregarLibro(nuevoLibro);
                    System.out.println("Libro añadido con éxito.");
                    break;



                case 2:
                    if (!(biblioteca.getGestionLibro().getLibros().isEmpty())) {

                        biblioteca.mostrarLibrosId();


                        System.out.print("Ingrese el ID del libro al cual desea añadir un autor: ");
                        int bookId = scanner.nextInt();
                        scanner.nextLine();

                        System.out.print("Ingrese el nombre del autor a añadir: ");
                        String nombreAutor = scanner.nextLine();

                        Autor autor = biblioteca.buscarAutorPorNombre(nombreAutor);
                        if (autor == null) {

                            autor = new Autor(biblioteca.getNextAutorId(), nombreAutor);
                            biblioteca.agregarAutor(String.valueOf(autor));
                            System.out.println("Nuevo autor agregado: " + nombreAutor);
                        }

                        biblioteca.addAuthorToBook(bookId, autor);
                    }else {
                        System.out.println("No hay libros en la biblioteca.");
                    }
                    break;


                case 3:
                    biblioteca.mostrarLibros();
                    break;

                case 4:
                    if (!(biblioteca.getGestionLibro().getLibros().isEmpty())) {
                        biblioteca.mostrarLibrosId();

                        editarLibro();

                    }else {
                        System.out.println("No hay libros en la biblioteca.");
                    }
                    break;

                case 5:
                    if (biblioteca.getGestionLibro().getLibros().isEmpty()) {
                        System.out.println("No hay libros en la biblioteca.");
                    } else {
                        biblioteca.mostrarLibrosId();

                        System.out.print("Ingrese el ID del libro a eliminar: ");
                        int idLibro = scanner.nextInt();
                        scanner.nextLine();

                        if (idLibro > 0) {
                            boolean eliminado = biblioteca.getGestionLibro().eliminarLibroPorId(idLibro);
                            if (eliminado) {
                                System.out.println("Libro eliminado exitosamente.");
                            }
                        }
                    }
                    break;

                case 6:
                    boolean continuarBusqueda = true;

                    while (continuarBusqueda && !(biblioteca.getGestionLibro().getLibros().isEmpty())) {


                        System.out.println("\nOpciones de búsqueda:");
                        System.out.println("1. Por titulo");
                        System.out.println("2. Por autor");
                        System.out.println("3. Por año de publicación");
                        System.out.println("4. Por tipo de documento");
                        System.out.println("5. Por palabra clave");
                        System.out.println("6. Return");
                        System.out.print("Seleccione una opción de búsqueda: ");

                        int opcionBusqueda = scanner.nextInt();
                        scanner.nextLine();

                        switch (opcionBusqueda) {
                            case 1:
                                System.out.print("Ingrese el titulo del libro: ");
                                String nombreLibro = scanner.nextLine();
                                List<Libro> resultadosLibro = biblioteca.buscarPorTitulo(nombreLibro);
                                biblioteca.imprimirResultadosBusquedaTitulo(resultadosLibro);
                                break;

                            case 2:
                                System.out.print("Ingrese el nombre del autor: ");
                                String nombreAutor = scanner.nextLine();
                                List<Libro> resultadosAutor = biblioteca.buscarPorAutor(nombreAutor);
                                biblioteca.imprimirResultadosBusquedaAutor(resultadosAutor);
                                break;
                            case 3:
                                System.out.print("Ingrese el año de publicación: ");
                                int year = scanner.nextInt();
                                scanner.nextLine();
                                List<Libro> resultadosAño = biblioteca.buscarPorAño(year);
                                biblioteca.imprimirResultadosAño(resultadosAño);
                                break;
                            case 4:
                                System.out.print("Ingrese el tipo de documento (LIBRO, REVISTA, ARTICULO, TESIS, NOVELA): ");
                                String type = scanner.nextLine().toUpperCase();
                                try {
                                    TipoLibro bookType = TipoLibro.valueOf(type);
                                    List<Libro> resultadosTipo = biblioteca.buscarPorTipo(bookType);
                                    biblioteca.imprimirResultadosDocumento(resultadosTipo);
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Tipo de documento no válido.");
                                }
                                break;

                            case 5:
                                System.out.print("Ingrese la palabra clave: ");
                                String palabraClave = scanner.nextLine();
                                List<Libro> resultadosPalabraClave = biblioteca.buscarPorPalabraClave(palabraClave);
                                biblioteca.imprimirResultadosPalabraClave(resultadosPalabraClave);
                                break;
                            case 6:
                                continuarBusqueda = false;
                                break;
                            default:
                                System.out.println("Opción no válida.");

                        }
                    }

                    if(biblioteca.getGestionLibro().getLibros().isEmpty()) {
                    System.out.println("No hay libros en la biblioteca.");
                    }

                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    break;
            }
        }
    }
}
