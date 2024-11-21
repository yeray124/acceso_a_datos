package practica7_8.practica7_8;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;

@Service
public class BibliotecaService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public BibliotecaService() {}
	
	public void InsertarBiblioteca(BibliotecaJuegos biblioteca) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.persist(biblioteca);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	
	public void BorrarBiblioteca(BibliotecaJuegos biblioteca) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.remove(biblioteca);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	
	public void ActualizarBiblioteca(Long id, String Creador, int Ventas, int Duracion) {
		Session session = sessionFactory.openSession();
		BibliotecaJuegos biblioteca = session.get(BibliotecaJuegos.class, id);
		biblioteca.setCreador(Creador);
		biblioteca.setVentas(Ventas);
		biblioteca.setDuracion(Duracion);
		
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.merge(biblioteca);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}		
	}
	
	public BibliotecaJuegos ObtenerBiblioteca(Long id) {
		Session session = sessionFactory.openSession();		
		BibliotecaJuegos biblioteca = null;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.get(BibliotecaJuegos.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return biblioteca;
	}
	
	
	public List<BibliotecaJuegos> ObtenerBibliotecaConEdadMenorA(int Duracion) {  
	    Session session = sessionFactory.openSession();
	    List<BibliotecaJuegos> biblioteca = null;

	    try {
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<BibliotecaJuegos> criteriaQuery = criteriaBuilder.createQuery(BibliotecaJuegos.class);
	        Root<BibliotecaJuegos> root = criteriaQuery.from(BibliotecaJuegos.class);
	        criteriaQuery.select(root).where(criteriaBuilder.lessThan(root.get("Duracion"), Duracion));
	        biblioteca = session.createQuery(criteriaQuery).getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return biblioteca;
	}
}