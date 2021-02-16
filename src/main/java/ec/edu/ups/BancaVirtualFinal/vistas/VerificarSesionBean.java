package ec.edu.ups.BancaVirtualFinal.vistas;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.Empleado;

/**
 * @author ADMINX
 *
 */
@ManagedBean
@ViewScoped
public class VerificarSesionBean implements Serializable {
	public void verificarSession() {
		try {
			FacesContext context = FacesContext.getCurrentInstance();
			Cliente cliente = (Cliente) context.getExternalContext().getSessionMap().get("cliente");

			if (cliente == null) {
				context.getExternalContext().redirect("LoginCliente.xhtml");
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public void verificarSessionAdmin(){
	        try {
	            FacesContext context = FacesContext.getCurrentInstance();
				Empleado  empleado= (Empleado) context.getExternalContext().getSessionMap().get("empleado");
	            if (empleado == null) {
	                context.getExternalContext().redirect("LoginEmpleado.xhtml");
	            }
	            
	        } catch (Exception e) {
	            System.out.println(e.getMessage());

	        } 
	}

}
