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

        libros.add(new Libro(1, "El señor de los anillos", 1954, Tipo.LIBRO));
        libros.add(new Libro(2, "El hobbit", 1937, Tipo.LIBRO));
        autores.add(new Autor(1, "J.R.R.", "Tolkien"));
        autores.add(new Autor(2, "J.", "son"));
        autorLibros.add(new AutorLibro(1, 1));
        autorLibros.add(new AutorLibro(1, 2));
        autorLibros.add(new AutorLibro(2, 1));

    }

    public void listaLibros() {
        for (Libro libro : libros) {
            System.out.println(libro.toString());
        }
    }
    public void listar() {
        System.out.println("-------------------------------------");
        for (Libro libro : libros) {
            System.out.println(libro.toString());
            for (Autor autor : buscarAutorPorLibro(libro.getId())) {
                System.out.println(autor.toString());
            }
            System.out.println("-------------------------------------");
        }
       
    }
    public void listaAutores() {
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    public void agregarLibro() {
        System.out.println("Ingrese el id del libro");
        int id = sc.nextInt();
        System.out.println("Ingrese el titulo del libro");
        sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        System.out.println("Ingrese el año de publicacion");
        int año = sc.nextInt();
        System.out.println("Ingrese el tipo de libro");
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
        libros.add(new Libro(id, titulo, año, tipo));
        String respuesta;
        do {
            System.out.println("Desea agregar autores? (s/n)");
            sc = new Scanner(System.in);
            respuesta = sc.nextLine();
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
        } while (respuesta.equals("s"));

    }

    public Autor agregarAutor() {
        System.out.println("Ingrese el nombre del autor");
        sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        System.out.println("Ingrese el apellido del autor");
        String apellido = sc.nextLine();
        System.out.println("Ingrese el id del autor");
        int id = sc.nextInt();
        autores.add(new Autor(id, nombre, apellido));
        return new Autor(id, nombre, apellido);

    }

    public void agregarAutorLibro(int idLibro, int idAutor) {
        autorLibros.add(new AutorLibro(idLibro, idAutor));
    }

    public void agregarPalabraClave() {
        String respuesta;
        do {
            System.out.println("Ingrese el id del libro");
            int idLibro = sc.nextInt();
            System.out.println("Ingrese la palabra clave");
            sc = new Scanner(System.in);
            String palabra = sc.nextLine();
            for (Libro libro : libros) {
                if (libro.getId() == idLibro) {
                    libro.añadirPalabraClave(palabra);
                }
            }
            System.out.println("Desea agregar otra palabra clave (s/n)");
            respuesta = sc.nextLine();
        } while (respuesta.equals("s"));

    }

    public void buscarLibroPorId() {
        System.out.println("Ingrese el id del libro");
        int id = sc.nextInt();
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                System.out.println(libro);
            }
        }
    }

    public Libro buscarLibroPorId(int id) {
        Libro libroEncontrado = null;
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                libroEncontrado = libro;
            }
        }
        return libroEncontrado;
    }

    public void buscarAutorPorId() {
        System.out.println("Ingrese el id del autor");
        int id = sc.nextInt();
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                System.out.println(autor);
            }
        }
    }
 

    public Autor buscarAutorPorId(int id) {
        Autor autorEncontrado = null;
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                autorEncontrado = autor;
            }
        }
        return autorEncontrado;
    }

    public ArrayList<Autor> buscarAutorPorLibro(int idLibro) {
        ArrayList<Autor> autoresDelLibro = new ArrayList<Autor>();
        for (AutorLibro autorLibro : autorLibros) {
            if (autorLibro.getIdLibro() == idLibro) {
                autoresDelLibro.add(buscarAutorPorId(autorLibro.getIdAutor()));
            }
        }
        return autoresDelLibro;
    }

    public ArrayList<Libro> buscarLibroPorAutor(int id) {
        ArrayList<Libro> librosDelAutor = new ArrayList<Libro>();
        for (AutorLibro autorLibro : autorLibros) {
            if (autorLibro.getIdAutor() == id) {
                librosDelAutor.add(buscarLibroPorId(autorLibro.getIdLibro()));
            }
        }
        return librosDelAutor;
    }

    public void modificarPorLibro() { 
        int id = 0;
        do {
            listaLibros();
            System.out.println("Ingrese el id del libro");
            id = sc.nextInt();
            if (buscarLibroPorId(id) != null) {
                break;
            }
        } while (true);

        for (Libro libro : libros) {
            if (libro.getId() == id) {
                System.out.println("Quieres modificar el titulo? (s/n)");
                sc = new Scanner(System.in);
                String respuestaTitulo = sc.nextLine();
                if (respuestaTitulo.equals("s")) {
                    System.out.println("Ingrese el nuevo titulo");
                    String titulo = sc.nextLine();
                    libro.setTitulo(titulo);
                }

                System.out.println("Quieres modificar el año de publicacion? (s/n)");
                String respuestaAño = sc.nextLine();
                if (respuestaAño.equals("s")) {
                    System.out.println("Ingrese el nuevo año de publicacion");
                    int año = sc.nextInt();
                    libro.setAnoDePublicacion(año);
                }

                System.out.println("Quieres modificar el tipo de libro? (s/n)");
                String respuestaTipo = sc.nextLine();
                if (respuestaTipo.equals("s")) {
                    System.out.println("Ingrese el nuevo tipo de libro");
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
                    libro.setTipo(tipo);

                }

                System.out.println("Quieres modificar los autores? (s/n)");
                String respuestaAutores = sc.nextLine();
                if (respuestaAutores.equals("s")) {
                    agregarAutor();
                }

            }
        }
    }

    public void modificarPorAutor() {
        Gestion gestion = new Gestion();
        int id = 0;
        do {
            gestion.listaAutores();
            System.out.println("Ingrese el id del autor");
            id = sc.nextInt();
            if (gestion.buscarAutorPorId(id) != null) {
                break;
            }
        } while (true);
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                System.out.println("Quieres modificar el autor? (s/n)");
                String respuestaAutor = sc.nextLine();
                if (respuestaAutor.equals("s")) {
                    agregarAutor();
                }


            }
        }
    }

    public void menu(){
        Scanner sc = new Scanner(System.in);
        int opcion = 0;
        do {
            System.out.println("1. Agregar libro");
            System.out.println("2. Agregar autor");
            System.out.println("3. Agregar palabra clave");
            System.out.println("4. Buscar libro por id");
            System.out.println("5. Buscar autor por id");
            System.out.println("6. Buscar autor por libro");
            System.out.println("7. Buscar libro por autor");
            System.out.println("8. Modificar por libro");
            System.out.println("9. Modificar por autor");
            System.out.println("10. Listar");
            System.out.println("11. Salir");
            opcion = sc.nextInt();
            if (opcion > 0 && opcion <= 11) {
                switch (opcion) {
                    case 1:
                        agregarLibro();
                        break;
                    case 2:
                        agregarAutor();
                        break;
                    case 3:
                        agregarPalabraClave();
                        break;
                    case 4:
                        buscarLibroPorId();
                        break;
                    case 5:
                        buscarAutorPorId();
                        break;
                    case 6:
                        System.out.println("Ingrese el id del libro");
                        int idLibro = sc.nextInt();
                        for(Autor autor:buscarAutorPorLibro(idLibro)){
                            System.out.println(autor.toString());
                        }
    
                        break;
                    case 7:
                        System.out.println("Ingrese el id del autor");
                        int idAutor = sc.nextInt();
                        for(Libro libro:buscarLibroPorAutor(idAutor)){
                            System.out.println(libro.toString());
                        }
                        break;
                    case 8:
                        modificarPorLibro();
                        break;
                    case 9:
                        modificarPorAutor();
                        break;
                    case 10:
                        listar();
                        break;
                                   
                    default:
                        break;
                }
            }else{
                System.out.println("Opción no válida");
            }
            
        } while (opcion != 11);
    }

    public void main() {
        Gestion gestion = new Gestion();
        
        gestion.menu();
        
    }

    public static void main(String[] args) {
        Gestion gestion = new Gestion();
        gestion.main();
    }



}
