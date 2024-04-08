import java.util.List;

public class Articulo extends Documento {
    public Articulo(String titulo, List<String> autores, int anoPublicacion, TipoDocumento tipo, List<String> palabrasClave, int cantidad) {
        super(titulo, autores, anoPublicacion, tipo, palabrasClave, cantidad);
    }
    
}