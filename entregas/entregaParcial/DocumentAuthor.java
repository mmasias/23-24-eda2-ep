import java.util.Objects;

public class DocumentAuthor {
    private Document document;
    private Author author;

    public DocumentAuthor(Document document, Author author) {
        this.document = document;
        this.author = author;
    }

    public Document getDocument() {
        return document;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentAuthor that = (DocumentAuthor) o;
        return document.equals(that.document) && author.equals(that.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, author);
    }

    @Override
    public String toString() {
        return author.getName();
    }
}
