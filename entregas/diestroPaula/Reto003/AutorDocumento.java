package entregas.diestroPaula.Reto003;

public class AutorDocumento {

    private int idDocumento;
    private int idAutor;

    public AutorDocumento(int idDocumento, int idAutor) {
        this.idDocumento = idDocumento;
        this.idAutor = idAutor;
    }

    public int getIdDocumento() {
        return idDocumento;
    }

    public int getIdAutor() {
        return idAutor;
    }
}
