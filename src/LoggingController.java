import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoggingController implements Initializable {
    @FXML private TextField nombre;
    @FXML private TextField id;

    private MainBanco banco;

    @FXML private void handleIngresar(ActionEvent event) {
        if(banco.verificaClienteLogin(Integer.parseInt(id.getText().trim()), nombre.getText().trim())){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));

            try {
                Parent root = loader.load();

                MenuController controller = loader.getController();

                Scene scene = new Scene(root);
                Stage stage = new Stage();

                controller.setBanco(banco);
                controller.setCliente(banco.getClienteMain());

                stage.initModality(Modality.WINDOW_MODAL);
                stage.setTitle("Keant Bank");
                stage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon")));
                stage.setResizable(false);
                stage.setScene(scene);
                close(event);
                stage.show();

            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 002\n"+e.getCause());
            }


        }
    }
    
    @FXML private void handleCrear(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarCliente.fxml"));
            
            Parent root = loader.load();//posible error aqui

            AgregarClienteController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            controller.setBanco(banco);

            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setTitle("Keantp Bank");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon")));
            stage.setResizable(false);
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: 001\n"+e.getCause());
        }
    }

    //closer
    @FXML private void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void setBanco(MainBanco banco){
        this.banco=banco;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
