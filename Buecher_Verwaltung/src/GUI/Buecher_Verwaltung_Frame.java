package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import javax.swing.plaf.ListUI;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter;
import javax.xml.soap.Text;
import Model.Buch;
import Model.Buecher;
import Model.BuecherDAO;

//igor.grossklaus@student.jade-hs.de
/**
 * Anwendungsfenster mit Men�s und einigen Button-Funktionen.
 * Klicken auf den Button "Hinzuf�gen". Mann kann hier einen neuen Buch-Objeckt
 * hinzuf�gen.(Funktion: Hinzuf�gen eines neues Buch-Objeckt) 
 * Klicken auf den Button "�ndern", der in der Liste stellt, kann man diesen Buch-Objecht �ndern.
 * (Funktion: �ndern eines vorhandenen Buch-Objecht) Ausw�hlen einen Buch-Objecht,der
 * in der Liste stellt.
 * Danach klicken auf den Button"L�schen", kann man diesen
 * Buch-Objecht l�schen. (Funktion: Entfernen eines vorhandenen Buch-Objecht)
 * Schreiben den ganzen Buch-Objecht und klicken auf den Button "Suchen", kann man
 * diesen Buch-Objecht suchen. Der gesuchte Buch-Objecht wird markiert.(Funktion:
 * Suchen eines Titels)
 *
 */
public class Buecher_Verwaltung_Frame extends JFrame implements ActionListener {
	/**
	 * Hier startet unsere Anwendung.
	 * 
	 * @param args Argumente der Befehlszeile, werden nicht ben�tigt.
	 */
	public static void main(String[] args) {
		/**
		 * Hier merken wir uns die Referenz des Anwendungsfensters in einer
		 * Klassenvariablen.
		 */
		new Buecher_Verwaltung_Frame();

	}

	Buecher buch = new Buecher(); // package Sichtbarkeit, damit GUIBuecherliste zugreifen kann
	// GUI-Elemente als Instanzvariablen:
	private GUI_Buecher_Liste guiListe = new GUI_Buecher_Liste(this);
	// GUI-Elemente als Instanzvariablen:

	private JTextField Identifikator = new JTextField(15);
    private JLabel lblIdentifikator = new JLabel("Identifikator");
	// Button "Hinzuf�gen", "�ndern" und "L�schen"
	private JButton Botten_Hinzufuegen = new JButton("  Hinzuf�gen  ");
	private JButton Botten_Aendern = new JButton("      �ndern      ");
	private JButton Botten_Loeschen = new JButton("     L�schen     ");

	// Button "Suchen" und "Schliessen" :
	private JTextField Suchen = new JTextField("Buchtitel eingeben", 15); // 15 Spalten
	private JButton Botten_Suchen = new JButton("Suchen");

	private JButton Botten_Schliessen = new JButton("Schlie�en");
	/**
	 * Men�leiste f�r das Anwendungsfenster.
	 */
	class MenuLeiste extends JMenuBar {

		/**
		 * Konstruktor initialisiert die Men�leiste.
		 */
		public MenuLeiste() {

			add(new DateiMenu());
		}

		/**
		 * Men� "Datei".
		 */
		class DateiMenu extends JMenu {

			/**
			 * Konstruktor initialisiert das Men�.
			 */
			public DateiMenu() {

				super("Datei"); // Konstruktur der SuperKlasse wird angerufen.

				add(new DateiOeffnenItem());// Hinzuf�grn eine neue DateiOeffenItem;
				addSeparator();
				add(new DateiSpeichernItem());// Hinzuf�grn eine neue DateiSpeichernItem;
			}
			/**
			 * Men�eintrag f�r "Datei �ffnen"
			 */
			class DateiOeffnenItem extends JMenuItem implements ActionListener {

