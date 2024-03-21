package clases;

public class DocumentoAutor {
    private int autorId;
    private long documentoIsbn;

    public DocumentoAutor(int autorId, long documentoIsbn) {
        this.autorId = autorId;
        this.documentoIsbn = documentoIsbn;
    }

    public int getAutorId() {
        return autorId;
    }

    public void setAutorId(int AutorId) {
        this.autorId = autorId;
    }

    public long getDocumentoIsbn() {
        return documentoIsbn;
    }

    public void setDocumentoIsbn(long LibroIsbn) {
        this.documentoIsbn = documentoIsbn;
    }
}
