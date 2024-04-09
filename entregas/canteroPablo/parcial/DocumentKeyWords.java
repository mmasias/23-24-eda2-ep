public class DocumentKeyWords {
    private int documentId;
    private int keyWordId;
    public DocumentKeyWords(int documentId, int keyWordId) {
        this.documentId = documentId;
        this.keyWordId = keyWordId;
    }
    public int getDocumentId() {
        return documentId;
    }
    public int getKeyWordId() {
        return keyWordId;
    }

}
