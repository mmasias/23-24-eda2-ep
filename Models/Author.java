package Models;

public class Author {
    private int id;
    private String name;
    private String surnames;

    public Author(int id, String name, String surnames) {
        this.id = id;
        this.name = name;
        this.surnames = surnames;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurnames() {
        return surnames;
    }

    @Override
    public String toString() {
        String fullAuthorInfo = "Author's Information: Id - " + id + ", Name - " + name + ", Surnames - " + surnames;
        return fullAuthorInfo;
    }
}
