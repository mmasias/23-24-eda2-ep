public class Author {
    private int id;
    private String nombre;

    public Author(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return this.id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public String toString() {
        return "Autor [ID=" + id + ", Nombre=" + nombre + "]";
    }
}
