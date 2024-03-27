import java.util.ArrayList;

public  class Documento {
    private int id;
    private String titulo;
    private int añoDePublicacion;
    private Tipo tipo;
    private ArrayList<String> palabrasClave;

    public Documento(String titulo, int anoDePublicacion, Tipo tipo, int id ) {
        this.titulo = titulo;
        this.añoDePublicacion = anoDePublicacion;
        this.id=id;
        this.tipo = tipo;
        this.palabrasClave = new ArrayList<String>();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnoDePublicacion() {
        return añoDePublicacion;
    }

    public void setAnoDePublicacion(int anoDePublicacion) {
        this.añoDePublicacion = anoDePublicacion;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Documento [id=" + id + ", titulo=" + titulo + ", añoDePublicacion=" + añoDePublicacion + ", tipo="
                + tipo + "]";
    }

    

}