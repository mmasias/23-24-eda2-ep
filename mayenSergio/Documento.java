package mayenSergio;

public class Documento {
    private String titulo;
    private TipoDocumento tipo;
    private Autor autor;

    public Documento(String titulo, TipoDocumento tipo, Autor autor) {
        this.titulo = titulo;
        this.tipo = tipo;
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "titulo='" + titulo + '\'' +
                ", tipo=" + tipo +
                ", autor=" + autor +
                '}';
    }

    public enum TipoDocumento {
        LIBRO,
        REVISTA,
        ARTICULO,
        PAPER
    }
}