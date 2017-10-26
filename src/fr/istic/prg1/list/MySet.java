package fr.istic.prg1.list;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

import fr.istic.prg1.list.util.Comparison;
import fr.istic.prg1.list.util.Iterator;
import fr.istic.prg1.list.util.List;
import fr.istic.prg1.list.util.SmallSet;

/**
 * @author Micka�l Foursov <foursov@univ-rennes1.fr>
 * @version 4.0
 * @since 2015-06-15
 */

public class MySet extends List<SubSet> {

    /**
     * Borne sup�rieure pour les rangs des sous-ensembles.
     */
    private static final int MAX_RANG = 128;
    /**
     * Sous-ensemble de rang maximal � mettre dans le drapeau de la liste.
     */
    private static final SubSet FLAG_VALUE = new SubSet(MAX_RANG,
            new SmallSet());
    /**
     * Entr�e standard.
     */
    private static final Scanner standardInput = new Scanner(System.in);

    public MySet() {
        super();
        setFlag(FLAG_VALUE);
    }

    /**
     * Fermer tout (actuellement juste l'entr�e standard).
     */
    public static void closeAll() {
        standardInput.close();
    }

    private static Comparison compare(int a, int b) {
        if (a < b) {
            return Comparison.INF;
        } else if (a == b) {
            return Comparison.EGAL;
        } else {
            return Comparison.SUP;
        }
    }

    /**
     * Afficher � l'�cran les entiers appartenant � this, dix entiers par ligne
     * d'�cran.
     */
    public void print() {
        System.out.println(" [version corrigee de contenu]");
        this.print(System.out);
    }

    // //////////////////////////////////////////////////////////////////////////////
    // //////////// Appartenance, Ajout, Suppression, Cardinal
    // ////////////////////
    // //////////////////////////////////////////////////////////////////////////////

    /**
     * @return true si le nombre saisi par l'utilisateur appartient � this,
     * false sinon
     */
    public boolean contains() {
        System.out.println(" valeur cherchee : ");
        int value = readValue(standardInput, 0);
        return this.contains(value);
    }

    /**
     * @param value valeur � tester
     * @return true si valeur appartient � l'ensemble, false sinon
     */

    public boolean contains(int value) {
        if (value < 0 || value > 32767) {
            return false;
        }

        if (this.isEmpty()) {
            return false;
        }

        int rank = value / 256;
        Iterator<SubSet> it = this.iterator();
        SubSet cur = it.getValue();
        while (cur.rank < rank && !it.isOnFlag()) {
            cur = it.nextValue();
        }

        return cur.rank == rank && !it.isOnFlag() && cur.set.contains(value % 256);
    }

    /**
     * Ajouter � this toutes les valeurs saisies par l'utilisateur et afficher
     * le nouveau contenu (arr�t par lecture de -1).
     */
    public void add() {
        System.out.println(" valeurs a ajouter (-1 pour finir) : ");
        this.add(System.in);
        System.out.println(" nouveau contenu :");
        this.printNewState();
    }

    /**
     * Ajouter � this toutes les valeurs prises dans is.
     *
     * @param is flux d'entr�e.
     */
    public void add(InputStream is) {
        Scanner scanner = new Scanner(is);
        while (scanner.hasNextInt()) {
            int value = scanner.nextInt();

            if (value >= 0) {
                this.addNumber(value);
            }
        }
        scanner.close();
    }

    /**
     * Ajouter value � this.
     *
     * @param value valuer � ajouter.
     */
    public void addNumber(int value) {
        if ((value >= 0 && value <= 32767) && !this.contains(value)) {
            int rank = value / 256;
            int element = value % 256;

            if (this.isEmpty()) {
                SmallSet s = new SmallSet();
                s.add(element);
                this.addTail(new SubSet(rank, s));
            } else {
                Iterator<SubSet> it = this.iterator();
                SubSet cur = it.getValue();
                while (cur.rank < rank) {
                    cur = it.nextValue();
                }

                if (cur.rank == rank) {
                    cur.set.add(element);
                } else {
                    SmallSet set = new SmallSet();
                    set.add(element);
                    it.addLeft(new SubSet(rank, set));
                }
            }
        }
    }

    /**
     * Supprimer de this toutes les valeurs saisies par l'utilisateur et
     * afficher le nouveau contenu (arrêt par lecture de -1).
     */
    public void remove() {
        System.out.println("  valeurs a supprimer (-1 pour finir) : ");
        this.remove(System.in);
        System.out.println(" nouveau contenu :");
        this.printNewState();
    }

    /**
     * Supprimer de this toutes les valeurs prises dans is.
     *
     * @param is flux d'entr�e
     */
    public void remove(InputStream is) {
        Scanner scanner = new Scanner(is);
        while (scanner.hasNext()) {
            int value = scanner.nextInt();

            if (value >= 0) {
                this.removeNumber(value);
            }
        }
        scanner.close();
    }

