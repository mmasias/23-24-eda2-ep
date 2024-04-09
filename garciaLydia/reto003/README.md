## Biblioteca Digital

### Clase `Documento`:

Esta clase define un documento con diferentes atributos como título, autores, año de publicación, tipo de documento y palabras clave. Cada documento se crea con estos atributos y proporciona métodos para acceder a estos valores.

- **Atributos**:
    - `titulo`: Representa el título del documento.
    - `autores`: Una lista de los autores del documento.
    - `anoPublicacion`: El año en que el documento fue publicado.
    - `tipoDocumento`: El tipo de documento, por ejemplo, "Libro", "Artículo", "Revista", etc.
    - `palabrasClave`: Una lista de palabras clave asociadas con el documento.

- **Métodos**:
    - `getTitulo()`: Devuelve el título del documento.
    - `getAutores()`: Devuelve la lista de autores del documento.
    - `getAnoPublicacion()`: Devuelve el año de publicación del documento.
    - `getTipoDocumento()`: Devuelve el tipo de documento.
    - `getPalabrasClave()`: Devuelve la lista de palabras clave asociadas con el documento.

### Clase `BibliotecaDigital`:

Esta clase representa una biblioteca digital que contiene una lista de documentos y proporciona métodos para agregar, eliminar y buscar documentos por diferentes criterios.

- **Atributos**:
    - `documentos`: Una lista de objetos `Documento` que representa los documentos en la biblioteca.

- **Métodos**:
    - `agregarDocumento(Documento documento)`: Agrega un documento a la biblioteca.
    - `eliminarDocumento(Documento documento)`: Elimina un documento de la biblioteca.
    - `buscarPorTitulo(String titulo)`: Busca documentos por título y devuelve una lista de documentos que coinciden con el título proporcionado.
    - `buscarPorAutor(String autor)`: Busca documentos por autor y devuelve una lista de documentos que tienen el autor proporcionado.
    - `buscarPorAnoPublicacion(int ano)`: Busca documentos por año de publicación y devuelve una lista de documentos publicados en el año proporcionado.
    - `buscarPorTipoDocumento(String tipoDocumento)`: Busca documentos por tipo de documento y devuelve una lista de documentos del tipo proporcionado.
    - `buscarPorPalabrasClave(String palabraClave)`: Busca documentos por palabras clave y devuelve una lista de documentos que contienen la palabra clave proporcionada.

### Clase `Main`:

Esta clase contiene el método `main` que actúa como punto de entrada del programa. En el método `main`, se crea una instancia de `BibliotecaDigital`, se agregan varios documentos a la biblioteca y se realizan búsquedas de documentos por título, autor y palabras clave.

![Diagrama UML]("C:\Users\lydia\OneDrive\Escritorio\reto003UML.jpg")

