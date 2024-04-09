public class AutorDocumento {
    private int documentoId;
    private int autorId;

    public AutorDocumento(int documentoId, int autorId) {
        this.documentoId = documentoId;
        this.autorId = autorId;
    }

    public int getDocumentoId() {
        return documentoId;
    }

    public int getAutorId() {
        return autorId;
    }
}
