package hirvioluola.loitsut;

import hirvioluola.domain.Liittolainen;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

/**
 * Kutsuu liittolaisen pelaajan valitsemaan ruutuun.
 * @author Ville
 */

public class KutsuLiittolainen extends Ruutuloitsu{
    
    private int voima, hpMax;
    
    public KutsuLiittolainen(int voima, int hpMax){
        this.voima = voima;
        this.hpMax = hpMax;
    }

    @Override
    public int kuluttaaMPta() {
        return voima + hpMax;
    }

    @Override
    protected void teeLoitsu(Taistelija loitsija) {
        Liittolainen kutsuttava = new Liittolainen(voima,hpMax,0);
        loitsija.getTaistelu().lisaaOlio(kutsuttava, super.x, super.y);
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        if(!taistelu.taistelukentanSisalla(x, y)){
            return false;
        }
        if(taistelu.olioRuudussa(x, y) != null){
            return false;
        }
        super.x = x;
        super.y = y;
        return true;
    }
    
    @Override
    public String toString(){
        return "Kutsu Liittolainen, voima: " + voima + ", hp: "
                + hpMax + ", kuluttaa: " + kuluttaaMPta() + " mp";
    }

    @Override
    public int vaatiiKokemuspisteita() {
        return (voima + hpMax) / 2;
    }
    
}
