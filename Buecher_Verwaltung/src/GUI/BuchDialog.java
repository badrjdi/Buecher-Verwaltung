package GUI;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


import Model.Buch;

public class BuchDialog extends JDialog implements ActionListener {
	public boolean closedOK = false; // true, wenn Dialog durch OK beendet wird

	// Referenz auf das Model-Objekt als Datenquelle:
	private Buch myBuch;

	// GUI-Elemente als Instanzvariablen:

	private JTextField Identifikator = new JTextField(15);
	private JLabel lblIdentifikator = new JLabel("Identifikator                            ");

	private JTextField Titel = new JTextField(15); // 15 Spalten
	private JLabel lblTitel = new JLabel("Titel                                           ");

	private JTextField VerkaufDatum = new JTextField(15); // 15 Spalten
	private JLabel lblVerkaufDatum = new JLabel("Verkauf Datum                       ");

	// Aufzählung mehrerer Sprachen
	private String wahl;
	private String[] texte = { "Bitte auswaehlen Sie", "Arabisch", "Französich", "Deutsch", "Englisch" };

	// ComboBox hinzufügen "Sprache"
	private JComboBox<String> Sprache = new JComboBox<String>(texte);
	private JLabel lblSprache = new JLabel("      Sprache                                           ");

	// Checkbox hinzufügen " neue "
	private JRadioButton Neue = new JRadioButton(" Neue ");
	
	// Checkbox hinzufügen "Gebrauch"
		private JRadioButton Gebraucht = new JRadioButton(" Gebraucht ");
	
	// ButtonGroup hinzufügen 
	private ButtonGroup group = new ButtonGroup();
	
	// GUI-Elemente als Instanzvariablen:
	private JTextField Menge = new JTextField(15); // 15 Spalten
	private JLabel lblMenge = new JLabel("Menge                   ");

	private JTextField Preis = new JTextField(12); // 12 Spalten
	private JLabel lblPreis = new JLabel("Preis                      ");
	private JLabel lblEinheit = new JLabel("Euro ");

	//Button "Ok" und "Abbrechen
	private JButton ok = new JButton("OK");
	private JButton abbrechen = new JButton("Abbrechen");
	
	
	/**
	 * Konstruktor initialisiert den Dialog.
	 * Das Dialogfenster hat keine Daten, wenn ein neues Buch angelegt wird. In diesem
	 * Fall ist das übergebenen Buch-Objekt leer.
	 * Wird ein vorhandener Buch bearbeitet, werden dessen Daten zu Initialisierung
	 * der GUI-Elemente verwendet.
	 */

