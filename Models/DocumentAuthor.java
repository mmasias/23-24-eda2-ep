package Models;

public class DocumentAuthor {
    private int documentId;
    private int authorId;

    public DocumentAuthor(int documentId, int authorId) {
        this.documentId = documentId;
        this.authorId = authorId;
    }

    public int getDocumentId() {
        return documentId;
    }

    public int getAuthorId() {
        return authorId;
    }
}
