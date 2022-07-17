package ca.ahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import ca.ahuntsic.projet2.classes.Banque;
import ca.ahuntsic.projet2.classes.Client;
import ca.ahuntsic.projet2.classes.CompteBancaire;
import ca.ahuntsic.projet2.classes.CompteClassique;
import ca.ahuntsic.projet2.classes.CompteEpargne;

/**
 *Fichier : GestionClient.java
 * @author: Ricardo Jean
 * Date Création : 25 oct. 2021
 */
public class GestionClient extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private DefaultListModel<CompteBancaire> listModel =  new DefaultListModel<>();

	//private DefaultListModel<String>  listModel3 = new DefaultListModel<>();

	private  DefaultListModel<String> listModel4 = new DefaultListModel<>();
	ArrayList<CompteBancaire> listeCompte = new ArrayList<>();

	Client client1 = null;
	CompteBancaire cb = null;
	CompteClassique cc = null;
	CompteEpargne ce = null;
	Banque bq = new Banque("Ahuntsic");



	private JPanel contentPane;
	private JPanel panel;
	private JLabel lblNom;
	private JTextField textNom;
	private JLabel lblPrenom;
	private JTextField textPrenom;
	private JButton btnNouveauClient;
	private JButton btnRelevClient;
	private JPanel panel_1;
	private JButton btnOuvrirCompte;
	private JRadioButton rdbtnClassique;
	private JRadioButton rdbtnEpargne;
	private JLabel lblTauxDeRendement;
	private JComboBox<String> comboRendement;
	private JButton btnFermerCompte;
	private JButton btnTransaction;
	private JButton btnVirement;
	private JLabel lblMontant;
	private JTextField textMontant;
	private final ButtonGroup buttonGroup = new ButtonGroup();




	FenetreTransaction ft = new FenetreTransaction(null);
	private JList<CompteBancaire> listCompteClient;
	private JPanel panel_2;
	private JComboBox<String> comboBox;
	private JButton btnConfirmer;
	private JPanel panel_3;
	private JList<String> listCompteDestinaires;
	private JScrollPane scrollPane;
	private JScrollPane scrollPane_2;
	private JMenuBar menuBar;
	private JMenu mnFichier;
	private JMenuItem mntmSauvergarder;
	private JMenuItem mntmCharger;
	private JMenuItem mntmChargerjson;
	private JPanel panel_4;
	private JTextArea textArea;
	private JScrollPane scrollPane_1;



	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					GestionClient frame = new GestionClient();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public GestionClient(Banque b) {
		initialize();


		listCompteClient.setModel(listModel);
		listCompteDestinaires.setModel(listModel4);

		scrollPane_1 = new JScrollPane();
		contentPane.add(scrollPane_1);

		panel_4 = new JPanel();
		scrollPane_1.setViewportView(panel_4);
		panel_4.setBorder(new TitledBorder(null, "Affichage", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_4.setLayout(new BorderLayout(0, 0));

		textArea = new JTextArea();
		textArea.setFont(new Font("Monospaced", Font.BOLD, 13));
		panel_4.add(textArea);


	}
	private void initialize() {




		setTitle("Gestion des comptes d'un client");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 496);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 250, 210));
		contentPane.setBorder(new EmptyBorder(0, 7, 7, 7));
		setContentPane(contentPane);
		contentPane.setLayout(new BoxLayout(contentPane, BoxLayout.Y_AXIS));

		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnFichier = new JMenu("Fichier");
		mnFichier.setMnemonic('F');
		mnFichier.setHorizontalAlignment(SwingConstants.LEFT);
		mnFichier.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnFichier);

		mntmSauvergarder = new JMenuItem("SauvergarderJSON");
		mntmSauvergarder.addActionListener(new MntmSauvergarderActionListener());
		mntmSauvergarder.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnFichier.add(mntmSauvergarder);




		mntmCharger = new JMenuItem("ChargerTexte");
		mntmCharger.addActionListener(new MntmChargerActionListener());
		mntmCharger.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnFichier.add(mntmCharger);

		mntmChargerjson = new JMenuItem("ChargerJSON");
		mntmChargerjson.addActionListener(new MntmChargerjsonActionListener());
		mntmChargerjson.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnFichier.add(mntmChargerjson);

		panel_2 = new JPanel();
		contentPane.add(panel_2);
		panel_2.setLayout(new BoxLayout(panel_2, BoxLayout.X_AXIS));

		panel = new JPanel();
		panel_2.add(panel);
		panel.setBackground(new Color(154, 205, 50));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Informations du client", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setLayout(new GridLayout(0, 2, 0, 0));

		lblNom = new JLabel("Nom");
		lblNom.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblNom);

		textNom = new JTextField();
		panel.add(textNom);
		textNom.setColumns(10);

		lblPrenom = new JLabel("Prenom");
		lblPrenom.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel.add(lblPrenom);

		textPrenom = new JTextField();
		panel.add(textPrenom);
		textPrenom.setColumns(10);

		btnNouveauClient = new JButton("Nouveau client");
		btnNouveauClient.addActionListener(new BtnNouveauClientActionListener());
		btnNouveauClient.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(btnNouveauClient);

		btnRelevClient = new JButton("Relev\u00E9 client");
		btnRelevClient.addActionListener(new BtnRelevClientActionListener());
		btnRelevClient.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel.add(btnRelevClient);

		comboBox = new JComboBox<String>();
		panel.add(comboBox);

		panel_1 = new JPanel();
		panel_2.add(panel_1);
		panel_1.setBackground(new Color(154, 205, 50));
		panel_1.setBorder(new TitledBorder(null, "Gestion Client", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setLayout(new GridLayout(0, 2, 2, 2));

		btnOuvrirCompte = new JButton("Ouvrir Compte");
		btnOuvrirCompte.addActionListener(new BtnOuvrirCompteActionListener());
		btnOuvrirCompte.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnOuvrirCompte);

		rdbtnClassique = new JRadioButton("Classique");
		buttonGroup.add(rdbtnClassique);
		rdbtnClassique.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(rdbtnClassique);

		lblTauxDeRendement = new JLabel("Taux de rendement:");
		lblTauxDeRendement.setFont(new Font("Tahoma", Font.BOLD, 14));
		panel_1.add(lblTauxDeRendement);

		rdbtnEpargne = new JRadioButton("Epargne");
		buttonGroup.add(rdbtnEpargne);
		rdbtnEpargne.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(rdbtnEpargne);

		comboRendement = new JComboBox<>();

		comboRendement.setModel(new DefaultComboBoxModel<String>(new String[] {"0.5%"}));
		comboRendement.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(comboRendement);

		btnFermerCompte = new JButton("Fermer compte");
		btnFermerCompte.addActionListener(new BtnFermerCompteActionListener());
		btnFermerCompte.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnFermerCompte);

		lblMontant = new JLabel("Montant");
		lblMontant.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(lblMontant);

		btnTransaction = new JButton("Transaction");
		btnTransaction.addActionListener(new BtnTransactionActionListener());
		btnTransaction.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnTransaction);

		textMontant = new JTextField();
		panel_1.add(textMontant);
		textMontant.setColumns(10);

		btnConfirmer = new JButton("Confirmer");
		btnConfirmer.addActionListener(new BtnConfirmerActionListener());


		btnVirement = new JButton("Virement");
		btnVirement.addActionListener(new BtnVirementActionListener());
		btnVirement.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnVirement);
		btnConfirmer.setFont(new Font("Tahoma", Font.BOLD, 13));
		panel_1.add(btnConfirmer);

		panel_3 = new JPanel();
		contentPane.add(panel_3);
		panel_3.setLayout(new GridLayout(0, 2, 0, 0));

		scrollPane = new JScrollPane();
		panel_3.add(scrollPane);

		listCompteClient = new JList<CompteBancaire>();
		listCompteClient.addListSelectionListener(new ListCompteClientListSelectionListener());
		listCompteClient.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(listCompteClient);
		listCompteClient.setBorder(new TitledBorder(null, "Listes des comptes du client", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		scrollPane_2 = new JScrollPane();
		panel_3.add(scrollPane_2);

		listCompteDestinaires = new JList<String>();
		listCompteDestinaires.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane_2.setViewportView(listCompteDestinaires);
		listCompteDestinaires.setBorder(new TitledBorder(null, "Comptes destinataires", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		listCompteDestinaires.setVisible(false);





	}

	private class BtnTransactionActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {



			String num = listCompteDestinaires.getSelectedValue();

			if((num!=null) && (client1.getCompte(num)!= null)) {
				// affiche le numéro du compte à l'interface graphique
				FenetreTransaction ft = new FenetreTransaction(client1.getCompte(num));
				ft.setTitle("Compte :"+ num);
				ft.setVisible(true);
				ft.setModal(true);
			}


			try {
				double solde = Double.parseDouble(textMontant.getText());
				cc.depot(solde);
				cc.retrait(solde);

			} catch (Exception e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage());
			}

		}
	}
	private class BtnVirementActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			double solde = Double.parseDouble(textMontant.getText());
			String num = listCompteDestinaires.getSelectedValue();

			if((num!=null) && (client1.getCompte(num)!= null)) {
				// affiche le numéro du compte à l'interface graphique
				FenetreTransaction ft = new FenetreTransaction(client1.getCompte(num));
				ft.setTitle("Compte :"+ num);
				ft.setTitle("Compte :"+ num);
				ft.setVisible(true);
				ft.setModal(true);
			}
			try {
				ce.depot(solde);
				ce.retrait(solde);
				cc.virement(solde, ce);
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}


		}
	}
	private class BtnNouveauClientActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			String nom = textNom.getText();
			String prenom = textPrenom.getText();
			client1 = new Client(nom,  prenom);

			String nomPrenom = nom +" " +prenom;
			if(textNom.getText().isEmpty() || textPrenom.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Le nom ou le prénom ne peut pas être vide");

			} else {
				comboBox.addItem(nomPrenom);
			}

		}
	}
	private class BtnRelevClientActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			textArea.setText(client1.toString());


		}
	}

	private class BtnOuvrirCompteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) throws NumberFormatException {
			double montant = Double.parseDouble(textMontant.getText());
			SimpleDateFormat dateFmt = new SimpleDateFormat("dd/MM/yyyy");


			try {
				CompteClassique cc = new CompteClassique(montant,  dateFmt.format(new Date()));
				CompteEpargne ce = new CompteEpargne(montant, "01/02/2020");
				listeCompte.add(cc);
				listeCompte.add(ce);
				if(rdbtnClassique.isSelected()) {

					client1.ouvrirCompte(cc);

				}else if(rdbtnEpargne.isSelected()) {

					if(ce instanceof CompteBancaire) {
						//int index = comboRendement.getSelectedIndex();

						client1.ouvrirCompte(ce);

						ce.getTauxInteret();
						ce.calculInteret();
					}
				}



				for(CompteBancaire cb2 : listeCompte ){
					listModel.addElement(cb2);



				}
			} catch ( Exception e1) {
				JOptionPane.showMessageDialog(null, "Veuillez selectionner un type de compte");
			}

		}


	}
	private class BtnFermerCompteActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			listCompteClient.repaint();
			listCompteDestinaires.repaint();
			try {
				String num = listCompteDestinaires.getSelectedValue();

				if((e.getSource() == listCompteClient)) {
					client1.fermer(num);
					listModel.removeElement(num);


				}
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
			}

		}


	}
	private class BtnConfirmerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if(textMontant.getText().isEmpty()) {

				JOptionPane.showMessageDialog(null, "Vous devez entrer un montant");

			} else if (Double.parseDouble(textMontant.getText()) < 1) {

				JOptionPane.showMessageDialog(null, "Le montant entré ne peut pas être plus petit que 0");
			} else {
				JOptionPane.showMessageDialog(null, "Montant accepté, vous pouvez maintenant ouvrir votre compte");

			}

		}
	}


	private class MntmSauvergarderActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {

			bq.addClients(client1);

			bq.sauvegardrJSON("Banque.JSON", bq);

		}
	}
	private class MntmChargerjsonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent arg0) {
			bq.chargerJSON("Banque.JSON", bq);
			//System.out.println(bq);
		}
	}
	private class MntmChargerActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			//bq.chargerClients("client.txt");
		}
	}
	private class ListCompteClientListSelectionListener implements ListSelectionListener {
		@Override
		public void valueChanged(ListSelectionEvent e) {


			cb = listCompteClient.getSelectedValue();
			if(e.getSource() == listCompteClient) {
				listCompteDestinaires.setVisible(true);
				listModel4.addElement(cb.getNumCompte());

			}
		}
	}

}
