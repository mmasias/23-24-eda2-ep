public class Author {
    private static int nextId = 1;
    private int authorId;
    private String authorName;

    protected Author(String authorName) {
        this.authorName = authorName;
        this.authorId = nextId++;
    }

    protected Author(int authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    protected int getAuthorId() {
        return authorId;
    }

    private void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    protected String getAuthorName() {
        return authorName;
    }

    private void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}