
package hirvioluola.loitsut;

import hirvioluola.domain.Loitsija;
import hirvioluola.domain.Taistelija;

public abstract class LoitsuJolleValitaanKohde extends Loitsu {
    
    public LoitsuJolleValitaanKohde(int kuluttaaMPta, Loitsija loitsija){
        super(kuluttaaMPta, loitsija);
    }
    
    public abstract void setKohde(Taistelija kohde);
}
