package jeu;

public class Case {
    private boolean bombe;
    private int nbBombesAdjacentes;
    private boolean revelee;
    private boolean dejaDecouverte;

    public Case() {
        this.bombe = false;
        this.nbBombesAdjacentes = 0;
        this.revelee = false;
        this.dejaDecouverte = false;
    }

    public boolean estBombe() {
        return bombe;
    }

    public void setBombe(boolean bombe) {
        this.bombe = bombe;
    }

    public int getNbBombesAdjacentes() {
        return nbBombesAdjacentes;
    }

    public void setNbBombesAdjacentes(int nbBombesAdjacentes) {
        this.nbBombesAdjacentes = nbBombesAdjacentes;
    }

    public boolean estRevelee() {
        return revelee;
    }

    public void setRevelee(boolean revelee) {
        this.revelee = revelee;
    }

    public boolean estDejaDecouverte() {
        return dejaDecouverte;
    }

    public void decouvrirCase() {
        this.dejaDecouverte = true;
    }
}