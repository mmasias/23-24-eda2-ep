package Entrega_Parcial;
public class Book {
    private int id;
    private String title;
    private int year;
    private final String type;

    public Book(int id, String title, int year, String type) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.type = type;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String gettitle() {
        return title;
    }

    public void settitle(String title) {
        this.title = title;
    }

    public int getyear() {
        return year;
    }

    public void setyear(int year) {
        this.year = year;
    }

    public String gettype() {
        return type;
    }

    @Override
    public String toString() {
        return "- Información del libro:" + "  ID: " + id + "  Título: " + title + "  Año de publicación: " + year + "  Tipo de documento: " + type + "\n";
    }
}
