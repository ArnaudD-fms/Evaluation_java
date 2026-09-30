package fr.fms.trainingsales.business;

import java.util.Scanner;

/**
 * Classe permettant de contrôler et de retourner les saisies utilisateurs.
 */
public class ConsoleHandler {

    private final Scanner sc;

    public ConsoleHandler(Scanner sc) {
        this.sc = sc;
    }

    /**
     * Vérifie que l'utilisateur rentre un nombre entre un et le maximum de choix proposés.
     *
     * @param max le maximum de choix possible
     * @return un int correspondant au choix de l'utilisateur
     */
    public int getUserChoice(int max) {

        String invalidInputMessage = "Saisie invalide. Veuillez saisir un nombre entier entre 1 et " + max;

        while (true) {
            String input = sc.nextLine().trim();

            try {
                int userChoice = Integer.parseInt(input);

                if (userChoice >= 1 && userChoice <= max) return userChoice;

                System.out.println(invalidInputMessage);

            } catch (NumberFormatException e) {
                System.out.println(invalidInputMessage);
            }
        }
    }

    /**
     * Vérifie que l'utilisateur ne rentre pas une chaîne de caractère vide.
     *
     * @return une String contenant le mot-clé ou la phrase entrée par l'utilisateur
     */
    public String getValidString() {

        while (true) {
            String input = sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Saisie invalide. votre recherche ne doit pas être vide."
            );
        }
    }
}
