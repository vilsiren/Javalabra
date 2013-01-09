package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

/**
 * Palauttaa loitsijan hp:ta.
 * @author Ville
 */

public class Parannus extends Loitsu{
    
    private int parannus;
    
    public Parannus(int parannus){
        this.parannus = parannus;
    }

    @Override
    protected void teeLoitsu(Taistelija loitsija) {
        loitsija.parannu(parannus);
    }
    
    @Override
    public String toString(){
        return "Parannus, parantaa: " + parannus + " hp, kuluttaa: " + kuluttaaMPta() +  "mp";
    }

    @Override
    public int kuluttaaMPta() {
        return parannus / 2;
    }

    @Override
    public int vaatiiKokemuspisteita() {
        return parannus / 2;
    }
}
