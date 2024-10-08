package Paquete;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamTokenizer;

public class Ejercicio2 {

	public static void main(String[] args)  throws FileNotFoundException {
		StreamTokenizer recibir = new StreamTokenizer(
				 new FileInputStream("tema2_practica.txt"));
		
		try {
			while(recibir.nextToken() !=recibir.TT_EOF) {
				if (recibir.ttype == StreamTokenizer.TT_NUMBER){
					System.out.print("Numero: ");
					System.out.println((int)recibir.nval);
				}
			}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
