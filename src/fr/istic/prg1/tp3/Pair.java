package fr.istic.prg1.tp3;

/**
 * 
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2015-11-10
 * 
 *        Classe représentant des doublets non modifiables
 */

public class Pair implements Comparable<Pair> {

	private int x;
	private int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public int compareTo(Pair d) {
		if (equals(d)) {
			return 0;
		}

		if (this.less(d)) {
		    return -1;
        }

        return 1;
	}

	@Override
	public Pair clone() {
	    return this;
	}

	@Override
	public String toString() {
	    return null;
	}

	@Override
	public boolean equals(Object obj) {
		return  (this.x == ((Pair) obj).getX() && this.y == ((Pair) obj).getY());
	}

    /**
     * Verifie l'ordre de grandeur de cet objet avec celui passé en paramètre.
     *
     * @param pair
     * @return
     */
	public boolean less(Pair pair) {
	    return (this.x < pair.getX() || (this.x == pair.getX() && this.y < pair.getY()));
    }
}