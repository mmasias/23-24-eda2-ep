package clases;

public class DocumentoPalabra {
    private long documentoIsbn;
    private String palabraClave;

    public DocumentoPalabra(long documentoIsbn, String palabraClave) {
        this.documentoIsbn = documentoIsbn;
        this.palabraClave = palabraClave;
    }

    public long getDocumentoIsbn() {
        return documentoIsbn;
    }

    public void setDocumentoIsbn(long documentoIsbn) {
        this.documentoIsbn = documentoIsbn;
    }

    public String getPalabraClave() {
        return palabraClave;
    }

    public void setPalabraClave(String palabraClave) {
        this.palabraClave = palabraClave;
    }
}
