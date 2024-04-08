package Models;

import Enums.DocumentType;
import java.util.List;

public class Document {

  private Integer id;
  private String title;
  private List<Integer> authors;
  private int publicationYear;
  private DocumentType documentType;
  private List<Integer> keywords;

  public Document(
    String title,
    List<Integer> authors,
    int publicationYear,
    DocumentType documentType,
    List<Integer> keywords
  ) {
    this.title = title;
    this.authors = authors;
    this.publicationYear = publicationYear;
    this.documentType = documentType;
    this.keywords = keywords;
    this.id = generateId();
  }

  private Integer generateId() {
    String uniqueString = title + System.nanoTime();
    return Math.abs(uniqueString.hashCode());
  }

  public Integer getId() {
    return id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public List<Integer> getAuthors() {
    return authors;
  }

  public void setAuthors(List<Integer> authors) {
    this.authors = authors;
  }

  public int getPublicationYear() {
    return publicationYear;
  }

  public void setPublicationYear(int publicationYear) {
    this.publicationYear = publicationYear;
  }

  public DocumentType getDocumentType() {
    return documentType;
  }

  public void setDocumentType(DocumentType documentType) {
    this.documentType = documentType;
  }

  public List<Integer> getKeywords() {
    return keywords;
  }

  public void setKeywords(List<Integer> keywords) {
    this.keywords = keywords;
  }
}
