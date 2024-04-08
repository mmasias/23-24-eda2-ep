Aquí se subirá la refactorización del parcial:

Proceso de creación: Se tomaron las clases más reutilizables, y puesto que mi estructura era demasiado diferente se optó por crear las clases y reconstruirlas tomando de las mías lo posible: 

- en las clases de book, bookAuthor y Author al ser puramente de atributos a usar por la clase LibraryManager se copiaron los atributos de mi clase Author y debido a que mi enfoque respecto a autores era por medio de nombre y apellido únicamente pude tomar el atributo name

- en la clase LibraryManager se tomaron los métodos showAllDocuments() y el main de la clase colection, puesto que estos eran el menú (al cúal eliminé algunas funciones) y el listado de las arraylist (estructura que siempre se usa)

- la clase app es la ejecutable que se extrajo del código, como mi main estaba dentro de la clase colection se optó por seguir tu ejemplo y crear la clase app puramente para el ejecutable

- se optó por separar la creación de autores y su asignación fuera de la creación de los libros para no hacer la creación de libros tan tediosa y poder crear autores y relaciones en todo momento sin depender de la creación de libros

