package antonioGavilanes.reto003;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PalabrasClave {
    private List<String> palabrasClave;

    public PalabrasClave() {

        this.palabrasClave = new ArrayList<>();
    }

    public void agregarPalabra() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese una palabra clave (o escriba '-1' para terminar):");
        String palabra = scanner.nextLine();
        while (!palabra.equals("-1")) {
            palabrasClave.add(palabra);
            System.out.println("Palabra clave agregada correctamente.");
            System.out.println("Ingrese otra palabra clave (o escriba '-1' para terminar):");
            palabra = scanner.nextLine();
        }
    }

    public List<String> getPalabrasClave() {
        return palabrasClave;
    }
}