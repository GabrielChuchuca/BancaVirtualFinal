package ec.edu.ups.BancaVirtualFinal.vistas;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;
import ec.edu.ups.BancaVirtualFinal.on.GestionUsuarioLocal;

/**
 * @author ADMINX
 *
 */
@ManagedBean
@ViewScoped
public class CrearCliente {
	// Atributos de la clase
		@Inject
		private GestionUsuarioLocal gestionUsuarios;
		private Cliente cliente;
		private Cliente clienteSelecionado;
		private String numeroCuenta;
		private String saldoCuenta;
		private List<Cliente> lstClientes;
		private Part arCedula;
		private Part arPlanillaServicios;
		private Part arRolDePagos;
		private String idcliente;
		private CuentaDeAhorro cuentaDeAhorro;
	private String tipoCuenta;
		
		public String getTipoCuenta() {
			return tipoCuenta;
		}

		public void setTipoCuenta(String tipoCuenta) {
			this.tipoCuenta = tipoCuenta;
		}

		
		

		public Cliente getClienteSelecionado() {
			return clienteSelecionado;
		}

		public void setClienteSelecionado(Cliente clienteSelecionado) {
			this.clienteSelecionado = clienteSelecionado;
		}

		/**
		 * Metodo que permite inicializar atributos y metodos al momento que se llama a
		 * esta clase
		 */
		@PostConstruct
		private void iniciar() {
			cliente = new Cliente();
			clienteSelecionado = new Cliente();
			cuentaDeAhorro = new CuentaDeAhorro();
//			solicitudDeCredito = new SolicitudDeCredito();
		}

		public GestionUsuarioLocal getGestionUsuarios() {
			return gestionUsuarios;
		}

		
		
		public void setGestionUsuarios(GestionUsuarioLocal gestionUsuarios) {
			this.gestionUsuarios = gestionUsuarios;
		}

