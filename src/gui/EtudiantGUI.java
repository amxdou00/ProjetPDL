package gui;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import model.Etudiant;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EtudiantGUI {

	private JFrame frame;

	private PanelPlanning panelPlanning;
	private PanelVoirAbsence panelVoirAbsence;
	private PanelDepotJustificatif panelDepotJustificatif;
	private PanelPlanifierAbsence panelPlanifierAbsence;
	private PanelMessages panelMessages;
	
	

	public EtudiantGUI(Etudiant etudiant) {
		initialize(etudiant);
		
		panelPlanning.setVisible(true);
		panelVoirAbsence.setVisible(false);
		panelDepotJustificatif.setVisible(false);
		panelPlanifierAbsence.setVisible(false);
		panelMessages.setVisible(false);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Etudiant etudiant) {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(100, 100, 720, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		panelPlanning = new PanelPlanning(etudiant);
		panelVoirAbsence = new PanelVoirAbsence(etudiant);
		panelDepotJustificatif = new PanelDepotJustificatif(etudiant);
		panelPlanifierAbsence = new PanelPlanifierAbsence(etudiant);
		panelMessages = new PanelMessages(etudiant);
		
		JPanel sidePanel = new JPanel();
		sidePanel.setBackground(Color.RED);
		sidePanel.setBounds(0, 0, 189, 451);
		
		frame.getContentPane().add(sidePanel);
		sidePanel.setLayout(null);
		
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
				panelPlanning.setVisible(false);
				panelVoirAbsence.setVisible(false);
				panelDepotJustificatif.setVisible(false);
				panelPlanifierAbsence.setVisible(false);
				panelMessages.setVisible(false);
				
				panelPlanning.setVisible(true);
			}
		});
		buttonPlanning.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonPlanning.setForeground(Color.WHITE);
		buttonPlanning.setBackground(Color.RED);
		buttonPlanning.setBounds(0, 120, 189, 50);
		sidePanel.add(buttonPlanning);
		
		final JButton buttonVoirAbsence = new JButton("Voir mes absences");
		buttonVoirAbsence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonVoirAbsence.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonVoirAbsence.setBackground(Color.red);
			}
		});
		buttonVoirAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanning.setVisible(false);
				panelVoirAbsence.setVisible(false);
				panelDepotJustificatif.setVisible(false);
				panelPlanifierAbsence.setVisible(false);
				panelMessages.setVisible(false);
				
				panelVoirAbsence.setVisible(true);
			}
		});
		buttonVoirAbsence.setFocusable(false);
		buttonVoirAbsence.setBorderPainted(false);
		buttonVoirAbsence.setForeground(Color.WHITE);
		buttonVoirAbsence.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonVoirAbsence.setBackground(Color.RED);
		buttonVoirAbsence.setBounds(0, 170, 189, 50);
		sidePanel.add(buttonVoirAbsence);
		
		final JButton buttonJustifierAbsence = new JButton("Justifier absence(s)");
		buttonJustifierAbsence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonJustifierAbsence.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonJustifierAbsence.setBackground(Color.red);
			}
		});
		buttonJustifierAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanning.setVisible(false);
				panelVoirAbsence.setVisible(false);
				panelDepotJustificatif.setVisible(false);
				panelPlanifierAbsence.setVisible(false);
				panelMessages.setVisible(false);
				
				panelDepotJustificatif.setVisible(true);
			}
		});
		buttonJustifierAbsence.setFocusable(false);
		buttonJustifierAbsence.setBorderPainted(false);
		buttonJustifierAbsence.setForeground(Color.WHITE);
		buttonJustifierAbsence.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonJustifierAbsence.setBackground(Color.RED);
		buttonJustifierAbsence.setBounds(0, 220, 189, 50);
		sidePanel.add(buttonJustifierAbsence);
		
		final JButton buttonAnticiperAbsence = new JButton("Anticiper absence(s)");
		buttonAnticiperAbsence.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				buttonAnticiperAbsence.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				buttonAnticiperAbsence.setBackground(Color.red);
			}
		});
		buttonAnticiperAbsence.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanning.setVisible(false);
				panelVoirAbsence.setVisible(false);
				panelDepotJustificatif.setVisible(false);
				panelPlanifierAbsence.setVisible(false);
				panelMessages.setVisible(false);
				
				panelPlanifierAbsence.setVisible(true);
			}
		});
		buttonAnticiperAbsence.setFocusable(false);
		buttonAnticiperAbsence.setBorderPainted(false);
		buttonAnticiperAbsence.setForeground(Color.WHITE);
		buttonAnticiperAbsence.setFont(new Font("FreeSans", Font.BOLD, 15));
		buttonAnticiperAbsence.setBackground(Color.RED);
		buttonAnticiperAbsence.setBounds(0, 270, 189, 50);
		sidePanel.add(buttonAnticiperAbsence);
		
		final JButton btnMesMessages = new JButton("Mes messages");
		btnMesMessages.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnMesMessages.setBackground(new Color(0f, 0f, 0f, 0.5f));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnMesMessages.setBackground(Color.red);
			}
		});
		btnMesMessages.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panelPlanning.setVisible(false);
				panelVoirAbsence.setVisible(false);
				panelDepotJustificatif.setVisible(false);
				panelPlanifierAbsence.setVisible(false);
				panelMessages.setVisible(false);
				
				panelMessages.setVisible(true);
			}
		});
		btnMesMessages.setForeground(Color.WHITE);
		btnMesMessages.setFont(new Font("FreeSans", Font.BOLD, 15));
		btnMesMessages.setFocusable(false);
		btnMesMessages.setBorderPainted(false);
		btnMesMessages.setBackground(Color.RED);
		btnMesMessages.setBounds(0, 321, 189, 50);
		sidePanel.add(btnMesMessages);
		
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
		buttonAnticiperAbsence_1_1.setBounds(0, 368, 189, 50);
		sidePanel.add(buttonAnticiperAbsence_1_1);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setBounds(190, 0, 530, 451);
		mainPanel.setLayout(null);
		mainPanel.add(panelPlanning);
		mainPanel.add(panelVoirAbsence);
		mainPanel.add(panelDepotJustificatif);
		mainPanel.add(panelPlanifierAbsence);
		mainPanel.add(panelMessages);
		frame.getContentPane().add(mainPanel);
		mainPanel.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{panelPlanning, panelVoirAbsence}));
		
		
		
		frame.setVisible(true);
	}
}
