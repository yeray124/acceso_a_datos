package practica7_8.practica7_8;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Practica78Application {

	public static void main(String[] args) {
		SpringApplication.run(Practica78Application.class, args);
		
		
		Videojuego nuevoVideojuego = new Videojuego();
		nuevoVideojuego.setNombre("The Legend of Zelda");
		nuevoVideojuego.setGenero("Aventura");
		nuevoVideojuego.setEdad(12);

		VideojuegoService.InsertarVideojuego(nuevoVideojuego);

	}

}
