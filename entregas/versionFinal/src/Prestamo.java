import java.time.LocalDate;

public class Prestamo {
    private Usuario usuario;
    private Documento documento;
    private LocalDate fechaDevolucion;

    public Prestamo(Usuario usuario, Documento documento, LocalDate fechaDevolucion) {
        this.usuario = usuario;
        this.documento = documento;
        this.fechaDevolucion = fechaDevolucion;
    }    

    public Usuario getUsuario(){
        return usuario;
    }

    public Documento getDocumento() {
        return documento;
    }

    public LocalDate getFechaDevolucion() {
        return fechaDevolucion;
    }
    
}
