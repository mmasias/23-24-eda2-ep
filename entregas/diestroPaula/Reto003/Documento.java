package entregas.diestroPaula.Reto003;

public class Documento {
    private int id;
    private String titulo;
    private int añoPublicacion;
    private String tipo;

    public Documento(int id, String titulo, int añoPublicacion, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAñoPublicaion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "-> Documento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", añoPublicacion='" + añoPublicacion + '\'' +
                ", tipo='" + tipo + '\'' +
                '}';
    }
}