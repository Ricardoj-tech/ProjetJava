/**
 *
 */
package ca.ahuntsic.projet2.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.border.EmptyBorder;

/**
 *Fichier : Authentification.java
 * @author: Ricardo Jean
 * Date Création : 25 oct. 2021
 */
public class Authentification extends JDialog {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textIdentifiant;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Authentification dialog = new Authentification();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Authentification() {
		initialize();
	}
	private void initialize() {
		getContentPane().setBackground(new Color(153, 204, 0));
		setTitle("Authentification");
		setBounds(100, 100, 486, 200);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(153, 204, 51));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new GridLayout(0, 2, 0, 0));
		{
			JLabel lblIdentifiant = new JLabel("Identifiant");
			lblIdentifiant.setFont(new Font("Tahoma", Font.BOLD, 15));
			contentPanel.add(lblIdentifiant);
		}
		{
			textIdentifiant = new JTextField();
			contentPanel.add(textIdentifiant);
			textIdentifiant.setColumns(10);
		}
		{
			JLabel lblMotDePasse = new JLabel("Mot de passe");
			lblMotDePasse.setFont(new Font("Tahoma", Font.BOLD, 14));
			contentPanel.add(lblMotDePasse);
		}
		{
			passwordField = new JPasswordField();
			contentPanel.add(passwordField);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(153, 204, 0));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				okButton.addActionListener(new OkButtonActionListener());
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new CancelButtonActionListener());
				cancelButton.setFont(new Font("Tahoma", Font.BOLD, 14));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private class OkButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String id = textIdentifiant.getText();
			GestionClient gc = new GestionClient(null);

			String password = new String(passwordField.getPassword());

			if(id.isEmpty() || password.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Identifiant ou Mot de passe ne doit pas être vide ");
			}
			else if(id.equals("admin") && password.equals("Ricardo")) {
				gc.setVisible(true);
				dispose();

			} else {
				JOptionPane.showMessageDialog(null, "Identifiant ou Mot de passe incorrect ");
			}
		}
	}
	private class CancelButtonActionListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
}
