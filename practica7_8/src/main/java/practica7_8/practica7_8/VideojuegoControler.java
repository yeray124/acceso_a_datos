package practica7_8.practica7_8;

import java.util.List;

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
@RequestMapping("/videojuego")
public class VideojuegoControler {
	@Autowired
	private VideojuegoService videojuegoService;


	@PostMapping
	public ResponseEntity<Long> InsertarVideojuego (@RequestBody Videojuego videojuego){
		long id = videojuegoService.InsertarVideojuego(videojuego);
		
		return new ResponseEntity<>(id, HttpStatus.CREATED); 
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> ActualizarVideojuego (@PathVariable Long id, @RequestParam String Nombre, @RequestParam String Genero, @RequestParam  int Edad){
		
		videojuegoService.ActualizarVideojuego(id, Nombre, Genero, Edad);
		if (videojuegoService != null) {
			return new ResponseEntity<>(HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	    @GetMapping("/{id}")
	    public ResponseEntity<Videojuego> ObtenerVideojuego(@PathVariable Long id) {
	    	
	        Videojuego videojuego = videojuegoService.ObtenerVideojuego(id);
	        
	        if (videojuego != null) {
	            return new ResponseEntity<>(videojuego, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);	
	        }
	    }
	    
	    @GetMapping("/")
	    public ResponseEntity<List<Videojuego>> ObtenerTodosLosVideojuegos() {
	        List<Videojuego> videojuegos = videojuegoService.ObtenerTodosLosVideojuegos();
	        
	        if (videojuegos != null && !videojuegos.isEmpty()) {
	            return new ResponseEntity<>(videojuegos, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }
	    
	    @GetMapping("/edadMenorA/{edad}")
	    public ResponseEntity<List<Videojuego>> ObtenerVideojuegoConEdadMenorA(@PathVariable ("edad") int edad) {
	        List<Videojuego> videojuegos = videojuegoService.ObtenerVideojuegoConEdadMenorA(edad);
	        
	        if (videojuegos != null && !videojuegos.isEmpty()) {
	            return new ResponseEntity<>(videojuegos, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	        }
	    }
	    
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> BorrarVideojuegosId (@PathVariable ("id") Long id){
			
			boolean boleano = videojuegoService.BorrarVideojuegosId(id);
			
			if (boleano) {
				return new ResponseEntity<>(HttpStatus.OK);
			} else {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
		}
	    
	}