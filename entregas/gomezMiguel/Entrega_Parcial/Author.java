package Entrega_Parcial;
public class Author {
    String name;
    int id;

    public Author(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public int getid() {
        return id;
    }

    public void setid(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Autor: " + name + "  ID: " + id;
    }
}
