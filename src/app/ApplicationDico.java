package app;

import java.io.*;

import metier.Dico;

public class ApplicationDico {

	Dico dico;

	@SuppressWarnings("unused")
	public static void main(String args[]) {
		// l'utilisateur doit passer en paramètre de la ligne de commande
		// le chemin complet du fichier assurant la persistance
		if (args.length != 1) {
			System.err.println("Erreur : paramètre ");
			System.exit(0);
		}

		// Chemin absolu à indiquer
		// args[0] = /**/com.nsis.EntreesSorties/resources/dico.txt

		// instanciation d'un objet dictionnaire
		ApplicationDico dico = new ApplicationDico(args[0]);
	}

	public ApplicationDico(String fichierSource) {
		dico = new Dico(fichierSource);
		afficherMenu();
	}

	private void consulter(BufferedReader flux) throws IOException {
		System.out.println("\nConsultation. \nEntrer une clef : ");
		// lecture de la clef
		String clef = flux.readLine();

		// affichage de la valeur
		System.out.println("Valeur associée :");
		System.out.println(dico.consulter(clef));
	}

	private void creer(BufferedReader flux) throws IOException {
		System.out.println("\nCréation. \nEntrer une clef : ");
		// lecture de la clef
		String clef = flux.readLine();

		System.out.println("Entrer une valeur :");
		// lecture de la valeur
		String valeur = flux.readLine();

		dico.creer(clef, valeur);
	}

	private void listerStdout() {
		System.out.println("\nInformations contenues dans le dictionnaire");
		// affichage des associations du dictionnaire dans le flux standard de
		// sortie
		dico.listerStdout();
	}

	private void fin() {
		System.out.println("\n\n\nFin normale d'exécution");
		// fin normale d'exécution
		System.exit(0);
	}

	@SuppressWarnings("deprecation")
	private void afficherMenu() {
		// chargement du dictionnaire
		try {
			dico.charger();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true) {
			// menu des choix possibles
			System.out.println("\nMenu de l'application Dico");
			System.out.println("1- Consulter une association");
			System.out.println("2- Ajouter une association");
			System.out.println("3- Lister les associations à l'écran");
			System.out.println("4- Terminer l'exécution");
			try {
				// lecture est la variable référençant le flux standard d'entrée
				BufferedReader flux = new BufferedReader(new InputStreamReader(System.in));
				String choix = flux.readLine();
				switch (new Integer(choix).intValue()) {
				case 1:
					consulter(flux);
					break;
				case 2:
					creer(flux);
					break;
				case 3:
					listerStdout();
					break;
				case 4:
					fin();
				default:
					System.out.println("Erreur : 1-4");
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NumberFormatException e) {
				System.out.println("Erreur : 1-4");
			}
		}
	}// main(String[])

}