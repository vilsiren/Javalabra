
package hirvioluola.loitsut;

import hirvioluola.domain.Hirvio;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Salama extends Loitsu implements ToimintoJolleValitaanRuutu {
    
    private int vahinko;
    private Taistelija kohde;
    private int kuluttaaMPta;

    public Salama(int kuluttaaMPta, int vahinko) {
        this.kuluttaaMPta = kuluttaaMPta;
        this.vahinko = vahinko;
    }    

    @Override
    public void teeLoitsu(Taistelija loitsija) {
        if(!(loitsija instanceof Pelaaja)){
            kohde = loitsija.getTaistelu().getPelaaja();
        }
        kohde.vahingoitu(vahinko);
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        Hirvio hirvio = taistelu.hirvioRuudussa(x,y);
        if(hirvio == null){
            return false;
        }
        else{
            kohde = hirvio;
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
