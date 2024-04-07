import java.util.ArrayList;
import java.util.List;

class Busqueda {
    private List<Libro> libros;

    public Busqueda(List<Libro> libros) {
        this.libros = new ArrayList<>(libros);
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        List<Libro> resultado = new ArrayList<>();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            for (int j = 0; j < libro.getAutores().size(); j++) {
                if (libro.getAutores().get(j).getName().equalsIgnoreCase(nombreAutor)) {
                    resultado.add(libro);
                    break;
                }
            }
        }
        return resultado;
    }

    public List<Libro> buscarPorAño(int año) {
        List<Libro> resultado = new ArrayList<>();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getAñoPublicacion() == año) {
                resultado.add(libros.get(i));
            }
        }
        return resultado;
    }

    public List<Libro> buscarPorPalabraClave(String palabraClave) {
        List<Libro> resultado = new ArrayList<>();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getPalabrasClave().contains(palabraClave)) {
                resultado.add(libros.get(i));
            }
        }
        return resultado;
    }

    public List<Libro> buscarPorTipo(TipoLibro tipo) {
        List<Libro> resultado = new ArrayList<>();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTipoLibro() == tipo) {
                resultado.add(libros.get(i));
            }
        }
        return resultado;
    }
}
