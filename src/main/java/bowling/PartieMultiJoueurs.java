package bowling;


import java.util.*;


public class PartieMultiJoueurs implements IPartieMultiJoueurs {


	private List<String> joueurs;
	private Map<String, PartieMonoJoueur> parties;
	private int joueurCourantIndex;
	private boolean partieDemarree;


	public PartieMultiJoueurs() {
		joueurs = new ArrayList<>();
		parties = new HashMap<>();
		joueurCourantIndex = 0;
		partieDemarree = false;
	}


	@Override
	public String demarreNouvellePartie(String[] nomsDesJoueurs) {
		if (nomsDesJoueurs == null || nomsDesJoueurs.length == 0) {
			throw new IllegalArgumentException("La liste des joueurs ne peut pas être vide.");
		}


		joueurs.clear();
		parties.clear();
		partieDemarree = true;
		joueurCourantIndex = 0;


		for (String joueur : nomsDesJoueurs) {
			joueurs.add(joueur);
			parties.put(joueur, new PartieMonoJoueur());
		}


		return getProchainTirMessage();
	}


	@Override
	public String enregistreLancer(int nombreDeQuillesAbattues) {
		if (!partieDemarree) {
			throw new IllegalStateException("La partie n'a pas encore commencé.");
		}


		String joueurCourant = joueurs.get(joueurCourantIndex);
		PartieMonoJoueur partie = parties.get(joueurCourant);


		boolean doitContinuer = partie.enregistreLancer(nombreDeQuillesAbattues);


		if (!doitContinuer) {
			joueurCourantIndex = (joueurCourantIndex + 1) % joueurs.size();
		}


		if (tousLesJoueursTermines()) {
			partieDemarree = false;
			return "Partie terminée";
		}


		return getProchainTirMessage();
	}


	@Override
	public int scorePour(String nomDuJoueur) {
		if (!parties.containsKey(nomDuJoueur)) {
			throw new IllegalArgumentException("Joueur inconnu");
		}


		return parties.get(nomDuJoueur).score();
	}


	private boolean tousLesJoueursTermines() {
		return joueurs.stream().allMatch(j -> parties.get(j).estTerminee());
	}


	private String getProchainTirMessage() {
		String joueurCourant = joueurs.get(joueurCourantIndex);
		PartieMonoJoueur partie = parties.get(joueurCourant);


		return String.format("Prochain tir : joueur %s, tour n° %d, boule n° %d",
			joueurCourant,
			partie.numeroTourCourant(),
			partie.numeroProchainLancer());
	}
}
