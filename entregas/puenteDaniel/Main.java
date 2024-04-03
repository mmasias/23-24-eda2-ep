package puenteDaniel;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Texto> textos = new ArrayList<Texto>();
        ArrayList<Autor> autores = new ArrayList<Autor>();
        ArrayList<AutorTexto> autoresTextos = new ArrayList<AutorTexto>();
        Gestion gestion = new Gestion(textos, autores, autoresTextos);
        gestion.añadirTexto();
        gestion.listarAutores();
    }
        
}
