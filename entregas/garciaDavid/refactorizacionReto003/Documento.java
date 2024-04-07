import java.util.ArrayList;

public  class Documento {
    private int id;
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;

    public Documento(String titulo, int anoDePublicacion, Tipo tipo, int id ) {
        this.titulo = titulo;
        this.añoDePublicacion = anoDePublicacion;
        this.id=id;
        this.tipo = tipo;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Documento [id=" + id + ", titulo=" + titulo + ", añoDePublicacion=" + añoDePublicacion + ", tipo="
                + tipo + "]";
    }

    

}