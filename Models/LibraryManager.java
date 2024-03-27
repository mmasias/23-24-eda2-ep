package Models;

import java.util.Scanner;

public class LibraryManager {

    private Library digitalLibrary;
    private Scanner userInput;

    public LibraryManager() {
        this.digitalLibrary = new Library();
        this.userInput = new Scanner(System.in);
    }

    public void manageLibrary() {
        System.out.println("Bienvenido al Gestor de Biblioteca");
        boolean isRunning = true;
        while(isRunning) {
            System.out.println("Seleccione una acción: ");
            System.out.println("C - Crear Documento | E - Editar Documento | A - Mostrar Autores | D - Mostrar Documentos | S - Salir");
            String userResponse = userInput.nextLine();

            switch(userResponse) {
                case "C":
                    digitalLibrary.createDocument();
                    break;
                case "E": 
                    digitalLibrary.editDocument();
                    break;
                case "A": 
                    digitalLibrary.showAuthors();
                    break;
                case "D":
                    digitalLibrary.showDocuments();
                    break;
                case "S":
                    isRunning = false;
                    userInput.close();
                    break;
                default:
                    System.out.println("Opción no válida, intenta de nuevo.");
                    break;
            }
        }
    }

}
