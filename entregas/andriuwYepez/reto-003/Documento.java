public abstract class Documento {

    private String titulo;
    private List<Autor> autores;
    private int anioPublicacion;
    private TipoDocumento tipoDocumento;
    private List<String> palabrasClave;

    public Documento(String titulo, List<Autor> autores, int anioPublicacion, TipoDocumento tipoDocumento, List<String> palabrasClave) {
        this.titulo = titulo;
        this.autores = autores;
        this.anioPublicacion = anioPublicacion;
        this.tipoDocumento = tipoDocumento;
        this.palabrasClave = palabrasClave;
    }

    // Métodos para obtener y modificar los atributos
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public int getAnioPublicacion() {
        return anioPublicacion;
    }

    public void setAnioPublicacion(int anioPublicacion) {
        this.anioPublicacion = anioPublicacion;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public List<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(List<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    @Override
    public String toString() {
        return "**" + tipoDocumento.name() + "**: " + titulo + " (" + anioPublicacion + ") - " + autoresToString();
    }

    private String autoresToString() {
        StringBuilder autoresStr = new StringBuilder();
        for (Autor autor : autores) {
            autoresStr.append(autor.getNombre()).append(", ");
        }
        return autoresStr.toString().substring(0, autoresStr.length() - 2);
    }

}
