package entregas.oteroJorge.reto003;

import java.util.ArrayList;
import java.util.Scanner;

public class BookAuthor {

    private ArrayList<Author> autores;

    public BookAuthor() {
        this.autores = new ArrayList<>();
    }

    public ArrayList<Author> getAutores(){
        return this.autores;
    }

    public void agregarAutor(int id, String nombreCompleto){
        Author autor = new Author(id, nombreCompleto);
        this.autores.add(autor);
    }

    public void editarAutor(String nombreViejo){
        Author autor = null;
        for (Author aut : autores) {
            if (aut.getNombreCompleto().equals(nombreViejo)) {
                autor = aut;
                break;
            }
        }
        if (autor != null) {
            System.out.println("Introduce el nuevo nombre del autor");
            String nuevoNombre = System.console().readLine();
            autor.setNombreCompleto(nuevoNombre);
        } else {
            System.out.println("El autor no existe");
        }
    }
    
    public void eliminarAutor(String nombre){
        Author autor = null;
        for (Author aut : autores) {
            if (aut.getNombreCompleto().equals(nombre)) {
                autor = aut;
                break;
            }
        }
        if (autor != null) {
            autores.remove(autor);
        } else {
            System.out.println("El autor no existe");
        }
    }

    public void agregarAutor(Author autor){
        this.autores.add(autor);
    }
    
    public void listarAutores(){
        System.out.println("Autores:");
        for (Author autor : autores) {
            System.out.println(autor.getId() + " - " + autor.getNombreCompleto());
        }
    }

    public String listarEsteAutor(ArrayList<Integer> idAutores){
        String autores = "";
        for (int id : idAutores) {
            for (Author autor : this.autores) {
                if (autor.getId() == id) {
                    autores += autor.getNombreCompleto() + ", ";
                }
            }
        }
        return autores;
    }

    public boolean existeAutor(int id){
        for (Author autor : autores) {
            if (autor.getId() == id) {
                return true;
            }
        }
        return false;
    }
    public static void main(String[] args) {
        BookAuthor gestorAutores = new BookAuthor();
        Author autor1 = new Author(1, "J.R.R. Tolkien");
        Author autor2 = new Author(2, "Isaac Asimov");
        Author autor3 = new Author(3, "Jorge");

        gestorAutores.agregarAutor(autor1);
        gestorAutores.agregarAutor(autor2);
        gestorAutores.agregarAutor(autor3);

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el nombre del autor a editar");
        String nombre = sc.nextLine();
        gestorAutores.editarAutor(nombre);
        gestorAutores.listarAutores();
    }
}
