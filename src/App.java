import au.edu.uts.ap.javafx.ViewLoader;
import javafx.application.Application;
import javafx.stage.Stage;
import model.LoginModel;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        ViewLoader.showStage(new LoginModel(), "/view/LoginView.fxml", "Login", new Stage());
    }
}

