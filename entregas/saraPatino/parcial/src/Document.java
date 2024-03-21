package src;

class Document {
    private static int contadorIds = 0;
    private int id;
    private Title title;
    private String type;
    private String language;
    private Author[] authors;
    private String[] keywords;
    private String publishYear;

    public Document(Title title, String type, String language, String publishYear) {
        this.id = ++contadorIds;
        this.title = title;
        this.type = type;
        this.language = language;
        this.publishYear = publishYear;
    }

    public int getId() {
        return id;
    }

    public Title getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public String getLanguage() {
        return language;
    }


    public Author[] getAuthors() {
        return authors;
    }

    public void setAuthors(Author[] authors) {
        this.authors = authors;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }


    public String[] getKeywords() {
        return keywords;
    }


    public String getPublishYear() {
        return publishYear;
    }

}
