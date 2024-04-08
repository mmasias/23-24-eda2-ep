import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public class BibliotecaDigital {
    private Map<String, Documento> documentos;

    public BibliotecaDigital() {
        this.documentos = new HashMap<>();
    }

    public void agregarDocumento(Documento documento) {
        documentos.put(documento.getTitulo(), documento);
    }

    public Documento buscarPorTitulo(String titulo) {
        return documentos.get(titulo);
    }

    public boolean eliminarDocumento(String titulo) {
        return documentos.remove(titulo) != null;
    }

    public List<Documento> buscarPorCriterio(String criterio, String valor) {
        List<Documento> resultado = new ArrayList<>();
        if ("titulo".equals(criterio)) {
            Documento doc = documentos.get(valor);
            if (doc != null) {
                resultado.add(doc);
            }
        }
        
        return resultado;
    }
    public List<Documento> getDocumentos() {
        return new ArrayList<>(documentos.values());
    }
    

}
