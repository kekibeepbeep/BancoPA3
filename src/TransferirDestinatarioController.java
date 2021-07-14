import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class TransferirDestinatarioController implements Initializable {
    private MainBanco banco;
    private Cliente cliente;

    @FXML private TextField monto;
    @FXML private ComboBox<Cliente> destinatarios;
    @FXML private ComboBox<Cuenta> cuentas;
    @FXML private ComboBox<Cuenta> cuentasLocales;

    @FXML private void handleTransferir(ActionEvent event) {
        //manipular el boton para hacer efectiva la transferencia
        banco.depositoTerceros(cliente, destinatarios.getValue(),cuentasLocales.getValue(), cuentas.getValue(), Integer.parseInt(monto.getText().trim()));
        JOptionPane.showMessageDialog(null, "Para Validar Su Identidad\nCierre y abra sesion por favor\nDe lo contrario no se mostraran los cambios");
    }
    @FXML private void setCuentasDestino(ActionEvent event) {
        cuentas.getItems().removeAll(cuentas.getItems());
        cuentas.getItems().addAll(destinatarios.getValue().cuentas);
    }

    public void setDestinatarios(ArrayList<Cliente> destinatarios){
        this.destinatarios.getItems().removeAll(this.destinatarios.getItems());
        this.destinatarios.getItems().addAll(destinatarios);
    }
    public void setCuentasLocales(ArrayList<Cuenta> cuentas){
        this.cuentasLocales.getItems().removeAll(cuentasLocales.getItems());
        this.cuentasLocales.getItems().addAll(cuentas);
        
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
