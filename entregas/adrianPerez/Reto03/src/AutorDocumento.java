public class AutorDocumento {
    private int documentoId;
    private int autorId;

    public AutorDocumento(int documentoId, int autorId) {
        this.documentoId = documentoId;
        this.autorId = autorId;
    }

    public int getdocumentoId() {
        return documentoId;
    }

    public int getautorId() {
        return autorId;
    }
}
