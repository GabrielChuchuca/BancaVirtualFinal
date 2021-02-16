package ec.edu.ups.BancaVirtualFinal.vistas;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.MoveEvent;

import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;
import ec.edu.ups.BancaVirtualFinal.on.GestionUsuarioLocal;

/**
 * @author ADMINX
 *
 */
@ManagedBean
@ViewScoped
public class PolizasBean {
	@Inject
	private GestionUsuarioLocal polizaON;

	private Poliza poliza;
	private Double interes;
	private int dias;

	private List<Poliza> listasPolizas;

	@PostConstruct
	public void init() {
		poliza = new Poliza();
		dias = 0;
		interes=0.0;
		loadData();
	}

	public void setListaEmpleados(List<Poliza> listaEmpleados) {
		this.listasPolizas = listaEmpleados;
	}

	public GestionUsuarioLocal getPolizaON() {
		return polizaON;
	}

	public int getDias() {
		return dias;
	}

	public void setDias(int dias) {
		this.dias = dias;
	}

	public void setPolizaON(GestionUsuarioLocal polizaON) {
		this.polizaON = polizaON;
	}

	public Poliza getPoliza() {
		return poliza;
	}

	public void setPoliza(Poliza poliza) {
		this.poliza = poliza;
	}

	public List<Poliza> getListasPolizas() {
		return listasPolizas;
	}

	public void setListasPolizas(List<Poliza> listasPolizas) {
		this.listasPolizas = listasPolizas;
	}

	public Double getInteres() {
		return interes;
	}

	public void setInteres(Double interes) {
		this.interes = interes;
	}

	public void addMessage(String summary, String detail) {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail));
	}

	public void handleClose(CloseEvent event) {
		addMessage(event.getComponent().getId() + " closed", "So you don't like nature?");
	}

	public void handleMove(MoveEvent event) {
		event.setTop(500);
		addMessage(event.getComponent().getId() + " moved", "Left: " + event.getLeft() + ", Top: " + event.getTop());
	}

	public String poInteres() {

		try {
			Poliza c;
			c = polizaON.guardaringresodias(dias);
			String tasa= String.valueOf(c.getTasaInteres());
			return tasa;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		return null;

	}

	// METODO PARA OBTENER
	public void obtenerInteres(){
		Poliza po;
		try {
			po = polizaON.guardaringresodias(dias);
			interes = po.getTasaInteres();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// METODO PARA GUARDAR POLIZAS
	public void guardarPoliza() {
		polizaON.guardarPoliza(poliza);
		System.out.println("Se muestra  la crear de la poliza desde Bean");
	}

	// METODO PARA LISTAR LAS POLIZAS
	public void loadData() {
		listasPolizas = polizaON.listasPolizas();
	}
}
