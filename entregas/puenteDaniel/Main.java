package puenteDaniel;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Texto> textos = new ArrayList<>();
        ArrayList<Autor> autores = new ArrayList<>();
        ArrayList<AutorTexto> autoresTextos = new ArrayList<>();
        Gestion gestion = new Gestion(textos, autores, autoresTextos);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenú Principal:");
            System.out.println("1. Gestionar Textos");
            System.out.println("2. Gestionar Autores");
            System.out.println("3. Gestionar AutorTexto");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestionarTextos(gestion, sc);
                    break;
                case 2:
                    gestionarAutores(gestion, sc);
                    break;
                case 3:
                    gestionarAutorTexto(gestion, sc);
                    break;
                case 4:
                    System.out.println("Saliendo del programa.");
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private static void gestionarTextos(Gestion gestion, Scanner sc) {
        while (true) {
            System.out.println("\nGestión de Textos:");
            System.out.println("1. Añadir texto");
            System.out.println("2. Listar textos");
            System.out.println("3. Buscar texto por título");
            System.out.println("4. Eliminar texto");
            System.out.println("5. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestion.añadirTexto();
                    break;
                case 2:
                    gestion.listarTextos();
                    break;
                case 3:
                    System.out.println("Introduce el título del texto a buscar:");
                    String titulo = sc.nextLine();
                    Texto textoEncontrado = gestion.buscarTextoPorTitulo(titulo);
                    if (textoEncontrado != null) {
                        System.out.println("Texto encontrado: " + textoEncontrado);
                    } else {
                        System.out.println("Texto no encontrado.");
                    }
                    break;
                case 4:
                    System.out.println("Introduce el ID del texto a eliminar:");
                    int idTexto = sc.nextInt();
                    gestion.eliminarTexto(idTexto);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private static void gestionarAutores(Gestion gestion, Scanner sc) {
        while (true) {
            System.out.println("\nGestión de Autores:");
            System.out.println("1. Añadir autor");
            System.out.println("2. Listar autores");
            System.out.println("3. Actualizar autor");
            System.out.println("4. Eliminar autor");
            System.out.println("5. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); 

            switch (opcion) {
                case 1:
                    gestion.añadirAutor();
                    break;
                case 2:
                    gestion.listarAutores();
                    break;
                case 3:
                    System.out.println("Introduce el ID del autor a actualizar:");
                    int idAutor = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Introduce el nuevo nombre del autor:");
                    String nuevoNombre = sc.nextLine();
                    System.out.println("Introduce el nuevo apellido del autor:");
                    String nuevoApellido = sc.nextLine();
                    gestion.actualizarAutor(idAutor, nuevoNombre, nuevoApellido);
                    break;
                case 4:
                    System.out.println("Introduce el ID del autor a eliminar:");
                    int idAutorEliminar = sc.nextInt();
                    gestion.eliminarAutor(idAutorEliminar);
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }

    private static void gestionarAutorTexto(Gestion gestion, Scanner sc) {
        while (true) {
            System.out.println("\nGestión de Relaciones Autor-Texto:");
            System.out.println("1. Añadir relación autor-texto");
            System.out.println("2. Listar relaciones autor-texto");
            System.out.println("3. Volver al menú principal");
            System.out.print("Selecciona una opción: ");
            int opcion = sc.nextInt();
            sc.nextLine(); 
    
            switch (opcion) {
                case 1:
                    System.out.println("Introduce el ID del autor:");
                    int idAutor = sc.nextInt();
                    System.out.println("Introduce el ID del texto:");
                    int idTexto = sc.nextInt();
                    sc.nextLine(); 
                    System.out.println("Introduce el tipo de relación (LIBRO|REVISTA|ARTICULO|PAPER):");
                    String tipo = sc.nextLine();
                    gestion.añadirRelacion(idTexto, idAutor, tipo);
                    break;
                case 2:
                    gestion.listarRelacionesAutorTexto();
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
    
}
