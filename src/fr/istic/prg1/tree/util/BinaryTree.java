/**
 * ANOH Abbah
 *    &
 * KOBENAN Sébastien
 */
package fr.istic.prg1.tree.util;

import java.util.Stack;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 4.0
 * @since 2015-06-15
 * @param <T>
 *            type formel d'objet pour la classe
 *
 *            Les arbres binaires sont construits par chaînage par références
 *            pour les fils et une pile de pères.
 */
public class BinaryTree<T> {

    /**
     * Type représentant les noeuds.
     */
    private class Element {
        public T value;
        public Element left, right;

        public Element() {
            value = null;
            left = null;
            right = null;
        }

        public boolean isEmpty() {
            return left == null && right == null;
        }
    }

    private Element root;

    public BinaryTree() {
        this.root = new Element();
    }

    /**
     * @return Un nouvel iterateur sur l'arbre this. Le noeud courant de
     *         l’itérateur est positionné sur la racine de l’arbre.
     */
    public TreeIterator iterator() {
        return new TreeIterator();
    }

    /**
     * @return true si l'arbre this est vide, false sinon
     */
    public boolean isEmpty() {
        return this.root.isEmpty();
    }

    /**
     * Classe représentant les itérateurs sur les arbres binaires.
     */
    public class TreeIterator implements Iterator<T> {
        private Element currentNode;
        private Stack<Element> stack;

        private TreeIterator() {
            stack = new Stack<Element>();
            currentNode = BinaryTree.this.root;
        }

        /**
         * L'itérateur se positionnne sur le fils gauche du noeud courant.
         *
         * @pre Le noeud courant n’est pas un butoir.
         */
        @Override
        public void goLeft() {
            try {
                assert !this.isEmpty() : "le butoir n'a pas de fils";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.stack.push(this.currentNode);
            this.currentNode = this.currentNode.left;
        }

        /**
         * L'itérateur se positionnne sur le fils droit du noeud courant.
         *
         * @pre Le noeud courant n’est pas un butoir.
         */
        @Override
        public void goRight() {
            try {
                assert !this.isEmpty() : "le butoir n'a pas de fils";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.stack.push(this.currentNode);
            this.currentNode = this.currentNode.right;
        }

        /**
         * L'itérateur se positionnne sur le père du noeud courant.
         *
         * @pre Le noeud courant n’est pas la racine.
         */
        @Override
        public void goUp() {
            try {
                assert !stack.empty() : " la racine n'a pas de pere";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.currentNode = this.stack.pop();
        }

        /**
         * L'itérateur se positionne sur la racine de l'arbre.
         */
        @Override
        public void goRoot() {
            this.stack.clear();
            this.currentNode = BinaryTree.this.root;
        }

        /**
         * @return true si l'iterateur est sur un sous-arbre vide, false sinon
         */
        @Override
        public boolean isEmpty() {
            return this.currentNode.isEmpty();
        }

        /**
         * @return Le genre du noeud courant.
         */
        @Override
        public NodeType nodeType() {
            if (this.isEmpty()) {
                return NodeType.SENTINEL;
            } else if (this.currentNode.left.isEmpty()) {
                // Si le sous-arbre gauche est vide
                // Et le sous-arbre droit est également vide, alors le noeud est une feuille.
                // Mais si seul le sous-arbre droit est vide, alors le noeud est un noeud simple à gauche
                return this.currentNode.right.isEmpty() ? NodeType.LEAF : NodeType.SIMPLE_RIGHT;
            } else {
                // Si le sous-arbre gauche est vide, mais le sous-arbre droit ne l'est pas,
                // alors le noeud est simple à droite.
                // Autrement c'est un noeud double
                return this.currentNode.right.isEmpty() ? NodeType.SIMPLE_LEFT : NodeType.DOUBLE;
            }
        }

        /**
         * Supprimer le noeud courant de l'arbre.
         *
         * @pre Le noeud courant n'est pas un noeud double.
         */
        @Override
        public void remove() {
            try {
                assert nodeType() != NodeType.DOUBLE : "retirer : retrait d'un noeud double non permis";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            Element newCurrentNode = null;
            switch (this.nodeType()) {
                case SENTINEL: return;
                case DOUBLE: break;
                case SIMPLE_LEFT:
                    newCurrentNode = this.currentNode.left;
                    break;
                case SIMPLE_RIGHT:
                    newCurrentNode = this.currentNode.right;
                    break;
                case LEAF:
                    newCurrentNode = new Element();
            }
            this.currentNode.value = newCurrentNode.value;
            this.currentNode.left = newCurrentNode.left;
            this.currentNode.right = newCurrentNode.right;
        }

        /**
         * Vider le sous–arbre référencé par le noeud courant, qui devient
         * butoir.
         */
        @Override
        public void clear() {
            this.currentNode.value = null;
            this.currentNode.right = null;
            this.currentNode.left = null;
        }

        /**
         * @return La valeur du noeud courant.
         */
        @Override
        public T getValue() {
            return  this.currentNode.value;
        }

        /**
         * Créer un nouveau noeud de valeur v à cet endroit.
         *
         * @pre Le noeud courant est un butoir.
         *
         * @param v
         *            Valeur à ajouter.
         */

        @Override
        public void addValue(T v) {
            try {
                assert isEmpty() : "Ajouter : on n'est pas sur un butoir";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }

            this.currentNode.value = v;
            this.currentNode.left = new Element();
            this.currentNode.right = new Element();
        }

        /**
         * Affecter la valeur v au noeud courant.
         *
         * @param v
         *            La nouvelle valeur du noeud courant.
         */
        @Override
        public void setValue(T v) {
            this.currentNode.value = v;
        }

        private void ancestor(int i, int j) {
            try {
                assert !stack.empty() : "switchValue : argument trop grand";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }
            Element x = stack.pop();
            if (j < i) {
                ancestor(i, j + 1);
            } else {
                T v = x.value;
                x.value = currentNode.value;
                currentNode.value = v;
            }
            stack.push(x);
        }

        /**
         * Échanger les valeurs associées au noeud courant et à son père d’ordre
         * i (le noeud courant reste inchangé).
         *
         * @pre i>= 0 et racine est père du noeud courant d’ordre >= i.
         *
         * @param i
         *            ordre du père
         */
        @Override
        public void switchValue(int i) {
            try {
                assert i >= 0 : "switchValue : argument négatif";
            } catch (AssertionError e) {
                e.printStackTrace();
                System.exit(0);
            }
            if (i > 0) {
                ancestor(i, 1);
            }
        }
    }
}
