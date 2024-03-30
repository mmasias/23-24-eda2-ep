import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Book> documents;
    private List<Author> authors;

    public LibraryManager() {
        this.documents = new ArrayList<>();
        this.authors = new ArrayList<>();
    }

    public List<Book> getAllDocuments() {
        return this.documents;
    }

    public List<Author> getAllAuthors() {
        return this.authors;        
    }

    public void addDocument(String title, List<String> authorNames, int publishingYear, String type, List<String> keywords) {
        Book document = new Book(title, publishingYear, type, keywords);
        
        for (String authorName : authorNames) {
            Author author = null;
            for (Author existingAuthor : authors) {
                if (existingAuthor.getName().equalsIgnoreCase(authorName)) {
                    author = existingAuthor;
                    break;
                }
            }
            if (author == null) {
                author = new Author(authorName);
                authors.add(author);
            }
            document.addAuthor(author);
        }

        documents.add(document);
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
    
    public List<String> getDocAuthors(Book document) {
        List<String> authorNames = new ArrayList<>();
        for (Author author : this.authors) {
            if (author.getDocuments().contains(document)) {
                authorNames.add(author.getName());
            }
        }
        return authorNames;
    }
    
    public Book searchDocByTitle(String title) {
        for (Book doc : documents) {
            if (doc.getTitle().equalsIgnoreCase(title)) {
                return doc;
            }
        }
        return null;
    }
    
    public List<Book> searchDocByAuthor(String authorName) {
        List<Book> results = new ArrayList<>();
        Author author = searchAuthorByName(authorName);
        if (author != null) {
            results.addAll(author.getDocuments());
        }
        return results;
    }
    
    public Author searchAuthorByName(String name) {
        for (Author author : authors) {
            if (author.getName().equalsIgnoreCase(name)) {
                return author;
            }
        }
        return null;
    }
    
    public List<Book> searcDocByType(String type) {
        List<Book> results = new ArrayList<>();
        for (Book doc : documents) {
            if (doc.getType().equalsIgnoreCase(type)) {
                results.add(doc);
            }
        }
        return results;
    }

    public List<Book> searchDocByYear(int year) {
        List<Book> results = new ArrayList<>();
        for (Book doc : documents) {
            if (doc.getPublishingYear() == year) {
                results.add(doc);
            }
        }
        return results;
    }
    
    public List<Book> searchDocByKeyword(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book doc : documents) {
            if (doc.getKeyWords().contains(keyword)) {
                results.add(doc);
            }
        }
        return results;
    }

    public void updateDocument(String title, Book updatedDocument) {
        for (Book doc : documents) {
            if (doc.getTitle().equalsIgnoreCase(title)) {
                doc.setTitle(updatedDocument.getTitle());
                doc.setPublishingYear(updatedDocument.getPublishingYear());
                doc.setType(updatedDocument.getType());
                doc.setKeyWords(updatedDocument.getKeyWords());

                for (Author docAuthor : doc.getAuthors()) {
                    boolean existsAuthor = false;
                    for (Author author : this.authors) {
                        if (author.getName().equals(docAuthor.getName())) {
                            existsAuthor = true;
                            break;
                        }
                    }
                    if (!existsAuthor) {
                        this.authors.add(docAuthor);
                    }
                    if (!docAuthor.getDocuments().contains(doc)) {
                        docAuthor.addDocument(doc);
                    }
                }
                return;
            }
        }
    }

    public void deleteDocument(String title) {
        Book toDelete = searchDocByTitle(title);
        
        if (toDelete != null) {
            List<Author> docAuthors = toDelete.getAuthors();
            if (docAuthors != null) {
                for (Author author : docAuthors) {
                    author.removeDocument(toDelete);
                }
            }
            documents.remove(toDelete);
        }
    }
    
}
