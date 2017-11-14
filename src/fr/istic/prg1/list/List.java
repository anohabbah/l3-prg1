package fr.istic.prg1.list;

import fr.istic.prg1.list.util.*;

public class List<T extends SuperT> {
	// liste en double chainage par references

	private class Element {
		// element de List<Item> : (Item, Element, Element)
		public T value;
		public Element left, right;

		public Element() {
			value = null;
			left = null;
			right = null;
		}
	} // class Element

	public class ListIterator implements Iterator<T> {
		private Element current;

		private ListIterator() {
		}

		@Override
		public void goForward() {
		}

		@Override
		public void goBackward() {
		}

		@Override
		public void restart() {
		}

		@Override
		public boolean isOnFlag() {
			return false;
		}

		@Override
		public void remove() {
			try {
				assert current != flag : "\n\n\nimpossible de retirer le drapeau\n\n\n";
			} catch (AssertionError e) {
				e.printStackTrace();
				System.exit(0);
			}
		}

		@Override
		public T getValue() {
			return null;
		}

		@Override
		public T nextValue() {
			return null;
		}

		@Override
		public void addLeft(T v) {
		}

		@Override
		public void addRight(T v) {
		}

		@Override
		public void setValue(T v) {
		}

		@Override
		public String toString() {
			return "parcours de liste : pas d'affichage possible \n";
		}

	} // class IterateurListe

	private Element flag;

	public List() {
	}

	public ListIterator iterator() {
		return null;
	}

	public boolean isEmpty() {
		return false;
	}

	public void clear() {
	}

	public void setFlag(T v) {
	}

	public void addHead(T v) {
	}

	public void addTail(T v) {
	}

	@SuppressWarnings("unchecked")
	public List<T> clone() {
		List<T> nouvListe = new List<T>();
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			nouvListe.addTail((T) p.getValue().clone());
			// UNE COPIE EST NECESSAIRE !!!
			p.goForward();
		}
		return nouvListe;
	}

	@Override
	public String toString() {
		String s = "contenu de la liste : \n";
		ListIterator p = iterator();
		while (!p.isOnFlag()) {
			s = s + p.getValue().toString() + " ";
			p.goForward();
		}
		return s;
	}
}
