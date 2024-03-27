package entregas.diestroPaula.Reto003;

public class Autor {
    private int id;
    private String nombre;

    public Autor(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "-> Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
