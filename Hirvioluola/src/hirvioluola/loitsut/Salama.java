
package hirvioluola.loitsut;

import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

/**
 * Vahingoittaa valitussa ruudussa (jos loitsija on pelaaja) olevaa ruutuoliota,
 * tai pelaajaa (jos loitsija on vihollinen).
 * @author Ville
 */
public class Salama extends Ruutuloitsu {
    
    private int vahinko;
    private Ruutuolio kohde;

    public Salama(int vahinko) {
        this.vahinko = vahinko;
    }    

    @Override
    public void teeLoitsu(Taistelija loitsija) {
        if(loitsija instanceof Vihollinen){
            kohde = loitsija.getTaistelu().getPelaaja();
        }
        kohde.vahingoitu(vahinko);
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        Ruutuolio olio = taistelu.olioRuudussa(x,y);
        if(olio == null){
            return false;
        }
        else{
            kohde = olio;
            return true;
        }
    }
    
    @Override
    public String toString(){
        return "Salama, vahingoittaa: " + vahinko + " hp, kuluttaa: " + kuluttaaMPta() + " mp";
    }

    @Override
    public int kuluttaaMPta() {
        return vahinko +2;
    }

    @Override
    public int vaatiiKokemuspisteita() {
        return vahinko;
    }
    
}
