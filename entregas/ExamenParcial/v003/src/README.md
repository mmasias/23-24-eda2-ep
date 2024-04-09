# v003

<div align=center>

|![Modelo UML](/modelo/modelo.svg)|
|:-:|
[App.java](App.java) 
[LibraryManager.java](LibraryManager.java)  
[Author.java](Author.java) / [BookAuthor.java](BookAuthor.java)  / [KeyWord.java](KeyWord.java) / [BookKeyWord.java](BookKeyWord.java) / [Book.java](Book.java)|
</div>

## Descripción
Esta versión implementa un sistema de gestión de biblioteca. Permite realizar operaciones como agregar, editar, buscar y eliminar documentos, autores, palabras clave y sus relaciones.

## Clases Principales
- `LibraryManager.java`: Clase principal que gestiona la biblioteca y sus operaciones.
- `Author.java`: Representa a un autor con su ID y nombre.
- `BookAuthor.java`: Relaciona libros y autores mediante sus IDs.
- `KeyWord.java`: Representa una palabra clave con su ID y nombre.
- `BookKeyWord.java`: Relaciona libros y palabras clave mediante sus IDs.
- `Book.java`: Representa un libro con su ID, título, año de publicación, tipo y sus relaciones.

## Funcionalidades
- Agregar un nuevo documento.
- Listar documentos, autores y palabras clave.
- Buscar un documento por título, año, autor, tipo o palabras clave.
- Editar un documento existente (título, año, tipo, autores, palabras clave).
- Eliminar un documento de la biblioteca.

## Uso
1. Ejecutar `App.java`.
2. Seguir las opciones del menú para realizar las operaciones deseadas.

