/**
 *
 */
package ca.ahuntsic.projet2.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

import javax.swing.JOptionPane;

/**
 *Fichier : CompteEpargne.java
 * @author: Ricardo Jean
 * Date Création : 19 oct. 2021
 */
public class CompteEpargne extends CompteBancaire{
	// attribut d'instance
	private final double  TAUXINTERET = 0.05;
	protected double interetObtenu ;
	private double soldeDepart;

	/**
	 * constructeur sans paramètre
	 */
	public CompteEpargne() {
		this(0.0,null);
	}
	/**
	 * constructeur avec paramètre
	 * @param solde
	 * @param dateOuverture
	 */
	public CompteEpargne(double solde, String dateOuverture) {
		super(solde,dateOuverture);
		setSoldeDepart(solde);
	}

	/**
	 * @return the tauxInteret
	 */
	public double getTauxInteret() {
		return TAUXINTERET;
	}

	/**
	 * @return the soldeDepart
	 */
	public double getSoldeDepart() {
		return soldeDepart;
	}

	/**
	 * @param soldeDepart the soldeDepart to set
	 */
	public void setSoldeDepart(double soldeDepart) {
		this.soldeDepart = soldeDepart;
	}
	/**
	 * méthode qui permet de calculer l'interet
	 * @return
	 */

	public double calculInteret() {
		double interetOb = 0.0;

		DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(dateOuverture,formateur);

		interetOb += (soldeDepart * ChronoUnit.DAYS.between(date,LocalDate.now())*TAUXINTERET) /100/365;

		for(int i = 0; i <listeOperations.size();i++) {
			String text= listeOperations.get(i).getDateOperation();
			LocalDate dat = LocalDate.parse(text,formateur);
			interetOb += (soldeDepart * ChronoUnit.DAYS.between(LocalDate.now(),dat)*TAUXINTERET) /100/365;

		}
		//solde = interetOb + soldeDepart;
		interetObtenu = interetOb ;
		return interetOb;
	}


	/**
	 * toString qui affiche les détails du compte; numéro, solde, taux interet
	 * @return
	 */
	@Override
	public String toString() {
		calculInteret();
		return "CompteÉpargne: " + super.toString() + " Taux: "+ TAUXINTERET + " Interet : "
		+ String.format("%.2f$", interetObtenu);


	}

	@Override
	public void depot (double montant)  {
		super.depot(montant);
		soldeDepart+=montant;
	}
	/**
	 * méthode qui empeche de faire un retrait sur un compte épargne
	 */
	@Override
	public boolean retrait(double montant) {

		JOptionPane.showMessageDialog(null, "Vous ne pouvez pas effectuer de retrait");
		return false;
	}
	public static void main(String[] args) {
		CompteEpargne ce = new CompteEpargne(3000, "01/02/2020");
		//ce.depot(500);
		System.out.println(ce);



		//System.out.println("Interets :" + ce.calculInteret());
		//System.out.println(ce.retrait(200));
	}


}
