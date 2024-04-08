import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        BibliotecaDigital biblioteca = new BibliotecaDigital();
        UserManager userManager = new UserManager();
        LoanManager loanManager = new LoanManager();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Agregar documento");
            System.out.println("2. Eliminar documento por título");
            System.out.println("3. Buscar documento por título");
            System.out.println("4. Listar todos los documentos");
            System.out.println("5. Prestar documento a usuario");
            System.out.println("6. Devolver documento por usuario");
            System.out.println("7. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                case 1:
                
                System.out.println("Introduzca el título:");
                String titulo = scanner.nextLine();
                System.out.println("Introduzca el año de publicación:");
                int fechaPublicacion = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Introduzca los autores separados por coma:");
                String autoresStr = scanner.nextLine();
                List<String> autores = new ArrayList<>(Arrays.asList(autoresStr.split("\\s*,\\s*")));

                System.out.println("Seleccione el tipo de documento:");
                for (TipoDocumento tipo : TipoDocumento.values()) {
                    System.out.println(tipo.ordinal() + 1 + ". " + tipo);
                }
                int tipoIndex = scanner.nextInt() - 1;
                scanner.nextLine();
                TipoDocumento tipoSeleccionado = TipoDocumento.values()[tipoIndex];
                System.out.println("Introduzca palabras clave separadas por coma:");
                String palabrasClaveStr = scanner.nextLine();
                List<String> palabrasClave = new ArrayList<>(Arrays.asList(palabrasClaveStr.split("\\s*,\\s*")));
                System.out.println("Introduzca la cantidad que desea agregar de ejemplares:");
                int cantidad = scanner.nextInt();
                scanner.nextLine();

                    Documento nuevoDocumento = null;
                    switch (tipoSeleccionado) {
                    case LIBRO:
                        nuevoDocumento = new Libro(titulo, autores, fechaPublicacion, tipoSeleccionado, palabrasClave, cantidad);
                    break;
                    case REVISTA:
                        nuevoDocumento = new Revista(titulo, autores, fechaPublicacion, tipoSeleccionado, palabrasClave, cantidad);
                    break;
                    case ARTICULO:
                        nuevoDocumento = new Articulo(titulo, autores, fechaPublicacion, tipoSeleccionado, palabrasClave, cantidad);
                    break;
                    case PAPER:
                        nuevoDocumento = new Paper(titulo, autores, fechaPublicacion, tipoSeleccionado, palabrasClave, cantidad);
                    break;
                    }
                    if (nuevoDocumento != null) {
                        biblioteca.agregarDocumento(nuevoDocumento);
                        System.out.println("Documento agregado exitosamente.");
                    } else {
                        System.out.println("Tipo de documento no válido.");
                    }
                    break;

                case 2:
                    System.out.println("Introduzca el título del documento a eliminar:");
                    String tituloEliminar = scanner.nextLine();
                    if (biblioteca.eliminarDocumento(tituloEliminar)) {
                        System.out.println("Documento eliminado.");
                    } else {
                        System.out.println("Documento no encontrado.");
                    }
                    break;
                case 3:
                    System.out.println("Introduzca el título del documento a buscar:");
                    String tituloBuscar = scanner.nextLine();
                    var resultados = biblioteca.buscarPorCriterio("titulo", tituloBuscar);
                    if (!resultados.isEmpty()) {
                        System.out.println("Documentos encontrados:");
                        for (Documento doc : resultados) {
                            System.out.println(doc.getTitulo() + " - " + doc.getCantidad());
                        }
                    } else {
                        System.out.println("No se encontraron documentos.");
                    }
                    break;
                case 4:
                    System.out.println("Documentos en la biblioteca:");
                    for (Documento doc : biblioteca.getDocumentos()) {
                        String nombresAutores = String.join(", ", doc.getAutores());
                        System.out.println(doc.getTitulo() + " - " + doc.getCantidad() + " - Autores: " + nombresAutores);
                      
                    }
                    break;
                    case 5:
                    System.out.println("Ingrese el nombre del usuario:");
                    String nombreUsuario = scanner.nextLine();
                    Usuario usuario = userManager.buscarUsuarioPorNombre(nombreUsuario);
                    if (usuario == null) {
                        usuario = new Usuario(nombreUsuario);
                        userManager.agregarUsuario(usuario);
                    }
                
                    System.out.println("Ingrese el título del documento a prestar:");
                    String tituloPrestar = scanner.nextLine();
                    Documento documento = biblioteca.buscarPorTitulo(tituloPrestar);
                
                    if (documento != null && documento.getCantidad() > 0) {
                        System.out.println("Fecha actual: ["+ LocalDate.now() + "]");
                        System.out.println("Ingrese la fecha de devolución (formato: YYYY-MM-DD):");
                        String fecha = scanner.nextLine();
                        LocalDate fechaDevolucion = LocalDate.parse(fecha);
                        LocalDate hoy = LocalDate.now();
                        
                
                        if (!fechaDevolucion.isBefore(hoy)) {
                            loanManager.agregarPrestamo(usuario, documento, fechaDevolucion);
                            documento.setCantidad(documento.getCantidad() - 1);
                            System.out.println("Documento prestado con éxito.");
                        } else {
                            System.out.println("La fecha de devolución no puede ser anterior a la fecha actual.");
                        }
                    } else {
                        System.out.println("Documento no disponible para préstamo.");
                    }
                    break;
                  
                case 6:
                    System.out.println("Ingrese el nombre del usuario que devuelve el documento:");
                    String nombreUsuarioDevuelve = scanner.nextLine();
                    Usuario usuarioDevuelve = userManager.buscarUsuarioPorNombre(nombreUsuarioDevuelve);
                
                    if (usuarioDevuelve != null) {
                        System.out.println("Ingrese el título del documento a devolver:");
                        String tituloDevolver = scanner.nextLine();
                
                        if (loanManager.devolverDocumento(usuarioDevuelve, tituloDevolver)) {
                            Documento documentoDevuelto = biblioteca.buscarPorTitulo(tituloDevolver);
                            if (documentoDevuelto != null) {
                                documentoDevuelto.setCantidad(documentoDevuelto.getCantidad() + 1); 
                                System.out.println("Documento devuelto con éxito.");
                            }
                        } else {
                            System.out.println("No se encontró el préstamo especificado.");
                        }
                    } else {
                        System.out.println("Usuario no encontrado.");
                    }
                    break;                
                case 7:
                    System.out.println("Saliendo...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.");
                    break;

            }
        }
    }
}
