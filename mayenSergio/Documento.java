package mayenSergio;

public class Documento {
    
    public enum TipoDocumento {
        LIBRO,
        REVISTA,
        ARTICULO,
        PAPER
    }

    private String titulo;
    private TipoDocumento tipo;

    public Documento(String titulo,  TipoDocumento tipo) {
        this.titulo = titulo;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTitulo(String titulo){
        this.titulo = titulo;
    }

    public void setTipo(TipoDocumento tipo){
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Documento{" +
                "titulo='" + titulo + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}