package entregas.oteroJorge.reto003;

import java.util.ArrayList;

public class Book {


    private String titulo;
    private ArrayList<Integer> idAutores;
    private String añoPublicacion;
    private String tipo;

    public Book(String titulo, ArrayList<Integer> idAutores, String añoPublicacion, String tipo) {
        this.titulo = titulo;
        this.idAutores = idAutores;
        this.añoPublicacion = añoPublicacion;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(String añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setAutores(ArrayList<Integer> idAutores) {
        this.idAutores = idAutores;
    }

    public ArrayList<Integer> getIdAutores() {
        return idAutores;
    }

    public int getIdAutor(int index) {
        return idAutores.get(index);
    }

    public void printDocumento(BookAuthor gestorAutores) {
        System.out.println(this.toString(gestorAutores));
    }

    public String listarAutores(BookAuthor gestorAutores) {
        String autores = "";
        for (int id : this.idAutores) {
            for (Author autor : gestorAutores.getAutores()) {
                if (autor.getId() == id) {
                    autores += autor.getNombreCompleto() + " -";
                }
            }
        }
        return autores;
    }

    public String toString(BookAuthor gestorAutores) {
        return "> " + this.tipo + " -> " + this.titulo + " -> " + this.listarAutores(gestorAutores) + "> " + this.añoPublicacion;
    }

    public static void main(String[] args) {
        BookAuthor gestorAutores = new BookAuthor();
        Author autor1 = new Author(1, "Jorge");
        Author autor2 = new Author(2, "Isaac Asimov");
        Author autor3 = new Author(3, "J.R.R. Tolkien");
        gestorAutores.agregarAutor(autor1);
        gestorAutores.agregarAutor(autor2);
        gestorAutores.agregarAutor(autor3);
        ArrayList<Integer> autores = new ArrayList<>();
        autores.add(1);
        autores.add(3);
        Book documento1 = new Book("El señor de los anillos", autores, "1954",  "Libro");
        documento1.printDocumento(gestorAutores);

    }

}
