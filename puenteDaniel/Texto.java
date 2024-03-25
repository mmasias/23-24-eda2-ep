package puenteDaniel;
import java.util.ArrayList;

public class Texto {
    private String titulo;
    private int año_publicacion, id;
    private ArrayList<String> palabras_clave;
    private String tipo;

    public Texto(String titulo, int año_publicacion, int id, String tipo) {
        this.titulo = titulo;
        this.año_publicacion = año_publicacion;
        this.id = id;
        this.tipo = tipo;
        this.palabras_clave = new ArrayList<String>();
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   public void setAño_publicacion(int año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    public int getAño_publicacion() {
        return año_publicacion;
    }

    public void setPalabras_clave(ArrayList<String> palabras_clave) {
        this.palabras_clave = palabras_clave;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   

    public ArrayList<String> getPalabras_clave() {
        return palabras_clave;
    }

    public String getTipo() {
        return tipo;
    }

    public void añadirPalabraClave(String palabra){
        palabras_clave.add(palabra);
    }


    public void eliminarPalabraClave(String palabra){
        palabras_clave.remove(palabra);
    }
}
