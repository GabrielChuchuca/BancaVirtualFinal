package ec.edu.ups.BancaVirtualFinal.on;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.CompletableFuture;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.persistence.NoResultException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

import ec.edu.ups.BancaVirtualFinal.dao.ClienteDAO;
import ec.edu.ups.BancaVirtualFinal.dao.CuentaDeAhorroDAO;
import ec.edu.ups.BancaVirtualFinal.dao.EmpleadoDAO;
import ec.edu.ups.BancaVirtualFinal.dao.PolizaDAO;
import ec.edu.ups.BancaVirtualFinal.dao.SesionClienteDAO;
import ec.edu.ups.BancaVirtualFinal.dao.SolicitudPolizaDAO;
import ec.edu.ups.BancaVirtualFinal.dao.TransaccionDAO;
import ec.edu.ups.BancaVirtualFinal.dao.TransferenciaExternaDAO;
import ec.edu.ups.BancaVirtualFinal.dao.TransferenciaLocalDAO;
import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;
import ec.edu.ups.BancaVirtualFinal.modelo.Empleado;
import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;
import ec.edu.ups.BancaVirtualFinal.modelo.SesionCliente;
import ec.edu.ups.BancaVirtualFinal.modelo.SolicitudPoliza;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;
import ec.edu.ups.BancaVirtualFinal.modelo.TransferenciaExterna;
import ec.edu.ups.BancaVirtualFinal.modelo.TransferenciaLocal;
import ec.edu.ups.BancaVirtualFinal.services.Respuesta;
import ec.edu.ups.BancaVirtualFinal.services.RespuestaTransferenciaExterna;

/**
 * @author ADMINX
 *
 */
@Stateless
public class GestionUsuarios implements GestionUsuarioLocal {
	@Inject
	private ClienteDAO clienteDAO;
	@Inject
	private SolicitudPolizaDAO solicitudPolizaDAO;
	@Inject
	private SesionClienteDAO sesionClienteDAO;
	@Inject
	private TransferenciaLocalDAO transferenciaLocalDAO;
	@Inject
	private EmpleadoDAO empleadoDAO;
	@Inject
	private CuentaDeAhorroDAO cuentaDeAhorroDAO;
	@Inject
	private TransaccionDAO transaccionDAO;

	@Inject
	private PolizaDAO polizaDAO;

	@Inject
	private TransferenciaExternaDAO transferenciaExternaDAO;

	private int contf = 0;

	public String generarNumeroDeCuenta() {
		int numeroInicio = 0;
		List<CuentaDeAhorro> listaCuentas = listaCuentaDeAhorros();
		int numero = listaCuentas.size() + 1;
		String resultado = String.format("%08d", numero);
		String resultadoFinal = String.valueOf(numeroInicio) + resultado;
		return resultadoFinal;
	}

	// SE MUESTRA EL NUEVO NUMERO DE CUENTA
	public String getUsuario(String cedula, String nombre, String apellido) {
		System.out.println(cedula);
		System.out.println(nombre);
		System.out.println(apellido);
		String ud = cedula.substring(cedula.length() - 1);
		String pln = nombre.substring(0, 1);
		int it = 0;
		for (int i = 0; i < apellido.length(); i++) {
			if (apellido.charAt(i) == 32) {
				it = i;
			}
		}
		String a = "";
		if (it == 0) {
			a = apellido.substring(0, apellido.length());
		} else {
			a = apellido.substring(0, it);
		}
		return pln.toLowerCase() + a.toLowerCase() + ud;
	}

	// METODO PARA GENERAR CONTRASEÑA
	public String getContraseña() {
		String simbolos = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefjhijklmnopqrstuvwxyz0123456789!#$%&()*+,-./:;<=>?@_";

		int tam = simbolos.length() - 1;
		System.out.println(tam);
		String clave = "";
		for (int i = 0; i < 10; i++) {
			int v = (int) Math.floor(Math.random() * tam + 1);
			clave += simbolos.charAt(v);
		}

		return clave;
	}

