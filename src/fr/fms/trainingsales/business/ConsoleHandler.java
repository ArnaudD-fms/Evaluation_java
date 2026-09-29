package fr.fms.trainingsales.business;

import java.util.Scanner;

public class ConsoleHandler {

    private final Scanner sc;

    public ConsoleHandler(Scanner sc) {
        this.sc = sc;
    }

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
}
