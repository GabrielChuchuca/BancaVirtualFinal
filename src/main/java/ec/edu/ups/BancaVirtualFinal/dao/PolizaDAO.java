package ec.edu.ups.BancaVirtualFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;
import ec.edu.ups.BancaVirtualFinal.modelo.Transaccion;

/**
 * @author ADMINX
 *
 */
@Stateless
public class PolizaDAO {
	// Atributo de la clase
		@PersistenceContext(name = "BancaVirtualFinalPersistenceUnit")
		private EntityManager em;

		public void insert(Poliza p) {
			em.persist(p);
		}

		public void update(Poliza p) {
			em.merge(p);
		}

		public Poliza read(int id) {
			return em.find(Poliza.class, id);
		}
		
		public Poliza buscarpor(Double interes) {
			return em.find(Poliza.class, interes);
		}

		public void delete(int id) {
			Poliza p = read(id);
			em.remove(p);
		}

		public List<Poliza> getPolizas() {
			String jpql = "SELECT c FROM Poliza c ";

			Query q = em.createQuery(jpql, Poliza.class);
			return q.getResultList();
		} 
		
		public Poliza validardias(int dia) throws Exception { 

			System.out.println("VALOR DE INGRESO >> "+dia);
			System.out.println("********************************************************************INICIO DE METODO");
			String tl = "select po from poliza po where "+dia+" BETWEEN po.diasminimo AND po.diasmaximo";
			try {
				String jpql = tl;
				System.out.println("--------------------------------------------------------------------------"+jpql);
				//Query q = em.createQuery(jpql, Poliza.class);
				Query q= em.createNativeQuery(jpql, Poliza.class);
				//(Poliza)q.getSingleResult();
				return null;
			} catch (NoResultException e) {
				System.out.println("ERORRE--------------+++++++++++++++++");
				throw new Exception("Erro Consultas Entre Fechas");
			}

		}
		
		
}
