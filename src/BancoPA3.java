import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class BancoPA3 extends Application {
    private MainBanco banco = new MainBanco();

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Logging.fxml"));

        Parent root = loader.load();

        LoggingController controller = loader.getController();

        Scene scene = new Scene(root);
        

        controller.setBanco(banco);
        stage.setTitle("Banck");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon")));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}
