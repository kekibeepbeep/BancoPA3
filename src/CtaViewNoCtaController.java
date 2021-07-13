import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;


public class CtaViewNoCtaController extends MenuController {


    @FXML private void handleCancel(ActionEvent event) {
        Node source = (Node) event.getSource();
        source.getParent().setVisible(false);
    }
    @FXML private void handleOk(ActionEvent event){
        setAddCta();
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
