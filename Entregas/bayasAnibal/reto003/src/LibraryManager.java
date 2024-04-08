
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    private ArrayList<Book> books;
    private ArrayList<Author> authors;
    private ArrayList<BookAuthor> relations;
    private Scanner sc;

    public LibraryManager() {
        this.books = new ArrayList<>();
        this.authors = new ArrayList<>();
        this.relations = new ArrayList<>();
        this.sc = new Scanner(System.in);
    }

    public void menu() {
        boolean salir = false;
        do {
            System.out.println("1. Agregar documento");
            System.out.println("2. Listar documentos");
            System.out.println("3. Agregar autor");
            System.out.println("4. Listar autores");
            System.out.println("5. Listar autores de un documento");
            System.out.println("6. Listar documentos de un autor");
            System.out.println("7. Hacer relacion");
            System.out.println("8. Salir");
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    addBook();
                    break;
                case 2:
                    listBook();
                    break;
                case 3:
                    System.out.println("Ingrese el nombre del autor");
                    String name = sc.nextLine();
                    System.out.println("Ingrese el ID del autor");
                    int id = sc.nextInt();
                    existAuthorCode(id);
                    existBookCode(id);
                    addAuthor(new Author(id, name));
                    break;
                case 4:
                    listAuthors();
                    break;
                case 5:
                    System.out.println("Ingrese el ID del documento");
                    int id2 = sc.nextInt();
                    Book book = findBookById(id2);
                    if (book != null) {
                        List<Author> authors = getAuthorsByBookId(id2);
                        for (Author author : authors) {
                            System.out.println(author);
                        }
                    } else {
                        System.out.println("No se encontro el documento");
                    }
                    break;
                case 6:
                    System.out.println("Ingrese el ID del autor");
                    int authorId = sc.nextInt();
                    Author author = findAuthorById(authorId);
                    if (author != null) {
                        List<Book> books = getBooksByAuthorId(authorId);
                        for (Book b : books) {
                            System.out.println(b);
                        }
                    } else {
                        System.out.println("No se encontro el autor");
                    }
                    break;
                case 7:

                case 8:
                    salir = true;
                    break;
                default:
                    break;
            }
        } while (!salir);
    }

    private void listBook() {
        for (Book book : books) {
            System.out.println(book);
        }
    }

    private void addBook() {
        System.out.println("Ingrese el titulo del documento");
        String titulo = sc.nextLine();
        System.out.println("Ingrese el ID del documento");
        int id = sc.nextInt();
        System.out.println("Ingrese el año de publicacion");
        int publicationYear = sc.nextInt();
        System.out.println("Ingrese el tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        int opcion = sc.nextInt();
        Tipo tipo = null;
        switch (opcion) {
            case 1:
                tipo = Tipo.LIBRO;
                break;
            case 2:
                tipo = Tipo.REVISTA;
                break;
            case 3:
                tipo = Tipo.ARTICULO;
                break;
            case 4:
                tipo = Tipo.PAPER;
                break;
            default:
                break;
        }
        Book book = new Book(id, titulo, publicationYear, tipo);
        addBook(book);
    }

    private void addBook(Book book) {
        books.add(book);
    }

    private void addAuthor(Book book) {
        boolean agregar = true;
        do {
            listAuthors();
            System.out.println("Ingrese el ID del autor");
            int id = sc.nextInt();
            if (findAuthorById(id) == null) {
                System.out.println("Ingrese el nombre y apellidos del autor: ");
                String name = sc.nextLine();
                addAuthor(new Author(id, name));
            }
            addRelation(book.getId(), id);
            System.out.println("Desea agregar otro autor? (si-no)");
            String respuesta = sc.nextLine();
            if (respuesta.equals("no")) {
                agregar = false;
            } else {
                agregar = true;
            }
        } while (agregar);

    }

    private void addAuthor(Author author) {
        this.authors.add(author);
    }

    private void addRelation(int bookId, int authorId) {
        relations.add(new BookAuthor(bookId, authorId));
    }

    private List<Author> getAuthorsByBookId(int bookId) {
        ArrayList<Author> auxiliar = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getBookId() == bookId) {
                auxiliar.add(findAuthorById(relation.getAuthorId()));
            }
        }
        return auxiliar;
    }

    private List<Book> getBooksByAuthorId(int authorId) {
        ArrayList<Book> auxiliar = new ArrayList<>();
        for (BookAuthor relation : relations) {
            if (relation.getAuthorId() == authorId) {
                auxiliar.add(( findBookById(relation.getBookId()) ));
            }
        }
        return auxiliar;
    }

    private Book findBookById(int BookId) {
        for (Book b : books) {
            if (b.getId() == BookId) {
                return b;
            }
        }
        return null;
    }

    private Author findAuthorById(int authorId) {
        for (Author a : authors) {
            if (a.getId() == authorId) {
                return a;
            }
        }
        return null;

    }

    private void listAuthors() {
        for(Author autor:this.authors){
            System.out.println(autor.toString());
        }
    }

    private void existAuthorCode(int codigo){
        for(Author autor:this.authors){
            if(autor.getId()==codigo){
                System.out.println("El autor ya existe");
                return;
            }
        }
    }
    private void existBookCode(int codigo){
        for(Book documento:this.books){
            if(documento.getId()==codigo){
                System.out.println("El documento ya existe");
                return;
            }
        }
    }

  /*

        ArrayList<String> misPalabras = documento.getPalabrasClave();
        for (String word : misPalabras) {
            if (indicePalabrasClave.containsKey(word)) {
                ArrayList<Book> documentoPalabras = indicePalabrasClave.get(word);
                documentoPalabras.add(documento);
                indicePalabrasClave.replace(word, documentoPalabras);
            } else {
                ArrayList<Book> documentoPalabras = new ArrayList<>();
                documentoPalabras.add(documento);
                indicePalabrasClave.put(word, documentoPalabras);
            }
        }


    }

    public void editar() {
        System.out.println("Ingrese el titulo del documento a editar");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();
        for (Book documento : books) {
            if (documento.getYear().equals(titulo)) {
                System.out.println("Ingrese el nuevo titulo del documento");
                String nuevoTitulo = sc.nextLine();
                documento.setYear(nuevoTitulo);
                System.out.println("Ingrese el nuevo año de publicacion");
                int nuevoAño = sc.nextInt();
                documento.setPublicationYear(nuevoAño);
                System.out.println("Ingrese el nuevo tipo de documento");
                System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
                int opcion = sc.nextInt();
                Tipo tipo = null;
                switch (opcion) {
                    case 1:
                        tipo = Tipo.LIBRO;
                        break;
                    case 2:
                        tipo = Tipo.REVISTA;
                        break;
                    case 3:
                        tipo = Tipo.ARTICULO;
                        break;
                    case 4:
                        tipo = Tipo.PAPER;
                        break;
                    default:
                        break;
                }
                documento.setTipo(tipo);
                boolean agregar = true;
                do {
                    System.out.println("Ingrese el nombre del autor");
                    Scanner sc2 = new Scanner(System.in);
                    String nombre = sc2.nextLine();
                    System.out.println("Ingrese el apellido del autor");
                    String apellido = sc2.nextLine();
                    Author autor = new Author(nombre);
                    ArrayList<Author> autores = new ArrayList<Author>();
                    autores.add(autor);
                    documento.setAutores(autores);
                    System.out.println("Desea agregar otro autor? (si-no)");
                    String respuesta = sc2.nextLine();
                    if (respuesta.equals("no")) {
                        agregar = false;
                    } else {
                        agregar = true;
                    }
                } while (agregar);
            }

            String palabra = "";
            do {
                System.out.println("Introduzca palabras clave (fin-para terminar)");
                palabra = sc.nextLine();
                if (!palabra.equals("fin")) {
                    documento.añadirPalabraClave(palabra);
                }
            } while (!palabra.equals("fin"));
        }

    }


    public void buscarPorPalabrasClave() {
        System.out.println("Ingrese la palabra clave a buscar");
        Scanner sc = new Scanner(System.in);
        String palabra = sc.nextLine();
        for (Book documento : books) {
            for (String palabraClave : documento.getPalabrasClave()) {
                if (palabraClave.equals(palabra)) {
                    System.out.println("Titulo: " + documento.getYear());
                    System.out.println("Año de publicacion: " + documento.getAnoDePublicacion());
                    System.out.println("Tipo: " + documento.getTipo());
                    System.out.println("Autores: ");
                    for (Author autor : documento.getAutores()) {
                        System.out.println(autor.getNombre() + " " + autor.getApellido());
                    }
                    System.out.println("Palabras clave: ");
                    for (String palabra2 : documento.getPalabrasClave()) {
                        System.out.println(palabra2);
                    }
                }
            }
        }
    }

    public void menu() {
        boolean salir = false;
        do {
            System.out.println("1. Agregar documento");
            System.out.println("2. Modificar documento");
            System.out.println("3. Buscar documento");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por palabras clave");
            System.out.println("6. Eliminar documento");
            System.out.println("7. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregar();
                    break;
                case 2:
                    editar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    buscarPorAutor();
                    break;
                case 5:
                    // buscarPorPalabrasClave();
                    break;
                case 6:
                    eliminar();
                    break;
                case 7:
                    salir = true;
                    break;
                default:
                    break;
            }
        } while (!salir);
    }
*/
}

