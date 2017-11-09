package fr.istic.prg1.tp3.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import java.util.List;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 2.0
 * @since 2015-06-15
 * 
 *        Classe lançant les tests unitaires pour les classes Fourmis,
 *        InsertionInteger et InsertionPair.
 */

public class TestRunner {

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(TestFourmis.class);
		List<Failure> listOfFailures = result.getFailures();
		for (Failure failure : listOfFailures) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());

		result = JUnitCore.runClasses(TestInsertionInteger.class);
		listOfFailures = result.getFailures();
		for (Failure failure : listOfFailures) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());

		result = JUnitCore.runClasses(TestInsertionPair.class);
		listOfFailures = result.getFailures();
		for (Failure failure : listOfFailures) {
			System.out.println(failure.toString());
		}
		System.out.println(result.wasSuccessful());
	}
}
