/**
 * ANOH Abbah
 * &
 * KOBENAN Sébastien
 */
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
        this.xAffect(it, image2.iterator());
    }

    /**
     * @param it1 iterateur sur this
     * @param it2 iterateur sur image2
     */
    protected void xAffect(Iterator<Node> it1, Iterator<Node> it2) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it2.goLeft();
            it1.goLeft();
            this.xAffect(it1, it2);
            it2.goUp();
            it1.goUp();
            it2.goRight();
            it1.goRight();
            this.xAffect(it1, it2);
            it2.goUp();
            it1.goUp();
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
        this.xRotate180(it, image2.iterator());
    }

    /**
     * @param it1
     * @param it2
     */
    protected void xRotate180(Iterator<Node> it1, Iterator<Node> it2) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it1.goRight();
            it2.goLeft();
            this.xRotate180(it1, it2);
            it1.goUp();
            it2.goUp();
            it1.goLeft();
            it2.goRight();
            this.xRotate180(it1, it2);
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
        System.out.println("-----------------------");
        System.out.println("  A ecrire : Rotate90  ");
        System.out.println("-----------------------");
    }

    /**
     * this devient inverse vidéo de this, pixel par pixel.
     *
     * @pre !image.isEmpty()
     */
    @Override
    public void videoInverse() {
        this.xVideoInverse(this.iterator());
    }

    /**
     * @param it iterateur sur this.
     */
    protected void xVideoInverse(Iterator<Node> it) {
        if (it.nodeType() != NodeType.LEAF) {
            it.goLeft();
            this.xVideoInverse(it);
            it.goUp();
            it.goRight();
            this.xVideoInverse(it);
            it.goUp();
        } else {
            it.setValue(Node.valueOf(1 - it.getValue().state));
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
        this.xMirrorV(it, image2.iterator(), 0);
    }

    /**
     * Methode auxiliaire de parcours de l'arbre.
     *
     * @param it1 iterateur sur this
     * @param it2 iterateur sur image2
     * @param i compteur pour de déterminer le niveau
     */
    protected void xMirrorV(Iterator<Node> it1, Iterator<Node> it2, int i) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it2.goLeft();
            if (i % 2 == 1) {
                it1.goLeft();
            } else {
                it1.goRight();
            }
            this.xMirrorV(it1, it2, ++i);

            it1.goUp();
            it2.goUp();
            --i;

            it2.goRight();
            if (i % 2 == 1) {
                it1.goRight();
            } else {
                it1.goLeft();
            }
            this.xMirrorV(it1, it2, ++i);

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
        this.xMirrorH(it, image2.iterator(), 0);
    }

    /**
     * @param it1 iterateur sur this
     * @param it2 iterateur sur image2
     * @param i compteur pour determiner le niveau du noeud
     */
    protected void xMirrorH(Iterator<Node> it1, Iterator<Node> it2, int i) {
        it1.addValue(Node.valueOf(it2.getValue().state));
        if (it2.nodeType() != NodeType.LEAF) {
            it2.goLeft();
            if (i % 2 == 0) {
                it1.goLeft();
            } else {
                it1.goRight();
            }
            this.xMirrorH(it1, it2, ++i);

            it1.goUp();
            it2.goUp();
            --i;

            it2.goRight();
            if (i % 2 == 0) {
                it1.goRight();
            } else {
                it1.goLeft();
            }
            this.xMirrorH(it1, it2, ++i);

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
        Iterator<Node> it = this.iterator();
        it.clear();
        this.xZoomIn(it, image2.iterator(), 0);
    }

    /**
     * @param it1 iterateur sur this.
     * @param it2 iterateur sur image2
     * @param i compteur pour determiner le niveau du noeud
     */
    protected void xZoomIn(Iterator<Node> it1, Iterator<Node> it2, int i) {
        if (i < 2) {
            if (it2.nodeType() != NodeType.LEAF) {
                it2.goLeft();
                this.xZoomIn(it1, it2, ++i);
            } else {
                this.xAffect(it1, it2);
            }
        } else {
            this.xAffect(it1, it2);
        }
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
        Iterator<Node> it = this.iterator();
        it.clear(); // vider d'abord l'arbre pour le reconstruire
        it.addValue(Node.valueOf(2)); // racine
        it.goRight(); // dans la zone inferieure
        it.addValue(Node.valueOf(0));
        it.goRoot(); // retour a la racine
        it.goLeft(); // zone superieure
        it.addValue(Node.valueOf(2));
        it.goRight(); // zone superieure droite
        it.addValue(Node.valueOf(0));
        it.goUp();
        it.goLeft();
        this.xAffect(it, image2.iterator(), 0);
        it.goRoot();
        // Filtrer les noeuds.
        // Suppimer les sous-arbres dont la racine n'a que des fils de type feuille qui ont le
        // même état et les remplacer par un noeud qui a le même état que les fils.
        this.testNodes(it);
    }

    /**
     * Méthode auxiliaire de parcours.
     *
     * @param it1 iterateur sur this.
     * @param it2 iterateur sur l'image à reduire.
     * @param i   compteur pour determiner la profondeur du noeud.
     */
    protected void xAffect(Iterator<Node> it1, Iterator<Node> it2, int i) {
        if (i < 14) {
            it1.addValue(Node.valueOf(it2.getValue().state));
            if (it2.nodeType() != NodeType.LEAF) {
                it2.goLeft();
                it1.goLeft();
                this.xAffect(it1, it2, ++i);
                it2.goUp();
                it1.goUp();
                --i;

                it2.goRight();
                it1.goRight();
                this.xAffect(it1, it2, ++i);
                it2.goUp();
                it1.goUp();
                --i;
            }
        } else { // Au dela de la profondeur 14 ajouter que les feuilles
            if (it2.getValue().state != 2) {
                it1.addValue(Node.valueOf(it2.getValue().state));
            } else {
                it2.goLeft();
                int leftChildState = it2.getValue().state;
                it2.goUp();
                it2.goRight();
                int rightChildSate = it2.getValue().state;
                it2.goUp();
                if (leftChildState != 1 && rightChildSate != 1) {
                    if (leftChildState != 2 || rightChildSate != 2) {
                        it1.addValue(Node.valueOf(0));
                    } else {
                        it1.addValue(Node.valueOf(1));
                    }
                } else {
                    it1.addValue(Node.valueOf(1));
                }
            }
        }
    }

    /**
     * Filtre un sous-arbre.
     * <p>Objectif: supprimer tous les sous-arbres dont les fils
     * ont le même etat et que leur état est different de 2</p>
     *
     * @param it iterateur representant le sous-arbre
     */
    protected void testNodes(Iterator<Node> it) {
        it.goLeft();
        int leftChildState = it.getValue().state;
        it.goUp();
        it.goRight();
        int rightChildState = it.getValue().state;
        it.goUp();

        if (leftChildState == 2) {
            it.goLeft();
            testNodes(it);
            leftChildState = it.getValue().state;
            it.goUp();
        }

        if (rightChildState == 2) {
            it.goRight();
            testNodes(it);
            rightChildState = it.getValue().state;
            it.goUp();
        }

        if (leftChildState == rightChildState && leftChildState != 2) {
            it.clear();
            it.addValue(Node.valueOf(leftChildState));
        }
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
        Iterator<Node> it = this.iterator();
        it.clear();
        this.xIntersection(it, image1.iterator(), image2.iterator());
    }

    protected void xIntersection(Iterator<Node> it1, Iterator<Node> it2, Iterator<Node> it3) {
        if (it2.getValue().state == 0 || it3.getValue().state == 0) {
            it1.addValue(Node.valueOf(0));
        } else if (it2.getValue().state == it3.getValue().state) {
            switch (it2.getValue().state) {
                case 1:
                    it1.addValue(Node.valueOf(1));
                    break;

                case 2:
                    it1.addValue(Node.valueOf(2));
                    it1.goLeft();
                    it2.goLeft();
                    it3.goLeft();
                    this.xIntersection(it1, it2, it3);
                    int state1 = it1.getValue().state;
                    it1.goUp();
                    it2.goUp();
                    it3.goUp();
                    it1.goRight();
                    it2.goRight();
                    it3.goRight();
                    this.xIntersection(it1, it2, it3);
                    int state2 = it1.getValue().state;
                    it1.goUp();
                    it2.goUp();
                    it3.goUp();

                    if (state1 == state2 && state2 != 2) {
                        it1.clear();
                        it1.addValue(Node.valueOf(state1));
                    }
                    break;
            }
        } else {
            if (it2.getValue().state == 2) {
                this.xAffect(it1, it2);
            } else {
                this.xAffect(it1, it3);
            }
        }
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
        this.xUnion(it, image1.iterator(), image2.iterator());
    }

    /**
     * Méthode union auxiliaire. Utilisée pour parcourir les arbres.
     *
     * @param it  iterateur sur this. Stocke le resultat de l'union.
     * @param it1 iterateur sur this pour la comparaison de l'union.
     * @param it2 iterateur sur image2 pour la comparaiseon de l'union.
     */
    protected void xUnion(Iterator<Node> it, Iterator<Node> it1, Iterator<Node> it2) {
        int state = it1.getValue().state + it2.getValue().state;
        switch (state) {
            case 2:
                if (it1.getValue().state == 0) {
                    xAffect(it, it2);
                } else if (it1.getValue().state == 2) {
                    xAffect(it, it1);
                } else {
                    it.addValue(Node.valueOf(1));
                }
                break;
            case 3:
                it.addValue(Node.valueOf(1));
                break;
            case 4:
                it.addValue(Node.valueOf(2));
                it.goLeft();
                it1.goLeft();
                it2.goLeft();
                xUnion(it, it1, it2);
                int left = it.getValue().state;
                it.goUp();
                it1.goUp();
                it2.goUp();
                it.goRight();
                it1.goRight();
                it2.goRight();
                xUnion(it, it1, it2);
                int right = it.getValue().state;
                it.goUp();
                it1.goUp();
                it2.goUp();
                if (left == right && left != 2) {
                    it.clear();
                    it.addValue(Node.valueOf(left));
                }
                break;

            default:
                it.addValue(Node.valueOf(state));
                break;
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
        return this.xTestDiagonal(this.iterator(), true, true);
    }

    protected boolean xTestDiagonal(Iterator<Node> it, boolean horizontal, boolean goToLeft) {
        if (it.getValue().state == 1) {
            return true;
        }
        if (it.getValue().state == 0) {
            return false;
        }

        if (horizontal) {
            it.goLeft();
            boolean bLeft = this.xTestDiagonal(it, false, true);
            it.goUp();
            it.goRight();
            boolean bRight = this.xTestDiagonal(it, false, false);
            it.goUp();
            return bLeft && bRight;
        } else {
            if (goToLeft) {
                it.goLeft();
                boolean bLeft = this.xTestDiagonal(it, true, true);
                it.goUp();
                return bLeft;
            } else {
                it.goRight();
                boolean bRight = this.xTestDiagonal(it, true, false);
                it.goUp();
                return bRight;
            }
        }
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
        Iterator<Node> it = this.iterator();
        int rank = 0;
        int upperX = 0;
        int upperY = 0;
        int window = 256;
        int height;

        while (it.nodeType() != NodeType.LEAF) {
            height = window / 2;
            if (rank % 2 == 0) {
                if ((y1 < upperY + height) && (y2 < upperY + height)) {
                    it.goLeft();
                } else if ((y1 >= upperY + height) && (y2 >= upperY + height)) {
                    upperY += height;
                    it.goRight();
                } else {
                    return false;
                }
            } else {
                window = height;
                if ((x1 < upperX + height) && (x2 < upperX + height)) {
                    it.goLeft();
                } else if ((x1 >= upperX + height) && (x2 >= upperX + height)) {
                    upperX += height;
                    it.goRight();
                } else {
                    return false;
                }
            }
            rank++;
        }
        return true;
    }

    /**
     * @param image2 autre image
     * @return true si this est incluse dans image2 au sens des pixels allumés
     * false sinon
     * @pre !this.isEmpty() && !image2.isEmpty()
     */
    @Override
    public boolean isIncludedIn(AbstractImage image2) {
        return this == image2 || this.xIncludedIn(this.iterator(), image2.iterator());

    }

    protected boolean xIncludedIn(Iterator<Node> it1, Iterator<Node> it2) {
        if (it2.getValue().state != 0 && it1.getValue().state != 1) {
            boolean left = false, right = false;
            it1.goLeft();
            it2.goLeft();

            if (it2.getValue().state == 1) {
                left = true;
            } else {
                if (it1.getValue().state == 0) {
                    left = true;
                } else {
                    this.xIncludedIn(it1, it2);
                }
            }

            it1.goUp();
            it2.goUp();
            it1.goRight();
            it2.goRight();

            if (it2.getValue().state == 1) {
                right = true;
            } else {
                if (it1.getValue().state == 0) {
                    right = true;
                } else {
                    this.xIncludedIn(it1, it2);
                }
            }
            it1.goUp();
            it2.goUp();

            return left && right;
        }

        return false;
    }
}
