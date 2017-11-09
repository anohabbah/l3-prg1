package fr.istic.prg1.tp3.test;

import org.junit.Test;

import fr.istic.prg1.tp3.Fourmis;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 2.0
 * @since 2015-06-15
 * 
 *        Classe contenant les tests unitaires pour la classe Fourmis.
 */

public class TestFourmis {

	@Test
	public void testNextTerm1() {
		String s0 = "1";
		String result = Fourmis.next(s0);
		String trueResult = "11";
		assertTrue(result.equals(trueResult));
	}

	@Test
	public void testNextTerm2() {
		String u8 = "31131211131221";
		String u9 = "13211311123113112211";
		String trueU9 = Fourmis.next(u8);
		assertTrue(u9.equals(trueU9));
	}

	/*************************************************
	 * Nos propres tests commence ici
	 *************************************************/
	@Test
	public void testNextTerm3() {
		String u0 = "a";
		String u1 = "1a";
		String trueU1 = Fourmis.next(u0);
		assertTrue("Test avec 1 caractère de l'alphabet", u1.equals(trueU1));
	}

	@Test
	public void testNextTerm4() {
		String u0 = "1aa113";
		String u1 = "112a2113";
		String trueU1 = Fourmis.next(u0);
		assertTrue("Test avec plusieurs caractères de l'alphabet", u1.equals(trueU1));
	}
}
