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

    private MainBanco banco;

    private Cliente cliente;

    @FXML protected void handleCerrar(ActionEvent event) {
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
    @FXML private void handleCtaView(ActionEvent event) throws IOException {
        ArrayList<Cuenta> cuentas = banco.getCtas(cliente);
        if(!cuentas.isEmpty()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CtaView.fxml"));
                Node node = loader.load();
                CtaViewController controller = loader.getController();
                controller.setBanco(banco);
                controller.setCliente(cliente);
                controller.setBox(cuentas);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 006\n"+e.getCause());
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CtaViewNoCta.fxml"));
                Node node = loader.load();
                CtaViewNoCtaController controller = loader.getController();
                controller.setBanco(banco);
                controller.setCliente(cliente);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 004\n"+e.getCause());
            }
        }
    }
    @FXML private void handleCtcView(ActionEvent event) throws IOException{
        ArrayList<Cuenta> cuentas = banco.getCtcs(cliente);
        if(!cuentas.isEmpty()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CtcView.fxml"));
                Node node = loader.load();
                CtcViewController controller = loader.getController();
                controller.setBanco(banco);
                controller.setCliente(cliente);
                controller.setBox(cuentas);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 007\n"+e.getCause());
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CtcViewNoCtc.fxml"));
                Node node = loader.load();
                CtcViewNoCtcController controller = loader.getController();
                controller.setBanco(banco);
                controller.setCliente(cliente);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 008\n"+e.getCause());
            }
        }
    }
    @FXML private void handleCdtView(ActionEvent event) throws IOException{
        ArrayList<Cuenta> cuentas = banco.getCdts(cliente);
        if(!cuentas.isEmpty()){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CdtView.fxml"));
                Node node = loader.load();
                CdtViewController controller = loader.getController();
                controller.setBanco(banco);
                controller.setCliente(cliente);
                controller.setBox(cuentas);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 007\n"+e.getCause());
            }
        }else{
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("CdtViewNoCdt.fxml"));
                Node node = loader.load();
                CdtViewNoCdtController controller = loader.getController();
                controller.setBanco(banco);
                controller.setCliente(cliente);
                menuPane.getChildren().setAll(node);
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "ERROR: 008\n"+e.getCause());
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
    @FXML private void handleVerDestinatario(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("VerDestinatario.fxml"));
            Node node = loader.load();
            VerDestinatarioController controller = loader.getController();
            controller.setBanco(banco);
            controller.setCliente(cliente);
            controller.setTableContent();
            menuPane.getChildren().setAll(node);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "ERROR: 010\n"+e.getCause());
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
