package voyages;

import java.util.*;

public class XSpeedit {

	private XSpeedit() {
	}

	/**
	 * Permet de mettre la ligne de caractères reçue en entrée dans un tableau
	 * d'une ligne trié par ordre croissant
	 * 
	 * @param articleEntree
	 *            : Ligne de caractères entrée par l'utilisateur
	 * @return tableauEntree : tableau une ligne contenant un caractère de la
	 *         ligne dans chaque colonne
	 */
	private static String[] triEntree(String articleEntree) {
		String[] tableauEntree = articleEntree.split("");
		Arrays.sort(tableauEntree);
		return tableauEntree;
	}

	/**
	 * Permet d'effectuer le tri optimisé. On parcourt le tableau en commençant
	 * par le haut, et on compare avec les premières entrées. Si la somme des
	 * valeurs fait moins de 10, on continue d'ajouter jusqu'à ce que cela fasse
	 * 10 ou plus. Une fois cette limite atteint, on insère cela dans une liste,
	 * et on recommence.
	 * 
	 * @param articleEntree
	 *            : Un tableau contenant les chiffres entrés par l'utilisateur
	 * @return resultatTmp : le resultat du tri sous forme de liste.
	 */
	private static List<String> premierTri(String[] articleEntree) {
		int i = 0;
		String listeTmp = "";
		int tailleEntree = articleEntree.length;
		List<String> resultatTmp = new ArrayList<>();
		int taillelisteTmp = 0;
		for (int j = tailleEntree - 1; j >= 0; j--) {
			taillelisteTmp = Integer.valueOf(articleEntree[i])
					+ Integer.valueOf(articleEntree[j]);
			if (i < j && taillelisteTmp <= 10) {
				listeTmp = articleEntree[i] + articleEntree[j];
				i++;
				while (i < j
						&& taillelisteTmp + Integer.valueOf(articleEntree[i]) <= 10) {
					listeTmp += articleEntree[i];
					taillelisteTmp += Integer.valueOf(articleEntree[i]);
					i++;
				}
				resultatTmp.add(listeTmp);
			} else if (i < j || i == j && tailleEntree % 2 == 1) {
				resultatTmp.add(articleEntree[j]);
			}
		}
		return resultatTmp;
	}

	/**
	 * Permets d'insérer les données de la liste dans une chaine de caractères
	 * sous la forme ab/cd/ à l'aide d'une boucle foreach sur la liste
	 * 
	 * @param resultatTmp
	 *            : Une liste de caractères
	 * @return resultatFinal : Le contenu de la liste de caractères en entrée
	 *         séparé par des "/"
	 */
	private static String fusionResultat(List<String> resultatTmp) {
		StringBuffer resultatFinal = new StringBuffer();
		for (Iterator<String> i = resultatTmp.iterator(); i.hasNext();) {
			resultatFinal.append(i.next());
			if (i.hasNext()) {
				resultatFinal.append("/");
			}
		}
		return resultatFinal.toString();
	}

	/**
	 * Permets d'utiliser toutes les méthodes private d'un seul coup afin de
	 * générer un tri optimisé d'une suite de chiffre.
	 * 
	 * @param articleEntree
	 *            : la chaine de caractère rentrée par l'utilisateur
	 * @return resultatFinal : La chaine affichée avec un tri optimisé
	 */
	public static String triOpti(String articleEntree) {

		String[] tableauEntree = triEntree(articleEntree);
		List<String> resultatTmp = premierTri(tableauEntree);
		String resultatFinal = fusionResultat(resultatTmp);
		return resultatFinal;
	}
}
