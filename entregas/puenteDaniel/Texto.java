package puenteDaniel;

import java.util.ArrayList;

public class Texto {
    private String titulo;
    private int año_publicacion, id;
    private String tipo;
    private ArrayList<String> palabras_clave;

    public Texto(String titulo, int año_publicacion, int id, String tipo, ArrayList<String> palabras_clave) {
        this.titulo = titulo;
        this.año_publicacion = año_publicacion;
        this.id = id;
        this.tipo = tipo;
        this.palabras_clave = new ArrayList<String>();
    
    }

    public String getTitulo() {
        return titulo;
    }

    public int getAño_publicacion() {
        return año_publicacion;
    }

    public int getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

   public void setAño_publicacion(int año_publicacion) {
        this.año_publicacion = año_publicacion;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void añadirPalabraClave(String palabra){
        palabras_clave.add(palabra);
    }

    public void eliminarPalabraClave(String palabra){
        palabras_clave.remove(palabra);
    }

    public ArrayList<String> getPalabras_clave() {
        return palabras_clave;
    }

    @Override
    public String toString(){
        return "Titulo: "+titulo+" Año de publicación: "+año_publicacion+" Id: "+id+" Tipo: "+tipo;
    }
}
