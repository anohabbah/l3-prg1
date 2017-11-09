package fr.istic.prg1.tp3.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.junit.Test;

import fr.istic.prg1.tp3.InsertionInteger;


/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 2.1
 * @since 2015-10-07
 * 
 *        Classe contenant les tests unitaires pour la classe InsertionInteger.
 */

public class TestInsertionInteger {

	public static void printArray(int[] array) {
		for (int i = 0; i < array.length; ++i) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}

	private static boolean equalIntegerArrays(int[] array1, int[] array2) {
		int length1 = array1.length;
		int length2 = array2.length;
		if (length1 != length2) {
			return false;
		}
		int i = 0;
		while (i < length1 && array1[i] == array2[i]) {
			++i;
		}
		return (i == length1);
	}

	@Test
	public void testInsertionInteger1() {
		InsertionInteger insert = new InsertionInteger();
		boolean result = insert.insert(36);
		assertTrue(result);
		result = insert.insert(36);
		assertFalse(result);
	}

	@Test
	public void testInsertionInteger2() {
		InsertionInteger ourArray = new InsertionInteger();
		int[] tabVide = new int[0];
		assertTrue(equalIntegerArrays(ourArray.toArray(), tabVide));
	}

	@Test
	public void testInsertionInteger3() {
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.insert(36);
		int[] tab = { 36 };
		assertTrue(ourArray.toString(),
				equalIntegerArrays(ourArray.toArray(), tab));
	}

	@Test
	public void testInsertionInteger4() throws FileNotFoundException {
		int[] realArray = { 1, 2, 3, 11, 22, 33 };
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		assertTrue("courte liste, test 1",
				equalIntegerArrays(ourArray.toArray(), realArray));
		scanner.close();
	}

	@Test
	public void testInsertionInteger5() throws FileNotFoundException {
		int[] realArray = { 1, 2, 3, 5, 11, 22, 33 };
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		ourArray.insert(5);
		assertTrue("courte liste, test 2, un ajout de plus",
				equalIntegerArrays(ourArray.toArray(), realArray));
		scanner.close();
	}

	@Test
	public void testInsertionInteger6() throws FileNotFoundException {
		int[] realArray = { 1, 2, 3, 5, 11, 22, 33 };
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		ourArray.insert(5);
		ourArray.insert(5);
		assertTrue("courte liste, test 3, m�me ajout une 2e fois",
				equalIntegerArrays(ourArray.toArray(), realArray));
		scanner.close();
	}

	@Test
	public void testInsertionInteger7() throws FileNotFoundException {
		int[] realArray = { 1, 2, 3, 5, 6, 11, 22, 33 };
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		ourArray.insert(5);
		ourArray.insert(5);
		ourArray.insert(6);
		assertTrue("courte liste, test 4, un ajout de plus",
				equalIntegerArrays(ourArray.toArray(), realArray));
		scanner.close();
	}

	@Test
	public void testInsertionInteger8() throws FileNotFoundException {
		File file = new File("fichier1.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		ourArray.insert(5);
		boolean b1 = ourArray.insert(5);
		assertTrue("insertion, test 8, insertion 2e fois", !b1);
		boolean b2 = ourArray.insert(6);
		assertTrue("insertion, test 8, insertion 1e fois", b2);
		scanner.close();
	}

	@Test
	public void testInsertionInteger9() throws FileNotFoundException {
		int[] array = { 4, 5, 6, 11, 12, 45, 54, 87, 89, 545 };
		File file = new File("fichier2.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		assertTrue("une longue liste d'entiers avec doublons",
				equalIntegerArrays(ourArray.toArray(), array));
		scanner.close();

	}

	@Test
	public void testInsertionInteger10() throws FileNotFoundException {
		int[] array = { 4, 5, 6, 11, 12, 45, 54, 87, 89, 545 };
		File file = new File("fichier2.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		ourArray.insert(4);
		assertTrue("insertion dans une liste pleine 1",
				equalIntegerArrays(ourArray.toArray(), array));
		scanner.close();

	}

	@Test
	public void testInsertionInteger11() throws FileNotFoundException {
		File file = new File("fichier2.txt");
		Scanner scanner = new Scanner(file);
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.createArray(scanner);
		boolean b = ourArray.insert(4);
		assertTrue("insertion dans une liste pleine 2", !b);
		scanner.close();
	}

	/*****************************************************
	 * Nos tests commenecent ici
	 *****************************************************/
	// Ce n'est pas vraiment notre test, mais nous l'avons decommenté pour le prendre en compte
	@Test
	public void testInsertionInteger12() {
		InsertionInteger ourArray = new InsertionInteger();
		ourArray.insert(-4);
		assertTrue("insertion d'un nombre négatif",
				ourArray.toArray().length == 0);
	}

}