				/**
				 * Konstruktor initialisiert den Men�eintrag.
				 */
				public DateiOeffnenItem() {
					super("�ffnen"); // Verbinden �ffnen-MenuItem mit MeunItem.
					addActionListener(this); // Reaktion auf Klicken
				}
				/**
				 * Reagieren auf Klick.
				 */
				public void actionPerformed(ActionEvent e) {
					// Standarddialog fc zum Ausw�hlen einer Datei:
					JFileChooser fc = new JFileChooser();
					//wenn der Dialog geschlossen wird .
					//wird der Wert der Ausw�hlen Datei in der Variable returnValue geschpeischert.  
					int returnValue = fc.showOpenDialog(Buecher_Verwaltung_Frame.this);
					if (returnValue == JFileChooser.OPEN_DIALOG) {
						File file = fc.getSelectedFile();
						String file2 = file.toString();
						BuecherDAO dao = new BuecherDAO(file2, false);
						try {
							dao.read(buch);
							dao.close();
						} catch (IOException ex) {
							JOptionPane.showMessageDialog(null, "Datei Format ist nicht richtig", "Fehler", JOptionPane.WARNING_MESSAGE);
							
						}
						updateGUI();
					}
				}
			}
			/**
			 * Men�eintrag f�r "Datei Speichern"
			 */
			class DateiSpeichernItem extends JMenuItem implements ActionListener {
				/**
				 * Konstruktor initialisiert den Men�eintrag.
				 */
				public DateiSpeichernItem() {
					super("Speichern unter");
					addActionListener(this);
				}
				/**
				 * Reagieren auf Klick.
				 */
				public void actionPerformed(ActionEvent e) {
					// Standarddialog zum Ausw�hlen einer Datei:
					JFileChooser fc = new JFileChooser();
					int returnValue = fc.showSaveDialog(Buecher_Verwaltung_Frame.this);
					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File file = fc.getSelectedFile();
						System.out.println("File wird geschrieben"+file);
						String fileName = file.toString();
						System.out.println("File wird genanat"+fileName);
						BuecherDAO dao = new BuecherDAO(fileName, true);
						
						try {
							dao.write(buch);
							System.out.println("buch"+fileName);
							System.out.println("File wird writeen"+fileName);
							dao.close();
						} catch (IOException ex) {
							
						}
					}
				}
			}

		}

	}

	/**
	 * // Layout festlegen und GUI-Elemente hinzufugen (BorderLayout und FlowLayout)
	 */
	// Am SouthPanel gibt es zwei Button "Hinzufuegen" , "Aendern" und "Loeschen"
	// und (FlowLayout) genutzt wird.
	class SouthPanel extends JPanel {

		class Link extends JPanel {

			/**
			 * Layout in Link entwerfen.
			 */
			public Link() {
				setLayout(new FlowLayout(FlowLayout.LEFT));

			}
		}

		class Center extends JPanel {
			/**
			 * Layout in Center entwerfen.
			 */
			public Center() {
				setLayout(new FlowLayout(FlowLayout.CENTER));
				add(Botten_Suchen);
				add(Suchen);
			}
		}

		class Recht extends JPanel {
			/**
			 * Layout in recht entwerfen.
			 */
			public Recht() {
				setLayout(new FlowLayout(FlowLayout.RIGHT));
				add(Botten_Schliessen);
			}
		}

		/**
		 * Layout in SouthPanel entwerfen.
		 */
		public SouthPanel() {
			setLayout(new GridLayout(0, 3, 2, 2));

			add(new Link());
			add(new Center());
			add(new Recht());

		}

	}
	/**
	 * Konstruktor initialisiert Anwendungsfenster.
	 */
	class EasthPanel extends Box {
		/**
		 * Layout in EasthPanel entwerfen.
		 */
		class ErsteLinie extends JPanel {
			/**
			 * Layout in ErsteLinie entwerfen.
			 */
			public ErsteLinie() {

			}
		}

		class ZweiteLinie extends JPanel {
			/**
			 * Layout in ZweiteLinie entwerfen.
			 */
			public ZweiteLinie() {

			}
		}

		class DritteLinie extends JPanel {
			/**
			 * Layout in DritteLinie entwerfen.
			 */
			public DritteLinie() {

			}
		}

		class VierteLinie extends JPanel {
			/**
			 * Layout in VierteLinie entwerfen.
			 */
			public VierteLinie() {
				setLayout(new FlowLayout(FlowLayout.CENTER));
				add(Botten_Hinzufuegen);
			}
		}

		class FuenfteLinie extends JPanel {
			/**
			 * Layout in FuenfteLinie entwerfen.
			 */
			public FuenfteLinie() {
				setLayout(new FlowLayout(FlowLayout.CENTER));
				add(Botten_Aendern);

			}
		}

		class SechsteLinie extends JPanel {
			/**
			 * Layout in SechsteLinie entwerfen.
			 */
			public SechsteLinie() {
				setLayout(new FlowLayout(FlowLayout.CENTER));
				add(Botten_Loeschen);
			}
		}

		class SiebteLinie extends JPanel {
			/**
			 * Layout in SiebteLinie entwerfen.
			 */
			public SiebteLinie() {

			}
		}

		class AchteLinie extends JPanel {
			/**
			 * Layout in AchteLinie entwerfen.
			 */
			public AchteLinie() {

			}
		}

		class NeunteLinie extends JPanel {
			/**
			 * Layout in NeunteLinie entwerfen.
			 */
			public NeunteLinie() {

			}
		}

		/**
		 * BoxLayout in EasthPanel entwerfen.
		 */
		public EasthPanel() {
			// hier habe ich BoxLayout benutzt , umd die compenent unter einander zu ordnen
			super(BoxLayout.Y_AXIS);
			add(new ErsteLinie());
			add(new ZweiteLinie());
			add(new DritteLinie());
			add(new VierteLinie());
			add(new FuenfteLinie());
			add(new SechsteLinie());
			add(new SiebteLinie());
			add(new AchteLinie());
			add(new NeunteLinie());

		}
	}

	public Buecher_Verwaltung_Frame() {

		// Reaktion beim Schlie�en des Fensters festlegen (statt WindowEventHandler):
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Men�zeile installieren:
		setJMenuBar(new MenuLeiste());
		// add (guiListe);

		// Buecherdatenliste in GUI-Liste �bertragen:
		guiListe.setBuecher(buch);

		// Layout setzen und GUI-Elemente hinzuf�gen:
		setLayout(new BorderLayout());
		add(new SouthPanel(), BorderLayout.SOUTH);
		add(guiListe, BorderLayout.CENTER);
		add(new EasthPanel(), BorderLayout.EAST);

		// Event-Handler installieren:
		Botten_Hinzufuegen.addActionListener(this);
		Botten_Aendern.addActionListener(this);
		Botten_Loeschen.addActionListener(this);
		Botten_Suchen.addActionListener(this);
		Botten_Schliessen.addActionListener(this);

		this.setTitle("Bucher Verwaltung");
		this.setSize(900, 550); // Breite: 900 Pixel; Hoehe: 550 Pixel
		this.setLocation(100, 50); // Koordination: x=100, y=50;
		setVisible(true); // Fensterrahmen sichtbar machen
		this.setResizable(true);// verschiben und reseisen

	}

	/**
	 * Klicken auf Button "Hinzuf�gen" auswerten:
	 */

	public void actionPerformed(ActionEvent e) {

		if (e.getActionCommand().equals("  Hinzuf�gen  ")) {
			// Neues Buch-Objekt erzeugen:
			Buch m = new Buch();
			// Dialog zum Bearbeite des neuen Objekts zeigen:
			BuchDialog dlg = new BuchDialog(this, m);

			if (dlg.closedOK) {
				// Neuen Buecher hinzuf�gen:
				buch.addBuch(m);
				// GUI-Elemente mit Daten aktualisieren:
				updateGUI();
				

			}
		}

		/**
		 * Klicken auf Button "�ndern" auswerten:
		 */
		else if (e.getActionCommand().equals("      �ndern      ")) {
			// Buch-Objekt w�hlen:
			int[] items = guiListe.getSelectedIndices();
			System.out.println(items.length);

			// das ausw�hlende Buch-Objekt �ndern:
			int i = items[0];
			Buch m = buch.get(i);
			BuchDialog dlg = new BuchDialog(this, m);
			if (dlg.closedOK) {

				// GUI-Elemente mit Daten aktualisieren:
				updateGUI();
			}
		}

		/**
		 * Klicken auf Button "L�schen" auswerten:
		 */
		else if (e.getActionCommand().equals("     L�schen     ")) {

			// Buch-Objekt w�hlen:
			int[] items = guiListe.getSelectedIndices();
			//Indexes in dem Array items speichern
			
			System.out.println(items.length);
			System.out.println(Arrays.toString(items));
			int decrease = 0;

			// JOptionPane ,um die warnung vor das l�schen.
			int result = JOptionPane.showConfirmDialog(null, "Sie werden Ihre Buch l�schen.\nSind Sie sicher?",
					"Buch l�schen", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			switch (result) {
			case JOptionPane.YES_OPTION:
				// Mehre Buch-Objekt l�schen:
				
				for (int item : items) {
					int x=item - decrease;
					buch.remove(x);
					System.out.println("Durchlauf");
					System.out.printf("%d",x);
					decrease++;
				}
				break;
			case JOptionPane.NO_OPTION:

				break;
			case JOptionPane.CANCEL_OPTION:

				break;
			}

			// GUI-Elemente mit Daten aktualisieren:
			updateGUI();
		}

		/**
		 * Klicken auf Button "Suchen" auswerten:
		 */
		else if (e.getActionCommand().equals("Suchen")) {

			ListModel<String> model = guiListe.getModel();

			// erhalten die eingegebene Titel
			// public String trim(), benuztet man diese Methode, wenn  man
			// Leerzeichen am Anfang schreibt und am Ende keine Behinderung f�r Erhaltung des Titels
			String suchtext = Suchen.getText().trim();
			// erhalten Anzahl der Buchtitel
			int size = model.getSize();

			// Die ganze Buchtitel pr�fen einzeln.
			for (int i = 0; i < size; i++) {
				Object o = model.getElementAt(i);
				// Hier kann man pr�fen, ob es passende Buchtitel in guiListe gibt
				if (o.equals(suchtext)) {
					guiListe.setSelectedIndex(i);
					return;
				}
			}
			// Wenn keine passende Buchtitel gesucht wird, zeigt ein Dialog " Not found".
			
			guiListe.setSelectedIndex(-1);
			JOptionPane.showMessageDialog(this, "Nicht gefunden. ");
			return;
		}
		/**
		 * Klicken auf Button "Schliessen" auswerten:
		 */
		else if (e.getSource() == Botten_Schliessen) {
			// JOptionPane ,um die warnung vor das Fenster schliessen.
			int result = JOptionPane.showConfirmDialog(null, "Programm jetzt schlie�en?", "Warnung",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);

			switch (result) {
			case JOptionPane.YES_OPTION:
				System.exit(0);
				break;
			case JOptionPane.CANCEL_OPTION:

				break;
			}

		}

	}

	void updateGUI() {

		// Buecherdatenliste in GUI-Liste �bertragen:
		guiListe.setBuecher(buch);

		// Auswertung f�r Anzahl Buecher aktualisieren:

		lblIdentifikator.setText("" + buch.size());
	}

}
