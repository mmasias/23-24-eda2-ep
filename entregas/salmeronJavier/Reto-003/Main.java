public class Main {
    public static void main(String[] args) {
        LibraryManager library = new LibraryManager();
        User user = new User(library);
        user.start();
    }
}