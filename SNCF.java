package voyages;
import java.io.IOException;
import java.util.*;

public class SNCF {

	public static void main(String[] args) throws IOException {
		String articleEntree = "";
		Map<Integer, List<String>> tabEntree = new HashMap<Integer, List<String>>();
		System.out.print("Veuillez saisir vos boites à trier :");
		//on utilise la fonction scanner pour prendre la ligne à trier
		Scanner scanner = new Scanner(System.in);
		scanner.useDelimiter("[1-9]");
		articleEntree = scanner.nextLine();
		scanner.close();
		List<Integer> reserve = new ArrayList<Integer>();
		String resultat = "";
		XSpeedit.stringVersListe(articleEntree, tabEntree);
		resultat = XSpeedit.insererResultat(tabEntree, reserve);
		resultat = XSpeedit.videReserve(reserve, resultat);
		System.out.print("Voici le tri optimisé : " + resultat);
		return;
	}

}