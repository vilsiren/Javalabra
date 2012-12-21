
package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

public class Salama implements LoitsuJolleValitaanKohde {
    
    private int kuluttaaMPta;
    private int vahinko;
    private Taistelija kohde;

    public Salama(int kuluttaaMPta, int vahinko) {
        this.kuluttaaMPta = kuluttaaMPta;
        this.vahinko = vahinko;
    }    

    @Override
    public int kuluttaaMPta() {
        return kuluttaaMPta;
    }

    @Override
    public void suorita() {
        kohde.vahingoitu(vahinko);
    }

    @Override
    public void setKohde(Taistelija kohde) {
        this.kohde = kohde;
    }
    
}
