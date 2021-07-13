import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class LoggingController implements Initializable {
    private MainBanco banco;

    @FXML private void handleIngresar(ActionEvent event) {
        System.out.println("Ingresar");
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
            stage.setTitle("Bank");
            stage.setScene(scene);
            stage.showAndWait();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: 001\n"+e.getCause());
        }
        
        System.out.println("Crear");
    }

    public void setBanco(MainBanco banco){
        this.banco=banco;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }    
    
}
