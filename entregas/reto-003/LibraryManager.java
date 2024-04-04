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

    public void LibraryManager(){

    }

    public void startLibraryManager(){

    }


    public void listBooks(){

    }

    public void addBook(){

    }

    public void addBook(Book book){

    }

    public void addAuthor(Book book){

    }

    public void addAuthor(Author author){

    }
    public void addRelation(int bookId, int authorId){

    }

    public List<Author> getAuthorsByBookId(int bookId){
        return authors;
    }

    public List<Book> getBooksByAuthorId(){
        return books;
    }

    public Book findBookById(int bookId){
        return Book;
    }

    public Author findAuthorById(int authorId){
        
        return Author;
    }

    public void listAuthors(){
        
    }
}
