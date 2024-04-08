
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

    public void showLibraryManager() {
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
                    sc = new Scanner(System.in);
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
                    int codeBook = -1;
                    do {
                        listBook();
                        System.out.println("Seleccione id de libro: ");
                        codeBook = sc.nextInt();
                    } while (!existBookCode(codeBook));

                    int codeAuthor = -1;
                    do {
                        listAuthors();
                        System.out.println("Seleccione id de autor: ");
                        codeAuthor = sc.nextInt();
                    } while (!existAuthorCode(codeAuthor));
                    addRelation(codeBook,codeAuthor);
                    break;
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
        sc = new Scanner(System.in);
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
        for (Author autor : this.authors) {
            System.out.println(autor.toString());
        }
    }

    private boolean existAuthorCode(int codigo) {
        for (Author autor : this.authors) {
            if (autor.getId() == codigo) {
                return true;
            }
        }
        return false;
    }

    private boolean existBookCode(int codigo) {
        for (Book documento : this.books) {
            if (documento.getId() == codigo) {
                return true;
            }
        }
        return false;
    }

}

