# Gestor de Biblioteca

Este proyecto es un sistema de gestión de biblioteca que permite administrar libros, autores y sus relaciones.

## Estructura del Proyecto

  - `Book.java`: Clase para representar libros.
  - `Author.java`: Clase para representar autores.
  - `BookAuthor.java`: Clase para manejar las relaciones entre libros y autores.
  - `LibraryManager.java`: Clase principal que gestiona la biblioteca.

## Clases

### Book

La clase `Book` representa un libro en la biblioteca. Contiene los siguientes atributos:

- `id`: Identificador único del libro.
- `title`: Título del libro.
- `publicationYear`: Año de publicación del libro.
- `type`: Tipo de documento (libro, revista, etc.).
- `keywords`: Palabras clave asociadas al libro.

### Author

La clase `Author` representa un autor en la biblioteca. Tiene los siguientes atributos:

- `id`: Identificador único del autor.
- `name`: Nombre del autor.

### BookAuthor

La clase `BookAuthor` representa la relación entre libros y autores. Contiene:

- `bookId`: ID del libro.
- `authorId`: ID del autor.

### LibraryManager

La clase `LibraryManager` es la interfaz principal para interactuar con la biblioteca. Ofrece las siguientes funcionalidades:

1. **Listar Libros y Autores:** Muestra la lista de libros y autores disponibles.
2. **Agregar Libros y Autores:** Permite añadir nuevos libros y autores al sistema.
3. **Relacionar Libros y Autores:** Establece una relación entre un libro y un autor existente.
4. **Buscar Libros por Palabras Clave:** Realiza una búsqueda de libros usando palabras clave.
5. **Listar Relaciones de Libros y Autores:** Muestra las relaciones establecidas entre libros y autores.

## Uso

1. Ejecuta la clase `LibraryManager` para iniciar la aplicación.
2. Selecciona las opciones del menú para realizar las operaciones deseadas.

## Ejemplo de Uso

Supongamos que queremos agregar un libro y relacionarlo con un autor existente:

1. Seleccionamos la opción **Agregar Libro** e ingresamos los detalles del libro.
2. Luego, elegimos la opción **Listar Autores** para ver la lista de autores disponibles.
3. Seleccionamos el ID del autor con el que queremos relacionar el libro al agregar la relación.

El sistema establecerá la relación entre el libro y el autor seleccionados.

## Listado Completo de Funcionalidades

- **Listar Libros y Autores:** Mostrar todos los libros y autores disponibles en la biblioteca.
- **Agregar Libros y Autores:** Añadir nuevos libros y autores al sistema.
- **Relacionar Libros y Autores:** Establecer relaciones entre libros y autores existentes.
- **Buscar Libros por Palabras Clave:** Realizar búsquedas de libros utilizando palabras clave.
- **Listar Relaciones de Libros y Autores:** Mostrar las relaciones existentes entre libros y autores.
- **Salir:** Finalizar la aplicación.