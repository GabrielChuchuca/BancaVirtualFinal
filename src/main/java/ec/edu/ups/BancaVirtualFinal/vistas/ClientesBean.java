package ec.edu.ups.BancaVirtualFinal.vistas;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.MoveEvent;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;
import ec.edu.ups.BancaVirtualFinal.modelo.SesionCliente;
import ec.edu.ups.BancaVirtualFinal.modelo.SolicitudPoliza;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;
import ec.edu.ups.BancaVirtualFinal.on.GestionUsuarioLocal;

/**
 * @author ADMINX
 *
 */

@ManagedBean
@SessionScoped
public class ClientesBean {
	// Atributos de la clase
		@Inject
		private GestionUsuarioLocal gestionUsuarios;
		private Cliente cliente;
		private Cliente garante;
		private String numeroCuenta;
		private String cedulaParametro;
		private Transaccion transaccion;
		private List<Cliente> lstClientes;
		private List<SesionCliente> lstSesionesCliente;
		private List<Transaccion> lstTransacciones;
//		private List<Credito> lstCreditosAprobados; 
//		private List<DetalleCredito> lstDetallesCredito;
		private String saldoCuenta;
		private Date fechaInicio;
		private Date fechaFinal;
		private String tipoTransaccion;
		private String fechasInvalidas;
		private String cedulaGarante;
		private InputStream arCedula;
		private InputStream arPlanillaServicios;
		private InputStream arRolDePagos;
		private String mensajeGarante;
		private double ingresos;
		private double egresos;
		private boolean editable;
		private int codigoCredito;
		private CuentaDeAhorro cuentaDeAhorro;
		private CuentaDeAhorro buscarCuentaDeAhorro;
		private Cliente idcliente;
		
		private SolicitudPoliza solicitudPoliza;
		private Double interes;
		private Double valorp;
		private int dias;

		/**
		 * Metodo que permite inicializar atributos y metodos al momento que se llama a
		 * esta clase
		 */
		@PostConstruct
		private void iniciar() {
			listarClientes();
			tipoTransaccion = "Todos";
			System.out.println(lstClientes.size());
			cuentaDeAhorro = new CuentaDeAhorro();
			cliente = new Cliente();
			garante = new Cliente();
			idcliente = new Cliente();
//			lstCreditosAprobados = new ArrayList<Credito>();  
//			solicitudDeCredito = new SolicitudDeCredito();
			solicitudPoliza = new SolicitudPoliza();
		}

		/**
		 * Metodo que permite obtener el atributo cliente
		 * 
		 * @return Atributo cliente de la clase
		 */
		public Cliente getCliente() {
			return cliente;
		}

		/**
		 * Metodo que permite asignar un valor al atributo cliente
		 * 
		 * @param cliente Variable asiganda al atributo cliente de la clase
		 */
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		/**
		 * Metodo que permte obtener el atrbuto gestionUsuarios de la clase
		 * 
		 * @return Atributo gestionUsuarios de la clase
		 */
		public GestionUsuarioLocal getGestionUsuarios() {
			return gestionUsuarios;
		}
		
		/**
		 * @return the garante
		 */
		public Cliente getGarante() {
			return garante;
		}

		/**
		 * @param garante the garante to set
		 */
		public void setGarante(Cliente garante) {
			this.garante = garante;
		}

		/**
		 * @return the cedulaGarante
		 */
		public String getCedulaGarante() {
			return cedulaGarante;
		}

		/**
		 * @param cedulaGarante the cedulaGarante to set
		 */
		public void setCedulaGarante(String cedulaGarante) {
			this.cedulaGarante = cedulaGarante;
		}

		/**
		 * @return the arCedula
		 */
		public InputStream getArCedula() {
			return arCedula;
		}

		/**
		 * @param arCedula the arCedula to set
		 */
		public void setArCedula(InputStream arCedula) {
			this.arCedula = arCedula;
		}

		/**
		 * @return the arPlanillaServicios
		 */
		public InputStream getArPlanillaServicios() {
			return arPlanillaServicios;
		}

		/**
		 * @param arPlanillaServicios the arPlanillaServicios to set
		 */
		public void setArPlanillaServicios(InputStream arPlanillaServicios) {
			this.arPlanillaServicios = arPlanillaServicios;
		}

		/**
		 * @return the arRolDePagos
		 */
		public InputStream getArRolDePagos() {
			return arRolDePagos;
		}

