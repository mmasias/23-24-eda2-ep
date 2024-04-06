package entregas.oteroJorge.reto003;

public class Topic {

    private int id;
    private String name;

    public Topic(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreCompleto(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "> Topic{" +
               "id=" + id +
               ", name='" + name + '\'' +
               '}';
    }
    
}
