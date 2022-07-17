/**
 *
 */
package ca.ahuntsic.projet2.classes;

import java.util.ArrayList;

/**
 *Fichier : Clients.java
 * @author:Ricardo Jean
 * Date Création : 19 oct. 2021
 */
public class Client  implements IClient{
	// atttribut d'instance
	protected String nom;
	protected String prenom;

	private final int MAX = 10;
	ArrayList<CompteBancaire> listeCompte = new ArrayList<>(MAX);

	/**
	 *constructeur sans paramètre
	 */
	public Client() {
		this(null,null);
	}
	/**Constructeurs avec paramètre
	 * @param nom
	 * @param prenom
	 * @param listeCompte
	 */
	public Client(String nom, String prenom) {
		this.nom = nom;
		this.prenom = prenom;

	}

	/**
	 * @param nom
	 * @param prenom
	 * @param listeCompte
	 */
	public Client(String nom, String prenom, ArrayList<CompteBancaire> listeCompte) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.listeCompte = listeCompte;
	}
	/**
	 * @return the nom
	 */

	public String getNom() {
		return nom;
	}
	/**
	 * @param nom the nom to set
	 */

	public void setNom(String nom) {
		this.nom = nom;
	}
	/**
	 * @return the prenom
	 */
	public String getPrenom() {
		return prenom;
	}
	/**
	 * @param prenom the prenom to set
	 */
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	/**
	 * @return the listeCompte
	 */
	public ArrayList<CompteBancaire> getListeCompte() {
		return listeCompte;
	}
	/**
	 * @param listeCompte the listeCompte to set
	 */
	public void setListeCompte(ArrayList<CompteBancaire> listeCompte) {
		this.listeCompte = listeCompte;
	}
	/**
	 * @return the mAX
	 */
	public int getMAX() {
		return MAX;
	}

	/**
	 * Méthode permettant d'ouvrir un compte tout en débutant par un solde et la date de création
	 * @throws Exception
	 */
	@Override
	public void ouvrirCompte(CompteBancaire cb) throws Exception  {
		// vérifier la capacité
		if( listeCompte.size() >= MAX) {
			// lever l'exception
			throw new Exception("Liste complète");
			// vérifier si client déjà présent
		}else if( listeCompte.indexOf(cb) !=-1) {
			throw new Exception("Client présent");
		}
		// ajouter client
		listeCompte.add(cb);

	}
	/**
	 * obtenir un compte connaissant son numéro
	 * @param numCompte
	 * @return
	 */
	@Override
	public CompteBancaire getCompte(String numCompte) {
		CompteBancaire compte = null;
		for(int i = 0; i < listeCompte.size(); i++) {

			if((listeCompte != null) && (listeCompte.get(i).getNumCompte() == numCompte)) {
				compte = listeCompte.get(i);
			}
		}
		return compte;
	}

	/**
	@Override
	public String obtenirCompte(String num) {
		String msg ="";
		for(CompteBancaire compteb : listeCompte) {
			if( compteb.numCompte.equals(num)) {
				msg +=  compteb.toString();
			}
		}
		return msg;
	}*/
	/**
	 * fermer un compte dont le numéro est donné
	 * @param numCompte
	 * @param cv
	 * @throws Exception
	 */

	@Override
	public void fermer(String numCompte) {
		CompteBancaire compteBank ;

		for(CompteBancaire cb : listeCompte) {
			compteBank = cb;
			if((compteBank != null) ) {
				listeCompte.remove(compteBank );
			}
		}

	}
	/**
	 * méthode qui permet de consulter ses comptes classiques
	 */
	public String consulterCompteClassique()  {


		String msg =  "" ;
		for(CompteBancaire compte : listeCompte) {
			if(compte instanceof CompteClassique) {
				msg +="CompteNuméro: "+compte.numCompte +" Ouvert le "+
						compte.getDateOuverture() +  " Solde " + compte.solde + "$\n";
			}

		}
		return msg;
	}
	/**
	 * méthode qui permet de consulter ses comptes épargne
	 */
	public String consulterCompteEparne() {

		CompteEpargne ce = new CompteEpargne();

		String msg =  "" ;

		for(CompteBancaire compte : listeCompte) {
			if(compte instanceof CompteEpargne) {
				((CompteEpargne) compte).calculInteret();
				msg += "CompteÉpargneNuméro: "+ compte.numCompte +" Ouvert le "+ compte.getDateOuverture()
				+  " Solde " + compte.solde +"$"+ " Taux: " + ce.getTauxInteret() +
				" Interet : "+ String.format("%.2f$", ((CompteEpargne) compte).interetObtenu);
			}
		}

		return msg;

	}
	/**
	 * méthode qui permet de faire un dépot sur compte donné
	 */
	@Override
	public void deposer(double montant,String num) {
		for(CompteBancaire compteb : listeCompte) {
			compteb.solde+=montant;
			ArrayList<Operation> listeOperations = new ArrayList<>();
			listeOperations.add(new Operation(true,montant));
		}
	}




	/**
	 * méthode qui permet de faire un retrait sur compte donné
	 */
	@Override
	public void retirer(double montant,String num) {
		for(CompteBancaire compteb : listeCompte) {
			compteb.solde-=montant;
			ArrayList<Operation> listeOperations = new ArrayList<>();
			listeOperations.add(new Operation(true,montant));

		}
	}
	/**
	 * méthode permettant d'obetnir le solde des comptes
	 * @return
	 */
	public double obtenirSolde() {
		double solde = 0.0;
		for(CompteBancaire total : listeCompte) {
			solde+=total.getSolde();
		}

		return solde;


	}
	/**
	 * méthode qui retourne le nombre total de comptes
	 * @return
	 */
	public int getSize() {
		return listeCompte.size();
	}


	/**
	 * toString qui affiche les informations d'un client; nom, prenom et listeCompte
	 */
	@Override
	public String toString() {

		return  nom + " " + prenom +  "\n " + listeCompte;
		//consulterCompteClassique() + "\n"
		//+ consulterCompteEparne();

		//+ "\n " + listeCompte;
	}
	public static void main(String[] args) {
		//Client c = new Client();
		Client clie = new Client("Jean ", "Ricardo");
		//Client clie8 = new Client("Joseph,", "Jerry");

		CompteBancaire cb2 = new CompteClassique(700,"2021-09-02");
		CompteBancaire cb = new CompteEpargne(500,"05/08/2020");

		try {
			clie.ouvrirCompte(cb);
			clie.ouvrirCompte(cb2);

			//System.out.println("Solde Total: " + clie.obtenirSolde());









			//System.out.println("Nombre: " + clie.getSize());

			System.out.println(clie);
			//clie.getCompte("0001");

			System.out.println(clie.getCompte(cb.getNumCompte()));
			clie.fermer(cb.getNumCompte());
			System.out.println(clie);
			//clie.deposer(752, clie.getNumeroSucc()+"0001-1");
			//System.out.println(clie.consulterCompteClassique());
			//System.out.println(clie.consulterCompteEparne());
			//clie.deposer(500, "0001");
			//System.out.println(clie);
			//clie.retirer(200, "0001");
			//System.out.println(clie);
			//clie.retirer(10,  "0001-25");
			//System.out.println("Depot "+ clie.toString());

			//System.out.println(clie8.toString());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

}
