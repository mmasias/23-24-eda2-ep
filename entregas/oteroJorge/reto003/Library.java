package entregas.oteroJorge.reto003;

import java.util.ArrayList;

public class Library {

    private ArrayList<Book> documentos;
    private LibraryManager manager = new LibraryManager();
    public Library() {
        this.documentos = new ArrayList<>();
        this.ingresarDocumentos();
        manager.setDocumentos(documentos);
        manager.run();
    }

    public void ingresarDocumentos(){
        manager.getGestorAutores().agregarAutor(new Author(1, "J.R.R Tolkien"));
        manager.getGestorAutores().agregarAutor(new Author(2, "Jorge"));
        manager.getGestorAutores().agregarAutor(new Author(3, "Luis"));
        manager.getGestorAutores().agregarAutor(new Author(4, "Juan"));
        manager.getGestorAutores().agregarAutor(new Author(5, "Pedro"));

        ArrayList<Integer> autores1 = new ArrayList<>();
        autores1.add(1);
        autores1.add(2);

        ArrayList<Integer> autores2 = new ArrayList<>();
        autores2.add(3);
        autores2.add(5);

        ArrayList<Integer> autores3 = new ArrayList<>();
        autores3.add(1);

        ArrayList<Integer> autores4 = new ArrayList<>();
        autores4.add(4);

        Book libro = new Book("El senor de los anillos", autores1, "1954", "Libro");
        Book revista = new Book("Revista de programacion", autores2, "2020", "Revista");
        Book articulo = new Book("Articulo de programacion", autores3, "2020", "Articulo");
        Book libro2 = new Book("Hob", autores4, "1954", "Libro");

        this.documentos.add(libro);
        this.documentos.add(revista);
        this.documentos.add(articulo);

        this.documentos.add(libro2);
    }
        
        public static void main(String[] args) {
            Library biblioteca = new Library();
        }
}
