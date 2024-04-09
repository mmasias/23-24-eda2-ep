package app;

public enum DocumentType {
    BOOK("Libro"),
    MAGAZINE("Revista"),
    ARTICLE("Artículo"),
    SCIENTIFIC_PAPER("Paper Científico");


    private String documentTypeName;
    
    private DocumentType(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

}
