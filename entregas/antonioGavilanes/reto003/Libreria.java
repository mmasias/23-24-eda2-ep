package antonioGavilanes.reto003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Libreria {
    private List<Documento> documentos;
    private List<Autor> autores;
    private List<AutorLibro> relaciones;
    private Scanner scanner;

    public void manage() {
        Scanner scanner = new Scanner(System.in);
        boolean continuar = true;

        while (continuar) {
            System.out.println("======Biblioteca Uneatlantico======");
            System.out.println("1. Crear documento");
            System.out.println("2. Mostrar todos los documentos");
            System.out.println("3. Editar documento");
            System.out.println("4. Eliminar documento");
            System.out.println("5. Buscar documento");
            System.out.println("6. Salir");

            int opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 1:
                    crearDocumento();
                    break;
                case 2:
                    mostrarDocumentos();
                    break;
                case 3:
                    editarDocumento();
                    break;
                case 4:
                    eliminarDocumento();
                    break;
                case 5:
                    buscarDocumento();
                    break;
                case 6:
                    continuar = false;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, selecciona una opción del menú.");
                    break;
            }
        }
    }

    public void startManager() {
        documentos = new ArrayList<>();
        autores = new ArrayList<>();
        relaciones = new ArrayList<>();
        manage();
    }

    private void mostrarDocumentos() {
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos para mostrar.");
        } else {
            System.out.println("Documentos:");
            for (int i = 0; i < documentos.size(); i++) {
                Documento documento = documentos.get(i);
                System.out.println("Documento " + (i + 1) + ":");
                System.out.println("Título: " + documento.getTitulo());
                System.out.println("Autores: " + String.join(", ", documento.getAutores()));
                System.out.println("Año de publicación: " + documento.getAñoPublicacion());
                System.out.println("Tipo de documento: " + documento.getTipoDocumento());
                System.out.println("Palabras clave: " + documento.getPalabrasClave());
            }
        }
    }

    private void crearDocumento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creación de nuevo documento:");
        System.out.println("Ingrese el título del documento:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese los autores del documento (separados por coma):");
        String autoresInput = scanner.nextLine();
        List<String> autores = Arrays.asList(autoresInput.split(","));
        System.out.println("Ingrese el año de publicación del documento:");
        int añoPublicacion = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese el tipo de documento:");
        List<String> tipos = TipoDocumento.getTipos();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i));
        }
        int opcionTipo = scanner.nextInt();
        String tipoDocumento = tipos.get(opcionTipo - 1);
        scanner.nextLine();
        PalabrasClave palabrasClave = new PalabrasClave();
        palabrasClave.agregarPalabra();
        documentos.add(new Documento(documentos.size() + 1, titulo, autores, añoPublicacion, tipoDocumento, palabrasClave.getPalabrasClave()));
        System.out.println("Documento creado con éxito.");
    }

    private void crearDocumento(Documento documento) {
        documentos.add(documento);
    }

    private void crearAutor(Documento documento) {
        for (String autor : documento.getAutores()) {
            Autor autorExistente = buscarAutorPorId(documento.getId());
            if (autorExistente == null) {
                autorExistente = new Autor(autores.size() + 1, autor);
                autores.add(autorExistente);
            }
            crearRelacion(documento.getId(), autorExistente.getId());
        }
    }

    private void crearAutor(Autor autor) {
        autores.add(autor);
    }

    private void crearRelacion(int libroId, int autorId) {
        relaciones.add(new AutorLibro(libroId, autorId));
    }

    private List<Autor> getAutorPorLibroId(int libroId) {
        List<Autor> autores = new ArrayList<>();
        for (AutorLibro relacion : relaciones) {
            if (relacion.getLibroId() == libroId) {
                autores.addAll(getAutorPorLibroId(relacion.getAutorId()));
            }
        }
        return autores;
    }

    private List<Documento> getDocumentosPorAutorId(int autorId) {
        List<Documento> documentos = new ArrayList<>();
        for (AutorLibro relacion : relaciones) {
            if (relacion.getAutorId() == autorId) {
                documentos.addAll(getDocumentosPorAutorId(relacion.getLibroId()));
            }
        }
        return documentos;
    }

    private Documento buscarDocumentoPorId(int DocumentoId) {
        for (Documento documento : documentos) {
            if (documento.getId() == DocumentoId) {
                return documento;
            }
        }
        return null;
    }

    private Autor buscarAutorPorId(int autorId) {
        for (Autor autor : autores) {
            if (autor.getId() == autorId) {
                return autor;
            }
        }
        return null;
    }

    private void listarAutores() {
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }

    public void editarDocumento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Documentos disponibles para editar:");
        for (int i = 0; i < documentos.size(); i++) {
            System.out.println((i + 1) + ". " + documentos.get(i).getTitulo());
        }
        System.out.println("Ingrese el número del documento que desea editar:");
        int numeroDocumento = scanner.nextInt();
        scanner.nextLine();

        if (numeroDocumento < 1 || numeroDocumento > documentos.size()) {
            System.out.println("Número de documento no válido.");
            return;
        }

        Documento documento = documentos.get(numeroDocumento - 1);
        System.out.println("Edición del documento " + numeroDocumento + ":");

        System.out.println("Ingrese el nuevo título del documento:");
        String titulo = scanner.nextLine();
        documento.setTitulo(titulo);

        System.out.println("Ingrese los nuevos autores del documento (separados por coma):");
        String autoresInput = scanner.nextLine();
        List<String> autores = Arrays.asList(autoresInput.split(","));
        documento.setAutores(autores);

        System.out.println("Ingrese el nuevo año de publicación del documento:");
        int añoPublicacion = scanner.nextInt();
        documento.setAñoPublicacion(añoPublicacion);
        scanner.nextLine();

        System.out.println("Ingrese el nuevo tipo de documento:");
        List<String> tipos = TipoDocumento.getTipos();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i));
        }
        int opcionTipo;
        do {
            opcionTipo = scanner.nextInt();
            scanner.nextLine();
            if (opcionTipo < 1 || opcionTipo > tipos.size()) {
                System.out.println("Opción no válida. Intente de nuevo:");
            }
        } while (opcionTipo < 1 || opcionTipo > tipos.size());

        String tipoDocumento = tipos.get(opcionTipo - 1);
        documento.setTipoDocumento(tipoDocumento);

        PalabrasClave palabrasClave = new PalabrasClave();
        palabrasClave.agregarPalabra();
        documento.setPalabrasClave(palabrasClave.getPalabrasClave());

        System.out.println("Documento editado con éxito.");
    }

    public void eliminarDocumento() {
        Scanner scanner = new Scanner(System.in);

        if (documentos.isEmpty()) {
            System.out.println("No hay documentos para eliminar.");
            return;
        }

        System.out.println("Documentos disponibles para eliminar:");
        for (int i = 0; i < documentos.size(); i++) {
            System.out.println((i + 1) + ". " + documentos.get(i).getTitulo());
        }

        System.out.println("Ingrese el número del documento que desea eliminar:");
        int numeroDocumento = scanner.nextInt();
        scanner.nextLine();

        if (numeroDocumento < 1 || numeroDocumento > documentos.size()) {
            System.out.println("Número de documento no válido.");
            return;
        }

        Documento documentoAEliminar = documentos.get(numeroDocumento - 1);

        System.out.println("¿Está seguro de que desea eliminar el siguiente documento?");
        System.out.println("Título: " + documentoAEliminar.getTitulo());
        System.out.println("Año de publicación: " + documentoAEliminar.getAñoPublicacion());
        System.out.println("Tipo de documento: " + documentoAEliminar.getTipoDocumento());
        System.out.println("Palabras clave: " + String.join(", ", documentoAEliminar.getPalabrasClave()));
        System.out.println("Ingrese 'S' para confirmar la eliminación o cualquier otra tecla para cancelar:");
        String confirmacion = scanner.nextLine();

        if (confirmacion.equalsIgnoreCase("S")) {

            documentos.remove(numeroDocumento - 1);
            System.out.println("Documento eliminado con éxito.");
        } else {
            System.out.println("Eliminación cancelada.");
        }
    }

    public void buscarDocumento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Menú de Búsqueda de Documentos:");
        System.out.println("1. Buscar por título");
        System.out.println("2. Buscar por autor");
        System.out.println("3. Buscar por año de publicación");
        System.out.println("4. Buscar por tipo de documento");
        System.out.println("5. Buscar por palabras clave");
        System.out.println("6. Buscar por id");
        System.out.println("7. Regresar al menú principal");

        int opcion = scanner.nextInt();
        scanner.nextLine();

        switch (opcion) {
            case 1:
                buscarPorTitulo();
                break;
            case 2:
                buscarPorAutor();
                break;
            case 3:
                buscarPorAño();
                break;
            case 4:
                buscarPorTipoDocumento();
                break;
            case 5:
                buscarPorPalabrasClave();
                break;
            case 6:
                System.out.println("Ingrese el id del documento a buscar:");
                int id = scanner.nextInt();
                buscarDocumentoPorId(id);
                break;
            case 7:
                break;
            default:
                System.out.println("Opción no válida. Por favor, seleccione una opción del menú.");
                break;
        }
    }

    private void buscarPorTitulo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el título del documento a buscar:");
        String titulo = scanner.nextLine();
        List<Documento> documentosEncontrados = new ArrayList<>();
        for (Documento doc : documentos) {
            if (doc.getTitulo().equalsIgnoreCase(titulo)) {
                documentosEncontrados.add(doc);
            }
        }
        mostrarResultadosBusqueda(documentosEncontrados);
    }

    private void buscarPorAutor() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del autor a buscar:");
        String autor = scanner.nextLine();
        List<Documento> documentosEncontrados = new ArrayList<>();
        for (Documento doc : documentos) {
            if (doc.getAutores().contains(autor)) {
                documentosEncontrados.add(doc);
            }
        }
        mostrarResultadosBusqueda(documentosEncontrados);
    }

    private void buscarPorAño() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el año de publicación a buscar:");
        int año = scanner.nextInt();
        List<Documento> documentosEncontrados = new ArrayList<>();
        for (Documento doc : documentos) {
            if (doc.getAñoPublicacion() == año) {
                documentosEncontrados.add(doc);
            }
        }
        mostrarResultadosBusqueda(documentosEncontrados);
    }

    private void buscarPorTipoDocumento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el tipo de documento a buscar:");
        String tipoDocumento = scanner.nextLine();
        List<Documento> documentosEncontrados = new ArrayList<>();
        for (Documento doc : documentos) {
            if (doc.getTipoDocumento().equalsIgnoreCase(tipoDocumento)) {
                documentosEncontrados.add(doc);
            }
        }
        mostrarResultadosBusqueda(documentosEncontrados);
    }

    private void buscarPorPalabrasClave() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese la palabra clave a buscar:");
        String palabraClave = scanner.nextLine();
        List<Documento> documentosEncontrados = new ArrayList<>();
        for (Documento doc : documentos) {
            if (doc.getPalabrasClave().contains(palabraClave)) {
                documentosEncontrados.add(doc);
            }
        }
        mostrarResultadosBusqueda(documentosEncontrados);
    }

    private void mostrarResultadosBusqueda(List<Documento> documentos) {
        if (documentos.isEmpty()) {
            System.out.println("No se encontraron documentos que coincidan con la búsqueda.");
        } else {
            System.out.println("Documentos encontrados:");
            for (Documento doc : documentos) {
                System.out.println("Título: " + doc.getTitulo());
                System.out.println("Autores: " + String.join(", ", doc.getAutores()));
                System.out.println("Año de publicación: " + doc.getAñoPublicacion());
                System.out.println("Tipo de documento: " + doc.getTipoDocumento());
                System.out.println("Palabras clave: " + String.join(", ", doc.getPalabrasClave()));
                System.out.println();
            }
        }
    }

}
