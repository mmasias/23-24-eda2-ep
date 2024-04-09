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
        List<Libro> libros = gestionLibro.getLibros();
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
        List<Libro> libros = gestionLibro.getLibros();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getAñoPublicacion() == año) {
                resultado.add(libros.get(i));
            }
        }
        return resultado;
    }


    public List<Libro> buscarPorPalabraClave(String palabraClave) {
        List<Libro> resultado = new ArrayList<>();
        List<Libro> libros = gestionLibro.getLibros();
        for (int i = 0; i < libros.size(); i++) {
            List<String> palabrasClave = libros.get(i).getPalabrasClave();
            for (int j = 0; j < palabrasClave.size(); j++) {
                if (palabrasClave.get(j).equalsIgnoreCase(palabraClave)) {
                    resultado.add(libros.get(i));
                    break;
                }
            }
        }
        return resultado;
    }

    public List<Libro> buscarPorTipo(TipoLibro tipo) {
        List<Libro> resultado = new ArrayList<>();
        List<Libro> libros = gestionLibro.getLibros();
        for (int i = 0; i < libros.size(); i++) {
            if (libros.get(i).getTipoLibro().equals(tipo)) {
                resultado.add(libros.get(i));
            }
        }
        return resultado;
    }
    public List<Libro> buscarPorTitulo(String titulo) {
        List<Libro> resultado = new ArrayList<>();
        List<Libro> libros = gestionLibro.getLibros();
        for (int i = 0; i < libros.size(); i++) {
            Libro libro = libros.get(i);
            if (libro.getTitulo().equalsIgnoreCase(titulo)) {
                resultado.add(libro);
                break;
            }
        }
        return resultado;
    }



}
