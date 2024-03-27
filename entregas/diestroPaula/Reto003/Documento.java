package entregas.diestroPaula.Reto003;

public class Documento {
    private int id;
    private String titulo;
    private String añoPublicacion;
    private String tipo;

    public Documento(int id, String titulo, String añoPublicacion, String tipo) {
        this.id = id;
        this.titulo = titulo;
        this.añoPublicacion = añoPublicacion;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
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