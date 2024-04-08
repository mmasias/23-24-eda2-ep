package Models;

import java.util.ArrayList;
import java.util.List;

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

  public Document getDocument(int documentIndex) {
    if (documentIndex >= 0 && documentIndex < documents.size()) {
      return documents.get(documentIndex);
    }
    return null;
  }

  public List<Document> getDocumentsByAuthor(int authorId) {
    List<Document> documentsByAuthor = new ArrayList<>();
    for (Document document : documents) {
      if (document.getAuthors().contains(authorId)) {
        documentsByAuthor.add(document);
      }
    }
    return documentsByAuthor;
  }

  public List<Document> getDocumentsByKeyword(int keywordId) {
    List<Document> documentsByKeyword = new ArrayList<>();
    for (Document document : documents) {
      if (document.getKeywords().contains(keywordId)) {
        documentsByKeyword.add(document);
      }
    }
    return documentsByKeyword;
  }

  public void updateDocument(int documentIndex, Document updatedDocument) {
    if (documentIndex >= 0 && documentIndex < documents.size()) {
      documents.set(documentIndex, updatedDocument);
    }
  }

  public void deleteDocument(int documentIndex) {
    if (documentIndex >= 0 && documentIndex < documents.size()) {
      documents.remove(documentIndex);
    }
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

  public Author getAuthorById(int authorId) {
    return authors
      .stream()
      .filter(author -> author.getId() == authorId)
      .findFirst()
      .orElse(null);
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

  public Keyword getKeywordById(int keywordId) {
    return keywords
      .stream()
      .filter(keyword -> keyword.getId() == keywordId)
      .findFirst()
      .orElse(null);
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
