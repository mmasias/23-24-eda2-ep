# Gestor de Biblioteca

Este proyecto es una implementación básica de un sistema de gestión de bibliotecas en Java.

## Clases

### Clase `Author`
- Representa un autor de libros.
- Atributos:
    - `id`: Identificador único del autor.
    - `name`: Nombre del autor.
- Métodos:
    - `getId()`: Retorna el ID del autor.
    - `getName()`: Retorna el nombre del autor.
    - `toString()`: Representación en cadena del autor.

### Clase `Book`
- Representa un libro.
- Atributos:
    - `id`: Identificador único del libro.
    - `title`: Título del libro.
    - `publicationYear`: Año de publicación del libro.
    - `type`: Tipo de libro.
    - `booksAuthors`: Lista de relaciones entre libros y autores.
- Métodos:
    - `addBookAuthor(BookAuthor bookAuthor)`: Agrega una relación autor-libro.
    - `getBooksAuthors()`: Retorna la lista de relaciones.
    - `toString()`: Representación en cadena del libro.

### Clase `BookAuthor`
- Representa la relación entre un libro y un autor.
- Atributos:
    - `author`: Objeto `Author`.
    - `book`: Objeto `Book`.
- Métodos:
    - `toString()`: Representación en cadena de la relación.

### Clase `LibraryManager`
- Gestiona la biblioteca.
- Atributos:
    - `books`: Lista de libros.
    - `authors`: Lista de autores.
    - `bookAuthors`: Lista de relaciones entre libros y autores.
    - `scanner`: Objeto `Scanner`.
- Métodos:
    - `startLibraryManager()`: Inicia la gestión de la biblioteca.
    - `listBooks()`: Muestra los libros.
    - `addBook()`: Agrega un libro.
    - `findOrCreateAuthor(String name)`: Busca o crea un autor.
    - `listAuthors()`: Muestra los autores.
    - `main()`: Punto de entrada del programa.

## Funcionamiento
- El programa permite listar libros, agregar nuevos libros y listar autores.
- Utiliza las clases `Book`, `Author` y `BookAuthor` para modelar libros, autores y relaciones.
- La clase `LibraryManager` coordina las operaciones de la biblioteca y proporciona una interfaz de usuario.

![Diagrama UML]("C:\Users\lydia\OneDrive\Escritorio\parcialUML.jpg")
