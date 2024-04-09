package mayenSergio;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Documento> documentos = GeneradorDocumentos.crearNuevoDocumento();
        Menu.mostrarMenu(documentos);
    }
}