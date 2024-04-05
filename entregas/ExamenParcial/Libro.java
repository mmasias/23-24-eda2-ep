import java.util.ArrayList;

public  class Libro {
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;
    private int idLibro;


    public Libro(String titulo, int añoDePublicacion, Tipo tipo, int idLibro) {
        this.titulo = titulo;
        this.añoDePublicacion = añoDePublicacion;
        this.tipo = tipo;
        this.idLibro = idLibro;

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoDePublicacion() {
        return añoDePublicacion;
    }

    public void setAñoDePublicacion(int añoDePublicacion) {
        this.añoDePublicacion = añoDePublicacion;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }


    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", añoDePublicacion=" + añoDePublicacion +
                ", tipo=" + tipo +
                ", idLibro=" + idLibro +
                '}';
    }

    public int getId() {
        return idLibro;
    }

    public void setId(int idLibro) {
        this.idLibro = idLibro;
    }

}