package fr.istic.prg1.tree;

import fr.istic.prg1.tree.util.BinaryTree;
import fr.istic.prg1.tree.util.Iterator;
import fr.istic.prg1.tree.util.Node;
import fr.istic.prg1.tree.util.NodeType;
import sun.awt.image.IntegerComponentRaster;

public class Trees {
    /**
     * @param tree arbre à tester.
     * @return <code>true</code> si l'arbre tree est organisé de façon symétrique (c'est-à-dire, la valeur d'un noeud
     * est supérieure est aux valeurs dans son fils gauche, et inférieure aux valeurs dans son fils droit),
     * <code>false</code> sinon.
     * @pre !tree.isEmpty()
     */
    public static boolean isSymetric(BinaryTree<Integer> tree) {
        try {
            assert !tree.isEmpty() : "Arbre vide";
        } catch (AssertionError e) {
            e.printStackTrace();
            System.exit(0);
        }

        return xIsSymmetric(tree.iterator());
    }

    /**
     * @param it
     * @return
     */
    private static boolean xIsSymmetric(Iterator<Integer> it) {
        if (it.nodeType() != NodeType.SENTINEL) {
            boolean left, right;
            Integer currentRoot = it.getValue();
            it.goLeft();
            left = xEvalSubTree(it, currentRoot, true);
            it.goUp();
            it.goRight();
            right = xEvalSubTree(it, currentRoot, false);
            it.goUp();

            if (left && right) {
                it.goLeft();
                xIsSymmetric(it);
                it.goUp();
                it.goRight();
                xIsSymmetric(it);
                it.goUp();
            } else
                return false;
        }

        return true;
    }

    private static boolean xEvalSubTree(Iterator<Integer> it, Integer currentRoot, boolean isLeft) {
        if (it.nodeType() != NodeType.SENTINEL) {
            Integer childNodeValue = it.getValue();
            if (isLeft) {
                if (currentRoot < childNodeValue)
                    return false;
            } else {
                if (currentRoot > childNodeValue)
                    return false;
            }

            it.goLeft();
            xEvalSubTree(it, currentRoot, isLeft);
            it.goUp();
            it.goRight();
            xEvalSubTree(it, currentRoot, isLeft);
            it.goUp();
        }

        return true;
    }

    /**
     * @param tree arbre à tester.
     * @return <code>true</code> si l'arbre tree ne comporte qu'une seule branche (c'est-à-dire il n'y a pas de noeuds
     * double dans tree), <code>false</code> sinon.
     * @pre !tree.isEmpty()
     */
    public static boolean isLinear(BinaryTree<Integer> tree) {
        try {
            assert !tree.isEmpty() : "Arbre vide";
        } catch (AssertionError e) {
            e.printStackTrace();
            System.exit(0);
        }

        Iterator<Integer> it = tree.iterator();
        NodeType type = it.nodeType();
        while (type != NodeType.LEAF) {
            if (type == NodeType.SIMPLE_LEFT) {
                it.goLeft();
            } else if (type == NodeType.SIMPLE_RIGHT) {
                it.goRight();
            } else {
                return false;
            }
        }

        return true;
    }

    /**
     * Affiche les valeurs paires présentent dans l'arbre, en faisant un parcours préfixe.
     *
     * @param tree arbre
     * @pre !tree.isEmpty()
     */
    public static void printEvenValues(BinaryTree<Integer> tree) {
        try {
            assert !tree.isEmpty() : "Arbre vide";
        } catch (AssertionError e) {
            e.printStackTrace();
            System.exit(0);
        }

        xPrintEvalValues(tree.iterator());
    }

    /**
     * Méthode auxiliaire de parcours de l'arbre.
     *
     * @param it itérateur sur l'arbre.
     */
    private static void xPrintEvalValues(Iterator<Integer> it) {
        Integer value = it.getValue();
        if (value % 2 == 0) {
            System.out.println(value);
            System.exit(0);
        }

        if (it.nodeType() != NodeType.LEAF) {
            it.goLeft();
            xPrintEvalValues(it);
            it.goUp();
            it.goRight();
            xPrintEvalValues(it);
            it.goUp();
        }
    }

    /**
     * @param tree l'arbre binaire à modifier.
     * @return l’arbre binaire ayant la même structure que tree, mais
     * dans lequel les valeurs des nœuds sont des doublets (value, depth) où value est la même que celle
     * de ce nœud dans tree et depth est la profondeur de ce nœud dans l’arbre (c’est-à-dire sa distance
     * de la racine).
     * @pre !tree.isEmpty()
     */
    public static BinaryTree<Pair> depthTree(BinaryTree<Integer> tree) {
        try {
            assert !tree.isEmpty() : "Arbre vide";
        } catch (AssertionError e) {
            e.printStackTrace();
            System.exit(0);
        }

        BinaryTree<Pair> pairTree = new BinaryTree<>();
        xDepthTree(tree.iterator(), pairTree.iterator(), 0);
        return pairTree;
    }

    private static void xDepthTree(Iterator<Integer> it1, Iterator<Pair> it2, int depth) {
        it2.addValue(new Pair(it1.getValue(), depth));
        if (it1.nodeType() != NodeType.LEAF) {
            it1.goLeft();
            it2.goLeft();
            xDepthTree(it1, it2, ++depth);
            it1.goUp();
            it2.goUp();
            --depth;
            it1.goRight();
            it2.goRight();
            xDepthTree(it1, it2, ++depth);
            it1.goUp();
            it2.goUp();
        }
    }
}
