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

## CAMBIOS IMPREVISTOS

De momento vacio 


