import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private List<Book> books;
    private List<Author> authors;
    private List<BookAuthor> relations;
    private Scanner scanner;

    public LibraryManager(){
        this.books=new ArrayList<>();
        this.authors=new ArrayList<>();
        this.relations=new ArrayList<>();
        this.scanner= new Scanner(System.in);
    }

    public void startLibraryManager(){
        while(true){
            System.out.println("Gestor de biblioteca");
            System.out.println("1. Listar los libros");
            System.out.println("2. Añadir Libro");
            System.out.println("3. Listar los Autores");
            System.out.println("4. Añadir Autor");
            System.out.println("5. Buscar Libro por ID");
            System.out.println("6. Buscar autores de un libro por ID del libro");
            System.out.println("7. Buscar libros de un autor por ID del autor");
            System.out.println("8. Cerrar Gestor");

            int selection = scanner.nextInt();

            switch (selection) {
                case 1:
                    listBooks();
                    break;
                case 2:
                    scanner.nextLine();
                    Book newBook = addBook();
                    if (newBook != null) {
                        System.out.println("¿Deseas añadir un autor y relacionarlo con este libro? (s/n)");
                        String decision = scanner.nextLine();
                        if (decision.equalsIgnoreCase("s")) {
                            Author newAuthor = addAuthor();
                            if (newAuthor != null) {
                                addRelation(newBook.getId(), newAuthor.getId());
                            }
                        }
                    }
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    Author newAuthor = addAuthor();
                    if (newAuthor != null) {
                        System.out.println("¿Deseas añadir un libro y relacionarlo con este autor? (s/n)");
                        String decision = scanner.nextLine();
                        if (decision.equalsIgnoreCase("s")) {
                            Book bookToAdd = addBook();
                            if (bookToAdd != null) {
                                addRelation(bookToAdd.getId(), newAuthor.getId());
                            }
                        }
                    }
                    break;
                case 5:
                    System.out.println("Indica el Id del Libro:");
                    int bookId=scanner.nextInt();
                    Book book = findBookById(bookId);
                    if(book!=null){
                        System.out.println(book.toString());
                    } else{
                        System.out.println("Libro no econtrado o id no valido");
                    }
                    break;
                case 6:
                    System.out.println("Indica el ID del libro para buscar sus autores:");
                    int otherBook = scanner.nextInt();
                    scanner.nextLine();
                    List<Author> authors = getAuthorsByBookId(otherBook);
                    if(authors.isEmpty()){
                        System.out.println("No se encontraron autores para el libro con ID: " + otherBook);
                    }else{
                        System.out.println("Autores del libro con ID: " + otherBook);
                        for(Author author : authors){
                            System.out.println(author.toString());
                        }
                    }
                    break;

                case 7:
                    System.out.println("Indica el ID del autor para buscar sus libros:");
                    int authorId = scanner.nextInt();
                    scanner.nextLine(); // Consume any leftover newline
                    List<Book> books = getBooksByAuthorId(authorId);
                    if(books.isEmpty()){
                        System.out.println("No se encontraron libros para el autor con ID: " + authorId);
                    }else{
                        System.out.println("Libros del autor con ID: " + authorId);
                        for(Book book2 : books){
                            System.out.println(book2.toString());
                        }
                    }
                    break;
                
                case 8:
                    System.out.println("Cerrando gestor Biblioteca");
                    return;
                default:
                    System.out.println("Opción no valida.");
                    break;
            }
        }
    }


    public void listBooks(){
        if(books.isEmpty()){
            System.out.println("La lista de libros esta vacia");

        }else{
            for(Book book : books){
                System.out.println(book.toString());
                System.out.println("----------------------");

            }
        }
    }

    public Book addBook(){
        
        System.out.println("Titulo del Libro");
        String title = scanner.nextLine();

        System.out.println("Fecha de Publicación (día/mes/año):");
        String dateString = scanner.nextLine();
        LocalDate publicationDate;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            publicationDate = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha no válido. Por favor, introduce la fecha en formato día/mes/año.");
            return null;
        }

        System.out.println("Tipo de Libro");
        String type = scanner.nextLine();
        int id = title.hashCode();

        Book newBook = new Book(id, title, publicationDate, type);
        books.add(newBook);
        System.out.println("Siguiente libro Añadido:"+newBook.toString());
        return newBook;
    }

    public Author addAuthor(){
        scanner.nextLine();
        System.out.println("Añadiendo Autor:");
        System.out.println("Nombre del Autor:");
        String name = scanner.nextLine();
        int id = name.hashCode();

        Author newAuthor = new Author(id, name);
        authors.add(newAuthor);
        System.out.println("Siguiente autor Añadido:"+newAuthor.toString());
        return newAuthor;
        
    }

    public void addRelation(int bookId, int authorId){
        Book book = findBookById(bookId);
        Author author = findAuthorById(authorId);

        if(book==null||author==null){
            System.out.println("Libro:"+bookId);
            System.out.println("Autor:"+authorId);
            System.out.println("Ha surgido un problema, revisa ambos id");
            return;
        }
        for(BookAuthor relation : relations){
            if(relation.getBookId()==bookId &&relation.getAuthorId()==authorId){
                System.out.println("La relación ya existe");
                return;
            }
        }

        BookAuthor newRelation = new BookAuthor(bookId, authorId);
        relations.add(newRelation);
        System.out.println("Relación creada con exito");

    }

    public List<Author> getAuthorsByBookId(int bookId){
        List<Author> authorsForBook = new ArrayList<>();
        for(BookAuthor relation : relations){
            if(relation.getBookId()==bookId){
                Author author = findAuthorById(relation.getAuthorId());
                if(author!=null){
                    authorsForBook.add(author);
                }
            }
        }
        return authorsForBook;
    }

    public List<Book> getBooksByAuthorId(int authorId){
        List<Book> booksForAutor = new ArrayList<>();
        for(BookAuthor relation : relations){
            if(relation.getAuthorId()==authorId){
                Book book = findBookById(relation.getBookId());
                if(book!=null){
                    booksForAutor.add(book);
                }
            }
        }
        return booksForAutor;
    }

    public Book findBookById(int bookId){
        for(Book book :books){
            if(book.getId()==bookId){
                return book;
            }
        }
        return null;
    }

    public Author findAuthorById(int authorId){
        for(Author author : authors){
            if(author.getId()==authorId){
                return author;
            }
        }
        return null;
    }

    public void listAuthors(){
        if(authors.isEmpty()){
            System.out.println("La lista de autores esta vacia");
        }else{
            for(Author author : authors)
            System.out.println(author.toString());
            System.out.println("----------------------");
        }
    }
}
