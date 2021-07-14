import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

class MainBanco {
  SimuladorBanco sb = new SimuladorBanco();
  public static void main(String[] args) {
  
    //testlasesPAI();4
    SimuladorBanco sb = new SimuladorBanco();
    System.out.println("Problema del Simulador Bancario, version con Polimorfismo, clases Abstractas e Interfases");

    //System.out.println("la multiplicacion de 6000 y 0.015 es " + 6000*0.015);

    Scanner in = new Scanner(System.in);

    int opcion;
    boolean seguir = true;

    String nombre; // para guardar el nombre de un cliente
    int cedula;    // para guardar la cedula de un cliente
    int cedula2;    // para guardar la cedula de un cliente
    int id;        // para guardar el id de una cta
    int monto;     // para guardar el monto del giro o deposito
    int tipo;      // para guardar el tipo de cta segun caso
    Class clase;   // para manejar las clases en RTTI (run-time type identification) 
    Cliente clieAux; // guarda un cliente en especifico
    Cliente clieAux2; // guarda un cliente en especifico
    double interesAhorro = 0.006; // interes cta ahorro
    double interesCDT; // interes negociable para CDT

    try {
      sb.rescatar();//automatica mente se carga la base de datos a la memoria
    } catch (IOException e1) {
      e1.printStackTrace();
    }
    try {
      sb.rescatar();
    } catch (IOException e1) {
      //handle error
    }
    while (seguir) {
      System.out.print("\n? (0 para ayuda) ");
      opcion = in.nextInt();
      switch (opcion) {

        case 0:
          System.out.print(
            "-1 cierra el programa.\n" +
            "0 esta ayuda.\n" +
            "1 listar a los clientes del banco.\n" +
            "2 simula un cambio de mes.\n" +
            "3 agrega un cliente.\n" +
            "4 mostrar al cliente segun la cedula.\n" +
            "5 verificar si la cedula es de un cliente.\n" +
            "6 agregar una ctacte a un cliente por cedula.\n" +
            "7 agregar una ctaahorro a un cliente por cedula.\n" +
            "8 agregar un cdt a un cliente por cedula.\n" +
            "9 borrar una ctacte a un cliente por cedula.\n" +
            "10 borrar una ctaahorro a un cliente por cedula.\n" +
            "11 cerrar un cdt a un cliente por cedula y cta cte destino.\n" +
            "12 depositar en una cuenta segun cedula, monto y idCta.\n" +
            "13 girar en una cuenta segun cedula, monto y idCta.\n" +
            "14 Borrar un cliente por cedula\n"+
            "15 Agregar destinatario a Agenda\n"+
            "16 Borrar destinatario de la Agenda\n"+
            "17 Trasferir entre destinatario\n"+
            "18 Serializar Programa\n"+
            "19 Rescatar datos serializados\n"
          );
          break;
        
        case 1: // listar clientes
          System.out.print(sb);
          sb.seriar();
          break;

        case 2: // simular el cambio de mes
          sb.simulaMes();
          sb.seriar();
          break;

        case 3: // agregar clientes
          System.out.print("Indica el nombre: ");
          nombre = in.next();
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          if (sb.agregarCliente(nombre, cedula)) {
            System.out.println("Cliente " + nombre + " agregado.");
          }
          else {
            System.out.println("No se pudo agregar al cliente " + nombre + ".");
          }
          sb.seriar();
          break;
        
        case 4: // mostrar cliente segun la cedula
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          clieAux = sb.obtenerCliente(cedula);
          if (clieAux != null) {
            System.out.println(clieAux);
            System.out.println("Ctas Ctes: " + clieAux.listarCuentas(CtaCte.class));
            System.out.println("Ctas Ahos: " + clieAux.listarCuentas(CtaAhorro.class));
            System.out.println("CDT: " + clieAux.listarCuentas(CDT.class));
          }
          else System.out.println("No existe el cliente " + cedula + ".");
          sb.seriar();
          break;

        case 5: // verificar si la cedula pertenece a algun cliente
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("La cedula " + cedula + " ");
          if (sb.esCliente(cedula)) {System.out.print("pertenece a un ");}
          else {System.out.print("no pertenece a ningun "); }
          System.out.println("cliente.");
          sb.seriar();
          break;

        case 6: // agregar una ctaCte a un cliente por cedula
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          if (sb.esCliente(cedula)) {
            if (sb.agregarCtaCte(cedula)) {
              System.out.println("Se agrego la cuenta corriente.");
            }
            else {System.out.println("No se pudo agregar la cuenta corriente.");}
          }
          else { System.out.println("no existe cliente " + cedula + "."); }
          sb.seriar();
          break;

        case 7: // agregar una ctaAhorro a un cliente por cedula
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          if (sb.esCliente(cedula)) {
            if (sb.agregarCtaAhorro(cedula, interesAhorro)) {
              System.out.println("Se agrego la cuenta de ahorro.");
            }
            else { System.out.println("No se pudo agregar la cuenta de ahorro."); }
          }
          else { System.out.println("no existe cliente " + cedula + "."); }
          sb.seriar();
          break;

        case 8: // agregar un cdt a un cliente por cedula
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("Indica el monto del CDT: ");
          monto = in.nextInt(); // pregunta: como asegurarse de que el monto sea positivo?
          System.out.print("Indica la tasa de interes del CDT en tanto por 1: ");
          interesCDT = in.nextDouble(); // pregunta: como asegurarse de que el interes sea positivo?
          if (sb.esCliente(cedula)) {
            if (sb.agregarCDT(cedula, monto, interesCDT)) {
              System.out.println("Se agrego el CDT.");
            }
            else { System.out.println("No se pudo agregar el CDT."); }
          }
          else { System.out.println("no existe cliente " + cedula); }
          sb.seriar();
          break;

        case 9: // borrar una ctacte a un cliente por cedula
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("Indica el id de la cta cte: ");
          id = in.nextInt();
          if (sb.esCliente(cedula) && sb.borrarCuenta(cedula, id, CtaCte.class)) {
            System.out.println("cuenta " + id + " de cliente " + cedula + " borrada");
          }
          else { System.out.println("no existe cliente " + cedula + " o cuenta " + id); }
          sb.seriar();
          break;

        case 10: // borrar una ctaahorro a un cliente por cedula
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("Indica el id de la cta ahorro ");
          id = in.nextInt();
          if (sb.esCliente(cedula) && sb.borrarCuenta(cedula, id, CtaAhorro.class)) {
            System.out.println("cuenta " + id + " de cliente " + cedula + " borrada");
          }
          else { System.out.println("no existe cliente " + cedula + " o cuenta " + id); }
          sb.seriar();
          break;

        case 11: // cerrar un cdt a un cliente por cedula y cta cte destino
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("Indica el id del CDT: ");
          id = in.nextInt();
          System.out.print("Indica el id de la cta cte de destino: ");
          int idCtaCte = in.nextInt();
          if (sb.esCliente(cedula) && sb.cerrarCDT(cedula, id, idCtaCte)) {
            System.out.println("CDT " + id + " de cliente " + cedula + " cerrado y transferido a cta cte " + idCtaCte + ".");
          }
          else { System.out.println("no existe cliente " + cedula + ", CDT " + id + " o cuenta cte " + idCtaCte + "."); }
          sb.seriar();
          break;

        case 12: // depositar en una cuenta segun cedula, monto y idCta
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("Indica el monto: ");
          monto = in.nextInt();
          System.out.print("Indica el id de la cta de destino: ");
          id = in.nextInt();
          System.out.print("Indica el tipo de cuenta (1 cte, 2 ahorros): ");
          tipo = in.nextInt();
          switch(tipo) {
            case 1: clase = CtaCte.class; break;
            case 2: clase = CtaAhorro.class; break;
            default: clase = null; break;
          }
          if (sb.esCliente(cedula) && sb.deposito(cedula, monto, id, clase)) {
            System.out.println("deposito en cuenta " + id + " tipo " + clase.getSimpleName() + " de cliente " + cedula + " hecho.");
          }
          else { System.out.println("no existe cliente " + cedula + ", cuenta " + id + " o tipo incorrecto."); }
          sb.seriar();
          break;
        
        case 13: //  girar en una cuenta segun cedula, monto y idCta
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          System.out.print("Indica el monto: ");
          monto = in.nextInt();
          System.out.print("Indica el id de la cta de destino: ");
          id = in.nextInt();
          System.out.print("Indica el tipo de cuenta (1 cte, 2 ahorros): ");
          tipo = in.nextInt();
          clase = (tipo==1)? CtaCte.class : ((tipo==2)?CtaAhorro.class:null);
          if (sb.esCliente(cedula) && sb.giro(cedula, monto, id, clase)) {
            System.out.println("giro en cuenta " + id + " tipo " + clase.getSimpleName() + " de cliente " + cedula + " hecho.");
          }
          else { System.out.println("no existe cliente " + cedula + ", cuenta " + id + " o tipo incorrecto."); }
          sb.seriar();
          break;
        case 14: //borrar un cliente por cédula.
          System.out.print("Indica la cedula: ");
          cedula = in.nextInt();
          clieAux = sb.obtenerCliente(cedula);
          if(sb.existeCliente(clieAux) && clieAux.saldoTotal()==0){
            sb.seriar();
            Serializador s = new Serializador();
            sb.clientes.remove(clieAux);
            s.borrar(Integer.toString(clieAux.getId()));
            System.out.println("se eliminó el cliente de cedula "+ clieAux.getId());
          }else{
            System.err.println("El saldo del cliente no es 0 por lo que no se  puede eliminar");
          }
          sb.seriar();
          break;
        case 15: //agrega un destinarario a la agenda por cedula
          //pregunta: esto invalida las cuentas anteriormente serializadas?
          System.out.print("Indica la cedula del dueño de la agenda: ");
          clieAux= sb.obtenerCliente(in.nextInt()); //dueño de la agenda destino
          System.out.print("Indica la cedula del destinatario a agregar: ");
          clieAux2 = sb.obtenerCliente(in.nextInt()); //cliente a agregar a la agenda destino

          if(sb.existeCliente(clieAux) && sb.existeCliente(clieAux2)){ //verifica que ambos cliente existan
            /*Agrega clieAux2 a agendaDestinatarios de clieAux que se encuentra en array
            clientes dentro del simulador bancario */
            sb.clientes.get(sb.getPosCliente(clieAux)).agregaDestinatario(clieAux2);
          }
          sb.seriar();
          break;
        case 16: //borra un destinatario de la agenda por cédula.
          System.out.print("Indica la cedula del dueño de la agenda: ");
          clieAux= sb.obtenerCliente(in.nextInt()); //dueño de la agenda destino
          System.out.print("Indica la cedula del destinatario a eliminar: ");
          clieAux2 = sb.obtenerCliente(in.nextInt()); //cliente a agregar a la agenda destino
          
          if(sb.existeCliente(clieAux) && sb.existeCliente(clieAux2)){
            sb.clientes.get(sb.getPosCliente(clieAux)).borrarDestinatario(clieAux2);
          }
          sb.seriar();
          break;
        case 17:
          //depositos entre agenda celuda plus id cuentas
          System.out.print("Indica la cedula del dueño de la agenda: ");
          clieAux= sb.obtenerCliente(in.nextInt()); //dueño de la agenda destino
          System.out.print("Indica el id del cliente destino: ");
          id = in.nextInt(); //cliente a agregar a la agenda destino
          if(sb.depositar(clieAux.getId(), id))
            System.out.println("Deposito exitoso");
          else
            System.err.println("Deposito fallido verificar datos ingresados");
          break;
        case 18:
          sb.seriar(); 
          System.out.println("\nSerializado completo\n");
          break;
        case 19:
          try {
            sb.rescatar();
            System.out.println("\nSe han cargado los datos con exito\n");
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        default: //"otro valor para terminar"
          seguir = false; sb.seriar(); break;
      }
    }    
  
  }

  public void agregarCliente(String nombre, int cedula){
    
    if (sb.agregarCliente(nombre, cedula)) {
      System.out.println("Cliente " + nombre + " agregado.");
      }
    else {
      System.out.println("No se pudo agregar al cliente " + nombre + ".");
    }
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
    Cliente clieAux = sb.obtenerCliente(idAdd); //cliente a agregar a la agenda destino

    if(sb.existeCliente(cliente) && sb.existeCliente(clieAux)){ //verifica que ambos cliente existan
      /*Agrega clieAux2 a agendaDestinatarios de clieAux que se encuentra en array
      clientes dentro del simulador bancario */
      sb.clientes.get(sb.getPosCliente(cliente)).agregaDestinatario(clieAux);
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