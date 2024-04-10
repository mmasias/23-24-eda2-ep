package antonioGavilanes.reto003;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Libreria {
    private List<Documento> documentos;
    private List<Autor> autores;
    private List<AutorLibro> relaciones;
    private Scanner scanner;

    public Libreria() {
        documentos = new ArrayList<>();
        autores = new ArrayList<>();
        relaciones = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void startLibraryManager() {
        boolean continuar = true;
        do {
            System.out.println("=====================================");
            System.out.println("Libreria Uneatlantico");
            System.out.println("1. Listar libros");
            System.out.println("2. Añadir libro");
            System.out.println("3. Listar autores");
            System.out.println("4. Añadir autor");
            System.out.println("5. Listar relaciones");
            System.out.println("6. Salir");
            System.out.println("=====================================");
            System.out.print("Introduce una opción: ");
            int option = scanner.nextInt();
            switch (option) {
                case 1:
                    listBooks();
                    break;
                case 2:
                    addBook();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    System.out.print("Elija un Documento para añadir un autor: ");
                    listBooks();
                    int bookId = scanner.nextInt();
                    Documento book = findBookById(bookId);
                    if (book != null) {
                        System.out.print("Elija un autor para añadir al Documento: ");
                        addAuthor(book);
                    } else {
                        System.out.println("Libro no encontrado");
                    }

                    break;
                case 5:
                    listarRelaciones();
                    break;
                case 6:
                    System.out.println("Saliendo del sistema...");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        } while (continuar);
    }

    public void listBooks() {
        System.out.println("Listado de libros");
        for (Documento book : documentos) {
            System.out.println(book);
        }
    }

    public void addBook() {
        System.out.print("Introduce el título del libro: ");
        String title = scanner.next();
        System.out.print("Introduce el año de publicación: ");
        int publicationYear = scanner.nextInt();
        System.out.println("Seleccione el tipo de documento:");
        List<String> tipos = TipoDocumento.getTipos();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i));
        }
        System.out.print("Introduce el número correspondiente al tipo de documento: ");
        int tipoIndex = scanner.nextInt();
        String tipo = tipos.get(tipoIndex - 1);

        Documento book = new Documento(documentos.size() + 1, title, publicationYear, tipo);
        documentos.add(book);
        System.out.println("Libro añadido correctamente");
    }

    public void addBook(Documento book) {
        documentos.add(book);
        System.out.println("Libro añadido correctamente");
    }

    public void addAuthor(Documento book) {
        System.out.print("Introduce el nombre del autor: ");
        String name = scanner.next();
        Autor author = new Autor(autores.size() + 1, name);
        autores.add(author);
        AutorLibro relation = new AutorLibro(book.getId(), author.getId());
        relaciones.add(relation);
        System.out.println("Autor añadido correctamente");
    }

    public void addAuthor(Autor author) {
        autores.add(author);
        System.out.println("Autor añadido correctamente");
    }

    public void addRelation(int bookId, int authorId) {
        AutorLibro relation = new AutorLibro(bookId, authorId);
        relaciones.add(relation);
    }

    public List<Autor> getAuthorsByBookId(int bookId) {
        List<Autor> authorsOfBook = new ArrayList<>();
        for (AutorLibro relation : relaciones) {
            if (relation.getBookId() == bookId) {
                Autor author = findAuthorById(relation.getAuthorId());
                if (author != null) {
                    authorsOfBook.add(author);
                }
            }
        }
        return authorsOfBook;
    }

    public List<Documento> getBooksByAuthorId(int authorId) {
        List<Documento> booksOfAuthor = new ArrayList<>();
        for (AutorLibro relation : relaciones) {
            if (relation.getAuthorId() == authorId) {
                Documento book = findBookById(relation.getBookId());
                if (book != null) {
                    booksOfAuthor.add(book);
                }
            }
        }
        return booksOfAuthor;
    }

    public Documento findBookById(int bookId) {
        for (Documento book : documentos) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Autor findAuthorById(int authorId) {
        for (Autor author : autores) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }

    public void listAuthors() {
        System.out.println("Listado de autores");
        for (Autor author : autores) {
            System.out.println(author);
        }
    }
    
    public void listarRelaciones() {
        System.out.println("Registro de relaciones entre autores y libros:");
        for (AutorLibro relacion : relaciones) {
            Documento libro = findBookById(relacion.getBookId());
            Autor autor = findAuthorById(relacion.getAuthorId());
            if (libro != null && autor != null) {
                System.out.println("Libro: " + libro.getTitle() + " (ID: " + libro.getId() + ")");
                System.out.println("Autor: " + autor.getName() + " (ID: " + autor.getId() + ")");
                System.out.println("---------------------");
            }
        }
    }
}