    /**
     * Supprimer value de this.
     *
     * @param value valeur � supprimer
     */
    public void removeNumber(int value) {
        if ((value >= 0 && value <= 32767) && this.contains(value)) {
            Iterator<SubSet> it = this.iterator();
            SubSet cur = it.getValue();
            while (cur.rank < value / 256 && !it.isOnFlag()) {
                cur = it.nextValue();
            }
            // value appartenant à this, à la fin de boucle on a cur.rank == value / 256
            cur.set.remove(value % 256);
            if (cur.set.isEmpty()) {
                it.remove();
            }
        }
    }

    /**
     * @return taille de l'ensemble this
     */
    public int size() {
//        if (this.isEmpty()) return 0;

        int cpt = 0;

        Iterator<SubSet> it = this.iterator();
        while (!it.isOnFlag()) {
            cpt += it.getValue().set.size();
            it.goForward();
        }

        return cpt;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // /////// Difference, DifferenceSymetrique, Intersection, Union ///////
    // /////////////////////////////////////////////////////////////////////////////

    /**
     * This devient la diff�rence de this et set2.
     *
     * @param set2 deuxi�me ensemble
     */
    public void difference(MySet set2) {
        if (this.equals(set2)) {
            this.clear();
            return;
        }

        Iterator<SubSet> it = this.iterator();
        Iterator<SubSet> it2 = set2.iterator();
        while (!it.isOnFlag()) {
            SubSet cur = it.getValue();
            SubSet cur2 = it2.getValue();
            switch (compare(cur.rank, cur2.rank)) {
                case INF:
                    it.goForward();
                    break;

                case SUP:
                    it2.goForward();
                    break;

                default:
                    cur.set.difference(cur2.set);
                    if (cur.set.isEmpty()) {
                        it.remove();
                    } else {
                        it.goForward();
                    }
                    it2.goForward();
            }
        }
    }

    /**
     * This devient la diff�rence sym�trique de this et set2.
     *
     * @param set2 deuxi�me ensemble
     */
    public void symmetricDifference(MySet set2) {
        if (this.equals(set2)) {
            this.clear();
            return;
        }

        Iterator<SubSet> it = this.iterator();
        Iterator<SubSet> it2 = set2.iterator();
        while (!it.isOnFlag()) {
            SubSet cur = it.getValue();
            SubSet cur2 = it2.getValue();

            switch (compare(cur.rank, cur2.rank)) {
                case INF:
                    it.goForward();
                    break;

                case SUP:
                    it.addLeft(cur2.clone());
                    it.goForward();
                    it2.goForward();
                    break;

                default:
                    cur.set.symmetricDifference(cur2.set);
                    if (cur.set.isEmpty()) {
                        it.remove();
                    } else {
                        it.goForward();
                    }
                    it2.goForward();
            }
        }

        while (!it2.isOnFlag()) {
            it.addLeft(it2.getValue());
            it2.goForward();
        }
    }

    /**
     * This devient l'intersection de this et set2.
     *
     * @param set2 deuxi�me ensemble
     */
    public void intersection(MySet set2) {
        if (this.equals(set2)) {
            return;
        }

        Iterator<SubSet> it = this.iterator();
        Iterator<SubSet> it2 = set2.iterator();
        while (!it.isOnFlag()) {
            SubSet cur = it.getValue();
            SubSet cur2 = it2.getValue();

            switch (compare(cur.rank, cur2.rank)) {
                case INF:
                    it.remove();
                    break;

                case SUP:
                    it2.goForward();
                    break;

                default:
                    cur.set.intersection(cur2.set);
                    if (cur.set.isEmpty()) {
                        it.remove();
                    } else {
                        it.goForward();
                    }
                    it2.goForward();
            }
        }

        while (!it.isOnFlag()) {
            it.remove();
        }
    }

    /**
     * This devient l'union de this et set2.
     *
     * @param set2 deuxi�me ensemble
     */
    public void union(MySet set2) {
        Iterator<SubSet> it = this.iterator();
        Iterator<SubSet> it2 = set2.iterator();
        while (!it.isOnFlag()) {
            SubSet cur = it.getValue();
            SubSet cur2 = it2.getValue();

            switch (compare(cur.rank, cur2.rank)) {
                case INF:
                    it.goForward();
                    break;

                case SUP:
                    it.addLeft(cur2.clone());
                    it.goForward();
                    it2.goForward();
                    break;

                default:
                    cur.set.union(cur2.set);
                    it.goForward();
                    it2.goForward();
            }
        }

        while (!it2.isOnFlag()) {
            this.addTail(it2.getValue().clone());
            it2.goForward();
        }
    }

    // /////////////////////////////////////////////////////////////////////////////
    // /////////////////// Egalité, Inclusion ////////////////////
    // /////////////////////////////////////////////////////////////////////////////

    /**
     * @param o deuxième ensemble
     * @return true si les ensembles this et o sont égaux, false sinon
     */
    @Override
    public boolean equals(Object o) {
        boolean b = true;
        if (this == o) {
            b = true;
        } else if (o == null) {
            b = false;
        } else if (!(o instanceof MySet)) {
            b = false;
        } else {
            Iterator<SubSet> it = this.iterator();
            Iterator<SubSet> it2 = ((MySet) o).iterator();
            while (!it.isOnFlag() && b) {
                SubSet cur = it.getValue();
                SubSet cur2 = it2.getValue();

                switch (compare(cur.rank, cur2.rank)) {
                    case INF:
                        b = false;
                        break;

                    case SUP:
                        b = false;
                        break;

                    default:
                        if (!cur.set.equals(cur2.set)) {
                            b = false;
                        }

                        it.goForward();
                        it2.goForward();
                }
            }

            // Il faut s'assurer que les 2 iterateurs sont sur leur drapeaux respectifs
            b = b && it.isOnFlag() && it2.isOnFlag();
        }

        return b;
    }

    /**
     * @param set2 deuxi�me ensemble
     * @return true si this est inclus dans set2, false sinon
     */
    public boolean isIncludedIn(MySet set2) {
        if (this.equals(set2)) return true;

        boolean b = true;

        Iterator<SubSet> it = this.iterator();
        Iterator<SubSet> it2 = set2.iterator();
        while (!it.isOnFlag() && b) {
            SubSet cur = it.getValue();
            SubSet cur2 = it2.getValue();

            if (cur.rank < cur2.rank) {
                b = false;
            } else if (cur.rank > cur2.rank) {
                it2.goForward();
            } else {
                if (!cur.set.isIncludedIn(cur2.set)) {
                    b = false;
                }

                it.goForward();
                it2.goForward();
            }
        }
        return b;
    }

    // /////////////////////////////////////////////////////////////////////////////
    // //////// Rangs, Restauration, Sauvegarde, Affichage //////////////
    // /////////////////////////////////////////////////////////////////////////////

    /**
     * Afficher les rangs pr�sents dans this.
     */
    public void printRanks() {
        System.out.println(" [version corrigee de rangs]");
        this.printRanksAux();
    }

    private void printRanksAux() {
        int count = 0;
        System.out.print(" Rangs presents : ");
        Iterator<SubSet> it = this.iterator();
        while (!it.isOnFlag()) {
            System.out.print("" + it.getValue().rank + "  ");
            count = count + 1;
            if (count == 10) {
                System.out.println();
                count = 0;
            }
            it.goForward();
        }
        if (count > 0) {
            System.out.println();
        }
    }

    /**
     * Cr�er this � partir d'un fichier choisi par l'utilisateur contenant une
     * s�quence d'entiers positifs termin�e par -1 (cf f0.ens, f1.ens, f2.ens,
     * f3.ens et f4.ens).
     */
    public void restore() {
        String fileName = readFileName();
        InputStream inFile;
        try {
            inFile = new FileInputStream(fileName);
            System.out.println(" [version corrigee de restauration]");
            this.clear();
            this.add(inFile);
            inFile.close();
            System.out.println(" nouveau contenu :");
            this.printNewState();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("fichier " + fileName + " inexistant");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("probleme de fermeture du fichier " + fileName);
        }
    }

    /**
     * Sauvegarder this dans un fichier d'entiers positifs termin� par -1.
     */
    public void save() {
        System.out.println(" [version corrigee de sauvegarde]");
        OutputStream outFile;
        try {
            outFile = new FileOutputStream(readFileName());
            this.print(outFile);
            outFile.write("-1\n".getBytes());
            outFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("pb ouverture fichier lors de la sauvegarde");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("probleme de fermeture du fichier");
        }
    }

    /**
     * @return l'ensemble this sous forme de cha�ne de caract�res.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        int count = 0;
        SubSet subSet;
        int startValue;
        Iterator<SubSet> it = this.iterator();
        while (!it.isOnFlag()) {
            subSet = it.getValue();
            startValue = subSet.rank * 256;
            for (int i = 0; i < 256; ++i) {
                if (subSet.set.contains(i)) {
                    String number = String.valueOf(startValue + i);
                    int numberLength = number.length();
                    for (int j = 6; j > numberLength; --j) {
                        number += " ";
                    }
                    result.append(number);
                    ++count;
                    if (count == 10) {
                        result.append("\n");
                        count = 0;
                    }
                }
            }
            it.goForward();
        }
        if (count > 0) {
            result.append("\n");
        }
        return result.toString();
    }

    /**
     * Imprimer this dans outFile.
     *
     * @param outFile flux de sortie
     */
    private void print(OutputStream outFile) {
        try {
            String string = this.toString();
            outFile.write(string.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Afficher l'ensemble avec sa taille et les rangs pr�sents.
     */
    private void printNewState() {
        this.print(System.out);
        System.out.println(" Nombre d'elements : " + this.size());
        this.printRanksAux();
    }

    /**
     * @param scanner
     * @param min     valeur minimale possible
     * @return l'entier lu au clavier (doit être entre min et 32767)
     */
    private static int readValue(Scanner scanner, int min) {
        int value = scanner.nextInt();
        while (value < min || value > 32767) {
            System.out.println("valeur incorrecte");
            value = scanner.nextInt();
        }
        return value;
    }

    /**
     * @return nom de fichier saisi psar l'utilisateur
     */
    private static String readFileName() {
        System.out.print(" nom du fichier : ");
        String fileName = standardInput.next();
        return fileName;
    }
}
