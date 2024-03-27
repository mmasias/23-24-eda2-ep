import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Documento> documentos;
    private ArrayList<Autor> autores;
    private ArrayList<AutorDocumento> autorDocumentos;
    Scanner scanner;

    public Gestion(ArrayList<Documento> documentos, ArrayList<Autor> autores, ArrayList<AutorDocumento> autorDocumentos,
            Scanner scanner) {
        this.documentos = documentos;
        this.autores = autores;
        this.autorDocumentos = autorDocumentos;
        this.scanner = scanner;
    }
   
    private void listarDocumentos() {
        for(int i=0; i<documentos.size();i++){
            System.out.println(documentos.get(i));
        }
    }

    private void agregarDocumento() {

        System.out.println("Ingrese el titulo del documento");
        Scanner sc = new Scanner(System.in);
        String titulo = sc.nextLine();

        System.out.println("Ingrese el año de publicacion");
        int año = sc.nextInt();

        System.out.println("Ingrese el tipo de documento");
        System.out.println("1. LIBRO 2. REVISTA 3. ARTICULO 4. PAPER");
        int opcion = sc.nextInt();

        Tipo tipo = null;
        switch (opcion) {
            case 1:
                tipo = Tipo.LIBRO;
                break;
            case 2:
                tipo = Tipo.REVISTA;
                break;
            case 3:
                tipo = Tipo.ARTICULO;
                break;
            case 4:
                tipo = Tipo.PAPER;
                break;
            default:
                break;
        }

        System.out.println("Ingrese el id del documento");
        int id = sc.nextInt();
        scanner.nextLine();
        Documento documento = new Documento(titulo, año, tipo,id);
    }

    private void agregarDocumento(Documento documento) {
        documentos.add(documento);
    }

    private void agregarAutor(Documento documento){

        System.out.println("Ingresa el id  del autor");
        int id=scanner.nextInt();

        System.out.println("Ingresa el nombre del autor");
        String nombre=scanner.nextLine();

        System.out.println("Ingresa el apellido del autor");
        String apellido=scanner.nextLine();
        Autor autor=new Autor(id, nombre, apellido);
    }

    private void agregarAutor(Autor autor){
        autores.add(autor);
    }

    private void agregarRelacion(int documentoId,int autorId){
        AutorDocumento autorDoc=new AutorDocumento(documentoId, autorId);
        autorDocumentos.add(autorDoc);

        Documento documento= buscarDocPorId(documentoId);
        if (documento!=null) {
            Autor autor= buscarAutorPorId(autorId);
            if (autor!=null) {
                agregarAutor(autor);                
            }
        }
    }

    private List<Autor> obtenerAutorPorDocumentoId(int documentoId){
        List<Autor> autores=new ArrayList<>();
        for(int i=0;i<autorDocumentos.size();i++){
            AutorDocumento autorDoc= autorDocumentos.get(i);
            if (autorDoc.getDocumentoId()==documentoId) {
                Autor autor= buscarAutorPorId(autorDoc.getAutorId());
                if(autor!=null){
                    autores.add(autor);
                }
            }
        }
        return autores;
    }

    private List<Documento> obtenerDocumentoPorAutorId(int autorId){
        List<Documento> documentos=new ArrayList<>();

        for(int i=0;i<autorDocumentos.size();i++){
            AutorDocumento autorDoc= autorDocumentos.get(i);
            if (autorDoc.getAutorId()==autorId) {
                Documento libro= buscarDocPorId(autorDoc.getDocumentoId());
                if(libro!=null){
                    documentos.add(libro);
                }
            }
        }
        return documentos;
    }

    private Documento buscarDocPorId(int id){
        for(int i=0;i<documentos.size();i++){
            Documento documento=documentos.get(i);
            if (documento.getId()==id) {
                return documento;
            }
        }
        return null;
    }

    private Autor buscarAutorPorId(int id) {
        for(int i=0;i<autores.size();i++){
            Autor autor=autores.get(i);
            if (autor.getId()==id) {
                return autor;   
            }
        }
        return null;
    }
    
    public void menu() {
        boolean salir = false;
        do {
            System.out.println("1. Agregar documento");
            System.out.println("2. Listar documento");
            System.out.println("3. Buscar documento");
            System.out.println("4. Buscar por autor");
            System.out.println("5. Buscar por palabras clave");
            System.out.println("6. Eliminar documento");
            System.out.println("7. Salir");
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    agregarDocumento();
                    break;
                case 2:
                    modificar();
                    break;
                case 3:
                    buscar();
                    break;
                case 4:
                    buscarPorAutor();
                    break;
                case 5:
                    buscarPorPalabrasClave();
                    break;
                case 6:
                    eliminar();
                    break;
                case 7:
                  
                    break;
                case 8: 
                    salir=true;
                    break;
                default:
                    break;
            }
        } while (!salir);
    }
}