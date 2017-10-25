package fr.istic.prg1.list;

import fr.istic.prg1.list.util.SmallSet;
import fr.istic.prg1.list.util.SuperT;

/**
 * Classe représentant les sous-ensembles de la classe MySet.
 * 
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 4.0
 * @since 2015-06-15
 */

public class SubSet implements SuperT{

	public final int rank;
	public SmallSet set;

	public SubSet() {
		rank = 0;
		set = new SmallSet();
	}

	public SubSet(int r, SmallSet f) {
		rank = r;
		set = f;
	}

	@Override
	public SubSet clone() {
		return new SubSet(rank, set.clone());
	}

	@Override
	public String toString() {
		return "Subset [rank=" + rank + ", set=" + set + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + rank;
		result = prime * result + ((set == null) ? 0 : set.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof SubSet)) {
			return false;
		}
		SubSet other = (SubSet) obj;
		return rank == other.rank && set.equals(other.set);
	}
}