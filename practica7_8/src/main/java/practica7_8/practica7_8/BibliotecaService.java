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
	
	public Long InsertarBiblioteca(BibliotecaJuegos biblioteca) {
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
		return biblioteca.getId();
	}
	
	public boolean BorrarBiblioteca(BibliotecaJuegos biblioteca) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    boolean eliminado = false;

	    try {
	        biblioteca = session.get(BibliotecaJuegos.class, biblioteca.getId()); 
	        if (biblioteca != null) {
	            transaction = session.beginTransaction();
	            session.remove(biblioteca);
	            transaction.commit();
	            eliminado = true;
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }
	    return eliminado;
	}

	public boolean ActualizarBiblioteca(Long id,int duracion ) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    boolean actualizado = false;

	    try {
	        BibliotecaJuegos biblioteca = session.get(BibliotecaJuegos.class, id);
	        if (biblioteca != null) {
	            biblioteca.setDuracion(duracion);
	            transaction = session.beginTransaction();
	            session.merge(biblioteca);
	            transaction.commit();
	            actualizado = true; 
	            System.out.println("Se ha actualizado la biblioteca creada por: " + biblioteca.getCreador());
	        } else {
	            System.out.println("No se encontro la biblioteca con el ID proporcionado.");
	        }
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return actualizado;  
	}
	
	public boolean ActualizarClaveExterna(Long id, Long id_videojuego) {
	    Session session = sessionFactory.openSession();
	    Transaction transaction = null;
	    boolean actualizado = false;

	    try {
	        BibliotecaJuegos biblioteca = session.get(BibliotecaJuegos.class, id);
	        if (biblioteca == null) {
	            return false; 
	        }
	        Videojuego videojuego = session.get(Videojuego.class, id_videojuego);
	        if (videojuego == null) {
	            return false;
	        }
	        biblioteca.setVideojuego(videojuego);
	        transaction = session.beginTransaction();
	        session.merge(biblioteca);
	        transaction.commit();
	        actualizado = true;
	    } catch (Exception e) {
	        if (transaction != null) {
	            transaction.rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        session.close();
	    }

	    return actualizado;
	}

	public BibliotecaJuegos ObtenerBiblioteca(Long id) {
		Session session = sessionFactory.openSession();		
		BibliotecaJuegos biblioteca = null;
		Transaction transaction = null;
		
		try {
			biblioteca = session.get(BibliotecaJuegos.class, id);
			transaction = session.beginTransaction();
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