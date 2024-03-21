package Models;

public enum DocumentType {
    BOOK("Book"),
    MAGAZINE("Magazine"),
    ARTICLE("Article"),
    SCIENTIFIC_PAPER("Scientific Paper");


    private String documentTypeName;
    
    private DocumentType(String documentTypeName) {
        this.documentTypeName = documentTypeName;
    }

    public String getDocumentTypeName() {
        return documentTypeName;
    }

}
