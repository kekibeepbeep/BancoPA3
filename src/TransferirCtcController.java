import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import org.junit.FixMethodOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferirCtcController implements Initializable {
    private MainBanco banco;
    private Cliente cliente;

    @FXML private ComboBox<Cuenta> cuentas;
    @FXML private TextField monto;

    @FXML private void handleTransferir(ActionEvent event) {
        Cuenta cuenta = cuentas.getValue();
        banco.depositarMain(cliente.getId(), Integer.parseInt(monto.getText().trim()), cuenta.getId(), 1);       
        JOptionPane.showMessageDialog(null, "Para Validar Su Identidad\nCierre y abra sesion por favor\nDe lo contrario no se mostraran los cambios");
    }
    @FXML private void handleGirar(ActionEvent event) {
        Cuenta cuenta = cuentas.getValue();
        banco.girarMain(cliente.getId(), Integer.parseInt(monto.getText().trim()), cuenta.getId(), 1);       
        JOptionPane.showMessageDialog(null, "Para Validar Su Identidad\nCierre y abra sesion por favor\nDe lo contrario no se mostraran los cambios");
    }
    public void setBanco(MainBanco banco) {
        this.banco = banco;
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    public void setCuentas(ArrayList<Cuenta> cuentas){
        this.cuentas.getItems().removeAll(this.cuentas.getItems());
        this.cuentas.getItems().addAll(cuentas);
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {}    
    
}
