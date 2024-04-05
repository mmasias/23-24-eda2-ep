
public class Autor {

    private String nombre;
    private String apellido;
    private int idAutor;
    

    public Autor(String nombre, String apellido, int idAutor) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.idAutor = idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getId() {
        return idAutor;
    }

    public void setId(int idAutor) {
        this.idAutor = idAutor;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", idAutor=" + idAutor +
                '}';
    }
}
