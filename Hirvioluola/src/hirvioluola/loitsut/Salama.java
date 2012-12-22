
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;

public class Salama extends LoitsuJolleValitaanRuutu {
    
    private int vahinko;
    private Taistelija kohde;

    public Salama(int kuluttaaMPta, int vahinko, Loitsija loitsija) {
        super(kuluttaaMPta, loitsija);
        this.vahinko = vahinko;
        if(!(super.loitsija instanceof Pelaaja)){
            kohde = super.loitsija.getTaistelu().getPelaaja();
        }
    }    

    @Override
    public void suorita() {
        super.suorita();
        kohde.vahingoitu(vahinko);
    }

    @Override
    public boolean setRuutu(int x, int y) {
        for(Taistelija hirvio : super.loitsija.getTaistelu().getHirviot()){
            if(hirvio.getX() == x && hirvio.getY() == y){
                kohde = hirvio;
                return true;
            }
        }
        return false;
    }
    
}
