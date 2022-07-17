/**
 *
 */
package ca.ahuntsic.projet2.classes;

import javax.swing.JOptionPane;

/**
 *Fichier : CompteClassique.java
 * @author: Ricardo Jean
 * Date Création : 19 oct. 2021
 */
public class CompteClassique extends CompteBancaire {
	// constante
	protected final int LIMITERETRAIT = 1000;
	protected final int LIMITEDEPOT = 1000;
	protected final int DECOUVERTMAX = -500;
	/**
	 * @return the lIMITERETRAIT
	 */
	public int getLIMITERETRAIT() {
		return LIMITERETRAIT;
	}

	/**
	 * @return the lIMITEDEPOT
	 */
	public int getLIMITEDEPOT() {
		return LIMITEDEPOT;
	}

	/**
	 * @return the dECOUVERTMAX
	 */
	public int getDECOUVERTMAX() {
		return DECOUVERTMAX;
	}






	/**
	 *constructeurs sans paramètre
	 */
	public CompteClassique() {
		this(0.0,null);
	}

	/**constructeurs avec paramètre
	 * @param solde
	 * @param string
	 * @param numeroSucc
	 */
	public CompteClassique(double solde, String dateOuverture ) {
		super(solde,dateOuverture);
	}




	/**
	 * méthode qui permet de faire un retrait tout en vérifiant
	 * la limite de retrait et de respecter le découvert
	 */
	@Override
	public boolean retrait(double montant) {
		boolean retraitA = false;
		double retrait = solde - montant ;
		if((montant <= LIMITERETRAIT) && (retrait >= DECOUVERTMAX)) {
			solde -= montant;
			listeOperations.add(new Operation(false,montant));
			retraitA = true;
			System.out.println("Voici votre argent: " + solde);
		}else {
			JOptionPane.showMessageDialog(null,montant + "$ limite de retraits dépasser " + " votre limite est:" + LIMITERETRAIT);
		}
		return retraitA;
	}
	/**
	 * méthode permettant de faire un depot
	 * et de ne pas dépasser la limite de depot autorise
	 */

	public boolean depotC(double montant) throws Exception {
		boolean depotA = false;
		if(montant <= LIMITEDEPOT) {
			solde += montant;
			listeOperations.add(new Operation(true,montant));
			depotA = true;
		}else {
			throw new Exception(montant + "$ vous ne pouvez pas ajouter ce montant");
		}
		return depotA;
	}

	/**
	 * toString affichant le numéro du compte, catégorie du compte et le solde
	 */

	@Override
	public String toString() {
		return super.toString() + "\nLimite depot: " + LIMITEDEPOT
				+ "\nLimite retarait: " + LIMITERETRAIT  + "\nDécouvert: " + DECOUVERTMAX + "\n";
	}



	public static void main(String[] args) {
		CompteClassique cc = new CompteClassique(700,"2021-10-22");
		CompteEpargne cb = new CompteEpargne(1000,"2021-11-22");

		try {
			cc.depotC(1000);
			cc.retrait(200);
			cc.virement(200, cb);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println(cc);

	}
}


