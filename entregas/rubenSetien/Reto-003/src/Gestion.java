import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Gestion {
    private ArrayList<Documentos> documentos;
    private ArrayList<Autor> autores;
    private ArrayList<DocumentoAutor> relaciones;
    Scanner se= new Scanner(System.in);
    public Gestion() {
        documentos = new ArrayList<>();
        autores=new ArrayList<>();
        relaciones=new ArrayList<>();


        documentos.add(new Documentos("Don Quijote", "2012","Novela",1));
         documentos.add(new Documentos("El pais", "2012","Periodico",2));
         autores.add(new Autor(3,"Manolo"));
         autores.add(new Autor(4,"Paco"));
         relaciones.add(new DocumentoAutor(1,3));
        relaciones.add(new DocumentoAutor(2,4));
        relaciones.add(new DocumentoAutor(1,4));
        //Datos de prueba
    }
public void menu() {
        int nMenu;
    do{

        System.out.println("1 agregar documento || 2 buscar documento por ID autor || "
            + "3 buscar Autor por ID documento ||\n4 añadir relación entre autor y libro || 5 listar autores || 6 salir");
        nMenu = se.nextInt();
        int idDocumento=0;
        int idAutor=0;
        Gestion g = new Gestion();

      switch (nMenu){
            case 1:
                g.agregarDocumento();
                break;
            case 2:
                boolean inputInvalido = true;
                while (inputInvalido) {
                    try {
                        System.out.println("Introduce el ID del autor:");
                        idAutor=0;
                        idAutor = se.nextInt();
                        se.nextLine();
                        inputInvalido = false;
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Debes ingresar un número entero para el ID.");
                        se.nextLine();
                    }
                }
                List<Documentos> documentosDelAutor = getDocumentoDeIDAutor(idAutor);
                for (Documentos documento : documentosDelAutor) {
                    System.out.println(documento);
                }
                break;
          case 3:
               inputInvalido = true;
              while (inputInvalido) {
                  try {
                      System.out.println("Introduzca el ID del documento");
                      idDocumento=0;
                      idDocumento = se.nextInt();
                      inputInvalido = false;
                  } catch (InputMismatchException e) {
                      System.out.println("Error: Debes ingresar un número entero para el ID.");
                      se.nextLine();
                  }
              }

              g.getAutorDeIdDocumento(idDocumento);
              break;
          case 4:
              inputInvalido = true;
              while (inputInvalido) {
                  try {
                      System.out.println("Introduzca el ID del autor");
                      idAutor=0;
                      idAutor = se.nextInt();
                      inputInvalido = false;
                  } catch (InputMismatchException e) {
                      System.out.println("Error: Debes ingresar un número entero para el ID.");
                      se.nextLine();
                  }
              }
              inputInvalido = true;
              while (inputInvalido) {
                  try {
                      System.out.println("Introduzca el ID del documento");
                      idDocumento=0;
                      idDocumento = se.nextInt();
                      inputInvalido = false;
                  } catch (InputMismatchException e) {
                      System.out.println("Error: Debes ingresar un número entero para el ID.");
                      se.nextLine();
                  }
              }

              g.añadirRelacion(idDocumento, idAutor);
              break;
          case 5:
              g.listAutor();
              break;
          case 6:
              System.out.println("Saliendo del programa...");
              break;

          default:
              System.out.println("Opción inválida. Inténtalo de nuevo.");
              break;
      }
}while (nMenu !=6);
}
    public void agregarDocumento() {
do {
    Documentos d = null;
    se = new Scanner(System.in);
    System.out.println("Introduce el nombre del tìtulo: ");
    String titulo = se.nextLine();
    System.out.println("Introduce el año de publicación:");
    String añoDePublicacion = se.nextLine();
    System.out.println("Introduce el tipo de documento:");
    String tipo = se.nextLine();
    int id = 0;
    boolean inputInvalido = true;


    while (inputInvalido) {
        try {
            System.out.println("Introduce el ISBN del libro:");
            id = se.nextInt();
            se.nextLine();
            inputInvalido = false;
        } catch (InputMismatchException e) {
            System.out.println("Error: Debes ingresar un número entero para el ID.");
            se.nextLine();
        }
    }

    d = new Documentos(titulo, añoDePublicacion, tipo, id);
    agregarDocumento(d);
    agregarAutor(d);
    System.out.println("¿Desea agregar otro documento? (Sí: 1, No: 0)");
}while (se.nextInt()==1);
se.nextLine();
        System.out.println("Volviendo al menu...");
menu();
    }
    public void agregarDocumento(Documentos d) {
        documentos.add(d);
    }
public void agregarAutor(Autor a){
        autores.add(a);
}
    public void agregarAutor(Documentos d) {

        Autor a = null;
        se = new Scanner(System.in);
        System.out.println("Introduce el nombre del autor:");
        String autor = se.nextLine();
        int id = 0;
        boolean inputInvalido = true;


        while (inputInvalido) {
            try {
                System.out.println("Introduce el id del autor:");
                id = se.nextInt();
                se.nextLine();
                inputInvalido = false;
            } catch (InputMismatchException e) {
                System.out.println("Error: Debes ingresar un número entero para el ISBN.");
                se.nextLine();
            }


            a = new Autor(id, autor);
            agregarAutor(a);
        }
    }
public void añadirRelacion(int documentoId, int autorId){
        DocumentoAutor l=null;
        l=new DocumentoAutor(documentoId,autorId);
        relaciones.add(l);
}
public List<Autor> getAutorDeIdDocumento(int idDocumento){
        ArrayList<Autor> autors= new ArrayList<>();
    for (DocumentoAutor d:relaciones) {
        if (d.getIdDocumento()==idDocumento){
            autors.add(buscarAutorPorId(d.getAutorId()));
        }
    }

    return autors;
}
    public List<Documentos> getDocumentoDeIDAutor(int idAutor) {
        List<Documentos> docu = new ArrayList<>();
        for (DocumentoAutor d : relaciones) {
            if (d.getAutorId() == idAutor) {
                Documentos documento = buscarDocumentoPorId(d.getIdDocumento());
                if (documento != null) {
                    docu.add(documento);
                }
            }
        }
        return docu;
    }

    public Documentos buscarDocumentoPorId(int idDocumento) {
        for (Documentos documento : documentos) {
            if (documento.getId() == idDocumento) {
                return documento;
            }
        }
        return null;
    }
public Autor buscarAutorPorId(int autorId){
    for (Autor a:autores){
        if (autorId==a.getId()){
            return a;
        }
    }

    return null;
}

    public void listAutor() {
        System.out.println("Lista de autores:");
        for (Autor autor : autores) {
            System.out.println(autor);
        }
    }


    public void editar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el titulo del documento que quieres actualizar los datos:");
        String titulo = sc.nextLine();
        for (int i = 0; i < documentos.size(); i++) {

            boolean ok = false;
            do {
                int n = documentos.indexOf(titulo);
                System.out.println("Introduce de nuevo la fecha de publicación:");
                String año = sc.nextLine();
                System.out.println("Introduce de nuevo el tipo de documento:");
                String tipo = sc.nextLine();
                System.out.println("Introduce de nuevo el id del documento:");
                int id = sc.nextInt();
                Documentos d1 = new Documentos(titulo, año, tipo,id);

                documentos.set(n, d1);
                ok = true;
            } while (!ok);

        }
    }



    public void eliminar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresa el titulo del documento que desea eliminar:");
        String titulo = sc.nextLine();
        for (int i = 0; i < documentos.size(); i++) {
            int n = documentos.indexOf(titulo);
            documentos.remove(n);
        }


        Documentos d = null;
        d.getTitulo().equals(titulo);

    }

    @Override
    public String toString() {
        return "Gestion{" +
                "documentos=" + documentos +
                ", autores=" + autores +
                ", relaciones=" + relaciones +
                ", se=" + se +
                '}';
    }

    public static void main(String[] args) {
        Gestion g = new Gestion();
       g.menu();
    }
}



