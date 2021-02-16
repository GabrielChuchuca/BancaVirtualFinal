package ec.edu.ups.BancaVirtualFinal.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ec.edu.ups.BancaVirtualFinal.modelo.Poliza;

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
			String tl = "select s from Polia s where "+dia+">= s.diasminimo AND "+dia+" <= s.diasmaximo";
			try {
				String jpql = tl;
				Query q = em.createQuery(jpql, Poliza.class);
				return (Poliza)q.getSingleResult();
			} catch (NoResultException e) {
				throw new Exception("Erro Consultas Entre Fechas");
			}

		}
}
