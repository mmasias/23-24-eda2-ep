public class Libro extends Documento {

    private String editorial;
    private int numeroPaginas;

    public Libro(String titulo, List<Autor> autores, int anioPublicacion, String editorial, int numeroPaginas) {
        super(titulo, autores, anioPublicacion, TipoDocumento.LIBRO, new ArrayList<>());
        this.editorial = editorial;
        this.numeroPaginas = numeroPaginas;
    }

    // Métodos para obtener y modificar los atributos específicos de un libro
    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + editorial + " (" + numeroPaginas + " pags.)";
    }

}
