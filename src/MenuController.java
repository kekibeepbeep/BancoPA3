import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


public class MenuController implements Initializable {
    private MainBanco banco;


    @FXML private void handleCerrar(ActionEvent event) {
        //maneja cerrar
    }
    
    public void setBanco(MainBanco banco){
        this.banco = banco;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
