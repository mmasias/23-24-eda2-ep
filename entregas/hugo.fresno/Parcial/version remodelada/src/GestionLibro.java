import java.util.ArrayList;
import java.util.List;

public class GestionLibro {
    private List<Libro> libros;
    private static int nextId = 1;

    public GestionLibro() {
        this.libros = new ArrayList<>();
    }

    public void agregarLibro(Libro libro) {
        if (libro != null && libro.getTitulo() != null && !libro.getTitulo().isEmpty()) {
            libro.setId(nextId++);
            libros.add(libro);
            System.out.println("Libro agregado: " + libro.getTitulo() + ". Total de libros: " + libros.size());
        }
    }


    //Buena implementacion de eliminar si los titulos no se repiten
    public boolean eliminarLibro(String titulo) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTitulo().equalsIgnoreCase(titulo)) {
                libros.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean eliminarLibroPorId(int id) {
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getId() == id) {
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


    public List<Libro> getLibros() {
        return new ArrayList<>(libros);
    }

    public void mostrarLibros() {
        if (libros.isEmpty()) {
            System.out.println("--No hay libros en la biblioteca.--");
            return;
        }

        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            List<String> nombresAutores = new ArrayList<>();
            for (int j = 0; j < libro.getAutores().size(); j++) {
                Autor autor = libro.getAutores().get(j);
                nombresAutores.add(autor.getName());
            }
            System.out.println("\nTitulo: " + libro.getTitulo());
            System.out.println("Autor(es): " + String.join(", ", nombresAutores));
            System.out.println("Id Libro: " + libro.getId());
            System.out.println("Tipo: " + libro.getTipoLibro());
            System.out.println("Año de Publicación: " + libro.getAñoPublicacion());
            System.out.println("Palabras Clave: " + String.join(", ", libro.getPalabrasClave()));
            System.out.println("-----------------------------------");
        }
    }


    public void mostrarLibrosId() {
        if (libros.isEmpty()) {
            System.out.println("--No hay libros en la biblioteca.--");
            return;
        }

        System.out.println();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            System.out.println("-Titulo: " + libro.getTitulo());
            System.out.println(" Id: " + libro.getId());
            System.out.println();
        }
    }



    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public static int getNextId() {
        return nextId;
    }

    public static void setNextId(int nextId) {
        GestionLibro.nextId = nextId;
    }
}
