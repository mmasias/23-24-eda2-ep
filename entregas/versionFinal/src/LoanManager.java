import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LoanManager {
    private List<Prestamo> prestamos;

    public LoanManager() {
        this.prestamos = new ArrayList<>();
    }

    public void agregarPrestamo(Usuario usuario, Documento documento, LocalDate fechaDevolucion) {
        Prestamo nuevoPrestamo = new Prestamo(usuario, documento, fechaDevolucion);
        prestamos.add(nuevoPrestamo);
    }

    public boolean devolverDocumento(Usuario usuario, String tituloDocumento) {
        for (Prestamo prestamo : usuario.getPrestamos()) {
            if (prestamo.getDocumento().getTitulo().equalsIgnoreCase(tituloDocumento)) {
                usuario.devolverDocumento(prestamo.getDocumento());
                return true;
            }
        }
        return false;
    }
}

