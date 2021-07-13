import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class MenuController implements Initializable {
    @FXML private Label nombre;
    @FXML private AnchorPane menuPane;

    protected MainBanco banco;

    private Cliente cliente;

    @FXML private void handleCerrar(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Logging.fxml"));

        try {
            Parent root = loader.load();

            LoggingController controller = loader.getController();

            Scene scene = new Scene(root);
            Stage stage = new Stage();

            controller.setBanco(banco);

            stage.initModality(Modality.WINDOW_MODAL);
            stage.setTitle("Bank");
            stage.getIcons().add(new Image(getClass().getResourceAsStream("img/icon")));
            stage.setResizable(false);
            stage.setScene(scene);
            close(event);
            stage.show();

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: 003\n"+e.getCause());
        }

    }
    @FXML private void handleCtaView(ActionEvent event) {
        ArrayList<Cuenta> cuentas = banco.getCtas(cliente);
        if(!cuentas.isEmpty()){
            System.out.println("Tiene Cuentas de Ahorro");
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CtaViewNoCta.fxml"));
                Node node = loader.load();
                CtaViewNoCtaController controller = loader.getController();
                controller.setBanco(banco);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 004\n"+e.getCause());
            }
        }
    }

    @FXML private void handleCtaT(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TransferirCta.fxml"));
            Node node = loader.load();
            TransferirCtaController controller = loader.getController();
            controller.setBanco(banco);
            menuPane.getChildren().setAll(node);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: 005\n"+e.getCause());
        } 
    }

    protected void setAddCta(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AgregarCta.fxml"));
            Node node = loader.load();
            menuPane.getChildren().setAll(node);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: 006\n"+e.getCause());
        } 
    }
    public void setBanco(MainBanco banco){
        this.banco = banco;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
        nombre.setText(this.cliente.getNombre());
    }

    //closer
    @FXML private void close(ActionEvent event) {
        Node source = (Node) event.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
    
}
