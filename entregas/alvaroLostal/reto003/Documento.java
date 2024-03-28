import java.util.ArrayList;

public class Documento {
    private int id;
    private String titulo;
    private int año;
    private ArrayList<String> palabrasClave;
    private Tipo tipo;
    
    

    public Documento(int id, String titulo, int año, ArrayList<String> palabrasClave, Tipo tipo) {
        this.id = id;
        this.titulo = titulo;
        this.año = año;
        this.palabrasClave = palabrasClave;
        this.tipo = tipo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public void agregarPalabrasClave(String palabra) {
        this.palabrasClave.add(palabra);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("-------------------------\n");
        sb.append("ID: ").append(id).append("\n");
        sb.append("Título: ").append(titulo).append("\n");
        sb.append("Año: ").append(año).append("\n");
        sb.append("Palabras Clave: ").append(palabrasClave.toString()).append("\n");
        sb.append("Tipo: ").append(tipo);
        return sb.toString();
    }

}
