package src;

class Author {
    private static int contadorIds = 0;
    private int id;
    private String name;

    public Author(String name) {
        this.id = ++contadorIds;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}