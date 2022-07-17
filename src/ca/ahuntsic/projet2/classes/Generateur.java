package ca.ahuntsic.projet2.classes;


import java.util.Random;
/**
 * Classe Generateur
 * @author	ma022714 - A Mebarek
 * Date Création: 	13 oct. 2021
 *
 */
public class Generateur {

	public static String genererCode(int longueur, int numSeq, int succursale ) {
		if (longueur <= 0) {
			longueur = 1;
		}

		String returnCode = ""; // String qui contiendra le code final
		returnCode += genererPrefixe(longueur);
		returnCode += String.format("%04d-", numSeq);
		returnCode += String.format("%02d", succursale);






		return returnCode;
	}

	static String genererPrefixe( int longueur) {
		Random rand = new Random();
		if(longueur ==2)
			return String.format("%02d-", rand.nextInt(100-1)+1);
		else if(longueur==3)
			return String.format("%03d-", rand.nextInt(1000-1)+1);
		return String.format("%04d-", rand.nextInt(100000-1)+1);
	}

	public static void main(String[] args) {
		int numSequ=101;
		String code1 = genererCode(3,numSequ , 25);
		System.out.println(code1);
		numSequ++;
		String code2 = genererCode(3, numSequ, 25);
		System.out.println(code2);
	}
}