	// Metodo que permite el envio de un correo

	public void enviarCorreo(String destinatario, String asunto, String cuerpo) {
		Properties propiedad = new Properties();
		propiedad.setProperty("mail.smtp.host", "smtp.gmail.com");
		propiedad.setProperty("mail.smtp.starttls.enable", "true");
		propiedad.setProperty("mail.smtp.port", "587");

		Session sesion = Session.getDefaultInstance(propiedad);
		String correoEnvia = "gazir.2409@gmail.com";
		String contrasena = "Gavriellalx2";

		MimeMessage mail = new MimeMessage(sesion);
		try {
			mail.setFrom();
			mail.addRecipient(Message.RecipientType.TO, new InternetAddress(destinatario));
			mail.setSubject(asunto);
			mail.setText(cuerpo);

			Transport transportar = sesion.getTransport("smtp");
			transportar.connect(correoEnvia, contrasena);
			transportar.sendMessage(mail, mail.getRecipients(Message.RecipientType.TO));
		} catch (AddressException ex) {
			System.out.println(ex.getMessage());
		} catch (MessagingException ex) {
			System.out.println(ex.getMessage());
		}
	}

	public Poliza buscarPoliza(Double interes) {
		Poliza p = polizaDAO.buscarpor(interes);
		return p;

	}

