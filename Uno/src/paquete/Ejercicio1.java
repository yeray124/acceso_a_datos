package paquete;
import java.io.File;
import java.io.IOException;

public class Ejercicio1 {

	public static void main(String[] args) throws IOException {
		 
		File fichero = new File("cine_granada");
		fichero.mkdir();
		String[] Semana = { "Lunes","Martes","Miercoles","Jueves","Viernes","Sabado", "Domingo"};
		fichero.mkdir();
		
		for (String dia : Semana) {
            File auxiliar = new File("C:\\Users\\AlumnoT\\Desktop\\acceso_a_datos\\Uno\\cine_granada\\" + dia);

            auxiliar.mkdir();
            File destino = new File("cine_granada/" + dia);
            
            if(!destino.exists()) {
            	destino.mkdir();
            	 System.out.println("Directorio cine_granada creado correctamente: " + fichero.getAbsolutePath());
            }
            if(!auxiliar.exists()) {
            	auxiliar.mkdir();
            	 System.out.println("Directorio auxiliar " + dia + " creado correctamente: " + auxiliar.getAbsolutePath());
            }
            if (auxiliar.renameTo(destino)) {
            	System.out.println(destino.getAbsolutePath());
            	System.out.println(destino.getPath());
            } else {
            	System.out.println("No se pudo mover el directorio" + dia + "a cine_granada");
            } 
            
            File sesionFile = new File(destino, "sesiones.txt");
            sesionFile.createNewFile();
            System.out.println("Archivo 'sesiones.txt' creado correctamente en: " + sesionFile.getAbsolutePath());
        }
    }
}