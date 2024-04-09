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

    public String toString() {
        return "Relación Libro-Autor [ID Libro=" + idLibro + ", ID Autor=" + idAutor + "]";
    }
}
