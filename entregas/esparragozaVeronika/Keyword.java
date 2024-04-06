public class Keyword {
    private static int nextId = 1;
    private int keywordId;
    private String keyword;

    public Keyword( String keyword) {
        this.keywordId = nextId++;
        this.keyword = keyword;
    }

    public Keyword(int keywordId, String keyword) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }

    public int getKeywordId() {
        return keywordId;
    }

    private void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    public String getKeyword() {
        return keyword;
    }

    private void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}