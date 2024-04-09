package bibliotecad;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class LibraryManager {
    private List<Book> books;  
    private List<Author> authors; 
    private List<Word> words;  
    private List<BookAuthor> relations;       
    private List<BookWord> relationsWord;
    private Book book;
    private Scanner userInput;


    public LibraryManager() {
        this.books= new ArrayList<Book>();
        this.authors= new ArrayList<Author>();
        this.words = new ArrayList<Word>();
        this.relations = new ArrayList<BookAuthor>();           
        this.relationsWord= new ArrayList<BookWord>();  
        this.userInput = new Scanner(System.in); 
    } 

    public void startLibraryManager() {
        System.out.println("Bienvenido al Gestor de Biblioteca");
        boolean salir=false;        
        while (!salir) {
            System.out.println("Elige una opción:");
            System.out.println("(1) Agregar un nuevo documento");
            System.out.println("(2) Edita documento por ID"); 
            System.out.println("(3) Edita documento por características");             
            System.out.println("(4) Borra documento por ID");
            System.out.println("(5) Borra documento por características"); 
            System.out.println("(6) Busca documento y muestra un documento por ID"); 
            System.out.println("(7) Busca documento y muestra un documento por catacterísticas");          
            System.out.println("(8) Lista todos los libros");           
            System.out.println("(9) Lista todos los autores");           
            System.out.println("(10) Lista todas las palabras");           
            System.out.println("(11) Añadir autor"); 

            System.out.println("(-1) Salir");
            String opcion = userInput.nextLine();
            if (opcion.compareTo("-1")==0)
                salir=true;
            else
            {
                switch (opcion){
                    case "1": addBook(); break;
                    case "2": editBookByID();break;
                    case "3": editBook();break;
                    case "4": deleteBookById();break;
                    case "5": deleteBook();break;
                    case "6": findAndPrintDocPorId(0);break;
                    case "7": findBook();break;
                    case "8": findAndPrintDocPorId(1);break;
                    case "9": listAuthors();break;
                    case "10": listWords();break; 
                    case "11": addAuthor();break;                  
                }               
            }
        }
        userInput.close();
    }    

    private void addAuthor()
    {
        System.out.println("Introduce el nombre del autor:");
        String nombre = userInput.nextLine();
        Author autor = new Author(authors.size() + 1,nombre);
        addAuthor(autor);
        listAuthors();
    }
    private void addAuthor(Author autor)
    {
        authors.add(autor);
    }
   
    private void findAndPrintDocPorId(int modo)
    {
        if (modo==0)
        {
            System.out.println("Introduce el id del libro");
            int opcion = Integer.parseInt(userInput.nextLine());
            Book book=findBookById(opcion);        
            if (book!=null)
            {            
                System.out.println(book.toString());
                printAuthorsBook(book.getId());
                printWordsBook(book.getId());
            }
        }
        else
            printAllBooks();
    }
    private void printAllBooks()
    {
        for(Book book:books)
        {
            System.out.println(book.toString());
            printAuthorsBook(book.getId());
            printWordsBook(book.getId());
        }
    }
        
  
    private void printAuthorsBook(int bookId)
    {
        Author autor=null;            
        for (BookAuthor autorlibro: relations)
        {                
            if (autorlibro.getBookId()==bookId)
               autor=findAuthorById(autorlibro.getAuthorId());
               if (autor!=null)
                    System.out.println(autor.toString());
        }
    }    
  
    private void printWordsBook(int bookId)
    {
        Word word=null;            
        for (BookWord wordlibro: relationsWord)
        {                
            if (wordlibro.getBookId()==bookId)
               word=findWordById(wordlibro.getWordId());
               if (word!=null)
               System.out.println(word.toString());
        }
    }  
    private Word findWordById(int wordId)
    {
        for (Word word: words)
        {
            if (word.getId()==wordId)
                return word;
        }
        return null;
    }   
    private Author findAuthorById(int authorId)
    {
        for (Author autor: authors)
        {
            if (autor.getId()==authorId)
                return autor;
        }
        return null;
    }  
    private Book findBook() {
        boolean encontrado=false;
        Book docres=null;
        List<Book> docs=null;     
        System.out.println("(TIPO,TITULO,ANIO,AUTORES,PALABRAS) Indique la propiedad por la que desea buscar el documento:");
        String tipo = userInput.nextLine();
        System.out.println("Indique el valor por el que desea buscar:");
        String propiedad=userInput.nextLine();
        docs=getBooksBy(tipo,propiedad, null); 
        if (docs!=null)
        {
            while (!encontrado)
            {
                if (docs.size()==1)
                {
                    System.out.println(docs.get(0).toString());
                    encontrado=true;
                    docres=docs.get(0);
                }
                else
                {
                    //Hay varios documentos en la lista que cumplen el criterio. Hay que acotar
                    System.out.println("(TIPO,TITULO,ANIO,AUTORES,PALABRAS) Indique la propiedad por la que desea buscar el documento:");
                    tipo = userInput.nextLine();
                    System.out.println("Indique el valor por el que desea buscar:");
                    propiedad=userInput.nextLine();
                    docs=getBooksBy(tipo,propiedad,docs);        
                }
            }
        }
        return docres;
    }
    private Book findBook(String type,String title,String anio) {
        boolean encontrado=false;
        Book docres=null;
        List<Book> docs=null;
        String tipo = type;
        String propiedad=title;
        docs=getBooksBy(tipo,propiedad, null);
        if (docs!=null)
        {
            while (!encontrado)
            {
                if (docs.size()==1)
                {
                    System.out.println(docs.get(0).toString());
                    encontrado=true;
                    docres=docs.get(0);
                }
                else
                {
                    //Hay varios documentos en la lista que cumplen el criterio. Hay que acotar

                    tipo = type;
                    String propiedad2=anio;
                    docs=getBooksBy(tipo,propiedad2,docs);
                }
            }
        }
        return docres;
    }
    private void editBookByID()
    {
        try{
            printAllBooks();
            System.out.println("Introduzca el ID del libro que desea editar:");
            int opcion = Integer.parseInt(userInput.nextLine());
            Book doc=findBookById(opcion);
            if (doc!=null)
                editBook(doc);
        }catch(NumberFormatException ex)
        {
            System.out.println(ex.toString());
        }
    }
    private void editBook()
    {
        Book doc=findBook();
        if (doc!=null)
            editBook(doc);
    }       
  
    public void editBook(Book doc)
    {
        int indice=this.books.indexOf(doc);
        if (indice>=0)
        {
            boolean actualizando=false;
            System.out.println("La información del documento es:");
            while(!actualizando)
            {
                System.out.println("Indique con tecleando el número de lo que desea modificar del documento:(-1 para terminar)");               
                System.out.println("1. Título");
                System.out.println("2. Año publicación");
                System.out.println("3. Autores");
                System.out.println("4. Palabras clave");         
                String texto = userInput.nextLine(); 
                switch(texto)
                {                
                    case "1":
                        System.out.println("Escriba el nuevo título");
                        String t=userInput.nextLine(); 
                        books.get(indice).setTitle(t);
                        break;
                    case "2":
                        System.out.println("Escriba el año de publicación del documento");         
                        texto = userInput.nextLine();
                        books.get(indice).setPubYear(Integer.parseInt(texto));
                        break;
                    case "3":
                        updateAuthor(doc);                        
                        break;
                    case "4":
                        updateWords(doc);
                        break;
                    case "-1":
                        actualizando=true;
                        break;
                }
            }     
        }
    }
    
 
    private void updateAuthor(Book book)
    {
        deleteBookAuthorsbyBookId(book.getId());
        addAuthor(book);
    }
   
    private void updateWords(Book book)
    {
        deleteBookWordsbyBookId(book.getId());
        addWords(book);
    }   
    private Book findBookById(int bookId) {
        for (Book book : books) {
            if (book.getId() == bookId) {
                return book;
            }
        }
        return null;
    }
    private void deleteBookById()
    {
        Book encontrado=null;
        try{
            System.out.println("Selecciona el ID de libro a borrar");
            listBooks();
            int bookId = Integer.parseInt(userInput.nextLine());
            encontrado=findBookById(bookId);        
            if (encontrado!=null)
            {               
                //Borramos relaciones del libro con autores
                this.deleteBookAuthorsbyBookId(bookId);
                //Borramos relaciones del libro con palabras
                this.deleteBookWordsbyBookId(bookId);                
                //Finalmente borramos libro
                this.books.removeIf(n -> n.getId()==bookId);
            }
        }catch(Exception e)
        {
            System.out.println("Ha habido un error al introducir el número de libro a borrar."+ e.toString());
        }
    }

    
    private void deleteBookAuthorsbyBookId(int bookId)
    {
        relations.removeIf(n -> n.getBookId()==bookId);        
    }
    private void deleteBookWordsbyBookId(int bookId)
    {
        relationsWord.removeIf(n -> n.getBookId()==bookId);        
    }
    private void deleteBook()
    {      
        System.out.println("Indique el tipo de documento que desea borrar (L,R,A,P)");
        String tipo = userInput.nextLine();
        System.out.println("Indique el título del libro");        
        String title= userInput.nextLine();
        System.out.println("Indique el año de publicación del libro");
        String anio=userInput.nextLine();

        Book book = findBook(tipo,title,anio);
        //Falta borrar relaciones libro autores
        this.deleteBookAuthorsbyBookId(book.getId());
        //Falta borrar relaciones libro palabras
        this.deleteBookWordsbyBookId(book.getId());
        //Finalmente borramos libro
        this.books.removeIf(n -> (n.getTipo().compareTo(tipo)==0 && n.getTitle().compareTo(title)==0 && n.getPubYear()==Integer.parseInt(anio)));


    }
    private void addBook() {
        System.out.println("(L,R,A,P) Indique el tipo de documento que va a crear:");
        String tipo = userInput.nextLine();
        System.out.println("Indique el título del documento");        
        String tit=userInput.nextLine();        
        System.out.println("Año de publicación del documento");         
        String texto = userInput.nextLine();        
        int aniopub=Integer.parseInt(texto);

        Book newBook= new Book(books.size() + 1, tit, aniopub, tipo);
        books.add(newBook);

        System.out.println("Libro añadido. ¿Deseas añadir autores a este libro? (s/n)");
        String response = userInput.nextLine();
        if ("s".equalsIgnoreCase(response)) 
            addAuthor(newBook);
        
        System.out.println("¿Deseas añadir palabras clave a este libro? (s/n)");
        response = userInput.nextLine();
        if ("s".equalsIgnoreCase(response))            
            addWords(newBook);                  
        
        System.out.println("Libro creado y añadido:"+newBook.toString());
    }
    private void addBook(Book book){
        books.add(book);
        System.out.println("Libro creado y añadido:"+book.toString());
    }
  
    public void addWords(Book libr)
    {
        boolean leyendo=false;        
        while (!leyendo)
        {
            System.out.println("Selecciona el ID de la palabra para asociar con el libro, o introduce 'nuevo' para añadir una nueva palabra:");
            listWords();
            String input = userInput.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce la palabra:");
                String name = userInput.nextLine();
                Word newWord = new Word(this.words.size() + 1, name);
                words.add(newWord);
                addRelationWord(libr.getId(), newWord.getId());
                System.out.println("Palabra nueva añadida y asociada al libro.");
            } else {
                try {
                    int wordId = Integer.parseInt(input);
                    addRelationWord(libr.getId(), wordId);
                    System.out.println("Palabra asociada al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otra palabra a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(userInput.nextLine())) {
                leyendo = !leyendo;
            }           
        }
    } 
    public void addAuthor(Book book)
    {        
        boolean leyendo=false;
        while (!leyendo)
        {            
            System.out.println("Selecciona el ID del autor para asociar con el libro, o introduce 'nuevo' para añadir un nuevo autor:");
            listAuthors();
            String input = userInput.nextLine();
            if ("nuevo".equalsIgnoreCase(input)) {
                System.out.println("Introduce el nombre del nuevo autor:");
                String name = userInput.nextLine();
                Author newAuthor = new Author(this.authors.size() + 1, name);
                authors.add(newAuthor);
                addRelation(book.getId(), newAuthor.getId());
                System.out.println("Autor nuevo añadido y asociado al libro.");
            } else {
                try {
                    int authorId = Integer.parseInt(input);
                    addRelation(book.getId(), authorId);
                    System.out.println("Autor asociado al libro.");
                } catch (NumberFormatException e) {
                    System.out.println("Entrada no válida.");
                }
            }
            System.out.println("¿Deseas añadir otro autor a este libro? (s/n)");
            if (!"s".equalsIgnoreCase(userInput.nextLine())) {
                leyendo = !leyendo;
            }
        }
    }
    private void addRelation(int bookId, int authorId) {

        relations.add(new BookAuthor(bookId, authorId));
    }
    private void addRelationWord(int bookId, int wordId) {
        relationsWord.add(new BookWord(bookId, wordId));
    }
    private void listWords() {
        if (words.isEmpty()) 
            System.out.println("> No hay palabras disponibles.");
        else 
            for (Word word : words) 
                System.out.println(word.toString());
    }
    private void listAuthors() {
        if (authors.isEmpty()) 
            System.out.println("> No hay autores disponibles.");
        else 
            for (Author author : authors) 
                System.out.println(author.toString());
    }
    private void listBooks() {
        if (books.isEmpty()) 
            System.out.println("> No hay libros disponibles.");
        else 
            for (Book libro : books) 
                System.out.println(libro.toString());
    }
   
    private List<Book> getBooksBy(String tipo, String propiedad, List<Book> donde) {
        if (donde==null)
            donde=books;
        List<Book> docres=new ArrayList<Book>();
        for (Book doc:donde)
            switch(tipo)
            {
                case "TIPO":               
                        if (doc.getTipo().compareTo(propiedad)==0)                    
                            docres.add(doc);  
                        break;
                case "TITULO":
                        if (doc.getTitle().compareTo(propiedad)==0)
                            docres.add(doc);                
                        break;
                case "ANIO":
                        if (doc.getPubYear()==Integer.parseInt(propiedad))
                            docres.add(doc);                
                        break;
                case "AUTORES":
                        if (comprueba(doc,propiedad,"Autor"))                        
                            docres.add(doc);                       
                        break;
                case "PALABRAS":
                        if (comprueba(doc,propiedad,"Palabras"))                        
                            docres.add(doc);   
                        break;
            }
        return docres;        
    }  
    private List<Author> getAuthorsBookById(int bookId)
    {
        List<Author> autores=new ArrayList<Author>();
        for (BookAuthor bookAutor:relations)
            if (bookAutor.getBookId()==bookId)
                autores.add(findAuthorById(bookAutor.getAuthorId()));
        return autores;
    }
    private List<Word> getWordsBookById(int bookId)
    {
        List<Word> palabras=new ArrayList<Word>();
        for (BookWord bookword:relationsWord)
            if (bookword.getBookId()==bookId)
                palabras.add(findWordById(bookword.getWordId()));
        return palabras;
    }
    private boolean comprueba(Book doc,String propiedad, String tipo)
    {
        switch (tipo) {
            case "Autor":
                if(getAuthorsBookById(doc.getId()).toString().contains(propiedad))
                    return true;                
                break;
            case "Palabras":
                if(getWordsBookById(doc.getId()).toString().contains(propiedad))
                    return true;  
                break;

        }
        return false;
    }
}
