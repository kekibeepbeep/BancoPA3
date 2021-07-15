import java.io.IOException;
import java.util.ArrayList;

class MainBanco {
  SimuladorBanco sb = new SimuladorBanco();
  public void agregarCliente(String nombre, int cedula){
    sb.agregarCliente(nombre, cedula);
    sb.seriar();
  }
  public void simulaMes(){
    sb.simulaMes();
    sb.seriar();
  }
  public boolean verificaCedulaCliente(int cedula){
    if (sb.esCliente(cedula)) {return true;}
    return false;

  }
  public void agregarCliente(Cliente cliente){
      sb.clientes.add(cliente);
      sb.seriar();
  }
  public boolean verificaClienteLogin(int cedula, String nombre){
    if (sb.esCliente(cedula)) {
      if (sb.verificaNombre(cedula, nombre)){
        sb.setClienteLoggeado(cedula);
        return true;
      }
      
    }
    return false;

  }

  public boolean agregarCtaCte(int cedula){
    
    if (sb.esCliente(cedula)) {
      if (sb.agregarCtaCte(cedula)) {
        sb.seriar();
        return true;
      }
    }
    return false;
  }
  public boolean agregarCtaAhorro(int cedula, double interesAhorro){
    if (sb.esCliente(cedula)) {
      if (sb.agregarCtaAhorro(cedula, interesAhorro)) {
        sb.seriar();
        return true;
      }
    }
    return false;

  }
  public boolean agregarCDT(int cedula, int monto, double interesCDT){
    if (sb.esCliente(cedula)) {
      if (sb.agregarCDT(cedula, monto, interesCDT)) {
        sb.seriar();
        return true;
      }
    }
    return false;
    
  }

  public void agregarAgenda(Cliente cliente, int idAdd){
    for (Cliente aux : sb.clientes) {
      if(aux.getId() == idAdd){
        cliente.agregaDestinatario(aux);
      }
    }
    sb.seriar();
  }
  public boolean borrarCtaCte(int cedula, int id){

    if (sb.esCliente(cedula) && sb.borrarCuenta(cedula, id, CtaCte.class)) {
      sb.seriar();
      return true;
    }
    return false;
  }
  public boolean borrarCtaDeAhorro(int cedula, int id){
    if (sb.esCliente(cedula) && sb.borrarCuenta(cedula, id, CtaAhorro.class)) {
      sb.seriar();
      return true;
    }
    return false;
    
  }
  public boolean cerrarCDT(int cedula,int idCDT, int idCtaCte){
    if (sb.esCliente(cedula) && sb.cerrarCDT(cedula, idCDT, idCtaCte)) {
      sb.seriar();
      return true;
    }
    return false;
  }
  public boolean depositarMain(int cedula, int monto, int idDestino, int tipo){
    Class<? extends Cuenta> clase;
    switch(tipo) {
      case 1: clase = CtaCte.class; break;
      case 2: clase = CtaAhorro.class; break;
      default: clase = null; break;
    }
    if (sb.esCliente(cedula) && sb.deposito(cedula, monto, idDestino, clase)) {
      sb.seriar();
      return true;
    }
    return false;
    
  }
  public void depositoTerceros(Cliente cliente1, Cliente cliente2, Cuenta owner, Cuenta destiny, int monto) {
    System.out.println(sb.depositar(cliente1.getId(), cliente2.getId(), owner.getId(), destiny.getId(), monto));
    sb.seriar();
  }
  public boolean girarMain(int cedula, int monto, int id, int tipo){
    Class<? extends Cuenta> clase = (tipo==1)? CtaCte.class : ((tipo==2)? CtaAhorro.class:null);
    if (sb.esCliente(cedula) && sb.giro(cedula, monto, id, clase)) {
      sb.seriar();
      return true;
    }
    return false;
    
  }
  public boolean borrarClienteMain(int cedula){
    Cliente clieAux = sb.obtenerCliente(cedula);
    if(sb.existeCliente(clieAux) && clieAux.saldoTotal()==0){
      Serializador s = new Serializador();
      sb.clientes.remove(clieAux);
      s.borrar(Integer.toString(clieAux.getId()));
      sb.seriar();
      return true;
    }
    return false;
  }
  public Cliente borrarDestinatario(Cliente cliente, int removeId) {
    for (Cliente aux : sb.clientes) {
      if(aux.getId() == removeId){
        cliente.borrarDestinatario(aux);
      }
    }
    System.out.println(cliente.agendaDestinatarios);
    sb.seriar();
    return cliente;
  }
  public ArrayList<Cuenta> getCtas(Cliente cliente) throws IOException{
    sb.rescatar();
    ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    for (Cuenta cuenta : cliente.cuentas){
      if(cuenta.getClass().equals(CtaAhorro.class)){
        cuentas.add(cuenta);
      }
    }
    return cuentas;
  }

  public ArrayList<Cuenta> getCtcs(Cliente cliente) throws IOException {
    sb.rescatar();
    ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    for (Cuenta cuenta : cliente.cuentas){
      if(cuenta.getClass().equals(CtaCte.class)){
        cuentas.add(cuenta);
      }
    }
    return cuentas;
  }

  public ArrayList<Cuenta> getCdts(Cliente cliente) throws IOException {
    sb.rescatar();
    ArrayList<Cuenta> cuentas = new ArrayList<Cuenta>();
    for (Cuenta cuenta : cliente.cuentas){
      if(cuenta.getClass().equals(CDT.class)){
        cuentas.add(cuenta);
      }
    }
    return cuentas;
  }
  public Cliente getClienteMain() throws IOException {
    sb.rescatar();
    return sb.getClienteLoggeado();
  }
}