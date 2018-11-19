package metier;

import java.util.*;
import java.io.*;

public class Dico {
	
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private String fichierSource;
	private HashMap<String, String> annuaire;

	public Dico(String chemin) {
		fichierSource = chemin;
		annuaire = new HashMap<String, String>();
	}

	public void charger() throws IOException {
		// Chargement du dictionnaire : lire le fichier et le stocker dans un hashMap
		try {
			InputStream flux = new FileInputStream(fichierSource); 
			InputStreamReader lecture = new InputStreamReader(flux);
			BufferedReader buff = new BufferedReader(lecture);
			String ligne;
			while ((ligne = buff.readLine()) != null) {
				StringTokenizer st = new StringTokenizer(ligne, "/");
				annuaire.put(st.nextToken(), st.nextToken());
			}
			buff.close(); 
		} catch (IOException e){
			System.out.println(e.toString());
		}
	}

	public String consulter(String clef) {
		// Consulter une association
		try {
			return annuaire.get(clef);
		} catch (Exception e) {
			return e.getMessage();
		}
	}

	public void creer(String clef, String valeur) throws IOException {
		// Créer une association
		annuaire.put(clef, valeur);
		// Ajouter l'association à la fin du fichier
		FileWriter fw = new FileWriter(fichierSource, true);
		fw.write(clef + "/" + valeur + "\n");
		fw.close();
		this.charger();
	}

	@SuppressWarnings("rawtypes")
	public void listerStdout() {
		// Lister les associations à l'écran
		Iterator iterator = annuaire.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry mapEntry = (Map.Entry) iterator.next();
			System.out.println("Clé: " + mapEntry.getKey() + ",Valeur :" + mapEntry.getValue());
		}
	}

}