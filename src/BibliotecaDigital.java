public class BibliotecaDigital {
    private List<Libro> libros;
    private List<Autor> autores;
    private List<AutorLibro> relaciones;
    private Scanner scanner;

    public LibraryManager() {
        libros = new ArrayList<>();
        autores = new ArrayList<>();
        relaciones = new ArrayList<>();
        scanner = new Scanner(System.in);
    }
    public void startBibliotecaDigital() {
        System.out.println("Bienvenido al gestor de bibliotecas. ¿Qué acción desea realizar?");
        System.out.println("1. Listar libros");
        System.out.println("2. Agregar libro");
        System.out.println("3. Listar autores");
        System.out.println("4. Salir");

        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                listLibros();
                break;
            case 2:
                addLibro();
                break;
            case 3:
                listAutores();
                break;
            case 4:
                System.out.println("Saliendo del gestor de bibliotecas...");
                return;
            default:
                System.out.println("Opción no válida.");
        }

        startLibraryManager();
    }

    public void listLibros() {
        if (books.isEmpty()) {
            System.out.println("No hay libros disponibles.");
            return;
        }

        System.out.println("Lista de libros:");
        for (Libro libro : libros) {
            System.out.println(libro);
        }
    }

    public void addLibro() {
        System.out.println("Ingrese el ID del libro:");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el título del libro:");
        String titulo = scanner.nextLine();

        System.out.println("Ingrese el año de publicación del libro:");
        int añoPublicación = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Ingrese el tipo del libro:");
        String tipo = scanner.nextLine();

        Book newBook = new Book(id, titulo, añoPublicación, tipo);
        libros.add(newLibro);
        System.out.println("Libro agregado correctamente.");
    }

    public void addAutor(Autor autor) {
        autores.add(autor);
    }

    public void addRelación(int libroId, int autorId) {
        relaciones.add(new AutorLibro(libroId, autorId));
    }

    public List<Autor> getAutoresByLibroId(int libroId) {
        List<Autor> autoresDelLibro = new ArrayList<>();
        for (AutorLibro relacion : relaciones) {
            if (relacion.getLibroId() == LibroId) {
                Autor autor = findAutorById(relation.getAutorId());
                if (autor != null) {
                    autoresDelLibro.add(autor);
                }
            }
        }
        return autoresDelLibro;
    }

    public List<Book> getBooksByAuthorId(int authorId) {
        List<Book> booksByAuthor = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getAuthorId() == authorId) {
                Book book = findBookById(relation.getBookId());
                if (book != null) {
                    booksByAuthor.add(book);
                }
            }
        }
        return booksByAuthor;
    }

    public Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }

    public Author findAuthorById(int authorId) {
        for (Author author : authors) {
            if (author.getId() == authorId) {
                return author;
            }
        }
        return null;
    }

    public void listAuthors() {
        if (authors.isEmpty()) {
            System.out.println("No hay autores disponibles.");
            return;
        }

        System.out.println("Lista de autores:");
        for (Author author : authors) {
            System.out.println(author);
        }
    }
}


public class Main {
    public static void main(String[] args) {
        LibraryManager libraryManager = new LibraryManager();
        libraryManager.startLibraryManager();
    }
}
