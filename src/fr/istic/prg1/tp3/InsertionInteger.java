package fr.istic.prg1.tp3;

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
        int[] arr = new int[this.size];

        System.arraycopy(this.array, 0, arr, 0, this.size);

        return arr;
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
        boolean exist = false;

        if (value < 0) {
            return false;
        }

        // Vérifier d'abord si value appartient à array[0..size-1]
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] == value) {
                exist = true;
                break; // Sortie de la boucle si value appartient à array[0..size-1]
            }
        }

        // Ajouter value à array si value ne l'appartient pas, incrementer size de 1 et trier array
        if (!exist && this.size < SIZE_MAX) {
            this.array[this.size] = value;
            this.size++;

            // Algorithme du trie par insertion
            for (int i = 0; i < this.size; i++) {
                int j = i;
                int x = this.array[i];

                while (j > 0 && x < this.array[j - 1]) {
                    this.array[j] = this.array[j - 1];
                    j--;
                }

                this.array[j] = x;
            }
        }

        return !exist;
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
