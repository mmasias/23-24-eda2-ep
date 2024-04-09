package bibliotecad;

public class Word {
    private int id;
    private String name;
    public Word(int ident,String palabra)
    {
        this.id=ident;
        this.name=palabra;
    }
    public int getId() {
        return id;
    }   
    public String getWord() {
        return name;
    }   
    public void setWord(String nombre) {
        this.name = nombre;
    }    
    @Override
    public String toString()
    {
        return "> Word{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
    }
    
}
