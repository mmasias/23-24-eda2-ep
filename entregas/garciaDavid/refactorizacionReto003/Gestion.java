import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Documento> documentos;
    private ArrayList<Autor> autores;
    private ArrayList<AutorDocumento> autorDocumentos;
    Scanner scanner;

    public Gestion() {
        this.documentos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.autorDocumentos = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    private void listarDocumentos() {

        System.out.println("Lista de Documentos:");
        for (Documento documento : documentos) {
            System.out.println(
                    "ID: " + documento.getId() + ", Año de publicación: " + documento.getAnoDePublicacion()
                            + ", Título: " + documento.getTitulo());
        }

    }

    private void agregarDocumento() {
        System.out.println("Ingrese el titulo del documento");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el año de publicacion");
        int año = scanner.nextInt();

        System.out.println("Ingrese el tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        int opcion = scanner.nextInt();

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
                tipo = Tipo.PAPER;
                break;
            default:
                break;
        }

        System.out.println("Ingrese el id del documento");
        int id = scanner.nextInt();
        Documento documento = new Documento(titulo, año, tipo, id);
        documentos.add(documento);
        scanner.nextLine();
    }

    private void agregarDocumento(Documento documento) {
        documentos.add(documento);
    }

    private void agregarAutor(Documento documento) {

        System.out.println("Ingresa el id  del autor");
        int id = scanner.nextInt();

        System.out.println("Ingresa el nombre del autor");
        String nombre = scanner.nextLine();

        System.out.println("Ingresa el apellido del autor");
        String apellido = scanner.nextLine();
        Autor autor = new Autor(id, nombre, apellido);
    }

    private void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    private void agregarRelacion(int documentoId, int autorId) {
        AutorDocumento autorDoc = new AutorDocumento(documentoId, autorId);
        autorDocumentos.add(autorDoc);

        Documento documento = buscarDocPorId(documentoId);
        if (documento != null) {
            Autor autor = buscarAutorPorId(autorId);
            if (autor != null) {
                agregarAutor(autor);
            }
        }
    }

    private List<Autor> obtenerAutorPorDocumentoId(int documentoId) {
        List<Autor> aut = new ArrayList<>();
        for (AutorDocumento autorDoc : autorDocumentos) {
            if (autorDoc.getDocumentoId() == documentoId) {
                Autor autor = buscarAutorPorId(autorDoc.getAutorId());
                if (autor != null) {
                    aut.add(autor);
                }
            }
        }
        return aut;
    }
    
    private List<Documento> obtenerDocumentoPorAutorId(int autorId) {
        List<Documento> doc = new ArrayList<>();
        for (AutorDocumento autorDoc : autorDocumentos) {
            if (autorDoc.getAutorId() == autorId) {
                Documento documento = buscarDocPorId(autorDoc.getDocumentoId());
                if (documento != null) {
                    doc.add(documento);
                }
            }
        }
        return doc;
    }


    private Documento buscarDocPorId(int id) {
        for (int i = 0; i < documentos.size(); i++) {
            Documento documento = documentos.get(i);
            if (documento.getId() == id) {
                return documento;
            }
        }
        return null;
    }

    private Autor buscarAutorPorId(int id) {
        for (int i = 0; i < autores.size(); i++) {
            Autor autor = autores.get(i);
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    private void listarAutores() {
        System.out.println("Lista de Autores:");
        for (Autor autor : autores) {
            System.out.println(
                    "ID: " + autor.getId() + ", Nombre: " + autor.getNombre() + ", Apellido: " + autor.getApellido());
        }
    }

    public void menu() {
        boolean salir = false;
        do {
            System.out.println("");
            System.out.println("1. Agregar documento");
            System.out.println("2. Listar documento");
            System.out.println("3. Agregar autor");
            System.out.println("4. Listar autor");
            System.out.println("5. Relación libro-autor");
            System.out.println("6. Buscar el autor por documento ID");
            System.out.println("7. Buscar el documento por el ID de autor");
            System.out.println("8. Salir");
            System.out.println("");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregarDocumento();
                    break;
                case 2:
                    listarDocumentos();
                    break;
                case 3:
                    System.out.println("Ingresa el id del autor");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume the newline
                    System.out.println("Ingresa el nombre del autor");
                    String nombre = scanner.nextLine();
                    System.out.println("Ingresa el apellido del autor");
                    String apellido = scanner.nextLine();
                    Autor autor = new Autor(id, nombre, apellido);
                    agregarAutor(autor);
                    break;
                case 4:
                    listarAutores();
                    break;
                case 5:
                    System.out.println("Dime el Id del documento");
                    int bookId = scanner.nextInt();
                    System.out.println("Dime id del autor");
                    int autorId = scanner.nextInt();
                    agregarRelacion(bookId, autorId);
                    break;
                case 6:
                    System.out.println("Ingrese el Id del documento");
                    int bookId2 = scanner.nextInt();
                    List<Autor> autores = obtenerAutorPorDocumentoId(bookId2);
                    for (Autor a : autores) {
                        System.out.println(a);
                    }
                    break;
                case 7:
                    System.out.println("Ingrese el Id del autor");
                    int autorId2 = scanner.nextInt();
                    List<Documento> documentos = obtenerDocumentoPorAutorId(autorId2);
                    for (Documento d : documentos) {
                        System.out.println(d);
                    }
                    break;

                case 8:
                    salir = true;
                    break;
                default:
                    break;
            }
        } while (!salir);
    }
}
