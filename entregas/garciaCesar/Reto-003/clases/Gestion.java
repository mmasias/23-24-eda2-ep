package clases;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Gestion {
    private List<Documento> documentos;
    private List<Autor> autores;
    private List<DocumentoPalabra> documentoPalabras;
    private List<DocumentoAutor> documentoAutores;
    private Scanner scanner;

    public Gestion() {
        this.documentos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.documentoPalabras = new ArrayList<>();
        this.documentoAutores = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }

    public void listarDocumento() {
        System.out.println("Lista de documentos:\n");
        for (Documento documento : documentos) {
            System.out.println("    " + documento.toString());
        }
    }

    public void agregarDocumento() {
        Documento documento = new Documento();
        System.out.println("Ingrese el ISBN del documento");
        documento.setIsbn(scanner.nextLong());
        System.out.println("Ingrese el titulo del documento");
        documento.setTitulo(scanner.nextLine());
        System.out.println("Ingrese el año de publicacion");
        documento.setAnoDePublicacion(scanner.nextInt());
        System.out.println("Ingrese el tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                documento.setTipo(Tipo.LIBRO);
                break;
            case 2:
                documento.setTipo(Tipo.REVISTA);
                break;
            case 3:
                documento.setTipo(Tipo.ARTICULO);
                break;
            case 4:
                documento.setTipo(Tipo.PAPER);
                break;
            default:
                break;
        }
        agregarDocumento(documento);
    }

    public void agregarDocumento(Documento documento) {
        documentos.add(documento);
        System.out.println("Documento agregado exitosamente.");
        String respuesta = null;
        do {
            System.out.println("Desea agregar autores? (si-no)");
            respuesta = scanner.nextLine();
            if (respuesta.equals("no")) {
                break;
            }
            listarAutores();
            System.out.println("Escoja de la lista o escriba 'nuevo' para agregar un nuevo autor:");
            respuesta = scanner.nextLine();
            if (respuesta.equals("nuevo")) {
                Autor autor = agregarAutor();
                añadirRelacion(autor.getId(), documento.getIsbn());
            } else {
                if (existeAutor(Integer.parseInt(respuesta))) {
                    añadirRelacion(Integer.parseInt(respuesta), documento.getIsbn());

                } else {
                    System.out.println("El autor no existe");
                }
            }
        } while (respuesta.equals("si"));
        do {
            System.out.println("Desea agregar palabras clave? (si-no)");
            respuesta = scanner.nextLine();
            if (respuesta.equals("no")) {
                break;
            }
            System.out.println("Escriba la palabra clave:");
            respuesta = scanner.nextLine();
            if (!existePalabraClave(respuesta)) {
                agregarPalabraClave(respuesta, documento.getIsbn());
            } else {
                System.out.println("La palabra clave ya existe");
            }
        } while (respuesta.equals("si"));
    }

    public boolean existeAutor(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return true;
            }
        }
        return false;
    }

    public void listarAutores() {
        System.out.println("Lista de autores:\n");
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

    public Autor agregarAutor() {
        Autor autor = new Autor();
        System.out.println("Ingrese el ID del autor");
        autor.setId(scanner.nextInt());
        System.out.println("Ingrese el nombre del autor");
        autor.setNombre(scanner.nextLine());
        System.out.println("Ingrese el apellido del autor");
        autor.setApellido(scanner.nextLine());
        autores.add(autor);
        return autor;
    }

    public boolean existePalabraClave(String palabra) {
        for (DocumentoPalabra documentoPalabra : documentoPalabras) {
            if (documentoPalabra.getPalabraClave().equals(palabra)) {
                return true;
            }
        }
        return false;
    }

    public void añadirRelacion(int idAutor, long isbnDocumento) {
        DocumentoAutor documentoAutor = new DocumentoAutor(idAutor, isbnDocumento);
        documentoAutores.add(documentoAutor);
    }

    public void agregarPalabraClave(String palabra, long isbnDocumento) {
        DocumentoPalabra documentoPalabra = new DocumentoPalabra(isbnDocumento, palabra);
        documentoPalabras.add(documentoPalabra);

    }

    public Documento encontrarDocumento(long isbn) {
        for (Documento documento : documentos) {
            if (documento.getIsbn() == isbn) {
                return documento;
            }
        }
        return null;
    }

    public Autor encontrarAutor(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    public List<Autor> encontrarAutorPorDocumento(long isbn) {
        List<Autor> autoresDocumento = new ArrayList<>();
        for (DocumentoAutor documentoAutor : documentoAutores) {
            if (documentoAutor.getDocumentoIsbn()== isbn) {
                autoresDocumento.add(encontrarAutor(documentoAutor.getAutorId()));
            }
        }
        return autoresDocumento;
    }

    public List<String> encontrarPalabrasClavePorDocumento(long isbn) {
        List<String> palabrasClaveDocumento = new ArrayList<>();
        for (DocumentoPalabra documentoPalabra : documentoPalabras) {
            if (documentoPalabra.getDocumentoIsbn() == isbn) {
                palabrasClaveDocumento.add(documentoPalabra.getPalabraClave());
            }
        }
        return palabrasClaveDocumento;
    }

    public List<Documento> encontrarDocumentosPorAutor(int idAutor) {
        List<Documento> documentosAutor = new ArrayList<>();
        for (DocumentoAutor documentoAutor : documentoAutores) {
            if (documentoAutor.getAutorId() == idAutor) {
                documentosAutor.add(encontrarDocumento(documentoAutor.getDocumentoIsbn()));
            }
        }
        return documentosAutor;
    }

    public void modificar() {
        System.out.println("Ingrese el titulo del documento a modificar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Documento documento : documentos) {
            if (documento.getTitulo().equals(titulo)) {
                System.out.println("Ingrese el nuevo titulo del documento");
                String nuevoTitulo = sc.nextLine();
                documento.setTitulo(nuevoTitulo);
                System.out.println("Ingrese el nuevo año de publicacion");
                int nuevoAño = sc.nextInt();
                documento.setAnoDePublicacion(nuevoAño);
                System.out.println("Ingrese el nuevo tipo de documento");
                System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
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
                        tipo = Tipo.PAPER;
                        break;
                    default:
                        break;
                }
                documento.setTipo(tipo);
                boolean agregar = true;
                do {
                    System.out.println("Ingrese el nombre del autor");
                    Scanner sc2 = new Scanner(System.in);
                    String nombre = sc2.nextLine();
                    System.out.println("Ingrese el apellido del autor");
                    String apellido = sc2.nextLine();
                    Autor autor = new Autor(nombre, apellido);
                    ArrayList<Autor> autores = new ArrayList<Autor>();
                    autores.add(autor);
                    documento.setAutores(autores);
                    System.out.println("Desea agregar otro autor? (si-no)");
                    String respuesta = sc2.nextLine();
                    if (respuesta.equals("no")) {
                        agregar = false;
                    } else {
                        agregar = true;
                    }
                } while (agregar);
            }

            String palabra = "";
            do {
                System.out.println("Introduzca palabras clave (fin-para terminar)");
                palabra = sc.nextLine();
                if (!palabra.equals("fin")) {
                    documento.añadirPalabraClave(palabra);
                }
            } while (!palabra.equals("fin"));
        }
    }

    public void modificarAutor(String nombreAutor) {
        Scanner sc = new Scanner(System.in);
        boolean autorEncontrado = false;
        ArrayList<Documento> documentosDelAutor = new ArrayList<>();

        for (Documento documento : documentos) {
            for (Autor autor : documento.getAutores()) {
                if (autor.getNombre().equals(nombreAutor)) {
                    documentosDelAutor.add(documento);
                    autorEncontrado = true;
                }
            }
        }

        if (!autorEncontrado) {
            System.out.println("No se encontró ningún autor con ese nombre.");
            return;
        }

        System.out.println("Documentos del autor '" + nombreAutor + "':");
        int index = 1;
        for (Documento doc : documentosDelAutor) {
            System.out.println(index++ + ". " + doc.getTitulo());
        }

        System.out.println("Seleccione el número del documento que desea modificar:");
        int opcion = sc.nextInt();

        if (opcion < 1 || opcion > documentosDelAutor.size()) {
            System.out.println("Opción no válida.");
            return;
        }

        Documento documentoSeleccionado = documentosDelAutor.get(opcion - 1);

        System.out.println("Ingrese el nuevo titulo del documento:");
        sc.nextLine();
        String nuevoTitulo = sc.nextLine();
        documentoSeleccionado.setTitulo(nuevoTitulo);

        System.out.println("Ingrese el nuevo año de publicacion:");
        int nuevoAño = sc.nextInt();
        documentoSeleccionado.setAnoDePublicacion(nuevoAño);

        System.out.println("Ingrese el nuevo tipo de documento:");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        opcion = sc.nextInt();
        Tipo nuevoTipo = null;
        switch (opcion) {
            case 1:
                nuevoTipo = Tipo.LIBRO;
                break;
            case 2:
                nuevoTipo = Tipo.REVISTA;
                break;
            case 3:
                nuevoTipo = Tipo.ARTICULO;
                break;
            case 4:
                nuevoTipo = Tipo.PAPER;
                break;
            default:
                break;
        }
        documentoSeleccionado.setTipo(nuevoTipo);

        ArrayList<Autor> autores = new ArrayList<>();
        boolean agregar = true;
        sc.nextLine();
        do {
            System.out.println("Ingrese el nombre del autor:");
            String nombre = sc.nextLine();
            System.out.println("Ingrese el apellido del autor:");
            String apellido = sc.nextLine();
            Autor autor = new Autor(nombre, apellido);
            autores.add(autor);

            System.out.println("Desea agregar otro autor? (si-no)");
            String respuesta = sc.nextLine();
            agregar = respuesta.equalsIgnoreCase("si");
        } while (agregar);
        documentoSeleccionado.setAutores(autores);

        ArrayList<String> palabrasClave = new ArrayList<>();
        String palabra;
        do {
            System.out.println("Introduzca palabras clave (fin-para terminar):");
            palabra = sc.nextLine();
            if (!palabra.equals("fin")) {
                palabrasClave.add(palabra);
            }
        } while (!palabra.equals("fin"));
        documentoSeleccionado.setPalabrasClave(palabrasClave);

        System.out.println("Documento modificado exitosamente.");
    }

    public void buscar() {
        System.out.println("\n Ingrese el titulo del documento a buscar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Documento documento : documentos) {
            if (documento.getTitulo().equals(titulo)) {
                System.out.println("    Titulo: " + documento.getTitulo());
                System.out.println("    Año de publicacion: " + documento.getAnoDePublicacion());
                System.out.println("    Tipo: " + documento.getTipo());
                System.out.println("    Autores: ");
                for (Autor autor : documento.getAutores()) {
                    System.out.println("    " + autor.getNombre() + " " + autor.getApellido());
                }
                System.out.println("    Palabras clave: ");
                for (String palabra : documento.getPalabrasClave()) {
                    System.out.println(palabra);
                }
            }
        }
    }

    public void buscarPorAutor() {
        System.out.println("\n Ingrese el nombre del autor a buscar");
        Scanner sc = new Scanner(System.in);
        String nombre = sc.nextLine();
        for (Documento documento : documentos) {
            for (Autor autor : documento.getAutores()) {
                if (autor.getNombre().equals(nombre)) {
                    System.out.println("    Titulo: " + documento.getTitulo());
                    System.out.println("    Año de publicacion: " + documento.getAnoDePublicacion());
                    System.out.println("    Tipo: " + documento.getTipo());
                    System.out.println("    Autores: ");
                    for (Autor autor2 : documento.getAutores()) {
                        System.out.println("    " + autor2.getNombre() + " " + autor2.getApellido());
                    }
                    System.out.println("    Palabras clave: ");
                    for (String palabra : documento.getPalabrasClave()) {

                    }
                }
            }
        }
    }

    public void buscarPorPalabrasClave() {
        System.out.println("\n Ingrese la palabra clave a buscar");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        for (Documento documento : documentos) {
            for (String palabraClave : documento.getPalabrasClave()) {
                if (palabraClave.equals(palabra)) {
                    System.out.println("    Titulo: " + documento.getTitulo());
                    System.out.println("    Año de publicacion: " + documento.getAnoDePublicacion());
                    System.out.println("    Tipo: " + documento.getTipo());
                    System.out.println("    Autores: ");
                    for (Autor autor : documento.getAutores()) {
                        System.out.println("    " + autor.getNombre() + " " + autor.getApellido());
                    }
                    System.out.println("    Palabras clave: ");
                    for (String palabra2 : documento.getPalabrasClave()) {
                        System.out.println(palabra2);
                    }
                }
            }
        }
    }

    public void eliminar() {
        System.out.println("Ingrese el titulo del documento a eliminar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Documento documento : documentos) {
            if (documento.getTitulo().equals(titulo)) {
                documentos.remove(documento);
                break;
            }
        }
    }

    public void menu() {
        boolean salir = false;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("\n====== GESTIÓN DE DOCUMENTOS ======");
            System.out.println("1. Agregar documento");
            System.out.println("2. Modificar documento");
            System.out.println("3. Buscar documento");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por palabras clave");
            System.out.println("6. Eliminar documento");
            System.out.println("7. Modificar por nombre del autor");
            System.out.println("8. Salir");

            System.out.print("\nSeleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    modificar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    buscarPorAutor();
                    break;
                case 5:
                    buscarPorPalabrasClave();
                    break;
                case 6:
                    eliminar();
                    break;
                case 7:
                    System.out.println("Ingrese el nombre del autor a modificar:");
                    Scanner sc = new Scanner(System.in);
                    String nombreAutor = sc.nextLine();
                    modificarAutor(nombreAutor);
                    break;
                case 8:
                    salir = true;
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (!salir);
    }
}
