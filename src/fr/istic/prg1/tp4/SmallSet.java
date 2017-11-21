package fr.istic.prg1.tp4;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 4.0
 * @since 2015-06-15
 */

public class SmallSet {

	/**
	 * taille de l'ensemble
	 */
	private static final int setSize = 256;

	/**
	 * Ensemble est représenté comme un tableau de booléens.
	 */
	private boolean[] tab = new boolean[SmallSet.setSize];

	public SmallSet() {
		for (int i = 0; i < SmallSet.setSize; ++i) {
			tab[i] = false;
		}
	}

	public SmallSet(boolean[] t) {
		for (int i = 0; i < SmallSet.setSize; ++i) {
			tab[i] = t[i];
		}
	}

	/**
	 * @return nombre de valeurs appartenant à l’ensemble
	 */
	public int size() {
		int cpt = 0;
		for (int i = 0; i < SmallSet.setSize; i++) {
			if (this.contains(i)) {
				cpt++;
			}
		}
		return cpt;
	}

	/**
	 * @param x
	 *            valeur à tester
	 * @return true, si l’entier x appartient à l’ensemble, false sinon
	 */
	public boolean contains(int x) {
		return (x >= 0 && x < SmallSet.setSize) && this.tab[x];
	}

	/**
	 * @return true, si l’ensemble est vide, false sinon
	 */
	public boolean isEmpty() {
		return this.size() == 0;
	}

	/**
	 * Ajoute x à l’ensemble (sans effet si x déjà présent)
	 *
	 * @param x
	 *            valeur à ajouter
	 * @pre 0 <= x <= 255
	 */
	public void add(int x) {
		if ((x >= 0 && x < SmallSet.setSize) && !this.contains(x)) {
			this.tab[x] = true;
		}
	}

	/**
	 * Retire x de l’ensemble (sans effet si x n’est pas présent)
	 *
	 * @param x
	 *            valeur à supprimer
	 * @pre 0 <= x <= 255
	 */
	public void remove(int x) {
        if ((x >= 0 && x < SmallSet.setSize) && this.contains(x)) {
            this.tab[x] = false;
        }
	}

	/**
	 * Ajoute à l’ensemble les valeurs deb, deb+1, deb+2, ..., fin.
	 *
	 * @param deb
	 *            début de l’intervalle
	 * @param fin
	 *            fin de l’intervalle
	 * @pre 0 <= begin <= end <= 255
	 */
	public void addInterval(int deb, int fin) {
		if (deb >= 0 && deb <= fin && fin < SmallSet.setSize) {
			for (int i = deb; i <= fin; i++) {
				this.add(i);
			}
		}
	}

	/**
	 * Retire de l’ensemble les valeurs deb, deb+1, deb+2, ..., fin.
	 *
	 * @param deb
	 *            début de l’intervalle
	 * @param fin
	 *            fin de l’intervalle
	 * @pre 0 <= begin <= end <= 255
	 */
	public void removeInterval(int deb, int fin) {
		if (deb >= 0 && deb <= fin && fin < SmallSet.setSize) {
			for (int i = deb; i <= fin; i++) {
				this.remove(i);
			}
		}
	}

	/**
	 * this devient l'union de this et set2
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void union(SmallSet set2) {
		for (int i = 0; i < SmallSet.setSize; i++) {
			this.tab[i] = this.contains(i) || set2.contains(i);
		}
	}

	/**
	 * this devient l'intersection de this et set2
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void intersection(SmallSet set2) {
		for (int i = 0; i < SmallSet.setSize; i++) {
			this.tab[i] = this.contains(i) && set2.contains(i);
		}
	}

	/**
	 * this devient la différence de this et set2
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void difference(SmallSet set2) {
		for (int i = 0; i < SmallSet.setSize; i++) {
			this.tab[i] = this.contains(i) && !set2.contains(i);
		}
	}

	/**
	 * this devient la différence symétrique de this et set2
	 * 
	 * @param set2
	 *            deuxième ensemble
	 */
	public void symmetricDifference(SmallSet set2) {
		for (int i = 0; i < SmallSet.setSize; i++) {
			this.tab[i] = this.contains(i) ^ set2.contains(i);
		}
	}

	/**
	 * this devient le complément de this.
	 */
	public void complement() {
		for (int i = 0; i < SmallSet.setSize; i++) {
			this.tab[i] = !this.tab[i];
		}
	}

	/**
	 * this devient l'ensemble vide.
	 */
	public void clear() {
		for (int i = 0; i < SmallSet.setSize; i++) {
			this.tab[i] = false;
		}
	}

	/**
	 * @param set2
	 *            second ensemble
	 * @return true, si this est inclus dans set2, false sinon
	 */
	public boolean isIncludedIn(SmallSet set2) {
		// this est inclus dans set2 si chaque élémént de this appartient à set2
        // Il suffit de trouver un élémént de this qui n'appartient pas à set2 pour
        // prouver que this n'est pas inclus dans set2.
		for (int i = 0; i < SmallSet.setSize; i++) {
			if (this.contains(i) && set2.contains(i)) {
				return false;
			}
		}

		return true;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SmallSet)) {
			return false;
		}
		// il reste le cas quand obj est un SmallSet
		if (this.size() != ((SmallSet) obj).size()) {
			return false;
		}

		// this est égal à obj si les éléments de this et de obj sont identiques.
		// Il suffit de trouver un élément de this (ou obj) qui n'appartient pas à obj (ou this)
		for (int i = 0; i < SmallSet.setSize; i++) {
			if ((this.contains(i) && !((SmallSet) obj).contains(i)) || (!this.contains(i) && ((SmallSet) obj).contains(i))) {
						return false;
			}

		}

		return true;
	}

	/**
	 * @return copie de this
	 */
	public SmallSet clone() {
		return new SmallSet(this.tab);
	}

	@Override
	public String toString() {
		String s;
		s = "elements presents : ";
		for (int i = 0; i < SmallSet.setSize; ++i) {
			if (tab[i]) {
				s = s + i + " ";
			}
		}
		return s;
	}
}
