package bibliotecad;
/**
 *
 * @author Administrador
 */
public class Author {
    private int id;
    private String name="";
    
    public Author(int id, String nom)
    {
        this.id=id;
        this.name=nom;
    }   
    public int getId() {
        return id;
    }    
   
    public String getName() {
        return name;
    }
   
    public void setName(String nombre) {
        this.name = nombre;
    }   
   
    @Override
    public String toString()
    {
        return "> Author{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
    }
    
    
}
