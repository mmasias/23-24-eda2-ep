public class BookAuthor {
    private int idLibro;
    private int idAutor;

    public BookAuthor(int idLibro, int idAutor) {
        this.idLibro = idLibro;
        this.idAutor = idAutor;
    }

    public int getIdLibro() {
        return this.idLibro;
    }

    public int getIdAutor() {
        return this.idAutor;
    }

    // Este método toString es opcional, dependiendo de si necesitas una representación en String de la relación
    public String toString() {
        return "Relación Libro-Autor [ID Libro=" + idLibro + ", ID Autor=" + idAutor + "]";
    }
}
