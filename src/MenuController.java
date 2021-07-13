import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;


public class MenuController implements Initializable {
    @FXML private Label nombre;

    private MainBanco banco;

    private Cliente cliente;

    @FXML private void handleCerrar(ActionEvent event) {
        //maneja cerrar
    }
    
    public void setBanco(MainBanco banco){
        this.banco = banco;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        nombre.setText(cliente.getNombre());
    }    
    
}
