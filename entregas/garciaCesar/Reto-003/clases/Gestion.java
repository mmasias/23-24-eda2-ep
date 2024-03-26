package clases;

import java.util.List;
import java.util.ArrayList;


public class Gestion {
    private List<Documento> documentos;
    private List<Autor> autores;
    private List<DocumentoPalabra> documentoPalabras;
    private List<DocumentoAutor> documentoAutores;
    private MyScanner scanner;

    public Gestion() {
        this.documentos = new ArrayList<>();
        this.autores = new ArrayList<>();
        this.documentoPalabras = new ArrayList<>();
        this.documentoAutores = new ArrayList<>();
        this.scanner = new MyScanner();
    }

    public void listarDocumento() {
        System.out.println("Lista de documentos:\n");
        for (Documento documento : documentos) {
            System.out.println("    " + documento.toString());
        }
    }

    public void agregarDocumento() {
        Documento documento = new Documento();
        do
        {
            System.out.println("Ingrese el ISBN del documento");
            documento.setIsbn(scanner.nextLong());
            if (encontrarDocumento(documento.getIsbn()) != null) {
                System.out.println("El ISBN ya existe. Intente con otro.");
            }
        } while (encontrarDocumento(documento.getIsbn()) != null);
        System.out.println("Ingrese el titulo del documento");
        scanner.nextLine();
        documento.setTitulo(scanner.nextLine());
        System.out.println("Ingrese el año de publicacion");
        documento.setAnoDePublicacion(scanner.nextInt());
        System.out.println("Ingrese el tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        int opcion = scanner.nextIntInRange(1,4);
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
        } while (true);
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
        } while (true);
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
        int id;
        String nombre;
        String apellido;
    
        do {
            System.out.println("Ingrese el ID del autor");
            id = scanner.nextInt();
            scanner.nextLine();
    
            if (existeAutor(id)) {
                System.out.println("El autor ya existe. Intente con otro ID.");
            }
        } while (existeAutor(id));
    
        System.out.println("Ingrese el nombre del autor");
        nombre = scanner.nextLine();
    
        System.out.println("Ingrese el apellido del autor");
        apellido = scanner.nextLine();
    
        Autor autor = new Autor(id, nombre, apellido);
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

    public void modificarDocumento() {
        System.out.println("Ingrese el ISBN del documento a modificar");
        long isbn = scanner.nextLong();
        scanner.nextLine();
    
        Documento documento = encontrarDocumento(isbn);
        if (documento != null) {
            System.out.println("Ha seleccionado modificar el documento: " + documento.getTitulo());
            System.out.println(documento.toString());
            menuModificar();
            int opcion = scanner.nextIntInRange(1, 6);
            switch (opcion) {
                case 1:
                    modificarTitulo(isbn);
                    break;
                case 2:
                    modificarAnoDePublicacion(isbn);
                    break;
                case 3:
                    modificarTipo(isbn);
                    break;
                case 4:
                    modificarAutores(isbn);
                    break;
                case 5:
                    modificarPalabrasClave(isbn);
                    break;
                case 6:
                    System.out.println("Saliendo...");
                    break;
                default:
                    break;
            }
        } else {
            System.out.println("Documento no encontrado.");
        }
    }
    

