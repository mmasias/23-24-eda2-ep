package src;

class Keyword {
    private int id;
    private String keyword;

    public Keyword(int id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    @Override
    public String toString() {
        return "> Keyword{" +
               "id=" + id +
               ", keyword='" + keyword + '\'' +
               '}';
    }

}