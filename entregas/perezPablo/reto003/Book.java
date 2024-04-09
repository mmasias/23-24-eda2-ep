package reto003;

public class Book {
    private int id;
    private String title;
    private int publicationYear;
    private final String type;

    public Book(int id, String title, int publicationYear, String type) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.type = type;
    }
    
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "> " + this.type + " : " + this.title + "\n" + " -> Id : " + this.id + "\n" + " -> Publicado en " + this.publicationYear;
    }

    /*
    public static void main (String[] args) {
        Book b = new Book(430284, "Memorias de una guerra", 1950, "Novela");
        System.out.println(b.toString());
    }
    */
    

}
