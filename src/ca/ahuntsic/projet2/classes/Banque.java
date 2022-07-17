/**
 *
 */
package ca.ahuntsic.projet2.classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import com.google.gson.Gson;

/**
 *Fichier : Banque.java
 * @author: Ricardo Jean
 * Date Création : 19 oct. 2021
 */
public class Banque {
	// attribut d'instance
	private String nom;
	private int numeroSucc= 25;
	private ArrayList<Client> listeClients = new ArrayList<>();

	/**
	 *constructeur sans paramètre
	 */
	public Banque() {
		this(null);
	}

	/**constructeur avec paramètre
	 * @param nom
	 * @param numeroSucc
	 * @param listeClients
	 */
	public Banque(String nom) {
		this.nom = nom;


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
	 * @return the numeroSucc
	 */
	public int getNumeroSucc() {
		return numeroSucc;

	}

	/**
	 * @param numeroSucc the numeroSucc to set
	 */
	public void setNumeroSucc(int numeroSucc) {
		this.numeroSucc = numeroSucc;
	}
	/**
	 * @return the listeClients
	 */
	public ArrayList<Client> getListeClients() {
		return listeClients;
	}
	/**
	 * @param listeClients the listeClients to set
	 */
	public void setListeClients(ArrayList<Client> listeClients) {
		this.listeClients = listeClients;
	}

	/**
	 * méthode charger à partir d'un fichier texte
	 * @param path
	 * @return
	 */
	public void chargerClients(String path){

		String ligne = null;
		String nom = null;
		String prenom = null;


		ArrayList<CompteBancaire> list = new ArrayList<CompteBancaire>();

		try(FileReader fr = new FileReader(path); BufferedReader br = new BufferedReader(fr);){
			ligne = br.readLine();
			do {
				Client client1 ;

				StringTokenizer str = new StringTokenizer(ligne,"\t");
				nom = str.nextToken();
				prenom = str.nextToken();


				String st = str.nextToken();

				StringTokenizer str1 = new StringTokenizer(st, "/");
				StringTokenizer str2 = new StringTokenizer(st, "-");
				String date1 = str1.nextToken(); String date2 = str2.nextToken();
				Double solde = Double.parseDouble( str1.nextToken());
				int	limiteDepot = Integer.parseInt(str.nextToken());
				int retrait = Integer.parseInt(str.nextToken());
				int decouvert = Integer.parseInt(str.nextToken());
				double taux = Double.parseDouble( str.nextToken());
				double interet = Double.parseDouble( str.nextToken());

				CompteClassique cc = new CompteClassique(solde, date2);
				CompteEpargne ce = new CompteEpargne(solde, date1);
				list.add(cc);
				list.add(ce);

				client1 =  new Client(nom, prenom, list);



			}
			while(ligne!= null) ;

		} catch(NumberFormatException | IOException | NoSuchElementException e) {
			System.out.println("Erreur : " + e.getMessage());
		}


	}




	/**
	 * enregister en format JSON
	 * @param chemin
	 * @param bq
	 */
	public void sauvegardrJSON(String path,Banque bq ) {

		Gson js = new Gson();

		try(Writer out = new FileWriter(path);){
			js.toJson(bq,out);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public void chargerJSON(String path, Banque bq) {
		Gson js = new Gson();

		try(
				Reader in = new FileReader(path);){
			bq = js.fromJson(in, bq.getClass());
		} catch ( IOException e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * méthode ajouter un client
	 * @param ajouter
	 */
	public void addClients(Client ajouter) {
		listeClients.add(ajouter);
	}
	/**
	 * méthode obtenir un client à une position donnée
	 * @param position
	 * @return
	 */
	public Client getClient(int position) {
		return listeClients.get(position);

	}
	/**
	 * obtenir la liste des clients
	 * @param liste
	 * @return
	 */
	public int getListeClients(ArrayList<Client> liste) {
		return getListeClients(liste);

	}
	/**
	 * méthode qui donne le nombre total des clients
	 * @return
	 */
	public int sizeClients() {
		return listeClients.size();
	}

	/**
	 * toString qui affiche le nom de la banque et le numéro succursale
	 */
	@Override
	public String toString() {
		return "Banque " + nom + " numeroSucc " + numeroSucc ;
	}

	public static void main(String[] args) {
		Banque bq = new Banque("Ahuntsic");



		Client clien = new Client("Ricardo","Jean");
		Client clien2 = new Client("cardo","Jea");
		Client clien3 = new Client("ardo","ea");




		//bq.addClients(clien);

		//bq.addClients(clien2);
		bq.addClients(clien3);
		///System.out.println(bq);
		//for(Client b : bq.listeClients) {

		//System.out.println(b);

		//}
		//System.out.println("Clients selon sa positon donnée, "+bq.getClient(0));

		//System.out.println("Nombre total des clients: " + bq.sizeClients());

		bq.chargerClients("Clients.txt");
		//bq.sauvegardrJSON("ayt.JSON", bq);

		System.out.println(bq);



	}
}
