package Models;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DigitalLibrary {

  private List<Document> documents = new ArrayList<>();
  private List<Author> authors = new ArrayList<>();
  private List<Keyword> keywords = new ArrayList<>();

  public List<Document> getDocuments() {
    return documents;
  }

  public List<Author> getAuthors() {
    return authors;
  }

  public List<Keyword> getKeywords() {
    return keywords;
  }

  public void addDocument(Document document) {
    documents.add(document);
  }

  public Document getDocument(int documentId) {
    return documents
      .stream()
      .filter(document -> document.getId() == documentId)
      .findFirst()
      .orElse(null);
  }

  public void updateDocument(int documentId, Document updatedDocument) {
    documents =
      documents
        .stream()
        .map(document ->
          document.getId() == documentId ? updatedDocument : document
        )
        .collect(Collectors.toList());
  }

  public void deleteDocument(int documentId) {
    documents.removeIf(document -> document.getId() == documentId);
  }

  public void addAuthor(Author author) {
    authors.add(author);
  }

  public Author getAuthor(int authorIndex) {
    if (authorIndex > 0 && authorIndex <= authors.size()) {
      return authors.get(authorIndex - 1);
    }
    return null;
  }

  public void updateAuthor(int authorIndex, Author updatedAuthor) {
    if (authorIndex > 0 && authorIndex <= authors.size()) {
      authors.set(authorIndex - 1, updatedAuthor);
    }
  }

  public void deleteAuthor(int authorIndex) {
    if (authorIndex > 0 && authorIndex <= authors.size()) {
      authors.remove(authorIndex - 1);
    }
  }

  public void addKeyword(Keyword keyword) {
    keywords.add(keyword);
  }

  public Keyword getKeyword(int keywordIndex) {
    if (keywordIndex > 0 && keywordIndex <= keywords.size()) {
      return keywords.get(keywordIndex - 1);
    }
    return null;
  }

  public void updateKeyword(int keywordIndex, Keyword updatedKeyword) {
    if (keywordIndex > 0 && keywordIndex <= keywords.size()) {
      keywords.set(keywordIndex - 1, updatedKeyword);
    }
  }

  public void deleteKeyword(int keywordIndex) {
    if (keywordIndex > 0 && keywordIndex <= keywords.size()) {
      keywords.remove(keywordIndex - 1);
    }
  }
}
