import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

public class AgregarClienteController implements Initializable {
    @FXML private TextField nombre;
    @FXML private TextField id;

    private MainBanco banco;

    @FXML private void handleAgregar(ActionEvent event) {
        banco.agregarCliente(nombre.getText().trim(), Integer.parseInt(id.getText().trim()));
    }
    
    public void setBanco(MainBanco banco){
        this.banco = banco;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
