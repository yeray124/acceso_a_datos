package practica7_8.practica7_8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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
	public ResponseEntity<?> InsertarVideojuego(@RequestBody BibliotecaJuegos biblioteca, @RequestParam Long id, @RequestParam String creador, @RequestParam int ventas, @RequestParam int duracion) {
	    Videojuego juego = videojuegoService.ObtenerVideojuego(id);
	    if (juego == null) {
	        return new ResponseEntity<>("Videojuego no encontrado", HttpStatus.NOT_FOUND);
	    } else {
	        biblioteca.setVideojuego(juego);
	        biblioteca.setCreador(creador);
	        biblioteca.setVentas(ventas);
	        biblioteca.setDuracion(duracion);
	        Long idBiblioteca = bibliotecaService.InsertarBiblioteca(biblioteca);
	        return new ResponseEntity<>(idBiblioteca, HttpStatus.CREATED);
	    }
	}
	
	@PutMapping("/{id}/duracion")
	public ResponseEntity<?> ActualizarBiblioteca(@PathVariable Long id, @RequestParam int duracion){
		boolean actualizado = bibliotecaService.ActualizarBiblioteca(id, duracion);
		
		if (actualizado) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	 @PutMapping("/{id}/videojuego/{id_videojuego}")
	public ResponseEntity<String> ActualizarVideojuegoEnBiblioteca(@PathVariable Long id, @PathVariable Long id_videojuego) {

	    BibliotecaJuegos existeBiblio = bibliotecaService.ObtenerBiblioteca(id);
	    if (existeBiblio == null) {
	        return new ResponseEntity<>("No se encontro la biblioteca con ID " + id, HttpStatus.NOT_FOUND);
	    }
	    boolean actualizado = bibliotecaService.ActualizarClaveExterna(id, id_videojuego);
	    if (actualizado) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("Error al actualizar el videojuego en la biblioteca.", HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> BorrarBibliotecaPorId(@PathVariable Long id) {
	    
	    BibliotecaJuegos biblioteca = bibliotecaService.ObtenerBiblioteca(id);
	    if (biblioteca == null) {
	        return new ResponseEntity<>("No se encontro la biblioteca con id " + id, HttpStatus.NOT_FOUND);
	    }
	    
	    boolean eliminado = bibliotecaService.BorrarBiblioteca(biblioteca);
	    
	    if (eliminado) {
	        return new ResponseEntity<>(HttpStatus.OK);
	    } else {
	        return new ResponseEntity<>("No se pudo borrar la biblioteca con id " + id, HttpStatus.NOT_FOUND);
	    }
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> ObtenerBibliotecaPorId(@PathVariable Long id) {
	    BibliotecaJuegos biblioteca = bibliotecaService.ObtenerBiblioteca(id);
	    
	    if (biblioteca == null) {
	        return new ResponseEntity<>("No se encontro la biblioteca con el ID " + id, HttpStatus.NOT_FOUND);
	    }
	    return new ResponseEntity<>(biblioteca, HttpStatus.OK);
	}
	
	@GetMapping("/creador/{id}")
	public ResponseEntity<?> obtenerCreadorPorId(@PathVariable Long id) {
	    BibliotecaJuegos biblioteca = bibliotecaService.ObtenerBiblioteca(id);
	    
	    if (biblioteca == null) {
	        return new ResponseEntity<>("No se encontroo la biblioteca con el ID " + id, HttpStatus.NOT_FOUND);
	    }

	    String creador = biblioteca.getCreador();
	    return new ResponseEntity<>(creador, HttpStatus.OK);
	}
}