	public void guardarSolicitudPoliza(SolicitudPoliza solicituPolizas) throws Exception {

		Poliza poliza = polizaDAO.validardias(solicituPolizas.getDias());
		Cliente cliente = clienteDAO.read(solicituPolizas.getCliente().getCedula());
		solicituPolizas.setDias(solicituPolizas.getDias());
		solicituPolizas.setMonto(solicituPolizas.getMonto());
		solicituPolizas.setCliente(cliente);
		solicituPolizas.setEstado("S");
		solicituPolizas.setPoliza(poliza);
		try {
			solicitudPolizaDAO.insert(solicituPolizas);
		} catch (ForbiddenException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean verificarSolicitudSolicitando(String numerocu) {
		List<SolicitudPoliza> solicitudes = solicitudPolizaDAO.getSolicitudPolizas();
		for (SolicitudPoliza solicitudDeCredito : solicitudes) {
			if (solicitudDeCredito.getEstado().equalsIgnoreCase("S")
					&& solicitudDeCredito.getCliente().getCedula().equalsIgnoreCase(numerocu)) {
				return false;
			}
		}
		return true;
	}
	/// METOD PARA CREAR LA POLIZA VALIDANDO EL MAXIMO

	public void guardarPoliza(Poliza p) {
		Poliza poliza = polizaDAO.read(p.getDiasMaximo());
		if (poliza == null) {
			Poliza per = p;
			polizaDAO.insert(per);
			System.out.println("Se crea  la poliza");
		} else {
			System.out.println("No se crea la poliza SE REPITE");
		}

	}

	/// Metodo que permite cambiar el formato de la fecha
	public String obtenerFecha(Date fecha) {
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return hourdateFormat.format(fecha);
	}

	// Metodo que me permite guardar el cliente en la base de datos

	public void guardarCliente(Cliente c) {
		clienteDAO.insert(c);

	}

	/// Metodo que permite la busqueda de un cliente
	public Cliente buscarCliente(String cedulaCliente) {
		Cliente cliente = clienteDAO.read(cedulaCliente);
		return cliente;
	}

	/// Metodo que permite la busqueda del cliente en base a su usuario y contraseña

	public Cliente buscarClienteUsuarioContraseña(String usuario, String contraseña) {
		try {
			return clienteDAO.obtenerClienteUsuarioContraseña(usuario, contraseña);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	/// Metodo que permite eliminar un cliente

	public void eliminarCliente(String cedulaCliente) {
		clienteDAO.delete(cedulaCliente);
	}

	/// Metodo que permite actualizar un cliente

	public void actualizarCliente(Cliente cliente) {
		clienteDAO.update(cliente);
	}

	public List<Cliente> listaClientes() {
		List<Cliente> clientes = clienteDAO.getClientes();
		return clientes;
	}

	/// SE LISTAR CLIENTES BLOQUEADOS

	public List<Cliente> listaClientesBloqueados() {
		List<Cliente> clientes = clienteDAO.getClientesBloqueados();
		return clientes;
	}

	// ESTE METODO DEVUELVE UNA POLIZA CON EL INTERES SEGUN EL DIA
	public Poliza guardaringresodias(int dias) throws Exception {
		Poliza po = polizaDAO.validardias(dias);
		if (po != null) {
			return po;
		} else {
			return null;
		}
	}

	public byte[] toByteArray(InputStream in) throws IOException {

		ByteArrayOutputStream os = new ByteArrayOutputStream();

		byte[] buffer = new byte[1024];
		int len;

		// read bytes from the input stream and store them in buffer
		while ((len = in.read(buffer)) != -1) {
			// write bytes from the buffer into output stream
			os.write(buffer, 0, len);
		}

		return os.toByteArray();
	}
	/*
	 * public boolean verificarSolicitudSolicitando(String numerocu) {
	 * List<SolicitudPoliza> solicitudes = solicitudPolizaDAO.getSolicitudPolizas();
	 * for (SolicitudPoliza solicitudDeCredito : solicitudes) { if
	 * (solicitudDeCredito.getEstado().equalsIgnoreCase("S") &&
	 * solicitudDeCredito.getCuenta().getNumeroCuentaDeAhorro().equalsIgnoreCase(
	 * numerocu)) { return false; } } return true; }
	 */

	public void guardarCuentaDeAhorros(CuentaDeAhorro c) {
		Cliente cliente = clienteDAO.read(c.getCliente().getCedula());
		if (cliente == null) {
			Cliente cli = c.getCliente();

			String usuario = getUsuario(cli.getCedula(), cli.getNombre(), cli.getApellido());
			String contraseña = getContraseña();
			cli.setEstado("C");
			cli.setUsuario(usuario);
			cli.setClave(contraseña);
			c.setCliente(cli);
			String destinatario = cli.getCorreo(); // A quien le quieres escribir.

			String asunto = "CREACION DE USUARIO";
			String cuerpo = "BANCA VIRTUAL                                             SISTEMA TRANSACCIONAL\n"
					+ "-------------------------------------------------------------------------------\n"
					+ "   Estimado(a): " + cli.getNombre().toUpperCase() + " " + cli.getApellido().toUpperCase() + "\n"
					+ "-------------------------------------------------------------------------------\n"
					+ "BANCA VIRTUAL le informa que el usuario ha sido habilitado exitosamente.       \n"
					+ "                                                                               \n"
					+ "                     Su nombre de usuario es : " + usuario + "                 \n"
					+ "                   	Su clave de acceso es:   " + contraseña + "               \n"
					+ "                     Fecha: " + fecha() + "                                    \n"
					+ "                                                                               \n"
					+ "-------------------------------------------------------------------------------\n";
			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
			cuentaDeAhorroDAO.insert(c);
		}

	}

	public CuentaDeAhorro buscarCuentaDeAhorro(String numeroCuentaDeAhorro) {
		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.read(numeroCuentaDeAhorro);
		return cuentaDeAhorro;
	}

	public CuentaDeAhorro buscarCuentaDeAhorroCliente(String cedulaCliente) {
		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.getCuentaCedulaCliente(cedulaCliente);
		return cuentaDeAhorro;

	}

	public void eliminarCuentaDeAhorro(String numeroCuentaDeAhorro) {
		cuentaDeAhorroDAO.delete(numeroCuentaDeAhorro);
	}

	public void actualizarCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
		cuentaDeAhorroDAO.update(cuentaDeAhorro);
	}

	public List<CuentaDeAhorro> listaCuentaDeAhorros() {
		List<CuentaDeAhorro> clientes = cuentaDeAhorroDAO.getCuentaDeAhorros();
		return clientes;
	}

	// SE VALIDA EL GUARDAR SESSION LOGIN VALIDANDO LOS CAMPOS
	public void guardarSesion(SesionCliente sesionCliente) {
		Cliente cli = sesionCliente.getCliente();
		String destinatario = cli.getCorreo();
		if (sesionCliente.getEstado().equalsIgnoreCase("Incorrecto") && cli.getEstado().equalsIgnoreCase("C")) {

			contf = contf + 1;
			if (contf < 3) {

				// A quien le quieres escribir.

				String asunto = "INICIO DE SESION FALLIDA";
				String cuerpo = "BANCA VIRTUAL                                             SISTEMA TRANSACCIONAL\n"
						+ "-------------------------------------------------------------------------------\n"
						+ "        Estimado(a): " + cli.getNombre().toUpperCase() + " "
						+ cli.getApellido().toUpperCase() + "\n"
						+ "-------------------------------------------------------------------------------\n"
						+ "BANCA VIRTUAL le informa que el acceso a su cuenta ha sido fallida en la fecha.\n"
						+ "  		Fecha: " + obtenerFecha(sesionCliente.getFechaSesion()) + "\n"
						+ "                                                                               \n"
						+ "-------------------------------------------------------------------------------\n";
				CompletableFuture.runAsync(() -> {
					try {
						enviarCorreo(destinatario, asunto, cuerpo);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

			} else if (contf == 3) {
				String asunto1 = "AVISO DE BLOQUEO DE CUENTA";
				String cuerpo1 = "BANCA VIRTUAL                                             SISTEMA TRANSACCIONAL\n"
						+ "-------------------------------------------------------------------------------\n"
						+ "        Estimado(a): " + cli.getNombre().toUpperCase() + " "
						+ cli.getApellido().toUpperCase() + "\n"
						+ "-------------------------------------------------------------------------------\n"
						+ "BANCA VIRTUAL le informa que el el la cuenta del cliente se ha bloqueado, solicitar recuperar cuenta o acercarse   ventanilla.\n"
						+ "  		Fecha: " + obtenerFecha(sesionCliente.getFechaSesion()) + "\n"
						+ "                                                                               \n"
						+ "-------------------------------------------------------------------------------\n";
				CompletableFuture.runAsync(() -> {
					try {
						enviarCorreo(destinatario, asunto1, cuerpo1);
						cli.setEstado("B");
						clienteDAO.update(cli);
					} catch (Exception e) {
						e.printStackTrace();
					}
				});

				contf = 0;

			}

		} else if (sesionCliente.getEstado().equalsIgnoreCase("Correcto") && cli.getEstado().equalsIgnoreCase("C")) {
			// A quien le quieres escribir.

			contf = 0;
			String asunto = "INICIO DE SESION CORRECTA";
			String cuerpo = "BANCA VIRTUAL                                             SISTEMA TRANSACCIONAL\n"
					+ "-------------------------------------------------------------------------------\n"
					+ "        Estimado(a): " + cli.getNombre().toUpperCase() + " " + cli.getApellido().toUpperCase()
					+ "\n" + "-------------------------------------------------------------------------------\n"
					+ "BANCA VIRTUAL le informa que el acceso a su cuenta ha sido correcta en la fecha.\n"
					+ "  		Fecha: " + obtenerFecha(sesionCliente.getFechaSesion()) + "\n"
					+ "                                                                               \n"
					+ "-------------------------------------------------------------------------------\n";

			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});

		}
		if (sesionCliente.getEstado().equalsIgnoreCase("Correcto") && cli.getEstado().equalsIgnoreCase("B")) {
			// SE EMVIA UN MENSAJE PARA DECIR QUE EL CLIENTE TIENE LA CUENTA BLOQUEADA
			contf = 0;
			String asunto = "SU CUENTA SE ENCUENTRA BLOQUEADA";
			String cuerpo = "BANCA VIRTUAL                                             SISTEMA TRANSACCIONAL\n"
					+ "-------------------------------------------------------------------------------\n"
					+ "        Estimado(a): " + cli.getNombre().toUpperCase() + " " + cli.getApellido().toUpperCase()
					+ "\n" + "-------------------------------------------------------------------------------\n"
					+ "BANCA VIRTUAL le informa que su cuenta se encuetra bloueada, porfavor acercarse a ventanilla.\n"
					+ "  		Fecha: " + obtenerFecha(sesionCliente.getFechaSesion()) + "\n"
					+ "                                                                               \n"
					+ "-------------------------------------------------------------------------------\n";

			CompletableFuture.runAsync(() -> {
				try {
					enviarCorreo(destinatario, asunto, cuerpo);
				} catch (Exception e) {
					e.printStackTrace();
				}
			});
		}

		sesionClienteDAO.insert(sesionCliente);

	}

	/**
	 * Metodo que permite buscar una Sesion
	 * 
	 * @param codigoSesionCliente Codigo de la sesion que se desea buscar
	 * @return Sesion obtenida de la busqueda
	 */

	public SesionCliente buscarSesionCliente(int codigoSesionCliente) {
		return sesionClienteDAO.read(codigoSesionCliente);
	}

	public List<SesionCliente> obtenerSesionesCliente(String cedulaCliente) {
		try {
			return sesionClienteDAO.obtenerSesionCliente(cedulaCliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean validadorDeCedula(String cedula) throws Exception {
		System.out.println(cedula + "    En Metodo ");
		boolean cedulaCorrecta = false;
		try {
			if (cedula.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula.substring(2, 3));
				if (tercerDigito < 6) {
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula.length() - 1); i++) {
						digito = Integer.parseInt(cedula.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}
					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			cedulaCorrecta = false;
			throw new Exception("Error cedula");
		}
		if (!cedulaCorrecta) {
			return cedulaCorrecta;

		}
		return cedulaCorrecta;
	}

	public void guardarEmpleado(Empleado empleado) throws SQLException, Exception {

		if (!validadorDeCedula(empleado.getCedula())) {
			throw new Exception("Cedula Incorrecta");
		} else {

			try {
				empleadoDAO.insertarEmpleado(empleado);
			} catch (Exception e) {
				throw new Exception(e.toString());
			}
		}
	}

	public Empleado usuarioRegistrado(String cedula) {
		return empleadoDAO.obtenerEmpleado(cedula);
	}

	public List<Empleado> listadoEmpleados() {
		return empleadoDAO.obtener();
	}

	public Empleado usuario(String usuario, String contra) throws Exception {
		try {
			Empleado em = empleadoDAO.obtenerUsuario(usuario, contra);
			if (em != null) {
				return em;
			}
		} catch (NoResultException e) {
			throw new Exception("Credenciales Incorrectas");
		}
		return null;

	}

	public List<Transaccion> listadeTransacciones(String cedula) {
		try {
			return transaccionDAO.getListaTransacciones(cedula);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	public void guardarTransaccion(Transaccion t) throws Exception {

		try {
			transaccionDAO.insert(t);
		} catch (Exception e) {
			throw new Exception(e.toString());
		}
	}

	public List<Transaccion> obtenerTransaccionesFechaHora(String cedula, String fechaI, String fechaF) {
		String fechaInicio = fechaI + " 00:00:00.000000";
		String fechaFinal = fechaF + " 23:59:59.000000";
		try {
			return transaccionDAO.getListaTransaccionesFechas(cedula, fechaInicio, fechaFinal);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public String realizarTransaccion(String cuenta, double monto, String tipoTransaccion) {
		CuentaDeAhorro clp = cuentaDeAhorroDAO.read(cuenta);
		if (clp != null) {
			if (tipoTransaccion.equalsIgnoreCase("deposito")) {
				Double nvmonto = clp.getSaldoCuentaDeAhorro() + monto;
				clp.setSaldoCuentaDeAhorro(nvmonto);
//				actualizarCuentaDeAhorro(clp);
				Transaccion t = new Transaccion();
				t.setCliente(clp.getCliente());
				t.setMonto(monto);
				t.setFecha(new Date());
				t.setTipo("deposito");
				t.setSaldoCuenta(nvmonto);
				try {
					// editable = false;
					guardarTransaccion(t);
					return "Hecho";
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			} else if (tipoTransaccion.equalsIgnoreCase("retiro") && monto <= clp.getSaldoCuentaDeAhorro()) {
				Double nvmonto2 = clp.getSaldoCuentaDeAhorro() - monto;
				clp.setSaldoCuentaDeAhorro(nvmonto2);
//				actualizarCuentaDeAhorro(clp);
				Transaccion t2 = new Transaccion();
				t2.setCliente(clp.getCliente());
				t2.setMonto(monto);
				t2.setFecha(new Date());
				t2.setTipo("retiro");
				t2.setSaldoCuenta(nvmonto2);
				try {
					guardarTransaccion(t2);
					return "Hecho";
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.getMessage();
				}
			} else {
				return "Monto exedido";
			}
		} else {
			return "Cuenta Inexistente";
		}
		return "Fallido";
	}

	public void guardarTransferenciaLocal(TransferenciaLocal transferenciaLocal) {
		transferenciaLocalDAO.insert(transferenciaLocal);
	}

	/**
	 * Metodo que permite dar acceso al cliente en la aplicación móvil mediante un
	 * servicio web.
	 * 
	 * @param username El nombre de usuario del cliente que se envio en el correo.
	 * @param password La contraseña del cliente que se envio en el correo de
	 *                 creación de la cuenta.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso,
	 *         con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
	public Respuesta loginServicio(String username, String password) {
		Cliente cliente = new Cliente();
		Respuesta respuesta = new Respuesta();
		CuentaDeAhorro cuentaDeAhorro = new CuentaDeAhorro();
		// List<Credito> lstCreditos = new ArrayList<Credito>();
		try {
			cliente = clienteDAO.obtenerClienteUsuarioContraseña(username, password);
			if (cliente != null) {
				respuesta.setCodigo(1);
				respuesta.setDescripcion("Ha ingresado exitosamente");
				respuesta.setCliente(cliente);
				cuentaDeAhorro = cuentaDeAhorroDAO.getCuentaCedulaCliente(cliente.getCedula());
				respuesta.setCuentaDeAhorro(cuentaDeAhorro);
				/*
				 * lstCreditos = creditosAprovados(cliente.getCedula()); List<CreditoRespuesta>
				 * lstNuevaCreditos = new ArrayList<CreditoRespuesta>(); for (Credito credito :
				 * lstCreditos) { CreditoRespuesta creditoRespuesta = new CreditoRespuesta();
				 * creditoRespuesta.setCodigoCredito(credito.getCodigoCredito());
				 * creditoRespuesta.setEstado(credito.getEstado());
				 * creditoRespuesta.setMonto(credito.getMonto());
				 * creditoRespuesta.setInteres(credito.getInteres());
				 * creditoRespuesta.setFechaRegistro(credito.getFechaRegistro());
				 * creditoRespuesta.setFechaVencimiento(credito.getFechaVencimiento());
				 * creditoRespuesta.setDetalles(credito.getDetalles());
				 * lstNuevaCreditos.add(creditoRespuesta); }
				 * respuesta.setListaCreditos(lstNuevaCreditos);
				 */
			}
		} catch (Exception e) {
			respuesta.setCodigo(2);
			respuesta.setDescripcion("Error " + e.getMessage());
		}
		return respuesta;
	}

	/**
	 * Metodo que permite obtener un cliente para el proceso de transacciones o
	 * transferencias.
	 * 
	 * @param numeroCuenta El numero de cuenta de la persona a la que se hace la
	 *                     transaccion o transferencia.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso,
	 *         con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
	public Respuesta obtenerClienteCuentaAhorro(String numeroCuenta) {
		Respuesta respuesta = new Respuesta();
		CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.read(numeroCuenta);
		try {
			if (cuentaDeAhorro != null) {
				respuesta.setCodigo(1);
				respuesta.setDescripcion("Se ha obtenido la cuenta exitosamente");
				respuesta.setCuentaDeAhorro(cuentaDeAhorro);
			} else {
				respuesta.setCodigo(2);
				respuesta.setDescripcion("La Cuenta de Ahorro no existe");
			}
		} catch (Exception e) {
			respuesta.setCodigo(3);
			respuesta.setDescripcion("Error " + e.getMessage());
		}
		return respuesta;
	}

	/**
	 * Metodo que permite cambiar la contraseña del cliente en la aplicación móvil
	 * mediante un servicio web.
	 * 
	 * @param correo        El correo del cliente que describio cuando creo una
	 *                      cuenta de ahorros.
	 * @param contraAntigua La contraseña del cliente antigua.
	 * @param contraActual  La contraseña del cliente nueva.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso,
	 *         con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */

	public Respuesta cambioContraseña(String correo, String contraAntigua, String contraActual) {
		System.out.println(correo + "" + contraAntigua);
		Cliente cliente = new Cliente();
		Respuesta respuesta = new Respuesta();
		try {
			cliente = clienteDAO.obtenerClienteCorreoContraseña(correo, contraAntigua);
			System.out.println(cliente.toString());
			cliente.setClave(contraActual);
			clienteDAO.update(cliente);
			respuesta.setCodigo(1);
			respuesta.setDescripcion("Se ha actualizado su contraseña exitosamente");
			cambioContrasena(cliente);
		} catch (Exception e) {
			respuesta.setCodigo(2);
			respuesta.setDescripcion("Error " + e.getMessage());
		}

		return respuesta;
	}

	/**
	 * Metodo que permite realizar una transferencia
	 * 
	 * @param cedula        Numero de cedula de la persona que hace la
	 *                      transferencia.
	 * @param cuentaAhorro2 El numero de cuenta de la persona a la que se hace la
	 *                      transferencia.
	 * @param monto         El valor de la transferencia.
	 * @return Un clase Respuesta indicando los datos del desarrollo del proceso,
	 *         con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
	public Respuesta realizarTransferencia(String cedula, String cuentaAhorro2, double monto) {
		Respuesta respuesta = new Respuesta();
		CuentaDeAhorro cuentaAhorro = cuentaDeAhorroDAO.getCuentaCedulaCliente(cedula);
		CuentaDeAhorro cuentaAhorroTransferir = cuentaDeAhorroDAO.read(cuentaAhorro2);
		try {
			if (cuentaAhorro.getSaldoCuentaDeAhorro() >= monto) {
				cuentaAhorro.setSaldoCuentaDeAhorro(cuentaAhorro.getSaldoCuentaDeAhorro() - monto);
				actualizarCuentaDeAhorro(cuentaAhorro);
				cuentaAhorroTransferir.setSaldoCuentaDeAhorro(cuentaAhorroTransferir.getSaldoCuentaDeAhorro() + monto);
				actualizarCuentaDeAhorro(cuentaAhorroTransferir);
				TransferenciaLocal transfereciaLocal = new TransferenciaLocal();
				transfereciaLocal.setCliente(cuentaAhorro.getCliente());
				transfereciaLocal.setCuentaDeAhorroDestino(cuentaAhorroTransferir);
				transfereciaLocal.setMonto(monto);
				guardarTransferenciaLocal(transfereciaLocal);
				respuesta.setCodigo(1);
				respuesta.setDescripcion("Transferencia Satisfactoria");
			} else {
				respuesta.setCodigo(2);
				respuesta.setDescripcion("Monto Excedido");
			}
		} catch (Exception e) {
			respuesta.setCodigo(3);
			respuesta.setDescripcion(e.getMessage());
		}
		return respuesta;
	}

	/**
	 * Método que permite realizar una transferencia externa en la aplicación móvil
	 * mediante un servicio web.
	 * 
	 * @param transferenciaExterna Una clase TransferenciaExterna que se envia en
	 *                             formato json mediante el servicio web.
	 * @return Un clase RespuestaTransferenciaExterna indicando los datos del
	 *         desarrollo del proceso, con un codigo, una descripción.
	 * @throws Exception Excepción por si sucede algún error en el proceso.
	 */
	public RespuestaTransferenciaExterna realizarTransferenciaExterna(TransferenciaExterna transferenciaExterna) {
		RespuestaTransferenciaExterna respuestaTransferenciaExterna = new RespuestaTransferenciaExterna();
		try {
			CuentaDeAhorro cuentaDeAhorro = cuentaDeAhorroDAO.read(transferenciaExterna.getCuentaPersonaLocal());
			if (cuentaDeAhorro != null) {
				if (cuentaDeAhorro.getSaldoCuentaDeAhorro() >= transferenciaExterna.getMontoTransferencia()) {
					transferenciaExterna.setFechaTransaccion(new Date());
					transferenciaExternaDAO.insert(transferenciaExterna);
					cuentaDeAhorro.setSaldoCuentaDeAhorro(
							cuentaDeAhorro.getSaldoCuentaDeAhorro() - transferenciaExterna.getMontoTransferencia());
					cuentaDeAhorroDAO.update(cuentaDeAhorro);
					respuestaTransferenciaExterna.setCodigo(1);
					respuestaTransferenciaExterna.setDescripcion("Transferencia se ha realizado exitosamente");
				} else {
					respuestaTransferenciaExterna.setCodigo(2);
					respuestaTransferenciaExterna.setDescripcion("No tiene esa cantidad en su cuenta");
				}
			} else {
				respuestaTransferenciaExterna.setCodigo(3);
				respuestaTransferenciaExterna.setDescripcion("La cuenta no existe");
			}
		} catch (Exception e) {
			respuestaTransferenciaExterna.setCodigo(4);
			respuestaTransferenciaExterna.setDescripcion("Error : " + e.getMessage());
		}
		return respuestaTransferenciaExterna;
	}

	/**
	 * Metodo que permite indicar los datos para enviar mediante el correo el
	 * mensaje de cambio de contraseña.
	 * 
	 * @param cliente Una clase Cliente con los datos del cliente.
	 * @throws Exception Excepción por si sucede algún error en el proceso de envio.
	 */
	public void cambioContrasena(Cliente cliente) {
		String destinatario = cliente.getCorreo();
		String asunto = "CAMBIO DE CONTRASEÑA";
		String cuerpo = "JAMVirtual                                               SISTEMA TRANSACCIONAL\n"
				+ "------------------------------------------------------------------------------\n"
				+ "              Estimado(a): " + cliente.getNombre().toUpperCase() + "          "
				+ cliente.getApellido().toUpperCase() + "\n"
				+ "------------------------------------------------------------------------------\n"
				+ "COOPERATIVA JAM le informa que su contraseña ha sido cambiada exitosamente.   \n"
				+ "                                                                              \n"
				+ "                   Su nueva contraseña es:   " + cliente.getClave() + "       \n"
				+ "                       Fecha: " + fecha() + "                                 \n"
				+ "                                                                              \n"
				+ "------------------------------------------------------------------------------\n";
		CompletableFuture.runAsync(() -> {
			try {
				enviarCorreo(destinatario, asunto, cuerpo);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});

//			} 
	}

	public String getDatos() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://35.238.98.31:8000/apiAnalisis/verDiagrama/");
		String res = target.request().get(String.class);
		client.close();
		return res;
	}

	public double valorDecimalCr(double valor) {
		String num = String.format(Locale.ROOT, "%.2f", valor);
		return Double.parseDouble(num);
	}

	public String fecha() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		return hourdateFormat.format(date);
	}

	public List<Poliza> listasPolizas() {
		return polizaDAO.getPolizas();
	}

	@Override
	public List<Poliza> getListasPolizas() {
		// TODO Auto-generated method stub
		return polizaDAO.getPolizas();
	}

	@Override
	public void desbloquear(Cliente cliente) {
		Cliente cli = cliente;
		cli.setEstado("C");
		clienteDAO.update(cli);
		// TODO Auto-generated method stub

	}
}
