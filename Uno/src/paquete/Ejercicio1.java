package paquete;
import java.io.File;

public class Ejercicio1 {

	public static void main(String[] args) {
		 
		File fichero = new File("cine_granada");
		
		fichero.mkdir();
		
		String[] Semana = { "Lunes","Martes","Miercoles","Jueves","Viernes","Sabado", "Domingo"};
		 
		fichero.mkdir();
		
		for (String dia : Semana) {
            File auxiliar = new File(dia); 
            
            auxiliar.mkdir();
            
            File destino = new File(fichero, dia); 

            if (auxiliar.renameTo(destino)) {
                System.out.println("Directorio '" + dia + "' movido a 'cine_granada'.");
            } else {
                System.out.println("No se pudo mover el directorio '" + dia + "'.");
            }
        }
    }
}