import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ManejadorBiblioteca {

    static Biblioteca biblioteca = new Biblioteca();
    static Scanner scanner = new Scanner(System.in);

    public static List<Autor> solicitarAutores() {
        List<Autor> autores = new ArrayList<>();
        System.out.println("Ingrese el nombre del autor. Ingrese 'fin' para terminar:");
        while (true) {
            String nombre = scanner.nextLine().trim();
            if ("fin".equalsIgnoreCase(nombre)) {
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


    public static void ejecutar() {


        while (true) {
            System.out.println("\nMenú de la Biblioteca:");
            System.out.println("1. Añadir libro");
            System.out.println("2. Añadir autor a un libro por ID");
            System.out.println("3. Listar todos los libros");
            System.out.println("4. Salir");
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
                    scanner.nextLine(); // Limpiar buffer

                    System.out.print("Ingrese el tipo de documento (LIBRO, REVISTA, ARTICULO, TESIS): ");
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
                    break;


                case 3:
                    biblioteca.mostrarLibros();
                    break;

                case 4:
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
