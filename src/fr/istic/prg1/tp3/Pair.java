package fr.istic.prg1.tp3;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2015-11-10
 * <p>
 * Classe représentant des doublets non modifiables
 */

public class Pair implements Comparable<Pair> {

    private int x;
    private int y;

    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Pair d) {
        if (this.equals(d)) {
            return 0;
        } else if (this.less(d)) {
            return -1;
        } else {
            return 1;
        }
    }

    @Override
    public Pair clone() {
        return new Pair(this.x, this.y);
    }

    @Override
    public String toString() {
        return "pair: x => " + this.x + ", y => " + this.y;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (!(obj instanceof Pair)) {
            return false;
        } else {
            Pair pair2 = (Pair) obj;
            return this.x == pair2.x && this.y == pair2.y;
        }
    }

    /**
     * Verifie l'ordre de grandeur de cet objet avec celui passé en paramètre.
     *
     * @param pair objet a comparer
     * @return boolean
     * true si this est plus petit que pair. false, sinon.
     */
    public boolean less(Pair pair) {
        return (this.x < pair.x || (this.x == pair.x && this.y < pair.y));
    }
}