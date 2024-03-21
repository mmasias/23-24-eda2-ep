package src;

class Title {
    private static int contadorIds = 0;
    private int id;
    private String name;

    public Title(String name) {
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