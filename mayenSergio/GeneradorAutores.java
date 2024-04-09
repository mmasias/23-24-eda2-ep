package mayenSergio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class GeneradorAutores {

    private Scanner scanner = new Scanner(System.in);

    public List<Autor> crearNuevoAutor() {
        List<Autor> nuevosAutores = new ArrayList<>();

        do {
            System.out.print("Nombre del autor: ");
            String nombre = scanner.nextLine();

            System.out.print("Apellido del autor: ");
            String apellido = scanner.nextLine();

            Autor autor = new Autor(nombre, apellido);
            nuevosAutores.add(autor);

            System.out.println("Autor agregado correctamente.\n");

            System.out.println("¿Desea crear más autores? (si/no): ");

        } while (scanner.nextLine().equalsIgnoreCase("si"));

        return nuevosAutores;
    }

    public void editarAutor(List<Autor> autores) {
        System.out.print("Ingrese el nombre del autor que desea editar: ");
        String nombreBuscado = scanner.nextLine();

        Iterator<Autor> iterator = autores.iterator();
        while (iterator.hasNext()) {
            Autor autor = iterator.next();
            if (autor.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.print("Nuevo nombre del autor: ");
                String nuevoNombre = scanner.nextLine();
                autor.setNombre(nuevoNombre);

                System.out.print("Nuevo apellido del autor: ");
                String nuevoApellido = scanner.nextLine();
                autor.setApellido(nuevoApellido);

                System.out.println("Autor editado correctamente.\n");
                return;
            }
        }
        System.out.println("Autor no encontrado.\n");
    }

    public void eliminarAutor(List<Autor> autores) {
        System.out.print("Ingrese el nombre del autor que desea eliminar: ");
        String nombreBuscado = scanner.nextLine();

        Iterator<Autor> iterator = autores.iterator();
        while (iterator.hasNext()) {
            Autor autor = iterator.next();
            if (autor.getNombre().equalsIgnoreCase(nombreBuscado)) {
                iterator.remove();
                System.out.println("Autor eliminado correctamente.\n");
                return;
            }
        }
        System.out.println("Autor no encontrado.\n");
    }

    public void buscarAutor(List<Autor> autores) {
        System.out.print("Ingrese el nombre del autor que desea buscar: ");
        String nombreBuscado = scanner.nextLine();

        for (Autor autor : autores) {
            if (autor.getNombre().equalsIgnoreCase(nombreBuscado)) {
                System.out.println("Autor encontrado:\n" + autor + "\n");
                return;
            }
        }
        System.out.println("Autor no encontrado.\n");
    }
}
