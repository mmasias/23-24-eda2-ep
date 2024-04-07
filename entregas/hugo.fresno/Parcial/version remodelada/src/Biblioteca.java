import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private GestionLibro gestionLibro;
    private Busqueda busqueda;
    private List<Autor> autores;


    public Biblioteca() {
        this.gestionLibro = new GestionLibro();
        this.busqueda = new Busqueda(gestionLibro.getLibros());
        this.autores = new ArrayList<>();

    }

    public void agregarLibro(Libro libro) {
        gestionLibro.agregarLibro(libro);

    }

    public void agregarAutor(Autor autor) {
        Autor autorExistente = buscarAutorPorNombre(autor.getName());
        if (autorExistente == null) {
            autores.add(autor);
        } else {
            System.out.println("Un autor con ese nombre ya existe.");
        }
    }

    public Autor buscarAutorPorNombre(String nombre) {
        for (Autor autor : autores) {
            if (autor.getName().equalsIgnoreCase(nombre)) {
                return autor;
            }
        }
        return null;
    }

    public void addAuthorToBook(int bookId, Autor nuevoAutor) {
        Libro libro = gestionLibro.buscarLibroPorId(bookId);
        if (libro != null) {
            libro.getAutores().add(nuevoAutor);
            System.out.println("Autor añadido con éxito al libro.");
        } else {
            System.out.println("No se encontró el libro con el ID proporcionado.");
        }
    }

    public Autor buscarAutorPorId(int autorId) {
        for (Autor autor : autores) {
            if (autor.getId() == autorId) {
                return autor;
            }
        }
        return null;
    }

    public void mostrarLibros() {
        gestionLibro.mostrarLibros();
    }
    public void mostrarLibrosId() {
        gestionLibro.mostrarLibrosId();
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        return busqueda.buscarPorAutor(nombreAutor);
    }

    public List<Libro> buscarPorAño(int año) {
        return busqueda.buscarPorAño(año);
    }

    public List<Libro> buscarPorPalabraClave(String palabraClave) {
        return busqueda.buscarPorPalabraClave(palabraClave);
    }

    public List<Libro> buscarPorTipo(TipoLibro tipo) {
        return busqueda.buscarPorTipo(tipo);
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        return gestionLibro.buscarLibroPorTitulo(titulo);
    }

    public void imprimirResultadosBusqueda(List<Libro> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            for (Libro libro : resultados) {
                List<String> nombresAutores = new ArrayList<>();
                for (Autor autor : libro.getAutores()) {
                    nombresAutores.add(autor.getName());
                }
                System.out.println(libro.getTitulo() + " - " + String.join(", ", nombresAutores));
            }
        }
    }
}
