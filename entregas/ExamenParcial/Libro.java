import java.util.ArrayList;

public  class Libro {
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;
    private ArrayList<String> palabrasClave;
    private int idLibro;

    public Libro(String titulo, int añoDePublicacion, Tipo tipo, int idLibro) {
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

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
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
    public String toStriNg(){
        return titulo + añoDePublicacion + tipo + palabrasClave;
    }

    public int getId() {
        return idLibro;
    }

    public void setId(int idLibro) {
        this.idLibro = idLibro;
    }

}