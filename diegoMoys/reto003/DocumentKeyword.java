import java.util.Objects;

public class DocumentKeyword {
    private Document document;
    private Keywords keyword;

    public DocumentKeyword(Document document, Keywords keyword) {
        this.document = document;
        this.keyword = keyword;
    }

    public Document getDocument() {
        return document;
    }

    public Keywords getKeyword() {
        return keyword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DocumentKeyword that = (DocumentKeyword) o;
        return document.equals(that.document) && keyword.equals(that.keyword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(document, keyword);
    }

    @Override
    public String toString() {
        return keyword.getKeyword();
    }
}
