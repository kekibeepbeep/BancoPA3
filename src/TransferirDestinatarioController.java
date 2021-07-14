import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferirDestinatarioController implements Initializable {
    private MainBanco banco;
    private Cliente cliente;

    @FXML private TextField monto;
    @FXML private ComboBox destinatarios;

    @FXML private void handleTransferir(ActionEvent event) {
        //manipular el boton para hacer efectiva la transferencia
    }

    public void setBanco(MainBanco banco) {
        this.banco = banco;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
