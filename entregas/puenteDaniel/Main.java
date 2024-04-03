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
            System.out.println("Elige una opción:");
            System.out.println("1. Añadir texto");
            System.out.println("2. Listar autores");
            System.out.println("3. Buscar texto por título");
            System.out.println("4. Actualizar autor");
            System.out.println("5. Eliminar texto");
            System.out.println("6. Salir");
            System.out.print("Opción: ");
            int opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    gestion.añadirTexto();
                    break;
                case 2:
                    gestion.listarAutores();
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
                    System.out.println("Introduce el ID del autor a actualizar:");
                    int idAutor = sc.nextInt();
                    sc.nextLine(); 
                    System.out.println("Introduce el nuevo nombre del autor:");
                    String nuevoNombre = sc.nextLine();
                    System.out.println("Introduce el nuevo apellido del autor:");
                    String nuevoApellido = sc.nextLine();
                    gestion.actualizarAutor(idAutor, nuevoNombre, nuevoApellido);
                    break;
                case 5:
                    System.out.println("Introduce el ID del texto a eliminar:");
                    int idTexto = sc.nextInt();
                    gestion.eliminarTexto(idTexto);
                    break;
                case 6:
                    System.out.println("Saliendo del programa.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }
        }
    }
}