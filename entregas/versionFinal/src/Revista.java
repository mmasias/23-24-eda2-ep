import java.util.List;

public class Revista extends Documento {
    public Revista(String titulo, List<String> autores, int anoPublicacion, TipoDocumento tipo, List<String> palabrasClave, int cantidad) {
        super(titulo, autores, anoPublicacion, tipo, palabrasClave, cantidad);
    }
    
}