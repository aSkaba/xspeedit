package voyages;

import java.util.*;
import java.io.IOException;

public class SNCF {

	/**
	 * le main qui lancera l'application, avant d'afficher le resultat trié.
	 * 
	 * @throws IOException
	 *             : renvoie une exception si la chaine de caractère en entrée
	 *             n'est pas uniquement composée de chiffre allant de 1 à 9
	 */
	public static void main(String[] args) throws IOException {
		String articleEntree = "";
		String resultat = "";
		System.out.print("Veuillez saisir vos boites à trier :");
		Scanner scanner = new Scanner(System.in);
		articleEntree = scanner.nextLine();
		scanner.close();
		if (articleEntree.matches("^[1-9]*$")) {
			resultat = XSpeedit.triOpti(articleEntree);
		} else {
			throw new IOException("Ce n'est pas une suite de chiffre qui a été rentrée");
		}
		System.out.print("Voici le tri optimisé : " + resultat);
		return;
	}
}
