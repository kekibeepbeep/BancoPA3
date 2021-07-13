import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CtaViewController implements Initializable {
    private MainBanco banco;
    private Cliente cliente;

    @FXML private ComboBox elegir;
    @FXML private TextField interesN;
    @FXML private Label id; 
    @FXML private Label saldo;
    @FXML private Label interes;

    @FXML private void handleMostrar(ActionEvent event){
        //muestra la cuenta en el text field
    }
    @FXML private void handleAgregar(ActionEvent event){
        //agrega una cuenta de ahorros a la lista de cuentas
    }
    @FXML private void handleEliminar(ActionEvent event){
        //elimina la cuenta que se esta mostrando
    }

    public void setBanco(MainBanco banco){
        this.banco = banco;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
}
