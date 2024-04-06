import Controllers.DigitalLibraryController;
import Models.DigitalLibrary;
import Views.DigitalLibraryView;

public class App {

  public static void main(String[] args) {
    DigitalLibrary digitalLibrary = new DigitalLibrary();

    DigitalLibraryView view = new DigitalLibraryView();

    DigitalLibraryController controller = new DigitalLibraryController(
      digitalLibrary,
      view
    );

    controller.start();
  }
}
