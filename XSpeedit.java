package voyages;

import java.io.IOException;
import java.util.*;

public final class XSpeedit {
	
	private XSpeedit(){}

	/**
	 * Cette méthode prend le premier chiffre de la plus longue des deux sous-listes
	 * entre [1-4] et [6-9], et la compare à tous les chiffres de l'autre sous-liste
	 * un à un. Si la somme fait 10 alors ils sont mis dans la même boite et on
	 * les supprime de la liste, sinon on continue. 
	 * 
	 * @param tabEntree : La liste contenant les chiffres à examiner pour en prendre les paire faisant 10.
	 * @param x : Permets d'indiquer au programme quelle sera la plus longue des deux sous-listes pour la première boucle.
	 * @param y : Permets d'indiquer au programme quelle est la plus courte des deux sous-liste pour la seconde boucle.
	 * @return resultat : Renvoie un premier resultat contenant toutes les paire faisant 10.
	 */
	private static String comparateurDeListe(Map<Integer, List<String>> tabEntree, int x, int y) {
		String resultat = "";
		for (Iterator<String> j = tabEntree.get(x).iterator(); j.hasNext();) {
			String max = j.next();
			for (Iterator<String> k = tabEntree.get(y).iterator(); k.hasNext();) {
				String min = k.next();
				if (Integer.valueOf(max) + Integer.valueOf(min) == 10) {
					resultat += max + min + "/";
					j.remove();
					k.remove();
					break;
				}
			}
		}
		return resultat;
	}

	/**
	 *
	 * Cette méthode sert à prendre les valeurs restantes dans la sous-liste et les
	 * insérer dans la reserve afin de pouvoir trier ceux qui restent dans le resultat.
	 *
	 * @param tabEntree : La liste contenant le reste des chiffres à examiner.
	 * @param reserve : La liste qui contiendra tout les chiffres n'ayant pas de paire.
	 * @param x : Permet d'indiquer au programme sur quelle sous-liste on veut travailler.
	 */
	private static void remplissageReserve(Map<Integer, List<String>> tabEntree, List<Integer> reserve, int x) {
		if (!(tabEntree.get(x).isEmpty())) {
			for (int i = 0; i < tabEntree.get(x).size(); i++) {
				reserve.add(Integer.valueOf(tabEntree.get(x).get(i)));
			}
		}
	}

	/**
	 * On parse la ligne de chiffre remplie afin de l'insérer dans une liste de
	 * 3 sous-listes contenant respectivement les chiffres 1 à 4, les chiffres 6 à 9
	 * et les chiffres 5. On vérifie au passage qu'il n'y ait que des chiffres
	 * dans ce qui est envoyé, sinon le programme se termine automatiquement en renvoyant une erreur.
	 * 
	 * @param articleEntree : La ligne de chifre que l'on veut trier de manière optimisée.
	 * @param tabEntree : La liste qui va contenir les chiffres.
	 * @throws IOException : Envoie une exception si la liste de caractère rentrée n'est pas uniquement composée de chiffres. 
	 */

	public static void  stringVersListe(String articleEntree, Map<Integer, List<String>> tabEntree) throws IOException{
			if (articleEntree.matches("^[1-9]*$")) {
				List<Character> tabTmp = new ArrayList<>();
				for (int i = 0; i < articleEntree.length(); i++) {
					tabTmp.add(articleEntree.charAt(i));
				}
				for (int i = 0; i < 3; i++) {
					List<String> list = new ArrayList<>();
					tabEntree.put(i, list);
				}
				String valueToCompare;
				for (int i = 0; i < tabTmp.size(); i++) {
					valueToCompare = tabTmp.get(i).toString();
					if (valueToCompare.matches("^[1-4]*$")) {
						tabEntree.get(0).add(String.valueOf(valueToCompare));
					} else if (valueToCompare.matches("^[6-9]*$")) {
						(tabEntree.get(1)).add(String.valueOf(valueToCompare));
					} else if (valueToCompare.matches("5")) {
						tabEntree.get(2).add(String.valueOf(valueToCompare));
					}
				}
			} else {
				throw new IOException("Ce n'est pas une suite de chiffre qui a été rentrée");
			}
	}

	/**
	 * On prend les valeurs opposées (ex: 1 et 9) afin d'avoir une somme de
	 * 10 et les ranger ensemble. Si la somme ne fait pas 10, on met la valeur
	 * dans une liste reserve et on passe à la suivante, etc etc.
	 * 
	 * @param tabEntree : La liste contenant les chiffres à trier de manière optimisée.
	 * @param reserve : La liste qui contiendra tout les chiffres n'ayant pas de paire.
	 * @return resTmp : Renvoie un premier résultat contenant toutes les paire faisant 10.
	 */
	public static String insererResultat(Map<Integer, List<String>> tabEntree, List<Integer> reserve) {
		String resTmp = "";
		if (!(tabEntree.get(0).isEmpty()) && !(tabEntree.get(1).isEmpty())) {
			if (tabEntree.get(0).size() >= tabEntree.get(1).size()) {
				resTmp = comparateurDeListe(tabEntree, 0, 1);
			} else if (tabEntree.get(0).size() < tabEntree.get(1).size()) {
				resTmp = comparateurDeListe(tabEntree, 1, 0);
			}
		}
			remplissageReserve(tabEntree, reserve, 2);
			remplissageReserve(tabEntree, reserve, 0);
			remplissageReserve(tabEntree, reserve, 1);
		return resTmp;
	}

	/**
	 * On prends les chiffres de la reserve et on les additionne ensemble. Si le
	 * résultat fait plus de 10, on met le résultat précédent dans une boite,
	 * sinon on continue jusqu'à ce que la somme soit supérieure à 10.
	 * 
	 * @param reserve : La liste qui contiendra tous les chiffres n'ayant pas de paire.
	 * @param resTmp : Le premier resultat contenant toutes les paire faisant 10.
	 * @return resultatFinal : Renvoie la version définitive du resultat.
	 */
	public static String videReserve(List<Integer> reserve, String resTmp) {
		String resultatFinal = resTmp;
		int reserveTmp = 0;
		String reserveEntree = "";
		int tailleReserve = reserve.size();
		for (int i = 0; i < tailleReserve; i++) {
			if (reserveTmp + Integer.valueOf(reserve.get(i)) <= 10) {
					reserveEntree += String.valueOf(reserve.get(i));
				reserveTmp = reserveTmp + reserve.get(i);
			} else if (reserveTmp + Integer.valueOf(reserve.get(i)) > 10) {
				resultatFinal = resultatFinal + reserveEntree + "/";
				reserveTmp = reserve.get(i);
				reserveEntree = String.valueOf(reserve.get(i));
			}
		}
		if (!(reserveEntree.isEmpty())) {
			resultatFinal = resultatFinal + reserveEntree;
		}
		return resultatFinal;
	}
}