import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CdtViewController implements Initializable {

    private MainBanco banco;
    private Cliente cliente;

    @FXML private ComboBox<Cuenta> elegir;
    @FXML private TextField interesN;
    @FXML private TextField saldoN;
    @FXML private Label id; 
    @FXML private Label saldo;
    @FXML private Label interes;

    @FXML private void handleMostrar(ActionEvent event){
        //muestra la cuenta en el text field
        Cuenta cuenta = elegir.getValue();
        id.setText(Integer.toString(cuenta.getId()));
        saldo.setText("$"+Integer.toString(cuenta.saldo));
        interes.setText(Double.toString(cuenta.interes)+"%");
    }
    @FXML private void handleAgregar(ActionEvent event){
        //agrega una cuenta de ahorros a la lista de cuentas
        banco.agregarCDT(cliente.getId(), Integer.parseInt(saldoN.getText().trim()), Float.parseFloat(interesN.getText().trim()));
        JOptionPane.showMessageDialog(null, "Para Validar Su Identidad\nCierre y abra sesion por favor\nDe lo contrario no se mostraran los cambios");

    }
    @FXML private void handleEliminar(ActionEvent event){
        //elimina la cuenta que se esta mostrando
        banco.borrarCtaDeAhorro(cliente.getId(), elegir.getValue().getId());
        JOptionPane.showMessageDialog(null, "Para Validar Su Identidad\nCierre y abra sesion por favor\nDe lo contrario no se mostraran los cambios");
    }

    public void setBanco(MainBanco banco){
        this.banco = banco;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void setBox(ArrayList<Cuenta> cuentas){
        elegir.getItems().removeAll(elegir.getItems());
        elegir.getItems().addAll(cuentas);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}
    
}
