package ec.edu.ups.BancaVirtualFinal.services;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;
import ec.edu.ups.BancaVirtualFinal.on.GestionUsuarioLocal;

@Path("clientes")
public class ClienteServiceRest {
	@Inject
	private GestionUsuarioLocal on;

	public String saludar(String nombre) {
		return nombre;
	}

	@GET
	@Path("listartransaccion")
	@Produces("application/json")
	public List<Transaccion> Tra(@QueryParam("cedula") String cedula) {
		return on.listadeTransacciones(cedula);
	}

	

	@GET
	@Produces("application/json")
	@Path("listarclientes")
	@Consumes("application/json")
	public List<Cliente> listarC() {
		return on.listaClientes();
	}

	@POST
	@Path("crearcliente")
	@Produces("application/json")
	@Consumes("application/json")
	public String Guardar(Cliente cliente) {
		Cliente c = cliente;

		try {
			on.guardarCliente(c);
			return "OK";

		} catch (Exception e) {
			e.printStackTrace();
			return c.toString();

			// TODO: handle exception
		}

	}

	@POST
	@Path("validCedula")

	public String vCedula(String ced) throws Exception {
		try {
			on.validadorDeCedula(ced);
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}

	}

}
