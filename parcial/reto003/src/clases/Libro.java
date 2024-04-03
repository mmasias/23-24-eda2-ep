package clases;

import java.util.ArrayList;
import java.util.Scanner;

public class Libro {
    private int id;
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;
    private ArrayList<String> palabrasClave;

    public Libro(int id, String titulo, int anoDePublicacion, Tipo tipo) {
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

    public void añadirPalabraClave(String palabraClave) {
        this.palabrasClave.add(palabraClave);
    }

    public void eliminarPalabraClave(String palabraClave) {
        this.palabrasClave.remove(palabraClave);
    }

    @Override
    public String toString() {
        return "Libro [id=" + id + ", titulo=" + titulo + ", añoDePublicacion=" + añoDePublicacion + ", tipo=" + tipo
                + ", palabrasClave=" + palabrasClave + "]";
    }

    

    
        

    /*public void modificar() {
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
                    for (Autor autor : autores) {
                        if (autor.getNombre().equals(autorAModificar)) {
                            aux = autor;
                            break;
                        }
                    }
                    if (aux != null) {
                        System.out.println("Ingrese el nuevo nombre del autor:");
                        String nombre = scanner.nextLine();
                        System.out.println("Ingrese el nuevo apellido del autor:");
                        String apellido = scanner.nextLine();
                        Autor nuevo = new Autor(nombre, apellido);
                        autores.remove(aux);
                        autores.add(nuevo);
                    }
                    this.setAutores(autores);
                    break;
                case 5:
                    System.out.println("Ingrese la palabra clave a modificar:");
                    String palabraClave = scanner.nextLine();
                    ArrayList<String> palabras = this.getPalabrasClave();
                    int posicion = palabras.indexOf(palabraClave);
                    if (posicion >= 0) {
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
        } while (!salir);
    }
    */
}
