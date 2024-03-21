package clases;

public  class Documento {
    private long isbn;
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;

    public Documento(long isbn, String titulo, int anoDePublicacion, Tipo tipo) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.añoDePublicacion = anoDePublicacion;
        this.tipo = tipo;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoDePublicacion() {
        return añoDePublicacion;
    }

    public void setAnoDePublicacion(int anoDePublicacion) {
        this.añoDePublicacion = anoDePublicacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
}
