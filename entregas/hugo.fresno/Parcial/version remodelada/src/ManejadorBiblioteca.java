import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ManejadorBiblioteca {

    static Biblioteca biblioteca = new Biblioteca();
    static Scanner scanner = new Scanner(System.in);

    public static List<Autor> solicitarAutores() {
        List<Autor> autores = new ArrayList<>();
        System.out.println("Ingrese el nombre del autor y su ID (ejemplo: AutorNombre,ID). Ingrese 'fin' para terminar:");
        while (true) {
            String input = scanner.nextLine().trim();
            if ("fin".equalsIgnoreCase(input)) {
                break; // Termina el bucle cuando el usuario escribe "fin"
            }
            String[] partes = input.split(",");
            if (partes.length != 2) {
                System.out.println("Formato inválido. Por favor, ingrese el nombre del autor y su ID de la forma 'AutorNombre,ID':");
                continue;
            }
            try {
                String nombre = partes[0].trim();
                int id = Integer.parseInt(partes[1].trim());
                // Verificar si el autor ya existe por ID o nombre para evitar duplicados
                Autor autorExistente = biblioteca.buscarAutorPorId(id);
                if (autorExistente == null) {
                    autorExistente = biblioteca.buscarAutorPorNombre(nombre);
                    if (autorExistente == null) {
                        Autor nuevoAutor = new Autor(id, nombre); // Crear el autor con el ID y nombre proporcionados
                        biblioteca.agregarAutor(nuevoAutor); // Asume que este método acepta objetos Autor
                        autores.add(nuevoAutor);
                    } else {
                        System.out.println("Ya existe un autor con este nombre. Se usará el autor existente.");
                        autores.add(autorExistente);
                    }
                } else {
                    System.out.println("Ya existe un autor con este ID. Se usará el autor existente.");
                    autores.add(autorExistente);
                }
            } catch (NumberFormatException e) {
                System.out.println("ID inválido. Por favor, ingrese un número válido para el ID.");
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

                    biblioteca.mostrarLibrosId();
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
