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
            this.current = flag.right;
        }

        @Override
        public void goForward() {
            try {
                assert this.current.right != null : "Impossible d'avancer, le voisin droit n'existe pas.";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.current = this.current.right;
        }

        @Override
        public void goBackward() {
            try {
                assert this.current.left != null : "Impossible d'avancer, le voisin de gauche n'existe pas.";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.current = this.current.left;
        }

        @Override
        public void restart() {
            this.current = flag.right;
        }

        @Override
        public boolean isOnFlag() {
            return this.current == flag;
        }

        @Override
        public void remove() {
            try {
                assert this.current != flag : "\n\n\nimpossible de retirer le drapeau\n\n\n";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            Element temp = this.current;
            Element right = this.current.right;
            Element left = this.current.left;
            right.left = left;
            left.right = right;
            this.current = this.current.right;
            temp.left = null;
            temp.right = null;
        }

        @Override
        public T getValue() {
            return this.current.value;
        }

        @Override
        public T nextValue() {
            try {
                assert this.current.right != null : "Impossible d'avancer, le voisin de droite n'existe pas.";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.goForward();
            return this.getValue();
        }

        @Override
        public void addLeft(T v) {}

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
        this.flag.left = null;
        this.flag.value = null;
        this.flag.right = null;
    }

    public ListIterator iterator() {
        return new ListIterator();
    }

    public boolean isEmpty() {
        return this.flag.left == this.flag;
    }

    public void clear() {
        this.flag.left = this.flag;
        this.flag.right = this.flag;
    }

    public void setFlag(T v) {
        this.flag.value = v;
    }

    public void addHead(T v) {
        List.ListIterator it = this.iterator();
        it.addLeft(v);
    }

    public void addTail(T v) {
        List.ListIterator it = this.iterator();
        it.goBackward();
        it.addLeft(v);
    }

    @SuppressWarnings("unchecked")
    public List<T> clone() {
        List<T> nouvListe = new List<T>();
        ListIterator p = this.iterator();

        while (!p.isOnFlag()) {
            nouvListe.addTail((T) p.getValue().clone());
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