		/**
		 * Metodo que permte obtener el atrbuto de tipo Cliente cliente de la clase
		 * 
		 * @return Atributo cliente de la clase
		 */
		public Cliente getCliente() {
			return cliente;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo Cliente cliente de la
		 * clase
		 * 
		 * @param cliente Variable asiganda al atributo cliente de la clase
		 */
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		/**
		 * Metodo que permte obtener el atrbuto de tipo String saldoCuenta de la clase
		 * 
		 * @return Atributo saldoCuenta de la clase
		 */
		public String getSaldoCuenta() {
			return saldoCuenta;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo String saldoCuenta de
		 * la clase
		 * 
		 * @param saldoCuenta Variable asiganda al atributo saldoCuenta de la clase
		 */
		public void setSaldoCuenta(String saldoCuenta) {
			this.saldoCuenta = saldoCuenta;
		}

		/**
		 * Metodo que permte obtener el atrbuto de tipo String numeroCuenta de la clase
		 * 
		 * @return Atributo numeroCuenta de la clase
		 */
		public String getNumeroCuenta() {
			return numeroCuenta;
		}
		

		public String getIdcliente() {
			return idcliente;
		}

		public void setIdcliente(String idcliente) {
			this.idcliente = idcliente;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo String numeroCuenta
		 * de la clase
		 * 
		 * @param numeroCuenta Variable asiganda al atributo numeroCuenta de la clase
		 */
		public void setNumeroCuenta(String numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
		}

		/**
		 * Metodo que permte obtener el atrbuto de tipo CuentaDeAhorro cuentaDeAhorro de
		 * la clase
		 * 
		 * @return Atributo numeroCuenta de la clase
		 */
		public CuentaDeAhorro getCuentaDeAhorro() {
			return cuentaDeAhorro;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo CuentaDeAhorro
		 * cuentaDeAhorro de la clase
		 * 
		 * @param cuentaDeAhorro Variable asiganda al atributo cuentaDeAhorro de la
		 *                       clase
		 */
		public void setCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
			this.cuentaDeAhorro = cuentaDeAhorro;
		}
		 

//		/** 
//		 * Metodo que permte obtener el atrbuto de tipo List lstClientes de la clase
//		 * @return Atributo lstClientes de la clase
//		 */
		public List<Cliente> getLstClientes() {
			return lstClientes;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo List lstClientes de
		 * la clase
		 * 
		 * @param lstClientes Variable asiganda al atributo lstClientes de la clase
		 */
		public void setLstClientes(List<Cliente> lstClientes) {
			this.lstClientes = lstClientes;
		}
		/**
		 * Metodo que permite obtener el atrbuto de tipo SolicitudDeCredito
		 * solicitudDeCredito de la clase
		 * 
		 * @return Atributo solicitudDeCredito de la clase
		 */
//		public SolicitudDeCredito getSolicitudDeCredito() {
//			return solicitudDeCredito;
//		}
//		/** 
//		 * Metodo que permite asignar un valor al atributo de tipo SolicitudDeCredito solicitudDeCredito de la clase
//		 * @param solicitudDeCredito Variable asiganda al atributo solicitudDeCredito de la clase
//		 */
//		public void setSolicitudDeCredito(SolicitudDeCredito solicitudDeCredito) {
//			this.solicitudDeCredito = solicitudDeCredito;
//		}

		/**
		 * Metodo que permite obtener el atrbuto de tipo Part arCedula de la clase
		 * 
		 * @return Atributo arCedula de la clase
		 */
		public Part getArCedula() {
			return arCedula;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo Part arCedula de la
		 * clase
		 * 
		 * @param arCedula Variable asiganda al atributo arCedula de la clase
		 */
		public void setArCedula(Part arCedula) {
			this.arCedula = arCedula;
		}

		/**
		 * Metodo que permite obtener el atrbuto de tipo Part arPlanillaServicios de la
		 * clase
		 * 
		 * @return Atributo arPlanillaServicios de la clase
		 */
		public Part getArPlanillaServicios() {
			return arPlanillaServicios;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo Part
		 * arPlanillaServicios de la clase
		 * 
		 * @param arPlanillaServicios Variable asiganda al atributo arPlanillaServicios
		 *                            de la clase
		 */
		public void setArPlanillaServicios(Part arPlanillaServicios) {
			this.arPlanillaServicios = arPlanillaServicios;
		}

		/**
		 * Metodo que permite obtener el atrbuto de tipo Part arRolDePagos de la clase
		 * 
		 * @return Atributo arRolDePagos de la clase
		 */
		public Part getArRolDePagos() {
			return arRolDePagos;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo Part arRolDePagos de
		 * la clase
		 * 
		 * @param arRolDePagos Variable asiganda al atributo arRolDePagos de la clase
		 */
		public void setArRolDePagos(Part arRolDePagos) {
			this.arRolDePagos = arRolDePagos;
		}

		public void handleClose(CloseEvent event) {
			addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
		}

		public void handleMove(MoveEvent event) {
			event.setTop(500);
			addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
		}

		/**
		 * Metodo que permite asignar un valor para poder enviar un mensaje de
		 * confirmacion
		 * 
		 * @param summary summary Variable tipo String la cual sera el titulo de la
		 *                confirmacion
		 * @param detail  Varibale tipo String en donde se almacenara la descripcion del
		 *                mensaje
		 */
		public void addMessage(String summary, String detail) {
			FacesContext context = FacesContext.getCurrentInstance();
			context.getExternalContext().getFlash().setKeepMessages(true);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
			// FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary,
			// detail);
			// FacesContext.getCurrentInstance().addMessage(null, message);
		}

		/**
		 * Metodo que permite guardar un cliente con sus respectivos atributos
		 * 
		 * @return Nulo
		 */
		public String crearCliente() {
			try {
				gestionUsuarios.guardarCliente(cliente);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		
		public String desbloquearCliente() {
			try {
				gestionUsuarios.desbloquear(cliente);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		public String validarCedula() {
			if (cliente.getCedula() != null) {
				Cliente cli = gestionUsuarios.buscarCliente(cliente.getCedula());
				if (cli != null) {
					return "Este cliente ya se encuentra registrado";
				}
				try {
					boolean verificar = gestionUsuarios.validadorDeCedula(cliente.getCedula());
					if (verificar) {
						return "Cedula Valida";
					} else if (verificar == false) {
						return "Cedula Incorrecta";
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			return " ";

		}

		public String generarNumeroCuenta() {
			this.numeroCuenta = gestionUsuarios.generarNumeroDeCuenta();
			return numeroCuenta;
		}

		public String crearCuenta() {
			try {
				cuentaDeAhorro.setNumeroCuentaDeAhorro(numeroCuenta);
				cuentaDeAhorro.setFechaDeRegistro(new Date());
				cuentaDeAhorro.setCliente(cliente);
				cuentaDeAhorro.setTipoCuenta(tipoCuenta);
				cuentaDeAhorro.setSaldoCuentaDeAhorro(Double.parseDouble(saldoCuenta));
				gestionUsuarios.guardarCuentaDeAhorros(cuentaDeAhorro);
				Transaccion transaccion = new Transaccion();
				transaccion.setFecha(new Date());
				transaccion.setMonto(cuentaDeAhorro.getSaldoCuentaDeAhorro());
				transaccion.setTipo("deposito");
				transaccion.setCliente(cliente);
				transaccion.setSaldoCuenta(cuentaDeAhorro.getSaldoCuentaDeAhorro());
				
				gestionUsuarios.guardarTransaccion(transaccion);
				addMessage("Confirmacion", "Cliente Guardado");
//				cliente = new Cliente();

				try {
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect("CrearCliente.xhtml");
				} catch (Exception t) {

				}
			} catch (

			Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}

		/**
		 * Metodo que permite obtener los clientes que se encuentren creados
		 * 
		 * @return Variable de tipo List en donde se encuentran todos los clientes
		 *         creados
		 */
		public List<Cliente> obtenerClientes() {
			try {
				List<Cliente> clis = gestionUsuarios.listaClientes();
				System.out.println(clis.size());
				return gestionUsuarios.listaClientes();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
		
		
		
		public void selecionarclienteb(Cliente clienteSelecionado) {
			
			System.out.println("SE DEBE DE RETORNAR VISTA LISTA BLOQUEOS CON ACTUALES B");
			clienteSelecionado.setEstado("C");
			gestionUsuarios.desbloquear(clienteSelecionado);
		}
		
		
		public List<Cliente> obtenerClientesBloqueados() {
			try {
				List<Cliente> clis = gestionUsuarios.listaClientesBloqueados();
				System.out.println(clis.size());
				return gestionUsuarios.listaClientesBloqueados();
			} catch (Exception e) {
				// TODO: handle exception
			}
			return null;
		}
}
