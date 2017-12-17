package fr.istic.prg1.tree.test;

import fr.istic.prg1.tree.Trees;
import fr.istic.prg1.tree.util.BinaryTree;
import fr.istic.prg1.tree.util.Iterator;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class TreesTest {

    @Test
    public void testIsSymetric() {
        BinaryTree<Integer> tree = new BinaryTree<>();
        Iterator<Integer> it = tree.iterator();
        it.addValue(20);
        it.goLeft();
        it.addValue(5);
        it.goLeft();
        it.addValue(3);
        it.goUp();
        it.goRight();
        it.addValue(12);
        it.goRight();
        it.addValue(13);
        it.goUp();
        it.goLeft();
        it.addValue(8);
        it.goLeft();
        it.addValue(6);
        it.goRoot();
        it.goRight();
        it.addValue(25);
        it.goRight();
        it.addValue(28);
        it.goUp();
        it.goLeft();
        it.addValue(21);

        assertTrue(Trees.isSymetric(tree));
    }

}
