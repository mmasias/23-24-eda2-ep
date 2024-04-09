package mayenSergio;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private GeneradorDocumentos generador;
    private Scanner scanner = new Scanner(System.in);

    public Menu(GeneradorDocumentos generador) {
        this.generador = generador;
    }

    public void mostrarMenu() {
        int opcion;
        List<Documento> documentos = null;

        do {
            System.out.println("Seleccione una opción:");            
            System.out.println("1. Crear otro documento");
            System.out.println("2. Editar documento");
            System.out.println("3. Eliminar documento");
            System.out.println("4. Buscar documento");
            System.out.println("5. Mostrar lista de documentos");
            System.out.println("6. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    if (documentos == null) {
                        documentos = generador.crearNuevoDocumento();
                    } else {
                        documentos.addAll(generador.crearNuevoDocumento());
                        System.out.println("Nuevos documentos creados.\n");
                    }
                    break;
                case 2:
                    if (documentos != null) {
                        generador.editarDocumento(documentos);
                    } else {
                        System.out.println("No hay documentos para editar. Crea uno primero.\n");
                    }
                    break;
                case 3:
                    if (documentos != null) {
                        generador.eliminarDocumento(documentos);
                    } else {
                        System.out.println("No hay documentos para eliminar. Crea uno primero.\n");
                    }
                    break;
                case 4:
                    if (documentos != null) {
                        generador.buscarDocumento(documentos);
                    } else {
                        System.out.println("No hay documentos para buscar. Crea uno primero.\n");
                    }
                    break;
                case 5:
                    if (documentos != null) {
                        System.out.println("\nLista de documentos:");
                        for (Documento documento : documentos) {
                            System.out.println(documento);
                        }
                        System.out.println();
                    } else {
                        System.out.println("No hay documentos para mostrar. Crea uno primero.\n");
                    }
                    break;
                case 6:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.\n");
            }
        } while (opcion != 6);
    }
}
