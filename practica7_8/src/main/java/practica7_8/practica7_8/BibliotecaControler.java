package practica7_8.practica7_8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/biblioteca")
public class BibliotecaControler {
	
	@Autowired
	private VideojuegoService videojuegoService;
	
	
	@Autowired
	private BibliotecaService bibliotecaService;
	

	@PostMapping
	public ResponseEntity<?> InsertarVideojuego(@RequestBody BibliotecaJuegos biblioteca, @RequestParam Long id){
		Videojuego juego = videojuegoService.ObtenerVideojuego(id);
		
		if (juego==null) {
            return new ResponseEntity<>("Pelicula no encontrada", HttpStatus.NOT_FOUND);
        } else {
        	biblioteca.setVideojuego(juego);
        	Long idBiblioteca = bibliotecaService.InsertarBiblioteca(biblioteca);
        	return new ResponseEntity<>(id, HttpStatus.CREATED);
        }
	}
	
	
	@PutMapping("/biblioteca/{id}/duracion")
	public ResponseEntity<Void> ActualizarBiblioteca (@PathVariable Long id, @RequestParam String Creador, @RequestParam int Ventas, @RequestParam  int Duracion){
		
		bibliotecaService.ActualizarBiblioteca(id, Creador, Ventas, Duracion);
		if (bibliotecaService != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	} 
}