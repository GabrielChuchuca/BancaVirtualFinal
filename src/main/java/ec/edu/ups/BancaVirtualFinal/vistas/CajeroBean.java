package ec.edu.ups.BancaVirtualFinal.vistas;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;
import org.primefaces.model.charts.pie.PieChartModel;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;
import ec.edu.ups.BancaVirtualFinal.on.GestionUsuarioLocal;

/**
 * @author Gabriel Leonardo Chu
 *
 */
public class CajeroBean {
	@Inject
	private GestionUsuarioLocal clienteON;

	private Double monto;

	private Cliente cliente;

	private List<Transaccion> listaTra;

	private boolean editable;
	
	private boolean editable2;

	private String tipoTransaccion;
	
	private String cedulaAux;
	
	private int codigoAux;
	
	private int codigoAux2;
	
	private int codigoAux3;
	
	private Transaccion transaccionAux;
	
	private PieChartModel pieModel;
	
	private boolean grafica;

	@PostConstruct
	public void init() {
		
		transaccionAux = new Transaccion();
		cliente = new Cliente();
		grafica = false;
	}

	public GestionUsuarioLocal getClienteON() {
		return clienteON;
	}

	public void setClienteON(GestionUsuarioLocal clienteON) {
		this.clienteON = clienteON;
	}

	/**
	 * Metodo para obtener un Monto
	 * 
	 * @return Me devuelve un valor de una transaccion
	 */
	public Double getMonto() {
		return monto;
	}

	/**
	 * Metodo para asignar un valor al monto
	 * 
	 * @param monto El parametro moto me permite asignar un valor al monto
	 */
	public void setMonto(Double monto) {
		this.monto = monto;
	}

	/**
	 * Metodo para obtener un Cliente
	 * 
	 * @return Un cliente para se utilizado en la paguina
	 */
	public Cliente getCliente() {
		return cliente;
	}

	/**
	 * Metodo para asignar un cliente
	 * 
	 * @param cliente el parametro cliente me permite asignar un valor a mi variable
	 *                cliente
	 */
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	/**
	 * Metodo para obtener una lista de tipo transacciones
	 * 
	 * @return Una lista de Transacciones
	 */
	public List<Transaccion> getListaTra() {
		return listaTra;
	}

	/**
	 * Metodo para asignar valores a la lista
	 * 
	 * @param listaTra el parametro listaTra me permite asignar una lista de
	 *                 transacciones a mi variable local de Tipo Lista de
	 *                 Transacciones
	 */
	public void setListaTra(List<Transaccion> listaTra) {
		this.listaTra = listaTra;
	}

	/**
	 * Metodo para asignar un valor booleano
	 * 
	 * @return Si es TRUE o FALSE
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Metodo para asignar un valor booleano
	 * 
	 * @param editable El parametro editable me permite cambiar a TRUE o FALSE el
	 *                 booleano
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Metdo para obtener el tipo de transaccion que se va a realizar
	 * 
	 * @return El valor del tipo de transaccion si es Retiro o Deposito
	 */
	public String getTipoTransaccion() {
		return tipoTransaccion;
	}

