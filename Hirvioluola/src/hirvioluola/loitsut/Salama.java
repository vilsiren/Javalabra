
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Pelaaja;
import hirvioluola.domain.Taistelija;

public class Salama extends LoitsuJolleValitaanRuutu {
    
    private int vahinko;
    private Taistelija kohde;

    public Salama(int kuluttaaMPta, int vahinko) {
        super(kuluttaaMPta);
        this.vahinko = vahinko;
    }    

    @Override
    public void suorita(Loitsija loitsija) {
        super.suorita(loitsija);
        if(!(loitsija instanceof Pelaaja)){
            kohde = loitsija.getTaistelu().getPelaaja();
        }
        kohde.vahingoitu(vahinko);
    }

    @Override
    public boolean setRuutu(int x, int y, Loitsija loitsija) {
        for(Taistelija hirvio : loitsija.getTaistelu().getHirviot()){
            if(hirvio.getX() == x && hirvio.getY() == y){
                kohde = hirvio;
                return true;
            }
        }
        return false;
    }
    
}
