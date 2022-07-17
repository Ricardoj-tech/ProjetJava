/**
 *
 */
package ca.ahuntsic.projet2.classes;

/**
 *Fichier : ICompteBancaire.java
 * @author: Ricardo Jean
 * Date Création : 24 oct. 2021
 */
public interface ICompteBancaire {


	boolean retrait(double montant) throws Exception;
	void depot(double montant) throws Exception;

	void virement(double montant, CompteBancaire compVirement) throws Exception ;
	/**
	 * @param montant
	 * @return
	 * @throws Exception
	 */

}
