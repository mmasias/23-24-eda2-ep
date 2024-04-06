package Models;

public class Keyword {

  private Integer id;
  private String keyword;

  public Keyword(String keyword) {
    this.keyword = keyword;
    this.id = generateId();
  }

  private Integer generateId() {
    String uniqueString = keyword + System.nanoTime();
    return uniqueString.hashCode();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }
}
