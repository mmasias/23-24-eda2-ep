import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;
import Types.DocType;

public class Author {
    private String id;
    private String name;
    private String email;
    private LocalDate birthDate;
    private ArrayList<DocType> genres;

    public Author(String name) {
        this.name = name;
        this.email = "";
        this.birthDate = LocalDate.now();
        this.genres = new ArrayList<>();
        this.id = generateId();
    }

    public Author(String name, LocalDate birthDate, ArrayList<DocType> genres) {
        this.name = name;
        this.email = "";
        this.birthDate = birthDate;
        this.genres = genres;
        this.id = generateId();
    }

    private String generateId() {
        return UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public ArrayList<DocType> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<DocType> genres) {
        this.genres = genres;
    }
}
