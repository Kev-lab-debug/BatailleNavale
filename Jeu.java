package jeu;

import java.util.Scanner;

public class Jeu {
    private Grille grille;
    private int nbCasesRestantes;

    public static void main(String[] args) {
        Jeu jeu = new Jeu();
        jeu.jouer();
    }

    public void jouer() {
        Scanner scanner = new Scanner(System.in);

        int choixNiveau = demanderNiveau(scanner);
        int taille = 0;
        int nbBombes = 0;

        switch (choixNiveau) {
            case 1:
                taille = 5;
                nbBombes = 3;
                break;
            case 2:
                taille = 8;
                nbBombes = 8;
                break;
            case 3:
                taille = 10;
                nbBombes = 15;
                break;
        }

        grille = new Grille(taille, nbBombes);
        nbCasesRestantes = taille * taille - nbBombes;

        while (true) {
            grille.afficherGrille();
            int x = demanderCoordonnee("ligne", scanner);
            int y = demanderCoordonnee("colonne", scanner);

            if (!grille.estValide(x, y)) {
                System.out.println("Coordonnées invalides !");
                continue;
            }

            if (grille.revelerCase(x, y)) {
                System.out.println("BOOM ! Vous avez révélé une bombe !");
                grille.afficherGrille();
                break;
            } else {
                nbCasesRestantes--;
                if (nbCasesRestantes == 0) {
                    System.out.println("Bravo, vous avez gagné !");
                    grille.afficherGrille();
                    break;
                }
            }
        }
    }

    private int demanderNiveau(Scanner scanner) {
        int choix = 0;
        while (choix < 1 || choix > 3) {
            System.out.println("Choisissez votre niveau de difficulté :");
            System.out.println("1. Facile");
            System.out.println("2. Moyen");
            System.out.println("3. Difficile");
            System.out.print("Votre choix : ");
            choix = scanner.nextInt();
        }
        return choix;
    }

    private int demanderCoordonnee(String coordonnee, Scanner scanner) {
        int choix = -1;
        while (choix < 0 || choix >= grille.getTaille()) {
            System.out.print("Entrez la " + coordonnee + " : ");
            choix = scanner.nextInt();
        }
        return choix;
    }
}