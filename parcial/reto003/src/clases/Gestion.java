package clases;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    private List<Libro> libros;
    private List<Autor> autores;
    private List<AutorLibro> autorLibros;
    Scanner sc = new Scanner(System.in);


    public Gestion() {
        libros = new ArrayList<Libro>();
        autores = new ArrayList<Autor>();
        autorLibros = new ArrayList<AutorLibro>();
    }
    private HashMap<String, ArrayList<Libro>> indiceAutores;
    private HashMap<String, ArrayList<Libro>> indicePalabras;


    
    public void listaLibros(){
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void listaAutores(){
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    public void agregarLibro() {
        System.out.println("Ingrese el id del libro");
        int id = sc.nextInt();
        System.out.println("Ingrese el titulo del documento");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el año de publicacion");
        int año = sc.nextInt();
        System.out.println("Ingrese el tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPEL");
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
                tipo = Tipo.PAPEL;
                break;
            default:
                break;
        }
        libros.add(new Libro(titulo, año, tipo));

        System.out.println("Desea agregar autores? (s/n)");
        String respuesta = sc.nextLine();
        if (respuesta.equals("s")) {
            listaAutores();
            System.out.println("Seleccione el id del autor o -1 para agregar un autor nuevo");
            int idAutor = sc.nextInt();
            if (idAutor == -1) {
                Autor autor = agregarAutor();
                agregarAutorLibro(id, autor.getId());
            } else {
                agregarAutorLibro(id, idAutor);
            }  
            
        }
    }

    public Autor agregarAutor() {
        System.out.println("Ingrese el nombre del autor");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido del autor");
        String apellido = sc.nextLine();
        System.out.println("Ingrese el id del autor");
        int id = sc.nextInt();        
        autores.add(new Autor(id,nombre, apellido));
        return new Autor(id,nombre, apellido);
    }

    public void agregarAutorLibro(int idLibro, int idAutor) {
        autorLibros.add(new AutorLibro(idLibro, idAutor));
    }
       

    

    





















































    public void modificarAutor(String nombreAutor) {
        ArrayList<Libro> librosDelAutor = libros.get(nombreAutor);
        Scanner sc = new Scanner(System.in);
        System.out.println("libros del autor '" + nombreAutor + "':");
        int index = 1;
        for (Libro doc : librosDelAutor) {
            System.out.println(index++ + ". " + doc.getTitulo());
        }
        System.out.println("Seleccione el número del libro que desea modificar:");
        int opcion = sc.nextInt();

        if (opcion < 1 || opcion > librosDelAutor.size()) {
            System.out.println("Opción no válida.");

        }
    }

    public void modificar() {
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el titulo del libro a editar");
        String tituloLibro = scanner.nextLine();

        for (Libro libro : libros) {
            if (libro.getTipo().equals(tituloLibro)) {
                System.out.println("Dime el nombre del nuevo titulo a añadir");
                String nuevoTitulo = scanner.nextLine();
                libro.setTitulo(nuevoTitulo);
                System.out.println("Dime el año de publicacion del libro");
                int anoPublicacion = scanner.nextInt();
                libro.setAnoDePublicacion(anoPublicacion);
                System.out.println("Dime que quieres añadir (1.LIBRO 2.REVISTA 3.ARTICULO 4.PAPEL)");
                int eleccion = scanner.nextInt();
                Tipo tipo = null;

                switch (eleccion) {
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
                        tipo = Tipo.PAPEL;
                        break;

                    default:
                        break;
                }
                libro.setTipo(tipo);
                boolean agregar = true;
                while (agregar) {
                    System.out.println("Dime el nombre del autor");
                    String nombre = scanner.nextLine();
                    System.out.println("Dime el apellido del autor");
                    String apellido = scanner.nextLine();
                    Autor autor = new Autor(nombre, apellido);
                    ArrayList<Autor> autores = new ArrayList<Autor>();
                    libro.añadirAutor(autor);
                    System.out.println("Quieres añadir otro autor? (s/n)");
                    String respuesta = scanner.nextLine();
                    if (respuesta.equals("n")) {
                        agregar = false;
                    }
                }

                String palabra = "";

                do {
                    System.out.println("Introduzca palabras clave (fin-para terminar)");
                    palabra = scanner.nextLine();
                    if (!palabra.equals("fin")) {
                        libro.añadirPalabraClave(palabra);
                    }

                } while (!palabra.equals("fin"));

            }
        }
    }

    public void buscarPorTitulo() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el titulo que desea buscar");
        String titulo = sc.nextLine();
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                System.out.println("Titulo: " + libro);
                System.out.println("Año de publicacion: " + libro.getAnoDePublicacion());
                System.out.println("Tipo: " + libro.getTipo());
            }
            for (Autor autor : libro.getAutores()) {
                System.out.print("Nombre: " + autor.getNombre());
                System.out.println("Apellido: " + autor.getApellido());
            }
            for (String palabraClave : libro.getPalabrasClave()) {
                System.out.println("Palabra clave: " + palabraClave);
            }
        }
    }

    public void buscarPorAutor() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del autor que desea buscar");
        String nombre = sc.nextLine();
        for (Libro libro : libros) {
            for (Autor autor : libro.getAutores()) {
                if (autor.getNombre().equals(nombre)) {
                    System.out.println("Titulo: " + libro);
                    System.out.println("Año de publicacion: " + libro.getAnoDePublicacion());
                    System.out.println("Tipo: " + libro.getTipo());
                }
                for (Autor nuevoAutor : libro.getAutores()) {
                    System.out.print("Nombre: " + nuevoAutor.getNombre());
                    System.out.println(" Apellido: " + nuevoAutor.getApellido());
                }
                for (String palabraClave : libro.getPalabrasClave()) {
                    System.out.println("Palabra clave: " + palabraClave);
                }
            }
        }
    }

    public void buscarPorPalabraClave() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce la palabra clave que desea buscar");
        String palabra = sc.nextLine();
        for (Libro libro : libros) {
            for (String palabraClave : libro.getPalabrasClave()) {
                if (palabraClave.equals(palabra)) {
                    System.out.println("Titulo: " + libro);
                    System.out.println("Año de publicacion: " + libro.getAnoDePublicacion());
                    System.out.println("Tipo: " + libro.getTipo());
                }
                for (Autor autor : libro.getAutores()) {
                    System.out.print("Nombre: " + autor.getNombre());
                    System.out.println("Apellido: " + autor.getApellido());
                }
                for (String nuevaPalabraClave : libro.getPalabrasClave()) {
                    System.out.println("Palabra clave: " + nuevaPalabraClave);
                }
            }
        }
    }

    public void eliminar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el titulo del libro que quieres eliminar");
        String titulo = sc.nextLine();
        for (Libro libro : libros) {
            if (libro.getTitulo().equals(titulo)) {
                libros.remove(libro);
            }
        }
    }

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("1. Agregar libro");
            System.out.println("2. Modificar libro");
            System.out.println("3. Buscar por titulo");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por palabra clave");
            System.out.println("6. Eliminar libro");
            System.out.println("7. Salir");
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    modificar();
                    break;
                case 3:
                    buscarPorTitulo();
                    break;
                case 4:
                    buscarPorAutor();
                    break;
                case 5:
                    buscarPorPalabraClave();
                    break;
                case 6:
                    eliminar();
                    break;
                default:
                    break;
            }
        } while (opcion != 7);
    }

    public static void main(String[] args) {
        Gestion gestion = new Gestion();
        gestion.menu();
    }
}