	/**
	 * Asignar el Tipo de Transaccion
	 * 
	 * @param tipoTransaccion El parametro tipoTransaccion me permite ver que tipo
	 *                        de Transaccion se esta realizando.
	 */
	public void setTipoTransaccion(String tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	

	
	/**
	 * Metodo para obtener la cedulaAux
	 * @return La cedula de la persona que va a realizar el pago de la transaccion
	 */
	public String getCedulaAux() {
		return cedulaAux;
	}
	
	public void setCedulaAux(String cedulaAux) {
		this.cedulaAux = cedulaAux;
	}
	
	public boolean isEditable2() {
		return editable2;
	}
	
	/**
	 * Metoo para asignar el valor a editable2
	 * @param editable2 El parametro editable2 me permite visualizar o no formularios
	 * el la pagina del cajero
	 */
	public void setEditable2(boolean editable2) {
		this.editable2 = editable2;
	}
	
	/**
	 * Metodo para obtener el codigo de un Credito
	 * @return El codigo del credito que se va a realizar el pago
	 */
	public int getCodigoAux() {
		return codigoAux;
	}
	
	/**
	 * Meoto para asignar el codifo de un credito
	 * @param codigoAux El parametro codigoAux me permite asignar el codigo 
	 * de una transaccion que se desea
	 */
	public void setCodigoAux(int codigoAux) {
		this.codigoAux = codigoAux;
	}
	
	/**
	 * Metodo para obtener una transaacion
	 * @return Una transaccion para poder realizar su respetivo registro
	 */
	public Transaccion getTransaccionAux() {
		return transaccionAux;
	}
	
	/**
	 * Asignar una Transaccion
	 * @param transaccionAux El parametro transaccionAux mepermite asignar
	 * una transaccion y sus valores
	 */
	public void setTransaccionAux(Transaccion transaccionAux) {
		this.transaccionAux = transaccionAux;
	}
		
	/**
	 * Metodo para obtener la grafica Pie
	 * @return Una grafica que representa los tipos de clientes
	 */
	public PieChartModel getPieModel() {
		return pieModel;
	}
	
	/**
	 * Asigna los valores al Pie
	 * @param pieModel El parametro pieModel me permite
	 * asignar valores para crear la grafica tipo Pie
	 */
	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

	public int getCodigoAux2() {
		return codigoAux2;
	}

	public void setCodigoAux2(int codigoAux2) {
		this.codigoAux2 = codigoAux2;
	}

	public int getCodigoAux3() {
		return codigoAux3;
	}

	public void setCodigoAux3(int codigoAux3) {
		this.codigoAux3 = codigoAux3;
	}
	
	
	/**
	 * Metodo para obtener valor de mostrar la grafica
	 * @return El valore de TRUE o FALSE 
	 */
	public boolean isGrafica() {
		return grafica;
	}
	
	/**
	 * Metodo para asignar el valor a la variable grafica
	 * @param grafica El parmtro grafica me permite asignar
	 * true o false para mostrar la grafixa
	 */
	public void setGrafica(boolean grafica) {
		this.grafica = grafica;
	}

	/**
	 * Metodo para validar la cedula de Un Cliente
	 * 
	 * @return Me devuelve un Mensaje si la ceudla es correcta, incorreta o si el
	 *         cliente no esta registrado
	 */
	public String valCedula() {
		if (cliente.getCedula() != null) {
			try {
				boolean c = clienteON.validadorDeCedula(cliente.getCedula());
				if (c) {
					Cliente usuarioRegistrado = clienteON.buscarCliente(cliente.getCedula());
					if (usuarioRegistrado != null) {
						CuentaDeAhorro cuen = clienteON.buscarCuentaDeAhorroCliente(usuarioRegistrado.getCedula());
						System.out.println("Registrado");
						String l = (String) (usuarioRegistrado.getNombre() + "    " + usuarioRegistrado.getApellido());
						editable = false;
						cargarTransacciones();
						return l;
					} else {
						String kl = "Cliente No registrado en el sistema";
						editable = false;
						cargarTransacciones();
						return kl;
					}
				} else {
					String ml = "Cedula Incorrecta";
					editable = false;
					cargarTransacciones();
					return ml;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}

		}
		return " ";
	}
	
	/**
	 * Metodo para obtener el numero de cueta de un cliente
	 * @return El numero de cuenta de un cliente
	 */
	public String numCuenta() {
		
		try {
			boolean c;
			c = clienteON.validadorDeCedula(cliente.getCedula());
			if (c) {
				Cliente usuarioRegistrado = clienteON.buscarCliente(cliente.getCedula());
				if (usuarioRegistrado != null) {
					CuentaDeAhorro cuen = clienteON.buscarCuentaDeAhorroCliente(usuarioRegistrado.getCedula());
					return cuen.getNumeroCuentaDeAhorro();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		return null;

	}

	/**
	 * Metodo para validar el Saldo de la Cuenta de Ahorro
	 * 
	 * @return El saldo disponible en la cuenta de ahorro de acuerdo a la
	 *         transaccion que se esta realizando
	 */
	public String valMonto() {
		try {
			if (cliente.getCedula() != null) {
				Cliente usuarioRegistrado = clienteON.buscarCliente(cliente.getCedula());
				if (usuarioRegistrado != null) {
					CuentaDeAhorro cl = clienteON.buscarCuentaDeAhorroCliente(cliente.getCedula());
					String l = String.valueOf(cl.getSaldoCuentaDeAhorro());
					if (tipoTransaccion.equalsIgnoreCase("retiro") && monto == null) {
						return l;
					} else if (tipoTransaccion.equalsIgnoreCase("retiro") && cl.getSaldoCuentaDeAhorro() < monto) {
						String ms = "La cuenta no cuenta con el saldo suficiente, Su saldo es: "
								+ cl.getSaldoCuentaDeAhorro();
						return ms;

					} else {
						return l;
					}

				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return " ";
	}

	/**
	 * Metodo para regitrar la transaccion
	 * 
	 * @return Me devuelve a la Pagina del cajero para realizar una nueva
	 *         transaccion
	 */
	public String registrar() {
		CuentaDeAhorro clp = clienteON.buscarCuentaDeAhorroCliente(cliente.getCedula());
		if (tipoTransaccion.equalsIgnoreCase("deposito")) {
			Double nvmonto = clp.getSaldoCuentaDeAhorro() + monto;
			clp.setSaldoCuentaDeAhorro(nvmonto);
			clienteON.actualizarCuentaDeAhorro(clp);
			Transaccion t = new Transaccion();
			t.setCliente(clp.getCliente());
			t.setMonto(monto);
			t.setFecha(new Date());
			t.setTipo("deposito");
			t.setSaldoCuenta(nvmonto);
			try {
				clienteON.guardarTransaccion(t);
				addMessage("Confirmacion", "Transaccion Guardada");
				editable = false;
				listaTra = new ArrayList<Transaccion>();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			try {
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("IndexCajero.xhtml");
			} catch (Exception e) {
			}
		} else if (tipoTransaccion.equalsIgnoreCase("retiro") && monto <= clp.getSaldoCuentaDeAhorro()) {
			Double nvmonto2 = clp.getSaldoCuentaDeAhorro() - monto;
			clp.setSaldoCuentaDeAhorro(nvmonto2);
			clienteON.actualizarCuentaDeAhorro(clp);
			Transaccion t2 = new Transaccion();
			t2.setCliente(clp.getCliente());
			t2.setMonto(monto);
			t2.setFecha(new Date());
			t2.setTipo("retiro");
			t2.setSaldoCuenta(nvmonto2);
			try {
				
				clienteON.guardarTransaccion(t2);
				addMessage("Confirmacion", "Transaccion Guardada");
				editable = false;
				listaTra = new ArrayList<Transaccion>();
				
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.getMessage();
			}
			try {
				
				FacesContext contex = FacesContext.getCurrentInstance();
				contex.getExternalContext().redirect("IndexCajero.xhtml");
			} catch (Exception e) {
			}
		}
		return "IndexCajero";
	}

	/**
	 * Metodo para Cargar las Transacciones
	 * 
	 * @return Una lista de transacciones del cliente que va a realizar un Deposito
	 *         o Retiro
	 */
	public String cargarTransacciones() {
		List<Transaccion> lis = clienteON.listadeTransacciones(cliente.getCedula());
		if (lis != null) {
			listaTra = lis;
			editable = true;
			editable2 = false;
		}
		return null;
	}
	

	
	/**
	 * Metodo para cambiar el estado de la visualizacion de la ventana del pago de credito
	 * 
	 */
	 public void activar() {
		 try {
			 editable = true;
			 editable2 = false;
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	 }
	 
	 /**
	  * Metodo para cambiar el estado de pago de credito
	  *  
	  * @param cod El parametro cod me permite asignar el codigo del credito que se va a actualizar
	  */
	 public void cambioVar(int cod) {
		 codigoAux = cod;
		 editable = false;
		 editable2 = true;
		 
	 }
	 
	
	 
	  public void addMessage(String summary, String detail) {   
	        FacesContext context = FacesContext.getCurrentInstance();
	        context.getExternalContext().getFlash().setKeepMessages(true);
	        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	    }
	 
	 public void handleClose(CloseEvent event) {
	        addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
	    }
	     
	    public void handleMove(MoveEvent event) {
	        event.setTop(500);
	    	addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
	    }

	    

		public String agregarCuenta() {
			CuentaDeAhorro clp = clienteON.buscarCuentaDeAhorroCliente(cliente.getCedula());
			
			if (tipoTransaccion.equalsIgnoreCase("deposito")) {
				Double nvmonto = clp.getSaldoCuentaDeAhorro() + monto;
				clp.setSaldoCuentaDeAhorro(nvmonto);
				clp.setCliente(clp.getCliente());
				clp.setFechaDeRegistro(new Date());
				clienteON.guardarCuentaDeAhorros(clp);
				
				Transaccion t = new Transaccion();
				
				t.setCliente(clp.getCliente());
				t.setMonto(monto);
				t.setFecha(new Date());
				t.setTipo("deposito");
				t.setSaldoCuenta(nvmonto);
				
				try {
					clienteON.guardarTransaccion(t);
					addMessage("Confirmacion", "Cuenta Guardada");
					editable = false;
					listaTra = new ArrayList<Transaccion>();
					
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
				try {
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect("IndexCajero.xhtml");
				} catch (Exception e) {
				}
			} 
			return "IndexCajero";
		}
}
