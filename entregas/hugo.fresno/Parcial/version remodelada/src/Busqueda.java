import java.util.ArrayList;
import java.util.List;

class Busqueda {
    //private List<Libro> libros;
    private GestionLibro gestionLibro;


    public Busqueda(GestionLibro gestionLibro) {
        this.gestionLibro = gestionLibro;
    }

    public List<Libro> buscarPorAutor(String nombreAutor) {
        List<Libro> resultado = new ArrayList<>();
        for (Libro libro : gestionLibro.getLibros()) {
            for (Autor autor : libro.getAutores()) {
                if (autor.getName().equalsIgnoreCase(nombreAutor)) {
                    resultado.add(libro);
                    break;
                }
            }
        }
        return resultado;
    }


    /*public List<Libro> buscarPorAño(int año) {
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
    }*/
}
