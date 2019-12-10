package Model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;




/**
 * Data Access Object für die Klasse Buch.
 */
public class BuchDAO extends DAO {

	
	/**
	 * Konstruktor um das Data Access Object mit einem Dateinamen zu initialisieren.
	 * 
	 * @param dateiName Dateiname
	 * @param openForWrite true wenn geschrieben werden soll
	 */
	public BuchDAO (String dateiName, boolean openForWrite) {
		
		super (dateiName, openForWrite);
	}
	
	
	/**
	 * Konstruktor um das Data Access Object mit bereits vorhandenen Streams zu initialisieren.
	 * 
	 * @param in InputStream oder null
	 * @param out OutputStream oder null
	 */
	public BuchDAO (DataInputStream in, DataOutputStream out) {
		
		super (in, out);
	}
	
	
	/**
	 * Daten des übergebenen Student-Objekts schreiben. Das Data Access Object muss dazu zum
	 * Schreiben bereit sein.
	 * 
	 * @param s Referenz auf Student-Objekt
	 * @throws IOException
	 */
	public void write (Object obj) throws IOException {
		
		if (out != null) {
			// Manual Upcasting 
			Buch s = (Buch)obj;
			
			out.writeUTF	 (s.getIdentifikator());
			out.writeUTF	 (s.getTitel());
			out.writeUTF     (s.getVerkaufDatum());
			out.writeUTF     (s.getSprache());
			out.writeBoolean (s.getGebraucht());
			out.writeBoolean (s.getNeue());
			out.writeInt     (s.getMenge());
			out.writeDouble	 (s.getPreis());
			System.out.println("reinstecken1"+s);
		}
	}
	
	
	/**
	 * Daten des übergebenen Student-Objekts lesen. Das Data Access Objekt muss dazu zum
	 * Lesen bereit sein.
	 * 
	 * @param s Referenz auf Student-Objekt
	 * @throws IOException
	 */
	public void read (Object obj) throws IOException {
		
		if (in != null) {
			
			Buch s = (Buch)obj;
			
			s.setIdentifikator (in.readUTF());
			s.setTitel         (in.readUTF());
			s.setVerkaufDatum  (in.readUTF());
			s.setSprache       (in.readUTF());
			s.setGebraucht     (in.readBoolean());
			s.setNeue          (in.readBoolean());
			s.setMenge         (in.readInt());
			s.setPreis         (in.readDouble());
			System.out.println("reinstecken"+s);
		}
	}
}



