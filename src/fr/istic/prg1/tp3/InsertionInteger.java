package fr.istic.prg1.tp3;

import java.util.Arrays;
import java.util.Scanner;

public class InsertionInteger {
    private static final int SIZE_MAX = 10;

    private int size;

    private  int[] array = new int[SIZE_MAX];

    public InsertionInteger() {
        this.size = 0;
    }

    /**
     * Retourne la partie remplie du tableau.
     *
     * @return la partie de <code>array</code> remplie.
     */
    public int[] toArray() {
        return Arrays.copyOf(this.array, this.size);
    }

    /**
     * Si value n'appartient pas à array[0..size-1] et size > SIZE_MAX, size est incrementé de 1, value est insere dans
     * array et les entiers array[0..size] sont triés par ordre croissant.
     * Sinon array est inhangé.
     *
     * @param value
     *          La valeur à insérer
     * @return false si value appartient à array[0..size-1] ou si array est complètement rempli;
     *          true si value n,appartient pas à array[0..size-1]
     */
    public boolean insert(int value) {
        if (value >= 0) {
            int insertPoint = Arrays.binarySearch(this.array, 0, this.size, value);
            boolean exist = insertPoint >= 0;

            // Ajouter value à array si value ne l'appartient pas,
            // incrementer size de 1
            // et trier array
            if (!exist && this.size < SIZE_MAX) {
                this.array[this.size] = value;
                this.size++;

                Arrays.sort(this.array, 0, this.size);
            }

            return !exist;
        }

        return false;
    }

    public void createArray(Scanner scanner) {
        while (scanner.hasNext()) {
            int value = scanner.nextInt();
            if (value >= 0) { // Seulement les valeurs positives
                this.insert(value);
            }
        }
    }

    @Override
    public String toString() {
        return null;
    }
}
