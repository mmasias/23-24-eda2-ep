
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Libro> libros;
    private ArrayList<Autor> autores;
    private ArrayList<AutorLibro> autorLibro;
    Scanner sc = new Scanner(System.in);

    public Gestion() {
        this.libros = new ArrayList<Libro>();
        this.autores = new ArrayList<Autor>();
        this.autorLibro = new ArrayList<AutorLibro>();
        this.sc = new Scanner(System.in);
    }

    public void agregar() {
        System.out.println("Ingrese el id del libro");
        int id = sc.nextInt();
        System.out.println("Ingrese el titulo del libro");
        sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        System.out.println("Ingrese el año de publicacion");
        int año = sc.nextInt();
        System.out.println("Ingrese el tipo de libro");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER 5. ID");
        int opcion = sc.nextInt();
        Tipo tipo = null;
        switch (opcion) {
            case 1:
                tipo = Tipo.LIBRO;
                break;
            case 2:
                tipo = Tipo.REVISTA;
                break;
            case 3:
                tipo = Tipo.ARTICULO;
                break;
            case 4:
                tipo = Tipo.PAPER;
                break;
            case 5:
                tipo = Tipo.ID;
                break;
            default:
                break;
        }
        Libro libro = new Libro(titulo, año, tipo, id);
        añadirLibro(libro);
    }

    public void agregarAutor() {

        System.out.println("Ingrese el nombre del autor");
        sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido del autor");
        String apellido = sc.nextLine();
        System.out.println("Ingrese el ID del autor");
        int idAutor = sc.nextInt();
        Autor autor = new Autor(nombre, apellido, idAutor);
        añadirAutor(autor);

    }

    public void buscar() {
        System.out.println("Ingrese el titulo del libro a buscar");
        sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        boolean encontrado = false;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Libro encontrado:");
                System.out.println(libro);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Libro no encontrado.");
        }
    }

    public void eliminar() {
        System.out.println("Ingrese el titulo del libro a eliminar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                libros.remove(libro);
                break;
            }
        }
    }

    public ArrayList<Autor> getAutoresPorId(int idLibro) {
        System.out.println(" Escribe el ID de un libro para saber su autor ");
        sc = new Scanner(System.in);
        idLibro = sc.nextInt();
        ArrayList<Autor> autores = new ArrayList<Autor>();
        for (AutorLibro autorLibro : autorLibro) {
            if (autorLibro.getIdLibro() == idLibro) {
                Autor autor = buscarAutorPorID(autorLibro.getIdAutor());
                autores.add(autor);
            }
        }
        return autores;
    }

    public void menu() {
        boolean salir = false;
        do {
            System.out.println("1. Agregar Documento");
            System.out.println("2. Listar Documentos");
            System.out.println("3. Agregar Autor");
            System.out.println("4. Listar Autores");
            System.out.println("5. Agregar Relacion");
            System.out.println("6. Listar Autores por Documento");
            System.out.println("7. Listar Documentos por Autor");
            System.out.println("8.Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    listarLibros();
                    break;
                case 3:
                    agregarAutor();
                    break;
                case 4:
                    listarAutores();
                    break;
                case 5:
                    añadirRelacion(opcion, opcion);
                    break;
                case 6:
                    System.out.println("Escribe el ID del docuemento para saber su autor");
                    int bookID2 = sc.nextInt();
                    sc.nextLine();
                    listarAutoresPorLibro(bookID2);
                    System.out.println(autores);
                    break;
                case 7:
                    listarLibrosPorAutor(opcion);
                    System.out.println(libros);
                    break;
                case 8:
                    salir = true;
                    break;

                default:
                    break;
            }
        } while (!salir);
    }

    public void listarLibros() {
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }

    private List<Autor> listarAutoresPorLibro(int idLibro) {
        for (AutorLibro autorLibro : autorLibro) {
            if (autorLibro.getIdLibro() == idLibro) {
                Autor autor = buscarAutorPorID(autorLibro.getIdAutor());
                System.out.println(autor.toString());
            }
        }
        return null;
    }

    private List<Libro> listarLibrosPorAutor(int idAutor) {
        List<Libro> libritos = new ArrayList<Libro>();
        for (int i = 0; i < autorLibro.size(); i++) {
            AutorLibro autoresLibro = autorLibro.get(i);
            if (autoresLibro.getIdAutor() == idAutor) {
                Libro libro = buscarLibroPorID(autoresLibro.getIdLibro());
                if (libro != null) {
                    libritos.add(libro);
                }
            }

        }
        return libritos;
    }

    public void añadirLibro(Libro libro) {
        libros.add(libro);
    }

    public void añadirAutor(Autor autor) {
        autores.add(autor);
    }

    public ArrayList<Libro> getLibrosPorId(int idAutor) {
        ArrayList<Libro> libros = new ArrayList<Libro>();
        for (AutorLibro autorLibro : autorLibro) {
            if (autorLibro.getIdAutor() == idAutor) {
                Libro libro = buscarLibroPorID(autorLibro.getIdLibro());
                libros.add(libro);
            }
        }
        return libros;
    }

    public Libro buscarLibroPorID(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    public Autor buscarAutorPorID(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    public void listarAutores() {
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

    public void añadirRelacion(int idLibro, int idAutor) {
        System.out.println("Escribe un ID del libro:");
        idLibro = sc.nextInt();
        if (buscarLibroPorID(idLibro) != null) {
            if (buscarAutorPorID(idLibro) != null) {
                AutorLibro nuevaRelacion = new AutorLibro(idLibro, idLibro); 
                autorLibro.add(nuevaRelacion);
                System.out.println("Relación creada exitosamente:");
                System.out.println("Libro: " + buscarLibroPorID(idLibro).getTitulo() + " (ID: " + buscarLibroPorID(idLibro).getId() + ")");
                System.out.println("Autor: " + buscarAutorPorID(idLibro).getNombre() + " " + buscarAutorPorID(idLibro).getApellido() + " (ID: " + buscarAutorPorID(idLibro).getId() + ")");
            } else {
                System.out.println("Error: No se pudo encontrar el autor con el ID proporcionado.");
            }
        } else {
            System.out.println("Error: No se pudo encontrar el libro con el ID proporcionado.");
        }


    }
}


