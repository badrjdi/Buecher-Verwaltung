package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Oberklasse für alle Data Access Object Klassen.
 *
 */
public abstract class DAO {

	protected DataInputStream in;
	protected DataOutputStream out;
	/**
	 * Konstruktor um das Data Access Object mit einem Dateinamen zu initialisieren.
	 * 
	 * @param dateiName Dateiname
	 * @param openForWrite true wenn geschrieben werden soll
	 */
	public DAO (String dateiName, boolean openForWrite) {
		

		try {
			if (openForWrite) {
			
				out =  new DataOutputStream (new FileOutputStream(dateiName));
			}
			else {
				
				in = new DataInputStream (new FileInputStream(dateiName));
			}
		}
		catch (IOException e) {
			System.out.println (e.getMessage());			
		}

	}

	/**
	 * Konstruktor um das Data Access Object mit bereits vorhandenen Streams zu initialisieren.
	 * 
	 * @param in InputStream oder null
	 * @param out OutputStream oder null
	 */
	public DAO (DataInputStream in, DataOutputStream out) {
		
		this.in = in;
		this.out = out;
	}
	
	/**
	 * Schliessen der zugeordneten Streams.
	 */
	public void close () {
		
		try {
			if (in != null) in.close();
			if (out != null) out.close();
		}
		catch (IOException e) {
				}
			}
		
	


	/**
	 * Schreiben der Daten eines Objekts.
	 * Muss von abgeleiteten Data Object Klassen implementiert werden.
  		* 
  		* @param obj Referenz auf Objekt das die Daten enthält.
  		* @throws IOException
  		*/
		public abstract void write (Object obj) throws IOException;

		/**
		 * Lesen der Daten eines Objekts.
		 * Muss von abgeleiteten Data Object Klassen implementiert werden.
		 * 
		 * @param obj Referenz auf Objekt das die Daten enthält.
		 * @throws IOException
		 */
		public abstract void read (Object obj) throws IOException;

		}

