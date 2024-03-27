package puenteDaniel;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Texto> textos;

    public Gestion(ArrayList<Texto> textos) {
        this.textos = textos;
    }

    public void agregar() {

        System.out.println("Ingrese el titulo del documento");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        System.out.println("Ingrese el año de publicacion");
        int año = sc.nextInt();
        System.out.println("Ingrese el tipo de documento");
        System.out.println(" LIBRO | REVISTA | ARTICULO | PAPER");
        String tipo = sc.nextLine();
        
        boolean agregar = true;
        ArrayList<Autor> autores = new ArrayList<Autor>();
        do {
            System.out.println("Ingrese el id del autor");
            int id = sc.nextInt();
            System.out.println("Ingrese el nombre del autor");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el apellido del autor");
            String apellido = sc.nextLine();
            Autor autor = new Autor(nombre, apellido,id);
            autores.add(autor);
            System.out.println("Desea agregar otro autor? (si-no)");
            String respuesta = sc.nextLine();
            if (respuesta.equals("no")) {
                agregar = false;
            } else {
                agregar = true;
            }
        } while (agregar);
        Texto texto = new Texto(titulo, autores, tipo);
        String palabra = "";
        do {;
            System.out.println("Introduzca palabras clave (fin-para terminar)");
            palabra = sc.nextLine();
            if (!palabra.equals("fin")) {
                texto.añadirPalabraClave(palabra);
            }

        } while (!palabra.equals("fin"));
        textos.add(texto);
    }

    public void editar() {
        System.out.println("Ingrese el titulo del documento a editar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Texto texto : textos) {
            if (texto.getTitulo().equals(titulo)) {
                System.out.println("Ingrese el nuevo titulo del documento");
                String nuevoTitulo = sc.nextLine();
                texto.setTitulo(nuevoTitulo);
                System.out.println("Ingrese el nuevo año de publicacion");
                int nuevoAño = sc.nextInt();
                texto.setAño_publicacion(nuevoAño);
                System.out.println("Ingrese el nuevo tipo de documento");
                System.out.println("LIBRO | REVISTA | ARTICULO | PAPER");
                int opcion = sc.nextInt();
                String tipo = sc.nextLine();
                texto.setTipo(tipo);
                boolean agregar = true;
                do {
                    System.out.println("Ingrese el id del autor");
                    int id = sc.nextInt();
                    System.out.println("Ingrese el nombre del autor");
                    String nombre = sc.nextLine();
                    System.out.println("Ingrese el apellido del autor");
                    String apellido = sc.nextLine();
                    Autor autor = new Autor(nombre, apellido,id);
                    ArrayList<Autor> autores = new ArrayList<Autor>();
                    autores.add(autor);
                    texto.setAutores(autores);
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
                    texto.añadirPalabraClave(palabra);
                }

            } while (!palabra.equals("fin"));
        }
    }

    public void buscar() {
        System.out.println("Ingrese el titulo del documento a buscar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Texto texto : textos) {
            if (texto.getTitulo().equals(titulo)) {
                System.out.println("Titulo: " + texto.getTitulo());
                System.out.println("Año de publicacion: " + texto.getAño_publicacion());
                System.out.println("Tipo: " + texto.getTipo());
                System.out.println("Autores: ");
                for (Autor autor : texto.getAutores()) {
                    System.out.println(autor.getNombre() + " " + autor.getApellido());
                }
                System.out.println("Palabras clave: ");
                for (String palabra : texto.getPalabras_clave()) {
                    System.out.println(palabra);
                }
            }
        }
    }

    public void buscarPorAutor() {
        System.out.println("Ingrese el nombre del autor a buscar");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        for (Texto texto : textos) {
            for (Autor autor : texto.getAutores()) {
                if (autor.getNombre().equals(nombre)) {
                    System.out.println("Titulo: " + texto.getTitulo());
                    System.out.println("Año de publicacion: " + texto.getAño_publicacion());
                    System.out.println("Tipo: " + texto.getTipo());
                    System.out.println("Autores: ");
                    for (Autor autor2 : texto.getAutores()) {
                        System.out.println(autor2.getNombre() + " " + autor2.getApellido());
                    }
                    System.out.println("Palabras clave: ");
                    for (String palabra : texto.getPalabras_clave()) {
                        System.out.println(palabra);
                    }
                }
            }
        }
    }

    public void buscarPorPalabrasClave() {
        System.out.println("Ingrese la palabra clave a buscar");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        for (Texto texto : textos) {
            for (String palabraClave : texto.getPalabras_clave()) {
                if (palabraClave.equals(palabra)) {
                    System.out.println("Titulo: " + texto.getTitulo());
                    System.out.println("Año de publicacion: " + texto.getAño_publicacion());
                    System.out.println("Tipo: " + texto.getTipo());
                    System.out.println("Autores: ");
                    for (Autor autor : texto.getAutores()) {
                        System.out.println(autor.getNombre() + " " + autor.getApellido());
                    }
                    System.out.println("Palabras clave: ");
                    for (String palabra2 : texto.getPalabras_clave()) {
                        System.out.println(palabra2);
                    }
                }
            }
        }
    }

    public void eliminar() {
        System.out.println("Ingrese el titulo del documento a eliminar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Texto texto : textos) {
            if (texto.getTitulo().equals(titulo)) {
                textos.remove(textos);
                break;
            }
        }
    }

    public void menu() {
        boolean salir = false;
        do {
            System.out.println("1. Agregar documento");
            System.out.println("2. Modificar documento");
            System.out.println("3. Buscar documento");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por palabras clave");
            System.out.println("6. Eliminar documento");
            System.out.println("7. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    editar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    buscarPorAutor();
                    break;
                case 5:
                    buscarPorPalabrasClave();
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

}