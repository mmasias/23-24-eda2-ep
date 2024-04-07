package Entrega_Parcial;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class LibraryManager {
    
private ArrayList<Book> books;
private ArrayList<Author> authors;
private ArrayList<BookAuthor> relations;
private Scanner scanner;

public LibraryManager(){
    this.books = new ArrayList<>();
    this.authors = new ArrayList<>();
    this.relations = new ArrayList<>();
    this.scanner = new Scanner(System.in);
}

public void UseLibraryManager(){
    System.out.println("Bienvenido a la biblioteca Virtual");
    boolean exit = false;
    while(!exit){
        System.out.println("1. Agregar libro");
        System.out.println("2. Agregar autor");
        System.out.println("3. Asignar autor a libro");
        System.out.println("4. Listar libros");
        System.out.println("5. Listar autores");
        System.out.println("6. Salir");
        System.out.println("Seleccione una opcion: ");
        int option = scanner.nextInt();
        scanner.nextLine();
        switch(option){
            case 1:
                addBook();
                break;
            case 2:
                addAuthor();
                break;
            case 3:
                assignAuthorToBook();
                break;
            case 4:
                listBooks();
                break;
            case 5:
                listAuthors();
                break;
            case 6:
                exit = true;
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }
}

private void addBook(){
    System.out.println("Ingrese el titulo del libro: ");
    String title = scanner.nextLine();
    System.out.println("Ingrese el año de publicacion: ");
    int year = scanner.nextInt();
    System.out.println("Ingrese el tipo de libro: ");
    String type = scanner.nextLine();
    Book book = new Book(books.size() + 1,title, year, type);
    books.add(book);
    System.out.println("Libro agregado con exito");
}

private void addAuthor(){
    System.out.println("Ingrese el nombre del autor: ");
    String name = scanner.nextLine();
    Author author = new Author(name, authors.size() + 1);
    authors.add(author);
    System.out.println("Autor agregado con exito");
}

private void assignAuthorToBook(){
    System.out.println("Seleccione el libro: ");
    for(int i = 0; i < books.size(); i++){
        System.out.println((i + 1) + ". " + books.get(i).gettitle());
    }
    int bookIndex = scanner.nextInt();
    System.out.println("Seleccione el autor: ");
    for(int i = 0; i < authors.size(); i++){
        System.out.println((i + 1) + ". " + authors.get(i).getname());
    }
    int authorIndex = scanner.nextInt();
    BookAuthor relation = new BookAuthor((bookIndex - 1),(authorIndex - 1));
    relations.add(relation);
    System.out.println("Autor asignado con exito");

}

private List<Author> getAuthorsByBookId(int bookId){
    List<Author> searchedAuthors = new ArrayList<>();
    for(BookAuthor relation : relations){
        if(relation.getbookid() == bookId){
            searchedAuthors.add(findAuthorById(relation.getauthorid()));
        }
    }
    return searchedAuthors;
}

private List<Book> getBooksByAuthorId(int authorId){
    List<Book> searchedbooks = new ArrayList<>();
    for(BookAuthor relation : relations){
        if(relation.getauthorid() == authorId){
            searchedbooks.add(this.books.get(relation.getbookid()));
        }
    }
    return searchedbooks;
}

private Book findBookById(int id){
    for(Book book : books){
        if(book.getid() == id){
            return book;
        }
    }
    return null;
}

private Author findAuthorById(int id){
    for(Author author : authors){
        if(author.getid() == id){
            return author;
        }
    }
    return null;
}

private void listBooks(){
    if (books.isEmpty()){
        System.out.println("No hay libros en la biblioteca");
        return;
    }
    for(Book book : books){
        System.out.println(book);
    }
}

private void listAuthors(){
    if (authors.isEmpty()){
        System.out.println("No hay autores en la biblioteca");
        return;
    }
    for(Author author : authors){
        System.out.println(author);
    }
}

}
