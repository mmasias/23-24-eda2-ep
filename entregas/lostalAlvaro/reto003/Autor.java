public class Autor {
    private int id;
    private String nombre, apellido;

    public Autor(int id, String nombre, String apellido) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + ". " + nombre + " " + apellido;
    }
    
}
