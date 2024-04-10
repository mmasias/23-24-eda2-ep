import java.util.ArrayList;
import java.util.Scanner;

public  class Documentos {

    private  String titulo;
    private int id;
    private String añoDePublicación;
    private ArrayList<String> palabrasClave;
    private String tipo;
    Scanner sc;

    public Documentos(String titulo, String añoDePublicación,  String tipo, int id) {
        this.titulo = titulo;
         this.id=id;
        this.añoDePublicación = añoDePublicación;
         palabrasClave=new ArrayList<>();
        this.tipo = tipo;
        sc=new Scanner(System.in);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Documentos{" +
                "titulo='" + titulo + '\'' +
                ", id=" + id +
                ", añoDePublicación='" + añoDePublicación + '\'' +
                ", palabrasClave=" + palabrasClave +
                ", tipo='" + tipo + '\'' +
                ", sc=" + sc +
                '}';
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }



    public String getAñoDePublicación() {
        return añoDePublicación;
    }

    public void setAñoDePublicación(String añoDePublicación) {
        this.añoDePublicación = añoDePublicación;
    }

    public ArrayList<String> getPalabrasClave() {
        return palabrasClave;
    }

    public void setPalabrasClave(ArrayList<String> palabrasClave) {
        this.palabrasClave = palabrasClave;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }




}
