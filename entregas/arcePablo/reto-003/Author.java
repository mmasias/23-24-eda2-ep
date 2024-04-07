import java.time.LocalDate;
import java.util.ArrayList;
import Types.DocType;

public class Author {
    private String name;
    private String email;
    private LocalDate birthDate;
    private ArrayList<DocType> genres;

    public Author(String name) {
        this.name = name;
        this.email = "";
        this.birthDate = LocalDate.now();
        this.genres = new ArrayList<>();
    }

    public Author(String name, LocalDate birthDate, ArrayList<DocType> genres) {
        this.name = name;
        this.email = "";
        this.birthDate = birthDate;
        this.genres = genres;
    }

    public String getName() {
        return name;
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
