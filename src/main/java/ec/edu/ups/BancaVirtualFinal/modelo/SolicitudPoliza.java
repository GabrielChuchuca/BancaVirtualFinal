package ec.edu.ups.BancaVirtualFinal.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

/**
 * @author ADMINX
 *
 */
@Entity
public class SolicitudPoliza implements Serializable {
	//Atributos de la entidad
		private static  final long serialVersionUID = 1L;
		@Id 
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name="idsp")
		
		private int idsp; 
		private CuentaDeAhorro cuenta;
		private Poliza poliza;
		private String estado;
		@Lob 
		@Column(length=16777216)
		private  byte[] cedula;
		@Lob 
		@Column(length=16777216)
		private  byte[] planilla;
		
		public SolicitudPoliza() {
		}
		
	 

		public byte[] getCedula() {
			return cedula;
		}



		public void setCedula(byte[] cedula) {
			this.cedula = cedula;
		}



		public byte[] getPlanilla() {
			return planilla;
		}



		public void setPlanilla(byte[] planilla) {
			this.planilla = planilla;
		}



		public int getIdsp() {
			return idsp;
		}
		public void setIdsp(int idsp) {
			this.idsp = idsp;
		}
		public CuentaDeAhorro getCuenta() {
			return cuenta;
		}
		public void setCuenta(CuentaDeAhorro cuenta) {
			this.cuenta = cuenta;
		}
		public Poliza getPoliza() {
			return poliza;
		}
		public void setPoliza(Poliza poliza) {
			this.poliza = poliza;
		}
		public String getEstado() {
			return estado;
		}
		public void setEstado(String estado) {
			this.estado = estado;
		}
}
