/**
 * 
 */
package ec.edu.ups.BancaVirtualFinal.modelo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Gabriel Leonardo Chu
 *
 */

@Entity
public class TransferenciaExterna {
	@Id   
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codigo_transferncia_externa")
	private int codigoTransferenciaExterna;   
	@Column(name="fecha_transaccion") 
	private Date fechaTransaccion;
	@Column(name="monto_transferencia")
	private double montoTransferencia;  
	@Column(name="nombre_Institucion")
	private String nombreInstitucionExterna; 
	@Column(name="cuenta_local")
	private String cuentaPersonaLocal;    
	@Column(name="cuenta_persona_externa") 
	private String cuentaPersonaExterna;
	@Column(name="nombre_persona")
	private String nombrePersonaExterna; 
	@Column(name="apellido_persona") 
	private String apellidoPersonaExterna;
	/**
	 * @return the codigoTransferenciaExterna
	 */
	public int getCodigoTransferenciaExterna() {
		return codigoTransferenciaExterna;
	}
	/**
	 * @param codigoTransferenciaExterna the codigoTransferenciaExterna to set
	 */
	public void setCodigoTransferenciaExterna(int codigoTransferenciaExterna) {
		this.codigoTransferenciaExterna = codigoTransferenciaExterna;
	}
	/**
	 * @return the fechaTransaccion
	 */
	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}
	/**
	 * @param fechaTransaccion the fechaTransaccion to set
	 */
	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}
	/**
	 * @return the montoTransferencia
	 */
	public double getMontoTransferencia() {
		return montoTransferencia;
	}
	/**
	 * @param montoTransferencia the montoTransferencia to set
	 */
	public void setMontoTransferencia(double montoTransferencia) {
		this.montoTransferencia = montoTransferencia;
	}
	/**
	 * @return the nombreInstitucionExterna
	 */
	public String getNombreInstitucionExterna() {
		return nombreInstitucionExterna;
	}
	/**
	 * @param nombreInstitucionExterna the nombreInstitucionExterna to set
	 */
	public void setNombreInstitucionExterna(String nombreInstitucionExterna) {
		this.nombreInstitucionExterna = nombreInstitucionExterna;
	}
	/**
	 * @return the cuentaPersonaLocal
	 */
	public String getCuentaPersonaLocal() {
		return cuentaPersonaLocal;
	}
	/**
	 * @param cuentaPersonaLocal the cuentaPersonaLocal to set
	 */
	public void setCuentaPersonaLocal(String cuentaPersonaLocal) {
		this.cuentaPersonaLocal = cuentaPersonaLocal;
	}
	/**
	 * @return the cuentaPersonaExterna
	 */
	public String getCuentaPersonaExterna() {
		return cuentaPersonaExterna;
	}
	/**
	 * @param cuentaPersonaExterna the cuentaPersonaExterna to set
	 */
	public void setCuentaPersonaExterna(String cuentaPersonaExterna) {
		this.cuentaPersonaExterna = cuentaPersonaExterna;
	}
	/**
	 * @return the nombrePersonaExterna
	 */
	public String getNombrePersonaExterna() {
		return nombrePersonaExterna;
	}
	/**
	 * @param nombrePersonaExterna the nombrePersonaExterna to set
	 */
	public void setNombrePersonaExterna(String nombrePersonaExterna) {
		this.nombrePersonaExterna = nombrePersonaExterna;
	}
	/**
	 * @return the apellidoPersonaExterna
	 */
	public String getApellidoPersonaExterna() {
		return apellidoPersonaExterna;
	}
	/**
	 * @param apellidoPersonaExterna the apellidoPersonaExterna to set
	 */
	public void setApellidoPersonaExterna(String apellidoPersonaExterna) {
		this.apellidoPersonaExterna = apellidoPersonaExterna;
	}
	
	
	

}
