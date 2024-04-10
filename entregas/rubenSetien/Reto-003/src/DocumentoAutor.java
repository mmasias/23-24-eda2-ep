public class DocumentoAutor {
    private int idDocumento;
    private int autorId;

    public DocumentoAutor(int idDocumento, int autorId) {
        this.idDocumento = idDocumento;
        this.autorId = autorId;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public int getAutorId() {
        return autorId;
    }
}
