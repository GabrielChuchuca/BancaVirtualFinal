package ec.edu.ups.BancaVirtualFinal.on;

import com.fasterxml.jackson.annotation.JsonProperty;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;

public class Respuesta {
	private int codigo;
	private String descripcion;
	private @JsonProperty("Cliente") Cliente cliente;
	private @JsonProperty("Cuenta") CuentaDeAhorro cuentaDeAhorro;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public CuentaDeAhorro getCuentaDeAhorro() {
		return cuentaDeAhorro;
	}

	public void setCuentaDeAhorro(CuentaDeAhorro cuentaDeAhorro) {
		this.cuentaDeAhorro = cuentaDeAhorro;
	}

}