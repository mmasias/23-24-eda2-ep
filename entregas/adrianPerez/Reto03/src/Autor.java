public class Autor {
    private String nombre;
    private int id;

    public String getNombre() {
        return nombre;
    }

    public int getid() {
        return id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setid(int id) {
        this.id = id;
    }

    public Autor(String nombre, int id) {
        this.nombre = nombre;
        this.id = id;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nombre='" + nombre + '\'' + '}';
    }
}