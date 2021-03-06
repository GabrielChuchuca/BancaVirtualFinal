package ec.edu.ups.BancaVirtualFinal.modelo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author ADMINX
 *
 */
@Entity
public class Poliza implements Serializable { 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) 

	@Column(name="idp")
	private int idp;
	private int diasMaximo;
	private int diasMinimo;
	private double tasaInteres;

	private static final long serialVersionUID = 1L;

	public Poliza() {
	}

	public int getIdp() {
		return idp;
	}

	public void setIdp(int idp) {
		this.idp = idp;
	}

	public int getDiasMaximo() {
		return diasMaximo;
	}

	public void setDiasMaximo(int diasMaximo) {
		this.diasMaximo = diasMaximo;
	}

	public int getDiasMinimo() {
		return diasMinimo;
	}

	public void setDiasMinimo(int diasMinimo) {
		this.diasMinimo = diasMinimo;
	}

	public double getTasaInteres() {
		return tasaInteres;
	}

	public void setTasaInteres(double tasaInteres) {
		this.tasaInteres = tasaInteres;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "Poliza [idp=" + idp + ", diasMaximo=" + diasMaximo + ", diasMinimo=" + diasMinimo + ", tasaInteres="
				+ tasaInteres + "]";
	}
 
	
	

}
