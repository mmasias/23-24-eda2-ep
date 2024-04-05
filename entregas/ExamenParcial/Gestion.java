import java.util.ArrayList;
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
        boolean agregar = true;
        do {
            System.out.println("Ingrese el nombre del autor");
            sc = new Scanner(System.in);
            String nombre = sc.nextLine();
            System.out.println("Ingrese el apellido del autor");
            String apellido = sc.nextLine();
            System.out.println("Ingrese el ID del autor");
            int idAutor = sc.nextInt();
            Autor autor = new Autor(nombre, apellido, idAutor);
            añadirAutor(autor);
            System.out.println("Desea agregar otro autor? (si-no)");
            sc = new Scanner(System.in);
            String respuesta = sc.nextLine();
            if (respuesta.equals("no")) {
                agregar = false;
            } else {
                agregar = true;
            }
        } while (agregar);
    }


    public void editar() {
        System.out.println("Ingrese el titulo del documento a editar");
        String titulo = sc.nextLine();
        boolean encontrado = false;
        boolean agregar = true;
        for (Libro libro : libros) {
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                System.out.println("Ingrese el nuevo titulo del libro");
                String nuevoTitulo = sc.nextLine();
                libro.setTitulo(nuevoTitulo);
                System.out.println("Ingrese el nuevo año de publicacion");
                int nuevoAño = sc.nextInt();
                libro.setAñoDePublicacion(nuevoAño);
                System.out.println("Ingrese el nuevo tipo de libro");
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
                libro.setTipo(tipo);
                encontrado = true;
                break;
            }
        }
        if (!encontrado) {
            System.out.println("Libro no encontrado.");
        }

                do {
                    System.out.println("Ingrese el nombre del autor");
                    sc = new Scanner(System.in);
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el apellido del autor");
                    String apellido = sc.nextLine();
                    System.out.println("Ingrese el ID del autor");
                    int idAutor = sc.nextInt();
                    Autor autor = new Autor(nombre, apellido, idAutor);
                    ArrayList<Autor> autores = new ArrayList<Autor>();
                    autores.add(autor);
                    System.out.println("Desea agregar otro autor? (si-no)");
                    sc = new Scanner(System.in);
                    String respuesta = sc.nextLine();
                    if (respuesta.equals("no")) {
                        agregar = false;
                    } else {
                        agregar = true;
                    }
                } while (agregar);
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
    public ArrayList<Autor> getAutoresPorId(int idLibro){
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
            System.out.println("1. Agregar documento");
            System.out.println("2. Agregar autor");
            System.out.println("3. Buscar documento");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Crear relación autor-libro");
            System.out.println("6. Eliminar documento");
            System.out.println("7. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    agregarAutor();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    getAutoresPorId(opcion);
                    break;
                case 5:
                    añadirRelacion(opcion, opcion);
                    break;
                case 6:
                    eliminar();
                    break;
                 case 7:
                    salir = true;
                    break;
                default:
                    break;
            }
        } while (!salir);
    }

    public void listarLibros(){
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }
    public void listarAutoresPorLibro(int idLibro){
        for (AutorLibro autorLibro : autorLibro) {
            if (autorLibro.getIdLibro() == idLibro) {
                Autor autor = buscarAutorPorID(autorLibro.getIdAutor());
                System.out.println(autor.toString());
            }
        }
    }
    public void añadirLibro(Libro libro){
        libros.add(libro);
    }
    public void añadirAutor(Autor autor){
        autores.add(autor);
    }
    public ArrayList<Libro> getLibrosPorId(int idAutor){
        ArrayList<Libro> libros = new ArrayList<Libro>();
        for (AutorLibro autorLibro : autorLibro) {
            if (autorLibro.getIdAutor() == idAutor) {
                Libro libro = buscarLibroPorID(autorLibro.getIdLibro());
                libros.add(libro);
            }
        }
        return libros;
    }
    public Libro buscarLibroPorID(int id){
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }
    public Autor buscarAutorPorID(int id){
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }
    public void listarAutores(){
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

    public void añadirRelacion(int idLibro, int idAutor){
        AutorLibro nuevaRelacion = new AutorLibro(idLibro, idAutor);
        autorLibro.add(nuevaRelacion);

        Libro libro = buscarLibroPorID(idLibro);
        if(libro != null){
            Autor autor = buscarAutorPorID(idAutor);
            if (autor != null) {
                agregarAutor();
            }
        }
    }
}

