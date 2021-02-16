package ec.edu.ups.BancaVirtualFinal.on;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;
import ec.edu.ups.BancaVirtualFinal.modelo.Empleado;
import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;
import ec.edu.ups.BancaVirtualFinal.modelo.SesionCliente;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;
/**
 * @author ADMINX
 *
 */

@Local
public interface GestionUsuarioLocal {
	public String generarNumeroDeCuenta();
	public String getUsuario(String cedula, String nombre, String apellido);
	public String getContraseña();
	public void enviarCorreo(String destinatario, String asunto, String cuerpo);
	public String fecha();
	public String obtenerFecha(Date fecha);
	public void guardarCliente(Cliente c);
	public Cliente buscarCliente(String cedulaCliente);
	public Cliente buscarClienteUsuarioContraseña(String usuario, String contraseña);
	public void eliminarCliente(String cedulaCliente);
	public void actualizarCliente(Cliente cliente);
	public List<Cliente> listaClientes();
	public void desbloquear(Cliente cliente);
	public List<Cliente> listaClientesBloqueados();
	public List<SesionCliente> obtenerSesionesCliente(String cedulaCliente);
	public void actualizarCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro);
	public List<Transaccion> listadeTransacciones(String cedula);
	public void guardarSesion(SesionCliente sesionCliente);
	public void eliminarCuentaDeAhorro(String numeroCuentaDeAhorro);
	public boolean validadorDeCedula(String cedula)throws Exception;
	public void guardarEmpleado(Empleado empleado)throws SQLException, Exception;
	public Empleado usuarioRegistrado(String cedula);
	public List<Empleado> listadoEmpleados();
	public Empleado usuario(String usuario,String contra)throws Exception ;
	public String getDatos();
	public void guardarCuentaDeAhorros(CuentaDeAhorro cuentaDeAhorro);
	public void guardarTransaccion(Transaccion transaccion) throws Exception;
	public CuentaDeAhorro buscarCuentaDeAhorroCliente(String cedulaCliente);
	public void guardarPoliza(Poliza p);
	public List<Poliza> listasPolizas();
	public List<Poliza> getListasPolizas();
	public Poliza guardaringresodias(int dias) throws Exception ;
	public String realizarTransaccion(String cuenta, double monto, String tipoTransaccion);
	public List<Transaccion> obtenerTransaccionesFechaHora(String cedula, String fechaI, String fechaF);
}
