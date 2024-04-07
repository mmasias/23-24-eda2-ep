import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private GestionLibro gestionLibro;
    private Busqueda busqueda;

    public Biblioteca() {
        this.gestionLibro = new GestionLibro();
        this.busqueda = new Busqueda(gestionLibro.getLibros());
    }

    public void agregarLibro(Libro libro) {
        gestionLibro.agregarLibro(libro);
        this.busqueda = new Busqueda(gestionLibro.getLibros());
    }

    public void addAuthorToBook(int bookId, Autor nuevoAutor) {
        Libro libro = gestionLibro.findBookById(bookId);
        if (libro != null) {
            if (libro.getAutores().isEmpty()) {
                libro.getAutores().add(nuevoAutor);
                System.out.println("Autor añadido con éxito al libro.");
            } else {
                System.out.println("Este libro ya tiene autor(es). No se pueden añadir más autores.");
            }
        } else {
            System.out.println("No se encontró el libro con el ID proporcionado.");
        }
    }

    public void mostrarLibros() {
        gestionLibro.mostrarLibros();
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        List<Libro> librosEncontrados = new ArrayList<>();
        for (int i = 0; i < gestionLibro.getLibros().size(); i++) {
            Libro libro = gestionLibro.getLibros().get(i);
            for (int j = 0; j < libro.getAutores().size(); j++) {
                if (libro.getAutores().get(j).getName().equalsIgnoreCase(nombreAutor)) {
                    librosEncontrados.add(libro);
                    break;
                }
            }
        }
        return librosEncontrados;
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
            for (int i = 0; i < resultados.size(); i++) {
                Libro libro = resultados.get(i);
                List<String> nombresAutores = new ArrayList<>();
                for (int j = 0; j < libro.getAutores().size(); j++) {
                    nombresAutores.add(libro.getAutores().get(j).getName());
                }
                System.out.println(libro.getTitulo() + " - " + String.join(", ", nombresAutores));
            }
        }
    }



}
