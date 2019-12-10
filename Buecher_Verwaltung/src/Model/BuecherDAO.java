package Model;

import java.io.IOException;

/**
 * Data Access Object für die Klasse Bucher.
 */
public class BuecherDAO  extends DAO {
	
	
	/**
	 * Konstruktor um das Data Access Object mit einem Dateinamen zu initialisieren.
	 * 
	 * @param dateiName Dateiname
	 * @param openForWrite true wenn geschrieben werden soll
	 */
	public BuecherDAO (String dateiName, boolean openForWrite) {

		super (dateiName, openForWrite);
	}
	
	
	
	public void write (Object obj) throws IOException {
		
		if (out != null) {
			
			Buecher Buecher = (Buecher)obj;
			// Anzahl Buecher speichern:
			out.writeInt(Buecher.size());
			System.out.println("speischer10000"+Buecher.size());
			// Nun die einzelnen Buecher speichern:
			BuchDAO BuecherDAO = new BuchDAO (null, out);
			//ForEach schleife
			for (Buch s: Buecher) {
				
				BuecherDAO.write(s);
				System.out.println("speischer"+s);
			}
		}
	}
	
	/**
	 * Neues Buecher-Objekt erzeugen und Daten aus Datei lesen.
	 */	
	public void read (Object obj) throws IOException {
		
		if (in != null) {
			
			Buecher Buecher = (Buecher)obj;
			
			// Anzahl der Buecher lesen:
			int nBuecher = in.readInt();
			
			// Nun die einzelnen Buecher lesen:
			BuchDAO BuecherDAO = new BuchDAO (in, null);
		
			for (int i=0; i<nBuecher; ++i) {
			 Buch s = new Buch();
			 BuecherDAO.read(s);
			 System.out.println("open"+s);
			 Buecher.add(s);
			 System.out.println("add open"+s);
			}
		}
	}
	
}
