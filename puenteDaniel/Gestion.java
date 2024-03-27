package puenteDaniel;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Texto> textos;
    private ArrayList<Autor> autores;
    private ArrayList<AutorTexto> autoresTextos;
    Scanner sc = new Scanner(System.in);

    public Gestion(ArrayList<Texto> textos, ArrayList<Autor> autores, ArrayList<AutorTexto> autoresTextos) {
        this.textos = new ArrayList<Texto>();
        this.autores = new ArrayList<Autor>();
        this.autoresTextos = new ArrayList<AutorTexto>();
    }

    public void listarLibros(){
        for (Texto texto : textos){
            if (texto.getTipo().equals("libro")){
                System.out.println(texto.toString());
            }
        }
    }

    public void añadirTexto() {
        System.out.println("Introduce el titulo del texto");
        String titulo = sc.nextLine();
        System.out.println("Introduce el año de publicación del texto");
        int año_publicacion = sc.nextInt();
        System.out.println("Introduce el id del texto");
        int id = sc.nextInt();
        System.out.println("Introduce el tipo del texto");
        String tipo = sc.nextLine();
        textos.add(new Texto( titulo, año_publicacion, id, tipo));
    }

    public void añadirTexto(Texto texto) {
        textos.add(texto);
    }

    public void añadirAutor(Texto texto) {
        textos.add(texto);
    }

    public void añadirAutor(Autor autor){
        autores.add(autor);
    }

    public void añadirRelacion(int id_texto,int id_autor){
        System.out.println("Introduce el tipo de relación");
        String tipo = sc.nextLine();
        autoresTextos.add(new AutorTexto(id_autor,id_texto,tipo));
    }

    public ArrayList<Texto> getTextosPorAutorId(int id_autor){
        ArrayList<Texto> textosPorAutor = new ArrayList<Texto>();
        for (AutorTexto autorTexto : autoresTextos){
            if (autorTexto.getId_autor() == id_autor){
                textosPorAutor.add(buscarTextoPorId(autorTexto.getId_texto()));
            }
        }
        return textosPorAutor;
    }

    public ArrayList<Autor> getAutoresPorTextoId(int id_texto){
        ArrayList<Autor> autoresPorTexto = new ArrayList<Autor>();
        for (AutorTexto autorTexto : autoresTextos){
            if (autorTexto.getId_texto() == id_texto){
                autoresPorTexto.add(buscarAutorPorId(autorTexto.getId_autor()));
            }
        }
        return autoresPorTexto;
    }

    public Texto buscarTextoPorId(int id){
        for (Texto texto : textos){
            if (texto.getId() == id){
                return texto;
            }
        }
        return null;
    }

    public Autor buscarAutorPorId(int id){
        for (Autor autor : autores){
            if (autor.getId() == id){
                return autor;
            }
        }
        return null;
    }

    public void listarTexto(){
        for(Texto texto : textos){
            System.out.println(texto.toString());
        }
    }

    public void listarAutores(){
        for(Autor autor : autores){
            System.out.println(autor.toString());
        }
    }

}