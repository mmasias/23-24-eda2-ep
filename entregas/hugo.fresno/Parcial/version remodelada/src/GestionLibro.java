import java.util.ArrayList;
import java.util.List;

public class GestionLibro {
    private List<Libro> libros;
    private static int nextId = 1;

    public GestionLibro() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        if (libro != null && libro.getTitulo() != null && !libro.getTitulo().trim().isEmpty()) {
            libro.setId(nextId++);
            libros.add(libro);
        }
    }


    public boolean eliminarLibro(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                libros.remove(i);
                return true;
            }
        }
        return false;
    }

    public void editarLibro(int idLibro, String nuevoTitulo, List<Autor> nuevosAutores, Integer nuevoAnio, TipoLibro nuevoTipo, List<String> nuevasPalabrasClave) {
        for (Libro libro : libros) {
            if (libro.getId() == idLibro) {
                if (nuevoTitulo != null && !nuevoTitulo.isEmpty()) libro.setTitulo(nuevoTitulo);
                if (nuevosAutores != null) libro.setAutores(nuevosAutores);
                if (nuevoAnio != null) libro.setAñoPublicacion(nuevoAnio);
                if (nuevoTipo != null) libro.setTipoLibro(nuevoTipo);
                if (nuevasPalabrasClave != null) libro.setPalabrasClave(nuevasPalabrasClave);
                System.out.println("Libro actualizado exitosamente.");
                return;
            }
        }
        System.out.println("Libro no encontrado.");
    }



    public Libro buscarLibroPorId(int id) {
        for (Libro libro : libros) {
            if (libro.getId() == id) {
                return libro;
            }
        }
        return null;
    }

    public Libro buscarLibroPorTitulo(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                return libros.get(i);
            }
        }
        return null;
    }

    public void editarLibro(int idLibro, Libro libroModificado) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == idLibro) {
                libros.set(i, libroModificado);
                return;
            }
        }
    }

    public List<Libro> getLibros() {
        return new ArrayList<>(libros);
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
            return;
        }

        for (Libro libro : libros) {
            List<String> nombresAutores = new ArrayList<>();
            for (Autor autor : libro.getAutores()) {
                nombresAutores.add(autor.getName());
            }
            System.out.println("\nTitulo: " + libro.getTitulo());
            System.out.println("Id Libro: "+libro.getId());
            System.out.println("Tipo: " + libro.getTipoLibro());
            System.out.println("Autor(es): " + String.join(", ", nombresAutores));
            System.out.println("Año de Publicación: " + libro.getAñoPublicacion());
            System.out.println("Palabras Clave: " + String.join(", ", libro.getPalabrasClave()));
            System.out.println("-----------------------------------");
        }
    }

    public void mostrarLibrosId() {
        if (libros.isEmpty()) {
            System.out.println("No hay libros en la biblioteca.");
            return;
        }

        for (Libro libro : libros) {
            List<String> nombresAutores = new ArrayList<>();
            for (Autor autor : libro.getAutores()) {
                nombresAutores.add(autor.getName());
            }
            System.out.println("\nTitulo: " + libro.getTitulo());
            System.out.println("Id: "+libro.getId());
            System.out.println();

        }
    }
}
