package mayenSergio;

import java.util.List;
import java.util.Scanner;

public class Menu {

    private Scanner scanner = new Scanner(System.in);
    private GeneradorDocumentos generadorDocumentos = new GeneradorDocumentos();
    private GeneradorAutores generadorAutores = new GeneradorAutores();
    private List<Documento> documentos = null;
    private List<Autor> autores = null;

    public void mostrarMenu() {
        int opcion;

        do {
            System.out.println("=== Menú Principal ===");
            System.out.println("1. Gestionar Documentos");
            System.out.println("2. Gestionar Autores");
            System.out.println("3. Salir");

            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                    gestionarOpcion(opcion);
                    break;
                case 2:
                    gestionarOpcion(opcion);
                    break;
                case 3:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Inténtelo de nuevo.\n");
            }
        } while (opcion != 3);
    }

    private void gestionarOpcion(int opcion) {
        int operacion;

        if(opcion == 1){

            do {
                System.out.println("=== Gestionar Documentos ===");
                System.out.println("1. Crear documento");
                System.out.println("2. Mostrar lista de documentos");
                System.out.println("3. Editar documento");
                System.out.println("4. Eliminar documento");
                System.out.println("5. Buscar documento");
                System.out.println("6. Volver al menú principal");

                operacion = scanner.nextInt();
                scanner.nextLine();

                switch (operacion) {
                    case 1:
                        if (autores != null) {
                            if (documentos == null) {
                                documentos = generadorDocumentos.crearNuevoDocumento(autores);
                            } else {
                                documentos.add((Documento) generadorDocumentos.crearNuevoDocumento(autores));
                                System.out.println("Nuevo documento creado.\n");
                            }
                        } else {
                            System.out.println("No hay autores disponibles. Cree al menos uno primero.\n");
                        }
                        break;
                    case 2:
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
                    case 3:
                        if (documentos != null) {
                            generadorDocumentos.editarDocumento(documentos);
                        } else {
                            System.out.println("No hay documentos para editar. Crea uno primero.\n");
                        }
                        break;
                    case 4:
                        if (documentos != null) {
                            generadorDocumentos.eliminarDocumento(documentos);
                        } else {
                            System.out.println("No hay documentos para eliminar. Crea uno primero.\n");
                        }
                        break;
                    case 5:
                        if (documentos != null) {
                            generadorDocumentos.buscarDocumento(documentos);
                        } else {
                            System.out.println("No hay documentos para buscar. Crea uno primero.\n");
                        }
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.\n");
                }
            } while (operacion != 6);

        } else {

            do {
                System.out.println("=== Gestionar Autores ===");
                System.out.println("1. Crear autor");
                System.out.println("2. Mostrar lista de autores");
                System.out.println("3. Editar autor");
                System.out.println("4. Eliminar autor");
                System.out.println("5. Buscar autor");
                System.out.println("6. Volver al menú principal");

                operacion = scanner.nextInt();
                scanner.nextLine();

                switch (operacion) {
                    case 1:
                        if (autores == null) {
                            autores = generadorAutores.crearNuevoAutor();
                        } else {
                            autores.addAll(generadorAutores.crearNuevoAutor());
                            System.out.println("Nuevos autores creados.\n");
                        }
                        break;
                    case 2:
                        if (autores != null) {
                            System.out.println("\nLista de autores:");
                            for (Autor autor : autores) {
                                System.out.println(autor);
                            }
                            System.out.println();
                        } else {
                            System.out.println("No hay autores para mostrar. Crea uno primero.\n");
                        }
                        break;
                    case 3:
                        if (autores != null) {
                            generadorAutores.editarAutor(autores);
                        } else {
                            System.out.println("No hay autores para editar. Crea uno primero.\n");
                        }
                        break;
                    case 4:
                        if (autores != null) {
                            generadorAutores.eliminarAutor(autores);
                        } else {
                            System.out.println("No hay autores para eliminar. Crea uno primero.\n");
                        }
                        break;
                    case 5:
                        if (autores != null) {
                            generadorAutores.buscarAutor(autores);
                        } else {
                            System.out.println("No hay autores para buscar. Crea uno primero.\n");
                        }
                        break;
                    case 6:
                        return;
                    default:
                        System.out.println("Opción no válida. Inténtelo de nuevo.\n");
                }
            } while (operacion != 6);
        }
        
    }
}
