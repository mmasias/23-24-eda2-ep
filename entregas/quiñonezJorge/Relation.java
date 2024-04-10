package entregas.quiñonezJorge;

public class Relation {
  private int authorId;
  private int documentId;

  public Relation(int authorId, int documentId) {
    this.authorId = authorId;
    this.documentId = documentId;
  }

  public int getAuthorId() {
    return authorId;
  }

  public int getDocumentId() {
    return documentId;
  }
}
