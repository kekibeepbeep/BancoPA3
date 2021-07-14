import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class VerDestinatarioController implements Initializable {

    @FXML private TextField idDestino;
    @FXML private TableView<Cliente> tableDestinos;
    @FXML private TableColumn<Cliente, String> nombres;
    @FXML private TableColumn<Cliente, String> id;

    private MainBanco banco;

    private Cliente cliente;

    
    @FXML private void handleBorrar(ActionEvent event){
        this.cliente = banco.borrarDestinatario(cliente, Integer.parseInt(idDestino.getText().trim()));
        setTableContent();
    }
    @FXML private void handleAgregar(ActionEvent event){
        banco.agregarAgenda(cliente, Integer.parseInt(idDestino.getText().trim()));
        setTableContent();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {}

    public void setBanco(MainBanco banco) {
        this.banco = banco;
    }
    public void setTableContent(){
        System.out.println(cliente.getAgenda());
        banco.agregarCliente(cliente);
        ObservableList<Cliente> contactos = FXCollections.observableArrayList(cliente.getAgenda());
        nombres.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        id.setCellValueFactory(new PropertyValueFactory<>("id"));

        tableDestinos.setItems(contactos);
    }
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }    
    
}
