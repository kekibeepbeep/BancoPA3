import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;

public class CtcViewNoCtcController implements Initializable {
    private MainBanco banco;

    private Cliente cliente;

    @FXML private void handleCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        source.getParent().setVisible(false);
    }
    @FXML private void handleOk(ActionEvent event){
        banco.agregarCtaCte(cliente.getId());
        JOptionPane.showMessageDialog(null, "Para Validar Su Identidad\nCierre y abra sesion por favor\nDe lo contrario no se mostraran los cambios");
        handleCancel(event);
    }

    public void setBanco(MainBanco banco){
        this.banco = banco;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
