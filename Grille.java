package jeu;

import java.util.Random;

public class Grille {
    private int taille;
    private int nbBombes;
    private Case[][] cases;

    public Grille(int taille, int nbBombes) {
        this.taille = taille;
        this.nbBombes = nbBombes;
        this.cases = new Case[taille][taille];
        initialiserGrille();
        placerBombes();
        compterBombesAdjacentes();
    }

    public void initialiserGrille() {
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                cases[x][y] = new Case();
            }
        }
    }

    public void placerBombes() {
        Random random = new Random();
        int nbBombesPlacees = 0;

        while (nbBombesPlacees < nbBombes) {
            int x = random.nextInt(taille);
            int y = random.nextInt(taille);
            if (!cases[x][y].estBombe()) {
                cases[x][y].setBombe(true);
                nbBombesPlacees++;
            }
        }
    }

    public void compterBombesAdjacentes() {
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if (!cases[x][y].estBombe()) {
                    int nbBombesAdjacentes = 0;
                    for (int i = -1; i <= 1; i++) {
                        for (int j = -1; j <= 1; j++) {
                            int xVoisin = x + i;
                            int yVoisin = y + j;
                            if (estValide(xVoisin, yVoisin) && cases[xVoisin][yVoisin].estBombe()) {
                                nbBombesAdjacentes++;
                            }
                        }
                    }
                    cases[x][y].setNbBombesAdjacentes(nbBombesAdjacentes);
                }
            }
        }
    }

    public boolean revelerCase(int x, int y) {
        if (!cases[x][y].estRevelee()) {
            cases[x][y].setRevelee(true);
            if (cases[x][y].estBombe()) {
                return true;
            } else if (cases[x][y].getNbBombesAdjacentes() == 0) {
                revelerCasesAdjacentes(x, y);
            }
        }
        return false;
    }

    public void revelerCasesAdjacentes(int x, int y) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int xVoisin = x + i;
                int yVoisin = y + j;
                if (estValide(xVoisin, yVoisin) && !cases[xVoisin][yVoisin].estRevelee()) {
                    revelerCase(xVoisin, yVoisin);
                }
            }
        }
    }

    public int getTaille() {
        return taille;
    }

    public int getNbBombes() {
        return nbBombes;
    }

    public boolean estValide(int x, int y) {
        return x >= 0 && x < taille && y >= 0 && y < taille;
    }
    
    public void afficherGrille() {
        System.out.print("  ");
        for (int x = 0; x < taille; x++) {
            System.out.print(x + " ");
        }
        System.out.println();
        for (int y = 0; y < taille; y++) {
            System.out.print(y + " ");
            for (int x = 0; x < taille; x++) {
                Case c = cases[x][y];
                if (!c.estRevelee()) {
                    System.out.print(". ");
                } else if (c.estBombe()) {
                    System.out.print("* ");
                } else {
                    System.out.print(c.getNbBombesAdjacentes() + " ");
                }
            }
            System.out.println();
        }
    }
    
    public void finDuJeu() {
        System.exit(0);
    }

    public boolean jeuGagne() {
        for (int x = 0; x < taille; x++) {
            for (int y = 0; y < taille; y++) {
                if (!cases[x][y].estRevelee() && !cases[x][y].estBombe()) {
                    return false;
                }
            }
        }
        return true;
    }

    public void decouvrirCase(int x, int y) {
        if (estValide(x, y)) {
            if (!cases[x][y].estDejaDecouverte()) {
                cases[x][y].decouvrirCase();
                if (cases[x][y].estBombe()) {
                    System.out.println("BOOM ! Vous avez perdu.");
                    finDuJeu();
                } else {
                    if (cases[x][y].getNbBombesAdjacentes() == 0) {
                        revelerCasesAdjacentes(x, y);
                    }
                    if (jeuGagne()) {
                        System.out.println("Félicitations, vous avez gagné !");
                        finDuJeu();
                    }
                }
            }
        }
    }
}