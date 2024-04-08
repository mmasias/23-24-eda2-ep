public class AuthorDocument {
    private int authorId;
    private int documentId;

    public AuthorDocument(int authorId, int documentId) {
        this.authorId = authorId;
        this.document = documentId;
    }

    public Author getAuthorId() {
        return authorId;
    }

    public Document getDocumentId() {
        return documentId;
    }
}