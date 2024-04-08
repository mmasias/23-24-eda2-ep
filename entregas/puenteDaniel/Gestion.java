package puenteDaniel;

import java.util.ArrayList;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Texto> textos;
    private ArrayList<Autor> autores;
    private ArrayList<AutorTexto> autoresTextos;
    Scanner sc = new Scanner(System.in);

    public Gestion(ArrayList<Texto> textos, ArrayList<Autor> autores, ArrayList<AutorTexto> autoresTextos) {
        this.textos = textos;
        this.autores = autores;
        this.autoresTextos = autoresTextos;
    }

    public void listarAutores(){
        for(Autor autor : autores){
            System.out.println(autor.toString());
        }
    }

    public void listarTextos(){
        for(Texto texto : textos){
            System.out.println(texto.toString());
        }
    }

    public void listarRelacionesAutorTexto() {
        for (AutorTexto autorTexto : autoresTextos) {
            Autor autor = buscarAutorPorId(autorTexto.getId_autor());
            Texto texto = buscarTextoPorId(autorTexto.getId_texto());
            if (autor != null && texto != null) {
                System.out.println("Relación: " + autorTexto.getTipo() + " - Autor: " + autor + ", Texto: " + texto.getTitulo());
            }
        }
    }


    public void añadirTexto() {
        System.out.println("Introduce el titulo del texto");
        String titulo = sc.nextLine();
        System.out.println("Introduce el año de publicación del texto");
        int año_publicacion = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el id del texto");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.println("Introduce el tipo del texto:LIBRO|REVISTA|ARTICULO|PAPER");
        String tipo = sc.nextLine();
        textos.add(new Texto( titulo, año_publicacion, id, tipo, new ArrayList<String>()));
    }

    public void añadirTexto(Texto texto) {
        textos.add(texto);
    }

    public void añadirAutor(Texto texto) {
        textos.add(texto);
    }

    public void añadirAutor() {
        System.out.println("Introduce el nombre del autor");
        String nombre = sc.nextLine();
        System.out.println("Introduce el apellido del autor");
        String apellido = sc.nextLine();
        System.out.println("Introduce el id del autor");
        int id = sc.nextInt();
        sc.nextLine();
        autores.add(new Autor(nombre,apellido,id));
        listarAutores();
    }

    public void actualizarAutor(int idAutor, String nuevoNombre, String nuevoApellido) {
        Autor autor = buscarAutorPorId(idAutor);
        if (autor != null) {
            autor.setNombre(nuevoNombre);
            autor.setApellido(nuevoApellido);
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    public void eliminarAutor(int idAutor) {
        Autor autorAEliminar = buscarAutorPorId(idAutor);
        if (autorAEliminar != null) {
            autores.remove(autorAEliminar);
            autoresTextos.removeIf(autorTexto -> autorTexto.getId_autor() == idAutor);
        } else {
            System.out.println("Autor no encontrado.");
        }
    }

    public void añadirAutor(Autor autor){
        autores.add(autor);
    }

    public void añadirRelacion(int idTexto, int idAutor, String tipo) {
        Autor autor = buscarAutorPorId(idAutor);
        Texto texto = buscarTextoPorId(idTexto);
    
        if (autor != null && texto != null) {
            autoresTextos.add(new AutorTexto(idAutor, idTexto, tipo));
            System.out.println("Relación añadida correctamente.");
        } else {
            System.out.println("Error: Autor o Texto no encontrado.");
        }
    }

    public void eliminarTexto(int idTexto) {
        Texto textoAEliminar = buscarTextoPorId(idTexto);
        if (textoAEliminar != null) {
            textos.remove(textoAEliminar);
            autoresTextos.removeIf(autorTexto -> autorTexto.getId_texto() == idTexto);
        } else {
            System.out.println("Texto no encontrado.");
        }
    }

    public Texto buscarTextoPorTitulo(String titulo) {
        for (Texto texto : textos) {
            if (texto.getTitulo().equalsIgnoreCase(titulo)) {
                return texto;
            }
        }
        return null;
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

    public Texto buscarTextoPorId(int id_texto){
        for (Texto texto : textos){
            if (texto.getId() == id_texto){
                return texto;
            }
        }
        return null;
    }

    public Autor buscarAutorPorId(int id_autor){
        for (Autor autor : autores){
            if (autor.getId() == id_autor){
                return autor;
            }
        }
        return null;
    }

    public void listarTextosPorAutorId(int idAutor) {
        ArrayList<Texto> textosDelAutor = getTextosPorAutorId(idAutor);
        if (!textosDelAutor.isEmpty()) {
            System.out.println("Textos del autor ID " + idAutor + ":");
            for (Texto texto : textosDelAutor) {
                System.out.println("ID: " + texto.getId() + " - Título: " + texto.getTitulo());
            }
        } else {
            System.out.println("No se encontraron textos para el autor con ID " + idAutor);
        }
    }

    public void listarAutoresPorTextoId(int idTexto) {
        ArrayList<Autor> autoresDelTexto = getAutoresPorTextoId(idTexto);
        if (!autoresDelTexto.isEmpty()) {
            System.out.println("Autores del texto ID " + idTexto + ":");
            for (Autor autor : autoresDelTexto) {
                System.out.println("ID: " + autor.getId() + " - Nombre: " + autor.getNombre() + " " + autor.getApellido());
            }
        } else {
            System.out.println("No se encontraron autores para el texto con ID " + idTexto);
        }
    }

    public void eliminarRelacion(int idTexto, int idAutor) {
        boolean eliminado = autoresTextos.removeIf(relacion -> relacion.getId_texto() == idTexto && relacion.getId_autor() == idAutor);
        if (eliminado) {
            System.out.println("La relación ha sido eliminada correctamente.");
        } else {
            System.out.println("No se encontró la relación especificada.");
        }
    }

    public void añadirPalabraClaveATexto(int idTexto, String palabraClave) {
        Texto texto = buscarTextoPorId(idTexto);
        if (texto != null) {
            texto.añadirPalabraClave(palabraClave);
            System.out.println("Palabra clave añadida exitosamente al texto.");
        } else {
            System.out.println("Texto no encontrado.");
        }
    }
    
    public void buscarTextosPorPalabraClave(String palabraClave) {
        boolean encontrado = false;
        for (Texto texto : textos) {
            if (texto.getPalabras_clave().contains(palabraClave)) {
                System.out.println("Texto encontrado: " + texto.getTitulo() + " (ID: " + texto.getId() + ")");
                encontrado = true;
            }
        }
        if (!encontrado) {
            System.out.println("No se encontraron textos con la palabra clave proporcionada.");
        }
    }
    
    public void eliminarPalabraClaveDeTexto(int idTexto, String palabraClave) {
        Texto texto = buscarTextoPorId(idTexto);
        if (texto != null) {
            if (texto.getPalabras_clave().contains(palabraClave)) {
                texto.eliminarPalabraClave(palabraClave);
                System.out.println("Palabra clave eliminada exitosamente.");
            } else {
                System.out.println("La palabra clave no se encontró en el texto especificado.");
            }
        } else {
            System.out.println("Texto no encontrado.");
        }
    }
    

}