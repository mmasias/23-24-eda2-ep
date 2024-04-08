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

    public void agregarDocumento() {
        Documento documento = new Documento();
        do {
            documento.setIsbn(scanner.nextLong("Ingrese el ISBN del documento: "));
            if (encontrarDocumento(documento.getIsbn()) != null) {
                System.out.println("El ISBN ya existe. Intente con otro.");
            }
        } while (encontrarDocumento(documento.getIsbn()) != null);
        documento.setTitulo(scanner.nextLine("Ingrese el titulo del documento: "));
        documento.setAnoDePublicacion(scanner.nextInt("Ingrese el año de publicacion", 0, 2024));
        int opcion = scanner.nextInt("Ingrese el tipo de documento\n1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER", 1, 4);
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
            respuesta = scanner.nextLine("Desea agregar autores? (si-no)");
            if (respuesta.equals("no")) {
                break;
            } else if (respuesta.equals("si")) {
                listarAutores();
                respuesta = scanner.nextLine("Escoja de la lista o escriba 'nuevo' para agregar un nuevo autor:");
                if (respuesta.equals("nuevo")) {
                    Autor autor = agregarAutor();
                    añadirRelacion(autor.getId(), documento.getIsbn());
                    System.out.println("Autor agregado exitosamente.");
                } else {
                    try {
                        if (existeAutor(Integer.parseInt(respuesta))) {
                            añadirRelacion(Integer.parseInt(respuesta), documento.getIsbn());
                            System.out.println("Autor agregado exitosamente.");
                        } else {
                            System.out.println("El autor no existe");
                        }
                    } catch (Exception e) {
                        System.out.println("Opción inválida. Intente de nuevo.");
                    }
                }
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (true);
        do {
            respuesta = scanner.nextLine("Desea agregar palabras clave? (si-no)");
            if (respuesta.equals("no")) {
                break;
            } else if (respuesta.equals("si")) {
                respuesta = scanner.nextLine("Escriba la palabra clave:");
                if (!existePalabraClave(respuesta)) {
                    agregarPalabraClave(respuesta, documento.getIsbn());
                } else {
                    System.out.println("La palabra clave ya existe");
                }
            } else {
                System.out.println("Opción inválida. Intente de nuevo.");
            }
        } while (true);
    }

    public Autor agregarAutor() {
        int id;
        String nombre;
        String apellido;
        do {
            id = scanner.nextInt("Ingrese el ID del autor:", 1, Integer.MAX_VALUE);
            if (existeAutor(id)) {
                System.out.println("El autor ya existe. Intente con otro ID.");
            }
        } while (existeAutor(id));

        nombre = scanner.nextLine("Ingrese el nombre del autor:");
        apellido = scanner.nextLine("Ingrese el apellido del autor:");

        Autor autor = new Autor(id, nombre, apellido);
        autores.add(autor);
        return autor;
    }

    public boolean existeAutor(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return true;
            }
        }
        return false;
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
            if (documentoAutor.getDocumentoIsbn() == isbn) {
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
        long isbn = scanner.nextLong("Ingrese el ISBN del documento a modificar:");
        scanner.nextLine();
        Documento documento = encontrarDocumento(isbn);
        if (documento != null) {
            System.out.println("Ha seleccionado modificar el documento: " + documento.getTitulo());
            System.out.println(documento.toString());
            menuModificar();
            int opcion = scanner.nextInt(1, 6);
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
        int idAutor = scanner.nextInt("Ingrese el ID del autor a modificar:", 1, Integer.MAX_VALUE);
        Autor autor = encontrarAutor(idAutor);
        if (autor != null) {
            List<Documento> documentoAutor = encontrarDocumentosPorAutor(idAutor);
            if (!documentoAutor.isEmpty()) {
                System.out.println("Libros del autor " + autor.getNombre() + ":");
                for (int i = 0; i < documentoAutor.size(); i++) {
                    System.out.println((i + 1) + ". " + documentoAutor.get(i).getTitulo());
                }
                int opcion = scanner.nextInt("Ingrese el número del libro a modificar:", 1, documentoAutor.size());
                if (opcion >= 1 && opcion <= documentoAutor.size()) {
                    Documento docSeleccionado = documentoAutor.get(opcion - 1);
                    System.out.println("Ha seleccionado modificar el libro: " + docSeleccionado.getTitulo());
                    System.out.println(docSeleccionado.toString());
                    menuModificar();
                    int opcion2 = scanner.nextInt(1, 6);
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

    public void modificarTitulo(long isbn) {
        scanner.nextLine("Ingrese el nuevo titulo del documento");
        String titulo = scanner.nextLine();
        encontrarDocumento(isbn).setTitulo(titulo);
    }

    public void modificarAnoDePublicacion(long isbn) {
        int anoDePublicacion = scanner.nextInt("Ingrese el nuevo año de publicacion", 0, 2024);
        encontrarDocumento(isbn).setAnoDePublicacion(anoDePublicacion);
    }

    public void modificarTipo(long isbn) {
        int opcion = scanner.nextInt("Ingrese el nuevo tipo de documento\n 1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER", 1, 4);
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

    public void modificarAutores(long isbn) {
        System.out.println("Autores actuales:");
        for (Autor autor : encontrarAutorPorDocumento(isbn)) {
            System.out.println(autor.toString());
        }
        scanner.nextLine("Desea agregar o quitar autores? (agregar/quitar)");
        String respuesta = scanner.nextLine();
        if (respuesta.equals("agregar")) {
            do {
                listarAutores();
                respuesta = scanner.nextLine("Escoja de la lista o escriba 'nuevo' para agregar un nuevo autor:");
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
                respuesta = scanner.nextLine("Desea agregar otro autor? (si-no)");
            } while (respuesta.equals("si"));
        } else if (respuesta.equals("quitar")) {
            int idAutor = scanner.nextInt("Ingrese el ID del autor a eliminar:", 1, Integer.MAX_VALUE);
            quitarAutor(idAutor, isbn);
        }
    }

    public void modificarPalabrasClave(long isbn) {
        System.out.println("Palabras clave actuales:");
        for (String palabra : encontrarPalabrasClavePorDocumento(isbn)) {
            System.out.println(palabra);
        }
        scanner.nextLine("Desea agregar o quitar palabras clave? (agregar/quitar)");
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
        }else {
            System.out.println("Opción inválida. Intente de nuevo.");
        }
    }

    public void listarDocumento() {
        System.out.println("Lista de documentos:\n");
        for (Documento documento : documentos) {
            System.out.println("    " + documento.toString());
        }
    }

    public void listarAutores() {
        System.out.println("Lista de autores:\n");
        for (Autor autor : autores) {
            System.out.println(autor.toString());
        }
    }

    public void eliminarDocumento() {
        long isbn = scanner.nextLong("Ingrese el ISBN del documento a eliminar:");
        Documento documento = encontrarDocumento(isbn);
        if (documento != null) {
            documentos.remove(documento);
            System.out.println("Documento eliminado exitosamente.");
        } else {
            System.out.println("Documento no encontrado.");
        }
    }

    public boolean quitarPalabraClave(String palabra, long isbnDocumento) {
        for (DocumentoPalabra documentoPalabra : documentoPalabras) {
            if (documentoPalabra.getPalabraClave().equalsIgnoreCase(palabra)
                    && documentoPalabra.getDocumentoIsbn() == isbnDocumento) {
                documentoPalabras.remove(documentoPalabra);
                return true;
            }
        }
        return false;

    }

    public boolean quitarAutor(int idAutor, long isbnDocumento) {
        for (DocumentoAutor documentoAutor : documentoAutores) {
            if (documentoAutor.getAutorId() == idAutor && documentoAutor.getDocumentoIsbn() == isbnDocumento) {
                documentoAutores.remove(documentoAutor);
                return true;
            }
        }
        return false;
    }

    public void listarPorAutor() {
        int id = scanner.nextInt("Ingrese el ID del autor:", 1, Integer.MAX_VALUE);
        Autor autor = encontrarAutor(id);
        if (autor != null) {
            List<Documento> documentosAutor = encontrarDocumentosPorAutor(id);
            if (!documentosAutor.isEmpty()) {
                System.out.println("Documentos del autor " + autor.getNombre() + " " + autor.getApellido() + ":");
                for (Documento documento : documentosAutor) {
                    System.out.println(documento.toString());
                }
            } else {
                System.out.println("No se encontraron documentos para este autor.");
            }
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    public void menuModificar() {
        System.out.println("1. Modificar titulo");
        System.out.println("2. Modificar año de publicacion");
        System.out.println("3. Modificar tipo");
        System.out.println("4. Modificar autores");
        System.out.println("5. Modificar palabras clave");
        System.out.println("6. Salir");
    }

    public void menu() {
        boolean salir = false;

        do {
            System.out.println("\n====== GESTIÓN DE DOCUMENTOS ======");
            System.out.println("1. Agregar documento");
            System.out.println("2. Modificar documento por ISBN");
            System.out.println("3. Modificar documento por autor");
            System.out.println("4. Eliminar documento");
            System.out.println("5. Listar documentos");
            System.out.println("6. Listar autores");
            System.out.println("7. Listar documentos por autor");
            System.out.println("8. Salir");

            System.out.print("\nSeleccione una opción: ");
            int opcion = scanner.nextInt(1, 8);

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
                case 5:
                    listarDocumento();
                    break;
                case 6:
                    listarAutores();
                    break;
                case 7:
                    listarPorAutor();
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
