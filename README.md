# EXAMEN PARCIAL - Ajustes realizados al reto 3

El primer paso realizado ha sido la modificación de los nombres de las clases para que se ajuste al modelo propuesto.  

Las clases con nombre en español son las originales, mientras que las clases con nombre en inglés son las que he modificado. Entre los cambios realizados se encuentran:

- He cambiado los atributos de públicos a privados.
- En la clase `Book` y `Author` he añadido el atributo `id`.
- En la clase `Book` he eliminado los arrays con autores y palabras clave, además de suprimir métodos y cambiar constructores y métodos `toString` para su impresión por pantalla.
- He implementado la clase `BookAuthor` para relacionar los IDs de autor y libro.
- En la clase `LibraryManager` (originalmente `Biblioteca`) he creado los atributos de las listas de libros, autores y relaciones, además de modificar los métodos para que puedan ser utilizados también por la lista `relations` que agrupa las relaciones `authorId-bookId`.
- Al implementar la clase `App`, únicamente he iniciado el método `startLibraryManager` de la clase principal `LibraryManager`.
