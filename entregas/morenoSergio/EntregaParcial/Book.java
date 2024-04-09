public class Book {
    private int id;
    private String titulo;
    private int anioPublicacion;
    private String tipo;

    public Book(int id, String titulo, int anioPublicacion, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.anioPublicacion = anioPublicacion;
        this.tipo = tipo;
    }

    public int getId() {
        return this.id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public int getAnioPublicacion() {
        return this.anioPublicacion;
    }

    public String getTipo() {
        return this.tipo;
    }

    public String toString() {
        return "Libro [ID=" + id + ", Título=" + titulo + ", Año de Publicación=" + anioPublicacion + ", Tipo=" + tipo + "]";
    }
}
