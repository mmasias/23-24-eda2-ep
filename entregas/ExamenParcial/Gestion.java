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
    }

    public void editar() {
        System.out.println("Ingrese el titulo del documento a editar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
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
                boolean agregar = true;
                do {
                    System.out.println("Ingrese el nombre del autor");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el apellido del autor");
                    String apellido = sc.nextLine();
                    System.out.println("Ingrese el ID del autor");
                    int idAutor = sc.nextInt();
                    Autor autor = new Autor(nombre, apellido, idAutor);
                    ArrayList<Autor> autores = new ArrayList<Autor>();
                    autores.add(autor);
                    System.out.println("Desea agregar otro autor? (si-no)");
                    String respuesta = sc.nextLine();
                    if (respuesta.equals("no")) {
                        agregar = false;
                    } else {
                        agregar = true;
                    }
                } while (agregar);
            }


            String palabra = "";
            do {
                System.out.println("Introduzca palabras clave (fin-para terminar)");
                palabra = sc.nextLine();
                if (!palabra.equals("fin")) {
                    libro.añadirPalabraClave(palabra);
                }

            } while (!palabra.equals("fin"));
        }
    }

    public void buscar() {
        System.out.println("Ingrese el titulo del libro a buscar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Año de publicacion: " + libro.getAñoDePublicacion());
                System.out.println("Tipo: " + libro.getTipo());
                System.out.println("Autores: ");
                System.out.println("Palabras clave: ");
                for (String palabra : libro.getPalabrasClave()) {
                    System.out.println(palabra);
                }
            }
        }
    }
    

    public void buscarPorPalabrasClave() {
        System.out.println("Ingrese la palabra clave a buscar");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        for (Libro libro : libros) {
            for (String palabraClave : libro.getPalabrasClave()) {
                if (palabraClave.equals(palabra)) {
                    System.out.println("Titulo: " + libro.getTitulo());
                    System.out.println("Año de publicacion: " + libro.getAñoDePublicacion());
                    System.out.println("Tipo: " + libro.getTipo());
                    System.out.println("Autores: ");
                    
                    System.out.println("Palabras clave: ");
                    for (String palabra2 : libro.getPalabrasClave()) {
                        System.out.println(palabra2);
                    }
                }
            }
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
            System.out.println("2. Modificar documento");
            System.out.println("3. Buscar documento");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por palabras clave");
            System.out.println("6. Crear relación autor-libro");
            System.out.println("7. Eliminar documento");
            System.out.println("8. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    editar();
                    break;
                case 2:
                    editar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    getAutoresPorId(opcion);
                    break;
                case 5:
                    buscarPorPalabrasClave();
                    break;
                case 6:
                    añadirRelacion(opcion, opcion);
                    break;
                case 7:
                eliminar();
                    break;

                case 8:
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
    public void añadirAutor(Libro libro){
        libros.add(libro);
    }
    public void añadirAutor(Autor autor){
        autores.add(autor);
    }
    public void añadirAutorLibro(int idLibro, int idAutor){
        AutorLibro autorLibro = new AutorLibro(idLibro, idAutor);
        autorLibro.add(autorLibro);
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
        AutorLibro autorLibro = new AutorLibro(idLibro, idAutor);
        autorLibro.add(autorLibro);

        Libro libro = buscarLibroPorID(idLibro);
        if(libro != null){
            Autor autor = buscarAutorPorID(idAutor);
            if (autor != null) {
                añadirAutor(autor);
            }
        }
    }

}
