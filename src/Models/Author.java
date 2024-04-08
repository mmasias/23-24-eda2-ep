package Models;

public class Author {

  private Integer id;
  private String name;
  private String affiliation;

  public Author(String name, String affiliation) {
    this.name = name;
    this.affiliation = affiliation;
    this.id = generateId();
  }

  private Integer generateId() {
    String uniqueString = name + System.nanoTime();
    return Math.abs(uniqueString.hashCode());
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAffiliation() {
    return affiliation;
  }

  public void setAffiliation(String affiliation) {
    this.affiliation = affiliation;
  }
}