    public void modificarDocumentoPorAutor() {
        System.out.println("Ingrese el ID del autor a modificar: ");
        int idAutor = scanner.nextInt();
        Autor autor = encontrarAutor(idAutor);
        if (autor != null) {
            List<Documento> documentoAutor = encontrarDocumentosPorAutor(idAutor);
            if (!documentoAutor.isEmpty()) {
                System.out.println("Libros del autor " + autor.getNombre() + ":");
                for (int i = 0; i < documentoAutor.size(); i++) {
                    System.out.println((i + 1) + ". " + documentoAutor.get(i).getTitulo());
                }
                System.out.println("Seleccione el número del libro que desea modificar:");
                int opcion = scanner.nextInt();
                if (opcion >= 1 && opcion <= documentoAutor.size()) {
                    Documento docSeleccionado = documentoAutor.get(opcion - 1);
                    System.out.println("Ha seleccionado modificar el libro: " + docSeleccionado.getTitulo());
                    System.out.println(docSeleccionado.toString());
                    menuModificar();
                    int opcion2= scanner.nextIntInRange(1, 6);
                    switch (opcion2) {
                        case 1:
                            modificarTitulo(docSeleccionado.getIsbn());
                            break;
                        case 2:
                            modificarAnoDePublicacion(docSeleccionado.getIsbn());
                            break;
                        case 3:
                            modificarTipo(docSeleccionado.getIsbn());
                            break;
                        case 4:
                            modificarAutores(docSeleccionado.getIsbn());
                            break;
                        case 5:
                            modificarPalabrasClave(docSeleccionado.getIsbn());
                            break;
                        case 6:
                            break;
                        default:
                            break;
                    }
                } else {
                    System.out.println("Opción inválida. Intente de nuevo.");
                }
            } else {
                System.out.println("No se encontraron libros para este autor.");
            }
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    public boolean quitarPalabraClave(String palabra, long isbnDocumento){
        for (DocumentoPalabra documentoPalabra : documentoPalabras) {
            if (documentoPalabra.getPalabraClave().equalsIgnoreCase(palabra) && documentoPalabra.getDocumentoIsbn() == isbnDocumento) {
                documentoPalabras.remove(documentoPalabra);
                return true;
            }
        }
        return false;

    }

    public boolean quitarAutor(int idAutor, long isbnDocumento){
        for (DocumentoAutor documentoAutor : documentoAutores) {
            if (documentoAutor.getAutorId() == idAutor && documentoAutor.getDocumentoIsbn() == isbnDocumento) {
                documentoAutores.remove(documentoAutor);
                return true;
            }
        }
        return false;
    }

    public void menuModificar(){
        System.out.println("1. Modificar titulo");
        System.out.println("2. Modificar año de publicacion");
        System.out.println("3. Modificar tipo");
        System.out.println("4. Modificar autores");
        System.out.println("5. Modificar palabras clave");
        System.out.println("6. Salir");
    }

    public void modificarTitulo(long isbn){
        System.out.println("Ingrese el nuevo titulo del documento");
        scanner.nextLine();
        String titulo = scanner.nextLine();
        encontrarDocumento(isbn).setTitulo(titulo);
    }

    public void modificarAnoDePublicacion(long isbn){
        System.out.println("Ingrese el nuevo año de publicacion");
        int anoDePublicacion = scanner.nextInt();
        encontrarDocumento(isbn).setAnoDePublicacion(anoDePublicacion);
    }

    public void modificarTipo(long isbn){
        System.out.println("Ingrese el nuevo tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1:
                encontrarDocumento(isbn).setTipo(Tipo.LIBRO);
                break;
            case 2:
                encontrarDocumento(isbn).setTipo(Tipo.REVISTA);
                break;
            case 3:
                encontrarDocumento(isbn).setTipo(Tipo.ARTICULO);
                break;
            case 4:
                encontrarDocumento(isbn).setTipo(Tipo.PAPER);
                break;
            default:
                break;
        }
    }

    public void modificarAutores(long isbn){
        System.out.println("Autores actuales:");
        for (Autor autor : encontrarAutorPorDocumento(isbn)) {
            System.out.println(autor.toString());
        }
        System.out.println("Desea agregar o quitar autores? (agregar/quitar)");
        scanner.nextLine();
        String respuesta = scanner.nextLine();
        if (respuesta.equals("agregar")) {
            do {
                listarAutores();
                System.out.println("Escoja de la lista o escriba 'nuevo' para agregar un nuevo autor:");
                respuesta = scanner.nextLine();
                if (respuesta.equals("nuevo")) {
                    Autor autor = agregarAutor();
                    añadirRelacion(autor.getId(), isbn);
                } else {
                    if (existeAutor(Integer.parseInt(respuesta))) {
                        añadirRelacion(Integer.parseInt(respuesta), isbn);

                    } else {
                        System.out.println("El autor no existe");
                    }
                }
                System.out.println("Desea agregar otro autor? (si-no)");
                respuesta = scanner.nextLine();
            } while (respuesta.equals("si"));
        } else if (respuesta.equals("quitar")) {
            System.out.println("Ingrese el ID del autor a quitar:");
            int idAutor = scanner.nextInt();
            quitarAutor(idAutor, isbn);
        }
    }

    public void modificarPalabrasClave(long isbn){
        System.out.println("Palabras clave actuales:");
        for (String palabra : encontrarPalabrasClavePorDocumento(isbn)) {
            System.out.println(palabra);
        }
        System.out.println("Desea agregar o quitar palabras clave? (agregar/quitar)");
        scanner.nextLine();
        String respuesta = scanner.nextLine();
        if (respuesta.equals("agregar")) {
            do {
                System.out.println("Escriba la palabra clave:");
                respuesta = scanner.nextLine();
                if (!existePalabraClave(respuesta)) {
                    agregarPalabraClave(respuesta, isbn);
                } else {
                    System.out.println("La palabra clave ya existe");
                }
                System.out.println("Desea agregar otra palabra clave? (si-no)");
                respuesta = scanner.nextLine();
            } while (respuesta.equals("si"));
        } else if (respuesta.equals("quitar")) {
            System.out.println("Ingrese la palabra clave a quitar:");
            String palabra = scanner.nextLine();
            quitarPalabraClave(palabra, isbn);
        }
    }

    public void eliminarDocumento(){
        System.out.println("Ingrese el ISBN del documento a eliminar");
        long isbn = scanner.nextLong();
        Documento documento = encontrarDocumento(isbn);
        if (documento != null) {
            documentos.remove(documento);
            System.out.println("Documento eliminado exitosamente.");
        } else {
            System.out.println("Documento no encontrado.");
        }
    }

    public void menu() {
        boolean salir = false;

        do {
            System.out.println("\n====== GESTIÓN DE DOCUMENTOS ======");
            System.out.println("1. Agregar documento");
            System.out.println("2. Modificar documento por ISBN");
            System.out.println("3. Modificar documento por autor");
            System.out.println("4. Eliminar documento");
            System.out.println("8. Salir");

            System.out.print("\nSeleccione una opción: ");
            int opcion = scanner.nextIntInRange(1,8);

            switch (opcion) {
                case 1:
                    agregarDocumento();
                    break;
                case 2:
                    modificarDocumento();
                    break;
                case 3:
                    modificarDocumentoPorAutor();
                    break;
                case 4:
                    eliminarDocumento();
                    break;
                case 8:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción inválida. Intente de nuevo.");
                    break;
            }
        } while (!salir);
    }
}
