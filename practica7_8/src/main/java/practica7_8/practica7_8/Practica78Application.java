package practica7_8.practica7_8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class Practica78Application {

	public static void main(String[] args) {
			ApplicationContext ctx = SpringApplication.run(Practica78Application.class, args);
			
			BibliotecaService biblioteca = ctx.getBean(BibliotecaService.class);
			VideojuegoService videojuego = ctx.getBean(VideojuegoService.class);
			
			// 2 Videojuegos insertados
			Videojuego nuevoVideojuego = new Videojuego();
			nuevoVideojuego.setNombre("The Legend of Zelda");
			nuevoVideojuego.setGenero("Aventura");
			nuevoVideojuego.setEdad(12);
			videojuego.InsertarVideojuego(nuevoVideojuego);
			System.out.println("Se ha insertado el videojuego: " + nuevoVideojuego.getNombre());
			
			Videojuego videojuego2 = new Videojuego();
	        videojuego2.setNombre("Super Mario Odyssey");
	        videojuego2.setGenero("Plataformas");
	        videojuego2.setEdad(7);
	        videojuego.InsertarVideojuego(videojuego2);
	        System.out.println("Se ha insertado el videojuego: " + videojuego2.getNombre());
	        
	        // Borrar el primer juego
	        Videojuego videojuegoParaBorrar = videojuego.ObtenerVideojuego(nuevoVideojuego.getId());
	        if (videojuegoParaBorrar != null) {
	            videojuego.BorrarVideojuego(videojuegoParaBorrar);
	            System.out.println("Se ha borrado el videojuego: " + nuevoVideojuego.getNombre());
	        } else {
	            System.out.println("El videojuego no existe en la base de datos.");
	        }

	        //Obtener por ID y mostrar por pantalla el videojuegooo
	        Videojuego videojuegoObtenido = videojuego.ObtenerVideojuego(videojuego2.getId());
	        if (videojuegoObtenido != null) {
	            System.out.println("Videojuego obtenido por ID: " + videojuegoObtenido.getNombre());
	        } else {
	            System.out.println("No se encontró el videojuego con el ID proporcionado.");
	        }

	        // Actualizar genero del videojuego
	        videojuego.ActualizarVideojuego(videojuego2.getId(), "Super Mario Odyssey", "Aventura espacial", videojuego2.getEdad());
	        System.out.println("Se ha actualizado el videojuego: " + videojuego2.getNombre() + " al género: Aventura espacial");
	        
	        //obtener videojuego con edad menor a 12
	        int edadMaxima = 12;
	        System.out.println("Videojuego con edad menor a " + edadMaxima + ":");
	        List<Videojuego> VideojuegoEdad = videojuego.ObtenerVideojuegoConEdadMenorA(edadMaxima);
	        for (Videojuego vj : VideojuegoEdad) {
	            System.out.println("Videojuego: " + vj.getNombre() + " | Edad: " + vj.getEdad());
	        }


	        // Insertamos 4 bibliotecas
	        
	    //    BibliotecaJuegos biblioteca1 = new BibliotecaJuegos();
	    //    biblioteca1.setCreador("Yeray Santiago");
	     //   biblioteca1.setVentas(1000);
	    //    biblioteca1.setDuracion(100);
	     //   biblioteca.InsertarBiblioteca(biblioteca1);
	     //   System.out.println("Se ha insertado la biblioteca creada por: " + biblioteca1.getCreador());

	     //   BibliotecaJuegos biblioteca2 = new BibliotecaJuegos();
	     //   biblioteca2.setCreador("Paquillo");
	     //   biblioteca2.setVentas(202);
	     //   biblioteca2.setDuracion(532);
	     //   biblioteca.InsertarBiblioteca(biblioteca2);
	     //   System.out.println("Se ha insertado la biblioteca creada por: " + biblioteca2.getCreador());


	}

}
