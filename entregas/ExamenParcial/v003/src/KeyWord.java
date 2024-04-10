package v003.src;

public class KeyWord {
    private int id;
    private String name;

    public KeyWord(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "> KeyWord{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
