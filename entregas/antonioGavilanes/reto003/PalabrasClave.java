package antonioGavilanes.reto003;

import java.util.Scanner;
import java.util.ArrayList;

public class PalabrasClave {
    private String palabraClave;
    private PalabrasClaveNode first;

    private ArrayList<String> palabrasClave;

    public PalabrasClave() {
        this.palabrasClave = new ArrayList<String>();
    }

    public void addPalabraClave(PalabrasClave palabraClave) {
        PalabrasClaveNode newPalabrasClaveNode = new PalabrasClaveNode(palabraClave);
        if (first == null) {
            first = newPalabrasClaveNode;
        } else {
            PalabrasClaveNode current = first;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(newPalabrasClaveNode);
        }
    }

    public void createPalabraClave() {
        boolean creating = true;
        Scanner userInput = new Scanner(System.in);
        while (creating) {
            System.out.println("Palabra clave por añadir (-1 para terminar)");
            String palabraClave = userInput.nextLine();
            if (palabraClave.equals("-1")) {
                creating = !creating;
            } else {
                PalabrasClave newPalabraClave = new PalabrasClave();
                addPalabraClave(newPalabraClave);
            }
        }
    }

    public void removePalabraClave(String palabraClave) {
        this.palabrasClave.remove(palabraClave);
    }

    public ArrayList<String> getPalabrasClave() {
        return this.palabrasClave;
    }

    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public void manage() {
        boolean managing = true;
        Scanner userInput = new Scanner(System.in);
        do {
            System.out.println("1. Añadir palabra clave");
            System.out.println("2. Eliminar palabra clave");
            System.out.println("3. Ver palabras clave");
            System.out.println("4. Salir");
            int option = userInput.nextInt();
            switch (option) {
                case 1:
                    System.out.println("Palabra clave a añadir");
                    String palabraClave = userInput.nextLine();
                    createPalabraClave();
                    break;
                case 2:
                    System.out.println("Palabra clave a eliminar");
                    String palabraC = userInput.nextLine();
                    removePalabraClave(palabraC);
                    break;
                case 3:
                    System.out.println(toString());
                    break;
                case 4:
                    managing = !managing;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (managing);
    }

    @Override
    public String toString() {
        String result = "";
        PalabrasClaveNode current = first;
        while (current != null) {
            System.out.println("====================================");
            System.out.println(result + "Palabra clave: " + current.getPalabraClave());
            System.out.println("====================================");
            current = current.getNext();
        }
    }

    public static void main(String[] args) {
        PalabrasClave palabrasClave = new PalabrasClave();
        palabrasClave.manage();

    }

}
