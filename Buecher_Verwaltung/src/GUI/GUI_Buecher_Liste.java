package GUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

import Model.Buch;
import Model.Buecher;

/**
 * GUI_Buecher_Liste erben von JList.
 */

public class GUI_Buecher_Liste extends JList<String> {

	
	private Buecher_Verwaltung_Frame frame;

	private DefaultListModel<String> myListModel = new DefaultListModel<String>();

	/* Hier definieren wir einen Eventhandler, um auf Mausereignisse zu reagieren.
	 */
	public class ListClickHandler extends MouseAdapter {

		public void mouseClicked(MouseEvent e) {

			// Doppelklick mit linker Taste auf Liste?
			if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
				// Ein vorhandener Student wird bearbeitet:
				int index = getSelectedIndex();
				System.out.println(index);
				if (index >= 0)	{

					Buch m = frame.buch.get(index);
					BuchDialog dlg = new BuchDialog (frame, m);
					if (dlg.closedOK) {
						  System.out.println("add Element mous"+m);
						frame.updateGUI();
					}
					
				}
			}
		}
	}


	public GUI_Buecher_Liste (Buecher_Verwaltung_Frame frame) {

		this.frame = frame;
		// Model und Liste verbinden:
		setModel (myListModel);
		// Maushandler verbinden, um auf Doppelklick zu reagieren:
		addMouseListener (new ListClickHandler());
	
				
	}


	public void setBuecher (Buecher buch) {

		// Initialisierung der Buecherliste
		myListModel.removeAllElements();
		 
		for (Buch m: buch) {

		  myListModel.addElement(m.getIdentifikator());
		  System.out.println("add Element"+m);

		//+"  "+m.getTitel() +"  "+ m.getVerkaufDatum()+"  "+m.getMenge()+"  "+m.getPreis()
		

	}
}
}