		/**
		 * @param arRolDePagos the arRolDePagos to set
		 */
		public void setArRolDePagos(InputStream arRolDePagos) {
			this.arRolDePagos = arRolDePagos;
		}

		/**
		 * @return the mensajeGarante
		 */
		public String getMensajeGarante() {
			return mensajeGarante;
		}

		/**
		 * @param mensajeGarante the mensajeGarante to set
		 */
		public void setMensajeGarante(String mensajeGarante) {
			this.mensajeGarante = mensajeGarante;
		}

		/**
		 * @return the codigoCredito
		 */
		public int getCodigoCredito() {
			return codigoCredito;
		}

		/**
		 * @param codigoCredito the codigoCredito to set
		 */
		public void setCodigoCredito(int codigoCredito) {
			this.codigoCredito = codigoCredito;
		}

		public String crearSolicitudPoliza() throws Exception {
			System.out.println("ENTRO EN LA SOLICITUD");		
			solicitudPoliza.setCliente(gestionUsuarios.buscarCliente(buscarCuentaDeAhorro.getCliente().getCedula()));
			System.out.println("++++++++++++++++++++++++++"+solicitudPoliza.getCliente().toString());
			solicitudPoliza.setPoliza(gestionUsuarios.guardaringresodias(solicitudPoliza.getDias()));	
			
			System.out.println("*****************7/7/48+++++++++++++"+solicitudPoliza.getPoliza().toString());
			solicitudPoliza.setEstado("S");
			solicitudPoliza.setDias(solicitudPoliza.getDias());		
			System.out.println("///////////////dias////////"+solicitudPoliza.getDias());
			solicitudPoliza.setMonto(solicitudPoliza.getMonto()); 
			
			solicitudPoliza.setCedula(gestionUsuarios.toByteArray(arCedula));
			solicitudPoliza.setPlanilla(gestionUsuarios.toByteArray(arPlanillaServicios));
			if(gestionUsuarios.verificarSolicitudSolicitando(cedulaParametro)) { 
				gestionUsuarios.guardarSolicitudPoliza(solicitudPoliza);
				addMessage("Confirmacion", "Solicitud ENVIADA");
			}else { 
				addMessage("Atencion", "Usted ya ha enviado una solicitud de POLIZA para su aprovacion");
			}
			return "SolicitudPoliza";
		}

		/**
		 * Metodo que permite asignar un valor al atributo gestionUsuarios de la clase
		 * 
		 * @param gestionUsuarios Variable asiganda al atributo gestionUsuarios de la
		 *                        clase
		 */
		public void setGestionUsuarios(GestionUsuarioLocal gestionUsuarios) {
			this.gestionUsuarios = gestionUsuarios;
		}

		/**
		 * Metodo que permite obtener el atributo numeroCuenta de la clase
		 * 
		 * @return Atributo numeroCuenta de la clase
		 */
		public String getNumeroCuenta() {
			return numeroCuenta;
		}

		public Cliente getIdcliente() {
			return idcliente;
		}

		public void setIdcliente(Cliente idcliente) {
			this.idcliente = idcliente;
		}

		/**
		 * Metodo que permite asignar un valor al atributo numeroCuenta de la clase
		 * 
		 * @param numeroCuenta Variable asiganda al atributo numeroCuenta de la clase
		 */
		public void setNumeroCuenta(String numeroCuenta) {
			this.numeroCuenta = numeroCuenta;
		}

		/**
		 * Metodo que permite obtener el atributo cuentaDeAhorro de la clase
		 * 
		 * @return Atributo cuentaDeAhorro de la clase
		 */
		public CuentaDeAhorro getCuentaDeAhorro() {
			return cuentaDeAhorro;
		}

		/**
		 * Metodo que permite asignar un valor al atributo cuentaDeAhorro de la clase
		 * 
		 * @param cuentaDeAhorro Variable asignada al atributo cuentaDeAhorro de la
		 *                       clase
		 */
		public void setCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
			this.cuentaDeAhorro = cuentaDeAhorro;
		}

		/**
		 * Metodo que permite obtener el atributo buscarCuentaDeAhorro de la clase
		 * 
		 * @return Atributo buscarCuentaDeAhorro de la clase
		 */
		public CuentaDeAhorro getBuscarCuentaDeAhorro() {
			return buscarCuentaDeAhorro;
		}

