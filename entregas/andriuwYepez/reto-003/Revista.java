public class Revista extends Documento {

    private String ISSN;
    private int numeroVolumen;
    private int numeroEdicion;

    public Revista(String titulo, List<Autor> autores, int anioPublicacion, String ISSN, int numeroVolumen, int numeroEdicion) {
        super(titulo, autores, anioPublicacion, TipoDocumento.REVISTA, new ArrayList<>());
        this.ISSN = ISSN;
        this.numeroVolumen = numeroVolumen;
        this.numeroEdicion = numeroEdicion;
    }

    // Métodos para obtener y modificar los atributos específicos de una revista
    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public int getNumeroVolumen() {
        return numeroVolumen;
    }

    public void setNumeroVolumen(int numeroVolumen) {
        this.numeroVolumen = numeroVolumen;
    }

    public int getNumeroEdicion() {
        return numeroEdicion;
    }

    public void setNumeroEdicion(int numeroEdicion) {
        this.numeroEdicion = numeroEdicion;
    }
    
    @Override
    public String toString() {
        // Modifica la representación del objeto Revista aquí
        return super.toString() + " - ISSN: " + ISSN + ", Volumen: " + numeroVolumen + ", Edición: " + numeroEdicion;
    }

}
