package ec.edu.ups.BancaVirtualFinal.vistas;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.MoveEvent;

import ec.edu.ups.BancaVirtualFinal.modelo.CuentaDeAhorro;
import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;
import ec.edu.ups.BancaVirtualFinal.modelo.SolicitudPoliza;
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
	//private Double interes;
	//private Double valorp;
	//private int dias;
	private List<Poliza> listasPolizas;
	private SolicitudPoliza solicitudPoliza;
	private String numeroc;
	private InputStream arCedula;
	private InputStream arPlanillaServicios;
	private CuentaDeAhorro cuenta;

	@PostConstruct
	public void init() {
		poliza = new Poliza();
		//dias = 0;
		//interes = 0.0;
		//valorp= 0.0;
		cuenta = new CuentaDeAhorro();
		loadData();
	}
	 
	

	/**
	 * @return the cuenta
	 */
	public CuentaDeAhorro getCuenta() {
		return cuenta;
	}



	/**
	 * @param cuenta the cuenta to set
	 */
	public void setCuenta(CuentaDeAhorro cuenta) {
		this.cuenta = cuenta;
	}




	public void archivo1(FileUploadEvent event) throws IOException {
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		arCedula = event.getFile().getInputStream();
	}
	/** 
	 * Metodo que me permite asignar un archivo al atributo de tipo InputStream arPlanillaServicios de la clase
	 * @param event Variable de tipo FileUploadEvent
	 * @throws IOException
	 */
	public void archivo2(FileUploadEvent event) throws IOException {
		FacesMessage msg = new FacesMessage("Successful", event.getFile().getFileName() + " is uploaded.");
		FacesContext.getCurrentInstance().addMessage(null, msg);
		arPlanillaServicios = event.getFile().getInputStream();
	}




	public InputStream getArCedula() {
		return arCedula;
	}


	public void setArCedula(InputStream arCedula) {
		this.arCedula = arCedula;
	}


	public SolicitudPoliza getSolicitudPoliza() {
		return solicitudPoliza;
	}


	public void setSolicitudPoliza(SolicitudPoliza solicitudPoliza) {
		this.solicitudPoliza = solicitudPoliza;
	}


	public String getNumeroc() {
		return numeroc;
	}


	public void setNumeroc(String numeroc) {
		this.numeroc = numeroc;
	}


	public InputStream getArPlanillaServicios() {
		return arPlanillaServicios;
	}


	public void setArPlanillaServicios(InputStream arPlanillaServicios) {
		this.arPlanillaServicios = arPlanillaServicios;
	}




	public void setListaEmpleados(List<Poliza> listaEmpleados) {
		this.listasPolizas = listaEmpleados;
	}

	public GestionUsuarioLocal getPolizaON() {
		return polizaON;
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

	//public String poInteres() {
//
	//	try {
		//	Poliza c;
			//c = polizaON.guardaringresodias(dias);
			//String tasa = String.valueOf(c.getTasaInteres());
			//return tasa;

		//} catch (Exception e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		//}
		//return null;

	//}

	// METODO PARA OBTENER
	//public void obtenerInteres() {
	//	Poliza po;
	//	try {
	//		po = polizaON.guardaringresodias(dias);
	//		interes = po.getTasaInteres();
	//	} catch (Exception e) {
	//		// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}

	//}

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