//		/**
//		 * Metodo que permite asignar un valor al atributo buscarCuentaDeAhorro de la
//		 * clase
//		 * 
//		 * @param buscarCuentaDeAhorro Variable asignada al atributo
//		 *                             buscarCuentaDeAhorro de la clase
//		 */
		public void setBuscarCuentaDeAhorro(CuentaDeAhorro buscarCuentaDeAhorro) {
			this.buscarCuentaDeAhorro = buscarCuentaDeAhorro;
		}

		/**
		 * Metodo que permite obtener el atributo cedulaParametro de la clase
		 * 
		 * @return Atributo cedulaParametro de la clase
		 */
		public String getCedulaParametro() {
			return cedulaParametro;
		}

		/**
		 * Metodo que permite asignar un valor al atributo fechasInvalidas de la clase
		 * 
		 * @return
		 */
		public String getFechasInvalidas() {
			return fechasInvalidas;
		}

		/**
		 * Metodo que permite asignar un valor al atributo fechasInvalidas de la clase
		 * 
		 * @param fechasInvalidas Variable asignada al atributo fechasInvalidas de la
		 *                        clase
		 */
		public void setFechasInvalidas(String fechasInvalidas) {
			this.fechasInvalidas = fechasInvalidas;
		}

		/**
		 * Metodo que permite asignar un valor al atributo cedulaParametro de la clase y
		 * a su vez buscar la cuenta de ahorros y transaccion de un cliente que tenga la
		 * cedula asignada al metodo
		 * 
		 * @param cedulaParametro Variable asignada al atributo cedulaParametro de la
		 *                        clase
		 */
		public void setCedulaParametro(String cedulaParametro) {
			this.cedulaParametro = cedulaParametro;
			if (cedulaParametro != null) {
				try {
					buscarCuentaDeAhorro = gestionUsuarios.buscarCuentaDeAhorroCliente(cedulaParametro);
					List<Transaccion> lista = gestionUsuarios.listadeTransacciones(cedulaParametro);
					transaccion = lista.get(lista.size() - 1);
					ultimosDias();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}


		




		/**
		 * Metodo que permite obtener el atributo de tipo lista lstClientes de la clase
		 * 
		 * @return Atributo de tipo lista lstClientes de la clase
		 */
		public List<Cliente> getLstClientes() {
			return lstClientes;
		}

		/***
		 * Metodo que permite obtener el atributo transaccion de la clase
		 * 
		 * @return Atributo transaccion de la clase
		 */
		public Transaccion getTransaccion() {
			return transaccion;
		}

		/**
		 * Metodo que permite asignar un valor al atributo transaccion de la clase
		 * 
		 * @param transaccion Variable asignada al atributo transaccion de la clase
		 */
		public void setTransaccion(Transaccion transaccion) {
			this.transaccion = transaccion;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo lista lstTransacciones de la
		 * clase
		 * 
		 * @return Atributo de tipo lista lstTransacciones de la clase
		 */
		public List<Transaccion> getLstTransacciones() {
			return lstTransacciones;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo lista
		 * lstTransacciones de la clase
		 * 
		 * @param lstTransacciones Variable asignada al atributo de tipo lista
		 *                         lstTransacciones de la clase
		 */
		public void setLstTransacciones(List<Transaccion> lstTransacciones) {
			this.lstTransacciones = lstTransacciones;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo lista lstClientes de
		 * la clase
		 * 
		 * @param lstClientes Variable asignada al atributo de tipo lista lstClientes de
		 *                    la clase
		 */
		public void setLstClientes(List<Cliente> lstClientes) {
			this.lstClientes = lstClientes;
		}

	//
		/**
		 * Metodo que permite obtener el atributo de tipo lista lstSesionesClientes de
		 * la clase
		 * 
		 * @return Atributo de tipo lista lstSesionesClientes de la clase
		 */
		public List<SesionCliente> getLstSesionesCliente() {
			return lstSesionesCliente;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo lista
		 * lstSesionesClientes de la clase
		 * 
		 * @param lstSesionesCliente Variable asignada al atributo de tipo lista
		 *                           lstSesionesClientes de la clase
		 */
		public void setLstSesionesCliente(List<SesionCliente> lstSesionesCliente) {
			this.lstSesionesCliente = lstSesionesCliente;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo date fechaInicio de la clase
		 * 
		 * @return Atributo de tipo date fechaInicio de la clase
		 */
		public Date getFechaInicio() {
			return fechaInicio;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo date fechainicio de
		 * la clase
		 * 
		 * @param fechaInicio Variable asignada al atributo de tipo date fechaInicio de
		 *                    la clase
		 */
		public void setFechaInicio(Date fechaInicio) {
			this.fechaInicio = fechaInicio;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo date fechaFinal de la clase
		 * 
		 * @return Atributo de tipo date fechaFinal de la clase
		 */
		public Date getFechaFinal() {
			return fechaFinal;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo date fechaFinal de la
		 * clase
		 * 
		 * @param fechaFinal Variable asignada al atributo de tipo date fechaFinal de la
		 *                   clase
		 */
		public void setFechaFinal(Date fechaFinal) {
			this.fechaFinal = fechaFinal;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo String tipoTransaccion de la
		 * clase
		 * 
		 * @return Atributo de tipo String tipoTransaccion de la clase
		 */
		public String getTipoTransaccion() {
			return tipoTransaccion;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo String
		 * tipoTransaccion de la clase
		 * 
		 * @param tipoTransaccion Variable asignada al atributo de tipo String
		 *                        tipoTransaccion de la clase
		 */
		public void setTipoTransaccion(String tipoTransaccion) {
			this.tipoTransaccion = tipoTransaccion;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo boolean editable de la clase
		 * 
		 * @return Atributo de tipo boolean editable de la clase
		 */
		public boolean isEditable() {
			return editable;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo boolean editable de
		 * la clase
		 * 
		 * @param editable Variable asignada al atributo de tipo boolean editable de la
		 *                 clase
		 */
		public void setEditable(boolean editable) {
			this.editable = editable;
		}

		/**
		 * Metodo que permite crear un cliente
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

		/**
		 * Metodo que permite obtener el atributo de tipo double ingresos de la clase
		 * 
		 * @return Atributo de tipo double ingresos de la clase
		 */
		public double getIngresos() {
			return ingresos;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo double ingresos de la
		 * clase
		 * 
		 * @param ingresos Variable asignada al atributo de tipo double ingresos de la
		 *                 clase
		 */
		public void setIngresos(double ingresos) {
			this.ingresos = ingresos;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo double egresos de la clase
		 * 
		 * @return Atributo de tipo double egresos de la clase
		 */
		public double getEgresos() {
			return egresos;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo double egresos de la
		 * clase
		 * 
		 * @param egresos Variable asignada al atributo de tipo double egresos de la
		 *                clase
		 */
		public void setEgresos(double egresos) {
			this.egresos = egresos;
		}

		/**
		 * Metodo que permite validar la cedula de un cliente
		 * 
		 * @return Mensaje de confirmacion
		 */

//		public String validarCedula() {
//			if (cliente.getCedula() != null) {
//				Cliente cli = gestionUsuarios.buscarCliente(cliente.getCedula());
//				if (cli != null) {
//					return "Este cliente ya se encuentra registrado";
//				}
//				try {
//					boolean verificar = gestionUsuarios.validadorDeCedula(cliente.getCedula());
//					if (verificar) {
//						return "Cedula Valida";
//					} else if (verificar == false) {
//						return "Cedula Incorrecta";
//					}
//				} catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//			return " ";
	//
//		}
	//
		/**
		 * Metodo que permite generar el numero de cuenta
		 * 
		 * @return Numero de cuenta que se ha generado
		 */
		public String generarNumeroCuenta() {
			this.numeroCuenta = gestionUsuarios.generarNumeroDeCuenta();
			return numeroCuenta;
		}

		/**
		 * Metodo que permite obtener el atributo de tipo String saldoCuenta de la clase
		 * 
		 * @return Atributo de tipo String saldoCuenta de la clase
		 */
		public String getSaldoCuenta() {
			return saldoCuenta;
		}

		/**
		 * Metodo que permite asignar un valor al atributo de tipo String saldoCuenta de
		 * la clase
		 * 
		 * @param saldoCuenta Variable asignada al atributo de tipo string saldoCuenta
		 *                    de la clase
		 */
		public void setSaldoCuenta(String saldoCuenta) {
			this.saldoCuenta = saldoCuenta;
		}

		
		
		/**
		 * @return the solicitudPoliza
		 */
		public SolicitudPoliza getSolicitudPoliza() {
			return solicitudPoliza;
		}

		/**
		 * @param solicitudPoliza the solicitudPoliza to set
		 */
		public void setSolicitudPoliza(SolicitudPoliza solicitudPoliza) {
			this.solicitudPoliza = solicitudPoliza;
		}

		/**
		 * @return the interes
		 */
		public Double getInteres() {
			return interes;
		}

		/**
		 * @param interes the interes to set
		 */
		public void setInteres(Double interes) {
			this.interes = interes;
		}

		/**
		 * @return the valorp
		 */
		public Double getValorp() {
			return valorp;
		}

		/**
		 * @param valorp the valorp to set
		 */
		public void setValorp(Double valorp) {
			this.valorp = valorp;
		}

		/**
		 * @return the dias
		 */
		public int getDias() {
			return dias;
		}

		/**
		 * @param dias the dias to set
		 */
		public void setDias(int dias) {
			this.dias = dias;
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
		 * @param summary Variable tipo String la cual sera el titulo de la confirmacion
		 * @param detail  Varibale tipo String en donde se almacenara la descripcion del
		 *                mensaje
		 */
		public void addMessage(String summary, String detail) {
			FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
			FacesContext.getCurrentInstance().addMessage(null, message);
		}

		/**
		 * Metodo que me permite obtener una lista de los clientes y asignarlo a la
		 * variable lstClientes de la clase
		 */
		public void listarClientes() {
			lstClientes = gestionUsuarios.listaClientes();
		}

		/**
		 * Metodo que permite cambiar el formato a una fecha
		 * 
		 * @param fecha Fecha a la que se cambia el formato
		 * @return Fecha cambiada el formato
		 */
		public String obtenerFecha(Date fecha) {
			DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			return hourdateFormat.format(fecha);
		}

		/**
		 * Metodo que permite obtener las sesiones de una cliente
		 * 
		 * @param cedula Cedula del cliente
		 * @return Lista de sesiones que tiene el cliente
		 */
		public List<SesionCliente> cargarSesiones() {
			List<SesionCliente> lis = gestionUsuarios.obtenerSesionesCliente(cedulaParametro);
			if (lis != null) {
				lstSesionesCliente = lis;
				return lstSesionesCliente;
			}
			return null;
		}

		/**
		 * Metodo que me permite obtener un mensaje
		 * 
		 * @return Variable de tipo String que me devuelve un mensaje en especifico
		 */
		public String consultarTransacciones() {
			return "ConsultaTransacciones";
		}

		/**
		 * Metodo qe me permite validar entre fechas, y obtener una lista de
		 * transacciones entre dichas fechas
		 * 
		 * @throws Exception
		 */
		public void validarFechas() throws Exception {
			if (this.fechaInicio != null && this.fechaFinal != null) {
				/*
				 * System.out.println(fechaInicio.getClass()); DateFormat hourdateFormat = new
				 * SimpleDateFormat("dd/MM/yyyy"); String d =
				 * hourdateFormat.format(fechaInicio);
				 * System.out.println(buscarCuentaDeAhorro.getNumeroCuentaDeAhorro());
				 * System.out.println(d +"***"+fechaFinal);
				 */
				DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
				String inicioF = hourdateFormat.format(fechaInicio);
				String finalF = hourdateFormat.format(fechaFinal);
				List<Transaccion> listaTrans = gestionUsuarios.obtenerTransaccionesFechaHora(cedulaParametro, inicioF,
						finalF);
				lstTransacciones = listaTrans;
				System.out.println("H" + lstTransacciones.size());
				System.out.println(cedulaParametro);
				System.out.println(new Date());
			}
		}

		/**
		 * Metodo que me devuelve el tipo de transaccion que se esta utilizando
		 */
		public void obtenerTransaccionesInicioFinal() {

			/*
			 * lstTransacciones =
			 * gestionUsuarios.obtenerTransaccionesFechaHora(buscarCuentaDeAhorro.getCliente
			 * ().getCedula(), fechaInicio, fechaFinal);
			 */

			System.out.println("Este es el tipo de transaccion : " + tipoTransaccion);

		}

		/**
		 * Metodo que permite obtener una lista de transacciones entre una fecha de
		 * inicio y una fecha final
		 */
		public void ultimosDias() {
			Calendar c = Calendar.getInstance();
			fechaFinal = c.getTime();
			c.add(Calendar.DATE, -30);
			fechaInicio = c.getTime();
			DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String inicioF = hourdateFormat.format(fechaInicio);
			String finalF = hourdateFormat.format(fechaFinal);
			List<Transaccion> listaTrans = gestionUsuarios.obtenerTransaccionesFechaHora(cedulaParametro, inicioF, finalF);
			lstTransacciones = listaTrans;
			System.out.println(lstTransacciones.size());
			System.out.println(cedulaParametro);
		}

		/**
		 * Metodo que permite obtener las transacciones entre una fechas, las cuales se
		 * obtienen dependiendo el tipo de transaccion que se requiera
		 * 
		 * @throws Exception
		 */
		public void validarFechas2() throws Exception {
			System.out.println(tipoTransaccion);

			if (this.fechaInicio != null && this.fechaFinal != null) {

				if (errorFechas() == null) {
					fechasInvalidas = errorFechas();
					DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd");
					String inicioF = hourdateFormat.format(fechaInicio);
					String finalF = hourdateFormat.format(fechaFinal);
					List<Transaccion> listaTrans = gestionUsuarios.obtenerTransaccionesFechaHora(cedulaParametro, inicioF,
							finalF);

					if (tipoTransaccion != null) {
						if (tipoTransaccion.equals("Todos")) {
							lstTransacciones = listaTrans;
						} else if (tipoTransaccion.equals("Depositos")) {
							lstTransacciones = new ArrayList<Transaccion>();
							for (Transaccion transaccion : listaTrans) {
								if (transaccion.getTipo().equals("deposito")) {
									lstTransacciones.add(transaccion);
								}
							}
						} else {
							lstTransacciones = new ArrayList<Transaccion>();
							for (Transaccion transaccion : listaTrans) {
								if (transaccion.getTipo().equals("retiro")) {
									lstTransacciones.add(transaccion);
								}
							}
						}
					}
				} else {
					fechasInvalidas = errorFechas();
					lstTransacciones.removeAll(lstTransacciones);
				}

				/*
				 * System.out.println("H"+lstTransacciones.size());
				 * System.out.println(cedulaParametro); System.out.println(new Date());
				 */
			}

			System.out.println("LISTA DE TRANSACCION SIZE :   " + lstTransacciones.size());
		}

		/**
		 * Metodo que permite verificar que la fecha de inicio no sea mayor a la fecha
		 * final
		 * 
		 * @return Variable de tipo String, el cual me dice si la fecha de inicio es
		 *         mayor
		 */
		public String errorFechas() {
			/*
			 * System.out.println(fechaInicio.after(fechaFinal));;
			 * System.out.println("Fecha inicio: "+this.fechaInicio);
			 * System.out.println("Fecha final: "+this.fechaFinal);
			 * if(fechaInicio.after(fechaFinal)) {
			 * System.out.println("ENTROOOOO VALIDACION"); fechasInvalidas = false; return
			 * "No se puede consultar entre estas fechas"; }else { fechasInvalidas = true; }
			 * return "si";
			 */
			Date fechaInicioDate = this.fechaInicio; // String a date
			Date fechaFinDate = this.fechaFinal; // String a date

			System.out.println("Inicial: " + fechaInicioDate);
			System.out.println("Final: " + fechaFinDate);
			// comprueba si es que inicio esta después que fecha actual
			if (fechaInicioDate.after(fechaFinDate)) {
				return "Fecha inicio mayor";
			}
			return null;
		}

		/**
		 * Metodo que permite guardar una solicitud de credito con sus respectivos
		 * atributos
		 * 
		 * @return Pagina en donde se realiza la Solicitud de Credito
		 * @throws IOException
		 */
		/*public String crearSolicitudPoliza() throws Exception {
			System.out.println("ENTRO EN LA SOLICITUD");		
			solicitudPoliza.setClientePoliza(gestionUsuarios.buscarCliente(cedulaParametro));
			solicitudPoliza.setPoliza(gestionUsuarios.buscarPoliza(interes));	
			solicitudPoliza.setEstado("S");
			solicitudPoliza.setDias(dias);		
			solicitudPoliza.setValorTotal(valorp);
			//Poliza p = polizaON.guardaringresodias(dias);
			
			solicitudPoliza.setCedula(gestionUsuarios.toByteArray(arCedula));
			solicitudPoliza.setPlanilla(gestionUsuarios.toByteArray(arPlanillaServicios));
			if(gestionUsuarios.verificarSolicitudSolicitando(cedulaParametro)) { 
				gestionUsuarios.guardarSolicitudCredito(solicitudPoliza);
				addMessage("Confirmacion", "Solicitud ENVIADA");
			}else { 
				addMessage("Atencion", "Usted ya ha enviado una solicitud de POLIZA para su aprovacion");
			}
			return "SolicitudPoliza";
		}*/
//		/** 
//		 * Metodo que permite buscar un cliente, en donde se realiza la validacion de que  
//		 * la persona que esta realizando la solicitud de credito no puede ser garante de si misma
//		 */
//		public void buscarGarante() {
//			if (cedulaGarante.equalsIgnoreCase(cedulaParametro)) {
//				mensajeGarante = "No puede ingresar su propia cedula";
//				garante = new Cliente();
//			} else {
//				garante = gestionUsuarios.buscarCliente(cedulaGarante);
//				mensajeGarante = null;
//			}
//		}

		public void selecionarclienteb(String Cedula) {

			System.out.println("SE DEBE DE RETORNAR VISTA LISTA BLOQUEOS CON ACTUALES B");
			cliente.setEstado("C");
			gestionUsuarios.desbloquear(cliente);
		}

		/**
		 * Metodo que me permite validar que los egresos sean mayores a los ingresos
		 * 
		 * @param ingresos Variable tipo double en donde se asigna los ingresos del
		 *                 cliente
		 * @param egresos  Variable tipo double en donde se asigna los egresos del
		 *                 cliente
		 * @return Variable tipo string en donde me devuelve un mensaje si los egresos
		 *         son mayores a los ingresos
		 */
		public String confirmarTasaPago(double ingresos, double egresos) {
			if (egresos > ingresos) {
				return "Los egresos no debe ser mayor a los ingresos";
			}
			return null;
		}

		/**
		 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
		 * arCedula de la clase
		 * 
		 * @param event Variable de tipo FileUploadEvent
		 * @throws IOException
		 */
		public void archivo1(FileUploadEvent event) throws IOException {
			FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			arCedula = event.getFile().getInputStream();
		}

		/**
		 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
		 * arPlanillaServicios de la clase
		 * 
		 * @param event Variable de tipo FileUploadEvent
		 * @throws IOException
		 */
		public void archivo2(FileUploadEvent event) throws IOException {
			FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			arPlanillaServicios = event.getFile().getInputStream();
		}

		/**
		 * Metodo que me permite asignar un archivo al atributo de tipo InputStream
		 * arPlanillaServicios de la clase
		 * 
		 * @param event Variable de tipo FileUploadEvent
		 * @throws IOException
		 */
		public void archivo3(FileUploadEvent event) throws IOException {
			FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
			FacesContext.getCurrentInstance().addMessage(null, msg);
			arRolDePagos = event.getFile().getInputStream();
		}

		/**
		 * Metodo que permite obtener una lista de creditos aprovados, en donde se pasa
		 * la cedula del cliente de quien se desea obtener los creditos aprovados. La
		 * lista de creditos aprovados se asigana a la variable de tipo list
		 * lstCreditosAprovados de la clase
		 * 
		 * @param cedula Variable en donde se asigna la cedula del cliente de quien se
		 *               desea obtener los creditos aprovados
		 */
//		public void creditosAprovados(String cedula) {   
//			System.out.println("ENTRO EN ESTE PINCHE METODO" + cedulaParametro);
//			lstCreditosAprobados = gestionUsuarios.creditosAprovados(cedula);
//		}  
		/**
		 * Metodo que me permite asignar un valor a las variables codigoCredito de la
		 * clase, y editable de la clase
		 * 
		 * @param cod Variable que se asigna ala variable codigoCredito de tipo int dde
		 *            la clase
		 */
		public void cambioVar(int cod) {
			codigoCredito = cod;
			editable = true;
		}

		/**
		 * Metodo que permite obtener los detalles de un credito en especifico en donde
		 * se asigana como paramatro el codigo del credito
		 * 
		 * @return Variable de tipo lista en donde contiene los detalles de un credito.
		 */
//		 public List<DetalleCredito> verDealles(){
//			 List<DetalleCredito> list = gestionUsuarios.verCredito(codigoCredito).getDetalles();
//			 return list;
//		 }

}
