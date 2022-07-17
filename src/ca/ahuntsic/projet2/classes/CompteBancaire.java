/**
 *
 */
package ca.ahuntsic.projet2.classes;

import java.util.ArrayList;

/**
 *Fichier : CompteBancaire.java
 * @author: Ricardo Jean
 * Date Cr�ation : 19 oct. 2021
 *
 *
 */
public abstract class CompteBancaire extends Banque implements ICompteBancaire {
	//attribut d'instance
	protected String numCompte;
	protected double solde;
	protected String dateOuverture;

	// attribut de classe
	private static int numSequentiel =0;


	protected ArrayList<Operation> listeOperations = new ArrayList<>();


	/**
	 *constructeur sans param�tre
	 */
	public CompteBancaire() {
		this(0.0,null);
	}


	/**
	 * constructeur avec param�tre
	 * @param numCompte
	 * @param solde
	 * @param dateOuverture
	 * @param listeOperations
	 */
	public CompteBancaire(double solde, String dateOuverture) {

		this.solde = solde;
		this.dateOuverture = dateOuverture;
		CompteBancaire.numSequentiel++;
		setNumCompte(CompteBancaire.numSequentiel);
	}


	/**
	 * @return the numCompte
	 */
	public String getNumCompte() {
		return numCompte;
	}


	/**
	 * setter qui permet de modifier le num�ro du compte
	 * @param numSequentiel2
	 * @param numCompte the numCompte to set
	 */
	public void setNumCompte(int sequentiel) {


		numCompte= Generateur.genererCode(3, CompteBancaire.numSequentiel,getNumeroSucc());
		//numCompte= sequenc + "000" +CompteBancaire.numSequentiel ;

	}


	/**
	 * @return the solde
	 */
	public double getSolde() {
		return solde;
	}


	/**
	 * @param solde the solde to set
	 */
	public void setSolde(double solde) {
		this.solde = solde;
	}


	/**
	 * @return the dateOuverture
	 */
	public String getDateOuverture() {
		return dateOuverture;
	}


	/**
	 * @param dateOuverture the dateOuverture to set
	 */
	public void setDateOuverture(String dateOuverture) {
		this.dateOuverture = dateOuverture;
	}


	/**
	 * @return the listeOperations
	 */
	public ArrayList<Operation> getListeOperations() {
		return listeOperations;
	}


	/**
	 * @param listeOperations the listeOperations to set
	 */
	public void setListeOperations(ArrayList<Operation> listeOperations) {
		this.listeOperations = listeOperations;
	}





	/**
	 * @return the numSequentiel
	 */
	public static int getNumSequentiel() {
		return CompteBancaire.numSequentiel;
	}

	/**
	 * m�thode fermer un compte donn�
	 * @param cb
	 * @return
	 */
	public boolean fermerCompte(CompteBancaire cb) {
		cb.depot(solde);
		solde -= solde;
		return false;

	}

	/** m�thode permettant de faire un depot
	 * @param solde2
	 */

	@Override
	public void depot(double montant) {
		solde+=montant;
		listeOperations.add(new Operation(true,montant));

	}
	/**
	 * m�thode permettant de faire un retrait
	 * et de v�rifier si le montant de retrait n'est pas plus petit que le solde
	 */
	@Override
	public boolean retrait(double montant) throws Exception {
		if(montant <= solde) {
			solde -= montant;
			return listeOperations.add(new Operation(false,montant));
		}else {
			throw new Exception("Vous ne pouvez pas retirer cette somme " + montant + " $ ");
		}
	}

	/**
	 * m�thode qui permet de faire un virement sur un compte donn�
	 */
	@Override
	public void virement(double montant, CompteBancaire compVirement) throws Exception {
		if(montant <= solde) {
			if(retrait(montant)) {
				compVirement.depot(montant);

			}
		} else {
			throw new Exception("Virement impossible, fonds insuffisant: " + solde + "$"
					+ "\nvous avez " + solde +"$"
					+ " et vous essayez de faire un virement de \n" + montant
					+ " $ ");
		}try {

		} finally {
			System.out.println("Virement effectu� avec succ�s");
		}
	}
	/**
	 *toString qui affiche le num�ro du compte, le solde,la date d'ouverture
	 *et la liste des op�rations
	 */

	@Override
	public String toString() {
		return "Num�ro compte: " + numCompte +  " Date d'ouverture " + dateOuverture
				+ "\nSolde : " + String.format("%.2f$", solde);
		//"\nOp�rations: \n" + listeOperations ;

	}
	public String toStringOperations() {
		return "Num�ro compte: " + numCompte +  " Date d'ouverture " + dateOuverture
				+ "\nSolde:" + String.format("%.2f$", solde)+
				"\n\n\tOp�rations:" + listeOperations;

	}


	public static void main(String[] args) {
		CompteClassique cc = new CompteClassique(7000,"2021-10-22");
		CompteEpargne ce = new CompteEpargne(1000,"2021-11-22");
		try {
			cc.virement(400, ce);
			cc.depot(500);
			cc.retrait(100);
			//cb2.virement(300, cb);
			//cb2.depot(400);
			//cb2.retrait(50);
		} catch (Exception e) {
			System.out.println(e.getMessage());

		}
		System.out.println(cc.toStringOperations());
		//System.out.println(cc);
		//System.out.println(cb2);
	}










}
