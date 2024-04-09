import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class Biblioteca {

    private List<Documento> documentos;
    private List<Autor> autores;
    private List<AutorDocumento> relaciones;
    private Scanner sc = new Scanner(System.in);

    public Biblioteca() {
        this.autores = new ArrayList<>();
        this.documentos = new ArrayList<>();
        this.relaciones = new ArrayList<>();
    }

    public void añadirDocumento() {
        System.out.println("Nombre del documento: ");
        String titulo = sc.nextLine();
        System.out.println("Año de publicación: ");
        int año = sc.nextInt();
        sc.nextLine();
        System.out.println("Tipo de documento: 1. Libro, 2. Revista, 3. Articulo, 4. Papel");
        String tipo = sc.nextLine();
    
        Documento doc = new Documento(documentos.size() + 1, titulo, año, tipo);
    
        System.out.println("Quiere añadir un autor al libro? 1. Si, 2. No");
        int respuesta = sc.nextInt();
        sc.nextLine();
    
        switch (respuesta) {
            case 1:
                añadirAutor(doc);
                break;
            case 2:
                break;
        }
    
        String palabraClave;
        boolean salir = false;
        do {
            System.out.println("Palabra clave: ");
            palabraClave = sc.nextLine();
            doc.añadirPalabraClave(palabraClave);
            System.out.println("Desea añadir otra palabra clave? 1. Si, 2. No");
            respuesta = sc.nextInt();
            sc.nextLine();
            if (respuesta == 2) {
                salir = true;
            }
        } while (!salir);
    
        documentos.add(doc);
    }

    public void añadirAutor(Documento documento) {
        boolean salir = false;
        do {
            System.out.println("Nombre del autor: ");
            String nombre = sc.nextLine();
            Autor autor = new Autor(nombre, autores.size() + 1);
            autores.add(autor);
            relaciones.add(new AutorDocumento(documento.getId(), autor.getId()));
            System.out.println("Desea añadir otro autor? 1. Si, 2. No");
            int respuesta = sc.nextInt();
            sc.nextLine();
            if (respuesta == 2) {
                salir = true;
            }
        } while (!salir);
    }

    public void buscarDocPorId(int id){
        for(Documento doc : documentos){
            if(doc.getId() == id){
                System.out.println(doc);
            }
        }  
    }

    public void buscarAutorPorId(int id){
        for(Autor autor : autores){
            if(autor.getId() == id){
                System.out.println(autor);
            }
        }
    }

    public List<Documento> buscarDocumentosPorAutorId(int autorId) {
        List<Documento> documentosAutor = new ArrayList<>();
    
        for (AutorDocumento relacion : relaciones) {
            if (relacion.getAutorId() == autorId) {
                int documentoId = relacion.getDocumentoId();
                for (Documento documento : documentos) {
                    if (documento.getId() == documentoId) {
                        documentosAutor.add(documento);
                        break;
                    }
                }
            }
        }
        return documentosAutor;
    }

    public List<Autor> buscarAutoresPorDocumentoId(int documentoId) {
        List<Autor> autoresDocumento = new ArrayList<>();
    
        for (AutorDocumento relacion : relaciones) {
            if (relacion.getDocumentoId() == documentoId) {
                int autorId = relacion.getAutorId();
                for (Autor autor : autores) {
                    if (autor.getId() == autorId) {
                        autoresDocumento.add(autor);
                        break;
                    }
                }
            }
        }
    
        return autoresDocumento;
    }
    
    public void eliminarDocumento(int id) {
        Documento documentoAEliminar = null;
        for (Documento documento : documentos) {
            if (documento.getId() == id) {
                documentoAEliminar = documento;
                break;
            }
        }
    
        if (documentoAEliminar != null) {
            documentos.remove(documentoAEliminar);
            eliminarRelacionesAutorDocumento(id);
            System.out.println("Documento eliminado correctamente.");
        } else {
            System.out.println("No se encontró un documento con el ID especificado.");
        }
    }
    
    private void eliminarRelacionesAutorDocumento(int documentoId) {
        relaciones.removeIf(relacion -> relacion.getDocumentoId() == documentoId);
    }

    public void listarAutores() {
        System.out.println("Lista de autores:");
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    public void listarDocumentos() {
        System.out.println("Lista de documentos:");
        for (Documento documento : documentos) {
            System.out.println(documento);
            List<Autor> autoresDocumento = buscarAutoresPorDocumentoId(documento.getId());
            if (!autoresDocumento.isEmpty()) {
                System.out.println("Autores:");
                for (Autor autor : autoresDocumento) {
                    System.out.println("- " + autor.getNombre());
                }
            }
            System.out.println();
        }
    }    

    public void menu() {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("=== MENÚ ===");
            System.out.println("1. Añadir documento");
            System.out.println("2. Eliminar documento");
            System.out.println("3. Buscar documento por ID");
            System.out.println("4. Buscar documentos por autor");
            System.out.println("5. Listar autores");
            System.out.println("6. Listar documentos");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    añadirDocumento();
                    break;
                case 2:
                    System.out.println("Ingrese el ID del documento a eliminar:");
                    int idEliminar = sc.nextInt();
                    sc.nextLine();
                    eliminarDocumento(idEliminar);
                    break;
                case 3:
                    System.out.println("Ingrese el ID del documento a buscar:");
                    int idBuscar = sc.nextInt();
                    sc.nextLine();
                    buscarDocPorId(idBuscar);
                    break;
                case 4:
                    System.out.println("Ingrese el ID del autor:");
                    int idAutor = sc.nextInt();
                    sc.nextLine();
                    List<Documento> documentosAutor = buscarDocumentosPorAutorId(idAutor);
                    if (!documentosAutor.isEmpty()) {
                        System.out.println("Documentos del autor con ID " + idAutor + ":");
                        for (Documento documento : documentosAutor) {
                            System.out.println(documento);
                        }
                    } else {
                        System.out.println("No se encontraron documentos para el autor con ID " + idAutor);
                    }
                    break;
                case 5:
                    listarAutores();
                    break;
                case 6:
                    listarDocumentos();
                    break;
                case 7:
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
            
            System.out.println();
        } while (opcion != 7);
    }
    
}