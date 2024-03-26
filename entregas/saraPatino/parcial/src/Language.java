package src;

class Language {
    private int id;
    private String keyword;

    public Language(int id, String keyword) {
        this.id = id;
        this.keyword = keyword;
    }

    public int getId() {
        return id;
    }

    public String getLanguage() {
        return keyword;
    }

    @Override
    public String toString() {
        return "> Language{" +
               "id=" + id +
               ", keyword='" + keyword + '\'' +
               '}';
    }

}