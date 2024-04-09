# REVISIÓN DE CÓDIGO 
## COMPARACIÓN CON LA SOLUCIÓN PROPUESTA
Primero voy a comprobar solo leyendo las clases lo que deben/pueden hacer. 
+ **BookAuthor** deberia hacer algo parecido a mi **GestorAutores**. BookAuthor relaciona directamente autor con los libros algo que deberia implementar en mi clase, en vez de que lo haga el libro.

+ **Book** es una clase con los atributos de los libros semejante a **Documento** aunque ahora eliminando la relación con los autores. De esto ya se encargará otra clase.

+ **Author** es semajante a **Autor**

+ **LibraryManager** es **Gestor** aunque a falta de implementar los cambios y revisar de nuevo

+ **App** es mi **Biblioteca** que ejecuta la aplicación y en mi caso introduce unos documentos a modo de ejemplo

## CAMBIOS

1. Traducir el programa al inglés 
2. Implementar la clase BookAuthor que se encarga de relacionar autores y libros para dar la correcta resposabilidad a cada clase
3. Corregir Gestor para que funcione con los cambios 
4. Crear e implementar las clase de **Topic** y **TopicBook**  

## CAMBIOS IMPREVISTOS

Una idea que he tenido para el buscador es que sea un metodo a parte que se encargue de juntar los otros metodos de buscar. Por ejemplo, si pulsar el 5 inicia el buscador y que se incie otro menu que muestre: 1-Buscar autor por ID, 2-Buscar libro por autor, etc. Haria el método start mucho más limpio y juntaría varios métodos que alargan demasiado *start*

## DIAGRAMA DE CLASES

El siguiente diagrama muestra la estructura de clases del proyecto:

![Diagrama de Clases](https://github.com/Jorgeog25/23-24-eda2-ep/blob/parcial/entregas/oteroJorge/reto003/documents/Library.png)

