package fr.istic.prg1.tree;

import java.util.Scanner;

import fr.istic.prg1.tree.util.AbstractImage;
import fr.istic.prg1.tree.util.Iterator;
import fr.istic.prg1.tree.util.Node;
import fr.istic.prg1.tree.util.NodeType;

/**
 * @author Micka√´l Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2016-04-20
 * 
 *        Classe d√©crivant les images en noir et blanc de 256 sur 256 pixels
 *        sous forme d'arbres binaires.
 * 
 */

public class Image extends AbstractImage {
	private static final Scanner standardInput = new Scanner(System.in);

	public Image() {
		super();
	}

	public static void closeAll() {
		standardInput.close();
	}

	/**
	 * @param x
	 *            abscisse du point
	 * @param y
	 *            ordonn√©e du point
	 * @pre !this.isEmpty()
	 * @return true, si le point (x, y) est allum√© dans this, false sinon
	 */
	@Override
	public boolean isPixelOn(int x, int y) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	    return false;
	}

	/**
	 * this devient identique √† image2.
	 *
	 * @param image2
	 *            image √† copier
	 *
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void affect(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient rotation de image2 √† 180 degr√©s.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate180(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient rotation de image2 √† 90 degr√©s dans le sens des aiguilles
	 * d'une montre.
	 *
	 * @param image2
	 *            image pour rotation
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void rotate90(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction non demeandÈe");
		System.out.println("-------------------------------------------------");
		System.out.println();	    
	}

	/**
	 * this devient inverse vid√©o de this, pixel par pixel.
	 *
	 * @pre !image.isEmpty()
	 */
	@Override
	public void videoInverse() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient image miroir verticale de image2.
	 *
	 * @param image2
	 *            image √† agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorV(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient image miroir horizontale de image2.
	 *
	 * @param image2
	 *            image √† agrandir
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void mirrorH(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient quart sup√©rieur gauche de image2.
	 *
	 * @param image2
	 *            image √† agrandir
	 * 
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Le quart sup√©rieur gauche de this devient image2, le reste de this
	 * devient √©teint.
	 * 
	 * @param image2
	 *            image √† r√©duire
	 * @pre !image2.isEmpty()
	 */
	@Override
	public void zoomOut(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'intersection de image1 et image2 au sens des pixels
	 * allum√©s.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void intersection(AbstractImage image1, AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * this devient l'union de image1 et image2 au sens des pixels allum√©s.
	 * 
	 * @pre !image1.isEmpty() && !image2.isEmpty()
	 * 
	 * @param image1 premiere image
	 * @param image2 seconde image
	 */
	@Override
	public void union(AbstractImage image1, AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	}

	/**
	 * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
	 * 
	 * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
	 *         sont allum√©s dans this, false sinon
	 */
	@Override
	public boolean testDiagonal() {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	    return false;
	}

	/**
	 * @param x1
	 *            abscisse du premier point
	 * @param y1
	 *            ordonn√©e du premier point
	 * @param x2
	 *            abscisse du deuxi√®me point
	 * @param y2
	 *            ordonn√©e du deuxi√®me point
	 * @pre !this.isEmpty()
	 * @return true si les deux points (x1, y1) et (x2, y2) sont repr√©sent√©s par
	 *         la m√™me feuille de this, false sinon
	 */
	@Override
	public boolean sameLeaf(int x1, int y1, int x2, int y2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
		return false;
	}

	/**
	 * @param image2
	 *            autre image
	 * @pre !this.isEmpty() && !image2.isEmpty()
	 * @return true si this est incluse dans image2 au sens des pixels allum√©s
	 *         false sinon
	 */
	@Override
	public boolean isIncludedIn(AbstractImage image2) {
		System.out.println();
		System.out.println("-------------------------------------------------");
		System.out.println("Fonction ‡ Ècrire");
		System.out.println("-------------------------------------------------");
		System.out.println();
	    return false;
	}

}
