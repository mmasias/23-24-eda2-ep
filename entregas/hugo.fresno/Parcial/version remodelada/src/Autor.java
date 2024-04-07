public class Autor {
    private int id;
    private String name;

    public Autor(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("Autor{id=%d, name='%s'}", id, name);
    }
}
