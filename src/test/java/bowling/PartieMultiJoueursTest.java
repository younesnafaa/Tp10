package bowling;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;


public class PartieMultiJoueursTest {


	@Test
	public void testDemarreNouvellePartie() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		String message = partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"});


		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 1", message);
	}


	@Test
	public void testEnregistreLancer() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"});


		String message = partie.enregistreLancer(5);
		assertEquals("Prochain tir : joueur Pierre, tour n° 1, boule n° 2", message);


		message = partie.enregistreLancer(3);
		assertEquals("Prochain tir : joueur Paul, tour n° 1, boule n° 1", message);
	}


//	@Test
//	public void testScorePour() {
//		PartieMultiJoueurs partie = new PartieMultiJoueurs();
//		partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"});
//
//
//		partie.enregistreLancer(10); // Strike pour Pierre
//		partie.enregistreLancer(7);
//		partie.enregistreLancer(2);
//
//
//		assertEquals(19, partie.scorePour("Pierre"));
//		assertEquals(9, partie.scorePour("Paul"));
//	}


	@Test
	public void testJoueurInconnu() {
		PartieMultiJoueurs partie = new PartieMultiJoueurs();
		partie.demarreNouvellePartie(new String[]{"Pierre", "Paul"});


		Exception exception = assertThrows(IllegalArgumentException.class, () -> {
			partie.scorePour("Jacques");
		});


		assertEquals("Joueur inconnu", exception.getMessage());
	}
}
