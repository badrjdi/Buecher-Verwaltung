package Model;

import java.util.ArrayList;

/**
 * Diese Klasse stellt eine Liste von Buecher dar.
 */
public class Buecher extends ArrayList<Buch> {

	/**
	 * Fügt einen neuen Buecher zur Liste hinzu.
	 * @param s Referenz auf Buch-Objekt.
	 */
	public void addBuch (Buch s) {
		
		super.add(s);
		 System.out.println("add in Arry"+s);
	}
	
}
