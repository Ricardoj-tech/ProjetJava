package ca.ahuntsic.projet2.classes;

import java.util.ArrayList;

/**
 *
 */

/**
 *Fichier : Test.java
 * @author: Ricardo Jean
 * Date Création : 1 nov. 2021
 */
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		Banque bq = new  Banque("Ahuntsic");
		Client client1 = new Client("Jean ", "Ricardo");
		Client client2 = new Client("Ferdilus", "Ansler");
		ArrayList<CompteBancaire> listeCompte = new ArrayList<>();


		CompteEpargne ce = new CompteEpargne(500, "03/09/2000");
		CompteClassique cc = new CompteClassique(700,"2001-10-22");

		listeCompte.add(ce);
		listeCompte.add(cc);

		for(CompteBancaire cb2 : listeCompte) {
			System.out.println("Liste compte" + cb2 );
		}


		try {
			/*******Test Banque et Client********/
			bq.addClients(client1);
			bq.addClients(client2);
			System.out.println(bq);
			client1.ouvrirCompte(cc);
			client1.ouvrirCompte(cc);
			client2.ouvrirCompte(ce);
			client2.ouvrirCompte(ce);


			CompteBancaire cb2 = new CompteClassique(500,"2000-05-23");
			CompteBancaire cb3 = new CompteEpargne(570,"07/10/2000");

			Client cl = new Client("Jean", "Ricardo", listeCompte);

			client1.deposer(800, "0003");
			client1.retirer(500,"003");
			client1.consulterCompteClassique();
			client1.consulterCompteEparne();
			//client1.fermer("0003");
			client1.getSize();

			System.out.println(client1.toString());
			System.out.println("Solde total: " + client1.obtenirSolde() + "\n");

			String msg = "Liste de clients:\n";
			for (Client c : bq.getListeClients()) {
				if( c != null) {
					System.out.println(msg + c);
				}

			}

			/****************Test compteClassique***************/
			System.out.println("\ncompteClassique");
			cc.depot(300);
			//cc.fermerCompte(cc);
			cc.retrait(200);
			cc.virement(100, ce);
			//cc.fermerCompte(cc);
			System.out.println(cc.toString());
			System.out.println("\n" + cc.toStringOperations());
			/****************Test compteÉpargne && CompteBancaire***************/
			System.out.println("\nCompteÉepargne");
			ce.calculInteret();
			//ce.retrait(500);
			ce.depot(500);
			//ce.fermerCompte(ce);
			System.out.println(ce.toString());
			System.out.println("\n"+ce.toStringOperations());
			/***************TestOperation*************************/
			System.out.println("\nTest opération");
			Operation op = new Operation(true, 100);
			Operation op2 =  new Operation(false, 2000);
			System.out.println(op + "\n" + op2);

			bq.sauvegardrJSON("BanqueTest.JSON",bq);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}





	}

}
