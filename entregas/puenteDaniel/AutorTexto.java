package puenteDaniel;
public class AutorTexto {
    private int id_autor, id_texto;
    private String tipo;

    public AutorTexto(int id_autor, int id_texto, String tipo) {
        this.id_autor = id_autor;
        this.id_texto = id_texto;
        this.tipo = tipo;
    }

    public int getId_autor() {
        return id_autor;
    }

    public void setId_autor(int id_autor) {
        this.id_autor = id_autor;
    }

    public int getId_texto() {
        return id_texto;
    }

    public void setId_texto(int id_texto) {
        this.id_texto = id_texto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
