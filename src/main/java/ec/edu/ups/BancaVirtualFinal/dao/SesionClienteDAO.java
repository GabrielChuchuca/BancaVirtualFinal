package ec.edu.ups.BancaVirtualFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.BancaVirtualFinal.modelo.SesionCliente;

/**
 * @author ADMINX
 *
 */
@Stateless
public class SesionClienteDAO {
	//Atributo de la clase
		@PersistenceContext(name = "BancaVirtualFinalPersistenceUnit") 
		private EntityManager em;
		
		/** 
		 * Metodo que permite registrar una sesion en la base de datos
		 * @param s Sesion que se inserta en la base de datos
		 */
		public void insert(SesionCliente s) {
			em.persist(s);
		}

		/** 
		 * Metodo que permite actualizar una sesion en la base de datos
		 * @param s Sesion que se actualiza en la base
		 */
		public void update(SesionCliente s) {
			em.merge(s);
		}
		
		/** 
		 * Metodo que permite obtener una sesion de la base de datos
		 * @param codigoSesion Codigo de la sesion que se busca
		 * @return una sesion que este registrada en la base de datos
		 */
		public SesionCliente read(int codigoSesion) {
			return em.find(SesionCliente.class, codigoSesion);
		}
		
		
		public void delete(int codigoSesion) {
			SesionCliente c = read(codigoSesion);
			em.remove(c);
		}
		
		
		public List<SesionCliente> getSesionClientes() {
			String jpql = "SELECT s FROM SesionCliente s ";

			Query q = em.createQuery(jpql, SesionCliente.class);
			return q.getResultList();
		} 
		
		public List<SesionCliente> obtenerSesionCliente(String cedulaCliente) throws Exception { 
			try {
				String jpql = "SELECT s FROM SesionCliente s WHERE s.cliente.cedula = :cedulaCliente order by s.fechaSesion desc";
				Query q = em.createQuery(jpql, SesionCliente.class);  
				q.setParameter("cedulaCliente",cedulaCliente);
				return q.getResultList();
			} catch (Exception e) {
				throw new Exception("No ha ingresado ni una sola vez");
			}
			
		}
}
