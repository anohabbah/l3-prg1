package fr.istic.prg1.tp3;

import java.util.Arrays;
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
        return Arrays.copyOf(this.array, this.size);
    }

    /**
     * Insère un objet {@link Pair}  dans array si cet objet n'appartient pas déjà à array[0..size-1].
     *
     * @param pair l'objet à inserer
     * @return boolean
     *              <p>Retourne false si pair appartient à array, autrement l'ajoute
     *                  et trie array par ordre croissant et retourne true</p>
     */
    public boolean insert(Pair pair) {
        if (this.size < SIZE_MAX) {
            // La méthode Arrays.binarySearch utilise l'algorithme de dichotomie pour effectuer la recherche.
            // Elle retourne un entier >= 0 si elle trouve l'élément
            int insertPoint = Arrays.binarySearch(this.array, 0, this.size, pair);
            boolean exist = insertPoint >= 0;

            // Si pair n'appartient par à array[0..size-1], ajouter pair à array[0..size],
            // incrementer size de 1 et trier à array[0..size] par ordre croissant.
            if (!exist) {
                this.array[this.size] = pair;
                this.size++;

                Arrays.sort(this.array, 0, this.size);
            }

            return !exist;
        }

        return false;
    }
}
