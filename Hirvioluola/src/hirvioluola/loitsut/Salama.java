
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Taistelija;

public class Salama extends LoitsuJolleValitaanKohde {
    
    private int vahinko;
    private Taistelija kohde;

    public Salama(int kuluttaaMPta, int vahinko, Loitsija loitsija) {
        super(kuluttaaMPta, loitsija);
        this.vahinko = vahinko;
    }    

    @Override
    public void suorita() {
        super.suorita();
        kohde.vahingoitu(vahinko);
    }

    @Override
    public void setKohde(Taistelija kohde) {
        this.kohde = kohde;
    }
    
}