	public BuchDialog(Window parent, Buch m) {
		//Buecher verwaltung  fenster fest machen und nur auf die dialog zugreifen koennen.
	super (parent, "Daten bearbeiten", Dialog.DEFAULT_MODALITY_TYPE);

		myBuch = m;
		
		// Daten aus Buchsobjekt übernehmen:
		Identifikator.setText(m.getIdentifikator());
		Titel.setText(m.getTitel());
		VerkaufDatum.setText(m.getVerkaufDatum());
		Sprache.setSelectedItem(""+ m.getSprache());
	
		//Entscheidung zwischen gebraucht oder neue
		if (myBuch.getGebraucht()) {
			
			Gebraucht.setSelected(true);
		}else{
			
			Neue.setSelected(true);
		}
		
		//Menge
		Menge.setText(Integer.toString(m.getMenge()));
		
		// Preis
		DecimalFormatSymbols dfs1 = DecimalFormatSymbols.getInstance();
		dfs1.setDecimalSeparator(','); // Dezimalkomma statt Punkt
		DecimalFormat df1 = new DecimalFormat("0.00", dfs1); // 2 Dezimalstellen
		Preis.setText(df1.format(m.getPreis()));

		/**
		 * // Layout festlegen und GUI-Elemente hinzufugen (BorderLayout und FlowLayout)
		 */

		class WestPanel extends Box {
			/**
			 * Layout in ErsteLinie entwerfen.
			 */

			class ErsteLinie extends JPanel {
				/**
				 * Layout in ThirdLine entwerfen.
				 */
				public ErsteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblIdentifikator);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Identifikator);
				}
			}

			class ZweiteLinie extends JPanel {
				/**
				 * Layout in ZweiteLinie entwerfen.
				 */
				public ZweiteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblTitel);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Titel);
				}
			}

			class DritteLinie extends JPanel {
				/**
				 * Layout in DritteLinie entwerfen.
				 */
				public DritteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblVerkaufDatum);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(VerkaufDatum);
				}
			}

			class VierteLinie extends JPanel {
				/**
				 * Layout in VierteLinie entwerfen.
				 */
				public VierteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblSprache);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Sprache);
				}
			}

			class FuenfteLinie extends JPanel {
				/**
				 * Layout in FuenfteLinie entwerfen.
				 */
				public FuenfteLinie() {
					
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(Neue);
					group.add(Neue); 
				}
			}

			class SechsteLinie extends JPanel {
				/**
				 * Layout in SechsteLinie entwerfen.
				 */
				public SechsteLinie() {

					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(Gebraucht);
					 group.add(Gebraucht); 
				}
			}

			class SiebteLinie extends JPanel {
				/**
				 * Layout in SiebteLinie entwerfen.
				 */
				public SiebteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblMenge);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Menge);
				}
			}

			class AchteLinie extends JPanel {
				/**
				 * Layout in chteLinie entwerfen.
				 */
				public AchteLinie() {
					setLayout(new FlowLayout(FlowLayout.LEFT));
					add(lblPreis);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(Preis);
					setLayout(new FlowLayout(FlowLayout.RIGHT));
					add(lblEinheit);
				}
			}


			/**
			 * BoxLayout in WestPanel entwerfen.
			 */
			public WestPanel() {

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

			}
		}

		// Am SouthPanel gibt es zwei Button "OK" und "Abbrechen" und FlowLayout genutzt
		// wird.
		class SouthPanel extends JPanel {

			/**
			 * Layout in SouthPanel entwerfen.
			 */
			public SouthPanel() {
				setLayout(new FlowLayout(FlowLayout.RIGHT));
				add(ok);
				add(abbrechen);
			}
		}
		class CenterPanel extends JPanel{
			
			public CenterPanel() {
				
				//BorderLayout wird benutzt und ein Bild hinzufügen
				setLayout(new BorderLayout());
				add(new JLabel(new ImageIcon("image\\Book1.jpg")),BorderLayout.EAST);
				
			}
		}
		// Layout setzen und GUI-Elemente hinzufügen:
		setLayout(new BorderLayout());
		add(new WestPanel(), BorderLayout.WEST);
		add(new SouthPanel(), BorderLayout.SOUTH);
		add(new CenterPanel(), BorderLayout.CENTER);

		// Event-Handler installieren:
		ok.addActionListener(this);
		abbrechen.addActionListener(this);

		this.setTitle("Neue Buch hinzufügen");
		this.setSize(620, 500); // Breite: 1200 Pixel; Hoehe: 550 Pixel
		this.setLocation(100,50); // Koordination: x=100, y=50;
		setVisible(true); // Fensterrahmen sichtbar machen
		pack(); // Optimale Groeße des Dialogfelds festlegen

		// verschiben und reseisen
		this.setResizable(true);

	}

	/**
	 * Klicken auf Button "Hinzufügen" auswerten:
	 */
	
	
	public void actionPerformed(ActionEvent e) {

		// Schaltfläche entnehmen:
		Object source = e.getSource();
		if (source == ok) {
			// Werte des Buch-Objekts entsprechend den Dialogelementen aktualisieren:

			myBuch.setIdentifikator(Identifikator.getText());
			myBuch.setTitel(Titel.getText());
			myBuch.setVerkaufDatum(VerkaufDatum.getText());
			myBuch.setSprache((String) Sprache.getSelectedItem());
			//Group box
			myBuch.setGebraucht(Gebraucht.isSelected());
			myBuch.setNeue(Neue.isSelected());
			
			/*
			 * try-catch-Anweisungen wird in einen Codeabschnitt Fehler (exceptions)
			 * abzufangen, sodass man darauf reagieren kann
			 */
			try {
				  // code der gesichert laeuft
				myBuch.setMenge(Integer.parseInt(Menge.getText()));
				}
				catch(Exception a) {
			     // Fehlerbehandlung
					JOptionPane.showMessageDialog(null, "Ungueltige Eingabe", "Error Message", JOptionPane.ERROR_MESSAGE);
				}
			
			
			// Preis hat Dezimalkomma.
			double Preise = 0; // Voreinstellung(Spielauer =0.0), falls Textumwandlung schief geht
			
			NumberFormat nf1 = NumberFormat.getInstance();
			try {
				Number sd = nf1.parse(Preis.getText());
				Preise = sd.doubleValue();

			} catch (ParseException ex) {

				JOptionPane.showMessageDialog(null, "Ungueltige Eingabe", "Error Message", JOptionPane.ERROR_MESSAGE);
			}
			myBuch.setPreis(Preise);
			// true,wenn Dialog durch Ok beendet wird.
			closedOK = true;
		}
		// Dialog schließen:
		setVisible(false);
	}

}