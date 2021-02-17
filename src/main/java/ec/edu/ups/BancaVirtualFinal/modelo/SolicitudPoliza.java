package ec.edu.ups.BancaVirtualFinal.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

import ec.edu.ups.BancaVirtualFinal.modelo.Cliente;
import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;

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

		@OneToOne
		@JoinColumn(name="cedula_cliente")
		private Cliente cliente; 
		@OneToOne
		@JoinColumn(name="idp")
		private Poliza poliza;
		private String estado;
		private int dias;
		private Double monto;
		
		
		
		@Lob 
		@Column(length=16777216)
		private  byte[] cedula;
		@Lob 
		@Column(length=16777216)
		private  byte[] planilla;
		
		public SolicitudPoliza() {
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



		/**
		 * @return the monto
		 */
		public Double getMonto() {
			return monto;
		}



		/**
		 * @param monto the monto to set
		 */
		public void setMonto(Double monto) {
			this.monto = monto;
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
		
		


		/**
		 * @return the cliente
		 */
		public Cliente getCliente() {
			return cliente;
		}



		/**
		 * @param cliente the cliente to set
		 */
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
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
