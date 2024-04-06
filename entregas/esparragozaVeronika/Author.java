public class Author {
    private static int nextId = 1;
    private int authorId;
    private String authorName;

    public Author(String authorName) {
        this.authorName = authorName;
        this.authorId = nextId++;
    }

    public Author(int authorId, String authorName) {
        this.authorId = authorId;
        this.authorName = authorName;
    }

    public int getAuthorId() {
        return authorId;
    }

    private void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return authorName;
    }

    private void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
}