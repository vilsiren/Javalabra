package hirvioluola.loitsut;

import hirvioluola.domain.Taistelija;

public interface Loitsu {
    
    public int kuluttaaMPta();
    public void suorita(Taistelija kohde);
}
