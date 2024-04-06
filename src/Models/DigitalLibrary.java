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

  public Author getAuthor(int authorId) {
    return authors
      .stream()
      .filter(author -> author.getId().equals(authorId))
      .findFirst()
      .orElse(null);
  }

  public void updateAuthor(int authorId, Author updatedAuthor) {
    authors =
      authors
        .stream()
        .map(author -> author.getId().equals(authorId) ? updatedAuthor : author)
        .collect(Collectors.toList());
  }

  public void deleteAuthor(int authorId) {
    authors.removeIf(author -> author.getId().equals(authorId));
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
