package fr.istic.prg1.tp3;

import java.util.Scanner;

public class InsertionPair {
    private static final int SIZE_MAX = 10;

    private int size;

    private Pair[] array = new Pair[SIZE_MAX];

    public InsertionPair() {
        this.size = 0;
    }

    /**
     * Lire le contenu d'un fichier de liste d'entiers et crée un tableau de doublets.
     *
     * @param scanner Le fichier à lire
     */
    public void createArray(Scanner scanner) {

        int[] arr = new int[2]; // stocke le prochain doublet à ajouter à array[0..size-1]
        int cpt = 0; // permet de savoir si nous avons un doublet ou pas

        // Lecture du contenu du fichier
        while (scanner.hasNext()) {
            int value = scanner.nextInt();

            // Seulement les entiers positifs sont pris en compte.
            if (value >= 0) {
                if (cpt == 2) {
                    this.insert(new Pair(arr[0], arr[1]));
                    cpt = 0;
                }
                arr[cpt] = value;
                cpt++;
            }
        }
    }

    /**
     * Retourne la partie remplie de array[0..size-1].
     *
     * @return Pair[]
     */
    public Pair[] toArray() {
        Pair[] arr = new Pair[this.size];

        System.arraycopy(this.array, 0, arr, 0, this.size);

        return arr;
    }

    /**
     * Insère un objet {@link Pair}  dans array si cet objet n'appartient pas déjà à array[0..size-1].
     * <p>
     * <p>Retourne false si pair appartient à array, autrement l'ajoute et trie array par ordre croissant. et retourne true</p>
     *
     * @param pair l'objet à inserer
     * @return boolean
     */
    public boolean insert(Pair pair) {
        boolean exist = false;

        // Verifier si pair appartient à array[0..size-1]
        for (int i = 0; i < this.size; i++) {
            if (this.array[i].equals(pair)) {
                exist = true;
                break; // Non nécessaire de continuer à chercher si pair appartient à array[0..size-1]
            }
        }

        // Si pair n'appartient par à array[0..size-1], ajouter pair à array[0..size],
        // incrementer size de 1 et trier à array[0..size] par ordre croissant.
        if (!exist && this.size < SIZE_MAX) {
            this.array[this.size] = pair;
            this.size++;

            // Algorithme du trie par insertion
            for (int i = 0; i < this.size; i++) {
                Pair p = this.array[i];
                int j = i;
                while (j > 0 && p.less(this.array[j - 1])) {
                    this.array[j] = this.array[j - 1];
                    j--;
                }
                this.array[j] = p;
            }
        }

        return !exist;
    }
}
