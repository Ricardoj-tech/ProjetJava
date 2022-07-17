/**
 *
 */
package ca.ahuntsic.projet2.classes;

/**
 *Fichier : IClient.java
 * @author: Ricardo Jean
 * Date Création : 24 oct. 2021
 */
public interface IClient {

	void ouvrirCompte(CompteBancaire cb) throws Exception;
	//void fermer(String numCompte,CompteBancaire cv) throws Exception;
	void deposer(double montant,String num);
	void retirer(double montant,String num);
	CompteBancaire getCompte(String numCompte);
	/**
	 * @param numCompte
	 * @throws Exception
	 */
	void fermer(String numCompte) throws Exception;

}
