package clases;
import java.util.ArrayList;
import java.util.Scanner;

public  class Libro {
    private int id;
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;
    private ArrayList<String> palabrasClave;

    public Libro(int id, String titulo, int anoDePublicacion, ArrayList<Autor> autores, Tipo tipo ) {
        this.id = id;
        this.titulo = titulo;
        this.añoDePublicacion = anoDePublicacion;
        this.tipo = tipo;
        this.palabrasClave = new ArrayList<String>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoDePublicacion() {
        return añoDePublicacion;
    }

    public void setAnoDePublicacion(int anoDePublicacion) {
        this.añoDePublicacion = anoDePublicacion;
    }

    public ArrayList<Autor> getAutores() {
        return autores;
    }

    public void setAutores(ArrayList<Autor> autores) {
        this.autores = autores;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo2) {
        this.tipo = tipo2;
    }

    public ArrayList<String> getPalabrasClave() {
        for (String palabra : palabrasClave) {
            System.out.print(" " + palabra + "\n");
        }
        System.out.println();
        return palabrasClave;
    }
    
    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public void añadirAutor(Autor autor){
        this.autores.add(autor);
    }

    public  void añadirPalabraClave(String palabraClave){
        this.palabrasClave.add(palabraClave);
    }

    public void eliminarAutor(Autor autor){
        this.autores.remove(autor);
    }

    public void eliminarPalabraClave(String palabraClave){
        this.palabrasClave.remove(palabraClave);
    }
    public static Libro crearDocumento(){
        System.out.println("Ingrese el titulo del documento");
        Scanner sc = new Scanner(System.in);
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
        boolean agregar = true;
        ArrayList<Autor> autores = new ArrayList<Autor>();
        do {
            System.out.println("Ingrese el nombre del autor");
            Scanner sc2 = new Scanner(System.in);
            String nombre = sc2.nextLine();
            System.out.println("Ingrese el apellido del autor");
            String apellido = sc2.nextLine();
            Autor autor = new Autor(nombre, apellido);
            autores.add(autor);
            System.out.println("Desea agregar otro autor? (si-no)");
            String respuesta = sc2.nextLine();
            if (respuesta.equals("no")) {
                agregar = false;
            } else {
                agregar = true;
            }
        } while (agregar);
        Libro documento = new Libro(titulo, año, autores, tipo);
        String palabra = "";
        do {
            System.out.println("Introduzca palabras clave (fin-para terminar)");
            palabra = sc.nextLine();
            if (!palabra.equals("fin")) {
                documento.añadirPalabraClave(palabra);
            }

        } while (!palabra.equals("fin"));
        return documento;
    }

    public void modificar() {
        boolean salir = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n====== GESTIÓN DE DOCUMENTOS POR AUTOR ======");
            System.out.println("1. Modificar titulo de documento");
            System.out.println("2. Modificar año de publicacion de documento");
            System.out.println("3. Modificar tipo de documento");
            System.out.println("4. Modificar autor de documento");
            System.out.println("5. Modificar palabras clave de documento");
            System.out.println("6. Salir");
            System.out.println("7. Volver al menú principal");
            System.out.println("Seleccione una opción: "); 
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Ingrese el titulo a modificar:");
                    String titulo = scanner.nextLine();
                    this.setTitulo(titulo);
                    break;
                case 2:
                    System.out.println("Ingrese el nombre del autor a modificar:");
                    int año = scanner.nextInt();
                    this.setAnoDePublicacion(año);
                    break;
                case 3:
                    System.out.println("Ingrese el nuevo Tipo: (1.LIBRO 2.REVISTA 3.ARTICULO 4.PAPEL)");
                    String tipo = scanner.nextLine();
                    this.setTipo(Tipo.valueOf(tipo));
                    break;
                case 4:
                    System.out.println("Ingrese el nombre del autor a modificar:");
                    String autorAModificar = scanner.nextLine();
                    ArrayList<Autor> autores = this.getAutores();
                    int pos = autores.indexOf(autorAModificar);
                    Autor aux = null;
                    for(Autor autor: autores){
                        if(autor.getNombre().equals(autorAModificar)){
                            aux = autor;
                            break;
                        }
                    }
                    if(aux!=null){
                        System.out.println("Ingrese el nuevo nombre del autor:");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese el nuevo apellido del autor:");
                        String apellido = scanner.nextLine();
                        Autor nuevo = new Autor(nombre,apellido);
                        autores.remove(aux);
                        autores.add(nuevo);
                    }
                    this.setAutores(autores);
                    break;
                case 5:
                    System.out.println("Ingrese la palabra clave a modificar:");
                    String palabraClave = scanner.nextLine();
                    ArrayList<String>palabras = this.getPalabrasClave();
                    int posicion = palabras.indexOf(palabraClave);
                    if(posicion>=0){
                        System.out.println("Ingrese la nueva palabra clave:");
                        palabras.set(posicion, scanner.nextLine());
                    }
                    this.setPalabrasClave(palabras);
                    break;
                case 6:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                case 7:
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }while (!salir);
    }
}