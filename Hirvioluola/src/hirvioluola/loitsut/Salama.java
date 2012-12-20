
package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

public class Salama implements Loitsu {
    
    private int kuluttaaMPta;
    private int vahinko;

    public Salama(int kuluttaaMPta, int vahinko) {
        this.kuluttaaMPta = kuluttaaMPta;
        this.vahinko = vahinko;
    }    

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }

    @Override
    public void suorita(Taistelija kohde) {
        kohde.vahingoitu(vahinko);
    }
    
}
