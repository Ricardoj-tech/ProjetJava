/**
 *
 */
package ca.ahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import ca.ahuntsic.projet2.classes.CompteBancaire;


/**
 *Fichier : FenetreTransaction.java
 * @author: Ricardo Jean
 * Date Création : 26 oct. 2021
 */
public class FenetreTransaction extends JDialog {

	private static final long serialVersionUID = 1L;
	CompteBancaire cBancaire = null;

	private final JPanel contentPanel = new JPanel();
	public JTextField textSolde;
	private JTextField textLimite;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnDepot;
	private JRadioButton rdbtnRetrait;
	private JRadioButton rdbtnMiseAJour;
	private JLabel lblLimite;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			FenetreTransaction dialog = new FenetreTransaction();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/


	/**
	 * Create the dialog.
	 */
	public FenetreTransaction(CompteBancaire cb) {
		initialize();
		setcBancaire(cb);
		if(cb != null) {
			afficherTransac();
		}
		setModal(true);


	}

	/**
	 * @return the cBancaire
	 */
	public CompteBancaire getcBancaire() {
		return cBancaire;
	}
	/**
	 * @param cBancaire the cBancaire to set
	 */
	public void setcBancaire(CompteBancaire cBancaire) {
		this.cBancaire = cBancaire;
	}
	/**
	 *
	 */
	private void afficherTransac() {
		textSolde.setText(String.valueOf(cBancaire.getSolde()));

	}
	private void initialize() {
		setTitle("Compte");


		setBounds(200, 200, 605, 466);
		getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(new GridLayout(0, 1, 0, 0));
		{
			JPanel panel = new JPanel();
			panel.setBackground(new Color(154, 205, 50));
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(0, 2, 0, 0));
			{
				JLabel lblSolde = new JLabel("Solde");
				lblSolde.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel.add(lblSolde);
			}
			{
				textSolde = new JTextField();
				textSolde.setEditable(false);
				panel.add(textSolde);
				textSolde.setColumns(10);
			}
			{
				lblLimite = new JLabel("Montant");
				lblLimite.setFont(new Font("Tahoma", Font.BOLD, 14));
				panel.add(lblLimite);
			}
			{
				textLimite = new JTextField();
				panel.add(textLimite);
				textLimite.setColumns(10);
			}
			{
				rdbtnDepot = new JRadioButton("Depot");
				rdbtnDepot.setBackground(new Color(154, 205, 50));
				buttonGroup.add(rdbtnDepot);
				rdbtnDepot.setFont(new Font("Tahoma", Font.BOLD, 13));
				panel.add(rdbtnDepot);
			}
			{
				rdbtnRetrait = new JRadioButton("Retrait");
				rdbtnRetrait.setBackground(new Color(154, 205, 50));
				buttonGroup.add(rdbtnRetrait);
				rdbtnRetrait.setFont(new Font("Tahoma", Font.BOLD, 13));
				panel.add(rdbtnRetrait);
			}
			{
				rdbtnMiseAJour = new JRadioButton("Mise a jour");
				rdbtnMiseAJour.addItemListener(new RdbtnMiseAJourItemListener());
				rdbtnMiseAJour.setBackground(new Color(154, 205, 50));
				buttonGroup.add(rdbtnMiseAJour);
				rdbtnMiseAJour.setFont(new Font("Tahoma", Font.BOLD, 13));
				panel.add(rdbtnMiseAJour);
			}
		}
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel);
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			{
				JButton btnConfirmer = new JButton("Confirmer");
				btnConfirmer.addActionListener(new BtnConfirmerActionListener());
				btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 15));
				panel.add(btnConfirmer);
			}
			{
				JButton btnEtatDuCompte = new JButton("Etat du compte");
				btnEtatDuCompte.addActionListener(new BtnEtatDuCompteActionListener());
				btnEtatDuCompte.setFont(new Font("Tahoma", Font.BOLD, 15));
				panel.add(btnEtatDuCompte);
			}
			{
				JButton btnRetour = new JButton("Retour");
				btnRetour.addActionListener(new BtnRetourActionListener());
				btnRetour.setFont(new Font("Tahoma", Font.BOLD, 15));
				panel.add(btnRetour);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane);
			{
				JPanel panel = new JPanel();
				scrollPane.setViewportView(panel);
				panel.setBorder(new TitledBorder(null, "Affichage", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
				panel.setLayout(new BorderLayout(0, 0));
				{
					textArea = new JTextArea();
					panel.add(textArea, BorderLayout.CENTER);
				}
			}
		}


	}


	private class BtnConfirmerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {


			double montant =Double.parseDouble(textLimite.getText());

			try {
				if(rdbtnDepot.isSelected()) {



					cBancaire.depot(montant);



				} else if(rdbtnRetrait.isSelected()) {


					cBancaire.retrait(montant);


				} else if(rdbtnMiseAJour.isSelected() || (e.getSource()== rdbtnDepot)
						||(e.getSource() == rdbtnRetrait)) {


					textArea.setText( montant + "$\nMise à jour effectuée");


				}
				else {
					JOptionPane.showMessageDialog(null, "Veuillez choisir un mode de transaction");
				}
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}
		}
	}



	private class BtnEtatDuCompteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {


			//listModel.addElement(cBancaire.toStringOperations());
			textArea.setText(cBancaire.toStringOperations());
			//System.out.println(cBancaire);

		}
	}
	private class BtnRetourActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	private class RdbtnMiseAJourItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			if(rdbtnMiseAJour.isSelected()) {
				lblLimite.setText("Limite");
			}else {
				lblLimite.setText("Montant");
			}
		}
	}



}
