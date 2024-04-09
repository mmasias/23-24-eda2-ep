package antonioGavilanes.reto003;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Usuario {
    private List<Documento> documentos;

    public Usuario() {
        this.documentos = new ArrayList<>();
    }

    public void crearDocumento() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Creación de nuevo documento:");
        System.out.println("Ingrese el título del documento:");
        String titulo = scanner.nextLine();
        System.out.println("Ingrese los autores del documento (separados por coma):");
        String autoresInput = scanner.nextLine();
        List<String> autores = Arrays.asList(autoresInput.split(","));
        System.out.println("Ingrese el año de publicación del documento:");
        int añoPublicacion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        System.out.println("Ingrese el tipo de documento:");
        List<String> tipos = TipoDocumento.getTipos();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i));
        }
        int opcionTipo = scanner.nextInt();
        String tipoDocumento = tipos.get(opcionTipo - 1);
        scanner.nextLine(); // Consumir el salto de línea
        PalabrasClave palabrasClave = new PalabrasClave();
        palabrasClave.agregarPalabra();
        documentos.add(new Documento(titulo, autores, añoPublicacion, tipoDocumento, palabrasClave.getPalabrasClave()));
        System.out.println("Documento creado con éxito.");
    }

    public void manage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Biblioteca Uneatlantico");
        System.out.println("Seleccione una opción:");
        System.out.println("1. Crear documento");
        System.out.println("2. Consultar documentos");
        System.out.println("3. Salir");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Consumir el salto de línea
        switch (opcion) {
            case 1:
                crearDocumento();
                break;
            case 2:
                System.out.println("Consulta de documentos:");
                for (Documento documento : documentos) {
                    System.out.println("Título: " + documento.getTitulo());
                    System.out.println("Autores: " + String.join(", ", documento.getAutores()));
                    System.out.println("Año de publicación: " + documento.getAñoPublicacion());
                    System.out.println("Tipo de documento: " + documento.getTipoDocumento());
                    System.out.println("Palabras clave: " + String.join(", ", documento.getPalabrasClave()));
                    System.out.println();
                }
                break;
            case 3:
                System.out.println("Gracias por usar la biblioteca virtual.");
                break;
            default:
                System.out.println("Opción inválida.");
                break;

        }
    }
}
