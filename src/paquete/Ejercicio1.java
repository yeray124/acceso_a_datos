package paquete;
import java.io.File;

public class Ejercicio1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String directorio = "cine_granada";
		 
		File fichero = new File(directorio);
		
		fichero.mkdir();
	}
}