package paquete;
import java.io.File;

public class Ejercicio1 {

	public static void main(String[] args) {
		 
		File fichero = new File("C:\\Users\\AlumnoT\\Desktop\\acceso_a_datos\\Uno\\uno");
		
		fichero.mkdir();
		
		String[] Semana = { "Lunes","Martes","Miercoles","Jueves","Viernes","Sabado", "Domingo"};
		 
		fichero.mkdir();
		
		for (String dia : Semana) {
            File auxiliar = new File("C:\\Users\\AlumnoT\\Desktop\\acceso_a_datos\\Uno\\uno\\" + dia);

            auxiliar.mkdir();
            
            
            File destino = new File(fichero, dia); 
            
            
            if(!destino.exists()) {
            	destino.mkdir();
            	System.out.println("La carpeta se ha creado ya que no existia");
            }
            
            if(!auxiliar.exists()) {
            	auxiliar.mkdir();
            	System.out.println("La carpeta se ha creado ya que no existia");
            }
            	auxiliar.renameTo(destino);
        }
    }
}