public class Relacion {
    private int idLibro;
    private int idAutor;
    
    public Relacion(int idLibro, int idAutor) {
        this.idLibro = idLibro;
        this.idAutor = idAutor;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public int getIdAutor() {
        return idAutor;
    }
}