package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;

import model.Enseignant;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EnseignantGUI {

	private JFrame frame;
	private PanelPlanningEnseignant panelPlanningEnseignant;
	private PanelFaireAppel panelFaireAppel;
	private PanelSyntheseAbsence panelSyntheseAbsence;
	
	
	public EnseignantGUI(Enseignant enseignant) {
		initialize(enseignant);
		
		panelPlanningEnseignant.setVisible(true);
		panelFaireAppel.setVisible(false);
		panelSyntheseAbsence.setVisible(false);
		
	}

	
	private void initialize(Enseignant enseignant) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panelPlanningEnseignant = new PanelPlanningEnseignant(enseignant);
		panelFaireAppel = new PanelFaireAppel(enseignant);
		panelSyntheseAbsence = new PanelSyntheseAbsence(enseignant);
		
		
		
		
		// Adding the side panel
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.RED);
		sidePanel.setBounds(0, 0, 189, 451);
		sidePanel.setLayout(null);
		frame.getContentPane().add(sidePanel);
		
		// Boutton Planning
		final JButton buttonPlanning = new JButton("Planning");
		buttonPlanning.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonPlanning.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonPlanning.setBackground(Color.red);
			}
		});
		buttonPlanning.setFocusable(false);
		buttonPlanning.setBorder(UIManager.getBorder("Button.border"));
		buttonPlanning.setBorderPainted(false);
		buttonPlanning.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanningEnseignant.setVisible(false);
				panelFaireAppel.setVisible(false);
				panelSyntheseAbsence.setVisible(false);
				
				panelPlanningEnseignant.setVisible(true);
			}
		});
		buttonPlanning.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonPlanning.setForeground(Color.WHITE);
		buttonPlanning.setBackground(Color.RED);
		buttonPlanning.setBounds(0, 120, 189, 50);
		sidePanel.add(buttonPlanning);
		
		// Boutton Faire Appel
		final JButton buttonFaireAppel = new JButton("Faire l'appel");
		buttonFaireAppel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonFaireAppel.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonFaireAppel.setBackground(Color.red);
			}
		});
		buttonFaireAppel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanningEnseignant.setVisible(false);
				panelFaireAppel.setVisible(false);
				panelSyntheseAbsence.setVisible(false);
				
				panelFaireAppel.setVisible(true);
			}
		});
		buttonFaireAppel.setFocusable(false);
		buttonFaireAppel.setBorderPainted(false);
		buttonFaireAppel.setForeground(Color.WHITE);
		buttonFaireAppel.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonFaireAppel.setBackground(Color.RED);
		buttonFaireAppel.setBounds(0, 170, 189, 50);
		sidePanel.add(buttonFaireAppel);
		
		final JButton buttonSyntheseAbsence = new JButton("Synthese Absence");
		buttonSyntheseAbsence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonSyntheseAbsence.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonSyntheseAbsence.setBackground(Color.red);
			}
		});
		buttonSyntheseAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanningEnseignant.setVisible(false);
				panelFaireAppel.setVisible(false);
				panelSyntheseAbsence.setVisible(false);
				
				panelSyntheseAbsence.setVisible(true);
			}
		});
		buttonSyntheseAbsence.setFocusable(false);
		buttonSyntheseAbsence.setBorderPainted(false);
		buttonSyntheseAbsence.setForeground(Color.WHITE);
		buttonSyntheseAbsence.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonSyntheseAbsence.setBackground(Color.RED);
		buttonSyntheseAbsence.setBounds(0, 220, 189, 50);
		sidePanel.add(buttonSyntheseAbsence);
		
		final JButton buttonAnticiperAbsence_1_1 = new JButton("Déconnexion");
		buttonAnticiperAbsence_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAnticiperAbsence_1_1.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonAnticiperAbsence_1_1.setBackground(Color.red);
			}
		});
		buttonAnticiperAbsence_1_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				LoginPage l = new LoginPage();
				frame.dispose();
			}
		});
		buttonAnticiperAbsence_1_1.setForeground(Color.WHITE);
		buttonAnticiperAbsence_1_1.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonAnticiperAbsence_1_1.setFocusable(false);
		buttonAnticiperAbsence_1_1.setBorderPainted(false);
		buttonAnticiperAbsence_1_1.setBackground(Color.RED);
		buttonAnticiperAbsence_1_1.setBounds(0, 271, 189, 50);
		sidePanel.add(buttonAnticiperAbsence_1_1);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(190, 0, 530, 451);
		mainPanel.setLayout(null);
		mainPanel.add(panelPlanningEnseignant);
		mainPanel.add(panelFaireAppel);
		mainPanel.add(panelSyntheseAbsence);
		frame.getContentPane().add(mainPanel);
		
	}
}
