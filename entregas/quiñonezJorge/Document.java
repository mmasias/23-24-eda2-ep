package entregas.quiñonezJorge;

import java.util.ArrayList;

public class Document {
  private int id;
  private String title;
  private String releaseDate;
  private String documentType;

  public Document(int id, String title, String releaseDate, String type) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
    this.documentType = type;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    String content = "";
    content += "\n" + "id: " + id + "\n" + "Title: " + title + "\n" + "Type: " + documentType + "\n" + "Release date: "
        + releaseDate
        + "\n";

    return content;
  }
}
