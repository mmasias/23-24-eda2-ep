import java.util.List;

public class Libro extends Documento {
    public Libro(String titulo, List<String> autores, int anoPublicacion, TipoDocumento tipo, List<String> palabrasClave, int cantidad) {
        super(titulo, autores, anoPublicacion, tipo, palabrasClave, cantidad);
    }
    
}
