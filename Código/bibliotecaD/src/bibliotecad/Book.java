package bibliotecad;


public class Book {
   private int id;
   private String title ="";
   private int pubYear =0;
   private final String type; 
      
   public Book(int id, String title, int publicationYear, String type)
   {   
        this.id = id;
        this.title = title;
        this.pubYear = publicationYear;
        this.type = type;        
    } 
    public int getId() {
        return id;
    }  
 
    public String getTitle() {
        return title;
    }      
    public void setTitle(String title) {
        this.title = title;
    }    
  
    public int getPubYear() {
        return pubYear;
    }  
    public void setPubYear(int pubYear) {
        this.pubYear = pubYear;
    } 
    public String getTipo() {
        return type;
    }


   
    @Override
    public String toString()
    {
        String cadena=">Book{id="+ this.id+ ", Título= " + this.title +", Tipo= " + this.type + ", Año= " + this.pubYear + " " ;
        return cadena;
        
    }       
}
