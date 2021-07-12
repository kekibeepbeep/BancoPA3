/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author diego
 */
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
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
