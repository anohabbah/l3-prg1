package fr.istic.prg1.tree;

import fr.istic.prg1.tree.util.AbstractImage;
import fr.istic.prg1.tree.util.Iterator;
import fr.istic.prg1.tree.util.Node;
import fr.istic.prg1.tree.util.NodeType;

import java.util.Scanner;

/**
 * @author Mickaël Foursov <foursov@univ-rennes1.fr>
 * @version 5.0
 * @since 2016-04-20
 * <p>
 * Classe décrivant les images en noir et blanc de 256 sur 256 pixels
 * sous forme d'arbres binaires.
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
     * @param x abscisse du point
     * @param y ordonnée du point
     * @return true, si le point (x, y) est allumé dans this, false sinon
     * @pre !this.isEmpty()
     */
    @Override
    public boolean isPixelOn(int x, int y) {
        int depth = 0;
        int upperX = 0, upperY = 0;
        int width = 256;
        int height;

        Iterator<Node> it = this.iterator();
        while (it.nodeType() != NodeType.LEAF) {
            height = width / 2;
            if (depth % 2 == 0) {
                if (y < height + upperY) {
                    it.goLeft();
                } else {
                    upperY += height;
                    it.goRight();
                }
            } else {
                width = height;
                if (x < upperX + height) {
                    it.goLeft();
                } else {
                    upperX += height;
                    it.goRight();
                }
            }
            ++depth;
        }

        return it.getValue().state == 1;
    }

    /**
     * this devient identique à image2.
     *
     * @param image2 image à copier
     * @pre !image2.isEmpty()
     */
    @Override
    public void affect(AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        it.clear();
        this.affectAux(it, image2.iterator());
    }

    private void affectAux(Iterator<Node> it, Iterator<Node> it2) {
        it.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it2.goLeft();
            it.goLeft();
            this.affectAux(it, it2);
            it2.goUp();
            it.goUp();
            it2.goRight();
            it.goRight();
            this.affectAux(it, it2);
            it2.goUp();
            it.goUp();
        }
    }

    /**
     * this devient rotation de image2 à 180 degrés.
     *
     * @param image2 image pour rotation
     * @pre !image2.isEmpty()
     */
    @Override
    public void rotate180(AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        it.clear();
        this.rotate180Aux(it, image2.iterator());
    }

    private void rotate180Aux(Iterator<Node> it1, Iterator<Node> it2) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it1.goRight();
            it2.goLeft();
            this.rotate180Aux(it1, it2);
            it1.goUp();
            it2.goUp();
            it1.goLeft();
            it2.goRight();
            this.rotate180Aux(it1, it2);
            it1.goUp();
            it2.goUp();
        }
    }

    /**
     * this devient rotation de image2 à 90 degrés dans le sens des aiguilles
     * d'une montre.
     *
     * @param image2 image pour rotation
     * @pre !image2.isEmpty()
     */
    @Override
    public void rotate90(AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction non demeand�e");
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    /**
     * this devient inverse vidéo de this, pixel par pixel.
     *
     * @pre !image.isEmpty()
     */
    @Override
    public void videoInverse() {
        this.videoInverseAux(this.iterator());
    }

    private void videoInverseAux(Iterator<Node> it) {
        int state = it.getValue().state == 1 ? 0 : (it.getValue().state == 0 ? 1 : it.getValue().state);
        it.setValue(Node.valueOf(state));

        if (it.nodeType() != NodeType.LEAF) {
            it.goLeft();
            this.videoInverseAux(it);
            it.goUp();
            it.goRight();
            this.videoInverseAux(it);
            it.goUp();
        }
    }

    /**
     * this devient image miroir verticale de image2.
     *
     * @param image2 image à agrandir
     * @pre !image2.isEmpty()
     */
    @Override
    public void mirrorV(AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        it.clear();
        this.mirrorVAux(it, image2.iterator(), 0);
    }

    private void mirrorVAux(Iterator<Node> it1, Iterator<Node> it2, int i) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it2.goLeft();
            if (i % 2 == 1) {
                it1.goLeft();
            } else {
                it1.goRight();
            }
            this.mirrorVAux(it1, it2, ++i);

            it1.goUp();
            it2.goUp();
            --i;

            it2.goRight();
            if (i % 2 == 1) {
                it1.goRight();
            } else {
                it1.goLeft();
            }
            this.mirrorVAux(it1, it2, ++i);

            it1.goUp();
            it2.goUp();
            --i;
        }
    }

    /**
     * this devient image miroir horizontale de image2.
     *
     * @param image2 image à agrandir
     * @pre !image2.isEmpty()
     */
    @Override
    public void mirrorH(AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        it.clear();
        this.mirrorHAux(it, image2.iterator(), 0);
    }

    private void mirrorHAux(Iterator<Node> it1, Iterator<Node> it2, int i) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it2.goLeft();
            if (i % 2 == 0) {
                it1.goLeft();
            } else {
                it1.goRight();
            }
            this.mirrorHAux(it1, it2, ++i);

            it1.goUp();
            it2.goUp();
            --i;

            it2.goRight();
            if (i % 2 == 0) {
                it1.goRight();
            } else {
                it1.goLeft();
            }
            this.mirrorHAux(it1, it2, ++i);

            it1.goUp();
            it2.goUp();
            --i;
        }
    }

    /**
     * this devient quart supérieur gauche de image2.
     *
     * @param image2 image à agrandir
     * @pre !image2.isEmpty()
     */
    @Override
    public void zoomIn(AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction � �crire");
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    /**
     * Le quart supérieur gauche de this devient image2, le reste de this
     * devient éteint.
     *
     * @param image2 image à réduire
     * @pre !image2.isEmpty()
     */
    @Override
    public void zoomOut(AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction � �crire");
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    /**
     * this devient l'intersection de image1 et image2 au sens des pixels
     * allumés.
     *
     * @param image1 premiere image
     * @param image2 seconde image
     * @pre !image1.isEmpty() && !image2.isEmpty()
     */
    @Override
    public void intersection(AbstractImage image1, AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction � �crire");
        System.out.println("-------------------------------------------------");
        System.out.println();
    }

    /**
     * this devient l'union de image1 et image2 au sens des pixels allumés.
     *
     * @param image1 premiere image
     * @param image2 seconde image
     * @pre !image1.isEmpty() && !image2.isEmpty()
     */
    @Override
    public void union(AbstractImage image1, AbstractImage image2) {
        Iterator<Node> it = this.iterator();
        it.clear();
        this.unionAux(it, image1.iterator(), image2.iterator());
    }

    private void unionAux(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
        int state = it1.getValue().state + it2.getValue().state;
        switch (state) {
            case 0:
            case 1:
                it.addValue(Node.valueOf(state));
                break;

            case 2:
                if (it1.getValue().state == 2) {
                    this.affectAux(it, it1);
                } else if (it1.getValue().state == 0) {
                    this.affectAux(it, it2);
                } else {
                    it.addValue(Node.valueOf(1));
                }
                break;

            case 3: // le state de l'un des noeuds est egal 1, donc on allume it
                it.addValue(Node.valueOf(1));
                break;

            case 4:
        }
    }

    /**
     * Attention : cette fonction ne doit pas utiliser la commande isPixelOn
     *
     * @return true si tous les points de la forme (x, x) (avec 0 <= x <= 255)
     * sont allumés dans this, false sinon
     */
    @Override
    public boolean testDiagonal() {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction � �crire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
    }

    /**
     * @param x1 abscisse du premier point
     * @param y1 ordonnée du premier point
     * @param x2 abscisse du deuxième point
     * @param y2 ordonnée du deuxième point
     * @return true si les deux points (x1, y1) et (x2, y2) sont représentés par
     * la même feuille de this, false sinon
     * @pre !this.isEmpty()
     */
    @Override
    public boolean sameLeaf(int x1, int y1, int x2, int y2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction � �crire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
    }

    /**
     * @param image2 autre image
     * @return true si this est incluse dans image2 au sens des pixels allumés
     * false sinon
     * @pre !this.isEmpty() && !image2.isEmpty()
     */
    @Override
    public boolean isIncludedIn(AbstractImage image2) {
        System.out.println();
        System.out.println("-------------------------------------------------");
        System.out.println("Fonction � �crire");
        System.out.println("-------------------------------------------------");
        System.out.println();
        return false;
    }

}
