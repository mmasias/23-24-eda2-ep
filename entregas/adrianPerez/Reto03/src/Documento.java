import java.util.ArrayList;

public class Documento {
    private String titulo;
    private int añoDePublicacion;
    private String tipo;
    private ArrayList<String> palabrasClave;
    private int id;

    public Documento(int id, String titulo, int añoDePublicacion, String tipo ) {
        this.titulo = titulo;
        this.añoDePublicacion = añoDePublicacion;
        this.tipo = tipo;
        this.palabrasClave = new ArrayList<String>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAñoDePublicacion() {
        return añoDePublicacion;
    }

    public void setAñoDePublicacion(int añoDePublicacion) {
        this.añoDePublicacion = añoDePublicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public  void añadirPalabraClave(String palabraClave){
        this.palabrasClave.add(palabraClave);
    }

    public void eliminarPalabraClave(String palabraClave){
        this.palabrasClave.remove(palabraClave);
    }

    @Override
    public String toString() {
        return "> Libro{" +
               "id=" + id +
               ", titulo='" + titulo + '\'' +
               ", añoDePublicación=" + añoDePublicacion +
               ", tipo='" + tipo + '\'' +
               '}';
    }

}