### Book
La clase `Book` modela un libro con atributos como identificador, título, año de publicación, y un conjunto de palabras clave.

- **Atributos**:
  - `id`: Identificador único del libro.
  - `title`: Título del libro.
  - `publicationYear`: Año de publicación del libro.

- **Métodos**:
  - Métodos `get` y `set` para cada atributo.
  - `getType()`: Retorna el tipo de documento.
  - `setKeywords(keywords: List<String>)`: Establece las palabras clave del libro.
  - `toString()`: Retorna una representación en cadena del libro.
  - `equals(obj: Object)`: Comprueba si otro objeto es igual a este libro.
  - `hashCode()`: Retorna el código hash del libro.

### BookAuthor
La clase `BookAuthor` establece la relación entre libros y autores, permitiendo representar qué autor(es) ha(n) escrito un libro.

- **Atributos**:
  - `bookId`: Identificador del libro.
  - `authorId`: Identificador del autor.

- **Métodos**:
  - `getBookId()`: Retorna el identificador del libro.
  - `getAuthorId()`: Retorna el identificador del autor.

### LibraryManager
`LibraryManager` es la clase central que maneja la lógica del sistema de biblioteca, incluyendo la gestión de libros, autores y sus relaciones.

- **Atributos**:
  - Listas para almacenar objetos de tipo `Book`, `Author`, y `BookAuthor`.
  - `scanner`: Para leer la entrada del usuario.

- **Métodos Principales**:
  - `startLibraryManager()`: Inicia el gestor de la biblioteca permitiendo al usuario realizar diversas acciones como listar libros/autores, agregar libros/autores, y más.
  - Métodos privados para soportar las funcionalidades de `startLibraryManager`, como `listBooks`, `addBook`, `listAuthors`, `addAuthor`, `addRelation`, `listRelations`, `searchBooksByKeywords`, entre otros.

## Funcionamiento
El sistema inicia a través de `App`, el cual inicia la clase `LibraryManager` que presenta un menú al usuario para realizar diversas operaciones. El usuario puede listar, añadir, y buscar tanto libros como autores. También es posible establecer relaciones entre libros y autores para representar qué libros ha escrito un autor.

Cada clase tiene un propósito específico y colabora con las demás para permitir el funcionamiento completo del sistema de gestión de la biblioteca. La interacción entre las clases se maneja principalmente a través de `LibraryManager`, que actúa como el controlador del sistema.

Entrega del Reto003 anterior en el que me baso para refactorizarlo con el código proporcionado.

https://github.com/ikercelaya/23-24-eda2/tree/main/entregas/celayaIker/Reto003/src