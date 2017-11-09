package fr.istic.prg1.tp3;

public class Fourmis {

    public static String next(String ui) {
        // Terme suivant
        StringBuilder result = new StringBuilder();

        // Compteur de caractères. Par défaut initialisé à 1.
        // Si un caratère appartient à ui, alors il existe au moins 1 fois
        int cpt = 1,
            length = ui.length();
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                char c = ui.charAt(i);

                // Si i est égal à (length - 1), alors nous sommes à la fin de la chaine. Donc retourner cpt + ui
                if (i == length - 1) {
                    String str = String.valueOf(cpt) + String.valueOf(c);
                    result.append(str);
                } else {
                    if (c == ui.charAt(i + 1)) {
                        cpt++;
                    } else {
                        String str = String.valueOf(cpt) + String.valueOf(c);
                        cpt = 1;
                        result.append(str);
                    }
                }
            }
        }

        return result.toString();
    }
}
