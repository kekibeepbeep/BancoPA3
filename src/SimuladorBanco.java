import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class SimuladorBanco {
  ArrayList<Cliente> clientes;
  int mesActual;
  private Cliente clienteLoggeado;

  public SimuladorBanco() {
    clientes = new ArrayList<Cliente>();
    mesActual = 0;
    try {
      rescatar();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String toString() {
    String answer = "mesActual " + mesActual + ".";
    answer += " Clientes: " + clientes.size() + "\n";
    
    Iterator<Cliente> it = clientes.iterator();
    while (it.hasNext()){
      Cliente clie = it.next();
      answer += clie + "\n";
    }

    return answer;
  }

  public void simulaMes() {
    // para cada cliente, calcula y agrega intereses en ctas de ahorro y cdts
    Iterator<Cliente> it = clientes.iterator();
    while (it.hasNext()) {
      //Cliente clie = it.next();
      //clie.simulaMes();
      
      it.next().simulaMes(); // como solo se necesita una acción con el cliente, la hago directamente
    }
    mesActual++;
  }

  public boolean agregarCliente(String nombre, int cedula) {
    // pregunta: que hacer para no repetir dos clientes con la misma cedula
    return clientes.add(new Cliente(nombre, cedula));
  }

  public Cliente obtenerCliente(int cedula) {
    // revisamos si alguno de los clientes tiene la cedula
    try {
      rescatar();
    } catch (IOException e) {
      e.printStackTrace();
    }
    Iterator<Cliente> it = clientes.iterator();
    while (it.hasNext()) {
      Cliente clie = it.next();
      if (clie.getId() == cedula) {
        return clie;
      }
    }
    return null;
  }

  public boolean esCliente(int cedula) { // aprovechando el metodo obtenerCliente
    return obtenerCliente(cedula) != null;
  }
  public boolean verificaNombre(int cedula, String nombre) { // aprovechando el metodo obtenerCliente
    Cliente cliente = obtenerCliente(cedula);
    String nombreCliente = cliente.getNombre();
    if (nombre.equals(nombreCliente)){return true;}
    return false;

  }

  public boolean agregarCtaCte(int cedula) {
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) {
      return clie.agregarCtaCte();
    }
    else {
      return false;
    }
  }

  public boolean agregarCtaAhorro(int cedula, double interes) {
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) {
      return clie.agregarCtaAhorro(interes);
    }
    else {
      return false;
    }
  }

  public boolean agregarCDT(int cedula, int monto, double interes) {
    if (monto < 0) {
      return false;
    }
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) {
      return clie.agregarCDT(monto, interes);
    }
    else {
      return false;
    }
  }

  public boolean borrarCuenta(int cedula, int id, Class clase) {
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) { // equiv a chequear (clie != null)
      return clie.borrarCuenta(id, clase);
    }
    else {return false;}
  }

  public boolean cerrarCDT(int cedula, int id, int idCtaCte) {
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) {
      return clie.cerrarCDT(id, idCtaCte);
    }
    else {return false;}
  }

  public boolean deposito(int cedula, int monto, int id, Class clase) {
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) {
      return clie.deposito(monto, id, clase);
    }
    else {return false;}
  }

  public boolean giro(int cedula, int monto, int id, Class clase) {
    Cliente clie = obtenerCliente(cedula);
    if (clie != null) {
      return clie.giro(monto, id, clase);
    }
    else {return false;}
  }
  
  public void seriar(){
    Serializador s = new Serializador();
    for (Cliente cliente : clientes) {
      try {
        s.ingresarABD(cliente);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
 
  public void rescatar() throws IOException{
    Serializador s = new Serializador();
    clientes = s.cargarDataBase();
  }
  public void listarClientes() { //lista clientes del banco hecha para fines de testeo.
    for (int i=0;i<clientes.size();i++) {
      System.out.println(clientes.get(i).toString());
    }
  }
  public boolean depositar(int idOrigen, int idDestino, int nmroCtadDestino, int nmroCtaOrigen, int dep) {
    Cliente cOrigen = obtenerCliente(idOrigen);
    Cliente cDestino = obtenerCliente(idDestino);
    if(cOrigen == null)return false;
    if(cDestino == null)return false;
    for (Cliente cliente : cOrigen.getAgenda()) {
        if(cliente.getId() == idDestino){
          for (Cuenta cuentaD : cliente.cuentas) {
            for(Cuenta cuentaO : obtenerCliente(idOrigen).cuentas){
              if(cuentaD.getId()==nmroCtadDestino && cuentaO.getId()==nmroCtaOrigen){
                //System.out.print("Cantidad a depositar: ");
                if(!cuentaD.depositar(dep))return false;
                if(!cuentaO.girar(dep))return false;
                return true;
              }
            }
          }
        } 
    }
    System.out.println("sali del ciclo sin exito");
    return false;
  }
  public boolean existeCliente(Cliente clienteTest){ //verifica si existe un cliente
    for (Cliente cliente : clientes) {
      System.out.println(cliente);
      if (cliente.getId() == clienteTest.getId()){
        return true;
      }   
    }
    return false;
  }

  public int getPosCliente(Cliente cliente){ //retorna posicion de un cliente en el arraylist clientes
    for (int i=0;i<clientes.size();i++) {
      if (cliente == clientes.get(i)){
        return i;
      } 
    }
    return -1;  //retorna -1 si no lo encuentra
  }
  public boolean setClienteLoggeado(int id){
    for (Cliente cliente : clientes) {
      if(cliente.getId() ==  id){
        this.clienteLoggeado = cliente;
        return true;
      }
    }
    this.clienteLoggeado = new Cliente("Cliente Fake", 000);
    return false;
  } 
  public Cliente getClienteLoggeado() {
    return clienteLoggeado;
    
  }



}

