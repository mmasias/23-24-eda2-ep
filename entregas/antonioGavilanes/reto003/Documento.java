package antonioGavilanes.reto003;

import java.util.List;

public class Documento {
    private int id;
    private String titulo;
    private List<String> autores;
    private int añoPublicacion;
    private String tipoDocumento;
    private List<String> palabrasClave;

    public Documento(int id, String titulo, List<String> autores, int añoPublicacion, String tipoDocumento, List<String> palabrasClave) {
        this.id = id;
        this.titulo = titulo;
        this.autores = autores;
        this.añoPublicacion = añoPublicacion;
        this.tipoDocumento = tipoDocumento;
        this.palabrasClave = palabrasClave;
    }

    public int getId() {
        return id;
    }
    
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(List<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String toString() {
        return "Documento{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autores=" + autores +
                ", añoPublicacion=" + añoPublicacion +
                ", tipoDocumento='" + tipoDocumento + '\'' +
                ", palabrasClave=" + palabrasClave +
                '}';
    }

}
