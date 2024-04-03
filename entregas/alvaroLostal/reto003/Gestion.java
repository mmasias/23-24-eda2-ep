import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

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

    public void agregarDocumento() {
        scanner = new Scanner(System.in);
        System.out.println("Ingrese los detalles del documento:");
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        System.out.print("Año: ");
        int año = scanner.nextInt();
        scanner.nextLine();
        Tipo tipo = null;
        boolean inputValido = false;
        while (!inputValido) {
            try {
                System.out.print("Ingrese el tipo del documento (LIBRO, REVISTA, ARTICULO, PAPER): ");
                tipo = Tipo.valueOf(scanner.next().toUpperCase());
                inputValido = true;
            } catch (IllegalArgumentException e) {
                System.out.print("Tipo de documento no válido. Por favor, ingrese uno de los siguientes: LIBRO, REVISTA, ARTICULO, PAPER");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        System.out.print("Ingrese las palabras clave del documento (separadas por comas): ");
        String palabrasClave = scanner.nextLine();
        String[] palabrasClaveArray = palabrasClave.split(",");
        ArrayList<String> palabrasClaveList = new ArrayList<>();
        for (String palabra : palabrasClaveArray) {
            palabrasClaveList.add(palabra.trim());
        }
        Documento documento = new Documento(documentos.size() + 1, titulo, año, palabrasClaveList, tipo);
        agregarDocumento(documento);
    }

    public void listarDocumentos() {
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos en la biblioteca.");
        } else {
            for (Documento documento : documentos) {
                System.out.println(documento);
                System.out.println("Autores:");
                ArrayList<Autor> autores = obtenerAutoresPorId(documento.getId());
                for (Autor autor : autores) {
                    System.out.println("    " + autor);
                }
            }
            System.out.println("-------------------------");
        }
    }

    public void modificarDocumento(Documento documento, String titulo, int año, ArrayList<String> palabrasClave,
            Tipo tipo) {
        documento.setTitulo(titulo);
        documento.setAño(año);
        documento.setPalabrasClave(palabrasClave);
        documento.setTipo(tipo);
    }

    public void modificarDocumento() {
        if (documentos.isEmpty()) {
            System.out.println("No hay documentos en la biblioteca.");
            return;
        }

        listarDocumentos();
        System.out.print("Ingrese el ID del documento que desea modificar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Documento documento = obtenerDocumentoPorId(id);
        if (documento != null) {
            System.out.println("Ingrese los nuevos detalles del documento (Pulse enter para no modificar)");
            System.out.print("Título: ");
            String titulo = scanner.nextLine();
            if (titulo.equals("")) {
                titulo = documento.getTitulo();
            }

            int año = 0;
            System.out.print("Año: ");
            String anio = scanner.nextLine();
            if (!anio.equals("")) {
                try {
                    año = Integer.parseInt(anio);
                } catch (NumberFormatException e) {
                    System.out.println("Año no válido. Manteniendo el año original.");
                    año = documento.getAño();
                }
            } else {
                año = documento.getAño();
            }

            Tipo tipo = null;
            System.out.print("Ingrese el tipo del documento (LIBRO, REVISTA, ARTICULO, PAPER): ");
            String tipoInput = scanner.nextLine().toUpperCase();
            if (!tipoInput.isEmpty()) {
                try {
                    tipo = Tipo.valueOf(tipoInput);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo de documento no válido. Manteniendo el tipo original.");
                    tipo = documento.getTipo();
                }
            } else {
                tipo = documento.getTipo();
            }

            System.out.print("Ingrese las palabras clave del documento (separadas por comas): ");
            String palabrasClave = scanner.nextLine();
            ArrayList<String> palabrasClaveList = new ArrayList<>();
            if (palabrasClave.equals("")) {
                palabrasClaveList = documento.getPalabrasClave();
            } else {
                String[] palabrasClaveArray = palabrasClave.split(",");
                for (String palabra : palabrasClaveArray) {
                    palabrasClaveList.add(palabra.trim());
                }
            }
            modificarDocumento(documento, titulo, año, palabrasClaveList, tipo);
            System.out.println("Documento modificado.");
        } else {
            System.out.println("Documento no encontrado.");

        }
    }

    public void eliminarDocumento(Documento documento) {
        try {
            documentos.remove(documento);
        } catch (Exception e) {
            System.out.println("Error al eliminar documento: " + e.getMessage());
        }
    }

    public void eliminarDocumento() {
        listarDocumentos();
        System.out.print("Ingrese el ID del documento que desea eliminar: ");
        int id = scanner.nextInt();
        Documento documento = obtenerDocumentoPorId(id);
        if (documento != null) {
            eliminarDocumento(documento);
            System.out.println("Documento eliminado.");
        } else {
            System.out.println("Documento no encontrado.");
        }
    }

    public void agregarAutor(Autor autor) {
        autores.add(autor);
    }

    public void agregarAutor(Documento documento) {
        boolean agregarAutor = true;
        ArrayList<Integer> autoresAgregados = new ArrayList<>();
        while (agregarAutor) {
            System.out.println(
                    "Selecciona el ID del autor para asociar con el libro, o introduce 'nuevo' para añadir un nuevo autor:");
            if (!autores.isEmpty()) {
                listarAutores();
            }
            String input = scanner.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre del nuevo autor:");
                String nombre = scanner.nextLine();
                System.out.println("Introduce el apellido del nuevo autor:");
                String apellido = scanner.nextLine();
                Autor nuevoAutor = new Autor(autores.size() + 1, nombre, apellido);
                if (nombreExistente(documento, nombre)) {
                    System.out.println("Este autor ya ha sido asociado al documento.");
                } else {
                    agregarAutor(nuevoAutor);
                    añadirRelacion(documento.getId(), nuevoAutor.getId());
                    System.out.println("Autor nuevo añadido y asociado al libro.");
                    autoresAgregados.add(nuevoAutor.getId());
                }
            } else {
                try {
                    int autorId = Integer.parseInt(input);
                    if (autoresAgregados.contains(autorId)) {
                        System.out.println("Este autor ya ha sido agregado al documento.");
                    } else {
                        añadirRelacion(documento.getId(), autorId);
                        System.out.println("Autor asociado al libro.");
                        autoresAgregados.add(autorId);
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otro autor a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(scanner.nextLine())) {
                agregarAutor = false;
            }
        }
    }

    public void agregarAutor() {
        scanner = new Scanner(System.in);
        System.out.println("Seleccione una opción:");
        System.out.println("1. Añadir autor a documento existente");
        System.out.println("2. Añadir autor a la lista de autores");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        switch (opcion) {
            case 1:
                agregarAutorADocumento();
                break;
            case 2:
                agregarAutorALista();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
    }

    public void agregarAutorADocumento() {
        listarDocumentos();
        scanner = new Scanner(System.in);
        System.out.print("Ingrese el ID del documento al que desea agregar un autor: ");
        int idDocumento = scanner.nextInt();
        scanner.nextLine();
        Documento documento = obtenerDocumentoPorId(idDocumento);
        if (documento != null) {
            System.out.println("Introduce el nombre del nuevo autor:");
            String nombre = scanner.nextLine();
            System.out.println("Introduce el apellido del nuevo autor:");
            String apellido = scanner.nextLine();
            Autor nuevoAutor = new Autor(autores.size() + 1, nombre, apellido);
            if (!nombreExistente(documento, nombre)) {
                agregarAutor(nuevoAutor);
                añadirRelacion(documento.getId(), nuevoAutor.getId());
                System.out.println("Autor nuevo añadido y asociado al documento.");
            } else {
                System.out.println("Este autor ya ha sido asociado al documento.");
            }
        } else {
            System.out.println("Documento no encontrado.");
        }
    }

    public void agregarAutorALista() {
        scanner = new Scanner(System.in);
        System.out.println("Introduce el nombre del nuevo autor:");
        String nombre = scanner.nextLine();
        System.out.println("Introduce el apellido del nuevo autor:");
        String apellido = scanner.nextLine();
        Autor nuevoAutor = new Autor(autores.size() + 1, nombre, apellido);
        agregarAutor(nuevoAutor);
        System.out.println("Autor nuevo añadido a la lista general de autores.");
    }

    public void eliminarAutor(Autor autor) {
        autores.remove(autor);
        for (Relacion relacion : relaciones) {
            if (relacion.getIdAutor() == autor.getId()) {
                relaciones.remove(relacion);
            }
        }
    }

    public void eliminarAutor() {
        if (autores.isEmpty()) {
            System.out.println("No hay autores en la biblioteca.");
            return;
        }
        listarAutores();
        System.out.print("Ingrese el ID del autor que desea eliminar: ");
        scanner = new Scanner(System.in);
        int id = scanner.nextInt();
        Autor autor = obtenerAutorPorId(id);
        if (autor != null) {
            eliminarAutor(autor);
            System.out.println("Autor eliminado.");
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    public void listarAutores() {
        System.out.println("-------------------------");
        for (Autor autor : autores) {
            System.out.println(autor);
        }
        System.out.println("-------------------------");
    }

    public void listarAutoresPorDocumento(Documento documento) {
        ArrayList<Autor> autores = obtenerAutoresPorId(documento.getId());
        System.out.println("-------------------------");
        for (Autor autor : autores) {
            System.out.println(autor);
        }
        System.out.println("-------------------------");
    }

    Documento obtenerDocumentoPorId(int id) {
        for (Documento documento : documentos) {
            if (documento.getId() == id) {
                return documento;
            }
        }
        return null;
    }

    Autor obtenerAutorPorId(int id) {
        for (Autor autor : autores) {
            if (autor.getId() == id) {
                return autor;
            }
        }
        return null;
    }

    ArrayList<Autor> obtenerAutoresPorId(int idDocumento) {
        ArrayList<Autor> autores = new ArrayList<>();

        for (Relacion relacion : relaciones) {
            if (relacion.getIdLibro() == idDocumento) {
                autores.add(obtenerAutorPorId(relacion.getIdAutor()));
            }

        }
        return autores;
    }

    ArrayList<Documento> obtenerDocumentosPorId(int idAutor) {
        ArrayList<Documento> documentos = new ArrayList<>();

        for (Relacion relacion : relaciones) {
            if (relacion.getIdAutor() == idAutor) {
                documentos.add(obtenerDocumentoPorId(relacion.getIdLibro()));
            }

        }
        return documentos;
    }

    public void añadirRelacion(int idDocumento, int idAutor) {
        Relacion relacion = new Relacion(idDocumento, idAutor);
        relaciones.add(relacion);
    }

    private boolean nombreExistente(Documento documento, String nombre) {
        ArrayList<Autor> autoresDocumento = obtenerAutoresPorId(documento.getId());
        for (Autor autor : autoresDocumento) {
            if (autor.getNombre().equalsIgnoreCase(nombre)) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<Documento> buscar(String criterio, String valor) {
        ArrayList<Documento> resultado = new ArrayList<>();

        for (Documento doc : documentos) {
            switch (criterio.toLowerCase()) {
                case "titulo":
                    if (doc.getTitulo().toLowerCase().contains(valor.toLowerCase())) {
                        resultado.add(doc);
                    }
                    break;
                case "anio":
                    if (String.valueOf(doc.getAño()).equals(valor)) {
                        resultado.add(doc);
                    }
                    break;
                case "tipo":
                    if (doc.getTipo().toString().toLowerCase().equals(valor.toLowerCase())) {
                        resultado.add(doc);
                    }
                    break;
                case "palabra clave":
                    for (String palabra : doc.getPalabrasClave()) {
                        if (palabra.toLowerCase().contains(valor.toLowerCase())) {
                            resultado.add(doc);
                            break;
                        }
                    }
                    break;
                case "idautor":
                    ArrayList<Autor> autores = obtenerAutoresPorId(doc.getId());
                    for (Autor autor : autores) {
                        if (String.valueOf(autor.getId()).equals(valor)) {
                            resultado.add(doc);
                        }
                    }   
                    break;
                case "iddocumento":
                    listarAutoresPorDocumento(doc);
                    break;
                default:
                    System.out.println("Criterio de búsqueda no válido.");
                    break;
            }
        }

        return resultado;
    }

    public void buscar() {
        System.out.println("Seleccione el criterio de búsqueda:");
        System.out.println("1. Título");
        System.out.println("2. Año");
        System.out.println("3. Tipo");
        System.out.println("4. Palabra clave");
        System.out.println("5. ID del Autor (Mostrar los documentos asociados a un autor)");
        System.out.println("6. ID del Documento (Mostrar los autores asociados a un documento)");
        System.out.print("Seleccione una opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();
        String criterio = "";
        String valor = "";
        switch (opcion) {
            case 1:
                criterio = "titulo";
                System.out.print("Introduce el título a buscar: ");
                valor = scanner.nextLine();
                break;
            case 2:
                criterio = "anio";
                System.out.print("Introduce el año a buscar: ");
                valor = scanner.nextLine();
                break;
            case 3:
                criterio = "tipo";
                System.out.print("Introduce el tipo a buscar: ");
                valor = scanner.nextLine();
                break;
            case 4:
                criterio = "palabra clave";
                System.out.print("Introduce la palabra clave a buscar: ");
                valor = scanner.nextLine();
                break;
            case 5:
                criterio = "idautor";
                System.out.print("Introduce el ID del autor a buscar: ");
                valor = scanner.nextLine();
                break;
            case 6:
                criterio = "iddocumento";
                System.out.print("Introduce el ID del documento a buscar: ");
                valor = scanner.nextLine();
                break;
            default:
                System.out.println("Opción no válida.");
                break;
        }
        ArrayList<Documento> resultado = buscar(criterio, valor);
        if (resultado.isEmpty() && opcion != 6) {
            System.out.println("No se encontraron documentos que coincidan con la búsqueda.");
        } else if (opcion != 6) {
            System.out.println("Documentos encontrados:");
            for (Documento doc : resultado) {
                System.out.println(doc);
                System.out.println("Autores:");
                ArrayList<Autor> autores = obtenerAutoresPorId(doc.getId());
                for (Autor autor : autores) {
                    System.out.println(autor);
                }
            }
            System.out.println("-------------------------");
        }
    }

    private void mostrarMenu(Gestion biblioteca) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            try {
                System.out.println("\u001B[31m" + "\nBiblioteca de Documentos" + "\u001B[0m");
                System.out.println("1. Agregar documento");
                System.out.println("2. Agregar autor");
                System.out.println("3. Listar documentos");
                System.out.println("4. Listar autores");
                System.out.println("5. Eliminar documento");
                System.out.println("6. Eliminar autor");
                System.out.println("7. Modificar documento");
                System.out.println("8. Buscar");
                System.out.println("0. Salir");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine();

                switch (opcion) {
                    case 1:
                        biblioteca.agregarDocumento();
                        break;
                    case 2:
                        biblioteca.agregarAutor();
                        break;
                    case 3:
                        biblioteca.listarDocumentos();
                        break;
                    case 4:
                        biblioteca.listarAutores();
                        break;
                    case 5:
                        biblioteca.eliminarDocumento();
                        break;
                    case 6:
                        biblioteca.eliminarAutor();
                        break;
                    case 7:
                        biblioteca.modificarDocumento();
                        break;
                    case 8:
                        biblioteca.buscar();
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
}