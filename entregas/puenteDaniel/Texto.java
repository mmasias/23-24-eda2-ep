package puenteDaniel;

public class Texto {
    private String titulo;
    private int año_publicacion, id;
    private String tipo;

    public Texto(String titulo, int año_publicacion, int id, String tipo) {
        this.titulo = titulo;
        this.año_publicacion = año_publicacion;
        this.id = id;
        this.tipo = tipo;
    
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

    @Override
    public String toString(){
        return "Titulo: "+titulo+" Año de publicación: "+año_publicacion+" Id: "+id+" Tipo: "+tipo;
    }
}
