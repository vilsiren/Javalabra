
package hirvioluola.loitsut;

import hirvioluola.domain.Vihollinen;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Ruutuolio;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Salama extends Ruutuloitsu {
    
    private int vahinko;
    private Ruutuolio kohde;
    private int kuluttaaMPta;

    public Salama(int kuluttaaMPta, int vahinko) {
        this.kuluttaaMPta = kuluttaaMPta;
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
        return kuluttaaMPta;
    }
    
}
