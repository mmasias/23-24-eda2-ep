import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Book> libros;
    private List<Author> autores;
    private List<BookAuthor> relaciones;
    private Scanner scanner;

    public LibraryManager() {
        libros = new ArrayList<>();
        autores = new ArrayList<>();
        relaciones = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void iniciarGestorBiblioteca() {
        boolean enEjecucion = true;
        while (enEjecucion) {
            System.out.println("\nBienvenido al Sistema de Gestión de la Biblioteca");
            System.out.println("1. Listar libros");
            System.out.println("2. Agregar un libro");
            System.out.println("3. Listar autores");
            System.out.println("4. Agregar un autor");
            System.out.println("5. Asociar autor con libro");
            System.out.println("6. Mostrar libros de un autor");
            System.out.println("7. Mostrar autores de un libro");
            System.out.println("8. Salir");
            System.out.print("Por favor elige una opción: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    listarLibros();
                    break;
                case 2:
                    agregarLibro();
                    break;
                case 3:
                    listarAutores();
                    break;
                case 4:
                    agregarAutor();
                    break;
                case 5:
                    asociarAutorConLibro();
                    break;
                case 6:
                    mostrarLibrosDeAutor();
                    break;
                case 7:
                    mostrarAutoresDeLibro();
                    break;
                case 8:
                    enEjecucion = false;
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        }
        scanner.close();
        System.out.println("Gracias por utilizar el sistema. ¡Hasta la próxima!");
    }

    private void listarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros registrados en el sistema.");
        } else {
            for (Book libro : libros) {
                System.out.println(libro);
            }
        }
    }

    private void agregarLibro() {
        System.out.println("Introduce el título del libro:");
        String titulo = scanner.nextLine();
        System.out.println("Introduce el año de publicación:");
        int anio = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Introduce el tipo de libro:");
        String tipo = scanner.nextLine();

        Book nuevoLibro = new Book(libros.size() + 1, titulo, anio, tipo);
        libros.add(nuevoLibro);
        System.out.println("Libro agregado exitosamente: " + nuevoLibro);
    }

    private void listarAutores() {
        if (autores.isEmpty()) {
            System.out.println("No hay autores registrados en el sistema.");
        } else {
            for (Author autor : autores) {
                System.out.println(autor);
            }
        }
    }

    private void agregarAutor() {
        System.out.println("Introduce el nombre del autor:");
        String nombre = scanner.nextLine();
        Author nuevoAutor = new Author(autores.size() + 1, nombre);
        autores.add(nuevoAutor);
        System.out.println("Autor agregado exitosamente: " + nuevoAutor);
    }

    private void asociarAutorConLibro() {
        System.out.println("Introduce el ID del libro:");
        int idLibro = scanner.nextInt();
        System.out.println("Introduce el ID del autor:");
        int idAutor = scanner.nextInt();

        Book libro = libros.stream().filter(l -> l.getId() == idLibro).findFirst().orElse(null);
        Author autor = autores.stream().filter(a -> a.getId() == idAutor).findFirst().orElse(null);

        if (libro != null && autor != null) {
            relaciones.add(new BookAuthor(idLibro, idAutor));
            System.out.println("Autor asociado con el libro exitosamente.");
        } else {
            System.out.println("Libro o autor no encontrado. Por favor verifica los IDs.");
        }
    }

    private void mostrarLibrosDeAutor() {
        System.out.println("Introduce el ID del autor:");
        int idAutor = scanner.nextInt();
        List<Book> librosDelAutor = new ArrayList<>();
        for (BookAuthor relacion : relaciones) {
            if (relacion.getIdAutor() == idAutor) {
                librosDelAutor.add(libros.stream().filter(l -> l.getId() == relacion.getIdLibro()).findFirst().orElse(null));
            }
        }
        if (librosDelAutor.isEmpty()) {
            System.out.println("No se encontraron libros para este autor.");
        } else {
            for (Book libro : librosDelAutor) {
                System.out.println(libro);
            }
        }
    }

    private void mostrarAutoresDeLibro() {
        System.out.println("Introduce el ID del libro:");
        int idLibro = scanner.nextInt();
        List<Author> autoresDelLibro = new ArrayList<>();
        for (BookAuthor relacion : relaciones) {
            if (relacion.getIdLibro() == idLibro) {
                autoresDelLibro.add(autores.stream().filter(a -> a.getId() == relacion.getIdAutor()).findFirst().orElse(null));
            }
        }
        if (autoresDelLibro.isEmpty()) {
            System.out.println("No se encontraron autores para este libro.");
        } else {
            for (Author autor : autoresDelLibro) {
                System.out.println(autor);
            }
        }
    }

    public static void main(String[] args) {
        LibraryManager gestorBiblioteca = new LibraryManager();
        gestorBiblioteca.iniciarGestorBiblioteca();
    }
}
