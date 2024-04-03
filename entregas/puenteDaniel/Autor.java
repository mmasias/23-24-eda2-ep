package puenteDaniel;
public class Autor {
    private String nombre, apellido;
    private int id;

    public Autor(String nombre, String apellido, int id) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.id = id;
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

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString(){
        return "Nombre: "+nombre+" Apellido: "+apellido+" Id: "+id;
    }
}
