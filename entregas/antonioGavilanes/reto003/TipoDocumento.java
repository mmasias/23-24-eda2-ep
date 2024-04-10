package antonioGavilanes.reto003;

import java.util.Arrays;
import java.util.List;

public class TipoDocumento {
    private static final List<String> tipos = Arrays.asList("libro", "revista", "artículo", "paper científico");

    public static List<String> getTipos() {
        return tipos;
    }
    
}
