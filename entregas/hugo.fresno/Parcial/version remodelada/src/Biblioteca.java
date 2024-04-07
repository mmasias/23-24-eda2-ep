import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private GestionLibro gestionLibro;
    private Busqueda busqueda;

    public Biblioteca() {
        this.gestionLibro = new GestionLibro();
        // Se actualiza la busqueda solo cuando es necesario, no cada vez que se agrega un libro
        this.busqueda = new Busqueda(gestionLibro.getLibros());
    }

    public void agregarLibro(Libro libro) {
        gestionLibro.agregarLibro(libro);
        // Actualizar la búsqueda no es necesario cada vez que se agrega un libro,
        // si Busqueda toma la lista de libros directamente de GestionLibro cada vez que busca
    }

    public void addAuthorToBook(int bookId, Autor nuevoAutor) {
        Libro libro = gestionLibro.buscarLibroPorId(bookId);
        if (libro != null) {
            // En lugar de verificar si el libro ya tiene autores, podrías permitir múltiples autores
            libro.getAutores().add(nuevoAutor);
            System.out.println("Autor añadido con éxito al libro.");
        } else {
            System.out.println("No se encontró el libro con el ID proporcionado.");
        }
    }

    public void mostrarLibros() {
        gestionLibro.mostrarLibros();
    }

    // El método buscarPorAutor ahora se simplifica usando la clase Busqueda
    public List<Libro> buscarPorAutor(String nombreAutor) {
        return busqueda.buscarPorAutor(nombreAutor);
    }

    public List<Libro> buscarPorAnio(int anio) {
        return busqueda.buscarPorAnio(anio);
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
