package ca.ahuntsic.projet2.classes;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *Fichier : Operation.java
 * @author: Ricardo Jean
 * Date Création : 19 oct. 2021
 */
public class Operation {
	// attribut d'instance
	private String dateOperation;
	private boolean type ;
	private double montant;

	/**
	 *constructeur sans paramètre
	 */
	public Operation() {
		this(true,0.0);
	}
	/**constructeur avec paramètre
	 * @param dateOperation
	 * @param type
	 * @param montant
	 */
	public Operation( boolean type, double montant) {
		// formater la date
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		dateOperation = LocalDate.now().format(dtf);
		this.type = type;
		this.montant = montant;
	}
	/**
	 * @return the dateOperation
	 */
	public String getDateOperation() {
		return dateOperation;
	}
	/**
	 * @param dateOperation the dateOperation to set
	 */
	public void setDateOperation(String dateOperation) {
		this.dateOperation = dateOperation;
	}
	/**
	 * @return the type
	 */
	public boolean isType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(boolean type) {
		this.type = type;
	}
	/**
	 * @return the montant
	 */
	public double getMontant() {
		return montant;
	}
	/**
	 * @param montant the montant to set
	 */
	public void setMontant(double montant) {
		this.montant = montant;
	}

	/**
	 * toString qui affiche la date de l'opération, le type et le solde
	 */
	@Override
	public String toString() {
		char typeOp = 'R' ;

		if(type) {
			typeOp = 'D';

		}
		return "\n\t"+dateOperation + "\t" + typeOp +"\t" + String.format("%1$.2f $", montant) + "\n\t";
	}

	public static void main(String[] args) {
		Operation op = new Operation(false,25555);
		Operation op2 = new Operation(true,555);
		System.out.println(op.toString());
		System.out.println(op2.toString());
	}



}
