public class Keyword {
    private static int nextId = 1;
    private int keywordId;
    private String keyword;

    protected Keyword( String keyword) {
        this.keywordId = nextId++;
        this.keyword = keyword;
    }

    private Keyword(int keywordId, String keyword) {
        this.keywordId = keywordId;
        this.keyword = keyword;
    }

    protected int getKeywordId() {
        return keywordId;
    }

    private void setKeywordId(int keywordId) {
        this.keywordId = keywordId;
    }

    protected String getKeyword() {
        return keyword;
    }

    private void setKeyword(String keyword) {
        this.keyword = keyword;
    }
}