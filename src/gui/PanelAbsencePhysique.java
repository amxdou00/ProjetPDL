package gui;

import java.awt.Rectangle;

import javax.swing.JPanel;

import model.Etudiant;

public class PanelAbsencePhysique extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * Create the panel.
	 */
	public PanelAbsencePhysique(Etudiant etudiant) {
		setBounds(new Rectangle(0, 0, 530, 451));
		setLayout(null);
	}

}
