import java.util.ArrayList;
import java.util.List;

public class Libreria {

    private List<Documento> documentos;

    public Libreria() {
        this.documentos = new ArrayList<>();
    }

    // Métodos para agregar, editar, mostrar y eliminar documentos
    public void agregarDocumento(Documento documento) {
        documentos.add(documento);
    }

    public void editarDocumento(Documento documento) {
        // Buscar el documento en la lista
        int indice = documentos.indexOf(documento);
        if (indice != -1) {
            // Actualizar el documento en la lista
            documentos.set(indice, documento);
        }
    }

    public void mostrarDocumentos() {
        for (Documento documento : documentos) {
            System.out.println(documento);
        }
    }

    public void eliminarDocumento(Documento documento) {
        documentos.remove(documento);
    }

    // Métodos para buscar documentos por título, autor, año de publicación, tipo de documento, o temas/palabras clave
    public List<Documento> buscarPorTitulo(String titulo) {
        List<Documento> resultados = new ArrayList<>();
        for (Documento documento : documentos) {
            if (documento.getTitulo().contains(titulo)) {
                resultados.add(documento);
            }
        }
        return resultados;
    }

    public List<Documento> buscarPorAutor(String autor) {
        List<Documento> resultados = new ArrayList<>();
        for (Documento documento : documentos) {
            for (Autor autorDocumento : documento.getAutores()) {
                if (autorDocumento.getNombre().contains(autor)) {
                    resultados.add(documento);
                    break;
                }
            }
        }
        return resultados;
    }

    public List<Documento> buscarPorAnioPublicacion(int anio) {
        List<Documento> resultados = new ArrayList<>();
        for (Documento documento : documentos) {
            if (documento.getAnioPublicacion() == anio) {
                resultados.add(documento);
            }
        }
        return resultados;
    }

    public List<Documento> buscarPorTipoDocumento(TipoDocumento tipoDocumento) {
        List<Documento> resultados = new ArrayList<>();
        for (Documento documento : documentos) {
            if (documento.getTipoDocumento() == tipoDocumento) {
                resultados.add(documento);
            }
        }
        return resultados;
    }

    public List<Documento> buscarPorPalabrasClave(List<String> palabrasClave) {
        List<Documento> resultados = new ArrayList<>();
        for (Documento documento : documentos) {
            boolean encontrado = true;
            for (String palabraClave : palabrasClave) {
                if (!documento.getPalabrasClave().contains(palabraClave)) {
                    encontrado = false;
                    break;
                }
            }
            if (encontrado) {
                resultados.add(documento);
            }
        }
        return resultados;
    }

    // Métodos para clasificar documentos por tipo y temas/palabras clave
    public List<Documento> clasificarPorTipoDocumento() {
        List<Documento> documentosClasificados = new ArrayList<>();
        for (TipoDocumento tipoDocumento : TipoDocumento.values()) {
            for (Documento documento : documentos) {
                if (documento.getTipoDocumento() == tipoDocumento) {
                    documentosClasificados.add(documento);
                }
            }
        }
        return documentosClasificados;
    }

    public List<Documento> clasificarPorPalabrasClave() {
        List<Documento> documentosClasificados = new ArrayList<>();
        for (String palabraClave : obtenerPalabrasClave()) {
            for (Documento documento : documentos) {
                if (documento.getPalabrasClave().contains(palabraClave)) {
                    documentosClasificados.add(documento);
                }
            }
        }
        return documentosClasificados;
    }

    private List<String> obtenerPalabrasClave() {
        List<String> palabrasClave = new ArrayList<>();
        for (Documento documento : documentos) {
            palabrasClave.addAll(documento.getPalabrasClave());
        }
        return palabrasClave;
    }

}
