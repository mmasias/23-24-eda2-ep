package entregas.quiñonezJorge;

import java.util.UUID;

public class Author {

  private String uuid;
  private String name;

  public Author(String name) {
    this.uuid = UUID.randomUUID().toString();
    this.name = name;
  }

  public String getUuid() {
    return uuid;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
