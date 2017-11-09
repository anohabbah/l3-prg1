package fr.istic.prg1.tp3.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import fr.istic.prg1.tp3.InsertionPair;
import fr.istic.prg1.tp3.Pair;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 2.1
 * @since 2015-10-07
 * 
 *        Classe contenant les tests unitaires pour la classe InsertionPair.
 */

public class TestInsertionPair {

	private static boolean equalPairArrays(Pair[] array1, Pair[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		if (length1 != length2) {
			System.out.println("Taille");
			return false;
		}
		boolean res = true;
		for (int i = 0; i < length1 && res; ++i) {
			if (array1[i] == null && array2[i] != null) {
				res = false;
			}
			if (array2[i] == null && array1[i] != null) {
				res = false;
			}
			if (array1[i] != null && array2[i] != null
					&& !array1[i].equals(array2[i])) {
				res = false;
			}
		}
		return res;
	}

	@Test
	public void testInsertionPair1() throws FileNotFoundException {
		Pair pair0 = new Pair(5, 5);
		Pair pair1 = new Pair(6, 89);
		Pair pair2 = new Pair(11, 12);
		Pair pair3 = new Pair(12, 12);
		Pair pair4 = new Pair(12, 45);
		Pair pair5 = new Pair(45, 4);
		Pair pair6 = new Pair(45, 45);
		Pair pair7 = new Pair(54, 7);
		Pair pair8 = new Pair(87, 4);
		Pair pair9 = new Pair(545, 87);
		Pair[] realArray = { pair0, pair1, pair2, pair3, pair4, pair5, pair6,
				pair7, pair8, pair9 };

		File file = new File("fichier2.txt");
		Scanner scanner = new Scanner(file);
		InsertionPair ourArray = new InsertionPair();
		ourArray.createArray(scanner);
		assertTrue("une longue liste de doublets",
				equalPairArrays(ourArray.toArray(), realArray));

		scanner.close();

	}

	@Test
	public void testInsertionPair2() throws FileNotFoundException {
		Pair pair10 = new Pair(1, 2);
		Pair pair11 = new Pair(2, 3);
		Pair pair12 = new Pair(11, 22);
		Pair pair13 = new Pair(33, 1);
		Pair[] realArray = { pair10, pair11, pair12, pair13 };
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		InsertionPair ourArray = new InsertionPair();
		ourArray.createArray(scanner);
		assertTrue(
				"une courte liste de doublets avec doublons et -1 en position impaire",
				equalPairArrays(ourArray.toArray(), realArray));
		scanner.close();
	}

	@Test
	public void testInsertionPair3() throws FileNotFoundException {
		Pair pair10 = new Pair(1, 2);
		Pair pair11 = new Pair(2, 3);
		Pair pair12 = new Pair(11, 22);
		Pair pair13 = new Pair(33, 1);
		Pair[] realArray = { pair10, pair11, pair12, pair13 };
		File file = new File("fichier3.txt");
		// juste une nouvelle avant-derni�re valeur
		Scanner scanner = new Scanner(file);
		InsertionPair ourArray = new InsertionPair();
		ourArray.createArray(scanner);
		assertTrue(
				"une courte liste de doublets avec doublons et -1 en position paire",
				equalPairArrays(ourArray.toArray(), realArray));
		scanner.close();
	}

	@Test
	public void testInsertionPair4() {
		InsertionPair ourArray = new InsertionPair();
		boolean res = ourArray.insert(new Pair(36, 28));
		assertTrue(ourArray.toString(), res);
		res = ourArray.insert(new Pair(36, 28));
		assertFalse(ourArray.toString(), res);
	}

	@Test
	public void testInsertionPair5() throws FileNotFoundException {
		File file = new File("fichier4.txt");
		// juste une nouvelle avant-derni�re valeur
		Scanner scanner = new Scanner(file);
		InsertionPair ourArray = new InsertionPair();
		ourArray.createArray(scanner);
		assertTrue("liste vide", ourArray.toArray().length == 0);
	}

//	@Test
//	public void testPair() {
//		Pair p = new Pair(1, 2);
//		assertFalse(p.toString().startsWith("fr.istic.prg1.tp3.version2015"));
//	}

}