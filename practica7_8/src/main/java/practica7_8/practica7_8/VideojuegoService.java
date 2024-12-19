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
public class VideojuegoService {
	
	@Autowired
	private SessionFactory sessionFactory;

	public VideojuegoService() {}
	
	public long InsertarVideojuego(Videojuego videojuego) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.persist(videojuego);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return videojuego.getId();
	}
	
	public void BorrarVideojuego(Videojuego videojuego) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.remove(videojuego);
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
	
	public void ActualizarVideojuego(Long id, String Nombre, String Genero, int Edad) {
		Session session = sessionFactory.openSession();
		Videojuego videojuego = session.get(Videojuego.class, id);
		videojuego.setNombre(Nombre);
		videojuego.setGenero(Genero);
		videojuego.setEdad(Edad);
		
		
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			session.merge(videojuego);
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
	
	public Videojuego ObtenerVideojuego(Long id) {
		Session session = sessionFactory.openSession();	
		
		Videojuego videojuego = null;
		Transaction transaction = null;
		
		try {
			transaction = session.beginTransaction();
			videojuego= session.get(Videojuego.class, id);
			transaction.commit();
		} catch (Exception e) {
			if (transaction != null){
				transaction.rollback();
			}
			e.printStackTrace();
		} finally {
			session.close();
		}
		return videojuego;
	}
	
	public List<Videojuego> ObtenerTodosLosVideojuegos() {  
	    Session session = sessionFactory.openSession();
	    List<Videojuego> videojuegos = null;

	    try {
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        
	        CriteriaQuery<Videojuego> criteriaQuery = criteriaBuilder.createQuery(Videojuego.class);
	        
	        Root<Videojuego> root = criteriaQuery.from(Videojuego.class);
	        
	        criteriaQuery.select(root);
	        
	        videojuegos = session.createQuery(criteriaQuery).getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return videojuegos;
	}
	
	public List<Videojuego> ObtenerVideojuegoConEdadMenorA(int edad) {  
	    Session session = sessionFactory.openSession();
	    List<Videojuego> videojuegos = null;

	    try {
	        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Videojuego> criteriaQuery = criteriaBuilder.createQuery(Videojuego.class);
	        Root<Videojuego> root = criteriaQuery.from(Videojuego.class);
	        criteriaQuery.select(root).where(criteriaBuilder.lessThan(root.get("Edad"), edad));
	        videojuegos = session.createQuery(criteriaQuery).getResultList();

	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return videojuegos;
	}
	
	public Boolean BorrarVideojuegosId(Long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        boolean deleted = false;
        
        try {
            transaction = session.beginTransaction();
            Videojuego videojuego = session.get(Videojuego.class, id);
            if(videojuego != null) {
                session.delete(videojuego);
                transaction.commit();
                deleted = true;
            } else {
            }
            
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return deleted;
    }
	
}