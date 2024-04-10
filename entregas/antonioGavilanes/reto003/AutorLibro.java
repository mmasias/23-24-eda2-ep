package antonioGavilanes.reto003;

public class AutorLibro {

    private int libroId;
    private int autorId;

    public AutorLibro(int libroId, int autorId) {
        this.libroId = libroId;
        this.autorId = autorId;
    }

    public int getLibroId() {
        return libroId;
    }

    public int getAutorId() {
        return autorId;
    }
    
}
