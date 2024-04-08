import java.util.List;

public abstract class Documento {
    protected String titulo;
    protected List<String> autores;
    protected int fechaPublicacion;
    protected TipoDocumento tipo;
    protected List<String> palabrasClave;
    private int cantidad;

    public Documento(String titulo, List<String> autores, int anoPublicacion, TipoDocumento tipo, List<String> palabrasClave, int cantidad) {
        this.titulo = titulo;
        this.autores = autores;
        this.fechaPublicacion = anoPublicacion; 
        this.tipo = tipo;
        this.palabrasClave = palabrasClave;
        this.cantidad = cantidad;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(int fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public TipoDocumento getTipo() {
        return tipo;
    }

    public void setTipo(TipoDocumento tipo) {
        this.tipo = tipo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public List<String> getAutores() {
        return autores;
    }
    
}
