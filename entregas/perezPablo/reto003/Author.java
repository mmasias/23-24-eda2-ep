package reto003;

public class Author {
    
    private int id;
    private String name;
    
    public Author (int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    @Override
    public String toString() {
        return "> Autor: " + this.name + "\n" + " -> Id: " + this.id + "\n";
    }
    
    /*
    public static void main (String[] args) {
        Author a = new Author(120930, "Juan Luis Guerra");
        System.out.println(a.toString());
    }
    */
}
