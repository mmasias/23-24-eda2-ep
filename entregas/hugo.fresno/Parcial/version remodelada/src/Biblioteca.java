import java.util.ArrayList;
import java.util.List;

public class Biblioteca {
    private GestionLibro gestionLibro;
    private Busqueda busqueda;
    private List<Autor> autores;

    private static int nextAutorId = 1;



    public Biblioteca() {
        this.gestionLibro = new GestionLibro();
        this.busqueda = new Busqueda(this.gestionLibro);
        this.autores = new ArrayList<>();

    }

    public void agregarLibro(Libro libro) {
        gestionLibro.agregarLibro(libro);

    }

    public void agregarAutor(String nombre) {
        Autor autorExistente = buscarAutorPorNombre(nombre);
        if (autorExistente == null) {
            autorExistente = new Autor(getNextAutorId(), nombre);
            autores.add(autorExistente);
            System.out.println("Autor agregado exitosamente con ID: " + autorExistente.getId());
        } else {
            System.out.println("Un autor con ese nombre ya existe. ID: " + autorExistente.getId());
        }
    }


    public Autor buscarAutorPorNombre(String nombre) {
        for (int i = 0; i < autores.size(); i++) {
            Autor autor = autores.get(i);
            if (autor.getName().equalsIgnoreCase(nombre)) {
                return autor;
            }
        }
        return null;
    }


    public int getNextAutorId() {
        return nextAutorId++;
    }

    public GestionLibro getGestionLibro() {
        return this.gestionLibro;
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
    public List<Libro> buscarPorTitulo(String nombreLibro) {
        return  busqueda.buscarPorTitulo(nombreLibro);
    }

    public void imprimirResultadosBusqueda(List<Libro> resultados) {
        if (resultados.isEmpty()) {
            System.out.println("No se encontraron resultados.");
        } else {
            for (int i = 0; i < resultados.size(); i++) {
                Libro libro = resultados.get(i);
                List<String> nombresAutores = new ArrayList<>();
                for (int j = 0; j < libro.getAutores().size(); j++) {
                    Autor autor = libro.getAutores().get(j);
                    nombresAutores.add(autor.getName());
                }
                System.out.println("\n**RESULTADO**");
                System.out.println("Titulo: " + libro.getTitulo());
                System.out.println("Id Libro: " + libro.getId());
            }
        }
    }




}
