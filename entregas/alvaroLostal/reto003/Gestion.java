import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.print.Doc;

public class Gestion {
    private ArrayList<Documento> documentos;
    private ArrayList<Autor> autores;
    private ArrayList<Relacion> relaciones;
    Scanner scanner = new Scanner(System.in);

    public Gestion() {
        this.documentos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.relaciones = new ArrayList<>();
    }

    public void agregarDocumento(Documento documento) {
        documentos.add(documento);
        System.out.println("Documento añadido. Deseas agregarle autores? (s/n)");
        String respuesta = scanner.nextLine();
        if ("s".equalsIgnoreCase(respuesta)) {
            agregarAutor(documento);
        }
    }

    public void agregarAutor() {
        System.out.println("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del autor: ");
        String apellido = scanner.nextLine();
        System.out.println("Ingrese el ID del autor: ");
        int id = scanner.nextInt();
        Autor autor = new Autor(nombre, apellido, id);
        agregarAutor(autor);
    }

    public void agregarAutor(int id) {
        System.out.println("Ingrese el nombre del autor: ");
        String nombre = scanner.nextLine();
        System.out.println("Ingrese el apellido del autor: ");
        String apellido = scanner.nextLine();
        Autor autor = new Autor(nombre, apellido, id);
        agregarAutor(autor);
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public void agregarAutor(Documento documento) {
        listarAutores();
        System.out.println("Ingrese el ID del autor que desea agregar: ");
        int id = scanner.nextInt();
        if(buscarAutorPorId(id)==null) {
            agregarAutor(id);
        }
        Relacion relacion = new Relacion(documento.getId(), id);
        relaciones.add(relacion);
    }

    public void listarDocumentos() {
        for (Documento documento : documentos) {
            System.out.println(documento);
        }
    }

    public void listarAutores() {
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    Documento buscarDocumentoPorId(int id) {
        for (Documento documento : documentos) {
            if (documento.getId() == id) {
                return documento;
            }
        }
        return null;
    }

    Autor buscarAutorPorId(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    public void añadirRelacion(Documento documento, Autor autor) {
        Relacion relacion = new Relacion(documento.getId(), autor.getId());
        relaciones.add(relacion);
    }

    public void modificar(Documento documento, String titulo, int año, ArrayList<Autor> autores,
            ArrayList<String> palabrasClave, Tipo tipo) {
        documento.setTitulo(titulo);
        documento.setAño(año);
        documento.setAutores(autores);
        documento.setPalabrasClave(palabrasClave);
        documento.setTipo(tipo);
    }



    public ArrayList<Documento> buscar(String criterio, String valor) {
        ArrayList<Documento> resultado = new ArrayList<>();
        try {
            for (Documento doc : documentos) {
                switch (criterio.toLowerCase()) {
                    case "titulo":
                        if (doc.getTitulo().toLowerCase().contains(valor.toLowerCase())) {
                            resultado.add(doc);
                        }
                        break;
                    case "autor":
                        for (Autor autor : doc.getAutores()) {
                            if (autor.getNombre().toLowerCase().contains(valor.toLowerCase()) ||
                                    autor.getApellido().toLowerCase().contains(valor.toLowerCase())) {
                                resultado.add(doc);
                                break;
                            }
                        }
                        break;
                    case "anio":
                        if(doc.getAño()==Integer.parseInt(valor)){
                            resultado.add(doc);
                        }
                        break;
                    case "tipo":
                        if (doc.getTipo().toString().toLowerCase().equals(valor.toLowerCase())) {
                            resultado.add(doc);
                        }
                        break;
                    case "palabras clave":
                        for (String palabraClave : doc.getPalabrasClave()) {
                            if (palabraClave.toLowerCase().contains(valor.toLowerCase())) {
                                resultado.add(doc);
                                break;
                            }
                        }
                        break;
                    default:
                        System.out.println("Criterio de búsqueda no válido.");
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Error al buscar documentos: " + e.getMessage());
        }
        return resultado;
    }

    public void eliminar(Documento documento) {
        try {
            documentos.remove(documento);
        } catch (Exception e) {
            System.out.println("Error al eliminar documento: " + e.getMessage());
        }
    }

    private void mostrarMenu(Gestion biblioteca) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            try {
                System.out.println("\u001B[31m" + "\nBiblioteca de Documentos" + "\u001B[0m");
                System.out.println("1. Agregar documento");
                System.out.println("2. Buscar documento");
                System.out.println("3. Modificar documento");
                System.out.println("4. Eliminar documento");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        agregarDocumento(biblioteca, scanner);
                        break;
                    case 2:
                        buscarDocumento(biblioteca, scanner);
                        break;
                    case 3:
                        modificarDocumento(biblioteca, scanner);
                        break;
                    case 4:
                        eliminarDocumento(biblioteca, scanner);
                        break;
                    case 0:
                        System.out.println("Saliendo del programa...");
                        break;
                    default:
                        System.out.println("Número no válido. Intente de nuevo.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Formato de entrada no válido. Intente de nuevo.");
                scanner.nextLine();
                opcion = -1;
            }
        } while (opcion != 0);

        scanner.close();
    }

    public static void main(String[] args) {
        Gestion biblioteca = new Gestion();
        biblioteca.mostrarMenu(biblioteca);
        
    }

    private static void buscarDocumento(Gestion biblioteca, Scanner scanner) {
        System.out.print("Ingrese el criterio de búsqueda (titulo, autor, anio, tipo, palabras clave): ");
        String criterio = scanner.nextLine();
        System.out.print("Ingrese el valor a buscar: ");
        String valor = scanner.nextLine();

        ArrayList<Documento> resultado = biblioteca.buscar(criterio, valor);

        if (resultado.isEmpty()) {
            System.out.println("No se encontraron documentos que coincidan con la búsqueda.");
        } else {
            System.out.println("Documentos encontrados:");
            for (Documento doc : resultado) {
                System.out.println("-------------------------");
                System.out.println("ID: " + doc.getId());
                System.out.println("Título: " + doc.getTitulo());
                System.out.println("Tipo: " + doc.getTipo());
                System.out.println("Año: " + doc.getAño());
                System.out.print("Autores: ");
                for (int i = 0; i < doc.getAutores().size(); i++) {
                    Autor autor = doc.getAutores().get(i);
                    System.out.print(autor.getNombre() + " " + autor.getApellido());
                    if (i < doc.getAutores().size() - 1) {
                        System.out.print(", ");
                    }
                }
                System.out.println();
                System.out.println("Palabras Clave: " + doc.getPalabrasClave().toString());

            }
            System.out.println("-------------------------");
        }
    }
}