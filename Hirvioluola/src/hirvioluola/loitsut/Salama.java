
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;
import hirvioluola.peli.Taistelu;

public class Salama implements Loitsu, ToimintoJolleValitaanRuutu {
    
    private int vahinko;
    private Taistelija kohde;
    private int kuluttaaMPta;

    public Salama(int kuluttaaMPta, int vahinko) {
        this.kuluttaaMPta = kuluttaaMPta;
        this.vahinko = vahinko;
    }    

    @Override
    public void suorita(Loitsija loitsija) {
        if(!(loitsija instanceof Pelaaja)){
            kohde = loitsija.getTaistelu().getPelaaja();
        }
        kohde.vahingoitu(vahinko);
    }

    @Override
    public boolean setRuutu(int x, int y, Taistelu taistelu) {
        for(Taistelija hirvio : taistelu.getHirviot()){
            if(hirvio.getX() == x && hirvio.getY() == y){
                kohde = hirvio;
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString(){
        return "Salama, vahingoitaa: " + vahinko + " hp, kuluttaa: " + kuluttaaMPta() + " mp";
    }

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }
    
}
