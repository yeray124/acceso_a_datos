package practica7_8.practica7_8;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;

@Entity
@Table(name="Biblioteca")
public class BibliotecaJuegos {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "id_videojuego", referencedColumnName = "id")
	
	private  Videojuego videojuego;
	
	
	private String Creador;
	private int Duracion, Ventas;

	public BibliotecaJuegos() {}
	
	public BibliotecaJuegos(String creador, int ventas, int duracion, Videojuego videojuego) {
		this.Creador = creador;
		this.Ventas = ventas;
		this.Duracion = duracion;
		this.videojuego = videojuego; 
	}
	
	public Videojuego getVideojuego() {
		return videojuego;
	}

	public void setVideojuego(Videojuego videojuego) {
		this.videojuego = videojuego;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCreador() {
		return Creador;
	}

	public void setCreador(String creador) {
		Creador = creador;
	}

	public int getDuracion() {
		return Duracion;
	}

	public void setDuracion(int duracion2) {
		Duracion = duracion2;
	}


	public int getVentas() {
		return Ventas;
	}

	public void setVentas(int ventas) {
		Ventas = ventas;
	}
}