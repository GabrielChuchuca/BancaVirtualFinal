package ec.edu.ups.BancaVirtualFinal.vistas;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import ec.edu.ups.BancaVirtualFinal.modelo.Empleado;
import ec.edu.ups.BancaVirtualFinal.on.GestionUsuarioLocal;

/**
 * @author Gabriel Leonardo Chu
 *
 */
@ManagedBean
@SessionScoped
public class LoginEmpleadoBeans {
	@Inject
	private GestionUsuarioLocal empleadoON;
	private Empleado empleado;
	private String usuario;
	private String contrasena;
	private boolean editable = false;
	private boolean editabledos = false;

	@PostConstruct
	public void init() {
		empleado = new Empleado();
	}

	public GestionUsuarioLocal getEmpleadoON() {
		return empleadoON;
	}

	public void setEmpleadoON(GestionUsuarioLocal empleadoON) {
		this.empleadoON = empleadoON;
	}

	/**
	 * Metodo para obtener el usuario
	 * 
	 * @return El usuario o valor digitado en la pagina xhtml
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * Metodo para asignar un usuario
	 * 
	 * @param usuario El parametro usuario me permite asignar valor a mi variable
	 *                usuario.
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * Metodo para obtener la contraseña
	 * 
	 * @return La contraseña que se ingresa en la pagina xhtml
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo para asignar una contraseña
	 * 
	 * @param contrasena El parametro contraseña me permite asignar un valor a mi
	 *                   variable contrasena
	 */
	public void setContrasena(String contrasena) {
		this.contrasena = contrasena;
	}

	/**
	 * Metodo para asiganr un booleano
	 * 
	 * @return Un boleano con TRUE O FALSE
	 */
	public boolean isEditable() {
		return editable;
	}

	/**
	 * Asignar el valor a editable
	 * 
	 * @param editable El parametro editable me permite asignar los valores de true
	 *                 o false
	 */
	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	/**
	 * Metodo para asiganr un booleano
	 * 
	 * @return Un boleano con TRUE O FALSE
	 */
	public boolean isEditabledos() {
		return editabledos;
	}

	/**
	 * Asignar el valor a editable
	 * 
	 * @param editabledos El parametro editabledos me permite asignar los valores de
	 *                    true o false
	 */
	public void setEditabledos(boolean editabledos) {
		this.editabledos = editabledos;
	}

	/**
	 * Metodo para obtener el empleado
	 * 
	 * @return
	 */
	public Empleado getEmpleado() {
		return empleado;
	}

	/**
	 * Asignar un empleado
	 * 
	 * @param empleado El parametro empleado me permite asignar el empleado que se
	 *                 encuentra conectado
	 */
	public void setEmpleado(Empleado empleado) {
		this.empleado = empleado;
	}

	/**
	 * Metodo para validar rol de Empleado
	 * 
	 * @return La pagina de acuerdo al rol del empleado
	 */
	public String validarUsuario() {
		Empleado emp;
		try {
			emp = empleadoON.usuario(usuario, contrasena);
			System.out.println("***************" + emp.getNombre());
			empleado = emp;
			if (emp != null && emp.getRol().equalsIgnoreCase("Cajero")) {
				try {
					addMessage("OK", "Ingreso");
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					// FacesContext contex2 = FacesContext.getCurrentInstance();
					FacesContext contex = FacesContext.getCurrentInstance();
					contex.getExternalContext().redirect("IndexCajero.xhtml");
				} catch (Exception e) {
				}
			} else if (emp != null && emp.getRol().equalsIgnoreCase("AsistenteDeCapacitaciones")) {
				try {
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					contex.getExternalContext().redirect("IndexAsistenteCapacitaciones.xhtml");
				} catch (Exception e) {
				}
			} else if (emp != null && emp.getRol().equalsIgnoreCase("Admin")) {
				try {
					FacesContext contex = FacesContext.getCurrentInstance();
					FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("empleado", emp);
					contex.getExternalContext().redirect("IndexAdmin.xhtml");
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			addMessage("ERROR", "NO SE PUEDO INGRESAR, REVISE USUARIO CONTRASEÑA");
			return "LoginEmpleado";
		}
		return null;
	}

	public void cambio() {
		editable = false;
		editabledos = true;
	}

	

	public void addMessage(String summary, String detail) {
		System.out.println(summary + "<<>>" + detail);
		
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "LoginEmpleado?faces-redirect=true";

	}
}
