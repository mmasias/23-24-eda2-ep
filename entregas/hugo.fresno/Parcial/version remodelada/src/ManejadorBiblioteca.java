import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ManejadorBiblioteca {

    static Biblioteca biblioteca = new Biblioteca();
    static Scanner scanner = new Scanner(System.in);

    public static List<Autor> solicitarAutores() {
        List<Autor> autores = new ArrayList<>();
        System.out.print("Ingrese los IDs de los autores (para varios, sepárelos por coma): ");
        String autorIdsStr = scanner.nextLine();
        String[] autorIds = autorIdsStr.split("\\s*,\\s*");

        for (String idStr : autorIds) {
            try {
                int autorId = Integer.parseInt(idStr.trim());
                Autor autor = biblioteca.buscarAutorPorId(autorId);
                if (autor != null) {
                    autores.add(autor);
                } else {
                    System.out.println("Autor con ID " + autorId + " no encontrado.");
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido: " + idStr);
            }
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

                    System.out.print("Ingrese el ID del autor a añadir: ");
                    int autorId = scanner.nextInt();
                    scanner.nextLine();

                    Autor nuevoAutor = biblioteca.buscarAutorPorId(autorId);
                    if (nuevoAutor != null) {
                        biblioteca.addAuthorToBook(bookId, nuevoAutor);
                    } else {
                        System.out.println("Autor con el ID proporcionado no encontrado.");
                    }
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
