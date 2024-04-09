# Sistema de Gestión de Biblioteca

Este repositorio contiene la implementación de un sistema de gestión de biblioteca en Java.

## Cambios Implementados

### Clases y Métodos

- `Autor.java` se transformó en `Author.java`.
- `Biblioteca.java` se transformó en `LibraryManager.java`.
- `Documento.java` y `Titulo.java` se fusionaron y transformaron en `Book.java`.
- Se creó `BookAuthor.java` para manejar las relaciones entre libros y autores.

### Funcionalidades Añadidas

En la clase `LibraryManager`, se añadieron las siguientes funcionalidades:

- `listarLibros()`: Lista todos los libros en el sistema.
- `agregarLibro()`: Permite al usuario añadir un nuevo libro.
- `listarAutores()`: Lista todos los autores en el sistema.
- `agregarAutor()`: Permite al usuario añadir un nuevo autor.
- `asociarAutorConLibro()`: Crea una relación entre un libro y un autor.
- `mostrarLibrosDeAutor()`: Muestra todos los libros asociados a un autor específico.
- `mostrarAutoresDeLibro()`: Muestra todos los autores asociados a un libro específico.